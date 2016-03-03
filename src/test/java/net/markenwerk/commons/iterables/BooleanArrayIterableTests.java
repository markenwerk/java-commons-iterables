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

import net.markenwerk.commons.iterables.BooleanArrayIterable;

/**
 * JUnit test for {@link BooleanArrayIterable}.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 */
public class BooleanArrayIterableTests {

	/**
	 * Iterate over a {@code boolean[]}.
	 */
	@Test
	public void iterate() {

		boolean[] values = new boolean[] { true, false };
		Iterator<Boolean> iterator = new BooleanArrayIterable(values).iterator();

		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(Boolean.valueOf(values[0]), iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(Boolean.valueOf(values[1]), iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}
	
	
	/**
	 * Iterate over a {@code boolean[]} twice.
	 */
	@Test
	public void iterateTwice() {

		boolean[] values = new boolean[] { true };
		Iterable<Boolean> iterable = new BooleanArrayIterable(values);
		Iterator<Boolean> iterator = iterable.iterator();

		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(Boolean.valueOf(values[0]), iterator.next());
		Assert.assertFalse(iterator.hasNext());
		
		Iterator<Boolean> iterator2 = iterable.iterator();

		Assert.assertNotSame(iterator, iterator2);

		Assert.assertTrue(iterator2.hasNext());
		Assert.assertEquals(Boolean.valueOf(values[0]), iterator2.next());
		Assert.assertFalse(iterator2.hasNext());

	}

	/**
	 * Iterate over a {@code null} array.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void iterateNullArray() {

		new BooleanArrayIterable(null).iterator();

	}

	/**
	 * Remove a value in a {@code boolean[]}.
	 */
	@Test
	public void removeWithFallback() {

		boolean replacement = false;
		boolean[] values = new boolean[] { true };
		Iterator<Boolean> iterator = new BooleanArrayIterable(values, replacement).iterator();

		iterator.next();
		iterator.remove();

		Assert.assertEquals(replacement, values[0]);

	}

	/**
	 * Remove a value in a {@code boolean[]}.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void removeWithoutFallback() {

		boolean[] values = new boolean[] { true };
		Iterator<Boolean> iterator = new BooleanArrayIterable(values).iterator();

		iterator.next();
		iterator.remove();

	}

}
