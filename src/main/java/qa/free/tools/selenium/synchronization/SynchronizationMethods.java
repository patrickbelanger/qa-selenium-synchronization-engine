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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import lombok.Getter;

/**
 * @author pbelanger <1848500+patrickbelanger@users.noreply.github.com>
 */
public enum SynchronizationMethods {
	ALERT_IS_PRESENT("AlertIsPresent", Alert.class),
	ELEMENT_TO_BE_CLICKABLE("ElementToBeClickable", WebElement.class),
	FRAME_TO_BE_AVAILABLE_AND_SWITCH_TO_IT("FrameToBeAvailableAndSwitchToIt", WebDriver.class),
	INVISIBILITY_OF_ELEMENT_LOCATED("InvisibilityOfElementLocated", Boolean.class),
	INVISIBILITY_OF_ELEMENT_WITH_TEXT("InvisibilityOfElementWithText", Boolean.class),
	NUMBER_OF_WINDOWS_TO_BE("NumberOfWindowsToBe", Boolean.class),
	PRESENCE_OF_ALL_ELEMENTS_LOCATED_BY("PresenceOfAllElementsLocatedBy", List.class),
	PRESENCE_OF_ELEMENT_LOCATED("PresenceOfElementLocated", WebElement.class),
	PRESENCE_OF_NESTED_ELEMENT_LOCATED_BY("PresenceOfNestedElementLocatedBy", WebElement.class),
	PRESENCE_OF_NESTED_ELEMENTS_LOCATED_BY("PresenceOfNestedElementsLocatedBy", List.class),
	STALENESS_OF("StalenessOf", WebElement.class),
	TEXT_TO_BE_PRESENT_IN_ELEMENT("TextToBePresentInElement", Boolean.class),
	TEXT_TO_BE_PRESENT_IN_ELEMENT_LOCATED("TextToBePresentInElementLocated", Boolean.class),
	TEXT_TO_BE_PRESENT_IN_ELEMENT_VALUE("TextToBePresentInElementValue", Boolean.class),
	TITLE_CONTAINS("TitleContains", Boolean.class),
	TITLE_IS("TitleIs", Boolean.class),
	URL_CONTAINS("UrlContains", Boolean.class),
	URL_MATCHES("UrlMatches", Boolean.class),
	URL_TO_BE("UrlToBe", Boolean.class),
	VISIBILITY_OF_ALL_ELEMENTS_LOCATED_BY("VisibilityOfAllElementsLocatedBy", List.class),
	VISIBILITY_OF_ALL_ELEMENTS("VisibilityOfAllElements", List.class),
	VISIBILITY_OF_ELEMENT_LOCATED("VisibilityOfElementLocated", WebElement.class),
	;
	
	@Getter
	private String className;
	
	@Getter
	private Class<?> classType;
	
	private SynchronizationMethods(String className, Class<?> classType) {
		this.className = className;
		this.classType = classType;
	}
	
}
