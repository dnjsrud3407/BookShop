package vo;

import java.util.Date;

public class BookBean {
	private int bookID;
    private String bookTitle;
    private String bookOriginImage; // 서버에 저장되는 상품 이미지 이름
    private String bookImage;   // 상품이미지 이름(보여지는)
    private String bookPublisher;   // 출판사
    private Date bookPublishedDate; // 출판일
    private int bookPrice;  // 가격
    private int bookEA; // 상품 재고
    private int salesVolume;
    private String bookIntroduce;   // 상품 소개
    private boolean bookisView; // 상품의 공개여부
    private float saveRatio;    // 상품별 포인트 적립률
    private int bookKategorie_BKID;
    private String BK1;
    private String BK2;
    private String BK3;
    
    public BookBean() {
		super();
	}

	// 책 등록할 때 사용
    public BookBean(int bookID, String bookTitle, String bookOriginImage, String bookImage, String bookPublisher,
            Date bookPublishedDate, int bookPrice, int bookEA, int salesVolume, String bookIntroduce, boolean bookisView,
            float saveRatio, int bookKategorie_BKID) {
        super();
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.bookOriginImage = bookOriginImage;
        this.bookImage = bookImage;
        this.bookPublisher = bookPublisher;
        this.bookPublishedDate = bookPublishedDate;
        this.bookPrice = bookPrice;
        this.bookEA = bookEA;
        this.salesVolume = salesVolume;
        this.bookIntroduce = bookIntroduce;
        this.bookisView = bookisView;
        this.saveRatio = saveRatio;
        this.bookKategorie_BKID = bookKategorie_BKID;
    }
    
    // 책 상세보기 (모든 컬럼 포함)
    public BookBean(int bookID, String bookTitle, String bookOriginImage, String bookImage, String bookPublisher,
			Date bookPublishedDate, int bookPrice, int bookEA, int salesVolume, String bookIntroduce, boolean bookisView,
			float saveRatio, int bookKategorie_BKID, String bK1, String bK2, String bK3) {
		super();
		this.bookID = bookID;
		this.bookTitle = bookTitle;
		this.bookOriginImage = bookOriginImage;
		this.bookImage = bookImage;
		this.bookPublisher = bookPublisher;
		this.bookPublishedDate = bookPublishedDate;
		this.bookPrice = bookPrice;
		this.bookEA = bookEA;
		this.salesVolume = salesVolume;
		this.bookIntroduce = bookIntroduce;
		this.bookisView = bookisView;
		this.saveRatio = saveRatio;
		this.bookKategorie_BKID = bookKategorie_BKID;
		this.BK1 = bK1;
		this.BK2 = bK2;
		this.BK3 = bK3;
	}
    
    // 수정할 때 사용 (salseVolum 이 없음)
    public BookBean(int bookID, String bookTitle, String bookOriginImage, String bookImage, String bookPublisher,
			Date bookPublishedDate, int bookPrice, int bookEA, String bookIntroduce, boolean bookisView,
			float saveRatio, int bookKategorie_BKID, String bK1, String bK2, String bK3) {
		super();
		this.bookID = bookID;
		this.bookTitle = bookTitle;
		this.bookOriginImage = bookOriginImage;
		this.bookImage = bookImage;
		this.bookPublisher = bookPublisher;
		this.bookPublishedDate = bookPublishedDate;
		this.bookPrice = bookPrice;
		this.bookEA = bookEA;
		this.bookIntroduce = bookIntroduce;
		this.bookisView = bookisView;
		this.saveRatio = saveRatio;
		this.bookKategorie_BKID = bookKategorie_BKID;
		this.BK1 = bK1;
		this.BK2 = bK2;
		this.BK3 = bK3;
	}

    // 메인에서 중간 배너 사용할 때
	public BookBean(int bookID, String bookTitle, String bookOriginImage, String bookImage, String bookPublisher, Date bookPublishedDate,
			int bookPrice) {
		super();
		this.bookID = bookID;
		this.bookTitle = bookTitle;
		this.bookOriginImage = bookOriginImage;
		this.bookImage = bookImage;
		this.bookPublisher = bookPublisher;
		this.bookPublishedDate = bookPublishedDate;
		this.bookPrice = bookPrice;
	}
	
	// 메인에서 베스트 셀러 사용할 때
	public BookBean(int bookID, String bookTitle, String bookImage) {
		super();
		this.bookID = bookID;
		this.bookTitle = bookTitle;
		this.bookImage = bookImage;
	}
	
	// 책리스트 불러올 때 카테고리 
	public BookBean(int bookKategorie_BKID, String bK1, String bK2, String bK3) {
		super();
		this.bookKategorie_BKID = bookKategorie_BKID;
		BK1 = bK1;
		BK2 = bK2;
		BK3 = bK3;
	}
	
	// 책 리스트에서 대표 카테고리 지정할 때 사용
	public BookBean(int bookKategorie_BKID, String bK2) {
		super();
		this.bookKategorie_BKID = bookKategorie_BKID;
		BK2 = bK2;
	}

	public int getBookID() {
        return bookID;
    }

	public void setBookID(int bookID) {
        this.bookID = bookID;
    }
    public String getBookTitle() {
        return bookTitle;
    }
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
    public String getBookOriginImage() {
        return bookOriginImage;
    }
    public void setBookOriginImage(String bookOriginImage) {
        this.bookOriginImage = bookOriginImage;
    }
    public String getBookImage() {
        return bookImage;
    }
    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }
    public String getBookPublisher() {
        return bookPublisher;
    }
    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher = bookPublisher;
    }
    public Date getBookPublishedDate() {
        return bookPublishedDate;
    }
    public void setBookPublishedDate(Date bookPublishedDate) {
        this.bookPublishedDate = bookPublishedDate;
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
    
    public int getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(int salesVolume) {
		this.salesVolume = salesVolume;
	}

	public String getBookIntroduce() {
        return bookIntroduce;
    }
    public void setBookIntroduce(String bookIntroduce) {
        this.bookIntroduce = bookIntroduce;
    }
    public boolean isBookisView() {
        return bookisView;
    }
    public void setBookisView(boolean bookisView) {
        this.bookisView = bookisView;
    }
    public float getSaveRatio() {
        return saveRatio;
    }
    public void setSaveRatio(float saveRatio) {
        this.saveRatio = saveRatio;
    }
	public int getBookKategorie_BKID() {
		return bookKategorie_BKID;
	}
	public void setBookKategorie_BKID(int bookKategorie_BKID) {
		this.bookKategorie_BKID = bookKategorie_BKID;
	}

	public String getBK1() {
		return BK1;
	}

	public void setBK1(String bK1) {
		BK1 = bK1;
	}

	public String getBK2() {
		return BK2;
	}

	public void setBK2(String bK2) {
		BK2 = bK2;
	}

	public String getBK3() {
		return BK3;
	}

	public void setBK3(String bK3) {
		BK3 = bK3;
	}
	
    
}
