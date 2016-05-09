package ir.co.dpq.pluf.tsaascms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ir.co.dpq.pluf.PException;
import ir.co.dpq.pluf.retrofit.PErrorHandler;
import ir.co.dpq.pluf.retrofit.saascms.IContentService;
import ir.co.dpq.pluf.retrofit.saascms.IPageService;
import ir.co.dpq.pluf.retrofit.saascms.PContent;
import ir.co.dpq.pluf.retrofit.saascms.PPage;
import ir.co.dpq.pluf.retrofit.user.IRUserService;
import ir.co.dpq.pluf.retrofit.user.RUser;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import retrofit.mime.TypedFile;

public class PageServiceTest {

	private IRUserService userService;
	private IPageService pageService;
	private IContentService contentService;

	private List<PPage> trash;
	private PContent content;
	private RUser user;

	@Before
	public void init() {
		trash = new ArrayList<PPage>();

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
				.setEndpoint(TestConfig.getApiURL())
				// ایجاد یک نمونه
				.build();
		// ایجاد سرویس‌ها
		this.userService = restAdapter.create(IRUserService.class);
		this.pageService = restAdapter.create(IPageService.class);
		this.contentService = restAdapter.create(IContentService.class);
	}

	@Before
	public void loginWithAdmin() {
		// Login
		 user = userService.login(TestConfig.getAdminLogin(),
		 TestConfig.getAdminPass());
		 assertNotNull(user);
		 
		 content = createContent();
	}

	public PContent createContent() {
		URI uri = null;
		try {
			uri = this.getClass().getResource("/saascms/person.png").toURI();
		} catch (URISyntaxException e) {
		}
		assertNotNull(uri);
		File file = new File(uri);
		assertEquals(true, file.exists());

		PContent pg = new PContent()//
				.setDescription("description")//
				.setTitle("title");

		PContent newContent = contentService.createContent(pg.toMap(), new TypedFile("image/png", file));

		assertNotNull(newContent);
		assertEquals(pg.getTitle(), newContent.getTitle());
		assertEquals(pg.getDescription(), newContent.getDescription());
		assertEquals(file.getName(), newContent.getFileName());
		assertNotEquals(new Long(0), newContent.getFileSize());
		assertNotNull(newContent.getFilePath());
		assertEquals("image/png", newContent.getMimeType());
		
		return newContent;
	}

	@After
	public void deleteTemp() {
		while (!trash.isEmpty()) {
			PPage dev = trash.get(0);
			pageService.deletePage(dev.getId());
			trash.remove(0);
		}
		contentService.deleteContent(content.getId());
		RUser logoutedUser = userService.logout();
		assertNotNull(logoutedUser);
//		assertEquals(user.getLogin(), logoutedUser.getLogin());
	}

	@Test
	public void createPageTest() {
		PPage pg = new PPage()//
				.setName("page name")//
				.setContentId(content.getId());

		PPage newPage = pageService.createPage(pg.toMap());

		assertNotNull(newPage);
		assertEquals(pg.getName(), newPage.getName());
		assertEquals(pg.getContentId(), newPage.getContentId());

		trash.add(newPage);
	}

	@Test
	public void getPageTest() {
		PPage pg = new PPage()//
				.setName("page name")//
				.setContentId(content.getId());

		PPage newPage = pageService.createPage(pg.toMap());

		assertNotNull(newPage);
		assertEquals(pg.getName(), newPage.getName());
		assertEquals(pg.getContentId(), newPage.getContentId());

		PPage page = pageService.getPage(newPage.getId());

		assertNotNull(page);
		assertEquals(newPage.getId(), page.getId());
		assertEquals(newPage.getName(), page.getName());
		assertEquals(newPage.getContentId(), page.getContentId());
		assertEquals(newPage.getTenantId(), page.getTenantId());
		assertEquals(newPage.getCreation(), page.getCreation());

		trash.add(page);
	}

	@Test
	public void updatePageTest() {

		PPage pg = new PPage()//
				.setName("page name")//
				.setContentId(content.getId());

		PPage newPage = pageService.createPage(pg.toMap());

		assertNotNull(newPage);
		assertEquals(pg.getName(), newPage.getName());
		assertEquals(pg.getContentId(), newPage.getContentId());

		// Update data:
		final String uName = "updated" + newPage.getName();
		final Long uContentId = newPage.getContentId() + 1;

		newPage.setName(uName)//
				.setContentId(uContentId);
		
		PPage page = pageService.updatePage(newPage.getId(), newPage.toMap());

		assertNotNull(page);
		assertEquals(newPage.getId(), page.getId());
		assertEquals(newPage.getName(), page.getName());
		assertEquals(newPage.getContentId(), page.getContentId());
		assertEquals(newPage.getTenantId(), page.getTenantId());
		assertEquals(newPage.getCreation(), page.getCreation());

		trash.add(newPage);
	}

	@Test
	public void deletePageTest() {
		PPage pg = new PPage()//
				.setContentId(content.getId())//
				.setName("should be removed page");

		PPage newPage = pageService.createPage(pg.toMap());

		assertNotNull(newPage);
		assertEquals(pg.getName(), newPage.getName());
		assertEquals(pg.getContentId(), newPage.getContentId());
		
		PPage oldPage = pageService.deletePage(newPage.getId());
		assertNotNull(oldPage);

		try {
			PPage page = pageService.getPage(newPage.getId());
			assertEquals(null, page);
		} catch (PException e) {
		}
	}

	@Test
	public void getPageByNameTest() {
		PPage pg = new PPage()//
				.setName("page name")//
				.setContentId(content.getId());

		PPage newPage = pageService.createPage(pg.toMap());

		assertNotNull(newPage);
		assertEquals(pg.getName(), newPage.getName());
		assertEquals(pg.getContentId(), newPage.getContentId());

		// Get page by name
		PPage page = pageService.getPage(newPage.getName());

		assertNotNull(page);
		assertEquals(newPage.getId(), page.getId());
		assertEquals(newPage.getName(), page.getName());
		assertEquals(newPage.getContentId(), page.getContentId());
		assertEquals(newPage.getTenantId(), page.getTenantId());
		assertEquals(newPage.getCreation(), page.getCreation());

		trash.add(page);
	}

	@Test
	public void updatePageByNameTest() {

		PPage pg = new PPage()//
				.setName("page name")//
				.setContentId(content.getId());

		PPage newPage = pageService.createPage(pg.toMap());

		assertNotNull(newPage);
		assertEquals(pg.getName(), newPage.getName());
		assertEquals(pg.getContentId(), newPage.getContentId());

		// Update data:
		final String uName = "updated" + newPage.getName();
		final Long uContentId = newPage.getContentId() + 1;

		newPage.setName(uName)//
				.setContentId(uContentId);
		
		// Update page with specified name
		PPage page = pageService.updatePage(pg.getName(), newPage.toMap());

		assertNotNull(page);
		assertEquals(newPage.getId(), page.getId());
		assertEquals(newPage.getName(), page.getName());
		assertEquals(newPage.getContentId(), page.getContentId());
		assertEquals(newPage.getTenantId(), page.getTenantId());
		assertEquals(newPage.getCreation(), page.getCreation());

		trash.add(newPage);
	}

	@Test
	public void deletePageByNameTest() {
		PPage pg = new PPage()//
				.setContentId(content.getId())//
				.setName("should be removed page");

		PPage newPage = pageService.createPage(pg.toMap());

		assertNotNull(newPage);
		assertEquals(pg.getName(), newPage.getName());
		assertEquals(pg.getContentId(), newPage.getContentId());
		
		PPage oldPage = pageService.deletePage(newPage.getName());
		assertNotNull(oldPage);

		try {
			PPage page = pageService.getPage(newPage.getName());
			assertEquals(null, page);
		} catch (PException e) {
		}
	}
	
	@Test
	public void findPageTest(){
		throw new RuntimeException("Not implemented yet!");
	}
}
