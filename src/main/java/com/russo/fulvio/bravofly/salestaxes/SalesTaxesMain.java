package com.russo.fulvio.bravofly.salestaxes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
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
		  
		  /* utility to calculate taxes on goods */
		  TaxCalculator taxCalculator = new TaxCalculator();
		  
		  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		  
		  int choice = -1;
		  
		  printMenu();
		    	      
		  while(choice !=0) {			  
			 
			 System.out.print("Enter your choice number:");
	    	 choice = Integer.parseInt(br.readLine().trim().toLowerCase());
	    	 
	         switch (choice) {
	             case 1:
	            	 try {
		            	 System.out.print("Good name > ");
		    	         String purchasedGood = br.readLine().trim().toLowerCase();
		    	         
		    	         System.out.print("Good category (book, food, medical, other) > ");
		    	         String goodCategory = br.readLine().trim().toLowerCase();
		    	         
		    	         System.out.print("Good price > ");   	         
	    	             double goodNetPrice = Double.parseDouble(br.readLine().trim().toLowerCase());   	             
	    	             
	    	             double importDutyTax = 0.0;
	    	             double basicSalesTax = 0.0; 
	    	             
	    	             /* apply 'imported duty tax' if product is an imported one */
	    	             if(StringUtils.containsIgnoreCase(purchasedGood, "imported")) {	    	            	
	    	            	importDutyTax = taxCalculator.getImportDutyTax(goodNetPrice); 
	    	            	//System.out.println("Imported product, apply duty tax of: "+importDutyTax);
	    	             }
	    	            
	    	             /* if good category is NOT in the category whitelist apply basic sales tax   */
	    	             if(! StringUtils.containsIgnoreCase(APPLICATION_CONFIG_READER.getBasicSalesTaxExcludedGoods(), goodCategory)) {	    	            	
	    	                 basicSalesTax = taxCalculator.getBasicSalesTax(goodNetPrice);
	    	                 //System.out.println("Good category not in exclusion list: apply Basic sales tax of: "+basicSalesTax);
	            	     }  	             
	    	             
	    	             double totalAmountTax = importDutyTax + basicSalesTax;	    
	    	             double priceWithTaxes = goodNetPrice + totalAmountTax;	 	    	             
	    	             
	    	             PurchasedGood good = new PurchasedGood(purchasedGood, goodNetPrice, totalAmountTax, priceWithTaxes);
	    			     purchasedGoodsList.add(good);
	    	             
	    	         }catch (NumberFormatException e) {	
	    	        	LOGGER.fatal("Error formatitng Good price, cause ",e);
	    	        	System.out.print("Error, Good price is a number, insert again Good price > ");	    	           
	 			     } 
	    	         
	                 break;
	             case 2:	            	
	            	 printReceipt(purchasedGoodsList);
	            	 purchasedGoodsList.clear(); 
	                 break;
	             case 3:
	            	 printMenu();
	                 break;    
	             default:
	            	 System.out.print("Invalid value");
	            	 printMenu();
	          }       
	      } 
	      
	      System.out.println("BYE !");
	}   
	
	/**
	 * Print the Receipt for the current purchase list if shopping list is not empty
	 * @param purchasedGoodsList the purchase list to print
	 */
	private static void printReceipt(List<PurchasedGood> purchasedGoodsList) {
		
		if(CollectionUtils.isEmpty(purchasedGoodsList)) {
			System.out.println("Shopping list is empty can't print receipt !");
		}else {
		
			double salesTaxesTotal = 0.0;
			double purchaseTotal = 0.0;
			
			System.out.println("\n");
					
			for(PurchasedGood good: purchasedGoodsList) {
				System.out.println(good.getGoodType()+": "+NumberFormatterUtils.formatDecimal(good.getPriceWithTaxes()));
				
				purchaseTotal += good.getPriceWithTaxes();
				salesTaxesTotal += good.getGoodTotalTax();
			}		
			
			System.out.println("Sales Taxes: "+NumberFormatterUtils.formatDecimal(salesTaxesTotal));
			System.out.println("Total: "+NumberFormatterUtils.formatDecimal(purchaseTotal));
		}
	}	
	
	/**
	 * Utility to print allowed user choices
	 */
	private static void printMenu() {	
		
		 System.out.print("-------Menu choices-----\n");
	   	 System.out.print("0 : Exit and clean\n");
	   	 System.out.print("1 : Purchase Good\n");
	   	 System.out.print("2 : Print receipt\n");
	   	 System.out.print("3 : Print menu\n"); 
	   	 System.out.print("-------------------------\n"); 
	}
}
