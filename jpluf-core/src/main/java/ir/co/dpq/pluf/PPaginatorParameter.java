package ir.co.dpq.pluf;

public class PPaginatorParameter {

	/**
	 * یک کویری است که انتظار داریم روی داده‌ها اجرا شود
	 */
	String query;

	/**
	 * صفحه جاری را تعیین می‌کند.
	 */
	int page;

	int itemPerPage;

	/**
	 * کلیدی را تعیین می‌کند که مرتب سازی باید بر اساس آن انجام شود
	 */
	String sortKey;

	/**
	 * ترتیب مرتب سازی را تعیین می‌کند.
	 */
	PSortOrder sortOrder;

	/**
	 * کلید فیلتر کردن را تعیین می‌کند.
	 */
	String filterKey;

	/**
	 * مقدار فیلتر کرد را
	 */
	String filterValue;

	public PPaginatorParameter() {
		itemPerPage = 10;
	}

	public PPaginatorParameter(PPaginatorParameter param) {
		setQuery(param.getQuery());
		setFilter(param.getFilterKey(), param.getFilterValue());
		setPage(param.getPage());
		setSortKey(param.getSortKey());
		setSortOrder(param.getSortOrder());
		setItemPerPage(param.getItemPerPage());
	}

	public String getQuery() {
		return query;
	}

	public PPaginatorParameter setQuery(String query) {
		this.query = query;
		return this;
	}

	public int getPage() {
		return page;
	}

	public PPaginatorParameter setPage(int page) {
		this.page = page;
		return this;
	}

	public String getSortKey() {
		return sortKey;
	}

	public PPaginatorParameter setSortKey(String sortKey) {
		this.sortKey = sortKey;
		return this;
	}

	public PSortOrder getSortOrder() {
		if (sortOrder == null)
			return PSortOrder.desc;
		return sortOrder;
	}

	public PPaginatorParameter setSortOrder(PSortOrder sortOrder) {
		this.sortOrder = sortOrder;
		return this;
	}

	public String getFilterKey() {
		return filterKey;
	}

	public PPaginatorParameter setFilterKey(String filterKey) {
		this.filterKey = filterKey;
		return this;
	}

	public String getFilterValue() {
		return filterValue;
	}

	public PPaginatorParameter setFilterValue(String filterValue) {
		this.filterValue = filterValue;
		return this;
	}

	/**
	 * فیلتر مورد نیاز را تعیین می‌کند.
	 * 
	 * @param key
	 * @param value
	 */
	public PPaginatorParameter setFilter(String key, String value) {
		setFilterKey(key);
		setFilterValue(value);
		return this;
	}

	public int getItemPerPage() {
		return itemPerPage;
	}

	public PPaginatorParameter setItemPerPage(int itemPerPage) {
		this.itemPerPage = itemPerPage;
		return this;
	}

}
