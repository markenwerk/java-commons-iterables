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

import net.markenwerk.commons.iterators.CountDownIterator;

/**
 * JUnit test for {@link CountDownIterable}.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 */
public class CountDownIterableTests {

	/**
	 * Count down from an upper bound that is smaller than the lower bound.
	 */
	@Test
	public void countDown_upperBoundSmallerThanLowerBound() {

		Iterator<Integer> iterator = new CountDownIterable(1, 2).iterator();

		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Count down from an upper bound that equals the lower bound.
	 */
	@Test
	public void countDown_upperBoundEqualsLowerBound() {

		Iterator<Integer> iterator = new CountDownIterable(0, 0).iterator();

		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(Integer.valueOf(0), iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Count down from an upper bound that is larger than the lower bound.
	 */
	@Test
	public void countDown_upperBoundLargerThanLowerBound() {

		Iterator<Integer> iterator = new CountDownIterable(2, 1).iterator();

		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(Integer.valueOf(2), iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(Integer.valueOf(1), iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Count down from an upper bound that is larger than the lower bound twice.
	 */
	@Test
	public void countDown_iterateTwice() {

		Iterable<Integer> iterable = new CountDownIterable(2, 1);
		Iterator<Integer> iterator = iterable.iterator();

		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(Integer.valueOf(2), iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(Integer.valueOf(1), iterator.next());
		Assert.assertFalse(iterator.hasNext());

		Iterator<Integer> iterator2 = iterable.iterator();

		Assert.assertNotSame(iterator, iterator2);

		Assert.assertTrue(iterator2.hasNext());
		Assert.assertEquals(Integer.valueOf(2), iterator2.next());
		Assert.assertTrue(iterator2.hasNext());
		Assert.assertEquals(Integer.valueOf(1), iterator2.next());
		Assert.assertFalse(iterator2.hasNext());

	}

	/**
	 * Remove a value from a {@link CountDownIterator}.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void countUp_remove() {

		Iterator<Integer> iterator = new CountDownIterable(1, 2).iterator();
		iterator.remove();

	}

}
