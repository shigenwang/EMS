package cn.future.ssh.domain;
//pdf文书
public class PDFDocument {
	
	private Long id;
	
	private String name;
	
	private String reallyName;
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getReallyName() {
		return reallyName;
	}

	public void setReallyName(String reallyName) {
		this.reallyName = reallyName;
	}

	public String getDocumentFullPath(){
		return "document/pdf/"+name;
	}
}
