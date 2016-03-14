package sample.web.ui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public abstract class BaseService<T> {
	@Autowired
	private CrudRepository<T, Long> repository;

	public void delete(Long id) {
		repository.delete(id);
	}

	public void delete(T entity) {
		repository.delete(entity);
	}

	public void exists(Long id) {
		repository.exists(id);
	}

	public Iterable<T> findAll() {
		return repository.findAll();
	}


	public T findOne(Long id) {
		return repository.findOne(id);
	}

	@Transactional
	public T save(T entity) {
		return repository.save(entity);
	}

}
