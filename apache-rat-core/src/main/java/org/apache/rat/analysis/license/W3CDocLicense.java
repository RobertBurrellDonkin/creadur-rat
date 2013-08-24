/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.rat.analysis.license;

import org.apache.rat.api.domain.RatLicenseFamily;

/**
 * The Class W3CDocLicense.
 */
public class W3CDocLicense extends SimplePatternBasedLicense {

	/** The Constant COPYRIGHT_URL. */
	private static final String COPYRIGHT_URL = "http://www.w3.org/Consortium/Legal/2002/copyright-documents-20021231";

	/**
	 * Instantiates a new w3 c doc license.
	 */
	public W3CDocLicense() {
		super(RatLicenseFamily.W3C_DOCUMENTATION.licenseFamily(),
				new String[] { COPYRIGHT_URL });
	}
}
