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

import net.markenwerk.commons.datastructures.Tuple;
import net.markenwerk.commons.iterators.TupleIterator;

/**
 * A {@link TupleIterable} is a {@link ProtectedIterable} that generates
 * {@link Iterator Iterators} that iterate over a given {@link NodeList}.
 * 
 * @param <Payload>
 *            The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 3.1.0
 */
public final class TupleIterable<Payload> implements ProtectedIterable<Payload> {

	private final Tuple<? extends Payload, ? extends Payload> tuple;

	/**
	 * Creates a new {@link TupleIterable}.
	 * 
	 * @param tuple
	 *            The {@link Tuple} to iterate over.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link Tuple} is {@literal null}.
	 */
	public TupleIterable(Tuple<? extends Payload, ? extends Payload> tuple) throws IllegalArgumentException {
		if (null == tuple) {
			throw new IllegalArgumentException("The given tuple is null");
		}
		this.tuple = tuple;
	}

	@Override
	public TupleIterator<Payload> iterator() {
		return new TupleIterator<Payload>(tuple);
	}

}
