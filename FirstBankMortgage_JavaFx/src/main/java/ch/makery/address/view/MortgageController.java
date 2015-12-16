package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.UUID;

import org.apache.poi.ss.formula.functions.FinanceLib;

import base.RateDAL;
import ch.makery.address.MainApp;
import ch.makery.address.model.Rate;


public class MortgageController {
	
	@FXML
	TextField txtIncome;
	
	@FXML
	TextField txtExpenses;
	
	@FXML
	TextField txtCreditScore;
	
	@FXML
	TextField txtHouseCost;
	
	@FXML
	Label mortagePayment;
	
	@FXML
	RadioMenuItem FifteenYear;
	
	@FXML
	RadioMenuItem ThirtyYearTerm;
	
    // Reference to the main application.
    private MainApp mainApp;

    @FXML
    public void CalculateMortage() {
    	double income = Double.parseDouble(txtIncome.getText());
    	double expenses = Double.parseDouble(txtExpenses.getText());
    	int creditScore = Integer.parseInt(txtCreditScore.getText());
    	double houseCost = Double.parseDouble(txtHouseCost.getText());
    	int Term;
    	
    	if (FifteenYear.isSelected()) {
    		Term = 15;
    	} else if (ThirtyYearTerm.isSelected()) {
    		Term = 30;
    	} else {
    		// Set a default value for the term variable
    		Term = 15;
   	}
    	
    	// Calculate interest rate from credit score and amount of payment terms
    	double interestRate = RateDAL.getRate(creditScore);
    	double amntTerms = Term * 12;
    	
    	// Calculate the PMT:
    	double PMT = FinanceLib.pmt(interestRate, amntTerms, houseCost, 0, false);
    	
    	// Calculate the maximums for mortage payments
    	double cap1 = income * .36;
    	double cap2 = (income + expenses*12)*.28;
    	
    	if(PMT <= cap1 && PMT <= cap2) {
    		mortagePayment.setText(Double.toString(PMT));
    	} else {
    		mortagePayment.setText("House Payment Too High");
    	}
    	
    }
    
    // Test function for the mortage
    // Returns 0.0 if they do not qualify for the mortage
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
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public MortgageController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
   
}