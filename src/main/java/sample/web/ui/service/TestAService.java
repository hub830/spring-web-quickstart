package sample.web.ui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sample.web.ui.domain.TestA;
import sample.web.ui.repository.TestARepository;

/**
 * 区域
 * @author zhu
 * 
 */
@Service
public class TestAService {

	@Autowired
	private TestARepository repository;

	@Autowired
	private TestBService testBService;
	


	public TestAService() {
		super();
	}

	
	public TestA save(TestA area) {
		return repository.save(area);
	}

	public void delete(TestA area) {
		repository.delete(area);
	}
	
	public TestA get(Long id) {
		return repository.findOne(id);
	}
	
	
	public Page<TestA> findAll(Specification<TestA> spec, PageRequest pageRequest) {
		return repository.findAll(spec,pageRequest);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void test1() {
		TestA testA = new TestA();
		testA = repository.save(testA);
		testBService.test1();
//		throw new NullPointerException();
	}
	
	@Transactional
	public void test2(Long id)
	{
		TestA testA = repository.findOne(id);
		testA.setA(testA.getA()+1);
		repository.save(testA);
	}
	
	@Transactional
	public void test3(Long id)
	{
		TestA testA = repository.findOne(id);
		testA.setA(testA.getA()+1);
		testA.setB(testA.getB()+1);
		repository.save(testA);
	}
}
