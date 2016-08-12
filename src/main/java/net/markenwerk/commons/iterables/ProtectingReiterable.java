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

import net.markenwerk.commons.iterators.ProtectedReiterator;
import net.markenwerk.commons.iterators.ProtectingReiterator;

/**
 * A {@link ProtectingReiterable} is a {@link ProtectedReiterable} that can be
 * wrapped around a given {@link Reiterable} and generates that every call to
 * {@linkplain ProtectingReiterable#iterator()} yields a
 * {@link ProtectedReiterator}.
 * 
 * @param <Payload>
 *            The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 3.3.1
 */
public final class ProtectingReiterable<Payload> implements ProtectedReiterable<Payload> {

	private final Reiterable<Payload> iterable;

	/**
	 * Creates a new {@link ProtectingReiterable}.
	 * 
	 * @param iterable
	 *            The {@link Reiterable} to iterate over.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link Reiterable} is {@literal null}.
	 */
	public ProtectingReiterable(Reiterable<Payload> iterable) throws IllegalArgumentException {
		if (null == iterable) {
			throw new IllegalArgumentException("The given iterable is null");
		}
		this.iterable = iterable;
	}

	@Override
	public ProtectingReiterator<Payload> iterator() {
		return new ProtectingReiterator<Payload>(iterable.iterator());
	}

}
