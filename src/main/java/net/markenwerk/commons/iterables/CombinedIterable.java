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

import net.markenwerk.commons.interfaces.Converter;
import net.markenwerk.commons.iterators.CombinedIterator;
import net.markenwerk.commons.iterators.ConvertingIterator;

/**
 * A {@link CombinedIterable} is an {@link Iterable} that wraps around some
 * given {@link Iterable Iterables} and combines them into a single
 * {@link Iterable} by iterating over all given {@link Iterable Iterables} in
 * the order they were given.
 * 
 * @param <Payload>
 *            The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public final class CombinedIterable<Payload> implements Iterable<Payload> {

	private final Iterable<? extends Iterable<? extends Payload>> iterables;

	/**
	 * Creates a new {@link CombinedIterable}.
	 * 
	 * @param iterables
	 *            The sequence of {@link Iterable Iterables} to iterate over.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given sequence of {@link Iterable Iterables} is
	 *             {@literal null}.
	 */
	public CombinedIterable(Iterable<? extends Payload>... iterables) {
		if (null == iterables) {
			throw new IllegalArgumentException("The given array of iterables is null");
		}
		this.iterables = new ArrayIterable<Iterable<? extends Payload>>(iterables);
	}

	/**
	 * Creates a new {@link CombinedIterable}.
	 * 
	 * @param iterable
	 *            The {@link Iterable} of {@link Iterable Iterables} to iterate over.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link Iterable} of {@link Iterable Iterables}
	 *             is {@literal null}.
	 */
	public CombinedIterable(Iterable<? extends Iterable<? extends Payload>> iterable) throws IllegalArgumentException {
		if (null == iterable) {
			throw new IllegalArgumentException("The given iterable of iterable is null");
		}
		this.iterables = iterable;
	}

	@Override
	public CombinedIterator<Payload> iterator() {
		// @formatter:off
		return new CombinedIterator<Payload>(
			new ConvertingIterator<Iterable<? extends Payload>, Iterator<? extends Payload>>(
				iterables.iterator(),
				new Converter<Iterable<? extends Payload>, Iterator<? extends Payload>>() {
					@Override
					public Iterator<? extends Payload> convert(Iterable<? extends Payload> iterable) {
						return iterable.iterator();
					}
				}
			)
		);
		// @formatter:on
	}

}
