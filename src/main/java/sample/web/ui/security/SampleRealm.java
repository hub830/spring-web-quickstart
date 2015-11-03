package sample.web.ui.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sample.web.ui.domain.Administrator;
import sample.web.ui.domain.Role;
import sample.web.ui.exception.CaptchaException;
import sample.web.ui.repository.AdministratorRepository;
import sample.web.ui.utils.CaptchaUtil;

//TODO DefaultPasswordService

@Component
public class SampleRealm extends AuthorizingRealm {

	@Autowired
	private AdministratorRepository administratorRepository;

	public SampleRealm() {
		setName("SampleRealm");
		HashedCredentialsMatcher hashService = new HashedCredentialsMatcher("SHA-256"); // 默认算法SHA-512
		setCredentialsMatcher(hashService);
		setAuthorizationCachingEnabled(false);
	}

	public SampleRealm(AdministratorRepository administratorRepository) {
		this.administratorRepository = administratorRepository;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
//		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		UsernamePasswordCaptchaToken token = (UsernamePasswordCaptchaToken) authcToken;
		Administrator administrator = administratorRepository.findByName(token.getUsername());
		String captcha = token.getCaptcha();
		String exitCode = (String) SecurityUtils.getSubject().getSession().getAttribute(CaptchaUtil.KEY_CAPTCHA);
		if (null == captcha || !captcha.equalsIgnoreCase(exitCode)) {
			throw new CaptchaException("验证码错�?");
		}
		if (administrator != null && administrator.getBlock() == false) {
			return new SimpleAuthenticationInfo(administrator.getId(), administrator.getPassword(), ByteSource.Util.bytes(administrator.getName()), getName());
		} else {
			return null;
		}
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		long administratorId = (long) principals.fromRealm(getName()).iterator().next();
		Administrator administrator = administratorRepository.findOne(administratorId);
		if (administrator != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			for (Role role : administrator.getRoles()) {
				info.addRole(role.getName());
				info.addStringPermissions(role.getPermissions());
			}
			return info;
		} else {
			return null;
		}
	}

}
