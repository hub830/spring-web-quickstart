package sample.web.ui.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import sample.web.ui.domain.Administrator;

@Repository
public interface  AdministratorRepository extends PagingAndSortingRepository<Administrator, Long>,CrudRepository<Administrator, Long>,JpaSpecificationExecutor<Administrator>{

//	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Administrator findByName(String name);
	
}