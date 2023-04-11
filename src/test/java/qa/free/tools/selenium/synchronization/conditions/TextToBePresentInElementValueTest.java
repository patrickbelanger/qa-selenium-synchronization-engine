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
class TextToBePresentInElementValueTest extends SynchronizeBaseTest {

	private TextToBePresentInElementValue underTest;
	
	@BeforeEach
	public void setUp() {
		super.setUp();
		underTest = new TextToBePresentInElementValue(getWebDriver());
	}
	
	@Test
	void textToBePresentInElementValue_ableToSynchronizeAWebElementBasedOnProvidedValue_usingByLocator() {
		getWebDriver().get("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_input_attributes_value");
		getWebDriver().switchTo().frame("iframeResult");
		Assertions.assertTrue(underTest.isConditionMet(By.xpath("//input[@name='fname']"), "John"));
	}
	
	@Test
	void textToBePresentInElement_ableToSynchronizeAWebElementBasedOnProvidedValue_usingWebElement() {
		getWebDriver().get("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_input_attributes_value");
		getWebDriver().switchTo().frame("iframeResult");
		WebElement webElement = getWebDriver().findElement(By.xpath("//input[@name='fname']"));
		Assertions.assertTrue(underTest.isConditionMet(webElement, "John"));
	}

	@Test
	void textToBePresentInElement_anExceptionIsRaisedWhenAttemptingCallingSynchronizeAlertMethod() {
		Assertions.assertThrows(NotImplementedException.class, () -> {
			underTest.getAlert();
		});	
	}

	@Test
	void textToBePresentInElement_anExceptionIsRaisedWhenAttemptingCallingSynchronizeNestedWebElementMethod() {
		Assertions.assertThrows(NotImplementedException.class, () -> {
			underTest.getNestedWebElement(null, null);
		});
	}
	
	@Test
	void textToBePresentInElement_anExceptionIsRaisedWhenAttemptingCallingSynchronizeNestedWebElementsMethod() {
		Assertions.assertThrows(NotImplementedException.class, () -> {
			underTest.getNestedWebElements(null, null);
		});
	}
	
	@Test
	void textToBePresentInElement_anExceptionIsRaisedWhenAttemptingCallingSynchronizeWebDriverInstanceMethod() {
		Assertions.assertThrows(NotImplementedException.class, () -> {
			underTest.getWebDriverInstance(null);
		});	
	}
	
	@Test
	void textToBePresentInElement_anExceptionIsRaisedWhenAttemptingCallingSynchronizeWebElementsMethod() {
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
