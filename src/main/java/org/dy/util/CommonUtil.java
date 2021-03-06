package org.dy.util;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpSession;


/**
 * 共通工具类.
 */
public class CommonUtil {
	/**
	 * 方法描述：判断一个字符串是否为null或空字符串（被trim后为空字符串的也算）。
	 * 
	 * @param str
	 *            需要判断的字符串
	 * @return false：不是空字符串，true：是空字符串
	 */
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 生成指定位数的随机整数
	 * 
	 * @param number
	 *            位数
	 * @return 随机整数
	 */
	public static int random(int number) {
		StringBuilder str=new StringBuilder();//定义变长字符串
		Random random=new Random();
		for (int i = 0; i < number; i++) {
			str.append(random.nextInt(10));
		}
		String result=str.toString();
		return Integer.parseInt(result);
	}

	/**
	 * 获取UUID
	 * 
	 * @return UUID
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * 判断session中存放的动作dto列表中是否包含指定的url
	 * @param session
	 * @param url 
	 * @param method http动作
	 * @return true:包含，false：不包含
	 */
//	public static boolean contains(HttpSession session,String url,String method) {
//		Object obj = session.getAttribute(SessionKeyConst.ACTION_INFO);
//		if(obj != null) {
//			@SuppressWarnings("unchecked")
//			List<ActionDto> dtoList = (List<ActionDto>)obj;
//			for(ActionDto actionDto : dtoList) {
//				if(!isEmpty(actionDto.getMethod()) && !actionDto.getMethod().equals(method)) {
//					continue;
//				}
//				if(!url.matches(actionDto.getUrl())) {
//					continue;
//				}
//				return true;
//			}
//		}
//		return false;
//	}
}
