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

import net.markenwerk.commons.interfaces.Predicate;

/**
 * JUnit test for {@link FilteringIterable}.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public class FilteringIterableTests {

	private final Object UNSATISFYING_OBJECT = new Object();

	private final Predicate<Object> UNSATISFYING_OBJECT_PREDICATE = new Predicate<Object>() {
		@Override
		public boolean test(Object object) {
			return null == object || UNSATISFYING_OBJECT != object;
		}
	};

	/**
	 * Filter out a unsatisfying value at the front of the underlying
	 * {@link Iterable}.
	 */
	@Test
	public void filtering_unsatisfyingAtFront() {

		Object[] values = new Object[] { UNSATISFYING_OBJECT, new Object() };
		Iterator<Object> iterator = new FilteringIterable<Object>(new ArrayIterable<Object>(values),
				UNSATISFYING_OBJECT_PREDICATE).iterator();

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[1], iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Filter out a unsatisfying value in the middle of the underlying
	 * {@link Iterable}.
	 */
	@Test
	public void filtering_unsatisfyingInMiddle() {

		Object[] values = new Object[] { new Object(), UNSATISFYING_OBJECT, new Object() };
		Iterator<Object> iterator = new FilteringIterable<Object>(new ArrayIterable<Object>(values),
				UNSATISFYING_OBJECT_PREDICATE).iterator();

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[0], iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[2], iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Filter out a unsatisfying value at the end of the underlying
	 * {@link Iterable}.
	 */
	@Test
	public void filtering_unsatisfyingAtEnd() {

		Object[] values = new Object[] { new Object(), UNSATISFYING_OBJECT };
		Iterator<Object> iterator = new FilteringIterable<Object>(new ArrayIterable<Object>(values),
				UNSATISFYING_OBJECT_PREDICATE).iterator();

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[0], iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Filter out a unsatisfying value from the underlying {@link Iterable}
	 * twice.
	 */
	@Test
	public void filtering_iterateTwice() {

		Object[] values = new Object[] { UNSATISFYING_OBJECT, new Object() };
		Iterable<Object> iterable = new FilteringIterable<Object>(new ArrayIterable<Object>(values),
				UNSATISFYING_OBJECT_PREDICATE);
		Iterator<Object> iterator = iterable.iterator();

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[1], iterator.next());
		Assert.assertFalse(iterator.hasNext());

		Iterator<Object> iterator2 = iterable.iterator();

		Assert.assertNotSame(iterator, iterator2);

		Assert.assertTrue(iterator2.hasNext());
		Assert.assertSame(values[1], iterator2.next());
		Assert.assertFalse(iterator2.hasNext());

	}

	/**
	 * Filter out a unsatisfying value in the middle of the underlying
	 * {@link Iterable}.
	 */
	@Test
	public void filtering_invertedPredicate() {

		Object[] values = new Object[] { new Object(), UNSATISFYING_OBJECT, new Object() };
		Iterator<Object> iterator = new FilteringIterable<Object>(new ArrayIterable<Object>(values),
				UNSATISFYING_OBJECT_PREDICATE, true).iterator();

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(UNSATISFYING_OBJECT, iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Remove an object from the underlying {@link Iterable}.
	 */
	@Test
	public void filtering_remove() {

		Object replacement = new Object();
		Object[] values = new Object[] { new Object() };
		Iterator<Object> iterator = new FilteringIterable<Object>(new ArrayIterable<Object>(values, replacement),
				UNSATISFYING_OBJECT_PREDICATE).iterator();

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(values[0], iterator.next());
		Assert.assertFalse(iterator.hasNext());

		iterator.remove();

		Assert.assertSame(replacement, values[0]);

	}

}