package salesTaxes;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.russo.fulvio.bravofly.salestaxes.TaxCalculator;



@RunWith(Parameterized.class)
public class TaxCalculatorTest {
	
	private double goodPrice;
	private double priceWithTax;
	
	/* parameters provider per i test cases: goodPrice,priceWithTax  */
	@Parameterized.Parameters
	public static Collection<Double[]> getParam() {
		return Arrays.asList(new Double[][]{ 
			{14.99, 16.49}
						
		});
	}
	
	public TaxCalculatorTest(double goodPrice, double priceWithTax) {
		this.goodPrice = goodPrice;
		this.priceWithTax = priceWithTax;
	}


	@Test
	public void testCalculatePriceWithBasicSalesTax() {
		
		TaxCalculator taxCalculator = new TaxCalculator();
		
		double taxedValue = taxCalculator.getBasicSalesTax(goodPrice);	
		
		assertTrue(priceWithTax==taxedValue);	
	}
	
	
	@Test
	public void testCalculatePriceImportDutyTax() {
		
		TaxCalculator taxCalculator = new TaxCalculator();
		
		double taxValue = taxCalculator.getBasicSalesTax(goodPrice);		
		
		assertTrue(10.50==taxValue);	
	}

}
