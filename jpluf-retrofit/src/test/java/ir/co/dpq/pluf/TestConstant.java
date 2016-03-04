package ir.co.dpq.pluf;

import static ir.co.dpq.pluf.TestConstant.API_URL;
import static ir.co.dpq.pluf.test.TestCoreConstant.ADMIN_LOGIN;
import static ir.co.dpq.pluf.test.TestCoreConstant.ADMIN_PASSWORD;
import static org.junit.Assert.assertNotNull;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ir.co.dpq.pluf.retrofit.PErrorHandler;
import ir.co.dpq.pluf.retrofit.user.IRUserService;
import ir.co.dpq.pluf.retrofit.wiki.IRWikiBookService;
import ir.co.dpq.pluf.retrofit.wiki.IRWikiPageService;
import ir.co.dpq.pluf.retrofit.wiki.RWikiBookPaginatorPage;
import ir.co.dpq.pluf.test.TestCoreConstant;
import ir.co.dpq.pluf.user.PUser;
import ir.co.dpq.pluf.wiki.PWikiBook;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class TestConstant extends TestCoreConstant {

	public static final String API_URL = "http://localhost:1384";

	@Test
	public void test() {
		CookieManager cookieManager = new CookieManager();
		cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
		CookieHandler.setDefault(cookieManager);

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder//
				.setDateFormat("yyyy-MM-dd HH:mm:ss");//
		Gson gson = gsonBuilder.create();

		RestAdapter restAdapter = new RestAdapter.Builder()
				// تعیین مبدل داده
				.setConverter(new GsonConverter(gson))
				// تعیین کنترل کننده خطا
				.setErrorHandler(new PErrorHandler())
				// تعیین آدرس سایت مورد نظر
				.setEndpoint("http://dpq.co.ir")
				// ایجاد یک نمونه
				.build();
		// ایجاد سرویس‌ها
		IRUserService userSerivece = restAdapter.create(IRUserService.class);
		IRWikiBookService wikiBookService = restAdapter.create(IRWikiBookService.class);
		IRWikiPageService wikiPageService = restAdapter.create(IRWikiPageService.class);

		PWikiBookDaoRetrofit wikiBookDaoRetrofit = new PWikiBookDaoRetrofit();
		wikiBookDaoRetrofit.setWikiBookService(wikiBookService);
		
		// Login
		PUser user = userSerivece.login(ADMIN_LOGIN, ADMIN_PASSWORD);
		assertNotNull(user);
		
		IPPaginatorPage<PWikiBook> result = wikiBookDaoRetrofit.findWikiBook(null);
		List<PWikiBook> books = result.getItems();
		for (PWikiBook b : books) {
			System.out.println("Book: "+b.getTitle());
		}
	}
}
