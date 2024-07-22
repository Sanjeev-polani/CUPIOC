package crossbrowser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CrossBrowserTest {
	public static WebDriver driver;						// declaring the web driver 					
	Scanner scanner = new Scanner(System.in);				// initiation of the scanner class to give the user preference
/*	
	//Declarion of the Browsers
	FirefoxOptions firefoxOptions = new FirefoxOptions();
	ChromeOptions chromeOptions = new ChromeOptions();
	EdgeOptions edgeOptions = new EdgeOptions();

	@BeforeTest												//Testng annotation to perform the logic before test
	//@Parameters("browser")
	public void initialize(String browser) {
		if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();			//Setup for the firefox browser
			driver = new FirefoxDriver();
			System.out.println("Firefox browser is launched");
		}

		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();			//Setup for the Chrome browser
			driver = new ChromeDriver();
			System.out.println("Chrome browser is launched");
		}

		if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();				//Setup for the Edge browser
			driver = new EdgeDriver();
			System.out.println("Edge browser is launched");
		}

	}
*/
	
	
	@BeforeTest
	public void Browserstack()
	{
		System.out.println("Select the Browser of your choice \n for Chrome Browser : 0 \n for Edge Browser : 1 \n for Firefox Browser : 2 \n for Safari Browser : 3 \n Enter the Browser value :");
		Scanner browser = new Scanner(System.in);	
		int b = browser.nextInt();
	
	switch(b)
	{
		case 0: 
				WebDriverManager.chromedriver().setup();			//Setup for the Chrome browser
				driver = new ChromeDriver();
				System.out.println("Chrome browser is launched");
				break;
				
		case 1:
				WebDriverManager.edgedriver().setup();			//Setup for the Edge browser
				driver = new EdgeDriver();
				System.out.println("Edge browser is launched");
				break;
		
		case 2: 
				WebDriverManager.firefoxdriver().setup();			//Setup for the Firefox browser
				driver = new FirefoxDriver();
				System.out.println("Firefox browser is launched");
				break;
				
		case 3:
		 
			WebDriverManager.safaridriver().setup();			//Setup for the Firefox browser
			driver = new SafariDriver();
			System.out.println("Safari browser is launched");
			break;
	}
	
	}
	
	
/*	
	public void CrossBrowsers()
	{
		WebDriverManager.chromedriver().setup();			//Setup for the Chrome browser
		driver = new ChromeDriver();
		System.out.println("Chrome browser is launched");
	}
*/	
	
	
	
	
//Login functionality
	@Test(priority = 1)										//Testng annotation declaration of the order in which code to execute
	public void cambridgeLogin() throws InterruptedException, Exception {								//Login Method
		driver.get("https://www.cambridgeone.org");				//calling the get method for redirection to the URL
		driver.manage().window().maximize();	//to Maximize the browser window
		System.out.println("Window is maximized");	
		CommonUtils.loginTest();								//Calling the logintest method from CommonUtils java class
	}

	public static void frameex() {
	WebElement wf = driver.findElement(By.xpath(".//iframe[contains(@id,'iframe')]"));	
	driver.switchTo().frame(wf);		
	}
	
	//Frame method
	public static void frame() {
		try {
			//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));						//Explicit wait for the presence of the element located
			//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//iframe[contains(@id,'i')]")));
			WebElement wf = driver.findElement(By.xpath(".//iframe[contains(@id,'iframe')]"));				//Storing the xpath to a webelement 
			String wfid = wf.getAttribute("id");
			System.out.println("Frame id is " + wfid);									//to print the attribute value in console
			driver.switchTo().frame(wfid);												// switching to frame
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// search for the material
	@Test(priority = 2)															//Priority 2 to execute this method in the second instance
	public void Material() throws InterruptedException, IOException {				//Declaration of material method with exceptions
		
		// Selecting the Library Tab
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//span[@class='nav-heading'][text()='My library']")));
		driver.findElement(By.xpath("//span[@class='nav-heading'][text()='My library']")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='bundleName']")));

		WebElement searchbox = driver.findElement(By.xpath("//*[@id='bundleName']"));
		searchbox.click();																//clicking on the search

		
		String upref = null;
		
		System.out.println("Enter the umbrella Title : \n Press 1 for MQA Ava Ops Test Product 02 \n Press 2 for MQA Ava Ops Test Product 05 \n Enter your preference :");
		int choice = scanner.nextInt();
		/*	
		switch(choice)
		{
		case 1 : upref = "MQA Ava Ops Test Product 02";
				System.out.println("Selected Umbrella : "+upref);
				break;
				
		case 2: upref = "MQA Ava Ops Test Product 05";
				System.out.println("Selected Umbrella : "+upref);
				break;
		}
	*/	
		
		if(choice == 1)
		{
			upref = "MQA Ava Ops Test Product 02";
			
			
			
		}
		else if(choice == 2)
		{
			upref = "MQA Ava Ops Test Product 05";
			
			
		}

		System.out.println("Selected Umbrella : "+upref);
		driver.findElement(By.xpath("//*[@id='bundleName']")).sendKeys(upref);
	/*	
		// User choice of Entering the material name in the search box

		System.out.println("Enter the umbrella title : ");
		String upref = scanner.nextLine();											//User preference to enter the Umbrella title
		System.out.println("Title entered :" + upref);								//Entered tex will be printed in the output console for reference purpose
		searchbox.sendKeys(upref);
*/
		
		
		String prod2 = "MQA Ava Ops Test Product 02";								//to compare the title declaration of the strings 
		String prod5 = "MQA Ava Ops Test Product 05";
		

		// Code for MQA Ava Ops Test Product 02
		if (upref.equals(prod2)) {
			// Selecting from the search list

			driver.findElement(By.xpath("//ul[@id='myLibrarySearchDropdown']//li[1]//a")).click();

			// Clicking on the course block

			wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//*[@id='myLibraryAllCourseMaterialBundleTitlemqaavallainopsproduct02']")));
			WebElement mqaAvaProd2ContainerBtn = driver
					.findElement(By.xpath("//*[@id='myLibraryAllCourseMaterialBundleTitlemqaavallainopsproduct02']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", mqaAvaProd2ContainerBtn);
			mqaAvaProd2ContainerBtn.click();

			// Clicking on practice extra
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					"//*[@id='#bundleInfoSectionCollapsemqaavallainopsproduct02']//div[@class='product-components']//p[contains(text(), 'Practice Extra')]")));
			driver.findElement(By.xpath(
					"//*[@id='#bundleInfoSectionCollapsemqaavallainopsproduct02']//div[@class='product-components']//p[contains(text(), 'Practice Extra')]"))
					.click();
			driver.findElement(By.xpath(
					"//product-details//section[@class='digital-components']//div[contains(@class, 'heading')][contains(text(), 'Practice Extra')]"))
					.click();

			// calling the method to read the data from excel
			avallainreleasesprod2();

		}

		

		// Code for MQA Ava Ops Test Product 05
				else if (upref.equalsIgnoreCase(prod5)) {
					// Selecting from the search list
					
					
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='myLibrarySearchDropdown']/li[1]/a")));
					//Thread.sleep(6000);
					driver.manage().timeouts().implicitlyWait(6000, TimeUnit.SECONDS);
					
					
				//	List<WebElement> suggestionlist = driver.findElements(By.xpath("//*[@class='dropdown-item p-0']"));
					//int suggestioncount = suggestionlist.size();
					//suggestionlist.get(suggestioncount-1).click();
					
					
					driver.findElement(By.xpath("//*[@id='myLibrarySearchDropdown']/li[1]")).click();
					
					
					
					//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='dropdown-item p-0']/a")));
					//driver.findElement(By.xpath("//*[@class='dropdown-item p-0']/a")).click();
					
					
					// Clicking on the course block
		 
					wait.until(ExpectedConditions.presenceOfElementLocated(
							By.xpath("//*[@id='myLibraryAllCourseMaterialBundleTitlemqaavaopspe05']")));
					WebElement mqaAvaProd5ContainerBtn = driver
							.findElement(By.xpath("//*[@id='myLibraryAllCourseMaterialBundleTitlemqaavaopspe05']"));
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", mqaAvaProd5ContainerBtn);
					mqaAvaProd5ContainerBtn.click();
				
		 
					// Clicking on practice extra
		 
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
							"//*[@id='#bundleInfoSectionCollapsemqaavaopspe05']//div[@class='product-components']//p[contains(text(), 'Practice Extra')]")));
					driver.findElement(By.xpath(
							"//*[@id='#bundleInfoSectionCollapsemqaavaopspe05']//div[@class='product-components']//p[contains(text(), 'Practice Extra')]"))
							.click();
					driver.findElement(By.xpath(
							"//product-details//section[@class='digital-components']//div[contains(@class, 'heading')][contains(text(), 'Practice Extra')]"))
							.click();
				
		 
					
					avallainreleasesprod5();
				}
			}
	
	
	// Clicking on user prefered Avallain release

	public void avallainreleasesprod5() throws IOException, InterruptedException {
		// Fetching on the avallain release
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@id, 'unit-')]")));
		List<WebElement> releaseList = driver.findElements(By.xpath("//*[contains(@id, 'unit-')]"));
		System.out.println("Select the Avallain Release of your Choice");
		// Reading the data from excel
		String excelfilepath = ".\\dataexcelfile\\Readexcel.xlsx";					//path of the excel file stored in a string

		// String excelfilepath =
		// "C:\\Users\\spolani\\Downloads\\Datafromexcel\\Readexcel.xlsx";

		FileInputStream fis = new FileInputStream(excelfilepath);						//To read the data from excel

		XSSFWorkbook workbook = new XSSFWorkbook(fis);									//Opens the excel file workbook
		// XSSFSheet sheet = workbook.getSheet("Ava");
		XSSFSheet sheet = workbook.getSheetAt(0);										//Opens the sheet in a workbook

		int rows = sheet.getLastRowNum();												// it will checks for the last row number and stores the intiger value
		int startingRow = rows > 14 ? rows-14 : 0;										// In the list of all avallain releases it will take the last 14 releases entries
		int cols = sheet.getRow(1).getLastCellNum();									//based on the row value it will gets the columns
		
		// printing the header row/columns
		XSSFRow header = sheet.getRow(0);
		for (int col = 0; col < cols; col++) {												//Looping the columns based on the last column value
			XSSFCell hcell = header.getCell(col);
			switch (hcell.getCellType()) {													//Switch cases to segrigate the value related to int or string
			case STRING:
				System.out.print(hcell.getStringCellValue());
				break;
			case NUMERIC:
				System.out.print((int) hcell.getNumericCellValue());
				break;
			}
			System.out.print(" ");															// It will print a space between the content while executed in the console
		}
		System.out.println();																// Print the content present in excel

		//Loop for the row
		for (int r = startingRow; r <= rows; r++) {											
			XSSFRow row = sheet.getRow(r);

			for (int c = 0; c < cols; c++) {
				XSSFCell cell = row.getCell(c);

				switch (cell.getCellType()) {						//Switch cases to segrigate the value related to int or string
				case STRING:
					System.out.print(cell.getStringCellValue());						
					break;
				case NUMERIC:
					System.out.print((int) cell.getNumericCellValue());
					break;
				}
				System.out.print(" ");							// It will print a space between the content while executed in the console
			}
			System.out.println();								// Print the content present in excel
		}
	}

	// Clicking on user prefered Avallain release

	public void avallainreleasesprod2() throws IOException, InterruptedException {
		
		// Fetching on the avallain release
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@id, 'unit-')]")));
		List<WebElement> releaseList = driver.findElements(By.xpath("//*[contains(@id, 'unit-')]"));					//List of webelements will be stored in the object

		// Reading the data from excel
		String excelfilepath = ".\\dataexcelfile\\Readexcel.xlsx";

		FileInputStream fis = new FileInputStream(excelfilepath);

		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		// XSSFSheet sheet = workbook.getSheet("Ava");
		XSSFSheet sheet = workbook.getSheetAt(1);

		int rows = sheet.getLastRowNum();
		int startingRow = rows > 14 ? rows-14 : 0;
		int cols = sheet.getRow(1).getLastCellNum();
		
		// printing the header row/columns
		XSSFRow header = sheet.getRow(0);
		for (int col = 0; col < cols; col++) {
			XSSFCell hcell = header.getCell(col);
			switch (hcell.getCellType()) {
			case STRING:
				System.out.print(hcell.getStringCellValue());
				break;
			case NUMERIC:
				System.out.print((int) hcell.getNumericCellValue());
				break;
			}
			System.out.print(" ");
		}
		System.out.println();

		for (int r = startingRow; r <= rows; r++) {
			XSSFRow row = sheet.getRow(r);

			for (int c = 0; c < cols; c++) {
				XSSFCell cell = row.getCell(c);

				switch (cell.getCellType()) {
				case STRING:
					System.out.print(cell.getStringCellValue());
					break;
				case NUMERIC:
					System.out.print((int) cell.getNumericCellValue());
					break;
				}
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	

	public List<String> getActivitiesFromExcel() throws IOException, InterruptedException {				//Reading the excel data for the activites to be executed directly
		// Reading the data from excel
		//String excelfilepath = ".\\dataexcelfile\\R44 LO Activities.xlsx";

		System.out.println(" Enter the value : 0 for Prod 5 and 1 for prod 2");
		Scanner sc = new Scanner(System.in);
		int activityvalue = sc.nextInt();
		
		
		
		String excelfilepath = ".\\dataexcelfile\\Stage2_ActivityTypes.xlsx";
		FileInputStream fis = new FileInputStream(excelfilepath);

		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		
		
		
		XSSFSheet sheet = workbook.getSheetAt(activityvalue);												//It will opens the first sheet
		
		
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(1).getLastCellNum();
		System.out.println("Number of Rows: " + rows);
		System.out.println("Number of columns: " + cols);
		
		/*
		 * List<String> activitiesFromExcel = new ArrayList<>(); //Declaration of the
		 * array list for (int r = 1; r <= rows; r++) { XSSFRow row = sheet.getRow(r);
		 * XSSFCell cell = row.getCell(cols-1); switch (cell.getCellType()) { case
		 * STRING: activitiesFromExcel.add(cell.getStringCellValue()); break; case
		 * NUMERIC: System.out.println("Invalid content"); break; } }
		 * System.out.println("Activities from excel::\n" +
		 * activitiesFromExcel.toString()); return activitiesFromExcel; }
		 */
	List<String> activitiesFromExcel = new ArrayList<>();									//Declaration of the array list
	for (int r = 1; r <= rows; r++)
	//for (int r = 32; r <= rows; r++)
		
	{
		XSSFRow row = sheet.getRow(r);
		XSSFCell cell = row.getCell(cols-1);
		switch (cell.getCellType()) {
		case STRING:
			activitiesFromExcel.add(cell.getStringCellValue());
			break;
		case NUMERIC:
			System.out.println("Invalid content");
			break;
		}
	}
	System.out.println("Activities from excel::\n" + activitiesFromExcel.toString());
	
	return activitiesFromExcel;
}
	
	
	
	
	
	@Test(priority = 3)
	public void ClickingonLessons() throws InterruptedException, IOException {

//common code to click on the exercises
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

//List<WebElement> avarel =  driver.findElements(By.xpath("//*[@class='unit-detail']"));
		System.out.println("Enter the release no to be verify : ");
		Scanner scanner = new Scanner(System.in);	
		
		
		int relasesToVerify = scanner.nextInt();	
		
		
//List<WebElement> avarel =  driver.findElements(By.xpath("//*[@class='circle minor-bg-color']"));	
		List<WebElement> avarel = driver.findElements(By.xpath("//*[@class='h5']"));
		WebElement clk = avarel.get(relasesToVerify);
		String text = avarel.get(relasesToVerify).getText(); // Avallain release getting the title
		clk.click(); // avallain release clicking
		System.out.println("Avallain Release Clicked");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// Clicking on lessons
		String lessonsXpath = "//div[contains(@aria-label,'" + text + "')]//*[contains(@class,'align-lesson')]";
		List<WebElement> lessons = clk.findElements(By.xpath(lessonsXpath));
		System.out.println(lessons.size());
		for (int l = 0; l < lessons.size(); l++) {
			WebElement lesson = lessons.get(l);
			System.out.println("Lessons found:: " + lessons.size());
			
			if (l == 0) 
		//	if (l == 1) 
			{
					//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", lesson);
					
					
					
				lesson.click();
			}
				
				else {
					
					 continue;
				 }

// Clicking on LO's
			String exXpath = lessonsXpath + "//*[contains(@id, 'lo-')]";
			List<WebElement> exercises = lesson.findElements(By.xpath(exXpath));
			for (int ex = 0; ex < exercises.size(); ex++) {
				WebElement exercise = exercises.get(ex);
				System.out.println("Excercises found:: " + exercises.size());
				if (l == 0 && ex == 0) 
			//	if (l == 1 && ex == 8)
				{
					exercise.click();
					
				}
			}

				//Looping all the activity types
				Properties props = CommonUtils.getConfiguration();
				String activitiesStr = props.getProperty("activities");
				String[] activities = activitiesStr.split(",");
				CommonUtils.resetCounters();
				
//				Activities from the excel file
				List<String> activitiesList = getActivitiesFromExcel();
				//CommonUtils.zoomOutPage();												//calling Zoomout 
				for (String activity : activitiesList) {								// Iterator functionality to check the activities
					boolean activityFound = CommonUtils.performActivityTesting(props, activity);
				}
				//CommonUtils.zoomInPage();
				
				/*
				 * for (int i = 1; i <= 20; i++) {
				 * 
				 * boolean screenExists = false; System.out.println("In screen number :: " + i);
				 * for (String activity : activities) {
				 * System.out.println("Clicking on the activity:: act." + activity); boolean
				 * activityFound = CommonUtils
				 * .findWebElementComponentTypeByXpath(props.getProperty("act." + activity),
				 * activity); if (activityFound) { screenExists = true; break; } } if
				 * (!screenExists) {
				 * System.out.println("None of the activities found in the screen"); break;
				 * 
				 * } }
				 */
	// Clicking on next activity button in the end result screen

				//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Next activity')]")));
				//driver.findElement(By.xpath("//*[contains(text(),'Next activity')]")).click();
				
//				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class,'btn mt-2 btn-primary')]")));
//				driver.findElement(By.xpath("//a[contains(@class,'btn mt-2 btn-primary')]")).click();
				
			
			}

		}
	
	
	
	
	
	
	public void screenshot() throws IOException
	{
		//Use TakesScreenshot method to capture screenshot
		
		TakesScreenshot screenshot = (TakesScreenshot)driver;  //typecasting
		
		
		
		//Saving the screenshot in desired location
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		File Target = new File(".\\SeleniumScreenshots\\Avallain_"+timestamp()+".png");
		
		//Path to the location to save screenshot
		FileUtils.copyFile(source, Target);
		System.out.println("Screenshot is captured");
		
		
		File dest = new File(".\\SeleniumScreenshots\\Screen.png");
		
	}
	
	public static String timestamp()
	{
		return new SimpleDateFormat("yyyy-mmm-dd HH-mm").format(new Date());
	}
	
	public void score() throws IOException, InterruptedException 
	{
		List<String> activitiesList = getActivitiesFromExcel();
			for (String activity : activitiesList) {
			//boolean activityFound = CommonUtils.performActivityTesting(props, activity);
				if(activity.equalsIgnoreCase("Presentation_RDP") || activity.equalsIgnoreCase("Listen & Record_RDP"))
				{
					System.out.println("Activity is not Scorable");
				}
				else
				{
					System.out.println("Activity is Scorable");
				}
			}
		}
	
	
	public void timer()
	{
		 // Get the current time before the page load
        long startTime = System.currentTimeMillis();

        // Find an element on the page to wait for
        WebElement element = driver.findElement(By.tagName("body"));

        // Get the current time after the page load
        long endTime = System.currentTimeMillis();

        // Calculate the time taken to load the screen
        long loadTime = endTime - startTime;

        // Print the load time
        System.out.println("Page load time: " + loadTime + " milliseconds");

	}
	
		
	public static void screencheck() throws InterruptedException, IOException						//Phase 2 - To check all the interactive elements in the page
	{
				
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);							//Implicit wait for 30 sec
		System.out.println("Checking the Screen contains features ");
		System.out.println("------------------------");
		CommonUtils.frameex();																//To switch to frame
		//CommonUtils.launchinglotest();                                                    //Once the Lo clicked it will check for the 1st creen is displaying or not
		CommonUtils.launchinglotestnew();
		CommonUtils.activityHelp();															//checks for the activity help present or not
		CommonUtils.progressbar();															//checks the no of screens available in the LO
		CommonUtils.hint();																	//checks for the hint present or not
		//CommonUtils.scorableornot();
		//CommonUtils.score();
		CommonUtils.rubricM();																//checks for the Rubric present or not
		
		CommonUtils.headermediaaudio();														//checks for the Audio present or not
		CommonUtils.pinnedobjects();														//checks for the Pinned objects present or not
		CommonUtils.headermediavideonew();													//checks for the Video present or not
		//CommonUtils.headermediavideo();
		//Commonproducttypes.nextScreen();
		CommonUtils.frameclose();															//Closing of the Iframe
		
		
		//CommonUtils.feedbackbar();
		 //validateprevious();
		 //Commonutil.validatenext();
	}
	
	
	
//	@AfterTest
	public void endTest() {
		driver.quit();																	// To close the webdriver instance
	}
}
