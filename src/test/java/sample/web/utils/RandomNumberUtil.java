package sample.web.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

/**
 * @author yihuan
 * @version RandomNumberUtil.java 2010-1-14 上午11:39:20
 */
public class RandomNumberUtil {

	private static final int[] prefix = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	/**
	 * 随机产生最大为18位的long型数据(long型数据的最大值是9223372036854775807,共有19位)
	 * 
	 * @param digit
	 *            用户指定随机数据的位数
	 */
	public static String randomStringLong(int digit) {
		return String.valueOf(randomLong(digit));
	}

	/**
	 * 随机产生最大为18位的long型数据(long型数据的最大值是9223372036854775807,共有19位)
	 * 
	 * @param digit
	 *            用户指定随机数据的位数
	 */
	public static long randomLong(int digit) {
		if (digit >= 19 || digit <= 0)
			throw new IllegalArgumentException("digit should between 1 and 18(1<=digit<=18)");
		String s = RandomStringUtils.randomNumeric(digit - 1);
		return Long.parseLong(getPrefix() + s);
	}

	/**
	 * 保证第一位不是零
	 * 
	 * @return
	 */
	private static String getPrefix() {
		return prefix[RandomUtils.nextInt(1, 9)] + "";
	}
}