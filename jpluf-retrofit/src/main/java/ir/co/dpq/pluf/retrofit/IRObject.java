package ir.co.dpq.pluf.retrofit;

import java.util.Map;

/**
 * مدل مجازی از اشیا
 * 
 * @author maso <mostafa.barmshory@dpq.co.ir>
 *
 */
public interface IRObject {

	/**
	 * شناسه شئی را تعیین می‌کند
	 * 
	 * @return
	 */
	Long getId();

	/**
	 * شئی را به یک نگاشت تبدیل می‌کند.
	 * 
	 * @return
	 */
	Map<String, Object> toMap();
}
