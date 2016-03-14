package sample.web.ui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sample.web.ui.domain.Proxy;
import sample.web.ui.repository.ProxyRepository;

/**
 * 用户管理.
 * 
 * @author zhu
 * 
 */
@Service
public class ProxyService extends BaseService<Proxy> {

//	@SuppressWarnings("unused")
	@Autowired
	private ProxyRepository repository;


	public ProxyService() {
		super();
	}
	
	public Proxy findByIP(String ip) {
		return repository.findByIp(ip);
	}
	
	public boolean exists(String ip) {
		Proxy proxy = repository.findByIp(ip);
		if(proxy==null) {
			return false;
		}
		return true;
	}
	
	public Page<Proxy> findAll(Pageable pageable){
		return repository.findAll(pageable);
	}
	
	public void checkAll() {
		int i = 0;
		boolean hasnext = checkAll(i);
		while(hasnext) {
			checkAll(i++);
		}
	}
	public boolean checkAll(int pageNo) {
		Pageable pageable = new   PageRequest(pageNo, 10);
		Page<Proxy> page = repository.findAll(pageable);
		

		return page.hasNext();
	}
}
