package vo;

import java.sql.Date;
import java.util.List;


public class OrderBean {
	
	public  OrderBean() {}

	// 주문번호, 주문자 ID, 최종 가격, 수령인, 배송지, 주문시간, 주문상태, 상태변경시간(주문시간), 쿠폰번호, 결제방식
	
	private String orderNum; //주문번호								***
	private String order_ID; //주문자 아이디 							***
	private int  totalPrice; // 최총 가격							***
	private int shipPrice;
	private String orderRec; //수령인								***  수령인 + 전화번호
	private String address2;//									***  배송지
	private String u_name2;
	private String address2_post;
	private String address2_1;
	private String address2_2; 
	private String phone_num2;
	private Date orderTime; //주문시간								***
	private String orderStatus; //주문상태
	private Date lastModTime; //주문상태 바꾼시간						***
	private int coupon_num;        //couponhistory 테이블 num		*** 
	private String paymentType;//								*** 결제 방식
	private List<OrderDetailBean> orderList;
	
	
	
	// 주문할 때 사용
	public OrderBean(String orderNum, String order_ID, int totalPrice, int shipPrice, String orderRec, String address2,
			String u_name2, String address2_post, String address2_1, String address2_2, String phone_num2, Date orderTime,
			String orderStatus, Date lastModTime, int coupon_num, String paymentType, List<OrderDetailBean> orderList) {
		super();
		this.orderNum = orderNum;
		this.order_ID = order_ID;
		this.totalPrice = totalPrice;
		this.shipPrice = shipPrice;
		this.orderRec = orderRec;
		this.address2 = address2;
		this.u_name2 = u_name2;
		this.address2_post = address2_post;
		this.address2_1 = address2_1;
		this.address2_2 = address2_2;
		this.phone_num2 = phone_num2;
		this.orderTime = orderTime;
		this.orderStatus = orderStatus;
		this.lastModTime = lastModTime;
		this.coupon_num = coupon_num;
		this.paymentType = paymentType;
		this.orderList = orderList;
	}



	public List<OrderDetailBean> getOrderList() {
		return orderList;
	}



	public void setOrderList(List<OrderDetailBean> orderList) {
		this.orderList = orderList;
	}



	public int getTotalPrice() {
		return totalPrice;
	}



	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	String bookTitle;//책 이름                book 테이블	
	int bookID; //상품번호        이거 order_detail 테이블
	int orderEA; //주문갯수                     order_detail 테이블
	String orderAddress; //주문자 주소
	int couponHistory_num; //쿠폰번호 		
	String coupon_name;    //coupon 테이블			
	String u_name;//									***  수령인
	String phone_num;//									***  수령인 전화번호
	
	
	String pointContent;   //pointhistory 테이블
	int pointValue;
	
	
	
	String bookOriginImage;
	String bookPublisher;
	int bookPrice;
	String address;
	String tell_num;
	String email;
	
	 int orderDetailCode;
	 int bookKategorie_BKID;
	 String bookIntroduce;
	 float saveRatio;
	
//	--------------
//	orderNum
//	order_ID
	int bookEA;
//	orderTime
//	orderStatus
//	orderAddress
//	bookID
//	bookTitle
//	bookOriginImage
//	bookPublisher
//	bookPrice
//	u_name
//	address2
//	phone_num
//	tell_num
//	email
//	coupon_name
	private String couponStatus;
	private int pointAction;
//	pointContent
	private Date pointRegTime;
	private int pID;
	private String ownerID;
//	pointValue
	private int volume; //string->int 변경
	private int couponAction;  // coupon 기간 만료 2
					   // coupon 사용 1
					   // coupon 사용가능 0
	
	//mypage(orderList)
	public OrderBean(String orderNum, String order_id,String bookTitle,int bookID, int orderEA, String orderAddress, Date orderTime,
			String orderStatus, Date lastModTime, int couponHistory_num,String coupon_name,int bookPrice) {
		super();
		this.orderNum = orderNum;
		this.order_ID = order_id;
		this.bookTitle = bookTitle;
		this.bookID = bookID;
		this.orderEA = orderEA;
		this.orderAddress = orderAddress;
		this.orderTime = orderTime;
		this.orderStatus = orderStatus;
		this.lastModTime = lastModTime;
		this.couponHistory_num = couponHistory_num;
		this.coupon_name = coupon_name;
		this.bookPrice = bookPrice;
	}
	
	
	
	//수정후 mypage 의 orderlist 포인트는 아직 ㄴㄴ
	
	public OrderBean(
			String orderNum,
			String order_ID,
			int bookEA,
			Date orderTime,
			String orderStatus,
			String orderAddress,
			int bookID,
			String bookTitle,
			String bookOriginImage,
			String bookPublisher,
			int bookPrice,
			String u_name2, 
			String address2_post, 
			String address2_1, 
			String address2_2, 
			String phone_num2,
			String email,
			String coupon_name,
			String couponStatus,
			String orderRec,
			int orderDetailCode,
			int bookKategorie_BKID,
			String paymentType,
			String bookIntroduce,
			Float saveRatio,
			int volume,
			int couponAction)
	     {
		super();
		this.orderNum = orderNum;
		this.order_ID = order_ID;
		this.bookEA = bookEA;
		this.orderTime = orderTime;
		this.orderStatus = orderStatus;
		this.orderAddress = orderAddress;
		this.bookID = bookID;
		this.bookTitle = bookTitle;
		this.bookOriginImage = bookOriginImage;
		this.bookPublisher = bookPublisher;
		this.bookPrice = bookPrice;
		this.u_name2 = u_name2;
		
		this.address2_post = address2_post;
		this.address2_1 = address2_1;
		this.address2_2 = address2_2;
		this.phone_num2 = phone_num2;
		this.email = email;
		
		this.coupon_name = coupon_name;
		this.couponStatus = couponStatus;
		this.orderRec = orderRec;
	
		this.orderDetailCode = orderDetailCode;
		this.bookKategorie_BKID = bookKategorie_BKID;
		this.paymentType = paymentType;
		this.bookIntroduce = bookIntroduce;
		this.saveRatio = saveRatio;
		this.volume = volume;
		this.couponAction = couponAction;
	}
	
	
	
	// 마이페이지
	public OrderBean(
			String orderNum,
			String order_ID,
			int bookEA,
			Date orderTime,
			String orderStatus,
			String orderAddress,
			int bookID,
			String bookTitle,
			String bookOriginImage,
			String bookPublisher,
			int bookPrice,
			String u_name2, 
			String address2_post, 
			String address2_1, 
			String address2_2, 
			String phone_num2,
			String email,
			String orderRec,
			int orderDetailCode,
			int bookKategorie_BKID,
			String paymentType,
			String bookIntroduce,
			Float saveRatio
		)
	     {
		super();
		this.orderNum = orderNum;
		this.order_ID = order_ID;
		this.bookEA = bookEA;
		this.orderTime = orderTime;
		this.orderStatus = orderStatus;
		this.orderAddress = orderAddress;
		this.bookID = bookID;
		this.bookTitle = bookTitle;
		this.bookOriginImage = bookOriginImage;
		this.bookPublisher = bookPublisher;
		this.bookPrice = bookPrice;
		this.u_name2 = u_name2;
		
		this.address2_post = address2_post;
		this.address2_1 = address2_1;
		this.address2_2 = address2_2;
		this.phone_num2 = phone_num2;
		this.email = email;
		
		this.orderRec = orderRec;
	
		this.orderDetailCode = orderDetailCode;
		this.bookKategorie_BKID = bookKategorie_BKID;
		this.paymentType = paymentType;
		this.bookIntroduce = bookIntroduce;
		this.saveRatio = saveRatio;
	}
		
	
	
	
	
	//포인트조회
		public OrderBean(int pID,String ownerID,Date pointRegTime,String pointContent,int pointValue,int pointAction,String orderNum) {
			super();
			this.pID = pID;
			this.ownerID = ownerID;
			this.pointRegTime = pointRegTime;
			this.pointContent = pointContent;
			this.pointValue = pointValue;
			this.pointAction = pointAction;
			this.orderNum = orderNum;
			}

		// orderDetail
		public OrderBean(String orderNum, String order_ID, int bookEA,Date orderTime,String orderStatus,
				String orderAddress,int bookID,String bookTitle,String bookOriginImage,String bookPublisher,
				int bookPrice,String u_name, String phone_num, String u_name2,String address2_post,String address2_1,String address2_2,String phone_num2,String email,
				int pointAction,String pointContent,Date pointRegTime,int pointValue,String orderRec,
				int orderDetailCode,
				int bookKategorie_BKID,
				String paymentType,
				String bookIntroduce,
				Float saveRatio,
//				int volume,
//				int couponAction,
				int totalPrice,
				int shipPrice
				)
		     
		{
			super();
			this.orderNum = orderNum;
			this.order_ID = order_ID;
			this.bookEA = bookEA;
			this.orderTime = orderTime;
			this.orderStatus = orderStatus;
			this.orderAddress = orderAddress;
			this.bookID = bookID;
			this.bookTitle = bookTitle;
			this.bookOriginImage = bookOriginImage;
			this.bookPublisher = bookPublisher;
			this.bookPrice = bookPrice;
			this.u_name = u_name;
			this.phone_num = phone_num;
			
			this.u_name2 = u_name2;
			this.address2_post = address2_post;
			this.address2_1 = address2_1;
			this.address2_2 = address2_2;
			this.phone_num2 = phone_num2;
			this.email = email;
			
//			this.coupon_name = coupon_name;
//			this.couponStatus = couponStatus;
			this.pointAction = pointAction;
			this.pointContent = pointContent;
			this.pointRegTime = pointRegTime;
			this.pointValue = pointValue;
			this.orderRec = orderRec;
			
			this.orderDetailCode = orderDetailCode;
			this.bookKategorie_BKID = bookKategorie_BKID;
			this.paymentType = paymentType;
			this.bookIntroduce = bookIntroduce;
			this.saveRatio = saveRatio;
//			this.volume = volume;
//			this.couponAction = couponAction;
			this.totalPrice = totalPrice;
			this.shipPrice = shipPrice;
		}
		
		// DeliveryDetail
		public OrderBean(String orderNum, String order_ID, int bookEA,Date orderTime,String orderStatus,
				String orderAddress,int bookID,String bookTitle,String bookOriginImage,String bookPublisher,
				int bookPrice,String u_name, String phone_num, String u_name2,String address2_post,String address2_1,String address2_2,String phone_num2,String email,
				String orderRec,
				int orderDetailCode,
				int bookKategorie_BKID,
				String paymentType,
				String bookIntroduce,
				Float saveRatio
				)
		     
		{
			super();
			this.orderNum = orderNum;
			this.order_ID = order_ID;
			this.bookEA = bookEA;
			this.orderTime = orderTime;
			this.orderStatus = orderStatus;
			this.orderAddress = orderAddress;
			this.bookID = bookID;
			this.bookTitle = bookTitle;
			this.bookOriginImage = bookOriginImage;
			this.bookPublisher = bookPublisher;
			this.bookPrice = bookPrice;
			this.u_name = u_name;
			this.phone_num = phone_num;
			
			this.u_name2 = u_name2;
			this.address2_post = address2_post;
			this.address2_1 = address2_1;
			this.address2_2 = address2_2;
			this.phone_num2 = phone_num2;
			this.email = email;
			
			this.orderRec = orderRec;
			
			this.orderDetailCode = orderDetailCode;
			this.bookKategorie_BKID = bookKategorie_BKID;
			this.paymentType = paymentType;
			this.bookIntroduce = bookIntroduce;
			this.saveRatio = saveRatio;
		}
		

	public int getpID() {
		return pID;
	}
	public void setpID(int pID) {
		this.pID = pID;
	}
	public String getOwnerID() {
		return ownerID;
	}
	public void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
	}

	public int getCouponAction() {
		return couponAction;
	}

	public void setCouponAction(int couponAction) {
		this.couponAction = couponAction;
	}

	public void setPointAction(int pointAction) {
		this.pointAction = pointAction;
	}


	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getOrderDetailCode() {
		return orderDetailCode;
	}

	public void setOrderDetailCode(int orderDetailCode) {
		this.orderDetailCode = orderDetailCode;
	}

	public int getBookKategorie_BKID() {
		return bookKategorie_BKID;
	}

	public void setBookKategorie_BKID(int bookKategorie_BKID) {
		this.bookKategorie_BKID = bookKategorie_BKID;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getBookIntroduce() {
		return bookIntroduce;
	}

	public void setBookIntroduce(String bookIntroduce) {
		this.bookIntroduce = bookIntroduce;
	}

	public float getSaveRatio() {
		return saveRatio;
	}

	public void setSaveRatio(float saveRatio) {
		this.saveRatio = saveRatio;
	}

	public String getOrder_ID() {
		return order_ID;
	}

	public void setOrder_ID(String order_ID) {
		this.order_ID = order_ID;
	}

	public int getBookEA() {
		return bookEA;
	}

	public void setBookEA(int bookEA) {
		this.bookEA = bookEA;
	}

	public String getCouponStatus() {
		return couponStatus;
	}

	public void setCouponStatus(String couponStatus) {
		this.couponStatus = couponStatus;
	}

	public int getPointAction() {
		return pointAction;
	}


	public Date getPointRegTime() {
		return pointRegTime;
	}

	public void setPointRegTime(Date pointRegTime) {
		this.pointRegTime = pointRegTime;
	}

	public String getOrderRec() {
		return orderRec;
	}

	public void setOrderRec(String orderRec) {
		this.orderRec = orderRec;
	}

	public String getBookTitle() {
		return bookTitle;
	}




	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}




	public String getCoupon_name() {
		return coupon_name;
	}


	public void setCoupon_name(String coupon_name) {
		this.coupon_name = coupon_name;
	}


	public int getCoupon_num() {
		return coupon_num;
	}


	public void setCoupon_num(int coupon_num) {
		this.coupon_num = coupon_num;
	}


	public String getPointContent() {
		return pointContent;
	}


	public void setPointContent(String pointContent) {
		this.pointContent = pointContent;
	}


	public int getPointValue() {
		return pointValue;
	}


	public void setPointValue(int pointValue) {
		this.pointValue = pointValue;
	}





	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getBookOriginImage() {
		return bookOriginImage;
	}

	public void setBookOriginImage(String bookOriginImage) {
		this.bookOriginImage = bookOriginImage;
	}

	public String getBookPublisher() {
		return bookPublisher;
	}

	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}

	public int getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getPhone_num() {
		return phone_num;
	}

	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}

	public String getTell_num() {
		return tell_num;
	}

	public void setTell_num(String tell_num) {
		this.tell_num = tell_num;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getOrder_id() {
		return order_ID;
	}
	public void setOrder_id(String order_ID) {
		this.order_ID = order_ID;
	}
	public int getbookID() {
		return bookID;
	}
	public void setbookID(int bookID) {
		this.bookID = bookID;
	}
	public int getOrderEA() {
		return orderEA;
	}
	public void setOrderEA(int orderEA) {
		this.orderEA = orderEA;
	}
	public String getOrderAddress() {
		return orderAddress;
	}
	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Date getLastModTime() {
		return lastModTime;
	}
	public void setLastModTime(Date lastModTime) {
		this.lastModTime = lastModTime;
	}
	public int getCouponHistory_num() {
		return couponHistory_num;
	}
	public void setCouponHistory_num(int couponHistory_num) {
		this.couponHistory_num = couponHistory_num;
	}
	public int getShipPrice() {
		return shipPrice;
	}
	public void setShipPrice(int shipPrice) {
		this.shipPrice = shipPrice;
	}
	public String getU_name2() {
		return u_name2;
	}
	public void setU_name2(String u_name2) {
		this.u_name2 = u_name2;
	}
	public String getAddress2_post() {
		return address2_post;
	}
	public void setAddress2_post(String address2_post) {
		this.address2_post = address2_post;
	}
	public String getAddress2_1() {
		return address2_1;
	}
	public void setAddress2_1(String address2_1) {
		this.address2_1 = address2_1;
	}
	public String getAddress2_2() {
		return address2_2;
	}
	public void setAddress2_2(String address2_2) {
		this.address2_2 = address2_2;
	}
	public String getPhone_num2() {
		return phone_num2;
	}
	public void setPhone_num2(String phone_num2) {
		this.phone_num2 = phone_num2;
	}
	
	
}
