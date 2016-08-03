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

import net.markenwerk.commons.iterators.SuffixedIterator;

/**
 * An {@link SuffixedIterable} is an {@link Iterable} that can be wrapped around
 * a given {@link Iterable} and generates {@link Iterator Iterators} that yield
 * some given suffix values after every object yielded by an {@link Iterator}
 * that is generated by the given {@link Iterable}.
 * 
 * @param <Payload>
 *            The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 2.2.0
 */
public final class SuffixedIterable<Payload> implements Iterable<Payload> {

	private final Iterable<? extends Payload> iterable;

	private final Payload[] suffixes;

	/**
	 * Creates a new {@link SuffixedIterable}.
	 * 
	 * @param iterable
	 *            The {@link Iterable} to iterate over.
	 * @param suffixes
	 *            The suffixes to be yielded.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link Iterable} is {@literal null}.
	 */
	public SuffixedIterable(Iterable<? extends Payload> iterable, Payload... suffixes) throws IllegalArgumentException {
		if (null == iterable) {
			throw new IllegalArgumentException("The given iterable is null");
		} else if (null == suffixes) {
			throw new IllegalArgumentException("The given array of suffixes is null");
		}
		this.iterable = iterable;
		this.suffixes = suffixes;
	}

	@Override
	public SuffixedIterator<Payload> iterator() {
		return new SuffixedIterator<Payload>(iterable.iterator(), suffixes);
	}

}
