package ir.co.dpq.pluf.retrofit;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import ir.co.dpq.pluf.IPPaginatorPage;

public class RPaginatorPage2<T> implements IPPaginatorPage<T>{

	int counts;
	@SerializedName("current_page")
	int currentPage;
	@SerializedName("items_per_page")
	int itemsPerPage;
	@SerializedName("page_number")
	int pageNumber;

	List<T> items;
	
	public int getCounts() {
		return counts;
	}

	public void setCounts(int counts) {
		this.counts = counts;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	public boolean isEmpty() {
		return items == null || items.isEmpty();
	}

	public List<T> getItems() {
		return items;
	}

}
