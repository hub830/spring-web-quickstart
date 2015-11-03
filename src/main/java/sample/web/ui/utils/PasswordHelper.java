package sample.web.ui.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import sample.web.ui.domain.Administrator;

public class PasswordHelper {
//	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
//	private static final int hashIterations = 2;
	private static String algorithmName = "SHA-256";

	public static void encryptPassword(Administrator administrator) {
		String newPassword = new SimpleHash(algorithmName, administrator.getPassword(), ByteSource.Util.bytes(administrator.getName())).toHex();
		administrator.setPassword(newPassword);
	}
	public static String encryptPassword(String oldPassowrd,String sign)
	{
		String password = new SimpleHash(algorithmName, oldPassowrd, ByteSource.Util.bytes(sign)).toHex();
		return password;
	}
}
