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

import net.markenwerk.commons.iterators.ByteArrayIterator;

/**
 * An {@link ByteArrayIterable} is a {@link Iterable} that generates
 * {@link Iterator Iterators} that iterate over a given {@code byte[]}.
 * 
 * <p>
 * Calling {@link ByteArrayIterable#iterator()} creates an instance of
 * {@link ByteArrayIterator}.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public final class ByteArrayIterable implements Iterable<Byte> {

	private final byte[] values;

	private final Byte replacement;

	/**
	 * Creates a new {@link ByteArrayIterable} for the given {@code byte[]}.
	 * 
	 * @param values
	 *            The {@code byte[]} to iterate over.
	 */
	public ByteArrayIterable(byte[] values) {
		this(values, null);
	}

	/**
	 * Creates a new {@link ByteArrayIterable} for the given {@code byte[]}.
	 * 
	 * @param values
	 *            The {@code byte[]} to iterate over.
	 * @param replacement
	 *            The value to replace removed values with.
	 */
	public ByteArrayIterable(byte[] values, byte replacement) {
		this(values, Byte.valueOf(replacement));
	}

	private ByteArrayIterable(byte[] values, Byte replacement) {
		this.values = values;
		this.replacement = replacement;
	}

	@Override
	public ByteArrayIterator iterator() {
		return null != replacement ? new ByteArrayIterator(values, replacement) : new ByteArrayIterator(values);
	}

}
