package sample.web.ui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import sample.web.ui.domain.Role;
import sample.web.ui.repository.RoleRepository;

/**
 * 
 * @author zhu
 * 
 */
@Service
public class RoleService {

	@Autowired
	private RoleRepository repository;

	
	public Role get(Long id) {
		return repository.findOne(id);
	}

	public Iterable<Role> findAll() {
		return repository.findAll();
	}

	public Iterable<Role> findByIssys(boolean sys){
		return repository.findByIssys(sys);
	}
	public Page<Role> findAll(Specification<Role> spec, PageRequest pageRequest) {
		return repository.findAll(spec,pageRequest);
	}

	public Role findByName(String name) {
		return repository.findByName(name);
	}

	public Role createRole(String name,String description)
	{
		Role role = new Role(name, description);
		return repository.save(role);
	}

	public Role save(Role role) {
		return repository.save(role);
	}

	public void delete(Long id) {
		repository.delete(id);
	}

}
