
package com.russo.fulvio.bravofly.salestaxes.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Utility class with methods to format numbers
 * 
 * @author russo fulvio
 *
 */
public class NumberFormatterUtils {

	/**
	 * 
	 * @param scale the scale to apply
	 * @param valueToFormat the number to format
	 * @return a double value formatted with the provide scale
	 */
	public static double formatDouble(int scale, double valueToFormat) {
		
		BigDecimal bd = BigDecimal.valueOf(valueToFormat);    	
				
	 	bd.round(new MathContext(scale, RoundingMode.HALF_UP));	 	
	 
	 	return bd.doubleValue();
	 	
	}
	
	/**
	 * 
	 * @param round the value to round
	 * @return
	 */
	public static int roundDouble(double round) { 
		
		int toRound = (int)Math.floor(round);
	    if (toRound % 5 == 0) {
			return toRound;
		}
		return toRound + ( 5 - (toRound % 5));		
	}
	
	/**
	 * 
	 * @param valueToFormat value to format
	 * @return a value with two decimal digits
	 */
	public static String formatDecimal(double valueToFormat) {		
		
	   DecimalFormat df = new DecimalFormat("#.##");
   
       return df.format(valueToFormat);
	}

}
