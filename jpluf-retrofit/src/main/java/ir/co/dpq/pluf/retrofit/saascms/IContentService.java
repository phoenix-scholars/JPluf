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

public interface IContentService {

	@Multipart
	@POST("/api/saascms/content/new")
	PContent createContent(@PartMap Map<String, Object> map, @Part("file") TypedFile file);

	@GET("/api/saascms/content/{contentId}")
	PContent getContent(@Path("contentId") Long id);

	@FormUrlEncoded
	@POST("/api/saascms/content/{contentId}")
	PContent updateContent(@Path("contentId") Long id, @FieldMap Map<String, Object> map);

	@Multipart
	@POST("/api/saascms/content/{contentId}")
	PContent updateContentFile(@Path("contentId") Long id, @PartMap Map<String, Object> map, @Part("file") TypedFile file);

	@DELETE("/api/saascms/content/{contentId}")
	PContent deleteContent(@Path("contentId") Long id);
	
}
