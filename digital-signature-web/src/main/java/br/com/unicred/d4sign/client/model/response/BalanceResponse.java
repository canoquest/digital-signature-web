package br.com.unicred.d4sign.client.model.response;

public class BalanceResponse {
	
	private String credit;
	
	private Integer sent;
	
	private String used_balance;

	public BalanceResponse() {
		super();
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public Integer getSent() {
		return sent;
	}

	public void setSent(Integer sent) {
		this.sent = sent;
	}

	public String getUsed_balance() {
		return used_balance;
	}

	public void setUsed_balance(String used_balance) {
		this.used_balance = used_balance;
	}	

}