package capturasUrl;

import java.util.concurrent.TimeUnit;
import java.io.*;  import java.util.Scanner;  
import com.opencsv.CSVReader;  

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;


import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.NoSuchElementException;


import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class capturasClass {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub

		//setting the driver executable
		
		
		
		//edge driver descomentar para edge
		System.setProperty("webdriver.edge.driver", ".\\Driver\\msedgedriver.exe");
		EdgeDriver driver = new EdgeDriver();
		

		//Initiating your chromedriver descomentar para chrome
		//System.setProperty("webdriver.chrome.driver", ".\\Driver\\chromedriver.exe");
		//WebDriver driver=new ChromeDriver();
		
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);  
		//read file
		CSVReader reader = null;  
		try  
		{  
			//parsing a CSV file into CSVReader class constructor  
			reader = new CSVReader(new FileReader("C:\\projectScreenshots\\csv\\Libro1.csv"));  
			String [] nextLine;  
			//reads one line at a time  
			int i = 0;
			while ((nextLine = reader.readNext()) != null)  
			{  
				for(String token : nextLine)  
				{  
					driver.get(token);
					Thread.sleep(5000);
					driver.manage().window().maximize();
					
					try {
						driver.findElement(By.className("with-menu")).click();
						//driver.findElement(By.className("m-headServ")).click();
						//driver.findElement(By.className("citi-navbar-item")).click();
					} catch (NoSuchElementException e) {
						// Catch block 
					}
					
					
					Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(ShootingStrategies.scaling(1.75f), 1000)).takeScreenshot(driver);
			        //Copy the file to a location and use try catch block to handle exception
			        try {                 
			        	ImageIO.write(screenshot.getImage(),"JPG",new File("C:\\projectScreenshots\\homePageScreenshot"+i+".jpg"));             
			        } catch (IOException e) {                 // TODO Auto-generated catch block                 
			        	  e.printStackTrace();            
			        }   
					i = i + 1;
				}  
					//System.out.print("\n");  
			}  
		}  
		catch (Exception e)   
		{  
			e.printStackTrace();  
		}  
		driver.close();
		
		
		
		
		
		
		//open browser with desried URL
		//driver.get("https://www.banamex.com/es/personas/banca-digital/bancanet.html");
		//driver.manage().window().maximize();
		//driver.findElement(By.className("m-headProd")).click();
		//driver.findElement(By.className("m-headServ")).click();
		
		
		//Take the screenshot
		//Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(ShootingStrategies.scaling(1.75f), 1000)).takeScreenshot(driver);
        //Copy the file to a location and use try catch block to handle exception
        //try {                 
        //	ImageIO.write(screenshot.getImage(),"JPG",new File("C:\\projectScreenshots\\homePageScreenshot.jpg"));             
        //} catch (IOException e) {                 // TODO Auto-generated catch block                 
        //	  e.printStackTrace();            
        //}   

		//closing the browser
		//driver.close();

	}

}
