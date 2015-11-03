package sample.web.ui.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import sample.web.ui.domain.TestB;

@Repository
public interface  TestBRepository extends PagingAndSortingRepository<TestB, Long>,CrudRepository<TestB, Long>,JpaSpecificationExecutor<TestB>{

	public List<TestB> findByTestAId(Long testAId); 
}