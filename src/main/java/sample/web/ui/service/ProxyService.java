package sample.web.ui.service;

import org.springframework.beans.factory.annotation.Autowired;
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

}
