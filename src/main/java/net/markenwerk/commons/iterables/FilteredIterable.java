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

import net.markenwerk.commons.interfaces.Predicate;
import net.markenwerk.commons.iterators.FilteredIterator;

/**
 * A {@link FilteredIterable} is an {@link Iterable} that can be wrapped around
 * a given {@link Iterable} and generates {@link Iterator Iterators} that
 * filters out values according to a given {@link Predicate}.
 * 
 * @param <Payload>
 *            The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public final class FilteredIterable<Payload> implements Iterable<Payload> {

	private final Iterable<? extends Payload> iterable;

	private final Predicate<? super Payload> predicate;

	private final boolean invertPredicate;

	/**
	 * Creates a new {@link FilteredIterable}.
	 * 
	 * @param iterable
	 *            The {@link Iterable} to iterate over.
	 * @param predicate
	 *            The {@link Predicate} to be used.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link Iterable} is {@literal null} or if the
	 *             given {@link Predicate} is {@literal null}.
	 */
	public FilteredIterable(Iterable<? extends Payload> iterable, Predicate<? super Payload> predicate)
			throws IllegalArgumentException {
		this(iterable, predicate, false);
	}

	/**
	 * Creates a new {@link FilteredIterable}.
	 * 
	 * @param iterable
	 *            The {@link Iterable} to iterate over.
	 * @param predicate
	 *            The {@link Predicate} to be used.
	 * @param invertPredicate
	 *            Whether to invert the test result and yield values that don't
	 *            satisfy the given {@link Predicate}.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link Iterable} is {@literal null} or if the
	 *             given {@link Predicate} is {@literal null}.
	 */
	public FilteredIterable(Iterable<? extends Payload> iterable, Predicate<? super Payload> predicate,
			boolean invertPredicate) throws IllegalArgumentException {
		if (null == iterable) {
			throw new IllegalArgumentException("The given iterable is null");
		} else if (null == predicate) {
			throw new IllegalArgumentException("The given predicate is null");
		}
		this.iterable = iterable;
		this.predicate = predicate;
		this.invertPredicate = invertPredicate;
	}

	@Override
	public FilteredIterator<Payload> iterator() {
		return new FilteredIterator<Payload>(iterable.iterator(), predicate, invertPredicate);
	}

}
