package ir.co.dpq.pluf.retrofit.wiki;

import ir.co.dpq.pluf.wiki.PWikiException;

public class WAssert {

	public static void assertNotNull(Object object, String message) {
		if(object == null){
			throw new PWikiException(message);
		}
	}

}
