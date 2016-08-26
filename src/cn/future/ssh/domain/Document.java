package cn.future.ssh.domain;
//文书
public class Document {
	private Long id;
	private String imageName;
	private Accreditation accreditation;
	
	
	
	public Accreditation getAccreditation() {
		return accreditation;
	}

	public void setAccreditation(Accreditation accreditation) {
		this.accreditation = accreditation;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getDocumentFullPath(){
		return "/document/"+accreditation.getId()+"/"+imageName;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	
}
