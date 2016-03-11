package ir.co.dpq.pluf.retrofit.wiki;

import ir.co.dpq.pluf.PException;
import ir.co.dpq.pluf.wiki.PWikiBook;
import ir.co.dpq.pluf.wiki.PWikiException;
import ir.co.dpq.pluf.wiki.PWikiPage;
import retrofit.RetrofitError;

public class WUtil {

	public static RWikiBook toRObject(PWikiBook book) {
		if (book instanceof RWikiBook)
			return (RWikiBook) book;
		return new RWikiBook(book);
	}

	public static RWikiPage toRObject(PWikiPage page) {
		if (page instanceof RWikiPage)
			return (RWikiPage) page;
		return new RWikiPage(page);
	}

	public static PException parsException(RetrofitError error) {
		return new PWikiException("fail to create book", error);
	}
}
