package ir.co.dpq.pluf.wiki;

import java.util.List;

import ir.co.dpq.pluf.IPCallback;
import ir.co.dpq.pluf.IPPaginatorPage;
import ir.co.dpq.pluf.PPaginatorParameter;
import ir.co.dpq.pluf.km.PCategory;
import ir.co.dpq.pluf.km.PLabel;

/**
 * دسترسی به صفحه‌های ویکی را به صورت غیر همزمان فراهم می‌کند
 *
 * صفحه‌های ویکی به عنوان یک منبع راهنما برای کاربران ایجاد شده است.
 * 
 * این واسط ابزارهای مورد نیاز برای دسترسی به این صفحه‌ها را به صورت غیر همزمان فراهم کرده است.
 *
 */
public interface IPWikiPageAsyncDao {

	void createWikiPage(PWikiPage page, IPCallback<PWikiPage> callback);

	void getWikiPage(Long id, IPCallback<PWikiPage> callback);

	void deleteWikiPage(PWikiPage page, IPCallback<PWikiPage> callback);

	void findWikiPage(PPaginatorParameter param, IPCallback<IPPaginatorPage<PWikiPage>> callback);

	void addLabelToPage(PWikiPage page, PLabel label, IPCallback<PWikiPage> callback);

	void getPageLabels(PWikiPage page, IPCallback<List<PLabel>> callback);

	void deleteLabelFromPage(PWikiPage page, PLabel label, IPCallback<PWikiPage> callback);

	void addCategoryToPage(PWikiPage page, PCategory category, IPCallback<PWikiPage> callback);
	
	void deleteCategoryFromPage(PWikiPage page, PCategory category, IPCallback<PWikiPage> callback);

	void getPageCategories(PWikiPage page, IPCallback<List<PCategory>> callback);
}
