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

import net.markenwerk.commons.iterables.CountUpIterable;
import net.markenwerk.commons.iterators.CountUpIterator;

/**
 * JUnit test for {@link CountUpIterable}.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public class CountUpIterableTests {

	/**
	 * Count up from a lower bound that is larger than the upper bound.
	 */
	@Test
	public void countUp_lowerBoundLargerThanUpperBound() {

		Iterator<Integer> iterator = new CountUpIterable(2, 1).iterator();

		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Count up from a lower bound that equals the upper bound.
	 */
	@Test
	public void countUp_lowerBoundEqualsUpperBound() {

		Iterator<Integer> iterator = new CountUpIterable(0, 0).iterator();

		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(new Integer(0), iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Count up from a lower bound that is smaller than the upper bound.
	 */
	@Test
	public void countUp_lowerBoundSmallerThanUpperBound() {

		Iterator<Integer> iterator = new CountUpIterable(1, 2).iterator();

		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(new Integer(1), iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(new Integer(2), iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Remove a value from a {@link CountUpIterator}.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void countUp_remove() {

		Iterator<Integer> iterator = new CountUpIterable(1, 2).iterator();
		iterator.remove();

	}

}