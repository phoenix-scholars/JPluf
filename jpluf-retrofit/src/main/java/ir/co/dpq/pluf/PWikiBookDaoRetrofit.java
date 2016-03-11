package ir.co.dpq.pluf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ir.co.dpq.pluf.km.PCategory;
import ir.co.dpq.pluf.km.PLabel;
import ir.co.dpq.pluf.retrofit.RPaginatorParameter;
import ir.co.dpq.pluf.retrofit.Util;
import ir.co.dpq.pluf.retrofit.wiki.IRWikiBookService;
import ir.co.dpq.pluf.retrofit.wiki.PWikiPageItemPaginatorPage;
import ir.co.dpq.pluf.retrofit.wiki.RWikiBook;
import ir.co.dpq.pluf.retrofit.wiki.RWikiBookPaginatorPage;
import ir.co.dpq.pluf.user.PUser;
import ir.co.dpq.pluf.wiki.IPWikiBookDao;
import ir.co.dpq.pluf.wiki.PWikiBook;
import ir.co.dpq.pluf.wiki.PWikiPage;
import ir.co.dpq.pluf.wiki.PWikiPageItem;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * 
 * @author maso
 *
 */
public class PWikiBookDaoRetrofit implements IPWikiBookDao {

	private IRWikiBookService wikiBookService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ir.co.dpq.pluf.wiki.IPWikiBookDao#createWikiBook(ir.co.dpq.pluf.wiki.
	 * PWikiBook)
	 */
	@Override
	public PWikiBook createWikiBook(PWikiBook book) {
		RWikiBook rbook = Util.toRObject(book);
		return wikiBookService.createWikiBook(rbook.toMap());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ir.co.dpq.pluf.wiki.IPWikiBookDao#createWikiBook(ir.co.dpq.pluf.wiki.
	 * PWikiBook, ir.co.dpq.pluf.IPCallback)
	 */
	@Override
	public void createWikiBook(PWikiBook book, final IPCallback<PWikiBook> callback) {
		RWikiBook rbook = Util.toRObject(book);
		wikiBookService.createWikiBook(rbook.toMap(), new Callback<RWikiBook>() {

			@Override
			public void success(RWikiBook t, Response response) {
				callback.success(t);
			}

			@Override
			public void failure(RetrofitError error) {
				callback.failure(new PException("Fail to create book", error));
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ir.co.dpq.pluf.wiki.IPWikiBookDao#getWikiBook(java.lang.Long)
	 */
	@Override
	public PWikiBook getWikiBook(Long bookId) {
		return this.wikiBookService.getWikiBook(bookId);
	}

	@Override
	public void getWikiBook(Long bookId, final IPCallback<PWikiBook> callback) {
		wikiBookService.getWikiBook(bookId, new Callback<RWikiBook>() {

			@Override
			public void success(RWikiBook t, Response response) {
				callback.success(t);
			}

			@Override
			public void failure(RetrofitError error) {
				callback.failure(new PException("Fail to create book", error));
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ir.co.dpq.pluf.wiki.IPWikiBookDao#updateWikiBook(ir.co.dpq.pluf.wiki.
	 * PWikiBook)
	 */
	@Override
	public PWikiBook updateWikiBook(PWikiBook book) {
		RWikiBook rbook = Util.toRObject(book);
		return wikiBookService.updateWikiBook(rbook.getId(), rbook.toMap());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ir.co.dpq.pluf.wiki.IPWikiBookDao#updateWikiBook(ir.co.dpq.pluf.wiki.
	 * PWikiBook, ir.co.dpq.pluf.IPCallback)
	 */
	@Override
	public void updateWikiBook(PWikiBook book, final IPCallback<PWikiBook> callback) {
		RWikiBook rbook = Util.toRObject(book);
		wikiBookService.updateWikiBook(rbook.getId(), rbook.toMap(), new Callback<RWikiBook>() {

			@Override
			public void success(RWikiBook t, Response response) {
				callback.success(t);
			}

			@Override
			public void failure(RetrofitError error) {
				callback.failure(new PException("Fail to create book", error));
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ir.co.dpq.pluf.wiki.IPWikiBookDao#deleteWikiBook(ir.co.dpq.pluf.wiki.
	 * PWikiBook)
	 */
	@Override
	public PWikiBook deleteWikiBook(PWikiBook book) {
		return this.wikiBookService.deleteWikiBook(book.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ir.co.dpq.pluf.wiki.IPWikiBookDao#deleteWikiBook(ir.co.dpq.pluf.wiki.
	 * PWikiBook, ir.co.dpq.pluf.IPCallback)
	 */
	@Override
	public void deleteWikiBook(PWikiBook book, final IPCallback<PWikiBook> callback) {
		wikiBookService.deleteWikiBook(book.getId(), new Callback<RWikiBook>() {
			@Override
			public void success(RWikiBook t, Response response) {
				callback.success(t);
			}

			@Override
			public void failure(RetrofitError error) {
				callback.failure(new PException("Fail to create book", error));
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ir.co.dpq.pluf.wiki.IPWikiBookDao#findWikiBook(ir.co.dpq.pluf.
	 * PPaginatorParameter)
	 */
	@Override
	public IPPaginatorPage<PWikiBook> findWikiBook(PPaginatorParameter param) {
		if (param == null)
			return wikiBookService.findWikiBook(new HashMap<String, Object>());
		RPaginatorParameter rparams = Util.toRObject(param);
		return wikiBookService.findWikiBook(rparams.toMap());
	}

	@Override
	public void findWikiBook(PPaginatorParameter param, final IPCallback<IPPaginatorPage<PWikiBook>> callback) {
		Map<String, Object> p = null;
		if (param == null)
			p = new HashMap<String, Object>();
		else
			p = Util.toRObject(param).toMap();
		wikiBookService.findWikiBook(p, new Callback<RWikiBookPaginatorPage>() {
			@Override
			public void success(RWikiBookPaginatorPage t, Response response) {
				callback.success(t);
			}

			@Override
			public void failure(RetrofitError error) {
				callback.failure(new PException("Fail to create book", error));
			}
		});
	}

	@Override
	public PWikiBook addLabelToBook(PWikiBook book, PLabel label) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PLabel> getBookLabels(PWikiBook book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PWikiBook deleteLabelFromBook(PWikiBook book, PLabel label) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PCategory> getBookCategories(PWikiBook book) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ir.co.dpq.pluf.wiki.IPWikiBookDao#getBookPages(ir.co.dpq.pluf.wiki.
	 * PWikiBook, ir.co.dpq.pluf.PPaginatorParameter)
	 */
	@Override
	public IPPaginatorPage<PWikiPageItem> getBookPages(PWikiBook book, PPaginatorParameter param) {
		List<PWikiPageItem> list = wikiBookService.getBookPages(book.getId());
		PWikiPageItemPaginatorPage pag = new PWikiPageItemPaginatorPage();
		pag.setItems(list);
		pag.setCurrentPage(0);
		pag.setPageNumber(1);
		return pag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ir.co.dpq.pluf.wiki.IPWikiBookDao#getBookPages(ir.co.dpq.pluf.wiki.
	 * PWikiBook, ir.co.dpq.pluf.PPaginatorParameter, ir.co.dpq.pluf.IPCallback)
	 */
	@Override
	public void getBookPages(PWikiBook book, PPaginatorParameter param,
			final IPCallback<IPPaginatorPage<PWikiPageItem>> callback) {
		wikiBookService.getBookPages(book.getId(), new Callback<List<PWikiPageItem>>() {
			@Override
			public void success(List<PWikiPageItem> t, Response response) {
				PWikiPageItemPaginatorPage pag = new PWikiPageItemPaginatorPage();
				pag.setItems(t);
				pag.setCurrentPage(0);
				pag.setPageNumber(1);
				callback.success(pag);
			}

			@Override
			public void failure(RetrofitError error) {
				callback.failure(new PException("Fail to create book", error));
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ir.co.dpq.pluf.wiki.IPWikiBookDao#addPageToBook(ir.co.dpq.pluf.wiki.
	 * PWikiBook, ir.co.dpq.pluf.wiki.PWikiPage)
	 */
	@Override
	public PWikiBook addPageToBook(PWikiBook book, PWikiPage page) {
		return wikiBookService.addPageToBook(book.getId(), page.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ir.co.dpq.pluf.wiki.IPWikiBookDao#deletePageFromBook(ir.co.dpq.pluf.wiki.
	 * PWikiBook, ir.co.dpq.pluf.wiki.PWikiPage)
	 */
	@Override
	public PWikiBook deletePageFromBook(PWikiBook book, PWikiPage page) {
		return wikiBookService.deletePageFromBook(book.getId(), page.getId());
	}

	@Override
	public IPPaginatorPage<PUser> getBookInteresteds(PWikiBook book, PPaginatorParameter param) {
		// TODO Auto-generated method stub
		return null;
	}

	public PWikiBookDaoRetrofit setWikiBookService(IRWikiBookService wikiBookService) {
		this.wikiBookService = wikiBookService;
		return this;
	}

	@Override
	public void addLabelToBook(PWikiBook book, PLabel label, IPCallback<PWikiBook> callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getBookLabels(PWikiBook book, IPCallback<List<PLabel>> callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteLabelFromBook(PWikiBook book, PLabel label, IPCallback<PWikiBook> callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public PWikiBook addBookToCategory(PWikiBook book, PCategory category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addBookToCategory(PWikiBook book, PCategory category, IPCallback<PWikiBook> callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public PWikiBook deleteCategoryFromBook(PWikiBook book, PCategory category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCategoryFromBook(PWikiBook book, PCategory category, IPCallback<PWikiBook> callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getBookCategories(PWikiBook book, IPCallback<List<PCategory>> callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addPageToBook(PWikiBook book, PWikiPage page, IPCallback<PWikiBook> callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePageFromBook(PWikiBook book, PWikiPage page, IPCallback<PWikiBook> callback) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addInterestedUser(PWikiBook book, IPCallback<PWikiBook> callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteInterestedUser(PWikiBook book, IPCallback<PWikiBook> callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getBookInteresteds(PWikiBook book, PPaginatorParameter param,
			IPCallback<IPPaginatorPage<PUser>> callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public PWikiBook addInterestedUser(PWikiBook book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PWikiBook deleteInterestedUser(PWikiBook book) {
		// TODO Auto-generated method stub
		return null;
	}

}
