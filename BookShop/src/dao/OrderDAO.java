package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vo.MemberBean;
import vo.OrderBean;
import vo.OrderDetailBean;

public class OrderDAO {
public OrderDAO() {}
	
	private static OrderDAO instance = new OrderDAO();

	public static OrderDAO getInstance() { 
		return instance;
	}
	// ----------------------------------------------------
	Connection con;
	
	public void setConnection(Connection con) {
		this.con = con;
	}

	public OrderBean selectOrder(int num) {
		return null;
	}

	public int updateReadcount(int num) {
		// TODO Auto-generated method stub
		return 0;
	}


	//mypage
			public ArrayList<OrderBean> getOrderList(String uId) { // 아이디별 주문목록 가져오기
				System.out.println("OrderDAO.getOrderList(uId)");

				PreparedStatement pstmt = null;
				ResultSet rs = null;
				ArrayList<OrderBean> orderList = null;
				String orderNum="";

				try {
					String sql = 

							"select * \n" + 
							"from order_detail orderD join book book\n" + 
							"on orderD.bookID = book.bookID\n" + 
							"join order_tb tb\n" + 
							"on tb.orderNum = orderD.orderNum\n" + 
							"join user user\n" + 
							"on tb.order_ID = user.uID\n" + 
							"where tb.order_ID=? order by tb.orderTime desc";
					
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, uId);
							rs = pstmt.executeQuery();
							
							OrderBean orderBean = null;
							orderList = new ArrayList<OrderBean>();

					while (rs.next()) {

						 orderBean = new OrderBean(
								rs.getString("orderNum"),
								rs.getString("order_ID"),
								rs.getInt("bookEA"),
								rs.getDate("orderTime"),
								rs.getString("orderStatus"),
								rs.getString("orderAddress"),
								rs.getInt("bookID"),
								rs.getString("bookTitle"),
								rs.getString("bookOriginImage"),
								rs.getString("bookPublisher"),
								rs.getInt("bookPrice"),
								rs.getString("u_name2"),
								rs.getString("address2_post"),
								rs.getString("address2_1"),
								rs.getString("address2_2"),
								rs.getString("phone_num2"),
								rs.getString("email"),
								rs.getString("orderRec"),
								rs.getInt("orderDetailCode"),
								rs.getInt("bookKategorie_BKID"),
								rs.getString("paymentType"),
								rs.getString("bookIntroduce"),
								rs.getFloat("saveRatio")
								 );
						orderList.add(orderBean);
					}
					
					for (int i = 0; i < orderList.size(); i++) {
						System.out.println(orderList.get(i).getOrderNum());
					}

						
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					close(rs);
					close(pstmt);
				}
				return orderList;
			}
			
			
			//mypage 주문취소가져오기
			public ArrayList<OrderBean> getOrderListCanCel(String uId) { // 아이디별 주문목록 가져오기
				System.out.println("OrderDAO.getOrderList(uId)");

				PreparedStatement pstmt = null;
				ResultSet rs = null;
				ArrayList<OrderBean> orderList = null;
				String orderNum="";

				try {
					String sql = 

							"select * \n" + 
							"from order_detail orderD join book book\n" + 
							"on orderD.bookID = book.bookID\n" + 
							"join order_tb tb\n" + 
							"on tb.orderNum = orderD.orderNum\n" + 
							"join user user\n" + 
							"on tb.order_ID = user.uID\n" + 
							"where tb.order_ID=? and tb.orderStatus='취소완료'";
					
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, uId);
							rs = pstmt.executeQuery();
							
							OrderBean orderBean = null;
							orderList = new ArrayList<OrderBean>();

					while (rs.next()) {

						 orderBean = new OrderBean(
									rs.getString("orderNum"),
									rs.getString("order_ID"),
									rs.getInt("bookEA"),
									rs.getDate("orderTime"),
									rs.getString("orderStatus"),
									rs.getString("orderAddress"),
									rs.getInt("bookID"),
									rs.getString("bookTitle"),
									rs.getString("bookOriginImage"),
									rs.getString("bookPublisher"),
									rs.getInt("bookPrice"),
									rs.getString("u_name2"),
									rs.getString("address2_post"),
									rs.getString("address2_1"),
									rs.getString("address2_2"),
									rs.getString("phone_num2"),
									rs.getString("email"),
									rs.getString("orderRec"),
									rs.getInt("orderDetailCode"),
									rs.getInt("bookKategorie_BKID"),
									rs.getString("paymentType"),
									rs.getString("bookIntroduce"),
									rs.getFloat("saveRatio")
								 );
						orderList.add(orderBean);
					}
					
					for (int i = 0; i < orderList.size(); i++) {
						System.out.println(orderList.get(i).getOrderNum());
					}

						
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					close(rs);
					close(pstmt);
				}
				return orderList;
			}		
			
			
			//주문 디테일 쿠폰 가져올거
			public MemberBean getCouponInfo(String uId, String orderNum) {
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				MemberBean memberBean =null;
				
				try {
					
					String sql ="select *\n" + 
							"from order_tb tb join couponhistory ch\n" + 
							"on tb.couponHistory_num = ch.num\n" + 
							"join coupon coupon\n" + 
							"on ch.cID = coupon.cID\n" + 
							"where tb.order_ID =? and tb.orderNum=?;";
					
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1,uId);
					pstmt.setString(2,orderNum);
					rs = pstmt.executeQuery();
					
					if (rs.next()) {
					memberBean = new MemberBean(
							rs.getString("coupon_name"),
							rs.getString("couponStatus"),
							rs.getString("couponContent"),
							rs.getDate("couponReg_date"),
							rs.getDate("couponEnd_date"),
							rs.getInt("couponAction"),
							rs.getInt("volume"));
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					close(pstmt);
					close(rs);
				}
				return memberBean;
			}	
	
		//mypage에 표시할 포인트를 따로 조회
		public ArrayList<OrderBean> getMypagePointInfo(String uId) {
			System.out.println("OrderDAO.getMypagePointInfo(String uId)");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<OrderBean> orderList2 = null;
			
			try {
				String sql ="select * from pointhistory where ownerID=?";
									pstmt=con.prepareStatement(sql);
									pstmt.setString(1,uId);
									rs = pstmt.executeQuery();
									
									
									orderList2 = new ArrayList<OrderBean>();
									OrderBean orderBean = null;
				while(rs.next()) {
					orderBean=new OrderBean(
							rs.getInt("pID"),
							rs.getString("ownerID"),
							rs.getDate("pointRegTime"),
							rs.getString("pointContent"),
							rs.getInt("pointValue"),
							rs.getInt("pointAction"),
							rs.getString("orderNum")
							);
					orderList2.add(orderBean);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rs);
			}
			return orderList2;
	    }

		
		//mypage 쿠폰정보 가져오기
		public ArrayList<MemberBean> getCouponList(String uId) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<MemberBean> getCouponList = null;
			
			try {
				String sql=
						"select *\n" + 
						"from order_tb tb join couponhistory ch\n" + 
						"on tb.couponHistory_num = ch.num\n" + 
						"join coupon coupon\n" + 
						"on ch.cID = coupon.cID\n" + 
						"where tb.order_ID =?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1,uId);
				rs = pstmt.executeQuery();

				getCouponList = new ArrayList<MemberBean>();
				MemberBean memberBean = null;
				
				while(rs.next()) {
					memberBean=new MemberBean(
							rs.getString("coupon_name"),
							rs.getString("couponStatus"),
							rs.getString("couponContent"),
							rs.getDate("couponReg_date"),
							rs.getDate("couponEnd_date"),
							rs.getInt("couponAction"),
							rs.getInt("volume"),
							rs.getString("orderNum")
							);
					getCouponList.add(memberBean);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return getCouponList;
			
		}

	
		
		//주문상세
	public ArrayList<OrderBean> orderDetail(String orderNum) {
		System.out.println("OrderDAO.orderDetail(orderNum)");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OrderBean> orderList = null;
		OrderBean orderBean = null;
		String uID=null;
		
		try {
			String sql=
					
					"select *\n" + 
					"from order_detail orderD join book book\n" + 
					"on orderD.bookID = book.bookID\n" + 
					"join order_tb tb\n" + 
					"on tb.orderNum = orderD.orderNum\n" + 
					"join user user\n" + 
					"on tb.order_ID = user.uID\n" + 
					"join pointhistory point\n" + 
					"on point.ownerID = user.uID\n" + 
					"where tb.orderNum=? group by orderD.orderDetailCode";
			
								pstmt=con.prepareStatement(sql);
								pstmt.setString(1,orderNum);
								rs = pstmt.executeQuery();
			
								orderList = new ArrayList<OrderBean>();

			while(rs.next()) {
				orderBean=new OrderBean(

						rs.getString("orderNum"),
						rs.getString("order_ID"),
						rs.getInt("bookEA"),
						rs.getDate("orderTime"),
						rs.getString("orderStatus"),
						rs.getString("orderAddress"),
						rs.getInt("bookID"),
						rs.getString("bookTitle"),
						rs.getString("bookOriginImage"),
						rs.getString("bookPublisher"),
						rs.getInt("bookPrice"),
						rs.getString("u_name"),
						rs.getString("phone_num"),
						rs.getString("u_name2"),
						rs.getString("address2_post"),
						rs.getString("address2_1"),
						rs.getString("address2_2"),
						rs.getString("phone_num2"),
						rs.getString("email"),
						rs.getInt("pointAction"),
						rs.getString("pointContent"),
						rs.getDate("pointRegTime"),
						rs.getInt("pointValue"),
						rs.getString("orderRec"),
						rs.getInt("orderDetailCode"),
						rs.getInt("bookKategorie_BKID"),
						rs.getString("paymentType"),
						rs.getString("bookIntroduce"),
						rs.getFloat("saveRatio"),
						rs.getInt("totalPrice"),
						rs.getInt("shipPrice")
						);
						
				orderList.add(orderBean);
				for (OrderBean orderBean2 : orderList) {
			
					System.out.println(		"결제타입 가져오기"+orderBean2.getPaymentType());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return orderList;

	}	


	
	
	
	//OrderDetail 테이블의 OrderDetailNum으로 판별하는 주문상세
	public OrderBean orderVeryDetail(int orderDetailNum) {
		System.out.println("OrderDAO.orderDetail(orderNum)");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		OrderBean orderBean = null;
		
		try {
			String sql=
					 "select *\n"+ 
					 "from order_detail orderD join book book\n"+ 
					 "on orderD.bookID = book.bookID\n"+ 
					 "join order_tb tb\n" + 
					 "on tb.orderNum = orderD.orderNum\n"+ 
					 "join user user\n"+ 
					 "on tb.order_ID = user.uID\n"+ 
					 "join couponhistory coponH\n"+ 
					 "on coponH.num = tb.couponHistory_num \n"+ 
					 "join coupon cp\n"+ 
					 "on coponH.cID = cp.cID\n"+ 
					 "join pointhistory point\n"+ 
					 "on point.ownerID = user.uID\n"+ 
					 "where orderD.orderDetailCode=?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,orderDetailNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				orderBean=new OrderBean(

						rs.getString("orderNum"),
						rs.getString("order_ID"),
						rs.getInt("bookEA"),
						rs.getDate("orderTime"),
						rs.getString("orderStatus"),
						rs.getString("orderAddress"),
						rs.getInt("bookID"),
						rs.getString("bookTitle"),
						rs.getString("bookOriginImage"),
						rs.getString("bookPublisher"),
						rs.getInt("bookPrice"),
						rs.getString("u_name"),
						rs.getString("phone_num"),
						rs.getString("u_name2"),
						rs.getString("address2_post"),
						rs.getString("address2_1"),
						rs.getString("address2_2"),
						rs.getString("phone_num2"),
						rs.getString("email"),
						rs.getInt("pointAction"),
						rs.getString("pointContent"),
						rs.getDate("pointRegTime"),
						rs.getInt("pointValue"),
						rs.getString("orderRec"),
						rs.getInt("orderDetailCode"),
						rs.getInt("bookKategorie_BKID"),
						rs.getString("paymentType"),
						rs.getString("bookIntroduce"),
						rs.getFloat("saveRatio"),
						rs.getInt("totalPrice"),
						rs.getInt("shipPrice")
						);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return orderBean;

	}	

	// 배송조회보기
	public OrderBean orderDeliveryDetail(int orderDetailNum) {
		System.out.println("OrderDAO.orderDetail(orderNum)");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		OrderBean orderBean = null;
		
		try {
			String sql=
					 "select *\n"+ 
					 "from order_detail orderD join book book\n"+ 
					 "on orderD.bookID = book.bookID\n"+ 
					 "join order_tb tb\n" + 
					 "on tb.orderNum = orderD.orderNum\n"+ 
					 "join user user\n"+ 
					 "on tb.order_ID = user.uID\n"+ 
					 "where orderD.orderDetailCode=?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,orderDetailNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				orderBean=new OrderBean(

						rs.getString("orderNum"),
						rs.getString("order_ID"),
						rs.getInt("bookEA"),
						rs.getDate("orderTime"),
						rs.getString("orderStatus"),
						rs.getString("orderAddress"),
						rs.getInt("bookID"),
						rs.getString("bookTitle"),
						rs.getString("bookOriginImage"),
						rs.getString("bookPublisher"),
						rs.getInt("bookPrice"),
						rs.getString("u_name"),
						rs.getString("phone_num"),
						rs.getString("u_name2"),
						rs.getString("address2_post"),
						rs.getString("address2_1"),
						rs.getString("address2_2"),
						rs.getString("phone_num2"),
						rs.getString("email"),
						rs.getString("orderRec"),
						rs.getInt("orderDetailCode"),
						rs.getInt("bookKategorie_BKID"),
						rs.getString("paymentType"),
						rs.getString("bookIntroduce"),
						rs.getFloat("saveRatio")
						);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return orderBean;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//mypage = 주문목록 검색하기
	public ArrayList<OrderBean> getOrderList(String startDate, String endDate, String uId) { // 아이디별 주문목록 가져오기
		System.out.println("OrderDAO.getOrderList(String startDate, String endDate, String uId)");
		System.out.println(startDate+","+endDate);

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OrderBean> orderList = null;

		try {
			String sql = 
							  "select *\n" + 
							  "from order_detail orderD join book book\n" + 
							  "on orderD.bookID = book.bookID\n" + 
							  "join order_tb tb\n" + 
							  "on tb.orderNum = orderD.orderNum\n" + 
							  "join user user\n" + 
							  "on tb.order_ID = user.uID\n" + 
							  "join couponhistory coponH\n" + 
							  "on coponH.num = tb.couponHistory_num\n" + 
							  "join coupon cp\n" + 
							  "on coponH.cID = cp.cID\n" + 
							  "where tb.order_ID=? and tb.orderTime >=? and tb.orderTime <= date_add(?,interval 1 day)";
			
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, uId);
					pstmt.setString(2, startDate);
					pstmt.setString(3, endDate);
					rs = pstmt.executeQuery();
					
					OrderBean orderBean = null;
					orderList = new ArrayList<OrderBean>();

			while (rs.next()) {

				orderBean = new OrderBean(
						rs.getString("orderNum"),
						rs.getString("order_ID"),
						rs.getInt("bookEA"),
						rs.getDate("orderTime"),
						rs.getString("orderStatus"),
						rs.getString("orderAddress"),
						rs.getInt("bookID"),
						rs.getString("bookTitle"),
						rs.getString("bookOriginImage"),
						rs.getString("bookPublisher"),
						rs.getInt("bookPrice"),
						rs.getString("u_name2"),
						rs.getString("address2_post"),
						rs.getString("address2_1"),
						rs.getString("address2_2"),
						rs.getString("phone_num2"),
						rs.getString("email"),
						rs.getString("coupon_name"),
						rs.getString("couponStatus"),
						rs.getString("orderRec"),
						rs.getInt("orderDetailCode"),
						rs.getInt("bookKategorie_BKID"),
						rs.getString("paymentType"),
						rs.getString("bookIntroduce"),
						rs.getFloat("saveRatio"),
						rs.getInt("volume"),
						rs.getInt("couponAction")
						)
						 ;
				orderList.add(orderBean);
			}
			
			if (rs.next()==false) {
				System.out.println("가져올 데이터 없음");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return orderList;
	}


		
		//주문 상태변경
		public int updateOrderStatus(String orderNum, String changeOrderStatus) {
			int right=0;
			String sqlchange = "";
			PreparedStatement pstmt = null;
			
			if (changeOrderStatus=="반품요청") {
				sqlchange="반품요청";
				System.out.println("DAO 반품신청");
			}else if(changeOrderStatus=="교환요청") {
				sqlchange="교환요청";
				System.out.println("DAO 교환신청");
			}else if(changeOrderStatus=="취소요청") {
				sqlchange="취소요청";
				System.out.println("DAO 취소신청");
			}else if(changeOrderStatus=="구매확정") {
				sqlchange="구매확정";
				System.out.println("DAO 확정신청");
			};
			

			try {
				String sql = "update order_tb set orderStatus=? where orderNum=?";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1,sqlchange);//관리자는 반품처리해줘야함
				pstmt.setString(2,orderNum);
				right = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			return right;
		}

		public int insertOrder(OrderBean orderBean) {
			int insertCount = 0;
			PreparedStatement pstmt = null;
			
			String sql = "INSERT INTO order_tb(orderNum, order_ID, orderRec, orderAddress, orderTime, "
					+ "orderStatus, lastModTime, paymentType, couponHistory_num, totalPrice, shipPrice) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, orderBean.getOrderNum()); pstmt.setString(2, orderBean.getOrder_ID());
				pstmt.setString(3, orderBean.getOrderRec()); pstmt.setString(4, orderBean.getAddress2());
				pstmt.setTimestamp(5, new Timestamp(orderBean.getOrderTime().getTime()));
				pstmt.setString(6, orderBean.getOrderStatus());
				pstmt.setTimestamp(7, new Timestamp(orderBean.getLastModTime().getTime()));
				pstmt.setString(8, orderBean.getPaymentType());
				if(orderBean.getCoupon_num() > 0) {pstmt.setInt(9, orderBean.getCoupon_num());} else {pstmt.setNull(9, Types.INTEGER); } 
				pstmt.setInt(10, orderBean.getTotalPrice());
				pstmt.setInt(11, orderBean.getShipPrice());
				
				int order_tb_count = pstmt.executeUpdate();
				
				if(order_tb_count > 0) {
					sql = "INSERT INTO order_detail(bookID, orderNum, bookTitle, bookPrice, bookEA) "
						+ "VALUES(?, ?, ?, ?, ?)";
					int order_detail_count = 0;
					for(OrderDetailBean orderDetail : orderBean.getOrderList()) {
						pstmt = con.prepareStatement(sql);
						
						pstmt.setInt(1, orderDetail.getBookID()); pstmt.setString(2, orderDetail.getOrderNum());
						pstmt.setString(3, orderDetail.getBookTitle()); pstmt.setInt(4, orderDetail.getBookPrice());
						pstmt.setInt(5, orderDetail.getBookEA());
						
						order_detail_count += pstmt.executeUpdate();
					}
					if(order_detail_count == orderBean.getOrderList().size()) {
						insertCount = 1;
					}
				
				}
				
				if(insertCount > 0) {
					sql ="update user set u_name2=?, address2_post =? "
							+ ", address2_1=?, address2_2=?, phone_num2=? where uID=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, orderBean.getU_name2());
					pstmt.setString(2, orderBean.getAddress2_post());
					pstmt.setString(3, orderBean.getAddress2_1());
					pstmt.setString(4, orderBean.getAddress2_2());
					pstmt.setString(5, orderBean.getPhone_num2());
					pstmt.setString(6, orderBean.getOrder_ID());
					
					insertCount = pstmt.executeUpdate();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return insertCount;
		}

		public int updateBookEA(List<OrderDetailBean> orderList) {
			// TODO Auto-generated method stub
			PreparedStatement pstmt = null;
			int updateBookCount = 0;
			
			String sql ="UPDATE book SET bookEA=bookEA-?, salesVolume=salesVolume+? WHERE bookID=?";
			
			try {
				for(OrderDetailBean orderDetailBean : orderList) {
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, orderDetailBean.getBookEA()); pstmt.setInt(2, orderDetailBean.getBookEA());
					pstmt.setInt(3, orderDetailBean.getBookID());
					
					updateBookCount += pstmt.executeUpdate();
				}
				if(updateBookCount != orderList.size()) {
					updateBookCount = 0;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return updateBookCount;
		}

		public int couponUpdate(int cHistory_num, String id) {
			PreparedStatement pstmt = null;
			int couponUpdateCount = 0;
			
			String sql ="UPDATE couponHistory SET couponHistoryDate=now(),couponStatus=?,couponContent=?,couponAction=? "
					+ "WHERE num=? AND uID=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "사용함");
				pstmt.setString(2, "결제완료");
				pstmt.setBoolean(3, true);
				pstmt.setInt(4, cHistory_num); pstmt.setString(5, id);
				
				couponUpdateCount = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return couponUpdateCount;
		}


		public int setPointHistory(String id, int usedPoint, int totalPrice, String orderNum) {
			PreparedStatement pstmt = null;
			int setPointHistoryCount = 0;
			String sql ="";
			int changedPoint = 0;
			 
			// pointAction  사용은 0 / 적립은 1
			try {
				sql = "INSERT INTO pointHistory(ownerID, pointRegTime, pointContent, pointValue, pointAction, orderNum) VALUES(?, now(), ?, ?, ?, ?)";
				if(usedPoint != 0) { // 사용 포인트가 0이 아닐 때
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, id); pstmt.setString(2, "상품 금액 차감"); pstmt.setInt(3, usedPoint); pstmt.setInt(4, 0); // 사용
					pstmt.setString(5, orderNum);
				
					setPointHistoryCount = pstmt.executeUpdate();
					if(setPointHistoryCount > 0) {
						changedPoint = -usedPoint;
						setPointHistoryCount = 0;
					} else {
						return 0;
					}
					
				}
				
				int savedPoint = (int)(totalPrice * 0.05);
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id); pstmt.setString(2, "상품 구매 적립"); pstmt.setInt(3, savedPoint); // 일괄 5퍼센트 적립
				pstmt.setInt(4, 1); // 적립
				pstmt.setString(5, orderNum);
				setPointHistoryCount = pstmt.executeUpdate();
				if(setPointHistoryCount > 0) {
					changedPoint += savedPoint;
					setPointHistoryCount = 0;
				} else {
					return 0;
				}
				
				sql = "UPDATE user SET point=point+? WHERE uID=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, changedPoint); pstmt.setString(2, id);
				
				setPointHistoryCount = pstmt.executeUpdate();
				if(setPointHistoryCount > 0) {
				} else {
					return 0;
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return setPointHistoryCount;
		}

		public List<OrderBean> orderList() {
			System.out.println("OrderDAO - orderList()");
			List<OrderBean> list = new ArrayList();
			List<OrderBean> listDetail = new ArrayList();
			List<OrderBean> list2 = new ArrayList();
			OrderBean orderStatus = new OrderBean();
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ResultSet rs2 = null;
			
			try {
					
//					String sql = "select * from order_tb WHERE NOT orderStatus IN ('배송내역','반품완료','교환완료','주문완료') ORDER BY orderTime DESC;";
					String sql = "select * from order_tb";
					
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
	
				
				while (rs.next()) {
					OrderBean ob = new OrderBean();
					ob.setOrderTime(rs.getDate("orderTime"));
					ob.setOrderNum(rs.getString("orderNum"));
					ob.setOrder_id(rs.getString("order_ID"));				
					//					ob.setBookPrice(rs.getInt("bookPrice")); 
					ob.setPaymentType(rs.getString("paymentType"));
					ob.setOrderStatus(rs.getString("orderStatus"));
					//					
					//					ob.setOrderRec(rs.getString("orderRec"));				
					//					ob.setOrderAddress(rs.getString("orderAddress"));				
					//					ob.setLastModTime(rs.getDate("lastModTime"));				
					//					ob.setCouponHistory_num(rs.getInt("couponHisstory_num"));				
					
					
					sql = "select * from order_detail where orderNum=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, rs.getString("orderNum"));
					rs2 = pstmt.executeQuery();
					
					int totalBook=0;
					
					while (rs2.next()) {
						
						totalBook+=rs2.getInt("bookPrice")*rs2.getInt("bookEA");
						
					}
					ob.setBookPrice(totalBook);
					
					list.add(ob);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
				close(rs);
			}
			
			return list;
		}
		
		//완료된 주문들만 불러오기
		public List<OrderBean> orderComplList(OrderBean orderStatus) {
			System.out.println("OrderDAO - orderComplList()");
			List<OrderBean> complList = new ArrayList(); 
			List<OrderBean> listDetail = new ArrayList();
			
			System.out.println("orderDAO orderbean's status: " + orderStatus.getOrderStatus());
			System.out.println("orderDAO if cencel status : " + orderStatus.getOrderStatus());

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ResultSet rs2 = null;
			String sql = "";
			try {
				  
				if(orderStatus.getOrderStatus().equals("취소요청")) {
					sql = "select * from order_tb WHERE orderStatus in('취소요청','취소완료') ORDER BY orderTime DESC";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					
				} else if(orderStatus.getOrderStatus().equals("반품요청")) {
					sql = "select * from order_tb WHERE orderStatus in('반품요청','반품완료') ORDER BY orderTime DESC";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					
				} else if(orderStatus.getOrderStatus().equals("교환요청")) {
					sql = "select * from order_tb WHERE orderStatus in('교환요청','교환완료') ORDER BY orderTime DESC";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();

				} else if(orderStatus.getOrderStatus().equals("결제완료")) {
					sql = "select * from order_tb WHERE orderStatus in('결제완료','배송중','배송완료') ORDER BY orderTime DESC";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();

				}  
//				else if(orderStatus.getOrderStatus().equals("주문완료")) {
//					sql = "select * from order_tb WHERE orderStatus in('결제완료','배송요청','취소요청'.'반품요청','교환요청') ORDER BY orderTime DESC";
//					pstmt = con.prepareStatement(sql);
//					rs = pstmt.executeQuery();
//
//				}  
//			
			while (rs.next()) {
				OrderBean ob = new OrderBean();
				ob.setOrderTime(rs.getDate("orderTime"));
				ob.setOrderNum(rs.getString("orderNum"));
				ob.setOrder_id(rs.getString("order_ID"));				
				//					ob.setBookPrice(rs.getInt("bookPrice")); 
				ob.setPaymentType(rs.getString("paymentType"));
				ob.setOrderStatus(rs.getString("orderStatus"));
				//					ob.setOrderRec(rs.getString("orderRec"));				
				//					ob.setOrderAddress(rs.getString("orderAddress"));				
				//					ob.setLastModTime(rs.getDate("lastModTime"));				
				//					ob.setCouponHistory_num(rs.getInt("couponHisstory_num"));				
				
				
				String sql2 = "select * from order_detail where orderNum=?";
				pstmt = con.prepareStatement(sql2);
				pstmt.setString(1, rs.getString("orderNum"));
				rs2 = pstmt.executeQuery();
				
				int totalBook=0;
				
				while (rs2.next()) {
					
					totalBook+=rs2.getInt("bookPrice")*rs2.getInt("bookEA");
					
				}
				ob.setBookPrice(totalBook);
				
				complList.add(ob);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
			
			return complList;
		}
		
		// 메인에서 주문현황 가져오기
		public int getOrderCount(String orderStatus) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int orderCount = 0;
			String sql = "";
			try {
				if(orderStatus.equals("결제완료")) {
					sql = "select count(*) from order_tb WHERE orderStatus=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, "결제완료");
					rs = pstmt.executeQuery();
				} else if(orderStatus.equals("배송중")) {
					sql = "select count(*) from order_tb WHERE orderStatus=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, "배송중");
					rs = pstmt.executeQuery();
				} else if(orderStatus.equals("배송완료")) {
					sql = "select count(*) from order_tb WHERE orderStatus=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, "배송완료");
					rs = pstmt.executeQuery();
				}
					
				if (rs.next()) {
					orderCount = rs.getInt(1);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
			
			return orderCount;
		}		
		
		// 메인에서 매출현황 가져오기
		public int orderComplList(String orderStatus) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int cashe = 0;
			String sql = "";
			try {
				if(orderStatus.equals("")) {
					sql = "select sum(totalPrice) from order_tb";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
				} else if(orderStatus.equals("취소완료")) {
					sql = "select sum(totalPrice) from order_tb WHERE orderStatus=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, "취소완료");
					rs = pstmt.executeQuery();
				} else if(orderStatus.equals("반품완료")) {
					sql = "select sum(totalPrice) from order_tb WHERE orderStatus=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, "반품완료");
					rs = pstmt.executeQuery();
				}
					
				if (rs.next()) {
					cashe = rs.getInt(1);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
			
			return cashe;
		}

		// 메인에서 월 별 매출현황 가져오기
		public int orderComplList(int year, int month) {
			int monthTotal = 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "";
			try {
					sql = "select sum(totalPrice) from order_tb "
							+ "where year(orderTime)=? and month(orderTime)=? and orderStatus not in (?,?)";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, year);
					pstmt.setInt(2, month);
					pstmt.setString(3, "반품완료");
					pstmt.setString(4, "취소완료");
					rs = pstmt.executeQuery();
				if (rs.next()) {
					monthTotal = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
				close(rs);
			}
			
			
			return monthTotal;
		}
		
		public ArrayList<OrderBean> orderTotal(String orderNum) {
			System.out.println("OrderDAO - orderList()");
			ArrayList<OrderBean> total = new ArrayList();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "select * from order_detail";

//				String sql = "select * from order_detail WHERE orderNum=?";
				pstmt = con.prepareStatement(sql);
//				pstmt.setString(1, orderNum);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					OrderBean ob = new OrderBean();
					ob.setOrderDetailCode(rs.getInt("orderDetailCode"));
					ob.setbookID(rs.getInt("bookID"));
					ob.setBookKategorie_BKID(rs.getInt("bookKategorie_BKID"));
					ob.setOrderNum(rs.getString("orderNum"));
					ob.setBookTitle(rs.getString("bookTitle"));
					ob.setBookPrice(rs.getInt("bookPrice"));
					ob.setBookEA(rs.getInt("bookEA"));
					ob.setPaymentType(rs.getString("paymentType"));
				
					total.add(ob);

				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close(pstmt);
				close(rs);
			}

			return total;
		}	
		
		//주문한 책 목록 불러오기
		public List<OrderBean> selectOrder(String orderNum) {
			System.out.println("OrderDAO - orderList()");
			List<OrderBean> order = new ArrayList<OrderBean>();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "select * from order_detail WHERE orderNum=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, orderNum);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					OrderBean orderBean = new OrderBean();
					orderBean.setOrderDetailCode(rs.getInt("orderDetailCode"));
					orderBean.setbookID(rs.getInt("bookID"));
//					order.setBookKategorie_BKID(rs.getInt("bookKategorie_BKID"));
					orderBean.setOrderNum(rs.getString("orderNum"));
					orderBean.setBookTitle(rs.getString("bookTitle"));
					orderBean.setBookPrice(rs.getInt("bookPrice"));
					orderBean.setBookEA(rs.getInt("bookEA"));
//					order.setPaymentType(rs.getString("paymentType"));
					order.add(orderBean);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close(pstmt);
				close(rs);
			}

			return order;
		}	
	
		//주문상세내용조회
		public OrderBean orderDetaile(String orderNum) {
			System.out.println("OrderDAO - orderList()");
			OrderBean orderDe = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "select * from order_tb WHERE orderNum=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, orderNum);
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					orderDe = new OrderBean();
					orderDe.setOrderNum(rs.getString("orderNum"));
					orderDe.setOrder_id(rs.getString("order_ID"));	
					orderDe.setOrderRec(rs.getString("orderRec"));				
					orderDe.setOrderAddress(rs.getString("orderAddress"));				
					orderDe.setOrderTime(rs.getDate("orderTime"));
					orderDe.setOrderStatus(rs.getString("orderStatus"));
					orderDe.setLastModTime(rs.getDate("lastModTime"));				
					orderDe.setPaymentType(rs.getString("paymentType"));
					orderDe.setCouponHistory_num(rs.getInt("couponHistory_num"));				
					orderDe.setTotalPrice(rs.getInt("totalPrice"));				
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close(pstmt);
				close(rs);
			}

			return orderDe;
		}		
		//주문금액 가져오기
//		public ArrayList<OrderBean> orderTotal(String orderNum) {
//			System.out.println("OrderDAO - orderList()");
//			ArrayList<OrderBean> total = new ArrayList();
//			PreparedStatement pstmt = null;
//			ResultSet rs = null;
//			
//			try {
//				String sql = "select * from order_detail";
//
////				String sql = "select * from order_detail WHERE orderNum=?";
//				pstmt = con.prepareStatement(sql);
////				pstmt.setString(1, orderNum);
//				rs = pstmt.executeQuery();
//				
//				while (rs.next()) {
//					OrderBean ob = new OrderBean();
//					ob.setOrderDetailCode(rs.getInt("orderDetailCode"));
//					ob.setbookID(rs.getInt("bookID"));
//					ob.setBookKategorie_BKID(rs.getInt("bookKategorie_BKID"));
//					ob.setOrderNum(rs.getString("orderNum"));
//					ob.setBookTitle(rs.getString("bookTitle"));
//					ob.setBookPrice(rs.getInt("bookPrice"));
//					ob.setBookEA(rs.getInt("bookEA"));
//					ob.setPaymentType(rs.getString("paymentType"));
//				
//					total.add(ob);
//
//				}
//
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} finally {
//				close(pstmt);
//				close(rs);
//			}
//
//			return total;
//		}	
		

		public int updateOrder(OrderBean order) {
			int updateCount = 0;
			
			PreparedStatement pstmt = null;

			try {
//				String sql = "UPDATE board SET board_name=?,board_subject=?,board_content=? WHERE board_num=?";
				String sql = "UPDATE order_tb SET orderStatus=? WHERE orderNum=?";
				pstmt = con.prepareStatement(sql);
//				pstmt.setString(1, article.getBoard_name());
				System.out.println("updateOrder의 orderStatus :" + order.getOrderStatus());
				System.out.println("updateOrder의 orderNum :" + order.getOrderNum());
				pstmt.setString(1, order.getOrderStatus());
				pstmt.setString(2, order.getOrderNum());

				updateCount = pstmt.executeUpdate();
				System.out.println("updateCount DAO : " + updateCount);

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return updateCount;

		}

		
		public ArrayList<OrderBean> getOrderCenCelList(String startDate, String endDate, String uId) {
			System.out.println("OrderDAO.getOrderCenCelList(String startDate, String endDate, String uId)");
			System.out.println(startDate+","+endDate);

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<OrderBean> orderList = null;

			try {
				String sql = 
								  "select *\n" + 
								  "from order_detail orderD join book book\n" + 
								  "on orderD.bookID = book.bookID\n" + 
								  "join order_tb tb\n" + 
								  "on tb.orderNum = orderD.orderNum\n" + 
								  "join user user\n" + 
								  "on tb.order_ID = user.uID\n" + 
								  "join couponhistory coponH\n" + 
								  "on coponH.num = tb.couponHistory_num\n" + 
								  "join coupon cp\n" + 
								  "on coponH.cID = cp.cID\n" + 
								  "where tb.order_ID=? and tb.orderStatus='취소완료' and tb.orderTime >=? and tb.orderTime <= date_add(?,interval 1 day)";
				
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, uId);
						pstmt.setString(2, startDate);
						pstmt.setString(3, endDate);
						rs = pstmt.executeQuery();
						
						OrderBean orderBean = null;
						orderList = new ArrayList<OrderBean>();

				while (rs.next()) {

					orderBean = new OrderBean(
							rs.getString("orderNum"),
							rs.getString("order_ID"),
							rs.getInt("bookEA"),
							rs.getDate("orderTime"),
							rs.getString("orderStatus"),
							rs.getString("orderAddress"),
							rs.getInt("bookID"),
							rs.getString("bookTitle"),
							rs.getString("bookOriginImage"),
							rs.getString("bookPublisher"),
							rs.getInt("bookPrice"),
							rs.getString("u_name2"),
							rs.getString("address2_post"),
							rs.getString("address2_1"),
							rs.getString("address2_2"),
							rs.getString("phone_num2"),
							rs.getString("email"),
							rs.getString("coupon_name"),
							rs.getString("couponStatus"),
							rs.getString("orderRec"),
							rs.getInt("orderDetailCode"),
							rs.getInt("bookKategorie_BKID"),
							rs.getString("paymentType"),
							rs.getString("bookIntroduce"),
							rs.getFloat("saveRatio"),
							rs.getInt("volume"),
							rs.getInt("couponAction")
							)
							 ;
					orderList.add(orderBean);
				}
				
				if (rs.next()==false) {
					System.out.println("가져올 데이터 없음");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			return orderList;
		}

		
		//배송중
		public List<OrderBean> getDeliveryList(String uId) {
			System.out.println("OrderDAO.getOrderList(uId)");

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<OrderBean> orderList = null;
			String orderNum="";

			try {
				String sql = 

						"select * \n" + 
						"from order_detail orderD join book book\n" + 
						"on orderD.bookID = book.bookID\n" + 
						"join order_tb tb\n" + 
						"on tb.orderNum = orderD.orderNum\n" + 
						"join user user\n" + 
						"on tb.order_ID = user.uID\n" + 
						"where tb.order_ID=? and tb.orderStatus='배송중' ";
				
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, uId);
						rs = pstmt.executeQuery();
						
						OrderBean orderBean = null;
						orderList = new ArrayList<OrderBean>();

				while (rs.next()) {

					 orderBean = new OrderBean(
								rs.getString("orderNum"),
								rs.getString("order_ID"),
								rs.getInt("bookEA"),
								rs.getDate("orderTime"),
								rs.getString("orderStatus"),
								rs.getString("orderAddress"),
								rs.getInt("bookID"),
								rs.getString("bookTitle"),
								rs.getString("bookOriginImage"),
								rs.getString("bookPublisher"),
								rs.getInt("bookPrice"),
								rs.getString("u_name2"),
								rs.getString("address2_post"),
								rs.getString("address2_1"),
								rs.getString("address2_2"),
								rs.getString("phone_num2"),
								rs.getString("email"),
								rs.getString("orderRec"),
								rs.getInt("orderDetailCode"),
								rs.getInt("bookKategorie_BKID"),
								rs.getString("paymentType"),
								rs.getString("bookIntroduce"),
								rs.getFloat("saveRatio")
							 );
					orderList.add(orderBean);
				}
				
				for (int i = 0; i < orderList.size(); i++) {
					System.out.println(orderList.get(i).getOrderNum());
				}

					
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			return orderList;
		}

		
		//반품교환
		public List<OrderBean> getorderCanCelReFundExCangeList(String uId) {

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<OrderBean> orderList = null;
			String orderNum="";

			try {
				String sql = 

						"select * \n" + 
						"from order_detail orderD join book book\n" + 
						"on orderD.bookID = book.bookID\n" + 
						"join order_tb tb\n" + 
						"on tb.orderNum = orderD.orderNum\n" + 
						"join user user\n" + 
						"on tb.order_ID = user.uID\n" + 
						"where tb.order_ID=? and tb.orderStatus='반품완료' or tb.orderStatus='반품요청' or tb.orderStatus='교환요청' or tb.orderStatus='교환완료' or tb.orderStatus='취소요청' or tb.orderStatus='취소완료'";
				
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, uId);
						rs = pstmt.executeQuery();
						
						OrderBean orderBean = null;
						orderList = new ArrayList<OrderBean>();

				while (rs.next()) {

					 orderBean = new OrderBean(
								rs.getString("orderNum"),
								rs.getString("order_ID"),
								rs.getInt("bookEA"),
								rs.getDate("orderTime"),
								rs.getString("orderStatus"),
								rs.getString("orderAddress"),
								rs.getInt("bookID"),
								rs.getString("bookTitle"),
								rs.getString("bookOriginImage"),
								rs.getString("bookPublisher"),
								rs.getInt("bookPrice"),
								rs.getString("u_name2"),
								rs.getString("address2_post"),
								rs.getString("address2_1"),
								rs.getString("address2_2"),
								rs.getString("phone_num2"),
								rs.getString("email"),
								rs.getString("orderRec"),
								rs.getInt("orderDetailCode"),
								rs.getInt("bookKategorie_BKID"),
								rs.getString("paymentType"),
								rs.getString("bookIntroduce"),
								rs.getFloat("saveRatio")
							 );
					orderList.add(orderBean);
				}
				
				for (int i = 0; i < orderList.size(); i++) {
					System.out.println(orderList.get(i).getOrderNum());
				}

					
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			return orderList;
		}
}