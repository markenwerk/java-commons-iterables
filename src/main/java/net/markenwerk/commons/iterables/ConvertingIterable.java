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
import net.markenwerk.commons.iterators.ConvertingIterator;

/**
 * A {@link ConvertingIterable} is an {@link Iterable} that can be wrapped
 * around a given {@link Iterable} and generates {@link Iterator Iterators} that
 * converts all values with a given {@link Converter}.
 * 
 * @param <From>
 *            The type to convert values from.
 * @param <To>
 *            The type to convert values to.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public final class ConvertingIterable<From, To> implements Iterable<To> {

	private final Iterable<? extends From> iterable;

	private final Converter<? super From, ? extends To> converter;

	/**
	 * Creates a new {@link ConvertingIterable}.
	 * 
	 * @param iterable
	 *            The {@link Iterable} to iterate over.
	 * @param converter
	 *            The {@link Converter} to be used.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link Iterable} is {@literal null} or if the
	 *             given {@link Converter} is {@literal null}.
	 */
	public ConvertingIterable(Iterable<? extends From> iterable, Converter<? super From, ? extends To> converter)
			throws IllegalArgumentException {
		if (null == iterable) {
			throw new IllegalArgumentException("The given iterable is null");
		} else if (null == converter) {
			throw new IllegalArgumentException("The given converter is null");
		}
		this.iterable = iterable;
		this.converter = converter;
	}

	@Override
	public ConvertingIterator<From, To> iterator() {
		return new ConvertingIterator<From, To>(iterable.iterator(), converter);
	}

}
