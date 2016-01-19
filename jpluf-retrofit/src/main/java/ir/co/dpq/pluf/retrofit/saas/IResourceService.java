package ir.co.dpq.pluf.retrofit.saas;

import java.util.Map;

import retrofit.Callback;
import retrofit.http.DELETE;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.http.QueryMap;
import retrofit.mime.TypedFile;

public interface IResourceService {

	@Multipart
	@POST("/api/saas/resource/create")
	RResource create(@Part("file") TypedFile file, @Part("description") String description);

	@Multipart
	@POST("/api/saas/resource/create")
	void create(@Part("file") TypedFile file, @Part("description") String description, Callback<RResource> callback);

	@GET("/api/saas/resource/{resourceId}")
	RResource get(@Path("resourceId") Long resourceId);

	@GET("/api/saas/resource/{resourceId}")
	void get(@Path("resourceId") Long resourceId, Callback<RResource> callback);

	@FormUrlEncoded
	@POST("/api/saas/resource/{resourceId}")
	RResource update(@Path("resourceId") Long resourceId, @FieldMap Map<String, Object> param);

	@FormUrlEncoded
	@POST("/api/saas/resource/{resourceId}")
	void update(@Path("resourceId") Long resourceId, @FieldMap Map<String, Object> param, Callback<RResource> callback);

	@DELETE("/api/saas/resource/{resourceId}")
	RResource delete(@Path("resourceId") Long resourceId);

	@DELETE("/api/saas/resource/{resourceId}")
	void delete(@Path("resourceId") Long resourceId, Callback<RResource> callback);

	@GET("/api/saas/resource/find")
	RResourcePaginatorPage find(@QueryMap Map<String, Object> param);

	@GET("/api/saas/resource/find")
	void find(@QueryMap Map<String, Object> param, Callback<RResourcePaginatorPage> callback);
}
