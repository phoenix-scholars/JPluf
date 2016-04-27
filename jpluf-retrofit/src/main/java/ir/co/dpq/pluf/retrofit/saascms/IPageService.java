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

	@Multipart
	@POST("/api/saascms/page/new")
	PPage createPage(@PartMap Map<String, Object> map, @Part("file") TypedFile file);

	@GET("/api/saascms/page/{pageId}")
	PPage getPage(@Path("pageId") Long id);

	@FormUrlEncoded
	@POST("/api/saascms/page/{pageId}")
	PPage updatePage(@Path("pageId") Long id, @FieldMap Map<String, Object> map);

	@Multipart
	@POST("/api/saascms/page/{pageId}")
	PPage updatePageFile(@Path("pageId") Long id, @PartMap Map<String, Object> map, @Part("file") TypedFile file);

	@DELETE("/api/saascms/page/{pageId}")
	PPage deletePage(@Path("pageId") Long id);
	
}
