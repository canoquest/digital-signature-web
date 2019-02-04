package br.com.unicred.autentique.client.model.response;

import java.util.Date;
import java.util.List;

public class DocumentResponse {
	
	private String uuid;
	
	private String  nome;
	
	private Date created;
	
	private String assinatura;
	
	private String transacao;
	
	private Date publicado;
	
	private List<AssinaturaResponse> assinaturas;

	public DocumentResponse() {
		super();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getAssinatura() {
		return assinatura;
	}

	public void setAssinatura(String assinatura) {
		this.assinatura = assinatura;
	}

	public String getTransacao() {
		return transacao;
	}

	public void setTransacao(String transacao) {
		this.transacao = transacao;
	}

	public Date getPublicado() {
		return publicado;
	}

	public void setPublicado(Date publicado) {
		this.publicado = publicado;
	}

	public List<AssinaturaResponse> getAssinaturas() {
		return assinaturas;
	}

	public void setAssinaturas(List<AssinaturaResponse> assinaturas) {
		this.assinaturas = assinaturas;
	}	

}