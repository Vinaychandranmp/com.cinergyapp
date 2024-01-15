package com.cinergyapp.page;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TicketBookingPages 
{	

    public WebDriver driver;
    Properties properties = new Properties();

    public TicketBookingPages(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void initialize(String browser, String url) {
        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        Duration duration = Duration.ofSeconds(10);
        driver.manage().timeouts().implicitlyWait(duration);

        driver.get(url);
        driver.manage().window().maximize();
    }

    public void select_location() {
    	WebElement location_charlotte=driver.findElement(By.xpath("//select[@id='location_select']//option[contains(text(),'Charlotte, NC')]"));
    	location_charlotte.click();
        
    }
    public void movies_menu_selection()
    {
    	WebElement movie_menu_location=driver.findElement(By.xpath("//div[@id='navbarSupportedContent']//a[contains(text(),'Movies')]"));
    	movie_menu_location.click();
    }
   
    public void movie_selection()
    {	
    	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(15));
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(),'The Color Purple')]")));
    	WebElement movie_selected=driver.findElement(By.xpath("//h3[contains(text(),'The Color Purple')]"));
    	movie_selected.click();
    	WebElement dateselection=driver.findElement(By.xpath("//select[@id='choose_date']"));
    	Select d1=new Select(dateselection);
    	d1.selectByValue("2024-01-15");
    }
    public void opt_showtime()
    {
    	WebElement showtime=driver.findElement(By.xpath("//li[@class='new_time_list_inner 2024-01-15 choose_time_li']//span[contains(text(),' 3:20 PM ')]"));
    	showtime.click();
    }
    public void seat_selection()
    {	
    	List<WebElement> selection_seat=driver.findElements(By.xpath("//div[@class='seats pb-5']//li"));
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selection_seat.get(0));
    	int count=0;
    	for(WebElement element:selection_seat)
    	{	
    		
    		if(element.isDisplayed() && element.isEnabled())
    		{	

    			element.click();
    			
    			count++;
    			if (count == 4) {
                    break;
    			}
    		}
    			
    	}
    	
    }
    public void apply_voucher_code()
    {	
    	
    	WebElement vouchercode_applied=driver.findElement(By.xpath("//input[@id='voucher']"));
    	WebElement apply_button=driver.findElement(By.xpath("//button[@class='btn btn-primary login apply-voucher']"));
    	vouchercode_applied.sendKeys("Test@123");
    	apply_button.click();
    }
    public void select_senior_citizen()
    {
    	for(int i=1;i<=3;i++)
    	{
    		WebElement senior_citizen=driver.findElement(By.xpath("//li[@onclick=\"addCart('Ticket7',1)\"]"));
    		senior_citizen.click();
    		Assert.assertTrue(senior_citizen.isDisplayed(), "Ticket type dropdown is not displayed");
    		WebElement senior_citizen_name=driver.findElement(By.xpath("//table[@id='ticket']//td[contains(text(),'Senior (55+)')]"));
    		Assert.assertEquals(senior_citizen_name.getText(), "Senior (55+)", "Incorrect ticket type displayed");
    		String actualTicketType=senior_citizen_name.getText();
    		String expectedTicketType = "Senior (55+)";
    		System.out.println("Ticket type validation result: " + (actualTicketType.equals(expectedTicketType) ? "Pass" : "Fail"));
    	}
    	
    }
    public void continue_booking()
    {
    	WebElement continue_booking_ticket=driver.findElement(By.xpath("//a[@id='continue_web']"));
    	WebElement confirm_continue=driver.findElement(By.xpath("//div[@class='popup-left text-center popup-margin-outside']//button[contains(text(),'Continue')]"));
    	continue_booking_ticket.click();
    	confirm_continue.click();
    }
    public void billing_details()
    {
    	WebElement firstname=driver.findElement(By.xpath("//input[@name='first_name']"));
    	firstname.sendKeys("Vinay");
    	WebElement lastname=driver.findElement(By.xpath("//input[@name='last_name']"));
    	lastname.sendKeys("MP");
    	WebElement email=driver.findElement(By.xpath("//input[@name='email']"));
    	email.sendKeys("vinay@gmail.com");
    	WebElement phone=driver.findElement(By.xpath("//input[@name='phone']"));
    	phone.sendKeys("9562340662");
    	WebElement zip=driver.findElement(By.xpath("//input[@name='zip']"));
    	zip.sendKeys("76768");
    	WebElement continue_final=driver.findElement(By.xpath("//button[contains(text(),'CONTINUE')]"));
    	continue_final.click();
    	WebElement confirm=driver.findElement(By.xpath("//button[@class='btn btn-primary yes_btn']"));
    	confirm.click();
    	WebElement skip_button=driver.findElement(By.xpath("//a[contains(text(),'SKIP')]"));
    	skip_button.click();
    	
    	WebElement card_number=driver.findElement(By.xpath("//input[@id='PaymentCardNumber']"));
    	card_number.sendKeys("4111111111111111");
    	WebElement card_type=driver.findElement(By.xpath("//select[@id='card_type']"));
    	Select s1=new Select(card_type);
    	s1.selectByValue("0");
    	WebElement card_name=driver.findElement(By.xpath("//input[@id='billing_name']"));
    	card_name.sendKeys("Vinay");
    	WebElement expiration_month=driver.findElement(By.xpath("//select[@name='exp_month']"));
    	Select s2=new Select(expiration_month);
    	s2.selectByValue("12");
    	WebElement expiration_year=driver.findElement(By.xpath("//select[@name='exp_year']"));
    	Select s3=new Select(expiration_year);
    	s3.selectByValue("2024");
    	WebElement cvv=driver.findElement(By.xpath("//input[@name='cvv']"));
    	cvv.sendKeys("123");
    	WebElement billing_zip=driver.findElement(By.xpath("//input[@name='billing_zip']"));
    	billing_zip.sendKeys("48108");
    	WebElement pay_button=driver.findElement(By.xpath("//button[contains(text(),'Pay')]"));
    	pay_button.click();
    	String selected_movie="The Color Purple(PG13)";
    	String selected_screen=" Screen 10 ";
    	WebElement movieElement = driver.findElement(By.id("//table[@id='order_details_table']//td[contains(text(),'The Color Purple(PG13)')]"));
    	WebElement screen_element=driver.findElement(By.xpath("//table[@id='order_details_table']//td[contains(text(),' Screen 10 ')]"));
    	String actualMovie = movieElement.getText();
        String actualScreen = screen_element.getText();
        Assert.assertEquals(actualMovie, selected_movie, "Movie name does not match");
        Assert.assertEquals(actualScreen, selected_screen, "Screen number does not match");

    	
    }
    public void returnhome()
    {
    	WebElement returnhome=driver.findElement(By.xpath("//a[contains(text(),'Return Home')]"));
    	returnhome.click();
    }
    
    public void tearDown()
    {
    	driver.quit();
    }
}