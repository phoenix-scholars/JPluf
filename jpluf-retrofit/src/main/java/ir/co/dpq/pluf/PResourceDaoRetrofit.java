package ir.co.dpq.pluf;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import ir.co.dpq.pluf.retrofit.IRConfigurationService;
import ir.co.dpq.pluf.retrofit.RPaginatorParameter;
import ir.co.dpq.pluf.retrofit.Util;
import ir.co.dpq.pluf.retrofit.saas.IResourceService;
import ir.co.dpq.pluf.retrofit.saas.RResource;
import ir.co.dpq.pluf.retrofit.saas.RResourcePaginatorPage;
import ir.co.dpq.pluf.saas.IPResourceDao;
import ir.co.dpq.pluf.saas.PResource;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

public class PResourceDaoRetrofit implements IPResourceDao {

	IResourceService resourceService;

	IRConfigurationService configurationService;

	private TypedFile getFileType(PResource resource) {
		File file = new File(resource.getFilePath(), resource.getFile());
		String mimeType = "application/binary";
		return new TypedFile(mimeType, file);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ir.co.dpq.pluf.saas.IPResourceDao#create(ir.co.dpq.pluf.saas.PResource)
	 */
	@Override
	public PResource create(PResource resource) {
		return resourceService.create(getFileType(resource), resource.getDescription());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ir.co.dpq.pluf.saas.IPResourceDao#create(ir.co.dpq.pluf.saas.PResource,
	 * ir.co.dpq.pluf.IPCallback)
	 */
	@Override
	public void create(PResource resource, final IPCallback<PResource> callback) {
		resourceService.create(getFileType(resource), resource.getDescription(), new Callback<RResource>() {
			@Override
			public void success(RResource t, Response response) {
				callback.success(t);
			}

			@Override
			public void failure(RetrofitError error) {
				callback.failure(new PException("Fail to delete resouce", error));
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ir.co.dpq.pluf.saas.IPResourceDao#get(java.lang.Long)
	 */
	@Override
	public PResource get(Long id) {
		return resourceService.get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ir.co.dpq.pluf.saas.IPResourceDao#get(java.lang.Long,
	 * ir.co.dpq.pluf.IPCallback)
	 */
	@Override
	public void get(Long id, final IPCallback<PResource> callback) {
		resourceService.get(id, new Callback<RResource>() {
			@Override
			public void success(RResource t, Response response) {
				callback.success(t);
			}

			@Override
			public void failure(RetrofitError error) {
				callback.failure(new PException("Faile to get resource", error));
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ir.co.dpq.pluf.saas.IPResourceDao#delete(ir.co.dpq.pluf.saas.PResource)
	 */
	@Override
	public PResource delete(PResource resource) {
		return resourceService.delete(resource.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ir.co.dpq.pluf.saas.IPResourceDao#delete(ir.co.dpq.pluf.saas.PResource,
	 * ir.co.dpq.pluf.IPCallback)
	 */
	@Override
	public void delete(PResource resource, final IPCallback<PResource> callback) {
		resourceService.delete(resource.getId(), new Callback<RResource>() {
			@Override
			public void success(RResource t, Response response) {
				callback.success(t);
			}

			@Override
			public void failure(RetrofitError error) {
				callback.failure(new PException("Fail to remove", error));
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ir.co.dpq.pluf.saas.IPResourceDao#update(ir.co.dpq.pluf.saas.PResource)
	 */
	@Override
	public PResource update(PResource resource) {
		RResource rr = Util.toRObject(resource);
		return resourceService.update(resource.getId(), rr.toMap());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ir.co.dpq.pluf.saas.IPResourceDao#update(ir.co.dpq.pluf.saas.PResource,
	 * ir.co.dpq.pluf.IPCallback)
	 */
	@Override
	public void update(PResource resource, final IPCallback<PResource> callback) {
		RResource rr = Util.toRObject(resource);
		resourceService.update(resource.getId(), rr.toMap(), new Callback<RResource>() {
			@Override
			public void success(RResource t, Response response) {
				callback.success(t);
			}

			@Override
			public void failure(RetrofitError error) {
				callback.failure(new PException("fail to update resource", error));
			}
		});
	}

	@Override
	public URL getFile(PResource resource) {
		String path = String.format("%s/api/saas/resource/%d/download", //
				configurationService.getEndpoint(), //
				resource.getId());//
		try {
			return new URL(path);
		} catch (MalformedURLException e) {
			throw new PException("", e);
		}
	}

	@Override
	public IPPaginatorPage<PResource> find(PPaginatorParameter param) {
		RPaginatorParameter rparams = Util.toRObject(param);
		return resourceService.find(rparams.toMap());
	}

	@Override
	public void find(PPaginatorParameter param, final IPCallback<IPPaginatorPage<PResource>> callback) {
		RPaginatorParameter rparams = Util.toRObject(param);
		resourceService.find(rparams.toMap(), new Callback<RResourcePaginatorPage>() {

			@Override
			public void success(RResourcePaginatorPage t, Response response) {
				callback.success(t);
			}

			@Override
			public void failure(RetrofitError error) {
				callback.failure(new PException("error in network", error));
			}
		});
	}

	public void setResourceService(IResourceService resourceService) {
		this.resourceService = resourceService;
	}

	public void setConfigurationService(IRConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

}
