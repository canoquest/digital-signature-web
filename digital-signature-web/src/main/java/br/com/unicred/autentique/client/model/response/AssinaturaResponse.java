package br.com.unicred.autentique.client.model.response;

public class AssinaturaResponse {
	
	private String assinado;
	
	private String funcao;
	
	private String email;

	public AssinaturaResponse() {
		super();
	}

	public String getAssinado() {
		return assinado;
	}

	public void setAssinado(String assinado) {
		this.assinado = assinado;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}