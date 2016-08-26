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

import org.w3c.dom.NodeList;

import net.markenwerk.commons.datastructures.Optional;
import net.markenwerk.commons.iterators.OptionalIterator;

/**
 * A {@link OptionalIterable} is a {@link ProtectedBidirectionalIterable} that
 * generates {@link Iterator Iterators} that iterate over a given
 * {@link NodeList}.
 * 
 * @param <Payload>
 *            The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 3.1.0
 */
public final class OptionalIterable<Payload> implements ProtectedBidirectionalIterable<Payload> {

	private final Optional<Payload> optional;

	/**
	 * Creates a new {@link OptionalIterable}.
	 * 
	 * @param optional
	 *            The {@link Optional} to iterate over.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link Optional} is {@literal null}.
	 */
	public OptionalIterable(Optional<Payload> optional) throws IllegalArgumentException {
		if (null == optional) {
			throw new IllegalArgumentException("The given optional is null");
		}
		this.optional = optional;
	}

	@Override
	public OptionalIterator<Payload> iterator() {
		return new OptionalIterator<Payload>(optional);
	}

}
