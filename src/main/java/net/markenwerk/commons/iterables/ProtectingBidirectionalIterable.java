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

import net.markenwerk.commons.iterators.ProtectedIterator;
import net.markenwerk.commons.iterators.ProtectingBidirectionalIterator;

/**
 * A {@link ProtectingBidirectionalIterable} is a
 * {@link ProtectedBidirectionalIterable} that can be wrapped around a given
 * {@link BidirectionalIterable} and generates that every call to
 * {@linkplain ProtectingBidirectionalIterable#iterator()} yields a
 * {@link ProtectedIterator}.
 * 
 * @param <Payload>
 *            The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 3.2.0
 */
public final class ProtectingBidirectionalIterable<Payload> implements ProtectedBidirectionalIterable<Payload> {

	private final BidirectionalIterable<Payload> iterable;

	/**
	 * Creates a new {@link ProtectingBidirectionalIterable}.
	 * 
	 * @param iterable
	 *            The {@link Iterable} to iterate over.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link Iterable} is {@literal null}.
	 */
	public ProtectingBidirectionalIterable(BidirectionalIterable<Payload> iterable) throws IllegalArgumentException {
		if (null == iterable) {
			throw new IllegalArgumentException("The given iterable is null");
		}
		this.iterable = iterable;
	}

	@Override
	public ProtectingBidirectionalIterator<Payload> iterator() {
		return new ProtectingBidirectionalIterator<Payload>(iterable.iterator());
	}

}
