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
import ir.co.dpq.pluf.retrofit.saascms.PContent;
import ir.co.dpq.pluf.retrofit.user.IRUserService;
import ir.co.dpq.pluf.retrofit.user.RUser;
import retrofit.RestAdapter;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import retrofit.mime.TypedFile;

public class ContentServiceTest {

	private IRUserService userService;
	private IContentService contentService;

	private RUser user;
	private List<PContent> trash;

	@Before
	public void init() {
		trash = new ArrayList<PContent>();

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
		this.contentService = restAdapter.create(IContentService.class);
	}

	@Before
	public void loginWithAdmin() {
		// Login
		 user = userService.login(TestConfig.getAdminLogin(),
		 TestConfig.getAdminPass());
		 assertNotNull(user);
	}

	@After
	public void deleteTemp() {
		while (!trash.isEmpty()) {
			PContent dev = trash.get(0);
			contentService.deleteContent(dev.getId());
			trash.remove(0);
		}
		RUser logoutedUser = userService.logout();
		assertNotNull(logoutedUser);
//		assertEquals(user.getLogin(), logoutedUser.getLogin());
	}

	@Test
	public void createContentTest() {
		URI uri = null;
		try {
			uri = this.getClass().getResource("/saascms/person.png").toURI();
		} catch (URISyntaxException e) {
		}
		assertNotNull(uri);
		File file = new File(uri);
		assertEquals(true, file.exists());

		PContent cont = new PContent()//
				.setDescription("description")//
				.setTitle("title");

		PContent newContent = contentService.createContent(cont.toMap(), new TypedFile("image/png", file));

		assertNotNull(newContent);
		assertEquals(cont.getTitle(), newContent.getTitle());
		assertEquals(cont.getDescription(), newContent.getDescription());
		assertEquals(file.getName(), newContent.getFileName());
		assertNotEquals(new Long(0), newContent.getFileSize());
		assertNotNull(newContent.getFilePath());
		assertEquals("image/png", newContent.getMimeType());

		trash.add(newContent);
	}

	@Test
	public void getContentTest() {
		URI uri = null;
		try {
			uri = this.getClass().getResource("/saascms/person.png").toURI();
		} catch (URISyntaxException e) {
		}
		assertNotNull(uri);
		File file = new File(uri);
		assertEquals(true, file.exists());

		PContent cont = new PContent()//
				.setDescription("description")//
				.setTitle("title");

		PContent newContent = contentService.createContent(cont.toMap(), new TypedFile("image/png", file));

		assertNotNull(newContent);
		assertEquals(cont.getTitle(), newContent.getTitle());
		assertEquals(cont.getDescription(), newContent.getDescription());
		assertEquals(file.getName(), newContent.getFileName());
		assertNotEquals(new Long(0), newContent.getFileSize());
		assertNotNull(newContent.getFilePath());
		assertEquals("image/png", newContent.getMimeType());

		PContent content = contentService.getContent(newContent.getId());

		assertNotNull(content);
		assertEquals(newContent.getId(), content.getId());
		assertEquals(newContent.getTitle(), content.getTitle());
		assertEquals(newContent.getDescription(), content.getDescription());
		assertEquals(newContent.getFileName(), content.getFileName());
		assertEquals(newContent.getFileSize(), content.getFileSize());
		assertEquals(newContent.getFilePath(), content.getFilePath());
		assertEquals(newContent.getMimeType(), content.getMimeType());
		assertEquals(newContent.getDownloads(), content.getDownloads());
		assertEquals(newContent.getTenantId(), content.getTenantId());
		assertEquals(newContent.getSubmitterId(), content.getSubmitterId());

		trash.add(content);
	}

	@Test
	public void updateContentTest() {

		URI uri = null;
		try {
			uri = this.getClass().getResource("/saascms/person.png").toURI();
		} catch (URISyntaxException e) {
		}
		assertNotNull(uri);
		File file = new File(uri);
		assertEquals(true, file.exists());

		PContent cont = new PContent()//
				.setDescription("description")//
				.setTitle("title");

		PContent newContent = contentService.createContent(cont.toMap(), new TypedFile("image/png", file));

		assertNotNull(newContent);
		assertEquals(cont.getTitle(), newContent.getTitle());
		assertEquals(cont.getDescription(), newContent.getDescription());
		assertEquals(file.getName(), newContent.getFileName());
		assertNotEquals(new Long(0), newContent.getFileSize());
		assertNotNull(newContent.getFilePath());
		assertEquals("image/png", newContent.getMimeType());

		// Update data:
		final String uTitle = "updated" + newContent.getTitle();
		final String uDescription = "updated" + newContent.getDescription();
		final String uFileName = "updated" + newContent.getFileName();
		final String uMimeType = "updated" + newContent.getMimeType();

		newContent.setTitle(uTitle)//
				.setDescription(uDescription)//
				.setFileName(uFileName)//
				.setMimeType(uMimeType);

		PContent content = contentService.updateContent(newContent.getId(), newContent.toMap());

		assertNotNull(content);
		assertEquals(newContent.getId(), content.getId());
		assertEquals(newContent.getTitle(), content.getTitle());
		assertEquals(newContent.getDescription(), content.getDescription());
		assertEquals(newContent.getFileName(), content.getFileName());
		assertEquals(newContent.getFileSize(), content.getFileSize());
		assertEquals(newContent.getFilePath(), content.getFilePath());
		assertEquals(newContent.getMimeType(), content.getMimeType());
		assertEquals(newContent.getDownloads(), content.getDownloads());
		assertEquals(newContent.getTenantId(), content.getTenantId());
		assertEquals(newContent.getSubmitterId(), content.getSubmitterId());

		trash.add(newContent);
	}

	@Test
	public void updateContentFileTest() {
		URI uri = null;
		try {
			uri = this.getClass().getResource("/saascms/person.png").toURI();
		} catch (URISyntaxException e) {
		}
		assertNotNull(uri);
		File file = new File(uri);
		assertEquals(true, file.exists());

		PContent cont = new PContent()//
				.setDescription("description")//
				.setTitle("title");

		PContent newContent = contentService.createContent(cont.toMap(), new TypedFile("image/png", file));

		assertNotNull(newContent);
		assertEquals(cont.getTitle(), newContent.getTitle());
		assertEquals(cont.getDescription(), newContent.getDescription());
		assertEquals(file.getName(), newContent.getFileName());
		assertNotEquals(new Long(0), newContent.getFileSize());
		assertNotNull(newContent.getFilePath());
		assertEquals("image/png", newContent.getMimeType());

		// Update data:
		uri = null;
		try {
			uri = this.getClass().getResource("/saascms/person2.png").toURI();
		} catch (URISyntaxException e) {
		}
		assertNotNull(uri);
		File newFile = new File(uri);
		assertEquals(true, newFile.exists());
		final String uTitle = "updated" + newContent.getTitle();
		final String uDescription = "updated" + newContent.getDescription();
		final String uFileName = "updated" + newContent.getFileName();
		final String uMimeType = "updated" + newContent.getMimeType();

		newContent.setTitle(uTitle)//
				.setDescription(uDescription)//
				.setFileName(uFileName)//
				.setMimeType(uMimeType);

		PContent content = contentService.updateContentFile(newContent.getId(), newContent.toMap(),
				new TypedFile("image/png", newFile));

		assertNotNull(content);
		assertEquals(newContent.getId(), content.getId());
		assertEquals(newContent.getTitle(), content.getTitle());
		assertEquals(newContent.getDescription(), content.getDescription());
		assertEquals(newContent.getFilePath(), content.getFilePath());
		assertEquals(newContent.getDownloads(), content.getDownloads());
		assertEquals(newContent.getTenantId(), content.getTenantId());
		assertEquals(newContent.getSubmitterId(), content.getSubmitterId());

		assertEquals(newFile.getName(), content.getFileName());
		assertEquals("image/png", content.getMimeType());
		assertNotEquals(newContent.getFileSize(), content.getFileSize());

		trash.add(newContent);
	}

	@Test
	public void deleteContentTest() {
		URI uri = null;
		try {
			uri = this.getClass().getResource("/saascms/person.png").toURI();
		} catch (URISyntaxException e) {
		}
		assertNotNull(uri);
		File file = new File(uri);
		assertEquals(true, file.exists());

		PContent cont = new PContent()//
				.setDescription("description")//
				.setTitle("title");

		PContent newContent = contentService.createContent(cont.toMap(), new TypedFile("image/png", file));

		assertNotNull(newContent);
		assertEquals(cont.getTitle(), newContent.getTitle());
		assertEquals(cont.getDescription(), newContent.getDescription());
		assertEquals(file.getName(), newContent.getFileName());
		assertNotEquals(new Long(0), newContent.getFileSize());
		assertNotNull(newContent.getFilePath());
		assertEquals("image/png", newContent.getMimeType());

		PContent oldContent = contentService.deleteContent(newContent.getId());
		assertNotNull(oldContent);

		try {
			PContent content = contentService.getContent(newContent.getId());
			assertEquals(null, content);
		} catch (PException e) {
		}
	}

	@Test
	public void downloadContentFileTest() {
		URI uri = null;
		try {
			uri = this.getClass().getResource("/saascms/person.png").toURI();
		} catch (URISyntaxException e) {
		}
		assertNotNull(uri);
		File file = new File(uri);
		assertEquals(true, file.exists());

		PContent cont = new PContent()//
				.setDescription("description")//
				.setTitle("title");

		PContent newContent = contentService.createContent(cont.toMap(), new TypedFile("image/png", file));

		assertNotNull(newContent);
		assertEquals(cont.getTitle(), newContent.getTitle());
		assertEquals(cont.getDescription(), newContent.getDescription());
		assertEquals(file.getName(), newContent.getFileName());
		assertNotEquals(new Long(0), newContent.getFileSize());
		assertNotNull(newContent.getFilePath());
		assertEquals("image/png", newContent.getMimeType());

		// Download Content file
		Response response = contentService.downloadContentFile(newContent.getId());

		assertNotNull(response);
		assertEquals(200, response.getStatus());
		assertEquals("image/png", response.getBody().mimeType());
		assertNotEquals(new Long(0), (Long) response.getBody().length());
		
		PContent content = contentService.getContent(newContent.getId());
		assertNotNull(content);
		assertEquals(new Long(1), content.getDownloads());
		assertEquals(newContent.getId(), content.getId());
		assertEquals(newContent.getTitle(), content.getTitle());
		assertEquals(newContent.getDescription(), content.getDescription());
		assertEquals(newContent.getFilePath(), content.getFilePath());
		assertEquals(newContent.getTenantId(), content.getTenantId());
		assertEquals(newContent.getSubmitterId(), content.getSubmitterId());

		// Change file of content and download it again
		uri = null;
		try {
			uri = this.getClass().getResource("/saascms/sample.txt").toURI();
		} catch (URISyntaxException e) {
		}
		assertNotNull(uri);
		File newFile = new File(uri);
		assertEquals(true, newFile.exists());

		// Change file of content
		content = contentService.updateContentFileByAttach(newContent.getId(), newContent.toMap(),
				new TypedFile("text/plain", newFile));
		// Download Content file
		response = contentService.downloadContentFile(newContent.getId());

		assertNotNull(response);
		assertEquals(200, response.getStatus());
		assertEquals("text/plain;charset=UTF-8", response.getBody().mimeType());
		assertNotEquals(new Long(0), (Long) response.getBody().length());
		
		content = contentService.getContent(newContent.getId());
		assertNotNull(content);
		assertEquals(new Long(2), content.getDownloads());
		assertEquals(newContent.getId(), content.getId());
		assertEquals(newContent.getTitle(), content.getTitle());
		assertEquals(newContent.getDescription(), content.getDescription());
		assertEquals(newContent.getFilePath(), content.getFilePath());
		assertEquals(newContent.getTenantId(), content.getTenantId());
		assertEquals(newContent.getSubmitterId(), content.getSubmitterId());
		
		trash.add(newContent);
	}

	@Test
	public void updateContentFileByBodyTest() {
		URI uri = null;
		try {
			uri = this.getClass().getResource("/saascms/person.png").toURI();
		} catch (URISyntaxException e) {
		}
		assertNotNull(uri);
		File file = new File(uri);
		assertEquals(true, file.exists());

		PContent cont = new PContent()//
				.setDescription("description")//
				.setTitle("title");

		PContent newContent = contentService.createContent(cont.toMap(), new TypedFile("image/png", file));

		assertNotNull(newContent);
		assertEquals(cont.getTitle(), newContent.getTitle());
		assertEquals(cont.getDescription(), newContent.getDescription());
		assertEquals(file.getName(), newContent.getFileName());
		assertNotEquals(new Long(0), newContent.getFileSize());
		assertNotNull(newContent.getFilePath());
		assertEquals("image/png", newContent.getMimeType());

		// Update data:
		uri = null;
		try {
			uri = this.getClass().getResource("/saascms/sample.txt").toURI();
		} catch (URISyntaxException e) {
		}
		assertNotNull(uri);
		File newFile = new File(uri);
		assertEquals(true, newFile.exists());

		PContent content = contentService.updateContentFileByBody(newContent.getId(),
				new TypedFile("image/png", newFile));

		// @Note: Hadi: 1395-02-10:
		// تغییر محتوای فایل از طریق ارسال متن فایل در بدنه درخواست
		// تنها فیلد اندازه را تغییر می‌دهد هیچ تغییر در سایر فیلدها ایجاد
		// نمی‌کند
		// حتی mime_type نیز تغییر نمی‌کند.
		assertNotNull(content);
		assertEquals(newContent.getId(), content.getId());
		assertEquals(newContent.getTitle(), content.getTitle());
		assertEquals(newContent.getDescription(), content.getDescription());
		assertEquals(newContent.getFilePath(), content.getFilePath());
		assertEquals(newContent.getDownloads(), content.getDownloads());
		assertEquals(newContent.getTenantId(), content.getTenantId());
		assertEquals(newContent.getSubmitterId(), content.getSubmitterId());
		assertEquals(newContent.getFileName(), content.getFileName());
		assertEquals(newContent.getMimeType(), content.getMimeType());

		assertNotEquals(newContent.getFileSize(), content.getFileSize());

		trash.add(newContent);

	}

	@Test
	public void updateContentFileByAttachTest() {
		URI uri = null;
		try {
			uri = this.getClass().getResource("/saascms/person.png").toURI();
		} catch (URISyntaxException e) {
		}
		assertNotNull(uri);
		File file = new File(uri);
		assertEquals(true, file.exists());

		PContent cont = new PContent()//
				.setDescription("description")//
				.setTitle("title");

		PContent newContent = contentService.createContent(cont.toMap(), new TypedFile("image/png", file));

		assertNotNull(newContent);
		assertEquals(cont.getTitle(), newContent.getTitle());
		assertEquals(cont.getDescription(), newContent.getDescription());
		assertEquals(file.getName(), newContent.getFileName());
		assertNotEquals(new Long(0), newContent.getFileSize());
		assertNotNull(newContent.getFilePath());
		assertEquals("image/png", newContent.getMimeType());

		// Update data:
		uri = null;
		try {
			uri = this.getClass().getResource("/saascms/sample.txt").toURI();
		} catch (URISyntaxException e) {
		}
		assertNotNull(uri);
		File newFile = new File(uri);
		assertEquals(true, newFile.exists());
		final String uTitle = "updated" + newContent.getTitle();
		final String uDescription = "updated" + newContent.getDescription();
		final String uFileName = "updated" + newContent.getFileName();
		final String uMimeType = "updated" + newContent.getMimeType();

		newContent.setTitle(uTitle)//
				.setDescription(uDescription)//
				.setFileName(uFileName)//
				.setMimeType(uMimeType);

		PContent content = contentService.updateContentFileByAttach(newContent.getId(), newContent.toMap(),
				new TypedFile("text/plain", newFile));

		assertNotNull(content);
		assertEquals(newContent.getId(), content.getId());
		assertEquals(newContent.getTitle(), content.getTitle());
		assertEquals(newContent.getDescription(), content.getDescription());
		assertEquals(newContent.getFilePath(), content.getFilePath());
		assertEquals(newContent.getDownloads(), content.getDownloads());
		assertEquals(newContent.getTenantId(), content.getTenantId());
		assertEquals(newContent.getSubmitterId(), content.getSubmitterId());

		assertEquals(newFile.getName(), content.getFileName());
		assertEquals("text/plain", content.getMimeType());
		assertNotEquals(newContent.getFileSize(), content.getFileSize());

		trash.add(newContent);
	}

	@Test
	public void findContentTest() {
		throw new RuntimeException("Not implemented yet!");
	}
}
