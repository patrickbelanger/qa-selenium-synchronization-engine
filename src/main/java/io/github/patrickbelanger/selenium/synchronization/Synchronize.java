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

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.patrickbelanger.selenium.synchronization.exceptions.ElementSynchronizationException;
import io.github.patrickbelanger.selenium.synchronization.exceptions.IncompatibleSynchronizationMethodClassTypeReturnException;
import io.github.patrickbelanger.selenium.synchronization.exceptions.NotImplementedException;

public class Synchronize {	
	
	private WebDriver webDriver;
	
	public Synchronize(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	/**
	 * Perform synchronization until an alert is present
	 * 
	 * @return {@link Alert} Instance of the alert, allowing to interact with it (i.e., accept, dismiss, get text, send keys).
	 */
	public Alert synchronizeAlert() {
		try {
  		return ((SynchronizationEngine) 
  				Class.forName(getConditionPackageName(SynchronizationMethods.ALERT_IS_PRESENT))
  					.getDeclaredConstructor(WebDriver.class).newInstance(webDriver)).getAlert();
		} catch(NotImplementedException e) {
			throw new NotImplementedException(e);
		} catch(Exception e) {
			throw new ElementSynchronizationException(e);
		}
	}
	
	/**
	 * Perform synchronization until the driver is returned (e.g., the given frame is available to switch to, the driver
	 * instance with that context will be returned).
	 *  
	 * @param synchronizationMethod {@link SynchronizationMethods} Synchronization method returning a WebDriver instance
	 * @param object {@link By} locator or {@link WebElement} webElement.
	 * @return {@link WebDriver} Instance of the webdriver with the new context
	 * 
	 */
	public WebDriver synchronizeWebDriverInstance(SynchronizationMethods synchronizationMethod, Object object) {
		if (!isSynchronizationClassTypeWebDriver(synchronizationMethod)) {
			throw new IncompatibleSynchronizationMethodClassTypeReturnException(
					synchronizationMethod.getClassName(), getCurrentMethodName());
		}
		try {
  		return ((SynchronizationEngine) 
  				Class.forName(getConditionPackageName(synchronizationMethod))
  					.getDeclaredConstructor(WebDriver.class).newInstance(webDriver)).getWebDriverInstance(object);
		} catch(NotImplementedException e) {
			throw new NotImplementedException(e);
		} catch(Exception e) {
			throw new ElementSynchronizationException(e);
		}
	}
	
	public WebElement synchronizeNestedWebElement(By locator, By childLocator) {
		try {
  		return ((SynchronizationEngine) 
  				Class.forName(getConditionPackageName(SynchronizationMethods.PRESENCE_OF_NESTED_ELEMENT_LOCATED_BY))
  					.getDeclaredConstructor(WebDriver.class).newInstance(webDriver))
  						.getNestedWebElement(locator, childLocator);
		} catch(NotImplementedException e) {
			throw new NotImplementedException(e);
		} catch(Exception e) {
			throw new ElementSynchronizationException(e);
		}
	}
	
	public List<WebElement> synchronizeNestedWebElements(By locator, By childLocator) {
		try {
  		return ((SynchronizationEngine) 
  				Class.forName(getConditionPackageName(SynchronizationMethods.PRESENCE_OF_NESTED_ELEMENTS_LOCATED_BY))
  					.getDeclaredConstructor(WebDriver.class).newInstance(webDriver))
  						.getNestedWebElements(locator, childLocator);
		} catch(NotImplementedException e) {
			throw new NotImplementedException(e);
		} catch(Exception e) {
			throw new ElementSynchronizationException(e);
		}
	}
	
	public WebElement synchronizeWebElement(SynchronizationMethods synchronizationMethod, By locator) {
		if (!isSynchronizationClassTypeWebElement(synchronizationMethod)) {
			throw new IncompatibleSynchronizationMethodClassTypeReturnException(
					synchronizationMethod.getClassName(), getCurrentMethodName());
		}
		try {
  		return ((SynchronizationEngine) 
  				Class.forName(getConditionPackageName(synchronizationMethod))
  					.getDeclaredConstructor(WebDriver.class).newInstance(webDriver)).getWebElement(locator);
		} catch(NotImplementedException e) {
			throw new NotImplementedException(e);
		} catch(Exception e) {
			throw new ElementSynchronizationException(e);
		}
	}
	
	public boolean isSynchronized(SynchronizationMethods synchronizationMethod, By locator) {
		if (!isSynchronizationClassTypeBoolean(synchronizationMethod)) {
			throw new IncompatibleSynchronizationMethodClassTypeReturnException(
					synchronizationMethod.getClassName(), getCurrentMethodName());
		}
		try {
  		return ((SynchronizationEngine) 
  				Class.forName(getConditionPackageName(synchronizationMethod))
  					.getDeclaredConstructor(WebDriver.class).newInstance(webDriver)).isConditionMet(locator);
		} catch(NotImplementedException e) {
			throw new NotImplementedException(e);
		} catch(Exception e) {
			throw new ElementSynchronizationException(e);
		}
	}
	
	public boolean isSynchronized(SynchronizationMethods synchronizationMethod, int number) {
		if (!isSynchronizationClassTypeBoolean(synchronizationMethod)) {
			throw new IncompatibleSynchronizationMethodClassTypeReturnException(
					synchronizationMethod.getClassName(), getCurrentMethodName());
		}
		try {
  		return ((SynchronizationEngine) 
  				Class.forName(getConditionPackageName(synchronizationMethod))
  					.getDeclaredConstructor(WebDriver.class).newInstance(webDriver)).isConditionMet(number);
		} catch(NotImplementedException e) {
			throw new NotImplementedException(e);
		} catch(Exception e) {
			throw new ElementSynchronizationException(e);
		}
	}
	
	public boolean isSynchronized(SynchronizationMethods synchronizationMethod, By locator, boolean condition) {
		if (!isSynchronizationClassTypeBoolean(synchronizationMethod)) {
			throw new IncompatibleSynchronizationMethodClassTypeReturnException(
					synchronizationMethod.getClassName(), getCurrentMethodName());
		}
		try {
  		return ((SynchronizationEngine) 
  				Class.forName(getConditionPackageName(synchronizationMethod))
  					.getDeclaredConstructor(WebDriver.class).newInstance(webDriver)).isConditionMet(locator, condition);
		} catch(NotImplementedException e) {
			throw new NotImplementedException(e);
		} catch(Exception e) {
			throw new ElementSynchronizationException(e);
		}
	}
	
	public boolean isSynchronized(SynchronizationMethods synchronizationMethod, By locator, String text) {
		if (!isSynchronizationClassTypeBoolean(synchronizationMethod)) {
			throw new IncompatibleSynchronizationMethodClassTypeReturnException(
					synchronizationMethod.getClassName(), getCurrentMethodName());
		}
		try {
  		return ((SynchronizationEngine) 
  				Class.forName(getConditionPackageName(synchronizationMethod))
  					.getDeclaredConstructor(WebDriver.class).newInstance(webDriver)).isConditionMet(locator, text);
		} catch(NotImplementedException e) {
			throw new NotImplementedException(e);
		} catch(Exception e) {
			throw new ElementSynchronizationException(e);
		}
	}
	
	public boolean isSynchronized(SynchronizationMethods synchronizationMethod, By locator, String attribute, String value) {
		if (!isSynchronizationClassTypeBoolean(synchronizationMethod)) {
			throw new IncompatibleSynchronizationMethodClassTypeReturnException(
					synchronizationMethod.getClassName(), getCurrentMethodName());
		}
		try {
  		return ((SynchronizationEngine) 
  				Class.forName(getConditionPackageName(synchronizationMethod))
  					.getDeclaredConstructor(WebDriver.class).newInstance(webDriver)).isConditionMet(locator, attribute, value);
		} catch(NotImplementedException e) {
			throw new NotImplementedException(e);
		} catch(Exception e) {
			throw new ElementSynchronizationException(e);
		}
	}
	
	public boolean isSynchronized(SynchronizationMethods synchronizationMethod, WebElement webElement) {
		if (!isSynchronizationClassTypeBoolean(synchronizationMethod)) {
			throw new IncompatibleSynchronizationMethodClassTypeReturnException(
					synchronizationMethod.getClassName(), getCurrentMethodName());
		}
		try {
  		return ((SynchronizationEngine) 
  				Class.forName(getConditionPackageName(synchronizationMethod))
  					.getDeclaredConstructor(WebDriver.class).newInstance(webDriver)).isConditionMet(webElement);
		} catch(NotImplementedException e) {
			throw new NotImplementedException(e);
		} catch(Exception e) {
			throw new ElementSynchronizationException(e);
		}
	}
	
	public boolean isSynchronized(SynchronizationMethods synchronizationMethod, WebElement webElement, boolean condition) {
		if (!isSynchronizationClassTypeBoolean(synchronizationMethod)) {
			throw new IncompatibleSynchronizationMethodClassTypeReturnException(
					synchronizationMethod.getClassName(), getCurrentMethodName());
		}
		try {
  		return ((SynchronizationEngine) 
  				Class.forName(getConditionPackageName(synchronizationMethod))
  					.getDeclaredConstructor(WebDriver.class).newInstance(webDriver)).isConditionMet(webElement, condition);
		} catch(NotImplementedException e) {
			throw new NotImplementedException(e);
		} catch(Exception e) {
			throw new ElementSynchronizationException(e);
		}
	}
	
	public boolean isSynchronized(SynchronizationMethods synchronizationMethod, WebElement webElement, String text) {
		if (!isSynchronizationClassTypeBoolean(synchronizationMethod)) {
			throw new IncompatibleSynchronizationMethodClassTypeReturnException(
					synchronizationMethod.getClassName(), getCurrentMethodName());
		}
		try {
  		return ((SynchronizationEngine) 
  				Class.forName(getConditionPackageName(synchronizationMethod))
  					.getDeclaredConstructor(WebDriver.class).newInstance(webDriver)).isConditionMet(webElement, text);
		} catch(NotImplementedException e) {
			throw new NotImplementedException(e);
		} catch(Exception e) {
			throw new ElementSynchronizationException(e);
		}
	}
	
	public List<WebElement> synchronizeWebElements(SynchronizationMethods synchronizationMethod, By locator) {
		if (!isSynchronizationClassTypeList(synchronizationMethod)) {
			throw new IncompatibleSynchronizationMethodClassTypeReturnException(
					synchronizationMethod.getClassName(), getCurrentMethodName());
		}
		try {
  		return ((SynchronizationEngine) 
  				Class.forName(getConditionPackageName(synchronizationMethod))
  					.getDeclaredConstructor(WebDriver.class).newInstance(webDriver)).getWebElements(locator);
		} catch(NotImplementedException e) {
			throw new NotImplementedException(e);
		} catch(Exception e) {
			throw new ElementSynchronizationException(e);
		}
	}
	
	public boolean synchronizeWebPage(SynchronizationMethods synchronizationMethod, String matcher) {
		if (!isSynchronizationClassTypeBoolean(synchronizationMethod)) {
			throw new IncompatibleSynchronizationMethodClassTypeReturnException(
					synchronizationMethod.getClassName(), getCurrentMethodName());
		}
		try {
  		return ((SynchronizationEngine) 
  				Class.forName(getConditionPackageName(synchronizationMethod))
  					.getDeclaredConstructor(WebDriver.class).newInstance(webDriver)).isConditionMet(matcher);
		} catch(NotImplementedException e) {
			throw new NotImplementedException(e);
		} catch(Exception e) {
			throw new ElementSynchronizationException(e);
		}
	}
	
	private String getCurrentMethodName() {
		return new Throwable().getStackTrace()[0].getMethodName();
	}
	
	private boolean isSynchronizationClassTypeBoolean(SynchronizationMethods synchronizationMethod) {
		return isSynchronizationClassTypeReturnMatch(synchronizationMethod, Boolean.class);
	}
	
	private boolean isSynchronizationClassTypeList(SynchronizationMethods synchronizationMethod) {
		return isSynchronizationClassTypeReturnMatch(synchronizationMethod, List.class);
	}
	
	private boolean isSynchronizationClassTypeWebDriver(SynchronizationMethods synchronizationMethod) {
		return isSynchronizationClassTypeReturnMatch(synchronizationMethod, WebDriver.class);
	}
	
	private boolean isSynchronizationClassTypeWebElement(SynchronizationMethods synchronizationMethod) {
		return isSynchronizationClassTypeReturnMatch(synchronizationMethod, WebElement.class);
	}
	
	private boolean isSynchronizationClassTypeReturnMatch(SynchronizationMethods synchronizationMethod, Class<?> target) {
		return synchronizationMethod.getClassType().equals(target);
	}
	
	private String getConditionPackageName(SynchronizationMethods synchronizationMethods) {
		return String.format("%s.conditions.%s", Synchronize.class.getPackageName(), synchronizationMethods.getClassName());
	}
	
}
