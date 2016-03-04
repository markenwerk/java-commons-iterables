/*
 * Copyright (c) 2016 Torsten Krause, Markenwerk GmbH
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package net.markenwerk.commons.iterables;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

import org.junit.Assert;
import org.junit.Test;

import net.markenwerk.commons.exceptions.CreationException;
import net.markenwerk.commons.interfaces.Producer;

/**
 * JUnit test for {@link EnumerationIterable}.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 */
public class EnumerationIteratorTests {

	/**
	 * Iterate with a {@code null} {@link Enumeration} {@link Producer}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void iterateNullTokenizer() {

		new EnumerationIterable<Object>(null);

	}

	/**
	 * Iterate over an empty {@link Enumeration}.
	 */
	@Test
	public void iterateEmpty() {

		Iterator<Object> iterator = new EnumerationIterable<Object>(new Producer<Enumeration<Object>>() {
			@Override
			public Enumeration<Object> create() throws CreationException {
				return new Vector<Object>(Arrays.asList()).elements();
			}
		}).iterator();

		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Iterate over an {@link Enumeration} with one element.
	 */
	@Test
	public void iterateOne() {

		final Object value = new Object();
		Iterator<Object> iterator = new EnumerationIterable<Object>(new Producer<Enumeration<Object>>() {
			@Override
			public Enumeration<Object> create() throws CreationException {
				return new Vector<Object>(Arrays.asList(value)).elements();
			}
		}).iterator();

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(value, iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Iterate over an {@link Enumeration} with two elements.
	 */
	@Test
	public void iterateTwo() {

		final Object[] values = new Object[] { new Object(), new Object() };
		Iterator<Object> iterator = new EnumerationIterable<Object>(new Producer<Enumeration<Object>>() {
			@Override
			public Enumeration<Object> create() throws CreationException {
				return new Vector<Object>(Arrays.asList(values)).elements();
			}
		}).iterator();

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[0], iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[1], iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

}
