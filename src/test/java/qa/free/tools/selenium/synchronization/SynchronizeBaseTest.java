package qa.free.tools.selenium.synchronization;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import lombok.Data;

@Data
public abstract class SynchronizeBaseTest {

	private WebDriver webDriver;
	
	@BeforeEach
	public void setUp() {
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		webDriver = new ChromeDriver();
	}
	
	@AfterEach
	public void tearDown() {
		webDriver.quit();
	}
	
}
