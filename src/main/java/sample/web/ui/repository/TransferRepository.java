package sample.web.ui.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import sample.web.ui.domain.Transfer;
import sample.web.ui.enums.TransferStatus;

@Repository
public interface  TransferRepository extends PagingAndSortingRepository<Transfer, Long>,JpaSpecificationExecutor<Transfer>{
	public List<Transfer> findByStatusIn(Collection<TransferStatus> status);
}