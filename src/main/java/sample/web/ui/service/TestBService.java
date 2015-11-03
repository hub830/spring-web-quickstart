package sample.web.ui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import sample.web.ui.domain.TestB;
import sample.web.ui.repository.TestBRepository;

/**
 * 区域
 * @author zhu
 * 
 */
@Service
public class TestBService {

	@Autowired
	private TestBRepository repository;



	public TestBService() {
		super();
	}

	
	public TestB save(TestB area) {
		return repository.save(area);
	}

	public void delete(TestB area) {
		repository.delete(area);
	}
	
	public TestB get(Long id) {
		return repository.findOne(id);
	}
	
	
	public Page<TestB> findAll(Specification<TestB> spec, PageRequest pageRequest) {
		return repository.findAll(spec,pageRequest);
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void test1()
	{
		TestB testB = new TestB();
		testB = repository.save(testB);
	}
}
