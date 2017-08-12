package ir.co.dpq.pluf.test;

import static ir.co.dpq.pluf.test.TestCoreConstant.ADMIN_LOGIN;
import static ir.co.dpq.pluf.test.TestCoreConstant.ADMIN_PASSWORD;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ir.co.dpq.pluf.IModelDao;
import ir.co.dpq.pluf.IPPaginatorPage;
import ir.co.dpq.pluf.PException;
import ir.co.dpq.pluf.PPaginatorParameter;
import ir.co.dpq.pluf.user.IPUserDao;
import ir.co.dpq.pluf.user.PUser;

public abstract class AbstractModelDaoTest<T> {

	private IModelDao<T> modelDao;
	private IPUserDao userDao;
	protected List<T> modelCache;

	@Before
	public void initTest() {
		modelDao = getModelDaoInstance();
		userDao = getUserDaoInstance();
		modelCache = new ArrayList<>();
	}

	@Before
	public void loginWithAdmin() {
		// Login
		PUser user = userDao.login(ADMIN_LOGIN, ADMIN_PASSWORD);
		assertNotNull(user);
		assertEquals(ADMIN_LOGIN, user.getLogin());
	}

	/**
	 * ایجاد یک نمونه از سرویس
	 * 
	 * سایر پیاده سازی‌ها با بازنویسی این فراخوانی و ایجاد سرویس می‌توانند از
	 * این تست‌ها استفاده کنند.
	 * 
	 * @return
	 */
	protected abstract IModelDao<T> getModelDaoInstance();

	protected abstract IPUserDao getUserDaoInstance();

	/**
	 * Creates and returns a new object
	 * 
	 * @return
	 */
	protected abstract T getNewModel();

	/**
	 * Checks necessary assertions on obj and newObj.
	 * 
	 * @param obj
	 *            object contain information to create new object.
	 * @param newObj
	 *            newly object created which should be compared with obj.
	 */
	protected abstract void assertModel(T obj, T newObj);

	/**
	 * Returns id of given object.
	 * 
	 * @param obj
	 * @return
	 */
	protected abstract long getId(T obj);

	@Test
	public void createModelTest() {
		T obj = getNewModel();
		T newObj = modelDao.create(obj);
		assertNotNull(newObj);
		assertModel(obj, newObj);

		modelCache.add(newObj);
	}

	@Test
	public void getModelTest() {
		T obj = getNewModel();
		T obj2 = modelDao.create(obj);
		assertNotNull(obj2);
		assertModel(obj, obj2);

		T obj3 = modelDao.get(getId(obj2));
		assertNotNull(obj3);
		assertModel(obj, obj3);

		modelCache.add(obj2);
	}

	@Test
	public void findModelTest() {
		T obj = getNewModel();
		T obj2 = modelDao.create(obj);
		assertNotNull(obj2);
		assertModel(obj, obj2);

		PPaginatorParameter param = new PPaginatorParameter();
		IPPaginatorPage<T> objs = modelDao.find(param);
		assertNotNull(objs);
		assertNotNull(objs.getItems());
		assertTrue(objs.getItems().size() > 0);

		modelCache.add(obj2);
	}

	@Test
	public void deleteModelTest() {
		T obj = getNewModel();

		T obj2 = modelDao.create(obj);
		assertNotNull(obj2);
		assertModel(obj, obj2);

		modelDao.delete(getId(obj2));
		try {
			modelDao.get(getId(obj2));
		} catch (PException e) {
			// we expect not found exception here
			return;
		}
		assertTrue(false);
	}

	@After
	public void clearTestData() {
		for (T obj : modelCache) {
			try {
				modelDao.delete(getId(obj));
			} catch (Exception e) {

			}
		}
	}
	
}
