package sample.web.ui.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import sample.web.ui.domain.Proxy;

@Repository
public interface  ProxyRepository extends PagingAndSortingRepository<Proxy, Long>,JpaSpecificationExecutor<Proxy>{

	public Proxy findByIp(String ip);
}