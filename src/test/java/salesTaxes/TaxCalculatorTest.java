package salesTaxes;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.russo.fulvio.bravofly.salestaxes.TaxCalculator;
import com.russo.fulvio.bravofly.salestaxes.utils.AppConfigurationReader;
import com.russo.fulvio.bravofly.salestaxes.utils.NumberFormatterUtils;


@RunWith(Parameterized.class)
public class TaxCalculatorTest {
	
	private static final AppConfigurationReader APPLICATION_CONFIG_READER = AppConfigurationReader.getInstance();
	
	/* the good name (eg: music CD, imported chocolate, ...) */
	private String goodName;
	
	/* good price without taxes */
	private String netPrice;
	
	/* the type of the good (ie: book, medical...) */
	private String goodType;
	
	/* good price with taxes */
	private String taxedPrice;
	
	
	/* test parameters provider input: <netPrice>, <good-type>, <taxedType> */
	@Parameterized.Parameters
	public static Collection<String[]> getParam() {
		return Arrays.asList(new String[][]{ 
			{"book","12.49", "food","12.49"},
			{"imported chocolate","10.00", "food","10.50"},
			{"chocolate","0.85", "food","0.85"}	
		
						
		});
	}
	
	/**
	 * Constructor
	 * @param netPrice good price without tax
	 * @param taxedPrice good price with tax
	 */
	public TaxCalculatorTest(String goodName,String netPrice, String goodType, String taxedPrice) {
		this.goodName = goodName;
		this.netPrice = netPrice;
		this.goodType = goodType;
		this.taxedPrice = taxedPrice;
	}


	@Test
	public void testPriceWithBasicSalesTax() {
		
		TaxCalculator taxCalculator = new TaxCalculator();
		
		double importDutyTax = 0.0;
        double basicSalesTax = 0.0;
		
        /* check if apply 'basic tax' */
		if(! StringUtils.containsIgnoreCase(APPLICATION_CONFIG_READER.getBasicSalesTaxExcludedGoods(), goodType)) {	    	            	
            basicSalesTax = taxCalculator.getBasicSalesTax(Double.parseDouble(netPrice));             
 	    } 		
		
		assertNotNull(basicSalesTax);
		
		/* check if apply 'imported duty tax' */
        if(StringUtils.containsIgnoreCase(goodName, "imported")) {	    	            	
	       	importDutyTax = taxCalculator.getImportDutyTax(Double.parseDouble(netPrice)); 	       
        }
		
		double goodPriceWithTax = importDutyTax + basicSalesTax + Double.parseDouble(netPrice);		
		
		
		assertTrue(Double.parseDouble(taxedPrice)==goodPriceWithTax);	
	}	
  		
} 


