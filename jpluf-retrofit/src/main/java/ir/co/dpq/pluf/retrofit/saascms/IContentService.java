package ir.co.dpq.pluf.retrofit.saascms;

import java.util.Map;

import retrofit.client.Response;
import retrofit.http.Body;
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

	@GET("/api/saascms/content/find")
	PContent findContent(@FieldMap Map<String, Object> params);
	
	@GET("/api/saascms/content/{contentId}/download")
	Response downloadContentFile(@Path("contentId") Long id);
	
	@Multipart
	@POST("/api/saascms/content/{contentId}/download")
	PContent updateContentFileByAttach(@Path("contentId") Long id, @PartMap Map<String, Object> params, @Part("file") TypedFile file);
	
	@POST("/api/saascms/content/{contentId}/download")
	PContent updateContentFileByBody(@Path("contentId") Long id, @Body TypedFile file);
	
}
