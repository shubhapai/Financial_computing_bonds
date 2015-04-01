/**
 * Defines a class Bond
 * Constructs a list of Bond objects some zero coupon bonds and some coupon bearing bonds
 * @author Shubh
 */

package edu.nyu.cims.compfin14.hw2;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Shubh
 */
public class Bond {
   	
    List<Bond> bonds; // List of Bonds
	private double P;// Price of the Bond
	private double M; // Maturity of the Bond
	private double FV; // FaceValue of the Bond
    private double R; // Rate of the Bond
    Map<Double, Double> CF = new TreeMap<Double, Double>(); // Map of Cash Flow
	
	
	
	public Bond (double Price, double Maturity,double FaceValue)
	{
		this.P = Price;
		this.M = Maturity;
		this.FV = FaceValue;
		
	}
        
            
	public Bond(LinkedList<Bond> bonds) {
	    this.bonds = bonds;
	    }
	
        public Iterator<Bond> getIterator(){
	        return bonds.iterator();
	    }
        
     /**
     * @return The price of the Bond
     */
	public double getPrice(){
		return P;
	}
     /**
     * @return The Maturity of the Bond.
     */	
	 public double getMaturity(){
		 return M;
	 }
     /**
     * @return The FaceValue of the Bond.
     */	 	 
	 public double getFaceValue(){
		 return FV;
	 }
	 
     /**
     * @return The Map of CashFLow for the Bond.
     */		
	 public Map<Double,Double> getCashFlow(){
		return CF;		 
	 }
          
         /** get YTM for a Bond given the price
         * 
         * @param bond
         * @param price
         * @return Yield to Maturity of the Bond
         * using formula ytm = -(1/t)*log(PV/FV)
         */ 

	    public double getYTM(Bond bond, double price) {
	    	//double coupon = bond.getCoupon();
	    	FV = bond.getFaceValue();
	    	P = bond.getPrice();
	    	M = bond.getMaturity();	
	    	return -((1/M) * Math.log(P/FV));
		}
        
         /** get price for a Bond from the given yieldCurve
         * 
         * @param bond
         * @param R from ycm
         * @return price of the Bond
         * using formula PV= FV*e^(r*t)
         */    
            
            public double getPrice(YieldCurve ycm, Bond bond) {
         	FV = bond.getFaceValue();
	    	P = bond.getPrice();
	    	M = bond.getMaturity();	
            R= ycm.getInterestRate(M);
                  
                return FV*Math.exp(-R*M);
             
               }

         /** get price for a Bond from the given ytm
         * 
         * @param bond
         * @param ytm
         * @return price of the Bond
         * using formula PV= FV*e^(ytm*t)
         */                  
            public double getPrice(Bond bond, double ytm) {
            FV = bond.getFaceValue();
	    	P = bond.getPrice();
	    	M = bond.getMaturity();	
            return FV*Math.exp(-ytm*M);
		
	       }

}
