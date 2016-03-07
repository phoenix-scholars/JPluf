package ir.co.dpq.pluf.wiki;

import java.util.List;

import ir.co.dpq.pluf.IPCallback;
import ir.co.dpq.pluf.IPPaginatorPage;
import ir.co.dpq.pluf.PPaginatorParameter;
import ir.co.dpq.pluf.km.PCategory;
import ir.co.dpq.pluf.km.PLabel;
import ir.co.dpq.pluf.user.PUser;

/**
 * کار با کتاب‌های ویکی
 * 
 * 
 * @author maso
 *
 */
public interface IPWikiBookDao {

	PWikiBook createWikiBook(PWikiBook book);
	void createWikiBook(PWikiBook book, IPCallback<PWikiBook> callback);

	PWikiBook getWikiBook(Long bookId);
	void getWikiBook(Long bookId, IPCallback<PWikiBook> callback);

	PWikiBook updateWikiBook(PWikiBook book);
	void updateWikiBook(PWikiBook book, IPCallback<PWikiBook> callback);

	PWikiBook deleteWikiBook(PWikiBook book);
	void deleteWikiBook(PWikiBook book, IPCallback<PWikiBook> callback);

	IPPaginatorPage<PWikiBook> findWikiBook(PPaginatorParameter param);
	void findWikiBook(PPaginatorParameter param, IPCallback<IPPaginatorPage<PWikiBook>> callback);

	PWikiBook addLabelToBook(PWikiBook book, PLabel label);
	void addLabelToBook(PWikiBook book, PLabel label, IPCallback<PWikiBook> callback);

	List<PLabel> getBookLabels(PWikiBook book);
	void getBookLabels(PWikiBook book, IPCallback<List<PLabel>> callback);

	PWikiBook deleteLabelFromBook(PWikiBook book, PLabel label);
	void deleteLabelFromBook(PWikiBook book, PLabel label, IPCallback<PWikiBook> callback);

	PWikiBook addBookToCategory(PWikiBook book, PCategory category);
	void addBookToCategory(PWikiBook book, PCategory category, IPCallback<PWikiBook> callback);

	PWikiBook deleteCategoryFromBook(PWikiBook book, PCategory category);
	void deleteCategoryFromBook(PWikiBook book, PCategory category, IPCallback<PWikiBook> callback);

	List<PCategory> getBookCategories(PWikiBook book);
	void getBookCategories(PWikiBook book, IPCallback<List<PCategory>> callback);

	IPPaginatorPage<PWikiPageItem> getBookPages(PWikiBook book, PPaginatorParameter param);
	void getBookPages(PWikiBook book, PPaginatorParameter param, IPPaginatorPage<PWikiPageItem> callback);

	PWikiBook addPageToBook(PWikiBook book, PWikiPage page);
	void addPageToBook(PWikiBook book, PWikiPage page, IPCallback<PWikiBook> callback);

	PWikiBook deletePageFromBook(PWikiBook book, PWikiPage page);
	PWikiBook deletePageFromBook(PWikiBook book, PWikiPage page, IPCallback<PWikiBook> callback);

	PWikiBook addInterestedUser(PWikiBook book);
	void addInterestedUser(PWikiBook book, IPCallback<PWikiBook> callback);

	PWikiBook deleteInterestedUser(PWikiBook book);
	void deleteInterestedUser(PWikiBook book, IPCallback<PWikiBook> callback);

	IPPaginatorPage<PUser> getBookInteresteds(PWikiBook book, PPaginatorParameter param);
	void getBookInteresteds(PWikiBook book, PPaginatorParameter param, IPCallback<IPPaginatorPage<PUser>> callback);
}
