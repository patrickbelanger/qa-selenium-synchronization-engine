package qa.free.tools.selenium.synchronization;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import qa.free.tools.selenium.synchronization.exceptions.ElementSynchronizationException;

class SynchronizeTest {
	
	private WebDriver webDriver;
	private Synchronize underTest;
	
	@BeforeEach
	public void setUp() {
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		webDriver = new ChromeDriver();
		underTest = new Synchronize(webDriver);
	}
	
	@Test
	void synchronizeAlert_ableToSynchronizeAnAlertDialogAndInteractWithIt() {
		webDriver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_alert");
		webDriver.switchTo().frame("iframeResult");
		webDriver.findElement(By.tagName("button")).click();
		Assertions.assertNotNull(underTest.synchronizeAlert());
		Assertions.assertInstanceOf(Alert.class, underTest.synchronizeAlert());
		Assertions.assertEquals("Hello! I am an alert box!", underTest.synchronizeAlert().getText());
		underTest.synchronizeAlert().dismiss();
	}
	
	@Test
	void synchronizeAlert_anExceptionIsRaiseIfNoAlertBoxIsDisplayed() {
		Assertions.assertThrows(ElementSynchronizationException.class, () -> {
			underTest.synchronizeAlert();
		});
	}
	
	@Test
	void synchronizeWebElement_ableToSynchronizeAWebElementAndClickAButton() {
		webDriver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_button_test");
		webDriver.switchTo().frame("iframeResult");
		WebElement webElement = underTest
				.synchronizeWebElement(SynchronizationMethods.ELEMENT_TO_BE_CLICKABLE, By.tagName("button"));
		Assertions.assertNotNull(webElement);
		Assertions.assertInstanceOf(WebElement.class, webElement);
		webElement.click();
	}
	
	@Test
	void synchronizeWebElement_unableToSynchronizeAWebElementBecauseTheButtonIsDisabled() {
		webDriver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_button_disabled");
		webDriver.switchTo().frame("iframeResult");
		Assertions.assertThrows(Exception.class, () -> {
			underTest.synchronizeWebElement(SynchronizationMethods.ELEMENT_TO_BE_CLICKABLE, By.tagName("button"));
		});
	}
	
	@AfterEach
	public void tearDown() {
		webDriver.quit();
		underTest = null;
	}
	
}
