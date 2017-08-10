package ir.co.dpq.pluf;

public interface IModelDao<T> {

	/**
	 * Create new object
	 * 
	 * @param object
	 *            object contain information about new object
	 * @param callback
	 *            asynchronous response contain newly created {@link T}
	 * 
	 */
	void create(T object, IPCallback<T> callback);

	void get(long id, IPCallback<T> callback);

	void update(T object, IPCallback<T> callback);

	void delete(T object, IPCallback<T> callback);

	void find(PPaginatorParameter param, IPCallback<IPPaginatorPage<T>> callback);

}
