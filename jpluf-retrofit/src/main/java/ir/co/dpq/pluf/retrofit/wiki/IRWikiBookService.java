package ir.co.dpq.pluf.retrofit.wiki;

import java.util.List;
import java.util.Map;

import ir.co.dpq.pluf.km.PCategory;
import ir.co.dpq.pluf.km.PLabel;
import ir.co.dpq.pluf.retrofit.user.RUser;
import ir.co.dpq.pluf.user.PUser;
import ir.co.dpq.pluf.wiki.PWikiPageItem;
import retrofit.Callback;
import retrofit.http.DELETE;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.QueryMap;

/**
 * کار با کتاب‌های ویکی
 * 
 * 
 * @author maso
 *
 */
public interface IRWikiBookService {

	@FormUrlEncoded
	@POST("/api/wiki/book/create")
	RWikiBook createWikiBook(@FieldMap Map<String, Object> map);

	@FormUrlEncoded
	@POST("/api/wiki/book/create")
	void createWikiBook(@FieldMap Map<String, Object> map, Callback<RWikiBook> callback);

	@GET("/api/wiki/book/{bookId}")
	RWikiBook getWikiBook(@Path("bookId") long bookId);

	@GET("/api/wiki/book/{bookId}")
	void getWikiBook(@Path("bookId") long bookId, Callback<RWikiBook> callback);

	@FormUrlEncoded
	@POST("/api/wiki/book/{bookId}")
	RWikiBook updateWikiBook(@Path("bookId") long bookId, @FieldMap Map<String, Object> map);

	@FormUrlEncoded
	@POST("/api/wiki/book/{bookId}")
	void updateWikiBook(@Path("bookId") long bookId, @FieldMap Map<String, Object> map, Callback<RWikiBook> callback);

	@DELETE("/api/wiki/book/{bookId}")
	RWikiBook deleteWikiBook(@Path("bookId") long id);

	@DELETE("/api/wiki/book/{bookId}")
	void deleteWikiBook(@Path("bookId") long id, Callback<RWikiBook> callback);

	@GET("/api/wiki/book/find")
	RWikiBookPaginatorPage findWikiBook(@QueryMap Map<String, Object> params);

	@GET("/api/wiki/book/find")
	void findWikiBook(@QueryMap Map<String, Object> params, Callback<RWikiBookPaginatorPage> callback);

	@POST("/api/wiki/book/{bookId}/label/{labelId}")
	RWikiBook addLabelToBook(@Path("bookId") long bookId, @Path("labelId") long labelId);

	@GET("/api/wiki/book/{bookId}/labels")
	Map<String, PLabel> getBookLabels(@Path("bookId") long bookId);

	@DELETE("/api/wiki/book/{bookId}/label/{labelId}")
	RWikiBook deleteLabelFromBook(@Path("bookId") long bookId, @Path("labelId") long labelId);

	@POST("/api/wiki/book/{bookId}/category/{categoryId}")
	RWikiBook addCategoryToBook(@Path("bookId") long bookId, @Path("categoryId") long categoryId);

	@DELETE("/api/wiki/book/{bookId}/category/{categoryId}")
	RWikiBook deleteCategoryFromBook(@Path("bookId") long bookId, @Path("categoryId") long categoryId);

	@GET("/api/wiki/book/{bookId}/categories")
	Map<String, PCategory> getBookCategories(@Path("bookId") long bookId);

	@GET("/api/wiki/book/{bookId}/pages")
	List<PWikiPageItem> getBookPages(@Path("bookId") long bookId);
	
	@GET("/api/wiki/book/{bookId}/pages")
	void getBookPages(@Path("bookId") long bookId, Callback<List<PWikiPageItem>> callbac);

	@POST("/api/wiki/book/{bookId}/page/{pageId}")
	RWikiBook addPageToBook(@Path("bookId") long bookId, @Path("pageId") long pageId);

	@DELETE("/api/wiki/book/{bookId}/page/{pageId}")
	RWikiBook deletePageFromBook(@Path("bookId") long bookId, @Path("pageId") long pageId);

	@POST("/api/wiki/book/{bookId}/interested")
	RUser addInterestedUser(@Path("bookId") long bookId);

	@DELETE("/api/wiki/book/{bookId}/interested")
	RUser deleteInterestedUser(@Path("bookId") long bookId);

	@GET("/api/wiki/book/{bookId}/interesteds")
	Map<String, PUser> getBookInteresteds(@Path("bookId") long bookId);
}
