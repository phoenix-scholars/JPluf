package ir.co.dpq.pluf.wiki;

import java.util.List;

import ir.co.dpq.pluf.IPCallback;
import ir.co.dpq.pluf.IPPaginatorPage;
import ir.co.dpq.pluf.PPaginatorParameter;
import ir.co.dpq.pluf.km.PCategory;
import ir.co.dpq.pluf.km.PLabel;
import ir.co.dpq.pluf.user.PUser;

/**
 * مدیریت لایه داده‌ها به صورت غیر همزمان ایجاد می‌کند.
 * 
 * در مدل غیر همزان، یک فراخوانی برای انتقال نتیجه به کار گرفته می‌شود. به این
 * ترتیب پردازش مورد نظر بعد از اماده شدن به برنامه اصلی ارسال می‌شود.
 * 
 * @author maso <mostafa.barmshory@dpq.co.ir>
 *
 */
public interface IPWikiBookAsyncDao {

	void createWikiBook(PWikiBook book, IPCallback<PWikiBook> callback);

	void getWikiBook(Long bookId, IPCallback<PWikiBook> callback);

	void updateWikiBook(PWikiBook book, IPCallback<PWikiBook> callback);

	void deleteWikiBook(PWikiBook book, IPCallback<PWikiBook> callback);

	void findWikiBook(PPaginatorParameter param, IPCallback<IPPaginatorPage<PWikiBook>> callback);

	void addLabelToBook(PWikiBook book, PLabel label, IPCallback<PWikiBook> callback);

	void getBookLabels(PWikiBook book, IPCallback<List<PLabel>> callback);

	void deleteLabelFromBook(PWikiBook book, PLabel label, IPCallback<PWikiBook> callback);

	void addBookToCategory(PWikiBook book, PCategory category, IPCallback<PWikiBook> callback);

	void deleteCategoryFromBook(PWikiBook book, PCategory category, IPCallback<PWikiBook> callback);

	void getBookCategories(PWikiBook book, IPCallback<List<PCategory>> callback);

	void getBookPages(PWikiBook book, PPaginatorParameter param, IPCallback<IPPaginatorPage<PWikiPageItem>> callback);

	void addPageToBook(PWikiBook book, PWikiPage page, IPCallback<PWikiBook> callback);

	void deletePageFromBook(PWikiBook book, PWikiPage page, IPCallback<PWikiBook> callback);

	void addInterestedUser(PWikiBook book, IPCallback<PWikiBook> callback);

	void deleteInterestedUser(PWikiBook book, IPCallback<PWikiBook> callback);

	void getBookInteresteds(PWikiBook book, PPaginatorParameter param, IPCallback<IPPaginatorPage<PUser>> callback);

}
