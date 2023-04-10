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

import lombok.Getter;

/**
 * @author pbelanger <1848500+patrickbelanger@users.noreply.github.com>
 */
public enum SynchronizationMethods {
	ALERT_IS_PRESENT("AlertIsPresent"),
	ELEMENT_TO_BE_CLICKABLE("ElementToBeClickable"),
	FRAME_TO_BE_AVAILABLE_AND_SWITCH_TO_IT("FrameToBeAvailableAndSwitchToIt"),
	INVISIBILITY_OF_ELEMENT_LOCATED("InvisibilityOfElementLocated"),
	PRESENCE_OF_ELEMENT_LOCATED("PresenceOfElementLocated"),
	PRESENCE_OF_ALL_ELEMENTS_LOCATED_BY("PresenceOfAllElementsLocatedBy"),
	PRESENCE_OF_NESTED_ELEMENT_LOCATED_BY("PresenceOfNestedElementLocatedBy"),
	PRESENCE_OF_NESTED_ELEMENTS_LOCATED_BY("PresenceOfNestedElementsLocatedBy"),
	VISIBILITY_OF_ELEMENT_LOCATED("VisibilityOfElementLocated"),
	VISIBILITY_OF_ALL_ELEMENTS_LOCATED_BY("VisibilityOfAllElementsLocatedBy"),
	TITLE_CONTAINS("TitleContains"),
	TITLE_IS("TitleIs"),
	URL_CONTAINS("UrlContains"),
	URL_MATCHES("UrlMatches"),
	URL_TO_BE("UrlToBe")
	;
	
	@Getter
	private String className;
	
	private SynchronizationMethods(String className) {
		this.className = className;
	}
	
}
