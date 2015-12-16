package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.RateDomainModel;
import util.HibernateUtil;

public class RateDAL {


	public static double getRate(int GivenCreditScore) {
		//FinalExam - please implement		
		// Figure out which row makes sense- return back the 
		// right interest rate from the table based on the given credit score
		
		// Starts a new session, transaction, and initializes their rate
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		double theirRate = 0;
		
		try{
			// Begins the transaction
			session.beginTransaction();
			// Generates the SQL code to be used
			String hql = "SELECT INTERESTRATE FROM TBLRATE WHERE MINCREDIT SCORE <= " + Integer.toString(GivenCreditScore);
			Query interestRate = session.createSQLQuery(hql);
			ArrayList<Double> rates = new ArrayList<Double>();
			
			// Passes the result of the query back to the rates variable
			rates = (ArrayList<Double>) interestRate.list();
			
			// Sorts the rates so the lowest interest rate that applies to them is used
			Collections.sort(rates);
			
			// Sets their interest rate to the lowest one that they qualify for
			theirRate = rates.get(0);
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		//FinalExam - obviously change the return value
		
		return theirRate;
	}

}