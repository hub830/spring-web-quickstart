package sample.web.ui.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import sample.web.ui.domain.Order;

@Repository
public interface  OrderRepository extends PagingAndSortingRepository<Order, Long>,CrudRepository<Order, Long>,JpaSpecificationExecutor<Order>{

}