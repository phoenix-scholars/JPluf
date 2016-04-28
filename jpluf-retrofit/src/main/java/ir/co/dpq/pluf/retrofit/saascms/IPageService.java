package ir.co.dpq.pluf.retrofit.saascms;

import java.util.Map;

import retrofit.http.DELETE;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.PartMap;
import retrofit.http.Path;
import retrofit.mime.TypedFile;

public interface IPageService {

	@FormUrlEncoded
	@POST("/api/saascms/page/new")
	PPage createPage(@FieldMap Map<String, Object> map);

	@GET("/api/saascms/page/{pageId}")
	PPage getPage(@Path("pageId") Long id);

	@FormUrlEncoded
	@POST("/api/saascms/page/{pageId}")
	PPage updatePage(@Path("pageId") Long id, @FieldMap Map<String, Object> map);

	@DELETE("/api/saascms/page/{pageId}")
	PPage deletePage(@Path("pageId") Long id);
	
	@GET("/api/saascms/page/{pageName}")
	PPage getPage(@Path("pageName") String pageName);
	
	@FormUrlEncoded
	@POST("/api/saascms/page/{pageName}")
	PPage updatePage(@Path("pageName") String pageName, @FieldMap Map<String, Object> map);
	
	@DELETE("/api/saascms/page/{pageName}")
	PPage deletePage(@Path("pageName") String pageName);
	
	@GET("/api/saascms/page/find")
	PPage findPage(@FieldMap Map<String, Object> params);
	
	
}
