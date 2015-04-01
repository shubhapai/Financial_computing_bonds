package edu.nyu.cims.compfin14.hw2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import edu.nyu.cims.compfin14.hw2.YieldCurve;
import java.util.LinkedList;

/**
 *
 * @author Shubh
 */
public class Test {

	public static void main(String[] args) {
        
          
          /**
           * Build the new yield Curve Object for this Problem in Homework
           * 
           */                                 
           ArrayList<PointOnCurve> YC1 = new ArrayList<>();
           YC1.add(new PointOnCurve(1,0.02));
           YC1.add(new PointOnCurve(3,0.03));
           YC1.add(new PointOnCurve(2,0.025));
 
           YieldCurve ycm1= new YieldCurve(YC1);
			   
            System.out.println("--------------------------------------"); 
	        System.out.println("Printing Yield Curve for this Homework");
            System.out.println("--------------------------------------");
            Iterator<PointOnCurve> It2 = ycm1.getIterator();
	        while (It2.hasNext()){
			Object O1 =It2.next();
			System.out.println( O1.toString());
		}
       
           /**
           * Build Map of cash flow for th given bond in question 3
           * 
           */             
      
			TreeMap<Double, Double> CashFlow = new TreeMap<Double, Double>();
            CashFlow.put(1.0, 25.0);
            CashFlow.put(2.0, 25.0);
            CashFlow.put(3.0, 525.0);
           
            
           /**
           * Build the bonds given in the Homework
           * 
           */ 
            
            LinkedList<Bond> Newbonds;
            Newbonds = new LinkedList<Bond>();
            Newbonds.add(new ZeroCouponBond(95,0.5,100));
            Newbonds.add(new ZeroCouponBond(895,1,1000));
            Newbonds.add(new CouponBearingBond(0,3,500,5,CashFlow));
            
            
            ArrayList<PointOnCurve> YC = new ArrayList<>();  
            Bond NewBonds = new Bond(Newbonds);    
            Iterator<Bond> listitr = NewBonds.getIterator();
	        while(listitr.hasNext())
           {
            Object abc = listitr.next();
            if (abc instanceof ZeroCouponBond) // If its Zero Coupon Build the yield curve
            {
              YC.add(new PointOnCurve(((Bond) abc).getMaturity() , ((Bond) abc).getYTM(((Bond) abc), ((Bond) abc).getPrice())));
            }
            else if (abc instanceof CouponBearingBond) // for coupon bearing bond given in hw calculate the price and YTM 
            {   
                System.out.println("price and ytm of the 5% Coupon bearing bond with 500$ Facevalue ( which pays annually for 3 years) using the yield curve give in HW2");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
                double price  = ((CouponBearingBond) abc).getPrice(ycm1,((CouponBearingBond) abc));
                System.out.println("Price of coupon bearing bond:"+ price);
                System.out.println("YTM of coupon bearing bond:"+((CouponBearingBond) abc).getYTM(((CouponBearingBond) abc),price));
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
            }
            
        }  
          
           YieldCurve ycm= new YieldCurve(YC);
           System.out.println("-----------------------------------------------------"); 
	       System.out.println("Printing Yield Curve generated from Zero Coupon Bonds");
           System.out.println("-----------------------------------------------------");
           Iterator<PointOnCurve> It1 = ycm.getIterator();
	    while (It1.hasNext()){
			Object O1 =It1.next();
			System.out.println(O1.toString());
		}
	  
          /**
           * After building the yieldCurve from Zero Coupon bonds. Printing the interest rate
           * for when time = 0.75
           */   
          
         try {
         System.out.println("--------------------------------------------------------");      

          System.out.println("InterestRate for time =0.75: " +ycm.getInterestRate(0.75));  

         }catch(IndexOutOfBoundsException e){
          System.out.println("Exception thrown:"+ e);
          System.out.println("Out of the block");
         }
        System.out.println("----------------------------------------------------------");       
        }   


}


