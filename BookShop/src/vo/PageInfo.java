package vo;

public class PageInfo {
	// 페이징 처리를 위한 페이지 정보를 저장하는 객체
	private int page; // 현재 페이지
	private int maxPage; // 총 페이지 수
	private int startPage; // 시작 페이지
	private int endPage; // 끝 페이지
	private int pageBlock; // 페이징 사이즈 
	private int listCount; // 게시물 수
	private int limit;
	private String k1;
	private String k2;
	
	public PageInfo() {
		super();
	}
	
	
	public PageInfo(int page, int limit, String k1, String k2) {
		super();
		this.page = page;
		this.limit = limit;
		this.k1 = k1;
		this.k2 = k2;
	}


	public PageInfo(int page, int maxPage, int startPage, int endPage, int pageBlock, int listCount) {
		super();
		this.page = page;
		this.maxPage = maxPage;
		this.startPage = startPage; 
		this.endPage = endPage;
		this.pageBlock = pageBlock;
		this.listCount = listCount;
	}
	
	public PageInfo(int page, int maxPage, int startPage, int endPage, int listCount) {
		super();
		this.page = page;
		this.maxPage = maxPage;
		this.startPage = startPage;
		this.endPage = endPage;
		this.listCount = listCount;
	}

	
	public PageInfo(int page, int maxPage, int startPage, int endPage, int pageBlock, int listCount, int limit,
			String k1, String k2) {
		super();
		this.page = page;
		this.maxPage = maxPage;
		this.startPage = startPage;
		this.endPage = endPage;
		this.pageBlock = pageBlock;
		this.listCount = listCount;
		this.limit = limit;
		this.k1 = k1;
		this.k2 = k2;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getListCount() {
		return listCount;
	}
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}
	public int getPageBlock() {
		return pageBlock;
	}
	public void setPageBlock(int pageBlock) {
		this.pageBlock = pageBlock;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public String getK1() {
		return k1;
	}
	public void setK1(String k1) {
		this.k1 = k1;
	}
	public String getK2() {
		return k2;
	}
	public void setK2(String k2) {
		this.k2 = k2;
	}
	
	
}
