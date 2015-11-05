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
		Administrator admin2 = findByName(admin.getName());
		if(admin2 != null)
			throw new Exception("用户名已经存在!");
		admin.setBlock(block);
		
		
		
		
		RandomNumberGenerator rng = new SecureRandomNumberGenerator();
		ByteSource salt = rng.nextBytes();

		//我们的纯文本密码经过散列随机盐和多次迭代，
		//得到Base64编码的值（比Hex需要较少的空间）：
		String hashedPasswordBase64 = new Sha256Hash(password, salt.getBytes()).toString();

		//在新帐户保存盐。该 HashedCredentialsMatcher
		//稍后再的登录尝试的时候会处理它：
		admin.setSalt(salt.toString());
		
		
		
		
		
		
		
		
		
		
		
		
		
		

//		  Object hashedPassword=new Sha1Hash("password",salt);
		

//		admin.setPassword( new Sha256Hash(password,salt).toHex() );
		admin.setPassword( new Sha256Hash(password).toHex() );
		
//		admin.setPassword(hashedPasswordBase64);
		return administratorRepository.save(admin);
	}
}
