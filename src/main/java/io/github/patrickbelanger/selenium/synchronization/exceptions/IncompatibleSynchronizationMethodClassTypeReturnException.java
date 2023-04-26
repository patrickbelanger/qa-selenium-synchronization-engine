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

package io.github.patrickbelanger.selenium.synchronization.exceptions;

public class IncompatibleSynchronizationMethodClassTypeReturnException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4529367308770220518L;

	public IncompatibleSynchronizationMethodClassTypeReturnException(String message, String methodName) {
		super(String.format("%s synchronization method return type is not compatible with %s method", message, methodName));
	}
	
}
