package edu.nyu.cims.compfin14.hw2;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author Shubh
 * Class definition to define Coupon bearing Bond objects and 
 * Construct a Coupon bearing Bond given Price , Maturity , Facevalue , Coupon ,Cash flow
 */
public class CouponBearingBond extends Bond{
    private double P; // Price of the Bond
	private double R; // Rate of the Bond
   	double C; // Coupon of the bound. For definitions of other vars please refer super Bond.
        TreeMap<Double, Double> CF = new TreeMap<Double, Double>();
        private double YTM; // ytm for coupon bond
        private double Price; // price sent from test class
        private double precisionvalue=0.01; //  precision required for YTM calculation
        private int iterations =1000; // iterations required for YTM calculation
        

    public CouponBearingBond(double Price, double Maturity, double FaceValue,double Coupon, TreeMap<Double, Double> map) {
        super(Price, Maturity, FaceValue);
        this.C= Coupon;
        this.CF = map;
    }
	
		public double getCoupon() {
			return C;
		}
                
    
     /**
     * @return The Map of CashFLow for the Bond.
     */		
	 public Map<Double,Double> getCashFlow(){
		return CF;		 
	 }
     
               /**
                * 
                * @param ycm
                * @param bond
                * @return price of the bond 
                * using formula PV = C*E^(-R0*i0) +C*E^(-R1*i1)+............+(C+PAR)*E^(-Rn*in)
                */ 
                
               @SuppressWarnings("rawtypes")
			public double getPrice(YieldCurve ycm, CouponBearingBond bond) {
         	    P= 0; // Initializing value of P 
                Map CF = bond.getCashFlow(); // CashFlow Map
                Set set1 = CF.entrySet();
                Iterator i1 = set1.iterator();
                while(i1.hasNext()) {
                Map.Entry me1 = (Map.Entry)i1.next();    
                double i= Double.parseDouble(me1.getKey().toString());
                // get interest from the yielcurve for the given year
                R = ycm.getInterestRate(i);
                double CashFlow  = (Double.parseDouble(me1.getValue().toString()));
                 P= P + CashFlow*Math.exp(-R*i);
                }
                return P;
                
               }
               
               /**
                * 
                * @param bond
                * @param price
                * @return YTM for the given bond
                */
              
               	public double getYTM(CouponBearingBond bond, double price){
                   
                 Price = price;   
                 YTM= evaluateYTM(0,1,bond,Price); // setting the initial guess values of ytm to 0 and 1 (0 and 100%)
                 return YTM;
		       }
            
              // To get YTM using trail and error / interval bisection method by taking two initial values X0 and X1  
               public double evaluateYTM(double lower , double higher , CouponBearingBond bond , double price)
              //lower and higher are the initial estimates//
               {
                  int i=0; 
                  double fa; //fa and fb function evaluation of initial ‘guess’ values.//
                  double fb;
                  double fc; //fc is the function evaluation , f(x)//
                  double midvalue = 0;
                  fa=ComputeFunction(lower , bond , price);
                  fb=ComputeFunction(higher, bond, price);
                  
                 //Check to see if we have the root within the range bounds//
                  if (fa*fb>0)
                  {
                      //If fa∗fb>0 then both are either positive//
                      //or negative and don’t bracket zero.//
                      midvalue = 0;  // terminate program
                  }
                  else 
                  do
                  {
                      // relative precision//
                      midvalue = lower + 0.5*(higher-lower);  // implementing (x1+x2)/2 to get the mid value 
                                                              // between x1 and x2
                      fc = ComputeFunction(midvalue , bond , price); // Compute f(x) for mid value
                      if (fa*fc<0)
                      {
                          higher = midvalue;  
                      }
                      else
                      if (fa*fc>0)
                      {
                          lower = midvalue;
                      } 
                    i++;
                   } while((Math.abs(fc))> precisionvalue && i< iterations);   
                    //loops until desired number of iterations or precision is reached//
                     return midvalue;
               }
               
               
               /**
                * 
                * @param rate
                * @param bond
                * @param price
                * @return the function value F(R) =(C*e^-(R*i1) + C*e^-(R*i2)+......+C*e^(-R*i(n-1))+(C+FV)e^(-R*in)) - price = 0
                * where i1 , i2, i(n-1) , in are the time values from the Map
                */
               @SuppressWarnings("rawtypes")
			public double ComputeFunction (double rate , CouponBearingBond bond, double price)
               {
               
                P=0; // Initializing P value
                Price = price; // price of the bond
                R = rate; // Rate of the Bond
                
                Map CF = bond.getCashFlow();
                Set set1 = CF.entrySet();
                Iterator<?> i1 = set1.iterator();
                while(i1.hasNext()) {
                Map.Entry me1 = (Map.Entry)i1.next();   
                double i= Double.parseDouble(me1.getKey().toString());
                double CashFlow  = (Double.parseDouble(me1.getValue().toString()));
                 P= P + CashFlow*Math.exp(-R*i);
                }
                return P-price; 
               }
               
         /**
         * 
         * @param bond
         * @param ytm
         * @return price of the Bond
         * using formula PV = C*E^(-R0*i0) +C*E^(-R1*i1)+............+(C+PAR)*E^(-Rn*in)
         */                  
              @SuppressWarnings("rawtypes")
			public double getPrice(CouponBearingBond bond, double ytm) {
                P= 0; // Initializing value of P
	 
                Map CF = bond.getCashFlow();
                Set set1 = CF.entrySet();
                Iterator<?> i1 = set1.iterator();
                while(i1.hasNext()) {
                Map.Entry me1 = (Map.Entry)i1.next();    
                double i= Double.parseDouble(me1.getKey().toString());
                double CashFlow  = (Double.parseDouble(me1.getValue().toString()));
                P= P + CashFlow*Math.exp(-ytm*i);
	       }
                return P;
              }
  } 
              
              

            

