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
  		return ((ElementSynchronized) 
  				Class.forName(getConditionPackageName(SynchronizationMethods.ALERT_IS_PRESENT))
  					.getDeclaredConstructor(WebDriver.class).newInstance(webDriver)).getAlert();
		} catch(NotImplementedException e) {
			throw new NotImplementedException(e);
		} catch(Exception e) {
			throw new ElementSynchronizationException(e);
		}
	}
	
	public WebElement synchronizeWebElement(SynchronizationMethods synchronizationMethod, By by) {
		try {
  		return ((ElementSynchronized) 
  				Class.forName(getConditionPackageName(synchronizationMethod))
  					.getDeclaredConstructor(WebDriver.class).newInstance(webDriver)).getWebElement(by);
		} catch(NotImplementedException e) {
			throw new NotImplementedException(e);
		} catch(Exception e) {
			throw new ElementSynchronizationException(e);
		}
	}
	
	public List<WebElement> synchronizeWebElements(SynchronizationMethods synchronizationMethod, By by) {
		try {
  		return ((ElementSynchronized) 
  				Class.forName(getConditionPackageName(synchronizationMethod))
  					.getDeclaredConstructor(WebDriver.class).newInstance(webDriver)).getWebElements(by);
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
