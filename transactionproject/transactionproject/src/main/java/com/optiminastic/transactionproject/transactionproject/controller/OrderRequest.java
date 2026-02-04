package com.optiminastic.transactionproject.transactionproject.controller;

import java.math.BigDecimal;

public class OrderRequest {

	private BigDecimal balanceAmount;

	public BigDecimal getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
}
