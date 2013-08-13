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
package org.apache.rat.header;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.StringReader;

import org.junit.Before;
import org.junit.Test;

/**
 * The Class FilteringSequenceFactoryTest.
 */
public class FilteringSequenceFactoryTest {

	/** The capacity. */
	int capacity;

	/** The factory. */
	FilteringSequenceFactory factory;

	/** The filter. */
	SimpleCharFilter filter;

	/**
	 * Sets the up.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
		capacity = 50;
		filter = new SimpleCharFilter();
		factory = new FilteringSequenceFactory(capacity, filter);
	}

	/**
	 * No filtering.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void noFiltering() throws Exception {
		final String INPUT = "Whatever";
		StringReader reader = new StringReader(INPUT);
		CharSequence result = factory.filter(reader);
		assertNotNull(result);
		String output = result.toString();
		assertEquals("No filtering so input equals output.", INPUT, output);
		reader = new StringReader(INPUT);
		result = factory.filter(reader);
		assertNotNull(result);
		output = result.toString();
		assertEquals(
				"No filtering so input equals output. Independent of previous input",
				INPUT, output);
	}

	/**
	 * Filtering.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void filtering() throws Exception {
		final String INPUT = "Whatever";
		StringReader reader = new StringReader(INPUT);
		CharSequence result = factory.filter(reader);
		assertNotNull(result);
		String output = result.toString();
		assertEquals("No filtering so input equals output.", INPUT, output);
		filter.filterOut = true;
		reader = new StringReader(INPUT);
		result = factory.filter(reader);
		assertNotNull(result);
		assertEquals(
				"All filtered output is empty. Independent of previous input",
				0, result.length());
	}

	/**
	 * Over capacity.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void overCapacity() throws Exception {
		final String INPUT = "WhateverWhateverWhateverWhateverWhateverWhateverWhateverWhateverWhateverWhatever";
		StringReader reader = new StringReader(INPUT);
		CharSequence result = factory.filter(reader);
		assertNotNull(result);
		String output = new StringBuffer().append(result).toString();
		assertEquals("No filtering so input equals output.",
				INPUT.substring(0, capacity), output);
	}
}
