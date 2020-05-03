package vo;

import java.sql.Date;

public class MemberBean {
	
	
	private String uID;
	private String pw;
	private String u_name;
	private String address;
	private String address_post;
	private String address_1;
	private String address_2;
	private String phone_num;
	private String u_name2;
	private String address2_post;
	private String address2_1;
	private String address2_2;
	private String phone_num2;
	private String email;
	private String tell_num; 
	private String address2;
	private int point;
	private int grade;
	private Date joinDate;
	private String withdrawal;
	


	//---쿠폰조회---
	private int num;
	private int cID;
	private String coupon_name;
	private String couponStatus;
	private Date couponReg_date;
	private Date couponEnd_date;
	private String couponContent;
	private int volume;
	int couponAction;  // coupon 기간 만료 2
						// coupon 사용 1
						// coupon 사용가능 0
	private String orderNum;
//	포인트조회
	private int pointValue;
	private int pointAction; // tinyint 형 0 =false 1 = true
	private int pID;
	private String ownerID;
	private Date pointRegTime;
	private String pointContent;
	
	
	
	
	public MemberBean() {}
	
	public MemberBean(String uID, String pw) {
		super();
		this.uID = uID;
		this.pw = pw;
	}

	// 회원가입할 때 사용
//	public MemberBean(String uID, String pw, String u_name, String address, String phone_num, String email,
//			String tell_num, String address2, int point, int grade, String withdrawal) {
//		super();
//		this.uID = uID;
//		this.pw = pw;
//		this.u_name = u_name;
//		this.address = address;
//		this.phone_num = phone_num;
//		this.email = email;
//		this.tell_num = tell_num;
//		this.address2 = address2;
//		this.point = point;
//		this.grade = grade;
//		this.withdrawal = withdrawal;
//	}
	
	// 회원가입할 때 사용(우편번호 고쳤음)
	public MemberBean(String uID, String pw, String u_name, String address_post, String address_1, String address_2, 
			String phone_num, String email, int point, int grade, String withdrawal) {
		super();
		this.uID = uID;
		this.pw = pw;
		this.u_name = u_name;
		this.address_post = address_post;
		this.address_1 = address_1;
		this.address_2 = address_2;
		this.phone_num = phone_num;
		this.email = email;
		this.point = point;
		this.grade = grade;
		this.withdrawal = withdrawal;
	}

	public MemberBean(String uID, String pw, String u_name, String address, String phone_num, String email,
			String tell_num, String address2, int point, int grade, Date joinDate, String withdrawal) {
		super();
		this.uID = uID;
		this.pw = pw;
		this.u_name = u_name;
		this.address = address;
		this.phone_num = phone_num;
		this.email = email;
		this.tell_num = tell_num;
		this.address2 = address2;
		this.point = point;
		this.grade = grade;
		this.joinDate = joinDate;
		this.withdrawal = withdrawal;
	}

	public MemberBean(String uID, String pw, String u_name, String address, String phone_num, String email,
			String tell_num, String address2, int point, int grade, Date joinDate) {
		super();
		this.uID = uID;
		this.pw = pw;
		this.u_name = u_name;
		this.address = address;
		this.phone_num = phone_num;
		this.email = email;
		this.tell_num = tell_num;
		this.address2 = address2;
		this.point = point;
		this.grade = grade;
		this.joinDate = joinDate;
	}
	
	// 구입 시 배송지 고칠 때 사용
	public MemberBean(String uID, String u_name2, String address2_post, String address2_1, String address2_2, String phone_num2) {
		super();
		this.uID = uID;
		this.u_name2 = u_name2;
		this.address2_post = address2_post;
		this.address2_1 = address2_1;
		this.address2_2 = address2_2;
		this.phone_num2 = phone_num2;
	}
	
	
//---김봉주---------------------------------------------------
	
	
	//회원가입
	public MemberBean(String uID, String pw, String u_name, String address_post, String address_1, String address_2,
			String phone_num, String email, int point, int grade) {
		super();
		this.uID = uID;
		this.pw = pw;
		this.u_name = u_name;
		this.address_post = address_post;
		this.address_1 = address_1;
		this.address_2 = address_2;
		this.phone_num = phone_num;
		this.email = email;
		this.point = point;
		this.grade = grade;
	}
	

	// 구매시 들고오는 회원정보
	public MemberBean(String uID, String pw, String u_name, String address_post, String address_1, String address_2,String phone_num,
			String u_name2, String address2_post, String address2_1, String address2_2,String phone_num2,
			String email, int point, int grade) {
		super();
		this.uID = uID;
		this.pw = pw;
		this.u_name = u_name;
		this.address_post = address_post;
		this.address_1 = address_1;
		this.address_2 = address_2;
		this.phone_num = phone_num;
		this.u_name2 = u_name2;
		this.address2_post = address2_post;
		this.address2_1 = address2_1;
		this.address2_2 = address2_2;
		this.phone_num2 = phone_num2;
		this.email = email;
		this.point = point;
		this.grade = grade;
	}	
	
	
	//회원정보 수정
	public MemberBean(String uID, String pw, String u_name, String address_post, String address_1, String address_2, 
			String phone_num, String email) {
		super();
		this.uID = uID;
		this.pw = pw;
		this.u_name = u_name;
		this.address_post = address_post;
		this.address_1 = address_1;
		this.address_2 = address_2;
		this.phone_num = phone_num;
		this.email = email;
	}
	
	
	
	//쿠폰조회 mypage list타입
	public MemberBean(String coupon_name,String couponStatus,String couponContent, Date couponReg_date,Date couponEnd_date,int couponAction,int volume) {
		super();
		this.coupon_name = coupon_name;
		this.couponStatus = couponStatus;
		this.couponReg_date = couponReg_date;
		this.couponEnd_date = couponEnd_date;
		this.couponContent = couponContent;
		this.couponAction = couponAction;
		this.volume = volume;
		}
	
	//주문디테일에 bean 타입
		public MemberBean(String coupon_name,String couponStatus,String couponContent, Date couponReg_date,Date couponEnd_date,int couponAction,int volume,String orderNum) {
			super();
			this.coupon_name = coupon_name;
			this.couponStatus = couponStatus;
			this.couponReg_date = couponReg_date;
			this.couponEnd_date = couponEnd_date;
			this.couponContent = couponContent;
			this.couponAction = couponAction;
			this.volume = volume;
			this.orderNum = orderNum;	
			}
		
	
	// 주문할 때 사용하는 쿠폰 조회
	public MemberBean(int num, int cID, String coupon_name, int volume) {
		super();
		this.num = num;
		this.cID = cID;
		this.coupon_name = coupon_name;
		this.volume = volume;
	}
		
//포인트조회
	public MemberBean(int pID,String ownerID,Date pointRegTime,String pointContent,int pointValue,int pointAction) {
		super();
		this.pID = pID;
		this.ownerID = ownerID;
		this.pointRegTime = pointRegTime;
		this.pointContent = pointContent;
		this.pointValue = pointValue;
		this.pointAction = pointAction;
			
		}
		

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getCouponContent() {
		return couponContent;
	}

	public void setCouponContent(String couponContent) {
		this.couponContent = couponContent;
	}

	public int getCouponAction() {
		return couponAction;
	}

	public void setCouponAction(int couponAction) {
		this.couponAction = couponAction;
	}

	public String getuID() {
		return uID;
	}
	public void setuID(String uID) {
		this.uID = uID;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress_post() {
		return address_post;
	}
	public void setAddress_post(String address_post) {
		this.address_post = address_post;
	}
	public String getAddress_1() {
		return address_1;
	}
	public void setAddress_1(String address_1) {
		this.address_1 = address_1;
	}
	public String getAddress_2() {
		return address_2;
	}
	public void setAddress_2(String address_2) {
		this.address_2 = address_2;
	}
	public String getPhone_num() {
		return phone_num;
	}
	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTell_num() {
		return tell_num;
	}
	public void setTell_num(String tell_num) {
		this.tell_num = tell_num;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public int getcID() {
		return cID;
	}
	public void setcID(int cID) {
		this.cID = cID;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getCoupon_name() {
		return coupon_name;
	}

	public void setCoupon_name(String coupon_name) {
		this.coupon_name = coupon_name;
	}

	public String getCouponStatus() {
		return couponStatus;
	}

	public void setCouponStatus(String couponStatus) {
		this.couponStatus = couponStatus;
	}

	public Date getCouponReg_date() {
		return couponReg_date;
	}

	public void setCouponReg_date(Date couponReg_date) {
		this.couponReg_date = couponReg_date;
	}

	public Date getCouponEnd_date() {
		return couponEnd_date;
	}

	public void setCouponEnd_date(Date couponEnd_date) {
		this.couponEnd_date = couponEnd_date;
	}
	
	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getPointValue() {
		return pointValue;
	}

	public void setPointValue(int pointValue) {
		this.pointValue = pointValue;
	}

	public int getPointAction() {
		return pointAction;
	}

	public void setPointAction(int pointAction) {
		this.pointAction = pointAction;
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

	public Date getPointRegTime() {
		return pointRegTime;
	}

	public void setPointRegTime(Date pointRegTime) {
		this.pointRegTime = pointRegTime;
	}

	public String getPointContent() {
		return pointContent;
	}

	public void setPointContent(String pointContent) {
		this.pointContent = pointContent;
	}

	public String getWithdrawal() {
		return withdrawal;
	}
	
	public void setWithdrawal(String withdrawal) {
		this.withdrawal = withdrawal;
	}
	
}
