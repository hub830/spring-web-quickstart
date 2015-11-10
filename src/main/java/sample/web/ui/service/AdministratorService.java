package sample.web.ui.service;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import sample.web.ui.domain.Administrator;
import sample.web.ui.repository.AdministratorRepository;

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
		Administrator old = findByName(admin.getName());
		if(old != null)
			throw new Exception("用户名已经存在!");
		admin.setBlock(block);
		
		RandomNumberGenerator rng = new SecureRandomNumberGenerator();
		ByteSource salt = rng.nextBytes();

		admin.setSalt(salt.toString());
		admin.setPassword( new Sha256Hash(password,salt.toString()).toHex() );

		return administratorRepository.save(admin);
	}
}
