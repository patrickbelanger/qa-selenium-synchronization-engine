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

import java.util.List;

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
class PresenceOfAllElementsLocatedTest {

	private WebDriver webDriver;
	private PresenceOfAllElementsLocated underTest;
	
	@BeforeEach
	public void setUp() {
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		webDriver = new ChromeDriver();
		underTest = new PresenceOfAllElementsLocated(webDriver);
	}
	
	@Test
	void presenceOfAllElementsLocated_ableToSynchronizeAndGetAccessToEveryElements() {
		webDriver.get("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_table_intro");
		webDriver.switchTo().frame("iframeResult");
		List<WebElement> webElements = underTest.getWebElements(By.xpath("//table//td"));
		Assertions.assertNotNull(webElements);
		Assertions.assertInstanceOf(List.class, webElements);
		Assertions.assertTrue(webElements.size() > 1);
	}

	@Test
	void presenceOfElementLocated_anExceptionIsRaisedWhenAttemptingCallingSynchronizeAlertMethod() {
		Assertions.assertThrows(NotImplementedException.class, () -> {
			underTest.getAlert();
		});
		
	}
	
	@Test
	void presenceOfElementLocated_anExceptionIsRaisedWhenAttemptingCallingSynchronizeWebElementMethod() {
		Assertions.assertThrows(NotImplementedException.class, () -> {
			underTest.getWebElement(null);
		});
	}
	
	@AfterEach
	public void tearDown() {
		webDriver.quit();
		underTest = null;
	}
}
