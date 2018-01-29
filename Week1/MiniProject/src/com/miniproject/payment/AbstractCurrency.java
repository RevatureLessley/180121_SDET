package com.miniproject.payment;

import java.io.Serializable;

/*
 * Abstract currency base class to use to base other currency classes
 */
public abstract class AbstractCurrency implements Serializable {
	
	private static final long serialVersionUID = -5154659114273975069L;
	public double currency;
	
	public void setCurrency(double inCurrency) {
		currency = inCurrency;
	}
	
	public abstract double getCurrency();
	public abstract void increaseCurrency(double inCurr);
	public abstract double decreaseCurrency(double inCurr);
}
