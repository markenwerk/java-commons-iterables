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

import net.markenwerk.commons.iterators.IntegerArrayIterator;

/**
 * An {@link IntegerArrayIterable} is a {@link ProtectedIterable} that generates
 * {@link Iterator Iterators} that iterate over a given {@code int[]}.
 * 
 * <p>
 * Calling {@link IntegerArrayIterable#iterator()} creates an instance of
 * {@link IntegerArrayIterator}.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public final class IntegerArrayIterable implements ProtectedIterable<Integer> {

	private final int[] array;

	/**
	 * Creates a new {@link IntegerArrayIterable} for the given {@code int[]}.
	 * 
	 * @param array
	 *            The {@code int[]} to iterate over.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@code int[]} is {@literal null}.
	 */
	public IntegerArrayIterable(int[] array) throws IllegalArgumentException {
		if (null == array) {
			throw new IllegalArgumentException("array is null");
		}
		this.array = array;
	}

	@Override
	public IntegerArrayIterator iterator() {
		return new IntegerArrayIterator(array);
	}

}
