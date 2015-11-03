package sample.web.ui.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import sample.web.ui.domain.Role;

@Repository
public interface  RoleRepository extends PagingAndSortingRepository<Role, Long>,CrudRepository<Role, Long>,JpaSpecificationExecutor<Role>{

	Role findByName(String name);

	List<Role> findByIssys(boolean sys);
}