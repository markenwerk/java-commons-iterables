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

import net.markenwerk.commons.iterators.OptionalIterator;

/**
 * An {@link OptionalIterable} is a {@link Iterable} that behaves like an
 * {@link ObjectIterable} for a given payload object, or like an empty
 * {@link Iterable}, if the given payload object is {@literal null}.
 * 
 * <p>
 * Calling {@link OptionalIterable#iterator()} creates an instance of
 * {@link OptionalIterator}.
 * 
 * @param <Payload>
 *            The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public final class OptionalIterable<Payload> implements Iterable<Payload> {

	private final Payload value;

	/**
	 * Creates a new {@link OptionalIterable} for the given payload object.
	 * 
	 * @param value
	 *            The payload object to iterate over.
	 */
	public OptionalIterable(Payload value) {
		this.value = value;
	}

	@Override
	public Iterator<Payload> iterator() {
		return new OptionalIterator<Payload>(value);
	}

}