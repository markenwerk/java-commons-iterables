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

import net.markenwerk.commons.iterables.ShortArrayIterable;

/**
 * JUnit test for {@link ShortArrayIterable}.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public class ShortArrayIterableTests {

	/**
	 * Iterate over a {@code short[]}.
	 */
	@Test
	public void shortArray_iterate() {

		short[] values = new short[] { 1, 2 };
		Iterable<Short> iterable = new ShortArrayIterable(values);
		Iterator<Short> iterator = iterable.iterator();

		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(Short.valueOf(values[0]), iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(Short.valueOf(values[1]), iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}
	
	/**
	 * Iterate over a {@code short[]} twice.
	 */
	@Test
	public void shortArray_iterateTwice() {

		short[] values = new short[] { 1 };
		Iterator<Short> iterator = new ShortArrayIterable(values).iterator();

		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(Short.valueOf(values[0]), iterator.next());
		Assert.assertFalse(iterator.hasNext());
		
		Iterator<Short> iterator2 = new ShortArrayIterable(values).iterator();

		Assert.assertNotSame(iterator, iterator2);
		
		Assert.assertTrue(iterator2.hasNext());
		Assert.assertEquals(Short.valueOf(values[0]), iterator2.next());
		Assert.assertFalse(iterator2.hasNext());

	}

	/**
	 * Iterate over a {@code null} array.
	 */
	@Test
	public void shortArray_iterateNullArray() {

		Iterator<Short> iterator = new ShortArrayIterable(null).iterator();

		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Remove a value in a {@code short[]}.
	 */
	@Test
	public void shortArray_removeWithFallback() {

		short replacement = 0;
		short[] values = new short[] { 1 };
		Iterator<Short> iterator = new ShortArrayIterable(values, replacement).iterator();

		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(Short.valueOf(values[0]), iterator.next());
		Assert.assertFalse(iterator.hasNext());

		iterator.remove();

		Assert.assertEquals(replacement, values[0]);

	}

	/**
	 * Remove a value in a {@code short[]}.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void shortArray_removeWithoutFallback() {

		short[] values = new short[] { 1 };
		Iterator<Short> iterator = new ShortArrayIterable(values).iterator();

		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(Short.valueOf(values[0]), iterator.next());
		Assert.assertFalse(iterator.hasNext());

		iterator.remove();

	}

}