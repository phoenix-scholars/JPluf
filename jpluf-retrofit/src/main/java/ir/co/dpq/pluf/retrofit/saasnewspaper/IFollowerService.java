package ir.co.dpq.pluf.retrofit.saasnewspaper;

import java.util.Map;

import retrofit.http.DELETE;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface IFollowerService {
	@FormUrlEncoded
	@POST("/api/newspaper/follower/new")
	PFollower createFollower(@FieldMap Map<String, Object> map);

	@GET("/api/newspaper/follower/{followerId}")
	PFollower getFollower(@Path("followerId") Long id);

	@FormUrlEncoded
	@POST("/api/newspaper/follower/{followerId}")
	PFollower updateFollower(@Path("followerId") Long id, @FieldMap Map<String, Object> map);

	@DELETE("/api/newspaper/follower/{followerId}")
	PFollower deleteFollower(@Path("followerId") Long id);
}
