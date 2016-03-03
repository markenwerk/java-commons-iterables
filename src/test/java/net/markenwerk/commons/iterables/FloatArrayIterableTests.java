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

import net.markenwerk.commons.iterables.FloatArrayIterable;

/**
 * JUnit test for {@link FloatArrayIterable}.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 */
public class FloatArrayIterableTests {

	/**
	 * Iterate over a {@code float[]}.
	 */
	@Test
	public void iterate() {

		float[] values = new float[] { 1, 2 };
		Iterator<Float> iterator = new FloatArrayIterable(values).iterator();

		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(Float.valueOf(values[0]), iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(Float.valueOf(values[1]), iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}
	
	
	/**
	 * Iterate over a {@code float[]} twice.
	 */
	@Test
	public void iterateTwice() {

		float[] values = new float[] { 1 };
		Iterable<Float> iterable = new FloatArrayIterable(values);
		Iterator<Float> iterator = iterable.iterator();

		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(Float.valueOf(values[0]), iterator.next());
		Assert.assertFalse(iterator.hasNext());
		
		Iterator<Float> iterator2 = iterable.iterator();

		Assert.assertNotSame(iterator, iterator2);

		Assert.assertTrue(iterator2.hasNext());
		Assert.assertEquals(Float.valueOf(values[0]), iterator2.next());
		Assert.assertFalse(iterator2.hasNext());

	}

	/**
	 * Iterate over a {@code null} array.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void iterateNullArray() {

		new FloatArrayIterable(null);

	}

	/**
	 * Remove a value in a {@code float[]}.
	 */
	@Test
	public void removeWithFallback() {

		float replacement = 0;
		float[] values = new float[] { 1 };
		Iterator<Float> iterator = new FloatArrayIterable(values, replacement).iterator();

		iterator.next();
		iterator.remove();
		
		Assert.assertEquals(replacement, values[0], 0);

	}

	/**
	 * Remove a value in a {@code float[]}.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void removeWithoutFallback() {

		float[] values = new float[] { 1 };
		Iterator<Float> iterator = new FloatArrayIterable(values).iterator();

		iterator.next();
		iterator.remove();

	}

}
