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
		 * Constructor
	   */
	   public TaxCalculator() {
			
	   }	
	
	
	  /**
		 * Return the good price WITH the "basic sales tax"
		 * The returned value is rounded up to the nearest 0.05
		 * 
		 * @param goodPrice the price of the good
		 * @return the good price WITH the "basic sales tax"
	   */
	   public double getPriceWithBasicSalesTax(double goodPrice) {
		
	    	double basicSalesTaxPercentage = APPLICATION_CONFIG_READER.getBasicSalesTaxPercentage();
	    	
	    	double priceWithTax = (goodPrice * basicSalesTaxPercentage)/100 + goodPrice;  
	    	
	    	BigDecimal bd = new BigDecimal(priceWithTax).setScale(2, RoundingMode.HALF_EVEN);
	    	return bd.doubleValue();   		
	   }   
 
    
    
	  /**
		* Return the good price WITH the "import duty tax"
		* The returned value is rounded up to the nearest 0.05
		* 
		* @param goodPrice the price of the good
		* @return the good price WITH the "import duty tax"
	  */
	  public double getPriceWithImportDutyTax(double goodPrice) {
	    	
	    	double basicSalesTaxPercentage = APPLICATION_CONFIG_READER.getImportDutyTaxPercentage();
	    	
	    	double priceWithTax = (goodPrice * basicSalesTaxPercentage)/100 + goodPrice;  
	    	
	    	BigDecimal bd = new BigDecimal(priceWithTax).setScale(2, RoundingMode.HALF_EVEN);
	    	return bd.doubleValue();   		
	  } 
    
    
	  /**
		 * Return only the "basic sales tax" value
		 * The returned value is rounded up to the nearest 0.05
		 * 
		 * @param goodPrice the price of the good
		 * @return the tax value to be applied at the input good
	   */
	  public double getBasicSalesTax(double goodPrice) {
		
	  	double basicSalesTaxPercentage = APPLICATION_CONFIG_READER.getBasicSalesTaxPercentage(); 
	  	double basicSalesTax = (goodPrice * basicSalesTaxPercentage)/100;  
	  	
	  	return NumberFormatterUtils.formaDouble(2,basicSalesTax);  	
	  	
	  }
  
  
	  /**
	 	 * Return the "import duty tax" value
	 	 * 
	 	 * 
	 	 * @param goodPrice the price of the good
	 	 * @return the "import duty tax" value to be applied
	  */
	  public double getImportDutyTax(double goodPrice) {
	 	
	     	double basicSalesTaxPercentage = APPLICATION_CONFIG_READER.getImportDutyTaxPercentage();
	      	double importDutyTax = (goodPrice * basicSalesTaxPercentage)/100.0;   
	     	
	     	return NumberFormatterUtils.formaDouble(2,importDutyTax);      			
	 } 

}
