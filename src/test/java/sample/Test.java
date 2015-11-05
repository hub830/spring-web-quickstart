package sample;

import java.io.UnsupportedEncodingException;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.util.ByteSource;

public class Test {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String aaa = "zfIMf0p+q2ZY74lpi7ZX5g==";
//		System.out.println(ByteSource.Util.bytes(aaa));
		

		RandomNumberGenerator rng = new SecureRandomNumberGenerator();
		ByteSource salt = rng.nextBytes();
		System.out.println(salt);
		System.out.println(salt.toString());
		

		System.out.println(ByteSource.Util.bytes(salt.toString()));
		
	}
}
