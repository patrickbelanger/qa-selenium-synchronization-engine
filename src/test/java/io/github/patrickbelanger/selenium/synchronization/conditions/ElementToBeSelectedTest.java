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

package io.github.patrickbelanger.selenium.synchronization.conditions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.github.patrickbelanger.selenium.synchronization.SynchronizeBaseTest;
import io.github.patrickbelanger.selenium.synchronization.exceptions.NotImplementedException;

/**
 * @author pbelanger <1848500+patrickbelanger@users.noreply.github.com>
 */
class ElementToBeSelectedTest extends SynchronizeBaseTest {

	private ElementToBeSelected underTest;
	
	@BeforeEach
	public void setUp() {
		super.setUp();
		underTest = new ElementToBeSelected(getWebDriver());
	}
	
	@Test
	void elementToBeSelected_ableToSynchronizeBasedOnSelectedElement() {
		getWebDriver().get("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_elem_select_pre");
		getWebDriver().switchTo().frame("iframeResult");
		WebElement webElement = getWebDriver().findElement(By.xpath("//option[@value='fiat']"));
		Assertions.assertTrue(underTest.isConditionMet(webElement));
		Assertions.assertEquals("Fiat", webElement.getText());
	}

	@Test
	void elementToBeSelected_anExceptionIsRaisedWhenAttemptingCallingSynchronizeAlertMethod() {
		Assertions.assertThrows(NotImplementedException.class, () -> {
			underTest.getAlert();
		});	
	}

	@Test
	void elementToBeSelected_anExceptionIsRaisedWhenAttemptingCallingSynchronizeNestedWebElementMethod() {
		Assertions.assertThrows(NotImplementedException.class, () -> {
			underTest.getNestedWebElement(null, null);
		});
	}
	
	@Test
	void elementToBeSelected_anExceptionIsRaisedWhenAttemptingCallingSynchronizeNestedWebElementsMethod() {
		Assertions.assertThrows(NotImplementedException.class, () -> {
			underTest.getNestedWebElements(null, null);
		});
	}
	
	@Test
	void elementToBeSelected_anExceptionIsRaisedWhenAttemptingCallingSynchronizeWebDriverInstanceMethod() {
		Assertions.assertThrows(NotImplementedException.class, () -> {
			underTest.getWebDriverInstance(null);
		});	
	}
	
	@Test
	void elementToBeSelected_anExceptionIsRaisedWhenAttemptingCallingSynchronizeWebElementsMethod() {
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
