package ir.co.dpq.pluf.retrofit.wiki;

import java.util.List;

import ir.co.dpq.pluf.IPPaginatorPage;
import ir.co.dpq.pluf.retrofit.RPaginatorPage;
import ir.co.dpq.pluf.wiki.PWikiBook;

/**
 * 
 * @author maso
 *
 */
public class RWikiBookPaginatorPage extends RPaginatorPage implements IPPaginatorPage<PWikiBook> {

	List<PWikiBook> items;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ir.co.dpq.pluf.IPPaginatorPage#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return items == null || items.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ir.co.dpq.pluf.IPPaginatorPage#getItems()
	 */
	@Override
	public List<PWikiBook> getItems() {
		return items;
	}

}
