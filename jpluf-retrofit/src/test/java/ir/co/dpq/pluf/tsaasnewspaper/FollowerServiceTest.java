package ir.co.dpq.pluf.tsaasnewspaper;

import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.Random;

import ir.co.dpq.pluf.retrofit.saasnewspaper.IFollowerService;
import ir.co.dpq.pluf.retrofit.saasnewspaper.PFollower;
import retrofit.RestAdapter;

public class FollowerServiceTest extends AbstractGeneralServiceTest<PFollower>{

	IFollowerService followerService;

	@Override
	protected void createTService(RestAdapter restAdapter) {

		followerService = restAdapter.create(IFollowerService.class);
	}

	@Override
	protected PFollower deleteT(Long id) {
		PFollower follower = followerService.deleteFollower(id);
		return follower;
	}

	@Override
	protected void validate(PFollower t1, PFollower t2) {
		assertEquals(t1.getAddress(), t2.getAddress());
		assertEquals(t1.getType(), t2.getType());
		assertEquals(t1.getValidated(), t2.getValidated());
	}

	@Override
	protected PFollower createT(Map<String, Object> map) {
		PFollower follower = followerService.createFollower(map);
		return follower;
	}

	@Override
	protected PFollower getInstance() {
		Random random = new Random(System.currentTimeMillis());
		PFollower follower = new PFollower()//
				.setAddress("follower"+random.nextInt(1000000)+"@dpq.co.ir")//
				.setType("email");
		return follower;
	}

	@Override
	protected PFollower getT(Long id) {
		PFollower follower = followerService.getFollower(id);
		return follower;
	}

	@Override
	protected PFollower updateT(Long id, Map<String, Object> map) {
		PFollower follower = followerService.updateFollower(id, map);
		return follower;
	}

	@Override
	protected void updateFeilds(PFollower follower) {
		Random random = new Random(System.currentTimeMillis());
		follower.setAddress("0917"+random.nextInt(100000000));
		follower.setType("phone");
		follower.setValidated(true);
	}

}
