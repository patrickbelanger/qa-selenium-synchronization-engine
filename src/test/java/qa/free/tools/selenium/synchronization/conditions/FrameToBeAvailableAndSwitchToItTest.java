// Licensed to the Software Freedom Conservancy (SFC) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The SFC licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package qa.free.tools.selenium.synchronization.conditions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import qa.free.tools.selenium.synchronization.exceptions.NotImplementedException;

/**
 * @author pbelanger <1848500+patrickbelanger@users.noreply.github.com>
 */
class FrameToBeAvailableAndSwitchToItTest {

	private WebDriver webDriver;
	private FrameToBeAvailableAndSwitchToIt underTest;
	
	@BeforeEach
	public void setUp() {
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		webDriver = new ChromeDriver();
		underTest = new FrameToBeAvailableAndSwitchToIt(webDriver);
	}
	
	@Test
	void frameToBeAvailableAndSwitchToIt_ableToSynchronizeAndSwitchFrameUsingBy() {
		webDriver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_button_test");
		webDriver = underTest.getWebDriverInstance(By.id("iframeResult"));
		WebElement webElement = webDriver.findElement(By.tagName("button"));
		Assertions.assertNotNull(webElement);
		Assertions.assertInstanceOf(WebElement.class, webElement);
		Assertions.assertTrue(webElement.isDisplayed());
	}
	
	@Test
	void frameToBeAvailableAndSwitchToIt_ableToSynchronizeAndSwitchFrameUsingInteger() {
		webDriver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_button_test");
		webDriver = underTest.getWebDriverInstance(0);
		WebElement webElement = new ElementToBeClickable(webDriver).getWebElement(By.tagName("button"));
		Assertions.assertNotNull(webElement);
		Assertions.assertInstanceOf(WebElement.class, webElement);
		Assertions.assertTrue(webElement.isDisplayed());
	}
	
	@Test
	void frameToBeAvailableAndSwitchToIt_ableToSynchronizeAndSwitchFrameUsingString() {
		webDriver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_button_test");
		webDriver = underTest.getWebDriverInstance("iframeResult");
		WebElement webElement = webDriver.findElement(By.tagName("button"));
		Assertions.assertNotNull(webElement);
		Assertions.assertInstanceOf(WebElement.class, webElement);
		Assertions.assertTrue(webElement.isDisplayed());
	}

	@Test
	void frameToBeAvailableAndSwitchToIt_ableToSynchronizeAndSwitchFrameUsingWebElement() {
		webDriver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_button_test");
		WebElement webElement = webDriver.findElement(By.id("iframeResult"));
		webDriver = underTest.getWebDriverInstance(webElement);
		webElement = webDriver.findElement(By.tagName("button"));
		Assertions.assertNotNull(webElement);
		Assertions.assertInstanceOf(WebElement.class, webElement);
		Assertions.assertTrue(webElement.isDisplayed());
	}
	
	@Test
	void frameToBeAvailableAndSwitchToIt_anExceptionIsRaisedWhenAttemptingCallingSynchronizeAlertMethod() {
		Assertions.assertThrows(NotImplementedException.class, () -> {
			underTest.getAlert();
		});
	}
	
	@Test
	void frameToBeAvailableAndSwitchToIt_anExceptionIsRaisedWhenAttemptingCallingSynchronizeNestedWebElementMethod() {
		Assertions.assertThrows(NotImplementedException.class, () -> {
			underTest.getNestedWebElement(null, null);
		});
	}
	
	@Test
	void frameToBeAvailableAndSwitchToIt_anExceptionIsRaisedWhenAttemptingCallingSynchronizeNestedWebElementsMethod() {
		Assertions.assertThrows(NotImplementedException.class, () -> {
			underTest.getNestedWebElements(null, null);
		});
	}	
	
	@Test
	void frameToBeAvailableAndSwitchToIt_anExceptionIsRaisedWhenAttemptingCallingSynchronizeWebElementMethod() {
		Assertions.assertThrows(NotImplementedException.class, () -> {
			underTest.getWebElement(null);
		});
	}
	
	@Test
	void frameToBeAvailableAndSwitchToIt_anExceptionIsRaisedWhenAttemptingCallingSynchronizeWebElementsMethod() {
		Assertions.assertThrows(NotImplementedException.class, () -> {
			underTest.getWebElements(null);
		});
	}
	
	@AfterEach
	public void tearDown() {
		webDriver.quit();
		underTest = null;
	}
}
