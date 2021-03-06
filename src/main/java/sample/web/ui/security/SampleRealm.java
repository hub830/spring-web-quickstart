package sample.web.ui.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
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
//		HashedCredentialsMatcher hashService = new HashedCredentialsMatcher("SHA-256"); // 默认算法SHA-512
//		setCredentialsMatcher(hashService);
//		setAuthorizationCachingEnabled(false);
//		
//		setCredentialsMatcher(new Sha256CredentialsMatcher());
	    HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
	    matcher.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
//	    matcher.setHashIterations(1024);
	    setCredentialsMatcher(matcher);
	}

	public SampleRealm(AdministratorRepository administratorRepository) {
		this.administratorRepository = administratorRepository;
	}

    @Override  
    public boolean supports(AuthenticationToken token) {  
        //仅支持UsernamePasswordToken类型的Token  
        return token instanceof UsernamePasswordCaptchaToken;   
    } 
    
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
//		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		UsernamePasswordCaptchaToken token = (UsernamePasswordCaptchaToken) authcToken;
		Administrator administrator = administratorRepository.findByName(token.getUsername());
		String captcha = token.getCaptcha();
		String exitCode = (String) SecurityUtils.getSubject().getSession().getAttribute(CaptchaUtil.KEY_CAPTCHA);
		if (null == captcha || !captcha.equalsIgnoreCase(exitCode)) {
			throw new CaptchaException("验证码错");
		}
		if (administrator != null && administrator.getBlock() == false) {
//			return new SimpleAuthenticationInfo(administrator.getId(), administrator.getPassword(), ByteSource.Util.bytes(administrator.getName()), getName());

	        SimpleAuthenticationInfo ai = new SimpleAuthenticationInfo(administrator.getId(), administrator.getPassword(), getName());
	        ai.setCredentialsSalt(ByteSource.Util.bytes(administrator.getSalt())); //盐是随机数
	        return ai;
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
