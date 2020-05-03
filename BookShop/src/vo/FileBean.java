
package vo;

	public class FileBean {

		private int fileNum;
		private String originFilename;
		private String storedFileName;
		private String fileType;
		private int board_boardNum;
		private int board_kID;
		private String board_boardWriter;
		
		
		
		public FileBean(int fileNum, String originFilename, String storedFileName, String fileType, int board_boardNum,
				int board_kID, String board_boardWriter) {
			super();
			this.fileNum = fileNum;
			this.originFilename = originFilename;
			this.storedFileName = storedFileName;
			this.fileType = fileType;
			this.board_boardNum = board_boardNum;
			this.board_kID = board_kID;
			this.board_boardWriter = board_boardWriter;
		}

		public FileBean(String originFilename, String storedFileName, int board_boardNum, int board_kID) {
			super();
			this.originFilename = originFilename;
			this.storedFileName = storedFileName;
			this.board_boardNum = board_boardNum;
			this.board_kID = board_kID;
		}

		public FileBean(String originFilename, String storedFileName, String fileType, String board_boardWriter) {
			super();
			this.originFilename = originFilename;
			this.storedFileName = storedFileName;
			this.fileType = fileType;
			this.board_boardWriter = board_boardWriter;
		}

		public FileBean(String originFilename, String storedFileName) {
			super();
			this.originFilename = originFilename;
			this.storedFileName = storedFileName;
		}

		
		public FileBean(String originFilename, String storedFileName, String fileType) {
			super();
			this.originFilename = originFilename;
			this.storedFileName = storedFileName;
			this.fileType = fileType;
		}

		public int getFileNum() {
			return fileNum;
		}

		public void setFileNum(int fileNum) {
			this.fileNum = fileNum;
		}

		public String getOriginFilename() {
			return originFilename;
		}

		public void setOriginFilename(String originFilename) {
			this.originFilename = originFilename;
		}

		public String getstoredFileName() {
			return storedFileName;
		}

		public void setstoredFileName(String storedFileName) {
			this.storedFileName = storedFileName;
		}

		public String getFileType() {
			return fileType;
		}

		public void setFileType(String fileType) {
			this.fileType = fileType;
		}

		public int getBoard_boardNum() {
			return board_boardNum;
		}

		public void setBoard_boardNum(int board_boardNum) {
			this.board_boardNum = board_boardNum;
		}

		public int getBoard_kID() {
			return board_kID;
		}

		public void setBoard_kID(int board_kID) {
			this.board_kID = board_kID;
		}

		public String getBoard_boardWriter() {
			return board_boardWriter;
		}

		public void setBoard_boardWriter(String board_boardWriter) {
			this.board_boardWriter = board_boardWriter;
		}
		
		
		
		
	}

	
