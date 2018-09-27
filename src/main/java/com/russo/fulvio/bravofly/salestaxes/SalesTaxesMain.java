package com.russo.fulvio.bravofly.salestaxes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.russo.fulvio.bravofly.salestaxes.model.PurchasedGood;
import com.russo.fulvio.bravofly.salestaxes.utils.AppConfigurationReader;
import com.russo.fulvio.bravofly.salestaxes.utils.NumberFormatterUtils;

/**
 * Entry point for SalesTaxes Application
 * 
 * @author fulvio russo
 *
 */
public class SalesTaxesMain {
	
	private static final Logger LOGGER = LogManager.getLogger(SalesTaxesMain.class);	

	public static void main(String[] args) throws IOException {	
		
		  AppConfigurationReader APPLICATION_CONFIG_READER = AppConfigurationReader.getInstance();
		
		  /* store the purchased Goods */
		  ArrayList<PurchasedGood> purchasedGoodsList = new ArrayList<PurchasedGood>();		  
		  TaxCalculator taxCalculator = new TaxCalculator();
		  
		  int choice = -1;
		  
		  printMenu();
		    	      
		  while(choice !=0) {	    		  
	    	 
	    	 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	         
	    
	    	 choice = Integer.parseInt(br.readLine().trim().toLowerCase());
	    	 
	         switch (choice) {
	             case 1:
	            	 try {
		            	 System.out.print("Puchased Good > ");
		    	         String purchasedGood = br.readLine().trim().toLowerCase();
		    	         
		    	         System.out.print("Good category > ");
		    	         String goodCategory = br.readLine().trim().toLowerCase();
		    	         
		    	         //if(! StringUtils.containsIgnoreCase(APPLICATION_CONFIG_READER.getBasicSalesTaxExcludedGoods(), goodCategory))
		    	        //	 System.out.print("Good category > ");
		    	         
		    	         System.out.print("Good price > ");   	         
	    	             double goodNetPrice = Double.parseDouble(br.readLine().trim().toLowerCase());   	             
	    	             
	    	             double importDutyTax = 0.0;
	    	             double basicSalesTax = 0.0; 
	    	             
	    	             /* check if apply imported duty tax */
	    	             if(StringUtils.containsIgnoreCase(purchasedGood, "imported")) {
	    	            	System.out.println("imported product, apply duty tax");
	    	            	importDutyTax = taxCalculator.getImportDutyTax(goodNetPrice); 
	    	             }
	    	            
	    	             /* check if apply Basic sales tax depending on the good category  */
	    	             if(! StringUtils.containsIgnoreCase(APPLICATION_CONFIG_READER.getBasicSalesTaxExcludedGoods(), goodCategory)) {
	    	            	 System.out.println("Good category not in exclusion list: apply Basic salex tax");
	    	                 basicSalesTax = taxCalculator.getBasicSalesTax(goodNetPrice);
	            	     }  	             
	            	
	    	             //System.out.println("Basic Tax:"+basicSalesTax);
	    	             //System.out.println("Duty Tax:"+importDutyTax);
	    	             
	    	             double totalAmountTax = importDutyTax + basicSalesTax;	    
	    	             double priceWithTaxes = goodNetPrice + totalAmountTax;	  	
	    	             
	    	             PurchasedGood good = new PurchasedGood(purchasedGood, goodNetPrice, totalAmountTax, NumberFormatterUtils.formaDouble(2,priceWithTaxes));
	    			     purchasedGoodsList.add(good);
	    	             
	    	         }catch (NumberFormatException e) {	
	    	        	System.out.print("Error, Good price is a number, insert again Good price > ");	    	           
	 			     } 
	    	         
	                 break;
	             case 2:	            	
	            	 printPurchaseList(purchasedGoodsList);
	            	 purchasedGoodsList.clear(); 
	                 break;
	             case 3:
	            	 printMenu();
	                 break;    
	             default:
	            	 System.out.print("Invalid value");
	         }       
	      } 
	      
	      System.out.println("BYE !!");
	}   
	
	
	private static void printPurchaseList(List<PurchasedGood> purchasedGoodsList) {
		
		double salesTaxesTotal = 0.0;
		double purchaseTotal = 0.0;
				
		for(PurchasedGood good: purchasedGoodsList) {
			System.out.println(good.getGoodType()+": "+good.getPriceWithTaxes());
			
			purchaseTotal += good.getPriceWithTaxes();
			salesTaxesTotal += good.getGoodTotalTax();
		}		
		
		System.out.println("Sales Taxes:"+NumberFormatterUtils.formaDouble(2, salesTaxesTotal));
		System.out.println("Total: "+NumberFormatterUtils.formaDouble(2, purchaseTotal));
	}	
	
	
	private static void printMenu() {	
		
		 System.out.println("-------Menu-----\n");
	   	 System.out.println("0 Exit and clean\n");
	   	 System.out.println("1 Purchase Good\n");
	   	 System.out.println("2 Print receipt\n");
	   	 System.out.println("3 Print menu\n"); 
	   	 System.out.println("---------------\n"); 
	}

}
