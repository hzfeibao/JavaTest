package test.file;

public class ExportHeader {
	private int no;
	private String property;
	private String header;
	
	public ExportHeader() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExportHeader(int no, String property, String header) {
		super();
		this.no = no;
		this.property = property;
		this.header = header;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}
	
}
