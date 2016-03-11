package ir.co.dpq.pluf.wiki;

import java.util.List;

import ir.co.dpq.pluf.IPPaginatorPage;
import ir.co.dpq.pluf.PPaginatorParameter;
import ir.co.dpq.pluf.km.PCategory;
import ir.co.dpq.pluf.km.PLabel;
import ir.co.dpq.pluf.user.PUser;

/**
 * مدیریت لایه داده کتاب‌ها به روش همزمان را تعیین می‌کند.
 * 
 * این واسط کار با لایه داده‌ها را با استفاده از فراخوانی‌های همزمان پیاده سازی
 * می‌کند. در این مدل خروجی هر فراخوانی معادل با نتیجه این فراخوانی است. در
 * صورتی که خطای در اجرای پرداز روی دهد خطا صادر خواهد شد.
 * 
 * @author maso<mostafa.barmshory@dpq.co.ir>
 *
 */
public interface IPWikiBookSyncDao {

	PWikiBook createWikiBook(PWikiBook book);

	PWikiBook getWikiBook(Long bookId);

	PWikiBook updateWikiBook(PWikiBook book);

	PWikiBook deleteWikiBook(PWikiBook book);

	IPPaginatorPage<PWikiBook> findWikiBook(PPaginatorParameter param);

	PWikiBook addLabelToBook(PWikiBook book, PLabel label);

	List<PLabel> getBookLabels(PWikiBook book);

	PWikiBook deleteLabelFromBook(PWikiBook book, PLabel label);

	PWikiBook addBookToCategory(PWikiBook book, PCategory category);

	PWikiBook deleteCategoryFromBook(PWikiBook book, PCategory category);

	List<PCategory> getBookCategories(PWikiBook book);

	IPPaginatorPage<PWikiPageItem> getBookPages(PWikiBook book, PPaginatorParameter param);

	PWikiBook addPageToBook(PWikiBook book, PWikiPage page);

	PWikiBook deletePageFromBook(PWikiBook book, PWikiPage page);

	PWikiBook addInterestedUser(PWikiBook book);

	PWikiBook deleteInterestedUser(PWikiBook book);

	IPPaginatorPage<PUser> getBookInteresteds(PWikiBook book, PPaginatorParameter param);

}
