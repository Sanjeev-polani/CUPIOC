package changedcode_DontUse;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CommonUtils extends CrossBrowserTest {															//Method extends from the crossbrowsertest class
	public static Map<String, Integer> activityCountMap = new HashMap<String, Integer>();					//Declaration of the Hashmap functionality 
	private static Properties props = CommonUtils.getConfiguration();										

	
	//This method is used to execute from the first activity in a loop once an activity is found(resets the count)
	public static void resetCounters() {
		String activitiesStr = props.getProperty("activities");
		String[] activities = activitiesStr.split(",");
		for (String activity : activities) {
			if (("dragndropsourceclick").equalsIgnoreCase(activity) || ("dropdown").equalsIgnoreCase(activity)
					|| ("dragndrop").equalsIgnoreCase(activity)
					|| ("dndsourcetargetclick").equalsIgnoreCase(activity)) {
				activityCountMap.put(activity + ".source", 0);
				activityCountMap.put(activity + ".dest", 0);
			} else {
				activityCountMap.put(activity, 0);
			}
		}
	}
	
	//Sanjeev code
	//This method is used to login to the C1 platform
		public static void loginTest() {
	 
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='onboarding-header-login-btn']")));
			
			
			WebElement loginButton = driver.findElement(By.xpath("//*[@id='onboarding-header-login-btn']"));
			loginButton.click();
			
			/*
			 * Scanner scanner = new Scanner(System.in);
			 * System.out.println("Enter your username: "); String username =
			 * scanner.nextLine(); System.out.println("Enter your password: "); String
			 * password = scanner.nextLine(); scanner.close();
			 */
	 
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[contains(@id, 'gigya-loginID-')])[1]")));
			WebElement userName = driver.findElement(By.xpath("(//*[contains(@id, 'gigya-loginID-')])[1]"));
//			  userName.sendKeys(username);
	 
			userName.sendKeys("QATeacher1@cambridgedev.org");
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[contains(@id, 'gigya-password-')])[1]")));
			WebElement passwordele = driver.findElement(By.xpath("(//*[contains(@id, 'gigya-password-')])[1]"));
//			  passwordele.sendKeys(password);
			passwordele.sendKeys("IOCQATeacher1");
			
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='gigya-layout-row']//div[@class='gigya-composite-control gigya-composite-control-submit nemo-login-btn']//input[@class='gigya-input-submit'])[4]")));
			
			WebElement loginBtn = driver.findElement(By.xpath(
					"(//div[@class='gigya-layout-row']//div[@class='gigya-composite-control gigya-composite-control-submit nemo-login-btn']//input[@class='gigya-input-submit'])[4]"));
			loginBtn.click();
			System.out.println("Logged in");
	}
	 

	//This method is used to login to the C1 platform 
	public static void loginTest1() {
		System.out.println("Entered login method");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[contains(@id, 'gigya-loginID-')])[1]")));
		WebElement loginButton = driver.findElement(By.xpath("//*[@id='onboarding-header-login-btn']"));
	
		
		
		System.out.println("Searched login button");
		loginButton.click();
		

		/*
		 * Scanner scanner = new Scanner(System.in);
		 * System.out.println("Enter your username: "); String username =
		 * scanner.nextLine(); System.out.println("Enter your password: "); String
		 * password = scanner.nextLine(); scanner.close();
		 */

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[contains(@id, 'gigya-loginID-')])[1]")));
		WebElement userName = driver.findElement(By.xpath("(//*[contains(@id, 'gigya-loginID-')])[1]"));
//		  userName.sendKeys(username);

		userName.sendKeys("QATeacher1@cambridgedev.org");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[contains(@id, 'gigya-password-')])[1]")));
		WebElement passwordele = driver.findElement(By.xpath("(//*[contains(@id, 'gigya-password-')])[1]"));
//		  passwordele.sendKeys(password);
		passwordele.sendKeys("IOCQATeacher1");

		WebElement loginBtn = driver.findElement(By.xpath(
				"(//div[@class='gigya-layout-row']//div[@class='gigya-composite-control gigya-composite-control-submit nemo-login-btn']//input[@class='gigya-input-submit'])[4]"));
		loginBtn.click();
		System.out.println("Logged in");
 }

	// This method is used to get the activity types from the configuration.properties file
	public static Properties getConfiguration() {
		Properties props = new Properties();
		try {
			props.load(CommonUtils.class.getClassLoader().getResourceAsStream("configuration.properties"));
		} catch (IOException e) {
			System.out.println(e);
		}
		return props;
	}
	
	public static void dropdowntestcases()
	{
		
		//String expectedoptions [] = {"3", "4"};
		String expectedoptions [] = {"Doesn't", "Aren't", "Don't"};
		WebElement dropdown = driver.findElement(By.xpath("//*[@class='rich-dropdown listbox__dropdown']"));
		
		Select sel = new Select(dropdown);
		
		List<WebElement> Options = sel.getOptions();
		
		for(int i=0; i<Options.size(); i++)
		{
			//Options.get(i).getText();
			//expectedoptions[i];
			System.out.println(Options.get(i).getText()+"--->"+expectedoptions[i]);
			Assert.assertEquals(Options.get(i).getText(),expectedoptions[i]);
			
		}
		
	}
	
	public static void dropdownPositiveandNegative()

	{
		
	}
	
	public static void attachment() {
		if (driver.findElement(By.xpath(
				"//*[@class='act_head_button tooltipz attachmentButton--type attachmentButton--targetSelf attachmentButton--text']"))
				.isDisplayed()) {
			List<WebElement> attachment1 = driver.findElements(By.xpath("//*[@id='attachments']"));
			System.out.println("Attachment count is " + attachment1.size());

			for (int a = 0; a < attachment1.size(); a++) {
				attachment1.get(a).click();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

			}

		} else {
			System.out.println("There is no attachment button");
		}

	}

	// check method
	public static boolean validatecheck() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@title='Check']")));
			if (driver.findElement(By.xpath("//*[@title='Check']")).isDisplayed()) {
				driver.findElement(By.xpath("//*[@title='Check']")).click();
				System.out.println("Check button is clicked");
				//generalfeedbacknew();
				
				
				CrossBrowserTest ss = new CrossBrowserTest();
				ss.screenshot();
				
				
				
			} else {
				System.out.println("Check button is missing");
				return false;
			}
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
		return true;
	}

	
	public static void frameclose() {
		System.out.println("Closing the frame");
		driver.switchTo().defaultContent();
	}

	//Phase 2 methods
	
	public static void progressbar()
	{
		/*
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='activity-header']/ul")));
		} catch (Exception e) {
			System.out.println("The activity progress bar is not present");
			e.printStackTrace();
			return;
		}
		*/
		
		 boolean prog = driver.findElement(By.xpath("//*[@id='activity-header']/ul")).isDisplayed();
		 if(prog==true)
		 {
			 List<WebElement> progress = driver.findElements(By.xpath("//*[@id='activity-header']/ul/li/span"));
			int size = progress.size();
			 System.out.println(size +" : Screens available in this LO ");
			System.out.println();
			
			
			
			System.out.println("The activity progress bar is present at the top");
			
			for (WebElement token : progress)
			{
				token.getText();	
				if(token.isDisplayed())
				{
				
				System.out.println("User is on the Screen : " +token.getText() );
				}
			}
			
			 //System.out.println("The activity progress bar is present at the top");
		 }
		 else
		 {
			 System.out.println("The activity progress bar is not present");
		 }
	}
		
		
		private static int size(int i) {
			// TODO Auto-generated method stub
			return 0;
		}
	 
		public static void headermediaaudio()throws InterruptedException
		{
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class = 'play_wrap' or @class='tinyAudioPlayer__audio playbutton show-pause-on-playing' or @class = 'play']")));
				
			} catch (Exception e) {
				System.out.println("Header audio media is not present");
				e.printStackTrace();
				return;
			}
			//boolean progs = driver.findElement(By.xpath("//*[@class='play']")).isDisplayed();
			//List<WebElement> play = driver.findElements(By.xpath("//*[@class='play']"));
			
			List<WebElement> play = driver.findElements(By.xpath("//*[@class = 'play_wrap' or @class='tinyAudioPlayer__audio playbutton show-pause-on-playing' or @class = 'play']"));
			boolean playFound = false;
			for (WebElement oneItem : play) {
				playFound = oneItem.isDisplayed();
				if (playFound) {
				oneItem.click();
					break;
				}
			}
			if(playFound) {
				System.out.println("Header audio media is present");
			}
			else {
				System.out.println("Header audio media is not present");
			}
	}
	
		
		
		
		
		
		public static void rubric()
		{	
			
			
			
			
			
			
			
			boolean rubric1 = driver.findElement(By.xpath("//*[@id='rubric-0']")).isDisplayed();
			boolean rubric2 = driver.findElement(By.xpath("//*[@id='rubric-1']")).isDisplayed();
			boolean rubric3 = driver.findElement(By.xpath("//*[@id='rubric-2']")).isDisplayed();
			boolean rubric4 = driver.findElement(By.xpath("//*[@id='rubric-3']")).isDisplayed();
			boolean rubric5 = driver.findElement(By.xpath("//*[@id='rubric-4']")).isDisplayed();
			
			if(rubric1||rubric2||rubric3||rubric4||rubric5 ==true)
			 {
				 System.out.println("Rubric is present");
			 }
			 else
			 {
				 System.out.println("Rubric is not present");
			 }
		}	
		public static void rubricM()
		{
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id,'rubric-')]")));
			} catch (Exception e) {
				System.out.println("Rubric is not present");
				//e.printStackTrace();
				return;
			}
			List<WebElement> elements = driver.findElements(By.xpath("//*[contains(@id,'rubric-')]"));
			boolean rubricFound = true;
			for (WebElement webElement : elements) {
				rubricFound = webElement.isDisplayed();
				if(rubricFound) {
					System.out.println("Rubric is present");
					break;
				}
				else
				 {
					 System.out.println("Rubric is not present");
				 }
			}
			
			 
		}
		
		
		
		
		
		
		public void score() throws IOException, InterruptedException  
		{
			List<String> activitiesList = getActivitiesFromExcel();
				for (String activity : activitiesList) 
				{
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
		
		
		
		
		
		public static void scorableornot()
		{
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='content-wrap at-draggable' or @class='content-wrap withHeaderAudio at-draggable']")));
			} catch (Exception e) {
				System.out.println("Activity is non scroable");
				e.printStackTrace();
				return;
			}
			if(driver.findElements(By.xpath("//*[@class='content-wrap at-draggable' or @class='content-wrap withHeaderAudio at-draggable']")).size()!=0)
			{
				System.out.println("Activity is scorable");
			}
			else
			{
				System.out.println("Activity is non scroable");
			}
		}
		
		
		public static void activityHelpTestcase()
		{
			WebElement AH = driver.findElement(By.xpath("//*[@id='activity-help-button']"));
			if(AH.isEnabled())
			{
				System.out.println("ActivityHelp Button is enabled user can able to click");
			}
			else
			{
				System.out.println("ActivityHelp Button is disable cant able to click");
			}
			
		}
		
		public static void activityHelp() throws InterruptedException {
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='activity-help-button']")));
			} catch (Exception e) {
				System.out.println("There is no help button");
			//	e.printStackTrace();
				return;
			}
			//activityHelpTestcase();
			if (driver.findElement(By.xpath("//*[@id='activity-help-button']")).isDisplayed()) {
				WebElement button = driver.findElement(By.xpath("//*[@id='activity-help-button']"));
				try {
					activityHelpTestcase();
					
					button.click();
					
					
					//	System.out.println("Activityhelp is clicked");
					
					
					
					Thread.sleep(2000);
					
					//WebElement close = driver.findElement(By.xpath("//*[contains(@class,'close_dialog ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only')]"));
					
					List<WebElement> close = driver.findElements(By.xpath("//*[contains(@class,'close_dialog ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only')]"));
					
					
					//List<WebElement> play = driver.findElements(By.xpath("//*[@class = 'play_wrap' or @class='tinyAudioPlayer__audio playbutton show-pause-on-playing' or @class = 'play']"));
					boolean closeFound = false;
					for (WebElement oneItem : close) {
						closeFound = oneItem.isDisplayed();
						if (closeFound) {
							oneItem.click();
							break;
						}
					}
					if(closeFound) {
						
						System.out.println("popup closed");
					}
					else {
						System.out.println("popup close button is not active");
					}
			
					
					
					
					
					
					
					
					
					
			/*		
					Thread.sleep(6000);
					if(close.isDisplayed())
					{
						//CrossBrowserTest bs = new CrossBrowserTest();
						//bs.screenshot();
						
						close.click();
						System.out.println("Activity help button popup close button is clicked");
					}
					else 
					{
						System.out.println("Activity help button not clickable");
					}
					//WebElement close = driver.findElement(By.xpath("//*[contains(@class,'close_dialog ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only')]"));
					//close.click();
					
				
				
				 */
				}catch (Exception e) {
					System.out.println("Activity help button not clickable");
				}
			} else {
				System.out.println("Help button is hidden");
			}
		}
		
		
		
		public static void activityHelpold() throws InterruptedException
		{
			
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='activity-help-button']")));
			} catch (Exception e) {
				System.out.println("There is no help button");
				e.printStackTrace();
				return;
			}
			
			
			
			WebElement help = driver.findElement(By.xpath("//*[@id='activity-help-button']"));
			System.out.println(help.isDisplayed());
			Thread.sleep(2000);
			
			if(driver.findElement(By.xpath("//*[@id='activity-help-button']")).isDisplayed())
			{
				if(driver.findElement(By.xpath("//*[@id='activity-help-button']")).isEnabled())
				{
				
				WebElement button=driver.findElement(By.xpath("//*[@id='activity-help-button']"));
				button.click();
				 System.out.println("Activityhelp is clicked");
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[contains(@class,'close_dialog ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only')]")).click();	
			
				}
			}
			else
			{
				System.out.println("Help button is hidden");
			}
		}
	 
		/*
		 * public static void hint() { try { WebDriverWait wait = new
		 * WebDriverWait(driver, Duration.ofSeconds(5));
		 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
		 * "//*[@class='infotext__handle']"))); } catch (Exception e) {
		 * System.out.println("There is no hint button in the screen");
		 * e.printStackTrace(); return; }
		 * if(driver.findElement(By.xpath("//*[@class='infotext__handle']")).isDisplayed
		 * ()) { List<WebElement> notes =
		 * driver.findElements(By.xpath("//*[@class='infotext__handle']"));
		 * System.out.println("hint count is "+notes.size());
		 * 
		 * for (int j=0; j<notes.size();j++) { notes.get(j).click();
		 * driver.findElement(By.
		 * xpath("//*[@class='close_dialog ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']"
		 * )).click(); } } else { System.out.println("There is no hint button"); } }
		 */
		
		
		public static void hinttestcases()
		{
			
		}
		
		
		
		public static void hint() throws InterruptedException
		{
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='infotext__handle']")));
			} catch (Exception e) {
				System.out.println("There is no hint button in the screen");
				//e.printStackTrace();
				return;
			}
		
			if(driver.findElement(By.xpath("//*[@class='infotext__handle']")).isDisplayed())
			{
				/*
				List<WebElement> notes = driver.findElements(By.xpath("//*[@class='infotext__handle']"));
				 System.out.println("hint count is "+notes.size());
				
				 for (int j=0; j<notes.size();j++)
				 {
					 notes.get(j).click();
				*/
				WebElement  notes = driver.findElement(By.xpath("//*[@class='infotext__handle']"));
				if(notes.isDisplayed())
				{
					try {
						notes.click();
						System.out.println("Hint is clicked");
						Thread.sleep(2000);
						driver.findElement(By.xpath(
								"//*[contains(@class,'close_dialog ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only')]"))
								.click();
					} catch (Exception e) {
						System.out.println("Activity help button not clickable");
					}
				} else 
				{
					System.out.println("There is no hint button");
				}
			}
		}
				
		
	 /*
		public static void headermediaaudio()
		{
			if(driver.findElement(By.xpath("((//*[@class='play'])[5])|((//*[@class='play'])[1])")).isDisplayed())
			{
				driver.findElement(By.xpath("((//*[@class='play'])[5])|((//*[@class='play'])[1])")).click();
				
				driver.manage().timeouts().implicitlyWait(50, TimeUnit.MINUTES);
				
				driver.findElement(By.xpath("//*[@class='pause']")).click();
				System.out.println("Header audio media is present");
			}
			
			else
			{
				System.out.println("Header audio media is not present");
			}
		}
		*/
		
		
		public static void headermediavideonew() throws InterruptedException {
			try {
			
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'play_wrap')]")));
			} catch (Exception e) {
				System.out.println("There is no Video media");
			//	e.printStackTrace();
				return;
			}
			if (driver.findElement(By.xpath("//*[contains(@class, 'play_wrap')]")).isDisplayed()) 
			{
				WebElement button = driver.findElement(By.xpath("//*[contains(@class, 'play_wrap')]"));
				try {
					button.click();
					System.out.println("Video is clicked");
					Thread.sleep(2000);
					driver.findElement(By.xpath("//*[contains(@class, 'play_wrap')]")).click();
					
					
					if(driver.findElement(By.xpath("//*[contains(@class, 'videoControls__button mediaPlayer__button--stepForward')]")).isDisplayed())
					{
						WebElement fastforward = driver.findElement(By.xpath("//*[contains(@class, 'videoControls__button mediaPlayer__button--stepForward')]"));
						fastforward.click();
						System.out.println("The video got fast forwarded to the next 10 seconds");
					
						
						if(driver.findElement(By.xpath("//*[contains(@class, '//*[contains(@class, 'videoControls__button mediaPlayer__button--stepBack mediaPlayer_button--disabled')]")).isDisplayed())
						{
							WebElement stepback = driver.findElement(By.xpath("//*[contains(@class, 'videoControls__button mediaPlayer__button--stepBack mediaPlayer_button--disabled')]"));
							stepback.click();
							System.out.println("The video got back to the next 10 seconds");
							
						
					
					if(driver.findElement(By.xpath("//*[contains(@class, 'videoControls__button transcript_button')]")).isDisplayed())
					{
						WebElement transcriptbutton = driver.findElement(By.xpath("//*[contains(@class, 'videoControls__button transcript_button')]"));
						transcriptbutton.click();
						Thread.sleep(5000);
						
						
						
						if(driver.findElement(By.xpath("//*[contains(@class, 'videoControls__button subtitles_button')]")).isDisplayed())
						{
							WebElement subtitles = driver.findElement(By.xpath("//*[contains(@class, 'videoControls__button subtitles_button')]"));
							subtitles.click();
							System.out.println("subtitles is present for this video");
							
							
						}
						else
							{
							System.out.println("subtitles is not present");
								
							}
						}else
						{
							System.out.println("Transcript option is not present");
						}
						
						}else
						{
							System.out.println("stepback option is not present");
						}
						
						}else
						{
							System.out.println("fast forwarded option is not present");
						}}
					catch (Exception e) {
					System.out.println("Video is Paused");
				}
			} else {
				System.out.println("Video media is not present");
			}
		}
		
		
		
		
			
		public static void headermediavideo()
		{
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='play'])[4]")));
//				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='pause'])[3]")));
//				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='extras_button full_screen']")));
			} catch (Exception e) {
				System.out.println("Header video media is not present");
				e.printStackTrace();
				return;
			}
			boolean playFound = driver.findElement(By.xpath("(//*[@class='play'])")).isDisplayed();
//			if(playFound) {
//				driver.findElement(By.xpath("(//*[@class='play'])[4]")).click();
//			}
//			boolean pauseFound = driver.findElement(By.xpath("(//*[@class='pause'])[3]")).isDisplayed();
//			boolean extrasButtonFound = driver.findElement(By.xpath("//*[@class='extras_button full_screen']")).isDisplayed();
			if(playFound)
			{
				System.out.println("Header video media is present");
			}
			else
			{
				System.out.println("Header video media is not present");
			}
		}
		
		
		
		public static void launchinglotest()
		{
			//String lt=driver.findElement(By.xpath("//*[@class='step-number'][1]")).getText();
			
		//	String lt = driver.findElement(By.xpath("//*[@id='activity-header']/ul/li[1]/span")).getText();
			//String lt2 = driver.findElement(By.xpath("//*[@id='activity-header']/ul/li[2]/span")).getText();
		
			
			WebElement s1 = driver.findElement(By.xpath("//*[@id='activity-header']/ul/li[1]/span"));
			WebElement s2 = driver.findElement(By.xpath("//*[@id='activity-header']/ul/li[2]/span"));
			
			if(s1.isDisplayed() && s2.isDisplayed())
			{
				
				System.out.println("Lo is in the other screen");
			}
			else if(s1.isDisplayed())
			{
				System.out.println("When launching an LO for the first time, screen one is displaying.");
			}
			
		/*	
			int d=Integer.parseInt(lt2);
			System.out.println(d);
			
			int c=Integer.parseInt(lt);
			System.out.println(c);
			
			
			
			if(c==1 && d == 0)
			{
				
				System.out.println("When launching an LO for the first time, screen one is displayed.");
			}
			else
			{
				System.out.println("Lo is in the other screen");
			}
		*/
		
		
		}
		
	 
		public static void generalfeedbacknew()
		{
			System.out.println("General Feedback");
			frame();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='feedback-panel']")));
			boolean feedback1 = driver.findElement(By.xpath("//*[@id='feedback-panel']")).isDisplayed();
	 
			if (feedback1 == true) {
				System.out.println("general feedback bar is present");
				/*
				Actions a = new Actions(driver);
	 
				WebElement fb1 = driver.findElement(By.xpath("//*[@id='feedback-panel']"));
	 
				a.dragAndDropBy(fb1, 0, 100).build().perform();
				a.dragAndDropBy(fb1, 0, -200).build().perform();
				*/
				
				System.out.println("This activity is scorable");
				
			} else {
				System.out.println("general feedback bar is not present");
				System.out.println("This activity is non-scorable");
			}
			frameclose();
			
				
		}
		
		
		public static void generalfeedback()
		{
			System.out.println("General Feedback");
			frame();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"content-2\"]/div/div/p/div/div/div[2]/div[1]/div[3]/button")));
			boolean feedback1 = driver.findElement(By.xpath("//*[@id=\"content-2\"]/div/div/p/div/div/div[2]/div[1]/div[3]/button")).isDisplayed();

			if (feedback1 == true) {
				System.out.println("general feedback bar is present");
				/*
				Actions a = new Actions(driver);

				WebElement fb1 = driver.findElement(By.xpath("//*[@id='feedback-panel']"));

				a.dragAndDropBy(fb1, 0, 100).build().perform();
				a.dragAndDropBy(fb1, 0, -200).build().perform();
				*/
				
			} else {
				System.out.println("general feedback bar is not present");
			}
			frameclose();
			
				
		}
		
		
		
		
		public static void pinnedobjects() throws InterruptedException
		{
			System.out.println("Checking Pinned Objects");
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='attachment-pinned-object']/button")));
			} catch (Exception e) {
				System.out.println("pinned object is not present");
				//e.printStackTrace();
				return;
			}
			if(driver.findElement(By.xpath("//*[@id='attachment-pinned-object']/button")).isDisplayed())
			{
				
				List<WebElement> pin = driver.findElements(By.xpath("//*[@id='attachment-pinned-object']/button"));
				 System.out.println("No of Pinned objects is "+pin.size());
				
				 for (int j=0; j<pin.size();j++)
				 {
					 pin.get(j).click();
				
				//WebElement  notes = driver.findElement(By.xpath("//*[@class='infotext__handle']"));
				//notes.click();
					 Thread.sleep(2000);
					
					 headermediavideonew();
				
					 driver.findElement(By.xpath("//*[@id='attachment-pinned-object']/button")).click();
				 }
			}
			
			else
			{
				System.out.println("There is no Pinned Objects");
			}
		}
		
		
		
		
		
	/*
		public static void pinnedobjects() throws InterruptedException
		{
			System.out.println("Checking Pinned Objects");
			
			if(driver.findElement(By.xpath("//*[@id='attachment-pinned-object']/button")).isDisplayed())
			{
				
				List<WebElement> pin = driver.findElements(By.xpath("//*[@id='attachment-pinned-object']/button"));
				 System.out.println("No of Pinned objects is "+pin.size());
				
				 for (int j=0; j<pin.size();j++)
				 {
					 pin.get(j).click();
				
				//WebElement  notes = driver.findElement(By.xpath("//*[@class='infotext__handle']"));
				//notes.click();
					 Thread.sleep(2000);
					
					
				
					 driver.findElement(By.xpath("//*[@id='attachment-pinned-object']/button")).click();
				 }
			}
			
			else
			{
				System.out.println("There is no Pinned Objects");
			}
		}
		
	*/		
		
		
		
		
		
		
	public static void feedbackbar() {
		frame();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='feedback-panel']")));
		boolean feedback1 = driver.findElement(By.xpath("//*[@id='feedback-panel']")).isDisplayed();

		if (feedback1 == true) {
			System.out.println("feedback bar is present");
			/*
			Actions a = new Actions(driver);

			WebElement fb1 = driver.findElement(By.xpath("//*[@id='feedback-panel']"));

			a.dragAndDropBy(fb1, 0, 100).build().perform();
			a.dragAndDropBy(fb1, 0, -200).build().perform();
			*/
			
		} else if(feedback1 == false){
			System.out.println("feedback bar is not present");
		}
		frameclose();
	}

	
	
	public static boolean validatenext() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@title='Next']")));
			if (driver.findElement(By.xpath("//*[@title='Next']")).isDisplayed()) {
				driver.findElement(By.xpath("//*[@title='Next']")).click();
				System.out.println("Next button is clicked");
			} else {
				System.out.println("Next button is missing");
				return false;
			}
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
		return true;
	}

	public static void zoomOutPage() throws InterruptedException {
		System.out.println("Zoom out page");
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			for (int i = 0; i < 5; i++) {
				robot.keyPress(KeyEvent.VK_SUBTRACT);
				robot.keyRelease(KeyEvent.VK_SUBTRACT);
//				Thread.sleep(500);
			}
			robot.keyRelease(KeyEvent.VK_CONTROL);
		} catch (AWTException e) {
			e.printStackTrace();
		}
//		Thread.sleep(3000);
	}

	public static void zoomInPage() throws InterruptedException {
		System.out.println("Zoom in page");
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			for (int i = 0; i < 5; i++) {
				robot.keyPress(KeyEvent.VK_ADD);
				robot.keyRelease(KeyEvent.VK_ADD);
//				Thread.sleep(500);
			}
			robot.keyRelease(KeyEvent.VK_CONTROL);
		} catch (AWTException e) {
			e.printStackTrace();
		}
//		Thread.sleep(3000);
	}

	public static boolean nextScreen() {
		CommonUtils.frameclose();
		System.out.println("Clicking next screen");
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@title='Check']")));
		boolean isCheckClicked = CommonUtils.validatecheck();
		
			//feedbackbar();
//			frame(); 
//			CommonUtils.feedbackbar();
//			CommonUtils.frameclose();
		CommonUtils.validatenext();
		return isCheckClicked;
	}

//Picking up the code based on the activity types (Checking each activity type)
	public static boolean findWebElementComponentTypeByXpath(String xpath, String activityType)
			throws InterruptedException, IOException {
		boolean activityFound = false;
		System.out.println("Xpath:: " + xpath);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
		} catch (TimeoutException e) {
			System.out.println("Timeout for activity type:: " + activityType + " xpath:: " + xpath);
		} catch (Exception ex) {
			System.out.println(ex);
		}

		// Code for MC Single
		List<WebElement> webEleList = new ArrayList<WebElement>();
		if ("mcsingle".equalsIgnoreCase(activityType)) {
			System.out.println("multiple choice single");
			
			frame();
			webEleList = driver.findElements(By.xpath(xpath));
			if (webEleList != null && webEleList.size() > 0) {
				Integer startIndex = activityCountMap.get(activityType);
				for (int i = startIndex; i < webEleList.size(); i++) {
					WebElement ele = webEleList.get(i);
					if (ele.isDisplayed()) {
						activityCountMap.put(activityType, i + 1);
						activityFound = true;
						
						wait.until(ExpectedConditions.elementToBeClickable(ele));
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
//						ele.click();
						
					}
				}
				if (activityFound) {
					nextScreen();
				} else {
					frameclose();
				}
			} else {
				frameclose();
			}
		}
		// Code for text entry
		else if ("textentry".equalsIgnoreCase(activityType)) {
			System.out.println("Text entry");
			
			frame();
			webEleList = driver.findElements(By.xpath(xpath));
			if (webEleList != null && webEleList.size() > 0) {
				Integer startIndex = activityCountMap.get(activityType);
				for (int i = 0; i < webEleList.size(); i++) {
					WebElement ele = webEleList.get(i);
					if (ele.isDisplayed()) {
						activityCountMap.put(activityType, i + 1);
						activityFound = true;
						
						wait.until(ExpectedConditions.visibilityOf(ele));
						try {
							wait.until(ExpectedConditions.visibilityOf(ele));
							ele.click();
						} catch (ElementClickInterceptedException e) {
							((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", ele);
							ele.click();
						}
						String[] str = new String[] { "d", "e", "f", "o", "r" };
						for (int j = 0; j < str.length; j++) {
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
							ele.sendKeys(str[j]);
						}
					}
				}
				if (activityFound) {
					nextScreen();
				} else {
					frameclose();
				}
			} else {
				frameclose();
			}
		}

		// Code for Label and object
		else if ("labelAndObject".equalsIgnoreCase(activityType)) {
			System.out.println("labelAndObject");
			String[] labelObjectXpath = xpath.split(":");
			System.out.println(xpath);
			System.out.println(labelObjectXpath[0] + " -------- " + labelObjectXpath[1]);
			webEleList = driver.findElements(By.xpath(labelObjectXpath[1]));
			List<WebElement> tokens = driver.findElements(By.xpath(labelObjectXpath[0]));
			System.out.println("elements size:: " + webEleList.size());
			// zoom out the web page to get all the elements in visible range
//			zoomOutPage();
			Thread.sleep(3000);
			frame();
			Actions action = new Actions(driver);
			if (webEleList != null && webEleList.size() > 0) {
				int j = 0;
				Integer sourceIndex = activityCountMap.get(activityType + ".source");
				for (int i = 0; i < tokens.size(); i++) {
					WebElement source = tokens.get(i);
					if (source.isDisplayed()) {
						activityCountMap.put(activityType + ".source", sourceIndex + 1);
						Integer destIndex = activityCountMap.get(activityType + ".dest");
						WebElement dest = webEleList.get(j);
						if (dest.isDisplayed()) {
							activityCountMap.put(activityType + ".dest", destIndex + 1);
							wait.until(ExpectedConditions.elementToBeClickable(dest));
							((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", dest);
							action.dragAndDrop(source, dest).build().perform();
							activityFound = true;
							j++;
						}
					}
				}
				if (activityFound) {
					if (!nextScreen()) {
						activityFound = false;
					}
				} else {
					frameclose();
				}
			} else {
				frameclose();
			}
//			zoomInPage();
			Thread.sleep(3000);
			
			if(!activityFound) {
//				zoomOutPage();
				frame();
				if (webEleList != null && webEleList.size() > 0) {
					Integer sourceIndex = activityCountMap.get(activityType + ".source");
					// Integer destIndex = activityCountMap.get(activityType + ".dest");
					int j = 0;
					for (int i = 0; i < webEleList.size(); i++) {
						WebElement ele = webEleList.get(i);
						if (ele.isDisplayed()) {
							activityCountMap.put(activityType + ".source", i + 1);
							activityFound = true;
							
							System.out.println("target index :: " + i);
							int t = tokens.size();
							System.out.println("size::token::" + t);
							boolean actperf = false;
							while (j < t) {
								WebElement source = tokens.get(j);
								if (source.isDisplayed()) {
									try {
										wait.until(ExpectedConditions.elementToBeClickable(source));
										source.click();
										System.out.println("source clicked");
	
									} catch (Exception ex) {
	
										((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
												source);
										source.click();
										System.out.println("source in catch clicked");
	
									}
									Thread.sleep(1000);
									try {
										wait.until(ExpectedConditions.visibilityOf(ele));
										ele.click();
										System.out.println("target clicked");
									} catch (Exception ex) {
										((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", ele);
	//											wait.until(ExpectedConditions.elementToBeClickable(ele));
										Thread.sleep(1000);
	//											((JavascriptExecutor) driver).executeScript("a`guments[0].click();", ele);
										ele.click();
										System.out.println("target in catch clicked");
									}
									System.out.println("source index :: " + j);
									actperf = true;
								} else {
									j++;
									System.out.println("source hidden:: " + j);
								}
								if (actperf) {
									j++;
									break;
								}
							}
						} else {
							System.out.println("target hidden:: " + i);
							continue;
						}
					}
					if (activityFound) {
						nextScreen();
					} else {
						frameclose();
					}
				} else {
					frameclose();
				}
				// zoom in back to 100% after activity completed
//				zoomInPage();
			}
		}
		// Code for Dropdown
		else if ("dropdown".equalsIgnoreCase(activityType)) {
			System.out.println("Drop down");
			String[] dropDownXpath = xpath.split(":");
			System.out.println(xpath);
			System.out.println(dropDownXpath[0] + " -------- " + dropDownXpath[1]);
			
			frame();
			webEleList = driver.findElements(By.xpath(dropDownXpath[0]));
			System.out.println("elements size:: " + webEleList.size());
			if (webEleList != null && webEleList.size() > 0) {
				Integer sourceIndex = activityCountMap.get(activityType + ".source");
				// Integer destIndex = activityCountMap.get(activityType + ".dest");
				System.out.println("source index:: " + sourceIndex);
				for (int i = 0; i < webEleList.size(); i++) {
					WebElement ele = webEleList.get(i);
					if (ele.isDisplayed()) {
						activityCountMap.put(activityType + ".source", i + 1);
						activityFound = true;
						
						try {
							ele.click();
						} catch (ElementClickInterceptedException e) {
							//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", ele);
							ele.click();
						}
						List<WebElement> tokens = ele.findElements(By.xpath(dropDownXpath[1]));
						int t = tokens.size();
						System.out.println("size::token::" + t);
						
						wait.until(ExpectedConditions.elementToBeClickable(tokens.get(0)));
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", tokens.get(0));
						tokens.get(0).click();
					} else {
						continue;
					}
				}
				if (activityFound) {
					nextScreen();
				} else {
					frameclose();
				}
			} else {
				frameclose();
			}
		}
		// Code for Drag and drop source click
		else if ("dragndropsourceclick".equalsIgnoreCase(activityType)) {
			System.out.println("DragNDropSourceClick");
			String[] dndXpath = xpath.split(":");
			System.out.println(xpath);
			System.out.println(dndXpath[0] + " -------- " + dndXpath[1]);
			
			frame();
			
			
			
			
			webEleList = driver.findElements(By.xpath(dndXpath[1]));
			int destEleSize = webEleList.size();
			
			
			
			
			if (destEleSize <= 0) {
				webEleList = driver.findElements(By.xpath(dndXpath[2]));
				destEleSize = webEleList.size();
			}
			System.out.println("elements size:: " + webEleList.size());
			if (webEleList != null && destEleSize > 0) {
				Integer sourceIndex = activityCountMap.get(activityType + ".source");
				Integer destIndex = activityCountMap.get(activityType + ".dest");
				System.out.println("source index:: " + sourceIndex);
				System.out.println("dest index:: " + destIndex);
				
				for (int i = 0; i < webEleList.size(); i++) {
//					WebElement dest = webEleList.get(i);
//					if (dest.isDisplayed()) {
//						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", dest);
					activityCountMap.put(activityType + ".dest", destIndex + 1);
					List<WebElement> tokens = driver.findElements(By.xpath(dndXpath[0]));
					System.out.println("tokens size:: " + tokens.size());
					for (int j = 0; j < tokens.size(); j++) {
//					for (int j = sourceIndex; j <= i; j++ ) {
						WebElement token = tokens.get(j);
						if (token.isDisplayed()) {
							activityCountMap.put(activityType + ".source", sourceIndex + 1);
							wait.until(ExpectedConditions.elementToBeClickable(token));
							try {
								token.click();
							} catch (ElementClickInterceptedException e) {
								((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", token);
								token.click();
							}
							/*
							 * if (t == 1) { break; } else { System.out.println(dndXpath[1]);
							 * wait.until(ExpectedConditions.elementToBeClickable(dest)); dest.click(); }
							 * t--;
							 */
							
							activityFound = true;
							
						} else {
							continue;
						}
					}
					CrossBrowserTest mqa = new CrossBrowserTest();
		//			mqa.screencheck();
//					} else {
//						continue;
//					}
				}
				if (activityFound) {
					if (!nextScreen()) {
						activityFound = false;
					}
				} else {
					frameclose();
				}
			} else {
				frameclose();
			}
		}
		// Code for Drag and drop (hold and drop)
		else if ("dragndrop".equalsIgnoreCase(activityType)) {
			System.out.println("DragNDrop");
			String[] dndXpath = xpath.split(":");
			System.out.println(xpath);
			System.out.println(dndXpath[0] + " -------- " + dndXpath[1]);
			
			frame();
			List<WebElement> tokens = driver.findElements(By.xpath(dndXpath[0]));
			webEleList = driver.findElements(By.xpath(dndXpath[1]));
			System.out.println("size::web elements::" + webEleList.size());
			
			Actions action = new Actions(driver);
			if (webEleList != null && webEleList.size() > 0) {
				Integer sourceIndex = activityCountMap.get(activityType + ".source");
				
				for (int i = sourceIndex; i < tokens.size(); i++) {
					WebElement source = tokens.get(i);
					if (source.isDisplayed()) {
						activityCountMap.put(activityType + ".source", sourceIndex + 1);
						Integer destIndex = activityCountMap.get(activityType + ".dest");
						WebElement dest = webEleList.get(destIndex);
						if (dest.isDisplayed()) {
							activityCountMap.put(activityType + ".dest", destIndex + 1);
							wait.until(ExpectedConditions.elementToBeClickable(dest));
							((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", dest);
							action.dragAndDrop(source, dest).build().perform();
							activityFound = true;
						}
					}
				}
				if (activityFound) {
					if (!nextScreen()) {
						activityFound = false;
					}
				} else {
					frameclose();
				}
			} else {
				frameclose();
			}
		}

		// Code for drag and drop source and target click
		else if ("dndsourcetargetclick".equalsIgnoreCase(activityType)) {
			System.out.println("DragNDropSourceTargetClick");
			String[] dndXpath = xpath.split(":");
			System.out.println(xpath);
			System.out.println(dndXpath[0] + " -------- " + dndXpath[1]);
			
			frame();
			List<WebElement> tokens = driver.findElements(By.xpath(dndXpath[0]));
			webEleList = driver.findElements(By.xpath(dndXpath[1]));
			
			int destEleSize = webEleList.size();
			if (destEleSize <= 0) {
				webEleList = driver.findElements(By.xpath(dndXpath[2]));
				destEleSize = webEleList.size();
			}
			System.out.println("size::web elements::" + destEleSize);
			System.out.println("tokens size::" + tokens.size());
			if (webEleList != null && destEleSize > 0) {
				
//				zoomOutPage();
				Integer sourceIndex = activityCountMap.get(activityType + ".source");
				// Integer destIndex = activityCountMap.get(activityType + ".dest");
				int j = 0;
				for (int i = 0; i < webEleList.size(); i++) {
					WebElement ele = webEleList.get(i);
					if (ele.isDisplayed()) {
						activityCountMap.put(activityType + ".source", i + 1);
						activityFound = true;
						System.out.println("target index :: " + i);
						int t = tokens.size();
						System.out.println("size::token::" + t);
						boolean actperf = false;
						while (j < t) {
							WebElement source = tokens.get(j);
							if (source.isDisplayed()) {
								try {
									wait.until(ExpectedConditions.elementToBeClickable(source));
									source.click();
									System.out.println("source clicked");
								} catch (Exception ex) {
									((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
											source);
									source.click();
									System.out.println("source in catch clicked");
								}
								Thread.sleep(1000);
								try {
									wait.until(ExpectedConditions.visibilityOf(ele));
									ele.click();
									System.out.println("target clicked");
								} catch (Exception ex) {
									((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", ele);
//									wait.until(ExpectedConditions.elementToBeClickable(ele));
									Thread.sleep(1000);
//									((JavascriptExecutor) driver).executeScript("a`guments[0].click();", ele);
									ele.click();
									System.out.println("target in catch clicked");
								}
								System.out.println("source index :: " + j);
								actperf = true;
							} else {
								j++;
								System.out.println("source hidden:: " + j);
							}
							if (actperf) {
								j++;
								break;
							}
						}
					} else {
						System.out.println("target hidden:: " + i);
						continue;
					}
				}
				if (activityFound) {
					nextScreen();
				} else {
					frameclose();
				}
//				zoomInPage();
			} else {
				frameclose();
			}
		}

		else if ("mcmultiple".equalsIgnoreCase(activityType)) {
			System.out.println("Multiple choice multiple");
					 
			frame();
			
			webEleList = driver.findElements(By.xpath(xpath));
			if (webEleList != null && webEleList.size() > 0) {
				Integer startIndex = activityCountMap.get(activityType);
				for (int i = startIndex; i < webEleList.size(); i++) {
					WebElement ele = webEleList.get(i);
					
					if (ele.isDisplayed()) {
						activityCountMap.put(activityType, i + 1);
						activityFound = true;
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", ele);
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
					}
				}
				if (activityFound) {
					nextScreen();
				} else {
					frameclose();
				}
			} else {
				frameclose();
			}
		}

		// Code for Colouring activity
		else if ("coloring".equalsIgnoreCase(activityType)) {
			System.out.println("Match Color");
			frame();
			
			webEleList = driver.findElements(By.xpath(xpath));
			
			if (webEleList != null && webEleList.size() > 0) {
				
//				zoomOutPage();
				try {
					Integer startIndex = activityCountMap.get(activityType);
					for (int i = 0; i < webEleList.size(); i++) {
						WebElement ele = webEleList.get(i);
						activityCountMap.put(activityType, i + 1);
						
						// ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
						// ele.click();
						// ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
						
						Actions act = new Actions(driver);
						act.moveToElement(ele).click(ele).build().perform();
						activityFound = true;
					}
					if (activityFound) {
						nextScreen();
					} else {
						frameclose();
					}
				} catch (Exception ex) {
					System.out.println(ex);
				}
//				zoomInPage();
			} else {
				frameclose();
			}
			// Thread.sleep(30000);
			// activityFound = false;
		} else if ("presentation".equalsIgnoreCase(activityType)) {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
				WebElement nextActivityButton = driver.findElement(By.xpath(xpath));
				
				if (nextActivityButton.isDisplayed()) {
					
					nextActivityButton.click();
					System.out.println("NEXT ACTIVITY button found and clicked");
					activityFound = true;
				} else {
					System.out.println("NEXT ACTIVITY button not found");
				}
				if (activityFound) {
					System.out.println("Activity found");
				}
			} catch (Exception ex) {
				System.out.println(ex);
			}
		} else if ("pres".equalsIgnoreCase(activityType)) {
			boolean nextFound = validatenext();
			
			if (nextFound) {
				activityFound = true;
				
			}
			if (activityFound) {
				System.out.println("Activity found");
			}
		} else if ("exscreen".equalsIgnoreCase(activityType)) {
			boolean checkFound = validatecheck();
		
			if (checkFound) {
				boolean nextFound = validatenext();
				if (nextFound) {
					activityFound = true;
					System.out.println("Example screen found and clicked");
				} else {
					System.out.println("Next button not found");
				}
			} else {
				System.out.println("Check button not found");
			}
		}
		else if ("ListenRecord".equalsIgnoreCase(activityType))
		{
 
			 System.out.println("Listen & Record duplicate");
			String[] LRXpath = xpath.split(":");
			System.out.println(xpath);
			System.out.println(LRXpath[0] + " -------- " + LRXpath[1]+ " -------- " + LRXpath[2]);
			
			 frame();
			webEleList = driver.findElements(By.xpath(LRXpath[0]));
			if (webEleList != null && webEleList.size() > 0)
			{
				
				Integer startIndex = activityCountMap.get(activityType);
			
				for (int i = startIndex; i < webEleList.size(); i++)
				{
				 	WebElement ele = webEleList.get(i);
					 if (ele.isDisplayed())
					{
						activityCountMap.put(activityType+ ".source", i + 1);
						 activityFound = true;
						
						 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", ele);
						
						 ele.click();
						
						 Scanner sc = new Scanner(System.in);
					        System.out.println("Please record the voice and click a number to proceed further");
					        int a = sc.nextInt();
						
					}
				}
				
				webEleList = driver.findElements(By.xpath(LRXpath[1]));
				
				Integer Stopbutton = activityCountMap.get(activityType);
				for (int j = Stopbutton; j < webEleList.size(); j++)
				{
				 	WebElement ele = webEleList.get(j);
					 if (ele.isDisplayed())
					{
						 activityCountMap.put(activityType+ ".source", j + 1);
						 activityFound = true;
						
						 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", ele);
						
						 ele.click();
						 System.out.println("Stop button is clicked");
					}
				}
				
						
				webEleList = driver.findElements(By.xpath(LRXpath[2]));
				
				Integer play = activityCountMap.get(activityType);
				for (int k = play; k < webEleList.size(); k++)
				{
					WebElement ele = webEleList.get(k);
					 if (ele.isDisplayed())
					{
						 activityCountMap.put(activityType+ ".source", k + 0);
						 activityFound = true;
						
						 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", ele);
						
						 ele.click();
						
					}
						
				}
			
						
					
				
						 if (activityFound) {
							 frameclose();
							 CommonUtils.validatenext();
						// nextScreen();
						 }
						 }
		 }

		/*
		 * if(driver.findElement(By.xpath("("+ xpath + ")[1]")).isDisplayed()) {
		 * System.out.println("multiple choice single"); frame(); webEleList =
		 * driver.findElements(By.xpath(xpath)); // WebDriverWait wait = new
		 * WebDriverWait(driver, Duration.ofSeconds(30)); for(WebElement ele :
		 * webEleList) { // wait.until(ExpectedConditions.elementToBeClickable(ele));
		 * if(ele.isDisplayed()) {
		 * ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",
		 * ele); ele.click(); } } nextScreen(); } else {
		 * System.out.println("other activity"); }
		 */

//		ExpectedConditions.presenceOfElementLocated(By.xpath(xpath));
		return activityFound;
	}


public static boolean performActivityTesting(Properties props, String activity) throws InterruptedException, IOException {
	boolean activityFound = false;
	System.out.println("Activity to be tested:: " + activity);

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	List<WebElement> webEleList = new ArrayList<WebElement>();
	String activityType, xpath;
	List<WebElement> tokens = new ArrayList<WebElement>();
	String[] dndXpath = null;
	boolean nextFound = false;
	
	Thread.sleep(5000);
	//taking screenshot based on the activity launched
	TakesScreenshot screenshot = (TakesScreenshot)driver;
	File ssource = screenshot.getScreenshotAs(OutputType.FILE);
	File Target = new File(".\\SeleniumScreenshots\\"+activity+timestamp()+".png");
	FileUtils.copyFile(ssource, Target);
	System.out.println("Screenshot is captured");
	
	
	screencheck();
	
	switch (activity) {
	case "Drag & Drop (sentence)_RDP":
	case "Drag & Drop (categorisation)_RDP":
	case "Drag & Drop (basic)_RDP":
//		Drag and Drop Source Click
		System.out.println("DragNDropSourceClick");
		activityType = "dragndropsourceclick";
		xpath = props.getProperty("act." + activityType);
		dndXpath = xpath.split(":");
		System.out.println(xpath);
		System.out.println(dndXpath[0] + " -------- " + dndXpath[1]);
		frame();
		

		webEleList = driver.findElements(By.xpath(dndXpath[1]));
		System.out.println("elements size:: " + webEleList.size());
		if (webEleList != null && webEleList.size() > 0) {
			Integer sourceIndex = activityCountMap.get(activityType + ".source");
			Integer destIndex = activityCountMap.get(activityType + ".dest");
			System.out.println("source index:: " + sourceIndex);
			System.out.println("dest index:: " + destIndex);
			for (int i = 0; i < webEleList.size(); i++) {
//				WebElement dest = webEleList.get(i);
//				if (dest.isDisplayed()) {
//					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", dest);
				activityCountMap.put(activityType + ".dest", destIndex + 1);
				tokens = driver.findElements(By.xpath(dndXpath[0]));
				System.out.println("tokens size:: " + tokens.size());
				for (int j = 0; j < tokens.size(); j++) {
//				for (int j = sourceIndex; j <= i; j++ ) {
					WebElement token = tokens.get(j);
					if (token.isDisplayed()) {
						activityCountMap.put(activityType + ".source", sourceIndex + 1);
						wait.until(ExpectedConditions.elementToBeClickable(token));
						try {
							token.click();
						} catch (ElementClickInterceptedException e) {
							((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", token);
							token.click();
						}
						
						/*
						 * if (t == 1) { break; } else { System.out.println(dndXpath[1]);
						 * wait.until(ExpectedConditions.elementToBeClickable(dest)); dest.click(); }
						 * t--;
						 */
						activityFound = true;
					} else {
						continue;
					}
				}
			//				} else {
//					continue;
//				}
			}
			if (activityFound) {
				if (!nextScreen()) {
					activityFound = false;
				}
			} else {
				frameclose();
			}
		} else {
			frameclose();
		}
		
		if(!activityFound ) {
//		Drag and Drop
			System.out.println("DragNDrop");
			activityType = "dragndrop";
			xpath = props.getProperty("act." + activityType);
			dndXpath = xpath.split(":");
			System.out.println(xpath);
			System.out.println(dndXpath[0] + " -------- " + dndXpath[1]);
			frame();
			tokens = driver.findElements(By.xpath(dndXpath[0]));
			webEleList = driver.findElements(By.xpath(dndXpath[1]));
			System.out.println("size::web elements::" + webEleList.size());
			Actions action = new Actions(driver);
			if (webEleList != null && webEleList.size() > 0) {
				Integer sourceIndex = activityCountMap.get(activityType + ".source");
				for (int i = sourceIndex; i < tokens.size(); i++) {
					WebElement source = tokens.get(i);
					if (source.isDisplayed()) {
						activityCountMap.put(activityType + ".source", sourceIndex + 1);
						Integer destIndex = activityCountMap.get(activityType + ".dest");
						WebElement dest = webEleList.get(destIndex);
						if (dest.isDisplayed()) {
							activityCountMap.put(activityType + ".dest", destIndex + 1);
							wait.until(ExpectedConditions.elementToBeClickable(dest));
							((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", dest);
							action.dragAndDrop(source, dest).build().perform();
							activityFound = true;
						}
					}
				}
				if (activityFound) {
					if (!nextScreen()) {
						activityFound = false;
					}
				} else {
					frameclose();
				}
			} else {
				frameclose();
			}
		}
		
		if(!activityFound) {
//		Drag and Drop Target Click
			System.out.println("DragNDropSourceTargetClick");
			activityType = "dndsourcetargetclick";
			xpath = props.getProperty("act." + activityType);
			dndXpath = xpath.split(":");
			System.out.println(xpath);
			System.out.println(dndXpath[0] + " -------- " + dndXpath[1]);
			frame();
			tokens = driver.findElements(By.xpath(dndXpath[0]));
			webEleList = driver.findElements(By.xpath(dndXpath[1]));
			int destEleSize = webEleList.size();
			if (destEleSize <= 0) {
				webEleList = driver.findElements(By.xpath(dndXpath[2]));
				destEleSize = webEleList.size();
			}
			System.out.println("size::web elements::" + destEleSize);
			System.out.println("tokens size::" + tokens.size());
			if (webEleList != null && destEleSize > 0) {
//				zoomOutPage();
				Thread.sleep(3000);
				Integer sourceIndex = activityCountMap.get(activityType + ".source");
				// Integer destIndex = activityCountMap.get(activityType + ".dest");
				int j = 0;
				for (int i = 0; i < webEleList.size(); i++) {
					WebElement ele = webEleList.get(i);
					if (ele.isDisplayed()) {
						activityCountMap.put(activityType + ".source", i + 1);
						activityFound = true;
						System.out.println("target index :: " + i);
						int t = tokens.size();
						System.out.println("size::token::" + t);
						boolean actperf = false;
						while (j < t) {
							WebElement source = tokens.get(j);
							if (source.isDisplayed()) {
								try {
									wait.until(ExpectedConditions.elementToBeClickable(source));
									source.click();
									System.out.println("source clicked");
								} catch (Exception ex) {
									((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
											source);
									source.click();
									System.out.println("source in catch clicked");
								}
								Thread.sleep(1000);
								try {
									wait.until(ExpectedConditions.visibilityOf(ele));
									ele.click();
									System.out.println("target clicked");
								} catch (Exception ex) {
									((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", ele);
//									wait.until(ExpectedConditions.elementToBeClickable(ele));
									Thread.sleep(1000);
//									((JavascriptExecutor) driver).executeScript("a`guments[0].click();", ele);
									ele.click();
									System.out.println("target in catch clicked");
								}
								System.out.println("source index :: " + j);
								actperf = true;
							} else {
								j++;
								System.out.println("source hidden:: " + j);
							}
							if (actperf) {
								j++;
								break;
							}
						}
					} else {
						System.out.println("target hidden:: " + i);
						continue;
					}
				}
				if (activityFound) {
					nextScreen();
				} else {
					frameclose();
				}
//				zoomInPage();
				Thread.sleep(3000);
			} else {
				frameclose();
			}
		}
		break;
		
	case "Dropdown_RDP":
		System.out.println("Drop down");
		activityType = "dropdown";
		xpath = props.getProperty("act." + activityType);
		String[] dropDownXpath = xpath.split(":");
		System.out.println(xpath);
		System.out.println(dropDownXpath[0] + " -------- " + dropDownXpath[1]);
		frame();
		
		
		
		//dropdowntestcases();
		
		
		
		webEleList = driver.findElements(By.xpath(dropDownXpath[0]));
		System.out.println("elements size:: " + webEleList.size());
		
		
		if (webEleList != null && webEleList.size() > 0) {
			
			//zoomOutPage();
			Integer sourceIndex = activityCountMap.get(activityType + ".source");
			// Integer destIndex = activityCountMap.get(activityType + ".dest");
			System.out.println("source index:: " + sourceIndex);
			for (int i = 0; i < webEleList.size(); i++) {
				WebElement ele = webEleList.get(i);
				if (ele.isDisplayed()) {
					activityCountMap.put(activityType + ".source", i + 1);
					activityFound = true;
					try {
						ele.click();
					} catch (ElementClickInterceptedException e) {
						((JavascriptExecutor) driver).executeScript("arguments[1].scrollIntoView();", ele);
						ele.click();
					}
					tokens = ele.findElements(By.xpath(dropDownXpath[1]));
					int t = tokens.size();
					System.out.println("size::token::" + t);
					
					
					
					wait.until(ExpectedConditions.elementToBeClickable(tokens.get(0)));
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", tokens.get(0));
					//tokens.get(0).click();
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", tokens.get(0));
					
				} else {
					continue;
				}
			}
			if (activityFound) {
							
				nextScreen();
			} else {
				frameclose();
			}
		} else {
			frameclose();
		}
		break;
		
	case "Label an object (Drag)_RDP":
		System.out.println("labelAndObject");
		activityType = "labelAndObject";
		xpath = props.getProperty("act." + activityType);
		String[] labelObjectXpath = xpath.split(":");
		System.out.println(xpath);
		System.out.println(labelObjectXpath[0] + " -------- " + labelObjectXpath[1]);
		// zoom out the web page to get all the elements in visible range
//		zoomOutPage();
		Thread.sleep(3000);
		frame();
		tokens = driver.findElements(By.xpath(labelObjectXpath[0]));
		webEleList = driver.findElements(By.xpath(labelObjectXpath[1]));
		int destEleSize = webEleList.size();
		if (destEleSize <= 0) {
			webEleList = driver.findElements(By.xpath(labelObjectXpath[2]));
			destEleSize = webEleList.size();
		}
		System.out.println("size::web elements::" + webEleList.size());
		Actions action = new Actions(driver);
		if (webEleList != null && webEleList.size() > 0) {
			System.out.println("Size printed in a loop");
			int j = 0;
			Integer sourceIndex = activityCountMap.get(activityType + ".source");
			for (int i = 0; i < tokens.size(); i++) {
				System.out.println("Size tokens in a loop");
				WebElement source = tokens.get(i);
				if (source.isDisplayed()) {
					System.out.println("Source displayed");
					//activityCountMap.put(activityType + ".source", sourceIndex + 1);
					Integer destIndex = activityCountMap.get(activityType + ".dest");
					WebElement dest = webEleList.get(j);
					System.out.println("Source selected ");
					if (dest.isDisplayed()) {
						System.out.println("Destination loop displayed");
						//activityCountMap.put(activityType + ".dest", destIndex + 1);
						wait.until(ExpectedConditions.elementToBeClickable(dest));
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", dest);
						System.out.println("Scrolled");
						action.dragAndDrop(source, dest).build().perform();
						activityFound = true;
						j++;
					}
				}
			}
			if (activityFound) {
				if (!nextScreen()) {
					activityFound = false;
				}
			} else {
				frameclose();
			}
		} else {
			frameclose();
		}
//		zoomInPage();
		Thread.sleep(3000);
		
//		alternate way
		if(!activityFound) {
			// zoom out the web page to get all the elements in visible range
//			zoomOutPage();
			Thread.sleep(3000);
			if (webEleList != null && webEleList.size() > 0) {
				Integer sourceIndex = activityCountMap.get(activityType + ".source");
				// Integer destIndex = activityCountMap.get(activityType + ".dest");
				int j = 0;
				for (int i = 0; i < webEleList.size(); i++) {
					WebElement ele = webEleList.get(i);
					if (ele.isDisplayed()) {
						activityCountMap.put(activityType + ".source", i + 1);
						activityFound = true;
						System.out.println("target index :: " + i);
						int t = tokens.size();
						System.out.println("size::token::" + t);
						boolean actperf = false;
						while (j < t) {
							WebElement source = tokens.get(j);
							if (source.isDisplayed()) {
								try {
									wait.until(ExpectedConditions.elementToBeClickable(source));
									source.click();
									System.out.println("source clicked");
 
								} catch (Exception ex) {
 
									((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
											source);
									source.click();
									System.out.println("source in catch clicked");
 
								}
								Thread.sleep(1000);
								try {
									wait.until(ExpectedConditions.visibilityOf(ele));
									ele.click();
									System.out.println("target clicked");
								} catch (Exception ex) {
									((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", ele);
//											wait.until(ExpectedConditions.elementToBeClickable(ele));
									Thread.sleep(1000);
//											((JavascriptExecutor) driver).executeScript("a`guments[0].click();", ele);
									ele.click();
									System.out.println("target in catch clicked");
								}
								System.out.println("source index :: " + j);
								actperf = true;
							} else {
								j++;
								System.out.println("source hidden:: " + j);
							}
							if (actperf) {
								j++;
								break;
							}
						}
					} else {
						System.out.println("target hidden:: " + i);
						continue;
					}
				}
				if (activityFound) {
					nextScreen();
				} else {
					frameclose();
				}
			} else {
				frameclose();
			}
			// zoom in back to 100% after activity completed
//			zoomInPage();
			Thread.sleep(3000);
		}
		break;
		
	case "Multiple Choice (multiple answers)_RDP":
		System.out.println("Multiple choice multiple");
		activityType = "mcmultiple";
		xpath = props.getProperty("act." + activityType);
		frame();
		Thread.sleep(5000);
		webEleList = driver.findElements(By.xpath(xpath));
		if (webEleList != null && webEleList.size() > 0) {
			//Integer startIndex = activityCountMap.get(activityType);
			Integer startIndex = activityCountMap.getOrDefault(activityType, 0);
			for (int i = startIndex; i < webEleList.size(); i++) {
				WebElement ele = webEleList.get(i);
				if (ele.isDisplayed()) {
					activityCountMap.put(activityType, i + 1);
					activityFound = true;
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", ele);
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
				}
			}
			if (activityFound) {
				nextScreen();
			} else {
				frameclose();
			}
		} else {
			frameclose();
		}
		break;
		
	case "Presentation_RDP":
		activityType = "presentation";
		xpath = props.getProperty("act." + activityType);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
			WebElement nextActivityButton = driver.findElement(By.xpath(xpath));
			if (nextActivityButton.isDisplayed()) {
				nextActivityButton.click();
				System.out.println("NEXT ACTIVITY button found and clicked");
				activityFound = true;
			} else {
				System.out.println("NEXT ACTIVITY button not found");
			}
			if (activityFound) {
				System.out.println("Activity found");
			}
		} catch (Exception ex) {
			System.out.println(ex);
			
			 WebElement nextButton = driver.findElement(By.xpath("//*[@title='Next']"));

	            // Check if the "Next" button is displayed
	            if (nextButton.isDisplayed()) {
	                System.out.println("Next option is visible.");
	                nextButton.click();
	                
		}
		
		

        break;
		
		}
		
		
		
		
	case "Colouring in_RDP":
		System.out.println("Match Color");
		activityType = "coloring";
		xpath = props.getProperty("act." + activityType);
		frame();
				
				
				
		webEleList = driver.findElements(By.xpath(xpath));
	//	Thread.sleep(4000);
		//int destsize = webEleList.size();
		System.out.println("elements size:: " + webEleList.size());
		
		
		
		
		
		if (webEleList != null && webEleList.size() > 0) {
			zoomOutPage();
			Thread.sleep(3000);
			try {
				Integer startIndex = activityCountMap.get(activityType);
				for (int i = 0; i < webEleList.size(); i++) {
					Thread.sleep(1000);
					WebElement ele = webEleList.get(i);
					activityCountMap.put(activityType, i + 1);
					
					
					
		//  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			//		ele.click();
				//	((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
					
								
							
					
					Actions act = new Actions(driver);
					act.moveToElement(ele).click().build().perform();
					//act.moveToElement(ele).click().build().perform();
					
					
					
					//*[@id="colouring1"]/svg/path[3]
					//*[@id="colouring1"]/svg/path[2]
					
					//act.moveToElement(ele).click().build().perform();
					//act.moveToElement(ele).click().build().perform();
					
					System.out.println("Element clicked:: " + i);
					Thread.sleep(1000);
					
					activityFound= true;
					
				}
				
				if (activityFound) {
					Thread.sleep(9000);
					
					frameclose();
					if (driver.findElement(By.xpath("//*[@title='Check']")).isDisplayed())
					{
						nextScreen();
					
					}
					else
					{
						frame();
						
						Actions builder = new Actions(driver); 
						builder.keyDown(Keys.SHIFT).sendKeys(Keys.TAB).click().build().perform();
						

						new Actions(driver).keyUp(Keys.SHIFT).build().perform();
				
						for (int i = 0; i < webEleList.size(); i++) {
							Thread.sleep(1000);
							WebElement ele = webEleList.get(i);
							activityCountMap.put(activityType, i + 1);
							
											
									
							
							Actions act = new Actions(driver);
							act.moveToElement(ele).click().build().perform();
							//act.moveToElement(ele).click().build().perform();
							
						
						//driver.findElement(By.xpath(xpath)).sendKeys(Keys.SHIFT, Keys.TAB, Keys.ENTER);
					}
				}
					
				} else {
					frameclose();
				}
			} catch (Exception ex) {
				System.out.println(ex);
			}
//			zoomInPage();
			Thread.sleep(3000);
		} else {
			frameclose();
		}
		break;
		
	case "Text entry_RDP":
		System.out.println("Text entry");
		activityType = "textentry";
		xpath = props.getProperty("act." + activityType);
		frame();
		webEleList = driver.findElements(By.xpath(xpath));
		if (webEleList != null && webEleList.size() > 0) {
			Integer startIndex = activityCountMap.get(activityType);
			for (int i = 0; i < webEleList.size(); i++) {
				WebElement ele = webEleList.get(i);
				if (ele.isDisplayed()) {
					activityCountMap.put(activityType, i + 1);
					activityFound = true;
					wait.until(ExpectedConditions.visibilityOf(ele));
					try {
						wait.until(ExpectedConditions.visibilityOf(ele));
						ele.click();
					} catch (ElementClickInterceptedException e) {
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", ele);
						ele.click();
					}
					String[] str = new String[] { "d", "e", "f", "o", "r" };
					for (int j = 0; j < str.length; j++) {
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
						ele.sendKeys(str[j]);
					}
				}
			}
			if (activityFound) {
				nextScreen();
			} else {
				frameclose();
			}
		} else {
			frameclose();
		}
		break;
		
	case "Multiple Choice (single answer)_RDP":
		System.out.println("multiple choice single");
		activityType = "mcsingle";
		xpath = props.getProperty("act." + activityType);
		frame();
		webEleList = driver.findElements(By.xpath(xpath));
		if (webEleList != null && webEleList.size() > 0) {
			Integer startIndex = activityCountMap.get(activityType);
			for (int i = startIndex; i < webEleList.size(); i++) {
				WebElement ele = webEleList.get(i);
				if (ele.isDisplayed()) {
					activityCountMap.put(activityType, i + 1);
					activityFound = true;
					wait.until(ExpectedConditions.elementToBeClickable(ele));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
//					ele.click();
				}
			}
			if (activityFound) {
				nextScreen();
			} else {
				frameclose();
			}
		} else {
			frameclose();
		}
		break;
		
	case "Listen & Record_RDP":
		System.out.println("Listen & Record duplicate");
		activityType = "ListenRecord";
		xpath = props.getProperty("act." + activityType);
		String[] LRXpath = xpath.split(":");
		System.out.println(xpath);
		System.out.println(LRXpath[0] + " -------- " + LRXpath[1] + " -------- " + LRXpath[2]);
		frame();
		webEleList = driver.findElements(By.xpath(LRXpath[0]));
		if (webEleList != null && webEleList.size() > 0) {
			Integer startIndex = activityCountMap.get(activityType);
			for (int i = startIndex; i < webEleList.size(); i++) {
				WebElement ele = webEleList.get(i);
				if (ele.isDisplayed()) {
					activityCountMap.put(activityType + ".source", i + 1);
					activityFound = true;
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", ele);
					ele.click();
					Scanner sc = new Scanner(System.in);
					System.out.println("Please record the voice and click a number to proceed further");
					int a = sc.nextInt();
				}
			}
			webEleList = driver.findElements(By.xpath(LRXpath[1]));
			Integer Stopbutton = activityCountMap.get(activityType);
			for (int j = Stopbutton; j < webEleList.size(); j++) {
				WebElement ele = webEleList.get(j);
				if (ele.isDisplayed()) {
					activityCountMap.put(activityType + ".source", j + 1);
					activityFound = true;

					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", ele);

					ele.click();
					System.out.println("Stop button is clicked");
				}
			}
			webEleList = driver.findElements(By.xpath(LRXpath[2]));
			Integer play = activityCountMap.get(activityType);
			for (int k = play; k < webEleList.size(); k++) {
				WebElement ele = webEleList.get(k);
				if (ele.isDisplayed()) {
					activityCountMap.put(activityType + ".source", k + 0);
					activityFound = true;
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", ele);
					ele.click();
					System.out.println("re-play button is clicked");
				}
			}
			if (activityFound) {
				frameclose();
				
	        
		        try {
		            // Try to find the "Next" button
		            WebElement nextButton = driver.findElement(By.xpath("//*[@title='Next']"));

		            // Check if the "Next" button is displayed
		            if (nextButton.isDisplayed()) {
		                System.out.println("Next option is visible.");
		                nextButton.click();
		            } else {
		            	   System.out.println(" Next option not found.");
			            
		            }
		        } catch (org.openqa.selenium.NoSuchElementException e) {
		        	 // If "Next" button is not displayed, try to find and click "NextActivity" button
	                System.out.println("Next option is not visible. Trying NextActivity.");
	                WebElement nextActivityButton = driver.findElement(By.xpath("//*[@class='nextActivityBtn btn']"));
	                nextActivityButton.click();
	                e.printStackTrace();
		        }
		        
				break;
			}
		}
		
		
	case "Pres":
		System.out.println("Pres");
		activityType = "pres";
		xpath = props.getProperty("act." + activityType);
		nextFound = validatenext();
		if (nextFound) {
			activityFound = true;
		}
		if (activityFound) {
			System.out.println("Activity found");
		}
		break;
		
	case "ExScreen":
		System.out.println("ExScreen");
		activityType = "exscreen";
		xpath = props.getProperty("act." + activityType);
		boolean checkFound = validatecheck();
		if (checkFound) {
			nextFound = validatenext();
			if (nextFound) {
				activityFound = true;
				System.out.println("Example screen found and clicked");
			} else {
				System.out.println("Next button not found");
			}
		} else {
			System.out.println("Check button not found");
		}
		break;

	default:
		break;
	}
	
	return activityFound;
}

private static void clickButtonifDisplayed(WebDriver driver, By xpath) {
	// TODO Auto-generated method stub
	
}





}

