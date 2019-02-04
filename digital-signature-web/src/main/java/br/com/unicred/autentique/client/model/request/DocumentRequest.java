package br.com.unicred.autentique.client.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DocumentRequest {
	
	private String nome;
	
	@JsonProperty("partes[0][email]")
	private String email;
	
	@JsonProperty("partes[0][funcao]")
	private String funcao;
	
	private String arquivo;
	
	private String mensagem;
	
	private Boolean rejeitavel;
	
	private Boolean lembrete_assinatura;
	
	private Boolean lembrete_vencimento;
	
	private String frequencia;
	
	private Integer dias_vencimento;
	
	private String data_vencimento;
	
	public DocumentRequest() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFuncao() {
		return funcao;
	}
	
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Boolean getRejeitavel() {
		return rejeitavel;
	}

	public void setRejeitavel(Boolean rejeitavel) {
		this.rejeitavel = rejeitavel;
	}

	public Boolean getLembrete_assinatura() {
		return lembrete_assinatura;
	}

	public void setLembrete_assinatura(Boolean lembrete_assinatura) {
		this.lembrete_assinatura = lembrete_assinatura;
	}

	public Boolean getLembrete_vencimento() {
		return lembrete_vencimento;
	}

	public void setLembrete_vencimento(Boolean lembrete_vencimento) {
		this.lembrete_vencimento = lembrete_vencimento;
	}

	public String getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(String frequencia) {
		this.frequencia = frequencia;
	}

	public Integer getDias_vencimento() {
		return dias_vencimento;
	}

	public void setDias_vencimento(Integer dias_vencimento) {
		this.dias_vencimento = dias_vencimento;
	}

	public String getData_vencimento() {
		return data_vencimento;
	}

	public void setData_vencimento(String data_vencimento) {
		this.data_vencimento = data_vencimento;
	}	
	
}