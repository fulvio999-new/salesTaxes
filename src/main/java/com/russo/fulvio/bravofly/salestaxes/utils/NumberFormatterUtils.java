
package com.russo.fulvio.bravofly.salestaxes.utils;

import java.math.BigDecimal;

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
	public static double formaDouble(int scale, double valueToFormat) {
		
		BigDecimal bd = BigDecimal.valueOf(valueToFormat);     	
	 	bd = bd.setScale(scale, BigDecimal.ROUND_HALF_EVEN);
	 	
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

}
