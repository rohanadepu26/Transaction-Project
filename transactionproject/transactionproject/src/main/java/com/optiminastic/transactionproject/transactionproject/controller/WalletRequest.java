package com.optiminastic.transactionproject.transactionproject.controller;

import java.math.BigDecimal;

public class WalletRequest {

	private Long clientId;
    private BigDecimal balanceAmount;
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public BigDecimal getBalanceAmount() {
		return balanceAmount;
	}
	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
    
}
