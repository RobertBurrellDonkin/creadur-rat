/*
 * Licensed to the Apache Software Foundation (ASF) under one   *
 * or more contributor license agreements.  See the NOTICE file *
 * distributed with this work for additional information        *
 * regarding copyright ownership.  The ASF licenses this file   *
 * to you under the Apache License, Version 2.0 (the            *
 * "License"); you may not use this file except in compliance   *
 * with the License.  You may obtain a copy of the License at   *
 *                                                              *
 *   http://www.apache.org/licenses/LICENSE-2.0                 *
 *                                                              *
 * Unless required by applicable law or agreed to in writing,   *
 * software distributed under the License is distributed on an  *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY       *
 * KIND, either express or implied.  See the License for the    *
 * specific language governing permissions and limitations      *
 * under the License.                                           *
 */ 
package org.apache.rat.analysis.license;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.apache.rat.api.Document;
import org.apache.rat.document.MockLocation;
import org.junit.Before;
import org.junit.Test;

/**
 * The Class OASISLicenseTest.
 */
public class OASISLicenseTest {
    
	/** The Constant LICENSE. */
    private static final String LICENSE = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<!--\n" +
            "\n" +
            "\n" +
            "OASIS takes no position regarding the validity or scope of any intellectual property or other rights that might be claimed to pertain to the implementation or use of the technology described in this document or the extent to which any license under such rights might or might not be available; neither does it represent that it has made any effort to identify any such rights. Information on OASIS's procedures with respect to rights in OASIS specifications can be found at the OASIS website. Copies of claims of rights made available for publication and any assurances of licenses to be made available, or the result of an attempt made to obtain a general license or permission for the use of such proprietary rights by implementors or users of this specification, can be obtained from the OASIS Executive Director.\n" +
            "\n" +
            "\n" +
            "OASIS invites any interested party to bring to its attention any copyrights, patents or patent applications, or other proprietary rights which may cover technology that may be required to implement this specification. Please address the information to the OASIS Executive Director\n." +
            "\n" +
            "Copyright (C) OASIS Open (2004-2005). All Rights Reserved.\n" +
            "\n" +
            "This document and translations of it may be copied and furnished to others, and derivative works that comment on or otherwise explain it or assist in its implementation may be prepared, copied, published and distributed, in whole or in part, without restriction of any kind, provided that the above copyright notice and this paragraph are included on all such copies and derivative works. However, this document itself may not be modified in any way, such as by removing the copyright notice or references to OASIS, except as needed for the purpose of developing OASIS specifications, in which case the procedures for copyrights defined in the OASIS Intellectual Property Rights document must be followed, or as required to translate it into languages other than English.\n" +
            "\n" +
            "\n" +
            "The limited permissions granted above are perpetual and will not be revoked by OASIS or its successors or assigns.\n" +
            "\n" +
            "\n" +
            "This document and the information contained herein is provided on an \"AS IS\" basis and OASIS DISCLAIMS ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO ANY WARRANTY THAT THE USE OF THE INFORMATION HEREIN WILL NOT INFRINGE ANY RIGHTS OR ANY IMPLIED WARRANTIES OF MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.\n" +
            "-->\n";
    
	/** The license. */
	private OASISLicense license;

	/**
	 * Sets the up.
	 */
    @Before
	public void setUp() {
        license = new OASISLicense();
    }

	/**
	 * Test match oasis license.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    @Test
	public void testMatchOASISLicense() throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new StringReader(
				LICENSE));
		String line = bufferedReader.readLine();
        boolean result = false;
        final Document subject = new MockLocation("subject");
        while (line != null) {
            result = license.match(subject, line);
			line = bufferedReader.readLine();
        }
        assertTrue("OASIS license should be matched", result);
    }

	/**
	 * Test non match oasis license.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    @Test
	public void testNonMatchOASISLicense() throws IOException {
		final Document subject = new MockLocation("subject");
        license.reset();
		boolean result = license.match(subject, "New line");
		assertFalse("After reset, content should build up again", result);
    }

	/**
	 * Test notes.
	 */
	@Test
	public void testNotes() {
		assertThat(
				license.getNotes(),
				is("Note that OASIS requires a NOTICE. All modifications require notes. See https://www.oasis-open.org/policies-guidelines/ipr."));
	}

	/**
	 * Test category.
	 */
	@Test
	public void testCategory() {
		assertThat(license.getLicenseFamilyCategory(), is("OASIS"));
	}

	/**
	 * Test name.
	 */
	@Test
	public void testName() {
		assertThat(license.getLicenseFamilyName(), is("OASIS Open License"));
	}
   
}
