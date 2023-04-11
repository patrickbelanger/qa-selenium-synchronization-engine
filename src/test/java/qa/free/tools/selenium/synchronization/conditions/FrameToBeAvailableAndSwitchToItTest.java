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
import org.openqa.selenium.WebElement;

import qa.free.tools.selenium.synchronization.SynchronizeBaseTest;
import qa.free.tools.selenium.synchronization.exceptions.NotImplementedException;

/**
 * @author pbelanger <1848500+patrickbelanger@users.noreply.github.com>
 */
class FrameToBeAvailableAndSwitchToItTest extends SynchronizeBaseTest {

	private FrameToBeAvailableAndSwitchToIt underTest;
	
	@BeforeEach
	public void setUp() {
		super.setUp();
		underTest = new FrameToBeAvailableAndSwitchToIt(getWebDriver());
	}
	
	@Test
	void frameToBeAvailableAndSwitchToIt_ableToSynchronizeAndSwitchFrameUsingBy() {
		getWebDriver().get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_button_test");
		setWebDriver(underTest.getWebDriverInstance(By.id("iframeResult")));
		WebElement webElement = getWebDriver().findElement(By.tagName("button"));
		Assertions.assertNotNull(webElement);
		Assertions.assertInstanceOf(WebElement.class, webElement);
		Assertions.assertTrue(webElement.isDisplayed());
	}
	
	@Test
	void frameToBeAvailableAndSwitchToIt_ableToSynchronizeAndSwitchFrameUsingInteger() {
		getWebDriver().get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_button_test");
		setWebDriver(underTest.getWebDriverInstance(0));
		WebElement webElement = new ElementToBeClickable(getWebDriver()).getWebElement(By.tagName("button"));
		Assertions.assertNotNull(webElement);
		Assertions.assertInstanceOf(WebElement.class, webElement);
		Assertions.assertTrue(webElement.isDisplayed());
	}
	
	@Test
	void frameToBeAvailableAndSwitchToIt_ableToSynchronizeAndSwitchFrameUsingString() {
		getWebDriver().get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_button_test");
		setWebDriver(underTest.getWebDriverInstance("iframeResult"));
		WebElement webElement = getWebDriver().findElement(By.tagName("button"));
		Assertions.assertNotNull(webElement);
		Assertions.assertInstanceOf(WebElement.class, webElement);
		Assertions.assertTrue(webElement.isDisplayed());
	}

	@Test
	void frameToBeAvailableAndSwitchToIt_ableToSynchronizeAndSwitchFrameUsingWebElement() {
		getWebDriver().get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_button_test");
		WebElement webElement = getWebDriver().findElement(By.id("iframeResult"));
		setWebDriver(underTest.getWebDriverInstance(webElement));
		webElement = getWebDriver().findElement(By.tagName("button"));
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
		By by = By.xpath("//html");
		Assertions.assertThrows(NotImplementedException.class, () -> {
			underTest.getWebElement(by);
		});
	}
	
	@Test
	void frameToBeAvailableAndSwitchToIt_anExceptionIsRaisedWhenAttemptingCallingSynchronizeWebElementsMethod() {
		By by = By.xpath("//html");
		Assertions.assertThrows(NotImplementedException.class, () -> {
			underTest.getWebElements(by);
		});
	}
	
	@AfterEach
	public void tearDown() {
		super.tearDown();
		underTest = null;
	}
}
