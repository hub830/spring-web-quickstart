package sample.web.ui.repository;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import sample.web.ui.domain.TestA;

@Repository
public interface  TestARepository extends PagingAndSortingRepository<TestA, Long>,CrudRepository<TestA, Long>,JpaSpecificationExecutor<TestA>{
	

//	@Transactional//(propagation=Propagation.MANDATORY)
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public TestA findById(Long id);
	

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	TestA findOne(Long id);
}