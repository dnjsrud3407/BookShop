package vo;

public class OrderDetailBean {

	private int orderDetailCode;//		상세번호(오토 인크리먼트, null주면 알아서 들어감)
	private int bookID;//			책 번호
	private int bookKateoire_BKID;//	책 카테고리 번호
	private String orderNum;//		주문 번호(참조)
	private String bookTitle;//		책제목
	private int bookPrice;//		책 단가
	private int bookEA;//			책 수량
	private String paymentType;//		결제 방식
	
	
	
	
	public OrderDetailBean() {
		super();
	}


	public OrderDetailBean(int bookID, String orderNum, String bookTitle, int bookPrice, int bookEA) {
		super();
		this.bookID = bookID;
		this.orderNum = orderNum;
		this.bookTitle = bookTitle;
		this.bookPrice = bookPrice;
		this.bookEA = bookEA;
	}
	
	
	public int getOrderDetailCode() {
		return orderDetailCode;
	}
	public void setOrderDetailCode(int orderDetailCode) {
		this.orderDetailCode = orderDetailCode;
	}
	public int getBookID() {
		return bookID;
	}
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}
	public int getBookKateoire_BKID() {
		return bookKateoire_BKID;
	}
	public void setBookKateoire_BKID(int bookKateoire_BKID) {
		this.bookKateoire_BKID = bookKateoire_BKID;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public int getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}
	public int getBookEA() {
		return bookEA;
	}
	public void setBookEA(int bookEA) {
		this.bookEA = bookEA;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	
	
	
	
	
}
