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

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import qa.free.tools.selenium.synchronization.exceptions.ElementSynchronizationException;
import qa.free.tools.selenium.synchronization.exceptions.NotImplementedException;

public class Synchronize {	
	
	private WebDriver webDriver;
	
	public Synchronize(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
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
	
	public WebDriver synchronizeWebDriverInstance(SynchronizationMethods synchronizationMethod, Object object) {
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
	
	public boolean isSynchronized(SynchronizationMethods synchronizationMethod, By locator, String text) {
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
	
	public boolean isSynchronized(SynchronizationMethods synchronizationMethod, WebElement webElement, String text) {
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
	
	private String getConditionPackageName(SynchronizationMethods synchronizationMethods) {
		return String.format("%s.conditions.%s", Synchronize.class.getPackageName(), synchronizationMethods.getClassName());
	}
	
}
