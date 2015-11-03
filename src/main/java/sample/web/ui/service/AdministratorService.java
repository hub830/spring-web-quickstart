package sample.web.ui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import sample.web.ui.domain.Administrator;
import sample.web.ui.repository.AdministratorRepository;
import sample.web.ui.utils.PasswordHelper;

/**
 * 用户管理.
 * 
 * @author zhu
 * 
 */
@Service
public class AdministratorService {

	@Autowired
	private AdministratorRepository administratorRepository;


	public AdministratorService() {
		super();
	}

	public void save(Administrator administrator) {
		administratorRepository.save(administrator);
	}

	public void delete(Long id) {
		administratorRepository.delete(id);
	}

	public Administrator get(Long id) {
		return administratorRepository.findOne(id);
	}

	public Administrator findByName(String name) {
		return administratorRepository.findByName(name);
	}

	
	public Iterable<Administrator> findAll() {
		return administratorRepository.findAll();
	}

	public Page<Administrator> findAll(Specification<Administrator> spec, PageRequest pageRequest) {
		return administratorRepository.findAll(spec,pageRequest);
	}
	
	public Administrator createAdmin(String name,String password,String email,boolean block)throws Exception
	{
		Administrator admin = new Administrator(name,email,password);
		Administrator admin2 = findByName(admin.getName());
		if(admin2 != null)
			throw new Exception("用户名已经存在!");
		admin.setBlock(block);
		admin.setPassword(PasswordHelper.encryptPassword(admin.getPassword(),admin.getName()));
		return administratorRepository.save(admin);
	}
}
