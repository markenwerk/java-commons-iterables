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

import net.markenwerk.commons.iterators.ObjectIterator;

/**
 * An {@link ObjectIterable} is a {@link ProtectedBidirectionalIterable} that
 * generates {@link Iterator Iterators} that iterate over a single payload
 * object.
 * 
 * @param <Payload>
 *            The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.1.5
 */
public final class ObjectIterable<Payload> implements ProtectedBidirectionalIterable<Payload> {

	private final Payload value;

	private final boolean ignoreNull;

	/**
	 * Creates a new {@link ObjectIterable}.
	 * 
	 * @param value
	 *            The object to iterate over.
	 */
	public ObjectIterable(Payload value) {
		this(value, false);
	}

	/**
	 * Creates a new {@link ObjectIterable}.
	 * 
	 * @param value
	 *            The object to iterate over.
	 * @param ignoreNull
	 *            Whether to ignore {@literal null} values.
	 */
	public ObjectIterable(Payload value, boolean ignoreNull) {
		this.value = value;
		this.ignoreNull = ignoreNull;
	}

	@Override
	public ObjectIterator<Payload> iterator() {
		return new ObjectIterator<Payload>(value, ignoreNull);
	}

}
