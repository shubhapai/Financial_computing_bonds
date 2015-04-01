package edu.nyu.cims.compfin14.hw2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;
/**
 *
 * @author Shubh
 * Class to define a Yield Curve object
 * Constructs a yield curve object with the arraylist of PointOnCurve
  
 * 
 */
public class YieldCurve {
        
    	private ArrayList<PointOnCurve> yieldCurve; // Defining Arraylist for yieldCurve
    	private double rate=0; // Interest rate for continuous compounding
    	private double Dfr = 0; // Discount factor
    	private double Fwdrate = 0; // Forward rate
        private double x1 = 0 ; // left neighbour (time)
        private double x2 = 0; // right neighbour (time)
        private double y1 = 0; // left neighbour (rate)
        private double y2 = 0; // right neighbour (rate)
        
    	
		public YieldCurve(ArrayList<PointOnCurve> yieldCurve){    	
        this.yieldCurve = yieldCurve;
        Collections.sort(yieldCurve);
  
        }
    	
     /**
     * @return size of the yield curve
     */	
    	public int getSize(){
    		return yieldCurve.size();
    		
    	}
     /**
     * @return Iterator of the  yield curve
     */	    
        public Iterator<PointOnCurve> getIterator(){
            return yieldCurve.iterator();
        }
        	
         /** get  rate for a specific time
         * If the given time is not found on the yield curve then interpolate it from neighbors
         * @param time
         * @return  rate
         */
    	public double getInterestRate(double time) {
    		int i;
    		int size = yieldCurve.size();
    		
    		for (i = 0; i < size; i++) {
    		
    		if (time == yieldCurve.get(i).getTime())
    		{
    			rate= yieldCurve.get(i).getRate();
                }
                else if (time > yieldCurve.get(i).getTime() && time < yieldCurve.get(i+1).getTime())    
                {    
                       y1=  yieldCurve.get(i).getRate();
                       y2 = yieldCurve.get(i+1).getRate();                       
                       x1 = yieldCurve.get(i).getTime();
                       x2 = yieldCurve.get(i+1).getTime();
                       rate = interpolate(x1,y1,x2,y2,time);
                }
    	      
               }
                
                return rate;
         }
         /** get Forward rate between two specific times t0 and t1 in continuous Compounding
         * get corresponding r0 and r1 and compute the forward rate
         * @param t0
         * @param t1
         * @return Continuous Forward rate
         */          
   
        
         public double getForwardRate(double t0, double t1){
            double deltaT = t1 -t0; 
     		int i;
     		double r1 = 0; // rate from the yield curve for a give time t1
     		double r0 = 0; // rate from the yield curve for a give time t0
     		int size = yieldCurve.size();
     		
     		for (i = 0; i < size ; i++) {
     			if (t0 == yieldCurve.get(i).getTime())
     			{
     			     r0 = yieldCurve.get(i).getRate();
     			}
     			if (t1 == yieldCurve.get(i).getTime())
     			{
                     r1 = yieldCurve.get(i).getRate();
                }
     	   }	
                
     			
     		Fwdrate= (r1*t1) /(r0*t0)*(deltaT);
     			 
    	  return Fwdrate;
        	 
         }
         
         /** get Discount Factor for a specific time in continuous Compounding
         * For the given time t
         * @param t
         * @return continuous discount factor
         */         
         public double getDiscountFactor(double t){
      		int i;
      		double r = 0; // rate from the yield curve for a give time
      		int size = yieldCurve.size();
      		
     		for (i = 0; i < size; i++) {
     			if (t == yieldCurve.get(i).getTime())
     			{
     			     r = yieldCurve.get(i).getRate();
     			     Dfr = Math.exp(r*t); 
     			}
     			
         }
    		return Dfr;

        }
     
         /** linear interpolation between two sets of values
         *  
         * @param x1
         * @param y1
         * @param x2
         * @param y2
         * @param x, the value to get the y of.
         * @return interpolated value of y
         */
        static public double interpolate(final double x1, final double y1, final double x2, final double y2, final double x) {
                return( (y2-y1) / (x2-x1 ) * (x-x1) + y1);
        }
}
