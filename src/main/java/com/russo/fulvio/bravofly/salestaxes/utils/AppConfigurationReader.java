package com.russo.fulvio.bravofly.salestaxes.utils;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utility class to read the configuration parameters
 * @author fulvio russo
 *
 */
public class AppConfigurationReader {
	
	    private static final Logger LOGGER = LogManager.getLogger(AppConfigurationReader.class);

	    private static AppConfigurationReader instance;
	    
	    private Configurations configs = new Configurations();

	    /* the configuration in appConfiguration.properties file  */
	    private Configuration fileConfig;
	    
	    /*
	     * Constructor
	     */
	    private AppConfigurationReader(){
	    	try
	    	{
	    	  this.fileConfig = configs.properties(new File("appConfiguration.properties"));
	    	
	    	}catch (ConfigurationException ex)
	    	{
	    		LOGGER.fatal("Error configuring the Application, cause:", ex);	    		
	    	}
	    }

	    /**
	     * Return a configured instance of AppConfigurationReader
	     * */
	    public static AppConfigurationReader getInstance(){
	    	
	        if(instance == null){
	            instance = new AppConfigurationReader();
	        }
	        return instance;
	    }	    
	   
	    /* tax percentage applied at all goods, except books,food, and medical products */
	    public double getBasicSalesTaxPercentage(){	 
	    	return fileConfig.getDouble("basic.sales.tax.percentage",10.0);  	 
	    }
	    
	    /* tax applicable on all imported goods, with no exemptions */
	    public double getImportDutyTaxPercentage(){	 	
	    	return fileConfig.getDouble("import.duty.tax.percentage",5);  	 
	    }
	    
	    /* A csv String of goods excluded from basic sales tax */
	    public String getBasicSalesTaxExcludedGoods(){	 	
	    	return fileConfig.getString("basic.sales.tax.excluded.goods","books,food,medical");     	
	    }
	    
}
