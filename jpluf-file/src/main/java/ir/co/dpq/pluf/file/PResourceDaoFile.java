package ir.co.dpq.pluf.file;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ir.co.dpq.pluf.IPCallback;
import ir.co.dpq.pluf.IPPaginatorPage;
import ir.co.dpq.pluf.PException;
import ir.co.dpq.pluf.PPaginatorParameter;
import ir.co.dpq.pluf.saas.IPResourceDao;
import ir.co.dpq.pluf.saas.PResource;

public class PResourceDaoFile implements IPResourceDao {

	List<PResource> resources;
	File basePath;

	public PResourceDaoFile() {
		resources = new CopyOnWriteArrayList<PResource>();
		setBasePath("resource");
	}

	public PResourceDaoFile(File dir) {
		resources = new CopyOnWriteArrayList<PResource>();
		setBasePath(dir);
	}

	private File getBasePath() {
		return basePath;
	}

	public void setBasePath(File basePFile) {
		this.basePath = basePFile;
		try {
			this.basePath.mkdir();
			loadList();
		} catch (Exception ex) {
			// throw new PException("Store resource list", ex);
		}
	}

	public void setBasePath(String basePath) {
		setBasePath(new File(basePath));
	}

	private void saveList() {
		Gson gson = new Gson();
		String json = gson.toJson(resources);
		try {
			FileWriter writer = new FileWriter(getStorageFile());
			writer.write(json);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadList() {
		try {
			Type listType = new TypeToken<ArrayList<PResource>>() {
			}.getType();
			FileReader jsonReader = new FileReader(getStorageFile());
			resources = new Gson().fromJson(jsonReader, listType);
		} catch (Exception ex) {
			throw new PException("Store resource list", ex);
		}
	}

	private File getStorageFile() {
		return new File(getBasePath(), "storage.json");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ir.co.dpq.pluf.saas.IPResourceDao#create(ir.co.dpq.pluf.saas.PResource)
	 */
	@Override
	public PResource create(PResource resource) {
		try {
			PResource nr = new PResource(resource);
			nr.setFilePath(getBasePath().getPath());
			synchronized (this) {
				nr.setId(System.currentTimeMillis());
				nr.setCreation(new Date());
				nr.setModification(new Date());
			}
			FileUtil.copyFile(resource, nr);
			resources.add(nr);
			saveList();
			return nr;
		} catch (IOException ex) {
			throw new PException("fila", ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ir.co.dpq.pluf.saas.IPResourceDao#create(ir.co.dpq.pluf.saas.PResource,
	 * ir.co.dpq.pluf.IPCallback)
	 */
	@Override
	public void create(PResource resource, IPCallback<PResource> callback) {
		PResource t = create(resource);
		callback.success(t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ir.co.dpq.pluf.saas.IPResourceDao#get(java.lang.Long)
	 */
	@Override
	public PResource get(Long id) {
		Assert.assertNotNull(id, "Resource id is null");
		for (PResource resource : resources) {
			if (resource.getId().equals(id)) {
				return resource;
			}
		}
		throw new PException("Resource not fount");
	}

	/*
	 * (non-Javadoc)
	 * @see ir.co.dpq.pluf.saas.IPResourceDao#get(java.lang.Long, ir.co.dpq.pluf.IPCallback)
	 */
	@Override
	public void get(Long id, IPCallback<PResource> callback) {
		PResource t = get(id);
		callback.success(t);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ir.co.dpq.pluf.saas.IPResourceDao#delete(ir.co.dpq.pluf.saas.PResource)
	 */
	@Override
	public PResource delete(PResource resource) {
		PResource re = get(resource.getId());
		FileUtil.toJavaFile(re).deleteOnExit();
		resources.remove(re);
		return re;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ir.co.dpq.pluf.saas.IPResourceDao#delete(ir.co.dpq.pluf.saas.PResource,
	 * ir.co.dpq.pluf.IPCallback)
	 */
	@Override
	public void delete(PResource resource, IPCallback<PResource> callback) {
		PResource t = delete(resource);
		callback.success(t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ir.co.dpq.pluf.saas.IPResourceDao#update(ir.co.dpq.pluf.saas.PResource)
	 */
	@Override
	public PResource update(PResource resource) {
		delete(get(resource.getId()));
		return create(resource);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ir.co.dpq.pluf.saas.IPResourceDao#update(ir.co.dpq.pluf.saas.PResource,
	 * ir.co.dpq.pluf.IPCallback)
	 */
	@Override
	public void update(PResource resource, IPCallback<PResource> callback) {
		PResource t = update(resource);
		callback.success(t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ir.co.dpq.pluf.saas.IPResourceDao#getFile(ir.co.dpq.pluf.saas.PResource)
	 */
	@Override
	public URL getFile(PResource resource) {
		try {
			return FileUtil.toJavaFile(resource).toURI().toURL();
		} catch (MalformedURLException e) {
			throw new RuntimeException("", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ir.co.dpq.pluf.saas.IPResourceDao#find(ir.co.dpq.pluf.
	 * PPaginatorParameter)
	 */
	@Override
	public IPPaginatorPage<PResource> find(PPaginatorParameter param) {
		return new PResourcePaginatorPage(this.resources);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ir.co.dpq.pluf.saas.IPResourceDao#find(ir.co.dpq.pluf.
	 * PPaginatorParameter, ir.co.dpq.pluf.IPCallback)
	 */
	@Override
	public void find(PPaginatorParameter param, IPCallback<IPPaginatorPage<PResource>> callback) {
		IPPaginatorPage<PResource> result = find(param);
		callback.success(result);
	}

}
