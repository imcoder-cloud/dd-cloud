package fun.imcoder.cloud.base.utils;

import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	private static final String SLAT = "&%5123***&&%%$$#@";


	/**
	 * 获得加密后的16进制形式口令
	 * 
	 * @param str
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String md5Str(String str)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// 声明加密后的口令数组变量
		String base = str +"/"+SLAT;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

	/**
	 * 验证口令是否合法
	 * 
	 * @param password     输入的密码
	 * @param passwordInDb 数据库获取的加密密码
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static boolean valid(String password, String passwordInDb) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		// 将16进制字符串格式口令转换成字节数组
		String pwdInDb = md5Str(passwordInDb);
		// 比较根据输入口令生成的消息摘要和数据库中消息摘要是否相同
		if (pwdInDb.equals(password)) {
			// 口令正确返回口令匹配消息
			return true;
		} else {
			// 口令不正确返回口令不匹配消息
			return false;
		}
	}
}
