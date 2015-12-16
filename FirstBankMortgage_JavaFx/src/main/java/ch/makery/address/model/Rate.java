package ch.makery.address.model;

import org.apache.poi.ss.formula.functions.FinanceLib;

import base.RateDAL;
import domain.RateDomainModel;

public class Rate extends RateDomainModel {
	
	// Test function for the mortage
    // Returns 0.0 if they do not qualify for the mortage
	
	// NOTE PLEASE READ: I found it easier to place this method in the Mortgage Controller and have included it there
	// with proper functionality for the application. Please look there for how I properly implemented it.
	
    public double CalculateMortage(double income, double expenses, int creditScore, double houseCost, int Term) {
    	// Calculate interest rate from credit score and amount of payment terms
    	double interestRate = RateDAL.getRate(creditScore);
    	double amntTerms = Term * 12;
    	
    	// Calculate the PMT:
    	double PMT = FinanceLib.pmt(interestRate, amntTerms, houseCost, 0, false);
    	
    	// Calculate the maximums for mortage payments
    	double cap1 = income * .36;
    	double cap2 = (income + expenses*12)*.28;
    	
    	if(PMT <= cap1 && PMT <= cap2) {
    		return PMT;
    	} else {
    		return 0.0;
    	}
    	
    }
}
