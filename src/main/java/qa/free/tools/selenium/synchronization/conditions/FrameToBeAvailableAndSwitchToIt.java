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

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import qa.free.tools.selenium.synchronization.SynchronizationEngine;
import qa.free.tools.selenium.synchronization.converter.Converter;
import qa.free.tools.selenium.synchronization.exceptions.NotImplementedException;

/**
 * @author pbelanger <1848500+patrickbelanger@users.noreply.github.com>
 */
public class FrameToBeAvailableAndSwitchToIt extends SynchronizationEngine {

	public FrameToBeAvailableAndSwitchToIt(WebDriver webDriver) {
		super(webDriver);
	}

	@Override
	public Alert getAlert() {
		throw new NotImplementedException(getExceptionDetails(this.getClass()));
	}

	@Override
	public WebDriver getWebDriverInstance(Object object) {
		if (object instanceof By) {
			return performSynchronization(null, 
					ExpectedConditions.frameToBeAvailableAndSwitchToIt(new Converter<Object>(object).convertAsBy()));			
		}
		else if (object instanceof Integer) {
			return performSynchronization(null, 
					ExpectedConditions.frameToBeAvailableAndSwitchToIt(new Converter<Object>(object).convertAsInteger()));			
		}
		else if (object instanceof String) {
			return performSynchronization(null, 
					ExpectedConditions.frameToBeAvailableAndSwitchToIt(new Converter<Object>(object).convertAsString()));			
		}
		return performSynchronization(null, 
				ExpectedConditions.frameToBeAvailableAndSwitchToIt(new Converter<Object>(object).convertAsWebElement()));
	}
	
	@Override
	public WebElement getNestedWebElement(By locator, By childLocator) {
		throw new NotImplementedException(getExceptionDetails(this.getClass()));
	}

	@Override
	public List<WebElement> getNestedWebElements(By locator, By childLocator) {
		throw new NotImplementedException(getExceptionDetails(this.getClass()));
	}
	
	@Override
	public WebElement getWebElement(By by) {
		throw new NotImplementedException(getExceptionDetails(this.getClass()));
	}

	@Override
	public List<WebElement> getWebElements(By by) {
		throw new NotImplementedException(getExceptionDetails(this.getClass()));
	}

	@Override
	public boolean isConditionMet(Object object) {
		throw new NotImplementedException(getExceptionDetails(this.getClass()));
	}
	
}
