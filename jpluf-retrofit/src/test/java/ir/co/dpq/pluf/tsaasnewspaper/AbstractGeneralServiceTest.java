package ir.co.dpq.pluf.tsaasnewspaper;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ir.co.dpq.pluf.TestSettings;
import ir.co.dpq.pluf.retrofit.PErrorHandler;
import ir.co.dpq.pluf.retrofit.PModel;
import ir.co.dpq.pluf.retrofit.user.IRUserService;
import ir.co.dpq.pluf.retrofit.user.RUser;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public abstract class AbstractGeneralServiceTest<T extends PModel> {

	protected IRUserService userService;

	protected RUser user;
	protected List<T> trash;

	@Before
	public void init() {
		trash = new ArrayList<T>();
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
				.setEndpoint(TestSettings.getApiURL())
				// ایجاد یک نمونه
				.build();
		// ایجاد سرویس‌ها
		this.userService = restAdapter.create(IRUserService.class);
		createTService(restAdapter);
	}
	
	protected abstract void createTService(RestAdapter restAdapter);
	
	@Before
	public void loginWithAdmin() {
		// Login
		user = userService.login(TestSettings.getAdminLogin(), TestSettings.getAdminPass());
		assertNotNull(user);
	}

	@After
	public void deleteTemp() {
		while (!trash.isEmpty()) {
			T obj = trash.get(0);
			deleteT(obj.getId());
			trash.remove(0);
		}
		logoutUser();
	}

	protected abstract T deleteT(Long id);

	public void logoutUser() {
		RUser logoutedUser = userService.logout();
		assertNotNull(logoutedUser);
		// assertEquals(user.getLogin(), logoutedUser.getLogin());
	}

	protected abstract void validate(T t1, T t2);

	@Test
	public void createTTest() {
		T obj = getInstance();

		T newObj = createT(obj.toMap());

		assertNotNull(newObj);
		validate(obj, newObj);

		trash.add(newObj);
	}

	protected abstract T createT(Map<String, Object> map);

	protected abstract T getInstance();

	@Test
	public void getTTest() {
		T obj = getInstance();

		T newObj = createT(obj.toMap());

		assertNotNull(newObj);
		validate(obj, newObj);

		T gotObj = getT(newObj.getId());

		assertNotNull(gotObj);
		validate(newObj, gotObj);

		trash.add(gotObj);
	}

	protected abstract T getT(Long id);

	@Test
	public void updateTTest() {
		T obj = getInstance();

		T newObj = createT(obj.toMap());

		assertNotNull(newObj);
		validate(obj, newObj);

		trash.add(newObj);

		updateFeilds(obj);

		T updatedObj = updateT(newObj.getId(), obj.toMap());

		assertNotNull(updatedObj);
		validate(obj, updatedObj);
	}

	protected abstract T updateT(Long id, Map<String, Object> map);

	protected abstract void updateFeilds(T dev);

	@Test
	public void deleteTTest() {
		T obj = getInstance();

		T newObj = createT(obj.toMap());

		assertNotNull(newObj);
		validate(obj, newObj);

		T newObj2 = deleteT(newObj.getId());

		assertNotNull(newObj2);

		try {
			// این فراخوانی باید با خطا مواجه شود
			T gotObj = getT(newObj.getId());
			assertNull(gotObj);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	public void findTTest() {
		throw new RuntimeException("Not implemented yet!");
	}
	
}
