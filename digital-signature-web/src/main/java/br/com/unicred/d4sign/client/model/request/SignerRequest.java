package br.com.unicred.d4sign.client.model.request;

public class SignerRequest {
	
	private String email;
	
	private String act;
	
	private String foreign;
	
	private String certificadoicpbr;
	
	private String assinatura_presencial;
	
	private String docauth;
	
	private String docauthandselfie;
	
	private String embed_methodauth;
	
	private String embed_smsnumber;
	
	private String upload_allow;
	
	private String upload_obs;

	public SignerRequest() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public String getForeign() {
		return foreign;
	}

	public void setForeign(String foreign) {
		this.foreign = foreign;
	}

	public String getCertificadoicpbr() {
		return certificadoicpbr;
	}

	public void setCertificadoicpbr(String certificadoicpbr) {
		this.certificadoicpbr = certificadoicpbr;
	}

	public String getAssinatura_presencial() {
		return assinatura_presencial;
	}

	public void setAssinatura_presencial(String assinatura_presencial) {
		this.assinatura_presencial = assinatura_presencial;
	}

	public String getDocauth() {
		return docauth;
	}

	public void setDocauth(String docauth) {
		this.docauth = docauth;
	}

	public String getDocauthandselfie() {
		return docauthandselfie;
	}

	public void setDocauthandselfie(String docauthandselfie) {
		this.docauthandselfie = docauthandselfie;
	}

	public String getEmbed_methodauth() {
		return embed_methodauth;
	}

	public void setEmbed_methodauth(String embed_methodauth) {
		this.embed_methodauth = embed_methodauth;
	}

	public String getEmbed_smsnumber() {
		return embed_smsnumber;
	}

	public void setEmbed_smsnumber(String embed_smsnumber) {
		this.embed_smsnumber = embed_smsnumber;
	}

	public String getUpload_allow() {
		return upload_allow;
	}

	public void setUpload_allow(String upload_allow) {
		this.upload_allow = upload_allow;
	}

	public String getUpload_obs() {
		return upload_obs;
	}

	public void setUpload_obs(String upload_obs) {
		this.upload_obs = upload_obs;
	}	

}