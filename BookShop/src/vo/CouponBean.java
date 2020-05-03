package vo;

import java.sql.Timestamp;

public class CouponBean {
	int cID;
	String coupon_name;
	Timestamp couponReg_date;
	Timestamp couponEnd_date;
	int volume;
	
	
	
	public CouponBean() {
		super();
	}

	public CouponBean(int cID, String coupon_name, Timestamp couponReg_date, Timestamp couponEnd_date, int volume) {
		super();
		this.cID = cID;
		this.coupon_name = coupon_name;
		this.couponReg_date = couponReg_date;
		this.couponEnd_date = couponEnd_date;
		this.volume = volume;
	}
	
	

	public CouponBean(String coupon_name, Timestamp couponReg_date, Timestamp couponEnd_date, int volume) {
		super();
		this.coupon_name = coupon_name;
		this.couponReg_date = couponReg_date;
		this.couponEnd_date = couponEnd_date;
		this.volume = volume;
	}

	public int getcID() {
		return cID;
	}
	public void setcID(int cID) {
		this.cID = cID;
	}
	
	public String getCoupon_name() {
		return coupon_name;
	}

	public void setCoupon_name(String coupon_name) {
		this.coupon_name = coupon_name;
	}

	public Timestamp getCouponReg_date() {
		return couponReg_date;
	}
	public void setCouponReg_date(Timestamp couponReg_date) {
		this.couponReg_date = couponReg_date;
	}
	public Timestamp getCouponEnd_date() {
		return couponEnd_date;
	}
	public void setCouponEnd_date(Timestamp couponEnd_date) {
		this.couponEnd_date = couponEnd_date;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	
	

}
