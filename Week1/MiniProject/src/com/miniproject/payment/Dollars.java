package com.miniproject.payment;

import org.apache.log4j.Logger;

/*
 * Dollars extends AbstractCurrency for a currency based off of US (American) dollars
 */
public class Dollars extends AbstractCurrency{

	final static Logger logger = Logger.getLogger(Dollars.class);
	private static final long serialVersionUID = -8406410769549441944L;
	private double currency = 0.0f;
	
	public Dollars() {

	}
	
	public Dollars(double indouble) {
		this.currency = indouble;
	}
	
	public double getCurrency() {
		return currency;
	}

	public void setCurrency(double currency) {
		this.currency = currency;
	}

	public void increaseCurrency(double inCurr) {
		this.currency += inCurr;
	}
	
	@Override
	public double decreaseCurrency(double inCurr) {
		double decAmnt = inCurr;
		if(inCurr > this.currency) {
			decAmnt = this.currency;
			this.currency = 0;
		} else {
			this.currency -= inCurr;
		}
		
		logger.info(String.format("%.2f |amount removed", decAmnt));
		return decAmnt;
	}
}
