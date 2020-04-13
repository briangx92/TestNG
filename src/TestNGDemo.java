
import org.testng.annotations.*;

import com.sun.tools.javac.util.*;

import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.interactions.Actions;

public class TestNGDemo {
	static WebDriver driver;
	ChromeOptions options = new ChromeOptions();
	int testCases = 1;
	
	private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyz";
	    private static final int RANDOM_STRING_LENGTH = 5;
	     
	    /**
	     * This method generates random string
	     * @return
	     */
	    public String generateRandomString(){
	         
	        StringBuffer randStr = new StringBuffer();
	        for(int i=0; i<RANDOM_STRING_LENGTH; i++){
	            int number = getRandomNumber();
	            char ch = CHAR_LIST.charAt(number);
	            randStr.append(ch);
	        }
	        return randStr.toString();
	    }
	     
	    /**
	     * This method generates random numbers
	     * @return int
	     */
	    private int getRandomNumber() {
	        int randomInt = 0;
	        Random randomGenerator = new Random();
	        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
	        if (randomInt - 1 == -1) {
	            return randomInt;
	        } else {
	            return randomInt - 1;
	        }
	    }
	    
	    public String randomText() {
	    	TestNGDemo rs = new TestNGDemo();
	    	String boom = rs.generateRandomString();
	    	return boom;
	    }
	
	@BeforeSuite
	public void beforeSuite() {
		System.setProperty("webdriver.chrome.driver", "/Users/csetuser/Desktop/Drivers/chromedriver");
		driver = new ChromeDriver();
		options.addArguments("--headless","--ignore-certificate-errors");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
		
	}
	@AfterSuite
	public void afterSuite() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}
	
	@BeforeMethod
	public void beforeMethod() throws InterruptedException {
		driver.manage().deleteAllCookies();
		System.out.println("Testing Test Case #" + testCases);
		Thread.sleep(1500);
	}
	
	@AfterMethod
	public void afterMethod() throws InterruptedException {
		driver.manage().deleteAllCookies();
		testCases += 1;
		System.out.println("");
		Thread.sleep(2000);
	}
	
//	Drop Database
	public void dropDB() throws InterruptedException {
		driver.manage().deleteAllCookies();
		Actions actions = new Actions(driver);
		dbLogin();
		driver.get("http://localhost/phpmyadmin/server_databases.php");
		Thread.sleep(1500);
		driver.findElement(By.cssSelector("input[title=old_home]")).click();
		driver.findElement(By.cssSelector("button[title=Drop]")).click();
		Thread.sleep(2000);
		WebElement confirm = driver.findElement(By.cssSelector("body > div.ui-dialog.ui-corner-all.ui-widget.ui-widget-content.ui-front.ui-dialog-buttons.ui-draggable.ui-resizable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > div > button.submitOK.ui-button.ui-corner-all.ui-widget"));
		actions.moveToElement(confirm).click().build().perform();
		driver.manage().deleteAllCookies();
	}
		
//	DB Login Method
	public void dbLogin() throws InterruptedException {
		Actions actions = new Actions(driver);
		driver.get("http://localhost/phpmyadmin/index.php");
		WebElement login = driver.findElement(By.xpath("//*[@id=\"input_username\"]"));
		WebElement submit = driver.findElement(By.xpath("//*[@id=\"input_go\"]"));
		actions.click(login).sendKeys(login, "root").click(submit).build().perform();
		Thread.sleep(2000);
		
		
	}
	
//	Old Home Login Page Method
	public void oldHomeLogin() {
		driver.get("http://localhost/finalproject220/index.php");
		
	}
//	Admin Sign-In
	public void adminLogin() {
		oldHomeLogin();
		driver.findElement(By.cssSelector("input[type=email]")).sendKeys("a@a.com");
		driver.findElement(By.cssSelector("input[type=password]")).sendKeys("1");
		driver.findElement(By.cssSelector("button[type=submit]")).click();
		
	}
	
//	User Login
	public void userLogin(String user) {
		oldHomeLogin();
		driver.findElement(By.cssSelector("input[type=email]")).sendKeys(user);
		driver.findElement(By.cssSelector("input[type=password]")).sendKeys("1");
		driver.findElement(By.cssSelector("button[type=submit]")).click();
	}
	
//	Old Home Register Method
	public void oldHomeRegister() {
		driver.get("http://localhost/finalproject220/register.php");
		
	}

//	Database Edit Box Method
	public void dbEditBox() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"id_rows_to_delete0_left\"]")).click();
		WebElement editButton = driver.findElement(By.xpath("//*/div[2]/button[1]"));
		editButton.click();
		
	}
//	Database Edit Menu Method
	public void dbEditMenu() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input[title=\"Check all\"]")).click();
		driver.findElement(By.xpath("//*/div[2]/button[1]")).click();
		
	}
	
//	Database Search Box Method
	public void dbSearchBox(String sendText) throws InterruptedException {
		Actions actions = new Actions(driver);
		Thread.sleep(2000);
		WebElement search = driver.findElement(By.cssSelector("#page_content > div.sqlqueryresults.ajax > table:nth-child(2) > tbody > tr > td:nth-child(8) > input"));
		Thread.sleep(1500);
		actions.moveToElement(search).click(search).sendKeys(sendText).build().perform();
		
	}
	
//	Database login successful
//	@Test
	public void testCase1() throws InterruptedException {
		dbLogin();
		assertEquals()
	}
	
//	Login successful
	@Test
   	public void testCase2() {
		adminLogin();
	}
   	
//	New Supervisor User
	@Test
	public void testCase3() {
//		Registration
		oldHomeRegister();
		driver.findElement(By.cssSelector("#role > option:nth-child(5)")).click();
		driver.findElement(By.xpath("//*/form/fieldset/input[1]")).sendKeys("Test");
		driver.findElement(By.xpath("//*/form/fieldset/input[2]")).sendKeys("Case");
		driver.findElement(By.xpath("//*/form/fieldset/input[3]")).sendKeys("test@test.com");
		driver.findElement(By.xpath("//*/form/fieldset/input[4]")).sendKeys("password");
		driver.findElement(By.xpath("//*/form/fieldset/input[5]")).sendKeys("1234567890");
		driver.findElement(By.xpath("//*/form/fieldset/input[6]")).sendKeys("1234567890");
		driver.findElement(By.xpath("//*/form/fieldset/input[6]")).sendKeys("2020-02-02");
		driver.findElement(By.xpath("//*/form/fieldset/button")).click();
	}
	
//	Database Validation #1
//	Validate new Supervisor
	@Test
	public void testCase4() throws InterruptedException {
		dbLogin();
//		Users Table
		driver.get("http://localhost/phpmyadmin/sql.php?db=old_home&table=users&pos=0");
		driver.findElement(By.xpath("//*[contains(text(),'eric')]"));
		
	}
	
//	Patient inputs visible
	@Test
	public void testCase5() {
		oldHomeRegister();
		driver.findElement(By.cssSelector("#role > option:nth-child(2)")).click();
		driver.findElement(By.xpath("//*[@id='section']/label[1]"));
		driver.findElement(By.xpath("//*[@id='section']/label[2]"));
		driver.findElement(By.xpath("//*[@id='section']/label[3]"));
		
	}
	
//	Database Validation #2
//	Does patient Jane exist
	@Test
	public void testCase6() throws InterruptedException {
		oldHomeLogin();
//		Login patient 15
		driver.findElement(By.xpath("/html/body/form/label[1]/input")).sendKeys("pat@a.com");
		driver.findElement(By.xpath("/html/body/form/label[2]/input")).sendKeys("1");
		driver.findElement(By.xpath("/html/body/form/button")).click();
//		Checks for patient 15
		driver.findElement(By.xpath("//*[contains(text(),'15')]"));

		dbLogin();
//		DB Search
		driver.get("http://localhost/phpmyadmin/sql.php?db=old_home&table=users");
		driver.findElement(By.xpath("//*[contains(text(),'jane')]"));
		
		
	}
	
//	Database Validation #3
//	Does patient 15 have a family code of 54
	@Test
	public void testCase7() throws InterruptedException {
		dbLogin();
		driver.get("http://localhost/phpmyadmin/sql.php?db=old_home&table=patient&pos=0");
		WebElement search = driver.findElement(By.xpath("//*[@id=\"page_content\"]/div[2]/table[1]/tbody/tr/td[8]/input"));
		search.click();
		search.sendKeys("15");
		driver.findElement(By.xpath("//*[contains(text(),'15')]"));
		driver.findElement(By.xpath("//*[contains(text(),'54')]"));	
		
	}
	
//	Database Validation #4
//	Are patients able to access the Patients Homepage
	@Test
	public void testCase8() throws InterruptedException {
		userLogin("pat@a.com");
		String current = driver.getCurrentUrl();
		assertSame(current,current);
	}
	
//	Change roles for patients access to the Patients Homepage
	@Test
	public void testCase9() {
		adminLogin();
		driver.findElement(By.xpath("//li[9]/a")).click();
		driver.findElement(By.xpath("//tr[9]/td[3]/button")).click();	
	}
	
//	Create a new Roster
	@Test
	public void testCase10() {
		userLogin("sup@a.com");
		driver.get("http://localhost/finalproject220/newroster.php");
		Actions actions = new Actions(driver);
		WebElement dateBox = driver.findElement(By.cssSelector("input[type=date]"));
		actions.moveToElement(dateBox).sendKeys(dateBox, "11122021");
		actions.perform();
//		Caregiver name
		driver.findElement(By.xpath("//*/select[3]/option[2]")).click();
		driver.findElement(By.xpath("//*/select[5]/option[4]")).click();
		driver.findElement(By.xpath("//*/select[7]/option[3]")).click();
		driver.findElement(By.xpath("//*/select[9]/option[1]")).click();
//		Caregiver group
		driver.findElement(By.xpath("//*/select[4]/option[4]")).click();
		driver.findElement(By.xpath("//*/select[6]/option[2]")).click();
		driver.findElement(By.xpath("//*/select[8]/option[3]")).click();
		driver.findElement(By.xpath("//*/select[10]/option[1]")).click();
//		Doctor
		driver.findElement(By.xpath("//*/select[2]/option[2]")).click();
//		Submit
		driver.findElement(By.cssSelector("button[name=submit]")).click();
		
		
	}
	
//	Database Validation #5
//	Verify new Roster
	@Test
	public void testCase11() throws InterruptedException {
		dbLogin();
		Thread.sleep(2000);
		driver.get("http://localhost/phpmyadmin/db_structure.php?server=1&db=old_home&tbl_type=table");
		Thread.sleep(2000);
		driver.get("http://localhost/phpmyadmin/sql.php?db=old_home&table=roster&pos=0");
		driver.findElement(By.xpath("//*[@id=\"pma_navigation_tree_content\"]/ul/li[4]/div[4]/ul/li[2]/div[3]/ul/li[8]"));	
		
	}
	
//	Database Validation #6
//	Check userid 15's payment due
	@Test
	public void testCase12() throws InterruptedException {
		adminLogin();
		driver.get("http://localhost/finalproject220/payment.php");
		driver.findElement(By.cssSelector("input[name=patientid")).sendKeys("15");
		driver.findElement(By.xpath("//fieldset/button[1]")).click();
		String due = driver.findElement(By.cssSelector("input[name=due]")).getAttribute("value");
		dbLogin();
		Thread.sleep(2000);
		driver.get("http://localhost/phpmyadmin/sql.php?server=1&db=old_home&table=patient&pos=0");
		WebElement searchBox = driver.findElement(By.cssSelector("#page_content > div.sqlqueryresults.ajax > table:nth-child(2) > tbody > tr > td:nth-child(8) > input"));
		searchBox.click();
		searchBox.sendKeys(due);
		dbEditBox();
		WebElement dbDue = driver.findElement(By.xpath("//*[@id=\"field_8_3\"]"));
		String dbDueValue = dbDue.getAttribute("value");
		assertEquals(due,dbDueValue);
		
	}
	
//	Create new role
	@Test
	public void testCase13() {

		adminLogin();
		driver.get("http://localhost/finalproject220/role.php");
		final String randomString = randomText();
		final String output = randomString.substring(0, 1).toUpperCase() + randomString.substring(1);
		WebElement randomRole = driver.findElement(By.cssSelector("input[name=newrole]"));
		randomRole.sendKeys(randomString);
		driver.findElement(By.cssSelector("input[name=newrolesubmit]")).click();
		driver.findElement(By.xpath("//*[contains(text(),'"+ output+"')]"));
		
	}
	
//	Database Validation #7
//	Check new role
	@Test
	public void testCase14() throws InterruptedException {
		adminLogin();
		driver.get("http://localhost/finalproject220/role.php");
		final String randomString = randomText();
		final String output = randomString.substring(0, 1).toUpperCase() + randomString.substring(1);
		WebElement randomRole = driver.findElement(By.cssSelector("input[name=newrole]"));
		randomRole.sendKeys(randomString);
		driver.findElement(By.cssSelector("input[name=newrolesubmit]")).click();
		driver.findElement(By.xpath("//*[contains(text(),'"+ output+"')]"));
		
		dbLogin();
		driver.get("http://localhost/phpmyadmin/tbl_structure.php?server=1&db=old_home&table=role");
		driver.findElement(By.xpath("//*[@id='tablestructure']/tbody/*/*/*[contains(text(),'"+randomString+"')]"));
		
	}
	
//	Check patient info for patient 15
	@Test
	public void testCase15() {
		adminLogin();
		driver.get("http://localhost/finalproject220/patientinfo.php");
		WebElement patientinfo = driver.findElement(By.cssSelector("input[name=getpatientid]"));
		patientinfo.click();
		patientinfo.sendKeys("15");
		driver.findElement(By.cssSelector("input[name=search]")).click();
		driver.findElement(By.cssSelector("input[value='15']"));
		
	}

//	Update patient 15 group
	@Test	
	public void testCase16() {
		Scanner s = new Scanner(System.in);
		adminLogin();
		driver.get("http://localhost/finalproject220/patientinfo.php");
		WebElement patientinfo = driver.findElement(By.cssSelector("input[name=getpatientid]"));
		patientinfo.click();
		patientinfo.sendKeys("15");
		driver.findElement(By.cssSelector("input[name=search]")).click();
		
		System.out.print("enter a group number from 2-5: ");
		final int randoNum = s.nextInt();
		s.close();
		
		driver.findElement(By.cssSelector("body > form > fieldset > select > option:nth-child("+randoNum+")")).click();
		driver.findElement(By.cssSelector("input[name=change]")).click();
		
	}
	

//	Does the cancel button redirect you home
	@Test
	public void testCase17() {
		adminLogin();
		driver.get("http://localhost/finalproject220/patientinfo.php");
		driver.findElement(By.cssSelector("input[type=button]")).click();
		
	}
	
//	Database Validation #8
//	Does the patient 15 group update validate on the database
	@Test
	public void testCase18() throws InterruptedException	{
		adminLogin();
		driver.get("http://localhost/finalproject220/patientinfo.php");
		WebElement patientinfo = driver.findElement(By.cssSelector("input[name=getpatientid]"));
		patientinfo.click();
		patientinfo.sendKeys("15");
		WebElement patientID = driver.findElement(By.cssSelector("input[name=getpatientid]"));
		String id = patientID.getAttribute("value");
		driver.findElement(By.cssSelector("input[name=search]")).click();
		
//		WebElement group = driver.findElement(By.xpath("//*/select/option[1]"));
		driver.findElement(By.cssSelector("input[name=change]")).click();
		dbLogin();
		
		driver.get("http://localhost/phpmyadmin/sql.php?server=1&db=old_home&table=patient&pos=0");
		Thread.sleep(2000);
		dbSearchBox(id);
			
	}
	
//	Database Validation #9
//	Validate employee 13 salary
	@Test
	public void testCase19() throws InterruptedException {
		
		adminLogin();
		driver.get("http://localhost/finalproject220/employee.php");
		WebElement newSalaryBox = driver.findElement(By.cssSelector("input[name=newsalary]"));
		newSalaryBox.sendKeys("1000");
		driver.findElement(By.cssSelector("button[name=ok]")).click();
		
		dbLogin();
		driver.get("http://localhost/phpmyadmin/sql.php?db=old_home&table=employee&pos=0");
		dbSearchBox("1000");
		dbEditBox();
		WebElement dbSalaryNum = driver.findElement(By.xpath("//*/div[1]/div/table[2]/tbody/tr[1]/td[6]/span"));

		assertEquals(dbSalaryNum.getText(),"1000");
	}
	
//	Does Ok button update salary
	@Test
	public void testCase20() {
		Actions actions = new Actions(driver);
		adminLogin();
		driver.get("http://localhost/finalproject220/employee.php");
		WebElement newSalaryBox = driver.findElement(By.cssSelector("input[name=newsalary]"));
		String salaryNum = "1000";
		actions.sendKeys(newSalaryBox, salaryNum).perform();
		driver.findElement(By.cssSelector("button[name=ok]")).click();
		driver.findElement(By.xpath("//*/tbody/tr[2]/*[contains(text(),'"+salaryNum+"')]"));
		
	}
	
//	Does cancel button redirect you home from employee page
	@Test
	public void testCase21() {
		adminLogin();
		driver.get("http://localhost/finalproject220/employee.php");
		String empPage = driver.getCurrentUrl();
		driver.findElement(By.cssSelector("body > form > fieldset > label > input[type=button]:nth-child(6)")).click();
		String homePage = driver.getCurrentUrl();
		assertNotSame(empPage,homePage);
		}
		
	
//	Database Validation #10
//	Has the evil surgeon been approved?
	@Test
	public void testcase22() throws InterruptedException {
		dbLogin();
		driver.get("http://localhost/phpmyadmin/sql.php?server=1&db=old_home&table=users&pos=0");
		dbEditMenu();
		WebElement approve = driver.findElement(By.xpath("//*/div[15]/table/tbody/tr[9]/td[5]/input[2]"));
		assertEquals(approve.getAttribute("value"),"0");
		
	}
	
//	Can you approve the evil surgeon?
	@Test
	public void testCase22() {
		adminLogin();
		driver.get("http://localhost/finalproject220/regapproval.php");
		WebElement approved = driver.findElement(By.cssSelector("button[name=approved]"));
		assertEquals(approved.getText(),"Approve");

	}
	
//	Can you disapprove the evil surgeon?
	@Test
	public void testCase23() {
		adminLogin();
		driver.get("http://localhost/finalproject220/regapproval.php");
		WebElement deny = driver.findElement(By.cssSelector("button[name=denied]"));
		assertEquals(deny.getText(),"Deny");
		
	}
//	Login as dr. who
	@Test
	public void testCase24() {
		userLogin("doc@a.com");
	}
	
//	Does Dr. Who have Tracy as a patient
	@Test
	public void testCase25() {
		userLogin("doc@a.com");
		WebElement patientTracy = driver.findElement(By.xpath("//*/tbody/tr[2]/td[1]"));
		assertEquals(patientTracy.getText(),patientTracy.getText());
		
	}
	
//	Can Dr. Who search for a patient
	@Test
	public void testCase26() {
		userLogin("doc@a.com");
		driver.findElement(By.cssSelector("input[name='search_text']")).sendKeys("tracy");
		driver.findElement(By.cssSelector("button[name='search']")).click();
	}
	
//	Search for future appointments
	@Test
	public void testCase27() {
		userLogin("doc@a.com");
		Actions actions = new Actions(driver);
		WebElement dateBox = driver.findElement(By.cssSelector("input[type=date]"));
		actions.moveToElement(dateBox).sendKeys(dateBox, "11122021");
		actions.perform();
		driver.findElement(By.cssSelector("body > form:nth-child(8) > button")).click();
		
	}
	
//	Can doctor access roster home
	@Test
	public void testCase28() {
		userLogin("doc@a.com");
		driver.get("http://localhost/finalproject220/rosterhome.php");
		
	}
	
//	Is the supervisor E. Cartman for the first appointment
	@Test
	public void testCase29() {
		userLogin("doc@a.com");
		driver.get("http://localhost/finalproject220/rosterhome.php");
		driver.findElement(By.xpath("/html/body/form/table/tbody/tr[2]/*[contains(text(),'eric cartman')]"));
		
	}
	
//	Check patient login
	@Test
	public void testCase30() {
		userLogin("pat@a.com");
		
	}
	
//	Does roster home button direct you to rosterhome
	@Test
	public void testCase31() {
		userLogin("pat@a.com");
		driver.get("http://localhost/finalproject220/rosterhome.php");
		String roster = driver.getCurrentUrl();
		
		assertNotSame(roster,"http://localhost/finalproject220/rosterhome.php");
		
	}
	
//	Can caregivers login
	@Test
	public void testCase32() {
		for(int i = 1; i <= 4;i++) {
			userLogin("cg"+i+"@a.com");
			String current = driver.getCurrentUrl();
			assertSame(current,current);
			driver.findElement(By.xpath("//*/ul/li[3]/button")).click();
		}
	}
	
//	Does list button list patients duty for caregivers
	@Test
	public void testCase33() {
		userLogin("cg1@a.com");
		driver.findElement(By.cssSelector("body > form > button")).click();
		
	}
//	Does cancel button redirect caregivers home
	@Test
	public void testCase34() {
		userLogin("cg1@a.com");
		String current = driver.getCurrentUrl();
		driver.findElement(By.cssSelector("input[type=button]")).click();
		String url = driver.getCurrentUrl();
		
		assertNotSame(current,url);
	}
	
//	Does logout work for all caregivers
	@Test
	public void testCase35() {
		for(int i = 1; i <= 4;i++) {
			userLogin("cg"+i+"@a.com");
			String current = driver.getCurrentUrl();
			driver.findElement(By.xpath("//*/ul/li[3]/button")).click();
			String url = driver.getCurrentUrl();
			assertNotSame(current,url);
			
		}	
	}

//	Can doctor access doctor home page
	@Test
	public void testCase36() {
		userLogin("d@a.com");
		String home = driver.getCurrentUrl();
		assertSame(home, home);
		
	}
	
//	Can admin access docappt from nav bar
	@Test
	public void testCase37() {
		adminLogin();
		String current = driver.getCurrentUrl();
		driver.findElement(By.xpath("html/body/nav/ul/li[2]/a")).click();
		String url = driver.getCurrentUrl();
		assertNotSame(current,url);	
	}
	
//	Can admin access employee from nav bar
	@Test
	public void testCase38() {
		adminLogin();
		String current = driver.getCurrentUrl();
		driver.findElement(By.xpath("html/body/nav/ul/li[3]/a")).click();
		String url = driver.getCurrentUrl();
		assertNotSame(current,url);
		
	}
	
//	Can admin access new roster from nav bar
	@Test
	public void testCase39() {
		adminLogin();
		String current = driver.getCurrentUrl();
		driver.findElement(By.xpath("html/body/nav/ul/li[4]/a")).click();
		String url = driver.getCurrentUrl();
		assertNotSame(current,url);
		
	}
	
	
//	Can admin access patient info from nav bar
	@Test
	public void testCase40() {
		adminLogin();
		String current = driver.getCurrentUrl();
		driver.findElement(By.xpath("html/body/nav/ul/li[5]/a")).click();
		String url = driver.getCurrentUrl();
		assertNotSame(current,url);
		
	}
	
//	Can admin access payment from nav bar
	@Test
	public void testCase41() {
		adminLogin();
		String current = driver.getCurrentUrl();
		driver.findElement(By.xpath("html/body/nav/ul/li[6]/a")).click();
		String url = driver.getCurrentUrl();
		assertNotSame(current,url);
		
	}
	
//	Can admin access reg approval from nav bar
	@Test
	public void testCase42() {
		adminLogin();
		String current = driver.getCurrentUrl();
		driver.findElement(By.xpath("html/body/nav/ul/li[7]/a")).click();
		String url = driver.getCurrentUrl();
		assertNotSame(current,url);
		
	}
	
//	Can admin access register from nav bar
	@Test
	public void testCase43() {
		adminLogin();
		String current = driver.getCurrentUrl();
		driver.findElement(By.xpath("html/body/nav/ul/li[8]/a")).click();
		String url = driver.getCurrentUrl();
		assertNotSame(current,url);
		
	}
	
//	Can admin access role from nav bar
	@Test
	public void testCase44() {
		adminLogin();
		String current = driver.getCurrentUrl();
		driver.findElement(By.xpath("html/body/nav/ul/li[9]/a")).click();
		String url = driver.getCurrentUrl();
		assertNotSame(current,url);
		
	}
	
//	Can admin access roster home from nav bar
	@Test
	public void testCase45() {
		adminLogin();
		String current = driver.getCurrentUrl();
		driver.findElement(By.xpath("html/body/nav/ul/li[10]/a")).click();
		String url = driver.getCurrentUrl();
		assertNotSame(current,url);
		
	}
	
//	Does logout redirect admin to home
	@Test
	public void testCase46() {
		adminLogin();
		String current = driver.getCurrentUrl();
		driver.findElement(By.cssSelector("button[name=logout]")).click();
		String url = driver.getCurrentUrl();
		assertNotSame(current,url);
		
	}
	
//	Does logout redirect doctor to home
	@Test
	public void testCase47() {
		userLogin("doc@a.com");
		String current = driver.getCurrentUrl();
		driver.findElement(By.xpath("/html/body/nav/ul/li[3]/button")).click();
		String url = driver.getCurrentUrl();
		assertNotSame(current,url);
		
	}
	
//	Does logout redirect caregiver to home
	@Test
	public void testCase48() {
		userLogin("doc@a.com");
		String current = driver.getCurrentUrl();
		driver.findElement(By.xpath("/html/body/nav/ul/li[3]/button")).click();
		String url = driver.getCurrentUrl();
		assertNotSame(current,url);
		
		
	}
	
//	Can supervisor access doctappt from nav bar
	@Test
	public void testCase49() {
		userLogin("sup@a.com");
		String current = driver.getCurrentUrl();
		driver.findElement(By.xpath("/html/body/nav/ul/li[1]/a")).click();
		String url = driver.getCurrentUrl();
		assertNotSame(current,url);
	}
	
//	Can supervisor access newroster from nav bar
	@Test
	public void testCase50() {
		userLogin("sup@a.com");
		String current = driver.getCurrentUrl();
		driver.findElement(By.xpath("/html/body/nav/ul/li[2]/a")).click();
		String url = driver.getCurrentUrl();
		assertNotSame(current,url);
	}
//	Can supervisor access payment from nav bar
	@Test 
	public void testCase51() {
		userLogin("sup@a.com");
		String current = driver.getCurrentUrl();
		driver.findElement(By.xpath("/html/body/nav/ul/li[3]/a")).click();
		String url = driver.getCurrentUrl();
		assertNotSame(current,url);
	}
//	Can supervisor access reg approval from nav bar
	@Test
	public void testCase52() {
		userLogin("sup@a.com");
		String current = driver.getCurrentUrl();
		driver.findElement(By.xpath("/html/body/nav/ul/li[4]/a")).click();
		String url = driver.getCurrentUrl();
		assertNotSame(current,url);
	}
	
//	Can supervisor access register from nav bar
	@Test
	public void testCase53() {
		userLogin("sup@a.com");
		String current = driver.getCurrentUrl();
		driver.findElement(By.xpath("/html/body/nav/ul/li[5]/a")).click();
		String url = driver.getCurrentUrl();
		assertNotSame(current,url);
	}
	
//	Can supervisor access roster home from nav bar
	@Test
	public void testCase54() {
		userLogin("sup@a.com");
		String current = driver.getCurrentUrl();
		driver.findElement(By.xpath("/html/body/nav/ul/li[6]/a")).click();
		String url = driver.getCurrentUrl();
		assertNotSame(current,url);
	}
	
//	Can supervisor access supervisor home from nav bar
	@Test
	public void testCase55() {
		userLogin("sup@a.com");
		String current = driver.getCurrentUrl();
		driver.findElement(By.xpath("/html/body/nav/ul/li[7]/a")).click();
		String url = driver.getCurrentUrl();
		assertNotSame(current,url);
	}
	
//	Can Caregivers access roster home from nav bar
	@Test
	public void testCase56() {
		for(int i = 1; i <= 4;i++) {
			userLogin("cg"+i+"@a.com");
			String current = driver.getCurrentUrl();
			driver.findElement(By.xpath("/html/body/nav/ul/li[2]/a")).click();
			String url = driver.getCurrentUrl();
			assertNotSame(current,url);
			driver.findElement(By.xpath("/html/body/nav/ul/li[3]/button")).click();
		}
		
	}
	
//	Determine that there are 9 employees
	@Test
	public void testCase57() {
		adminLogin();
		driver.get("http://localhost/finalproject220/employee.php");
		ArrayList<WebElement> employees = (ArrayList<WebElement>) driver.findElements(By.tagName("tr"));
		employees.remove(0);
		System.out.println("employees: "+employees.size());
		assertSame(employees.size(), 9);
		
	}
	
//	Determine 1 pending registration approval
	@Test
	public void testCase58() {
		adminLogin();
		driver.get("http://localhost/finalproject220/regapproval.php");
		ArrayList<WebElement> pending = (ArrayList<WebElement>) driver.findElements(By.tagName("tr"));
		pending.remove(0);
		pending.remove(0);
		assertEquals(pending.size(), 1);
		
	}
	
//	Determine 3 rosters
	@Test
	public void testCase59() throws InterruptedException {
		adminLogin();
		driver.get("http://localhost/finalproject220/rosterhome.php");
		ArrayList<WebElement> roster = (ArrayList<WebElement>) driver.findElements(By.tagName("tr"));
		roster.remove(0);
		Thread.sleep(1000);
		assertEquals(roster.size(), 4);
	}
//	Determine if there are 6 roles
	@Test
	public void testCase60() {
		adminLogin();
		driver.get("http://localhost/finalproject220/role.php");
		ArrayList<WebElement> roles = (ArrayList<WebElement>) driver.findElements(By.xpath("/html/body/table/tbody/tr[1]/td[contains(text(),'')]"));
		roles.remove(0);
		assertEquals(roles.size(),6);
	}
	
//	Does search patient id button exist in payment
	@Test
	public void testCase61() {
		adminLogin();
		driver.get("http://localhost/finalproject220/payment.php");
		WebElement search = driver.findElement(By.cssSelector("button[name=search]"));
		assertEquals(search.getText(),search.getText());
	}
	
//	Does submit form button exist in payment
	@Test
	public void testCase62() {
		adminLogin();
		driver.get("http://localhost/finalproject220/payment.php");
		WebElement submit = driver.findElement(By.cssSelector("button[name=submit]"));
		assertEquals(submit.getText(),submit.getText());
	}
	
//	Does the admin have title as Admin Home
	@Test
	public void testCase63() {
		adminLogin();
		String window = driver.getTitle();
		assertEquals(window,window);
	}
	
//	Does doctor appointment have title in doctor appointment
	@Test
	public void testCase64() {
		adminLogin();
		String window = driver.getTitle();
		driver.get("http://localhost/finalproject220/doctappt.php");
		String newWindow = driver.getTitle();
		assertNotSame(window,newWindow);
		
	}
	
//	Does employee page have employee as a title in employee page
	@Test
	public void testCase65() {
		adminLogin();
		String window = driver.getTitle();
		driver.get("http://localhost/finalproject220/employee.php");
		String newWindow = driver.getTitle();
		assertNotSame(window,newWindow);
		
	}
	
//	Does new roster page have old home as a title
	@Test
	public void testCase66() {
		adminLogin();
		String window = driver.getTitle();
		driver.get("http://localhost/finalproject220/newroster.php");
		String newWindow = driver.getTitle();
		assertNotSame(window,newWindow);
		
	}
	
//	Does patient info page have patient info as a title
	@Test
	public void testCase67() {
		adminLogin();
		String window = driver.getTitle();
		driver.get("http://localhost/finalproject220/patientinfo.php");
		String newWindow = driver.getTitle();
		assertNotSame(window,newWindow);
		
	}
	
//	Does payment page have payment as a title
	@Test
	public void testCase68() {
		adminLogin();
		String window = driver.getTitle();
		driver.get("http://localhost/finalproject220/payment.php");
		String newWindow = driver.getTitle();
		assertNotSame(window,newWindow);
		
	}
	
//	Does registration approval page have registration approval as a title
	@Test
	public void testCase69() {
		adminLogin();
		String window = driver.getTitle();
		driver.get("http://localhost/finalproject220/regapproval.php");
		String newWindow = driver.getTitle();
		assertNotSame(window,newWindow);
		
	}
	
//	Does register page have register as a title
	@Test
	public void testCase70() {
		adminLogin();
		String window = driver.getTitle();
		driver.get("http://localhost/finalproject220/register.php");
		String newWindow = driver.getTitle();
		assertNotSame(window,newWindow);
		
	}
	
//	Does role page have role as a title
	@Test
	public void testCase71() {
		adminLogin();
		String window = driver.getTitle();
		driver.get("http://localhost/finalproject220/role.php");
		String newWindow = driver.getTitle();
		assertNotSame(window,newWindow);
		
	}
	
//	Does roster page have roster as a title
	@Test
	public void testCase72() {
		adminLogin();
		String window = driver.getTitle();
		driver.get("http://localhost/finalproject220/rosterhome.php");
		String newWindow = driver.getTitle();
		assertNotSame(window,newWindow);
		
	}
	
//	Does caregiver page have caregiver as a title
	@Test
	public void testCase73() {
		userLogin("cg1@a.com");
		String window = driver.getTitle();
		driver.get("http://localhost/finalproject220/caregiverhome.php");
		String newWindow = driver.getTitle();
		assertNotSame(window,newWindow);
		
	}
	
//	Does doctorhome page have doctor home as a title
	@Test
	public void testCase74() {
		userLogin("d@a.com");
		String window = driver.getTitle();
		driver.get("http://localhost/finalproject220/rosterhome.php");
		String newWindow = driver.getTitle();
		assertNotSame(window,newWindow);
		
	}
	
//	Does family home page have family home as a title
	@Test
	public void testCase75() {
		userLogin("fam@a.com");
		String window = driver.getTitle();
		driver.get("http://localhost/finalproject220/familyhome.php");
		String newWindow = driver.getTitle();
		assertNotSame(window,newWindow);
		
	}
	
//	Does supervisor home page have supervisor home as a title
	@Test
	public void testCase76() {
		userLogin("sup@a.com");
		String window = driver.getTitle();
		driver.get("http://localhost/finalproject220/supervisorhome.php");
		String newWindow = driver.getTitle();
		assertNotSame(window,newWindow);
		
	}
	
//	Does admin home page have a form
	@Test
	public void testCase77() {
		adminLogin();
		WebElement form = driver.findElement(By.xpath("/html/body/form"));
		assertNotNull(form);
		
	}
	
//	Does care giver home page have a form
	@Test
	public void testCase78() {
		userLogin("cg1@a.com");
		WebElement form = driver.findElement(By.xpath("/html/body/form"));
		assertNotNull(form);
		
	}
	
//	Does doctor appointment page have a form
	@Test
	public void testCase79() {
		adminLogin();
		driver.get("http://localhost/finalproject220/doctappt.php");
		WebElement form = driver.findElement(By.xpath("/html/body/form"));
		assertNotNull(form);
		
	}
	
//	Does doctor home page have a form
	@Test
	public void testCase80() {
		adminLogin();
		driver.get("http://localhost/finalproject220/doctorhome.php");
		WebElement form = driver.findElement(By.xpath("/html/body/form"));
		assertNotNull(form);
		
	}
	
//	Does employee page have a form
	@Test
	public void testCase81() {
		adminLogin();
		driver.get("http://localhost/finalproject220/employee.php");
		WebElement form = driver.findElement(By.xpath("/html/body/form"));
		assertNotNull(form);
		
	}
	
//	Does family home page have a form
	@Test
	public void testCase82() {
		userLogin("fam@a.com");
		driver.get("http://localhost/finalproject220/familyhome.php");
		WebElement form = driver.findElement(By.xpath("/html/body/form"));
		assertNotNull(form);
		
	}
	
//	Does new roster page have a form
	@Test
	public void testCase83() {
		userLogin("fam@a.com");
		driver.get("http://localhost/finalproject220/newroster.php");
		WebElement form = driver.findElement(By.xpath("/html/body/form"));
		assertNotNull(form);
		
	}
	
//	Does patient home page have a form
	@Test
	public void testCase84() {
		userLogin("pat@a.com");
		driver.get("http://localhost/finalproject220/patienthome.php");
		WebElement form = driver.findElement(By.xpath("/html/body/main/form"));
		assertNotNull(form);
		
	}
	
//	Does patient info page have a form
	@Test
	public void testCase85() {
		adminLogin();
		driver.get("http://localhost/finalproject220/patientinfo.php");
		WebElement form = driver.findElement(By.xpath("/html/body/form"));
		assertNotNull(form);
		
	}
	
//	Does payment page have a form
	@Test
	public void testCase86() {
		adminLogin();
		driver.get("http://localhost/finalproject220/payment.php");
		WebElement form = driver.findElement(By.xpath("/html/body/form"));
		assertNotNull(form);
		
	}
	
//	Does registration approval page have a form
	@Test
	public void testCase87() {
		adminLogin();
		driver.get("http://localhost/finalproject220/regapproval.php");
		WebElement form = driver.findElement(By.xpath("/html/body/form"));
		assertNotNull(form);
		
	}
	
//	Does register page have a form
	@Test
	public void testCase88() {
		adminLogin();
		driver.get("http://localhost/finalproject220/register.php");
		WebElement form = driver.findElement(By.tagName("form"));
		assertNotNull(form);
		
	}
//	Does role page have a form
	@Test
	public void testCase89() {
		adminLogin();
		driver.get("http://localhost/finalproject220/role.php");
		WebElement form = driver.findElement(By.tagName("form"));
		assertNotNull(form);
		
	}
	
//	Does roster home page have a form
	@Test
	public void testCase90() {
		adminLogin();
		driver.get("http://localhost/finalproject220/rosterhome.php");
		WebElement form = driver.findElement(By.tagName("form"));
		assertNotNull(form);
		
	}
//	Does supervisor home page have a form
	@Test
	public void testCase91() {
		userLogin("sup@a.com");
		driver.get("http://localhost/finalproject220/supervisor.php");
		WebElement form = driver.findElement(By.tagName("form"));
		assertNotNull(form);
		
	}
//	Does login have a form
	@Test
	public void testCase92() {
		oldHomeLogin();
		driver.get("http://localhost/finalproject220/index.php");
		WebElement form = driver.findElement(By.tagName("form"));
		assertNotNull(form);
		
	}
	
//	Does submit form button exist in role page
	@Test
	public void testCase93() {
		adminLogin();
		driver.get("http://localhost/finalproject220/role.php");
		WebElement button = driver.findElement(By.cssSelector("input[type=submit]"));
		assertNotNull(button);
	}
	
//	Does calender input exist in admin report page
	@Test
	public void testCase94() {
		adminLogin();
		WebElement calender = driver.findElement(By.cssSelector("input[type=date]"));
		assertNotNull(calender);
	}
	
//	Does submit button exist in admin report page
	@Test
	public void testCase95() {
		adminLogin();
		WebElement search = driver.findElement(By.cssSelector("button[name=submit]"));
 		assertNotNull(search);
	}
	
//	Does List Patients Duty button exist in caregiver home page
	@Test
	public void testCase96() {
		userLogin("cg1@a.com");
		WebElement list = driver.findElement(By.cssSelector("button[name=list]"));
		assertNotNull(list);
	}
	
//	Does cancel button exist in caregiver home page
	@Test
	public void testCase97() {
		userLogin("cg1@a.com");
		WebElement cancel = driver.findElement(By.cssSelector("button[name=list]"));
		assertNotNull(cancel);
	}
	
//	Does update button exist in patient info page
	@Test
	public void testCase98() {
		adminLogin();
		driver.get("http://localhost/finalproject220/patientinfo.php");
		WebElement update = driver.findElement(By.cssSelector("input[name=change]"));
		assertNotNull(update);
		
	}
	
//	Does doctor home page search button 1 exist
	@Test
	public void testCase99() {
		adminLogin();
		driver.get("http://localhost/finalproject220/doctorhome.php");
		WebElement search = driver.findElement(By.xpath("/html/body/form[1]/fieldset/button"));
		assertNotNull(search);
	}
	
//	Does doctor home page search button 2 exist
	@Test
	public void testCase100() {
		adminLogin();
		driver.get("http://localhost/finalproject220/doctorhome.php");
		WebElement search = driver.findElement(By.xpath("/html/body/form[2]/button"));
		assertNotNull(search);
	}
	
	
	
	

}
