package ir.co.dpq.pluf.retrofit.km;

import java.util.Map;

import ir.co.dpq.pluf.IPPaginatorPage;
import retrofit.http.DELETE;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.QueryMap;

/**
 * دسترسی و دستکاری دسته‌ها
 * 
 * @author maso
 *
 */
public interface IPCategoryService {

	/**
	 * یک دسته در ریشه ایجاد می‌کند.
	 * 
	 * @param params
	 * @return
	 */
	@FormUrlEncoded
	@POST("/api/km/category/create")
	PCategory createCategory(@FieldMap Map<String, Object> params);

	/**
	 * یک دسته در دسته تعیین شده ایجاد می‌کند.
	 * 
	 * @param params
	 * @return
	 */
	@FormUrlEncoded
	@POST("/api/km/category/{categoryId}/create")
	PCategory createCategory(@Path("categoryId") long parentId, @FieldMap Map<String, Object> params);

	@GET("/api/km/category/root")
	PCategory getRootCategory();

	@GET("/api/km/category/{categoryId}")
	PCategory getCategory(@Path("categoryId") long id);

	@GET("/api/km/category/{categoryId}/children")
	IPPaginatorPage<PCategory> getSubCategory(@Path("categoryId") long id, @QueryMap Map<String, Object> params);

	@FormUrlEncoded
	@POST("/api/km/category/{categoryId}")
	PCategory updateCategory( @Path("categoryId") long id, @FieldMap Map<String, Object> params);

	@DELETE("/api/km/category/{categoryId}")
	PCategory deleteCategory(@Path("categoryId") long id);

	@GET("/api/km/category/find")
	IPPaginatorPage<PCategory> findCategory(@QueryMap Map<String, Object> params);
}
