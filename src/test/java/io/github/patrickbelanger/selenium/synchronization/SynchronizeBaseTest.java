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

package io.github.patrickbelanger.selenium.synchronization;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import lombok.Data;

/**
 * @author pbelanger <1848500+patrickbelanger@users.noreply.github.com>
 */
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
