/*
 * Copyright (c) 2015 Torsten Krause, Markenwerk GmbH
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
 * JUnit test for {@link InfixedIterableTests}.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 */
public class InfixedIterableTests {

	private static final Object INFIX = new Object();
	
	/**
	 * Iterate over a {@code null} {@link Iterator}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void iterateNullIterator() {

		new InfixedIterable<Object>(null, INFIX);

	}

	
	/**
	 * Iterate over an empty iterator.
	 */
	@Test
	public void iterateEmpty() {

		Iterator<Object> iterator = new InfixedIterable<Object>(new EmptyIterable<Object>(), INFIX).iterator();

		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Iterate over an {@link Iterator} with one element.
	 */
	@Test
	public void iterateOne() {

		Object[] values = new Object[] { new Object() };
		Iterator<Object> iterator = new InfixedIterable<Object>(new ArrayIterable<Object>(values), INFIX).iterator();

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[0], iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Iterate over an {@link Iterator} with two elements.
	 */
	@Test
	public void iterateTwo() {

		Object[] values = new Object[] { new Object(), new Object() };
		Iterator<Object> iterator = new InfixedIterable<Object>(new ArrayIterable<Object>(values), INFIX).iterator();

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[0], iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(INFIX, iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[1], iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Iterate over an {@link Iterator} with three elements.
	 */
	@Test
	public void iterateThree() {

		Object[] values = new Object[] { new Object(), new Object(), new Object() };
		Iterator<Object> iterator = new InfixedIterable<Object>(new ArrayIterable<Object>(values), INFIX).iterator();

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[0], iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(INFIX, iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[1], iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(INFIX, iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[2], iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

}
