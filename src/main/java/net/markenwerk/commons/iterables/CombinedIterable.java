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
import net.markenwerk.commons.interfaces.exceptions.ConverterException;
import net.markenwerk.commons.iterators.CombinedIterator;
import net.markenwerk.commons.iterators.ConvertingIterator;

/**
 * An {@link CombinedIterable} is a {@link Iterable} that wraps arround some
 * given {@link Iterable Iterables} and combines them into a single
 * {@link Iterable} by iterating over all given {@link Iterable Iterables} in
 * the order they were given.
 * 
 * <p>
 * Calling {@link CombinedIterable#iterator()} creates an instance of
 * {@link CombinedIterator}.
 * 
 * @param <Payload>
 *            The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public final class CombinedIterable<Payload> implements Iterable<Payload> {

	private final Iterator<Iterable<Payload>> iterables;

	/**
	 * Creates a new {@link CombinedIterable} from the given {@link Iterable
	 * Iterables}.
	 * 
	 * @param iterables
	 *            The {@link Iterable Iterables} to combine into a single
	 *            {@link Iterable}.
	 */
	public CombinedIterable(Iterator<Iterable<Payload>> iterables) {
		this.iterables = iterables;
	}

	@Override
	public Iterator<Payload> iterator() {
		return new CombinedIterator<Payload>(new ConvertingIterator<Iterable<Payload>, Iterator<Payload>>(iterables,
				new Converter<Iterable<Payload>, Iterator<Payload>>() {
					@Override
					public Iterator<Payload> convert(Iterable<Payload> iterable) throws ConverterException {
						return iterable.iterator();
					}
				}));
	}

}
