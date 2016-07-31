package com.forerunner.core.tool;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 宸ュ叿绫�
 *
 */
public final class CommUtil {
	
	private static final String DEFAULT_FORMAT="yyyy-MM-dd HH:mm:ss";

	private CommUtil() {
		super();
	}

	/**
	 * 
	 * @param v
	 * @return
	 */
	public static String null2String(Object v) {
		return null2String(v, "");
	}

	/**
	 * 瀵硅薄杞琒tring 
	 * 
	 * @param obj
	 * @param defaultV
	 * @return
	 */
	public static String null2String(Object obj, String defaultV) {
		return obj==null||obj.toString().trim().length()<1? defaultV: obj.toString().trim() ;
	}

	/**
	 * 瀵硅薄杞琁nt 榛樿0
	 * 
	 * @param obj
	 * @return
	 */
	public static int null2Int(Object obj) {
		return null2Int(obj, 0);
	}

	/**
	 * 瀵硅薄杞琁nt
	 * 
	 * @param obj
	 * @param defaultV
	 * @return
	 */
	public static int null2Int(Object obj, int defaultV) {
		int v = defaultV;
		if (obj != null) {
			try {
				v = Integer.parseInt(null2String(obj));
			} catch (Exception e) {

			}
		}
		return v;
	}

	/**
	 * 瀵硅薄杞現loat 榛樿0.0F
	 * 
	 * @param obj
	 * @return
	 */
	public static float null2Float(Object obj) {
		return null2Float(obj, 0.0F);
	}

	/**
	 * 瀵硅薄杞現loat
	 * 
	 * @param obj
	 * @param defaultV
	 * @return
	 */
	public static float null2Float(Object obj, float defaultV) {
		float v = defaultV;
		if (obj != null) {
			try {
				v = Float.parseFloat(null2String(obj));
			} catch (Exception e) {

			}
		}
		return v;
	}

	/**
	 * 瀵硅薄杞珼ouble 榛樿0.0D
	 * 
	 * @param obj
	 * @return
	 */
	public static double null2Double(Object obj) {
		return null2Double(obj, 0.0D);
	}

	/**
	 * 瀵硅薄杞珼ouble
	 * 
	 * @param obj
	 * @param defaultV
	 * @return
	 */
	public static double null2Double(Object obj, double defaultV) {
		double v = 0.0D;
		if (obj != null) {
			try {
				v = Double.parseDouble(null2String(obj));
			} catch (Exception e) {

			}
		}
		return v;
	}

	/**
	 * 瀵硅薄杞琇ong 榛樿0L
	 * 
	 * @param obj
	 * @return
	 */
	public static long null2Long(Object obj) {
		return null2Long(obj, 0L);
	}

	/**
	 * 瀵硅薄杞琇ong
	 * 
	 * @param obj
	 * @param defaultV
	 * @return
	 */
	public static long null2Long(Object obj, long defaultV) {
		long v = 0L;
		if (obj != null) {
			try {
				v = Long.parseLong(null2String(obj));
			} catch (Exception e) {

			}
		}
		return v;
	}

	/**
	 * 对象转boolean<br>
	 * 支持yes/no 0/1 true/false<br>
	 * 默认 false<br>
	 * @param obj
	 * @return
	 */
	public static boolean null2Boolean(Object obj) {
		return null2Boolean(obj, false);
	}

	/**
	 * 对象转boolean<br>
	 * 支持yes/no 0/1 true/false<br>
	 * @param obj
	 * @param defaultV
	 * @return
	 */
	public static boolean null2Boolean(Object obj, boolean defaultV) {
		boolean v = defaultV;
		String s=null2String(obj);
		if("yes".equalsIgnoreCase(s)||"0".equals(s)||"true".equalsIgnoreCase(s)){
			v=true;
		}
		if("no".equalsIgnoreCase(s)||"1".equals(s)||"false".equalsIgnoreCase(s)){
			v=false;
		}
		return v;
	}

	/**
	 *褰撳墠IP鍦板潃
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		ip = ip.contains(",") ? ip.split(",")[0] : ip;
		return ip;
	}

	/**
	 * Double 鏍煎紡鍖� 淇濈暀2涓哄皬鏁�
	 * 
	 * @param money
	 * @return
	 */
	public static Double reservedDecimal(Object money) {
		return reservedDecimal(money, 2);
	}

	/**
	 * Double鏍煎紡鍖� 
	 * 
	 * @param money
	 * @param precision 淇濈暀灏忔暟
	 * @return
	 */
	public static Double reservedDecimal(Object money, int precision) {
		precision = precision < 0 ? 0 : precision;
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(precision);
		df.setGroupingSize(0);
		df.setRoundingMode(RoundingMode.FLOOR);
		return null2Double(df.format(null2Double(money)), 0.0D);
	}

	public static double formatMoney(Object money) {
		return 0D;
	}

	/**
	 * 是否为空<br>
	 * obj==null:false<br>
	 * obj=="":false<br>
	 * obj=="     ":false<br>
	 * obj=="  1  ":true<br>
	 * obj==" 1":true<br>
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNotNull(Object obj) {
		String v = null2String(obj, "");
		if (v == null || v.length() < 0 || "".equals(v)) {
			return false;
		}
		return true;
	}

	/**
	 * 鐢ㄦ埛鍚嶉殣钘�
	 * @return
	 */
	public static String hidename(Object obj) {
		if (isNotNull(obj)) {
			String v = null2String(obj);
			// 濡傛灉鏄墜鏈哄彿鐮�
			if (isMobile(obj)) {
				return v.substring(0, 3).concat("****").concat(v.substring(7));
			}
			// 濡傛灉鏄偖绠卞湴鍧�
			if (isEmail(obj)) {
				String str = v.split("@")[0];
				int len = str.length();
				if (len < 4) {
					return "****".concat(v.substring(len));
				}
				int x = len - 4;
				int a = x / 2;
				return str.substring(0, a).concat("****").concat(v.substring(a + 4));
			}
			int len = v.length();
			if (len <= 4) {
				return v;
			}
			int x = len - 4;
			int a = x / 2;
			return v.substring(0, a).concat("****").concat(v.substring(a + 4));
		}
		return "";
	}

	/**
	 * 是否是手机号码
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isMobile(Object obj) {
		String v = null2String(obj);
		String reg = "^[1][3,4,5,7,8][0-9]{9}$";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(v);
		return m.matches();
	}
	/**
	 * 鏁板瓧杞崲鎴怋igDecimal 濡傛灉涓嶆槸鏁板瓧 鍒欒繑鍥瀗ull 
	 * @param obj
	 * @return
	 */
	public static BigDecimal getBigDecimal(Object obj) {
		if (obj instanceof BigDecimal) {
			return  (BigDecimal) obj;
		} else {
			String s = null2String(obj, "");
			if(isNumber(s)){
				return new BigDecimal(s);
			}
		}
		return null;
	}
	/**
	 * 鏄惁鏄墜鏈�
	 * @param obj
	 * @return
	 */
	public static boolean isNumber(Object obj) {
		String v = null2String(obj);
		String reg = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(v);
		return m.matches();
	}

	/**
	 * 鏄惁鏄偖绠�
	 * @param obj
	 * @return
	 */
	public static boolean isEmail(Object obj) {
		String v = null2String(obj);
		String reg = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(v);
		return m.matches();
	}
	/**
	 * 随机生成唯一的字符串
	 * @return
	 */
	public static synchronized String getSalt(){
		String uuid=UUID.randomUUID().toString();
		uuid= Md5Encrypt.md5(uuid.replace("-", "")).toUpperCase();
		return uuid;
	}
	
	public static String getCatche(int size){
		String randString="123456789abcdefghjkmnpqrstuvwxy";
		String str="";
		while(str.length()<size){
			str+=randString.charAt(RandomUtils.nextInt(randString.length()));
		}		
		return str.toUpperCase();
	}
	
	public static Color getRandColor(int fc,int bc){
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + RandomUtils.nextInt(bc - fc - 16);
		int g = fc + RandomUtils.nextInt(bc - fc - 14);
		int b = fc + RandomUtils.nextInt(bc - fc - 18);
		return new Color(r, g, b);
	}
	public static void drowLine(Graphics g,int width,int height) {
		int x = RandomUtils.nextInt(width);
		int y = RandomUtils.nextInt(height);
		int xl =RandomUtils.nextInt(13);
		int yl = RandomUtils.nextInt(15);
		g.drawLine(x, y, x + xl, y + yl);
	}
	
	public static void drowString(Graphics g, String randomString,Font font) {
		g.setFont(font);
		g.setColor(new Color(RandomUtils.nextInt(101), RandomUtils.nextInt(111), RandomUtils.nextInt(121)));	
		g.translate(RandomUtils.nextInt(3), RandomUtils.nextInt(3));
		char chs[]=randomString.toCharArray();
		int i=0;
		for(char ch:chs){
			g.drawString(String.valueOf(ch), 13 * i, 16);
			i++;
		}	
	}
	
	public static String encrypt(String password,String salt){
		String encodePassword=Md5Encrypt.md5(password);
		String findpassword= Md5Encrypt.md5(encodePassword+salt);
		return findpassword;
	}
	/**
	 * 日期格式化
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatData(Date date,String format){
		if(date==null){
			date=new Date();
		}
		if(StringUtils.isBlank(format)){
			format=DEFAULT_FORMAT;
		}
		SimpleDateFormat sim=new SimpleDateFormat(format);
		return sim.format(date);
	}
	
	public static String formatDate(){
		return formatData(null,"");
	}
	
	public static String formatDate(Date date){
		return formatData(date,"");
	}
	
	public static String formatDate(String format){
		return formatData(null,format);
	}
	
	
	public static void main(String[] args) {
		System.out.println(CommUtil.reservedDecimal(12.01955555));
		System.out.println(isNotNull("   "));
		System.out.println(formatData(null,"yyyyMM"));
		System.out.println(hidename("15889414790"));
		System.out.println(getBigDecimal("123456222546546846465.0002123154156"));
		
	}
	
}
