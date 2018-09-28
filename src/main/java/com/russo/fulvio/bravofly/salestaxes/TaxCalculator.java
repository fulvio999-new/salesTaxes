package com.russo.fulvio.bravofly.salestaxes;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.russo.fulvio.bravofly.salestaxes.utils.AppConfigurationReader;
import com.russo.fulvio.bravofly.salestaxes.utils.NumberFormatterUtils;

/**
 * Tax calculation methods for good(s)
 * @author fulvio
 *
 */
public class TaxCalculator {
	
	   private static final AppConfigurationReader APPLICATION_CONFIG_READER = AppConfigurationReader.getInstance();
	
	   /**
		 * Default Constructor
	   */
	   public TaxCalculator() {
			
	   }	
	
	

    
    
	  /**
		* Return the "basic sales tax" value to apply at the provided good price		
		* 
		* @param goodPrice the price of the good
		* @return the good price WITH the "import duty tax"
	  */
//	  public double getPriceWithImportDutyTax(double goodPrice) {
//	    	
//	    	double basicSalesTaxPercentage = APPLICATION_CONFIG_READER.getImportDutyTaxPercentage();	    	
//	    	double priceWithTax = (goodPrice * basicSalesTaxPercentage)/100 + goodPrice;  
//	    	
//	    	BigDecimal bd = new BigDecimal(priceWithTax).setScale(2, RoundingMode.HALF_EVEN);
//	    	return bd.doubleValue();   		
//	  } 
    
    
	  /**
		 * Return the "basic sales tax" value to apply at the provided good price
		 * 
		 * 
		 * @param goodPrice the price of the good
		 * @return the tax value to be applied at the input good
	   */
	  public double getBasicSalesTax(double goodPrice) {
		
	  	 double basicSalesTaxPercentage = APPLICATION_CONFIG_READER.getBasicSalesTaxPercentage(); 
	  	 double basicSalesTax = (goodPrice * basicSalesTaxPercentage)/100.0;    
	    
	  	 return NumberFormatterUtils.formatDouble(2,basicSalesTax);  
	  }
  
  
	  /**
	 	* Return the "import duty tax" value
	 	* 
	 	* @param goodPrice the price of the good
	 	* @return the "import duty tax" value to be applied
	  */
	  public double getImportDutyTax(double goodPrice) {
	 	
	     double basicSalesTaxPercentage = APPLICATION_CONFIG_READER.getImportDutyTaxPercentage();
	     double importDutyTax = (goodPrice * basicSalesTaxPercentage)/100.0;  	     
	   
	     return NumberFormatterUtils.formatDouble(2,importDutyTax);      			
	 } 

}
