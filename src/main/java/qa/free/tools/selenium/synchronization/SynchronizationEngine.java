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

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AccessLevel;
import lombok.Getter;
import qa.free.tools.selenium.synchronization.exceptions.ElementSynchronizationException;
import qa.free.tools.selenium.synchronization.properties.SynchronizationProperties;

/**
 * @author pbelanger <1848500+patrickbelanger@users.noreply.github.com>
 */
public abstract class SynchronizationEngine {
	
	ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
	ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();
	ThreadLocal<Integer> synchronizationAttempts = new ThreadLocal<>();
	
	private static final String EXCEPTION = "Not implemented for %s";
	private static final String SYNCHRONIZATION_PERFORMED_AGAINST = "Synchronization performed - Against: {}";
	
	@Getter(AccessLevel.PRIVATE)
	static final Logger logger = LoggerFactory.getLogger(SynchronizationEngine.class);
	
	@Getter(AccessLevel.PRIVATE)
	static final SynchronizationProperties synchronizationProperties = 
		ConfigFactory.create(SynchronizationProperties.class);
	
	public int getSynchronizationAttempts() {
		return synchronizationAttempts.get();
	}

	public void setSynchronizationAttempts(int synchronizationAttempts) {
		this.synchronizationAttempts.set(synchronizationAttempts);
	}
	
	protected SynchronizationEngine(WebDriver webDriver) {
		setWebDriver(webDriver);
		setWait(new WebDriverWait(getWebDriver(), Duration.ofSeconds(synchronizationProperties.getTimeout())));
	}
	
	protected String getExceptionDetails(Class<?> clazz) {
		return String.format(EXCEPTION, clazz.toString());
	}
	
	protected WebDriver getWebDriver() {
		return webDriver.get();
	}

	protected WebDriverWait getWait() {
		return wait.get();
	}

	private void setWebDriver(WebDriver webDriver) {
		this.webDriver.set(webDriver);
	}

	private void setWait(WebDriverWait wait) {
		this.wait.set(wait);
	}
	
	@SuppressWarnings("unchecked")
	protected <V, T> V performSynchronization(By by, ExpectedCondition<T> expectedCondition) {
		for (int i = 0; i < synchronizationProperties.getMaximumRetryAttempts(); i++) {
			try {
				V element = (V) getWait().until((Function<? super WebDriver, Object>) expectedCondition);
				logSynchronizationElement(element);
				return element;
			} catch(TimeoutException e) {
				setSynchronizationAttempts(i + 1);
				logger.info(
						"Synchronization error - Attempting to find element using the expected condition: {} - Attempt #{}",
								expectedCondition, i + 1);
			}
		}
		logger.error("Unable to perform element synchronization after {} attempt(s)", getSynchronizationAttempts());
		throw new ElementSynchronizationException(String.format("Unable to find element %s", by));
	}
	
	private void logSynchronizationElement(Object object) {
		if (object instanceof Alert) {
			logger.info(SYNCHRONIZATION_PERFORMED_AGAINST, "Alert dialog");
		} else if (object instanceof WebElement) {
			logger.info(SYNCHRONIZATION_PERFORMED_AGAINST, "WebElement");
		} else if (object instanceof List<?>) {
			logger.info(SYNCHRONIZATION_PERFORMED_AGAINST, "List of WebElements");
		} else {
			logger.info(SYNCHRONIZATION_PERFORMED_AGAINST, "Condition");
		}
	}
	
	public abstract Alert getAlert();
	public abstract WebElement getWebElement(By by);
	public abstract List<WebElement> getWebElements(By by);
	public abstract boolean isConditionMet(Object object);
	
}
