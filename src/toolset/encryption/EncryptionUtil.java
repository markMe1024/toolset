package toolset.encryption;

import static toolset.string.StringUtil.*;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * @Description 加密工具类
 * @Author .Mark
 * @Date 2018年5月14日
 */
public class EncryptionUtil {

	/**
	 * @Description	md5加密
	 * @Author .Mark
	 * @Date 2018年5月14日
	 */
	public static String md5Encryption(String input) {
		try {
			//limit input parameter
			if(input == null || "".equals(input)) { return ""; }
			
			//Returns a MessageDigest object that implements the specified digest algorithm.
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");

			//Updates the digest using the specified array of bytes.
			messageDigest.update(input.getBytes("UTF-8"));
			
			//Completes the hash computation by performing final operations such as padding.
			byte[] byteArray = messageDigest.digest();
			
			//byte[] convert to hex string
			char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F' }; 
			char[] resultCharArray =new char[byteArray.length * 2];  
			int index = 0;  
			for(byte b : byteArray) {  
				resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];  
				resultCharArray[index++] = hexDigits[b & 0xf];  
			}  
			
			return new String(resultCharArray);
		}catch (Exception e) {
			return null;
		}
	}
	
	/**
     * @Description java8 base64 编码
     * @Author .Mark
     * @Date 2018年11月6日
     */
    public static String enCodeBase64(String toBeEncryptedStr) {
        if (nullOrEmpty(toBeEncryptedStr)) { 
            return toBeEncryptedStr; 
        }
        
        try {
            return Base64.getEncoder().encodeToString(toBeEncryptedStr.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } 
        
        return "";
    }
    
    /**
     * @Description java8 base64 解码
     * @Author .Mark
     * @Date 2018年11月6日
     */
    public static String decodeBase64(String encryptedStr) {
        if (nullOrEmpty(encryptedStr)) {
            return encryptedStr;
        }
        
        byte[] asBytes = Base64.getDecoder().decode(encryptedStr);
        try {
            return new String(asBytes, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } 
        
        return "";
    }
}
