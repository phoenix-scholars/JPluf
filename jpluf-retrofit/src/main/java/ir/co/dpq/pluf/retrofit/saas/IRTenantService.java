package ir.co.dpq.pluf.retrofit.saas;

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
 * 
 * @author maso <mostafa.barmshory@dpq.co.ir>
 *
 */
public interface IRTenantService {

	@FormUrlEncoded
	@POST("/api/saas/create")
	RTenant createTenant(@FieldMap Map<String, Object> properties);

	@GET("/api/saas/{appId}")
	RTenant getTenant(@Path("appId") Long id);

	@GET("/api/saas/curent")
	RTenant getTenant();

	@FormUrlEncoded
	@POST("/api/saas/{appId}")
	RTenant updateTenant(@Path("appId") Long id, @FieldMap Map<String, Object> map);

	@DELETE("/api/saas/{appId}")
	RTenant deleteTenant(@Path("appId") Long id);

	@GET("/api/saas/find")
	IPPaginatorPage<RTenant> findTenant(@QueryMap Map<String, Object> params);

	@GET("/api/saas/userList")
	IPPaginatorPage<RTenant> findUserTenant(@QueryMap Map<String, Object> params);
}
