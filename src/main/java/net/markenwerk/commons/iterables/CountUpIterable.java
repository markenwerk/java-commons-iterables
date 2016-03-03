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

import net.markenwerk.commons.iterators.CountUpIterator;

/**
 * An {@link CountUpIterable} is a {@link Iterable} that generates
 * {@link Iterator Iterators} that that yields all integer value between a given
 * lower value and a given upper bound.
 * 
 * <p>
 * Calling {@link CountUpIterable#iterator()} creates an instance of
 * {@link CountUpIterator}.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public final class CountUpIterable implements Iterable<Integer> {

	private final int fromLower;

	private final int toUpper;

	/**
	 * Creates a new {@link CountUpIterable} from the given lower and upper
	 * bound.
	 * 
	 * @param fromLower
	 *            The lower bound and first value to be yielded.
	 * @param toUpper
	 *            The upper bound and last value to be yielded.
	 */
	public CountUpIterable(int fromLower, int toUpper) {
		this.fromLower = fromLower;
		this.toUpper = toUpper;
	}

	@Override
	public CountUpIterator iterator() {
		return new CountUpIterator(fromLower, toUpper);
	}

}
