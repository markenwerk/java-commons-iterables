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

import net.markenwerk.commons.interfaces.Converter;
import net.markenwerk.commons.iterators.ConvertingIterator;

/**
 * An {@link ConvertingIterable} is a {@link Iterable} that can be wrapped
 * around a given {@link Iterable} and generates {@link Iterator Iterators} that
 * converts all values with a given {@link Converter}.
 * 
 * 
 * 
 * <p>
 * Calling {@link ConvertingIterable#iterator()} creates an instance of
 * {@link ConvertingIterator}.
 * 
 * @param <From>
 *            The type to convert values from.
 * @param <To>
 *            The type to convert values to.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public final class ConvertingIterable<From, To> implements Iterable<To> {

	private final Iterable<From> iterable;

	private final Converter<From, To> converter;

	/**
	 * Creates a new {@link ConvertingIterable} from the given {@link Iterable}.
	 * 
	 * @param iterable
	 *            The {@link Iterable}, around which the new
	 *            {@link ConvertingIterable} will be wrapped.
	 * @param converter
	 *            The {@link Converter} to {@link Converter#convert(Object)
	 *            convert} every value yielded by the given {@link Iterable}
	 *            with.
	 */
	public ConvertingIterable(Iterable<From> iterable, Converter<From, To> converter) {
		this.iterable = iterable;
		this.converter = converter;
	}

	@Override
	public ConvertingIterator<From, To> iterator() {
		return new ConvertingIterator<From, To>(iterable.iterator(), converter);
	}

}
