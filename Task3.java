package tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Task3 extends CommonMethods{
	@Test(dataProvider = "fetchData")
	public void runTest(String username,String Password,String filterBy,String browser) throws Exception {
		WebDriver driver=getDriver(browser);
		login(driver,username,Password);
		Double expectedPrice = 9.99;
		String expectedProductName=null;
		boolean productFound=false;
		Map<String, Double> productsWithPrice = new HashMap<String, Double>();
		List<WebElement> products=driver.findElements(By.xpath("//div[@class='inventory_item']"));

		for(int i=0;i<products.size();i++) {
			String productName = products.get(i).findElement(By.xpath(".//div[@class='inventory_item_name ']")).getText().trim();
			Double productPrice = Double.parseDouble(products.get(i)
					.findElement(By.xpath(".//div[@class='inventory_item_price']"))
					.getText()
					.trim()
					.replace("$", ""));
			
			productsWithPrice.put(productName, productPrice);
//			if(Double.compare(expectedPrice, productPrice)==0) {
//				productFound=true;
//				expectedProductName = productName;
//				System.out.println(expectedProductName);
//				
//			}
		}
		for (Map.Entry<String, Double> entry : productsWithPrice.entrySet()) {
			if(Double.compare(expectedPrice, entry.getValue())==0) {
				expectedProductName = entry.getKey();
				productFound=true;
				System.out.println("Expected Product Name: " + expectedProductName);
				break;
			} 			
		}
		
		Assert.assertTrue(productFound);
	}

}
