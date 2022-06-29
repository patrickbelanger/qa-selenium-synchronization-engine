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

package qa.free.tools.selenium.synchronization;

import java.util.List;

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

/**
 * @author pbelanger <1848500+patrickbelanger@users.noreply.github.com>
 */
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
	void synchronizeAlert_ableToSynchronizeAnAlertDialogAndInteractWithIt() throws ElementSynchronizationException {
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
		webDriver.get("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_table_intro");
		webDriver.switchTo().frame("iframeResult");
		List<WebElement> webElements = underTest
				.synchronizeWebElements(SynchronizationMethods.PRESENCE_OF_ALL_ELEMENTS_LOCATED,By.xpath("//table//td"));
		Assertions.assertNotNull(webElements);
		Assertions.assertInstanceOf(List.class, webElements);
		Assertions.assertTrue(webElements.size() > 1);
	}
	
	@Test
	void synchronizeWebElement_ableToSynchronizeAndGetAListOfElements() {
		webDriver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_button_test");
		webDriver.switchTo().frame("iframeResult");
		WebElement webElement = underTest
				.synchronizeWebElement(SynchronizationMethods.ELEMENT_TO_BE_CLICKABLE, By.tagName("button"));
		Assertions.assertNotNull(webElement);
		Assertions.assertInstanceOf(WebElement.class, webElement);
		webElement.click();
	}
	
	@Test
	void synchronizeWebElement_ableToSwitchFrame() {
		webDriver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_button_test");
		underTest.synchronizeWebDriverInstance(SynchronizationMethods.FRAME_TO_BE_AVAILABLE_AND_SWITCH_TO_IT, 
				"iframeResult");
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
