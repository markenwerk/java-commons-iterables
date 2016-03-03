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

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test for {@link OptionalIterable}.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 */
public class OptionalIterableTests {

	/**
	 * Iterate over a payload array.
	 */
	@Test
	public void iterate() {

		Object value = new Object();
		Iterator<Object> iterator = new OptionalIterable<Object>(value).iterator();

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(value, iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Iterate over a payload array twice.
	 */
	@Test
	public void iterateTwice() {

		Object value = new Object();
		Iterable<Object> iterable = new OptionalIterable<Object>(value);
		Iterator<Object> iterator = iterable.iterator();

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(value, iterator.next());
		Assert.assertFalse(iterator.hasNext());

		Iterator<Object> iterator2 = iterable.iterator();

		Assert.assertNotSame(iterator, iterator2);

		Assert.assertTrue(iterator2.hasNext());
		Assert.assertSame(value, iterator2.next());
		Assert.assertFalse(iterator2.hasNext());

	}
	
	/**
	 * Iterate over a payload array.
	 */
	@Test
	public void iterateNull() {

		Iterator<Object> iterator = new OptionalIterable<Object>(null).iterator();

		Assert.assertFalse(iterator.hasNext());

	}

}
