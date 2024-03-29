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

import java.util.List;

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
class PresenceOfAllElementsLocatedByTest extends SynchronizeBaseTest {
	
	private PresenceOfAllElementsLocatedBy underTest;
	
	@BeforeEach
	public void setUp() {
		super.setUp();
		underTest = new PresenceOfAllElementsLocatedBy(getWebDriver());
	}
	
	@Test
	void presenceOfAllElementsLocated_ableToSynchronizeAndGetAccessToEveryElements() {
		getWebDriver().get("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_table_intro");
		getWebDriver().switchTo().frame("iframeResult");
		List<WebElement> webElements = underTest.getWebElements(By.xpath("//table//td"));
		Assertions.assertNotNull(webElements);
		Assertions.assertInstanceOf(List.class, webElements);
		Assertions.assertTrue(webElements.size() > 1);
	}

	@Test
	void presenceOfAllElementsLocated_anExceptionIsRaisedWhenAttemptingCallingSynchronizeAlertMethod() {
		Assertions.assertThrows(NotImplementedException.class, () -> {
			underTest.getAlert();
		});
	}
	
	@Test
	void presenceOfAllElementsLocated_anExceptionIsRaisedWhenAttemptingCallingSynchronizeNestedWebElementMethod() {
		Assertions.assertThrows(NotImplementedException.class, () -> {
			underTest.getNestedWebElement(null, null);
		});
	}
	
	@Test
	void presenceOfAllElementsLocated_anExceptionIsRaisedWhenAttemptingCallingSynchronizeNestedWebElementsMethod() {
		Assertions.assertThrows(NotImplementedException.class, () -> {
			underTest.getNestedWebElements(null, null);
		});
	}	
	
	@Test
	void presenceOfAllElementsLocated_anExceptionIsRaisedWhenAttemptingCallingSynchronizeWebDriverInstanceMethod() {
		Assertions.assertThrows(NotImplementedException.class, () -> {
			underTest.getWebDriverInstance(null);
		});	
	}
	
	@Test
	void presenceOfAllElementsLocated_anExceptionIsRaisedWhenAttemptingCallingSynchronizeWebElementMethod() {
		By by = By.xpath("//html");
		Assertions.assertThrows(NotImplementedException.class, () -> {
			underTest.getWebElement(by);
		});
	}
	
	@AfterEach
	public void tearDown() {
		super.tearDown();
		underTest = null;
	}
}
