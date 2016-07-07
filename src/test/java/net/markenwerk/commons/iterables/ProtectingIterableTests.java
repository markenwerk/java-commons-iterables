/*
 * Copyright (c) 2015, 2016 Torsten Krause, Markenwerk GmbH
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

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test for {@link ProtectingIterable}.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 */
public class ProtectingIterableTests {

	/**
	 * Create with a {@code null} {@link Iterable}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void create_nullIterable() {

		new ProtectingIterable<Object>(null);

	}

	/**
	 * Create on {@link Iterator}.
	 */
	@Test
	public void iterator() {

		Iterable<Object> iterable = new ProtectingIterable<Object>(new EmptyIterable<Object>());

		Assert.assertNotNull(iterable.iterator());

	}

	/**
	 * Create multiple {@link Iterator Iterators}.
	 */
	@Test
	public void iterator_twice() {

		Iterable<Object> iterable = new ProtectingIterable<Object>(new EmptyIterable<Object>());

		Assert.assertNotSame(iterable.iterator(), iterable.iterator());

	}

}