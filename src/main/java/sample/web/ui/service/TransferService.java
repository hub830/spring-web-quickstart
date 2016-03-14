package sample.web.ui.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import sample.web.ui.domain.Transfer;
import sample.web.ui.enums.TransferStatus;
import sample.web.ui.repository.TransferRepository;
import sample.web.ui.utils.HttpClientUtil;
import sample.web.ui.utils.ProxyUtil;
import sample.web.ui.utils.Response;

/**
 * 用户管理.
 * 
 * @author zhu
 * 
 */
@Service
public class TransferService extends BaseService<Transfer> {
	private static final Logger log = LoggerFactory.getLogger(TransferService.class);

	private static String url1 = "http://fwjy1.bjchy.gov.cn/yyfh.appointment.do?m=add";
	private static String url2 = "http://fwjy1.bjchy.gov.cn/yyfh.appointment.do";
	// @SuppressWarnings("unused")
	@Autowired
	private TransferRepository repository;

	public TransferService() {
		super();
	}

	public Iterable<Transfer> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Iterable<Transfer> findAll(Specification<Transfer> spec) {
		return repository.findAll(spec);
	}

	public Iterable<Transfer> findAll(Specification<Transfer> spec, Pageable pageable) {
		return repository.findAll(spec, pageable);
	}

	public Iterable<Transfer> findAll(Specification<Transfer> spec, Sort sort) {
		return repository.findAll(spec, sort);
	}

	public long count(Specification<Transfer> spec) {
		return repository.count(spec);
	}

	/**
	 * 获取所有未处理的请求
	 * 
	 * @return
	 */
	public List<Transfer> findUnprocessed() {
		List<TransferStatus> list = new ArrayList<TransferStatus>();
		list.add(TransferStatus.INIT);
		list.add(TransferStatus.WAIT);
		return repository.findByStatusIn(list);
	}

	public Transfer step1(Long id) {
		log.info("step1 id:{}",id);
		Transfer transfer = findOne(id);
		transfer.setProxy(ProxyUtil.getProxy());
		try {
			transfer = getServerId(transfer);
			getAgreement(transfer);
			transfer = getSign(transfer);
			transfer = save(transfer);
		} catch (Exception e) {
			log.error("",e);
		}
		return transfer;
	}

	public void step2(Long id,String captch) {
		log.info("step1 id:{},captch:{}",id,captch);
		Transfer transfer = findOne(id);
		transfer.setJ_captcha_response(captch);
		try {
			getSubmit(transfer);
		} catch (Exception e) {
			log.error("",e);
		}
	}
	/**
	 * 获取服务ID
	 * @param transfer
	 * @return
	 * @throws XPatherException
	 */
	private Transfer getServerId(Transfer transfer) throws XPatherException {
		log.info("获取服务ID transfer:{}",transfer);
		String xpath = ".//*[@id='contable']/tbody/tr[2]/td/table/tbody/tr[1]/td[2]/select/option[3]";
		Response response = HttpClientUtil.get(url1, transfer.getProxy(), null);
		HtmlCleaner cleaner = new HtmlCleaner();
		TagNode tn = cleaner.clean(response.getContent());
		Object[] array = null;
		array = tn.evaluateXPath(xpath);
		if (array != null && array.length > 0) {
			TagNode tntr = (TagNode) array[0];
			String value = tntr.getAttributeByName("value");
			transfer.setServierId(value);
			log.info(value);
		}
		transfer.setCookie(response.getCookie());
		return transfer;
	}

	/**
	 * 获取协议
	 * @param transfer
	 */
	private void getAgreement(Transfer transfer) {
		log.info("获取协议 transfer:{}",transfer);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("p_Appointment_server_id", transfer.getServierId()));
		params.add(new BasicNameValuePair("service_ne", transfer.getServierId()));
		params.add(new BasicNameValuePair("m", "edit"));
		HttpClientUtil.get(url2, transfer.getProxy(), transfer.getCookie());
	}

	/**
	 * 同意协议
	 * @param transfer
	 * @return
	 * @throws XPatherException
	 * @throws InterruptedException
	 */
	private Transfer getSign(Transfer transfer) throws XPatherException, InterruptedException {
		log.info("获取验证码 transfer:{}",transfer);
		String xpath = "//table[@id='contable']//img";
		Thread.sleep(20000);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("p_Appointment_id", transfer.getServierId()));
		params.add(new BasicNameValuePair("serviceid", transfer.getServierId()));
		params.add(new BasicNameValuePair("m", "showAddPage"));
		Response response = HttpClientUtil.get(url2, transfer.getProxy(), transfer.getCookie());

		HtmlCleaner cleaner = new HtmlCleaner();
		TagNode tn = cleaner.clean(response.getContent());
		Object[] objarr = null;
		objarr = tn.evaluateXPath(xpath);

		if (objarr != null && objarr.length > 0) {
			for (Object obj : objarr) {
				TagNode tntr = (TagNode) obj;
				String value = tntr.getAttributeByName("src");
				transfer.setJ_captcha_response(value);
				log.info(value);
			}
		}
		return transfer;
	}

	private Transfer getSubmit(Transfer transfer) throws XPatherException, InterruptedException {
		log.info("申请 transfer:{}",transfer);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("amorpm", transfer.getServierId()));
		params.add(new BasicNameValuePair("info", "0"));
		params.add(new BasicNameValuePair("j_captcha_response", transfer.getServierId()));
		params.add(new BasicNameValuePair("m", "saveAdd"));
		params.add(new BasicNameValuePair("p_Appointment_app_1", transfer.getP_Appointment_app_1()));
		params.add(new BasicNameValuePair("p_Appointment_app_2", transfer.getSlwq()+transfer.getWqhth()));
		params.add(new BasicNameValuePair("p_Appointment_app_3", transfer.getP_Appointment_app_3()));
		params.add(new BasicNameValuePair("p_Appointment_app_4", transfer.getP_Appointment_app_4()));
		params.add(new BasicNameValuePair("p_Appointment_app_5", transfer.getP_Appointment_app_5()));
		params.add(new BasicNameValuePair("p_Appointment_app_6", transfer.getP_Appointment_app_6()));
		params.add(new BasicNameValuePair("p_Appointment_app_7", transfer.getP_Appointment_app_7()));
		params.add(new BasicNameValuePair("p_Appointment_app_8", transfer.getP_Appointment_app_8()));
		params.add(new BasicNameValuePair("p_Appointment_app_9", transfer.getP_Appointment_app_9()));
		params.add(new BasicNameValuePair("p_Appointment_app_10", transfer.getP_Appointment_app_10()));
		params.add(new BasicNameValuePair("p_Appointment_app_11", transfer.getP_Appointment_app_11()));
		params.add(new BasicNameValuePair("p_Appointment_app_12", transfer.getP_Appointment_app_12()));
		params.add(new BasicNameValuePair("p_Appointment_app_13", transfer.getP_Appointment_app_13()));
		params.add(new BasicNameValuePair("p_Appointment_app_14", transfer.getP_Appointment_app_14()));
		params.add(new BasicNameValuePair("p_Appointment_app_15", transfer.getP_Appointment_app_15()));
		params.add(new BasicNameValuePair("p_Appointment_app_16", transfer.getP_Appointment_app_16()));
		params.add(new BasicNameValuePair("p_Appointment_app_17", transfer.getP_Appointment_app_17()));
		params.add(new BasicNameValuePair("p_Appointment_app_date", transfer.getP_Appointment_app_date()));
		params.add(new BasicNameValuePair("p_Appointment_phonenumber", transfer.getP_Appointment_phonenumber()));
		params.add(new BasicNameValuePair("p_Appointment_testmsg", md5(transfer.getP_Appointment_testmsg())));
		params.add(new BasicNameValuePair("p_Appointment_testmsgtemp", transfer.getP_Appointment_testmsg()));
		params.add(new BasicNameValuePair("s", transfer.getS()));
		params.add(new BasicNameValuePair("server_id", transfer.getServierId()));
		params.add(new BasicNameValuePair("serviceid", transfer.getServierId()));
		params.add(new BasicNameValuePair("slwq", transfer.getSlwq()));
		params.add(new BasicNameValuePair("sx", transfer.getSx()));
		params.add(new BasicNameValuePair("yybh", transfer.getSlwq()+transfer.getWqhth()));
		Response response = HttpClientUtil.get(url2, transfer.getProxy(), transfer.getCookie());
		
		log.info("申请结果:{}",response.getContent());
		return transfer;
	}
	
	private static String md5(String msg) {
		String md5 = DigestUtils.md5Hex("msg");
		return md5.substring(8, 24).toUpperCase();
	}
}
