/**
 * Defines a class PointOnCurve
 * Constructs a list of PointOnCurve objects for a yieldCurve object
 */

package edu.nyu.cims.compfin14.hw2;


/**
 * 
 * 
 * @author Shubh
 * Class that create Points objects on the curve
 */
public class PointOnCurve implements Comparable<Object>{
	double time; // time
	double rate; // corresponding rate
	
	public PointOnCurve() {
		time =0;
		rate =0;
	}
	
	
	public PointOnCurve(double time, double rate) {
		this.time = time;
		this.rate = rate;
	} 
	
	/*
	 * toString method to print time and rate
	 * @see java.lang.Object#toString()
	 */

	public String toString() {
	    return time +","+ rate;
	}
     /**
     * @return time of a given point on yield curve
     */	

	public double getTime() {
		return time;
	}
     /**
     * @return rate of a given point on yield curve
     */	

	public double getRate() {
		return rate;
	}
  
     /**
     * @Compare method to sort the yield curve based on time
     * @param p1
     * @return compared value greater or lesser.
     */	
    public int compareTo(Object p1) {
        PointOnCurve other = (PointOnCurve)p1;
        if(getTime() > other.getTime())
                {
                return 1;
                }
        else 
            return -1;
                
    }
}
