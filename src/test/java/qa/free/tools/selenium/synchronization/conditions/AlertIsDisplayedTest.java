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
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import qa.free.tools.selenium.synchronization.exceptions.NotImplementedException;

/**
 * @author pbelanger <1848500+patrickbelanger@users.noreply.github.com>
 */
class AlertIsDisplayedTest {

	private WebDriver webDriver;
	private AlertIsPresent underTest;
	
	@BeforeEach
	public void setUp() {
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		webDriver = new ChromeDriver();
		underTest = new AlertIsPresent(webDriver);
	}
	
	@Test
	void alertIsPresent_ableToSynchronizeAnAlertDialogAndInteractWithIt() {
		webDriver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_alert");
		webDriver.switchTo().frame("iframeResult");
		webDriver.findElement(By.tagName("button")).click();
		Assertions.assertNotNull(underTest.getAlert());
		Assertions.assertInstanceOf(Alert.class, underTest.getAlert());
		Assertions.assertEquals("Hello! I am an alert box!", underTest.getAlert().getText());
		underTest.getAlert().dismiss();
	}

	@Test
	void alertIsPresent_anExceptionIsRaisedWhenAttemptingCallingSynchronizeNestedWebElementMethod() {
		Assertions.assertThrows(NotImplementedException.class, () -> {
			underTest.getNestedWebElement(null, null);
		});
	}
	
	@Test
	void alertIsPresent_anExceptionIsRaisedWhenAttemptingCallingSynchronizeNestedWebElementsMethod() {
		Assertions.assertThrows(NotImplementedException.class, () -> {
			underTest.getNestedWebElements(null, null);
		});
	}	
	
	@Test
	void alertIsPresent_anExceptionIsRaisedWhenAttemptingCallingSynchronizeWebDriverInstanceMethod() {
		Assertions.assertThrows(NotImplementedException.class, () -> {
			underTest.getWebDriverInstance(null);
		});	
	}
	
	@Test
	void alertIsPresent_anExceptionIsRaisedWhenAttemptingCallingSynchronizeWebElementMethod() {
		Assertions.assertThrows(NotImplementedException.class, () -> {
			underTest.getWebElement(null);
		});	
	}
	
	@Test
	void alertIsPresent_anExceptionIsRaisedWhenAttemptingCallingSynchronizeWebElementsMethod() {
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
