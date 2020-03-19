/*
 * Copyright 2002-2005 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package maf.context.exceptions;

import java.util.Locale;

/**
 * Exception thrown when a message can't be resolved.
 *
 * @author Rod Johnson
 */
public class NoSuchMessageException extends MafResourceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create a new exception.
	 * @param code code that could not be resolved for given locale
	 * @param locale locale that was used to search for the code within
	 */
	public NoSuchMessageException(String key, Locale locale) {
		super("No message found under code '" + key + "' for locale '" + locale + "'.");
	}
	
	public NoSuchMessageException(String key, String locale) {
		super("No message found under code '" + key + "' for locale '" + locale + "'.");
	}
	/**
	 * Create a new exception.
	 * @param code code that could not be resolved for given locale
	 */
	public NoSuchMessageException(String key) {
		super("No message found under code '" + key + "' for locale '" + Locale.getDefault() + "'.");
	}

}

