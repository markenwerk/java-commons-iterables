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

import java.util.Enumeration;
import java.util.Iterator;

import net.markenwerk.commons.interfaces.Producer;
import net.markenwerk.commons.iterators.EnumerationIterator;

/**
 * An {@link EnumerationIterable} is a {@link ProtectedIterable} that can be
 * wrapped around a given {@link Producer} for {@link Enumeration Enumerations}
 * and generates {@link Iterator Iterators} that yield all values from a
 * produced {@link Enumeration}.
 * 
 * <p>
 * Calling {@link EnumerationIterable#iterator()} creates an instance of
 * {@link EnumerationIterator}.
 * 
 * @param <Payload>
 *           The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.1.6
 */
public final class EnumerationIterable<Payload> implements ProtectedIterable<Payload> {

	private final Enumeratable<? extends Payload> enumeratable;

	/**
	 * Creates a new {@link EnumerationIterable} from the given {@link Enumeratable}.
	 * 
	 * @param enumeratable
	 *            The {@link Enumeratable} for {@link Enumeration Enumerations},
	 *            around which the new {@link EnumerationIterable} will be
	 *            wrapped.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link Producer} for {@link Enumeration
	 *             Enumerations} is {@literal null}.
	 */
	public EnumerationIterable(Enumeratable<? extends Payload> enumeratable) throws IllegalArgumentException {
	if (null == enumeratable) {
			throw new IllegalArgumentException("enumeratable is null");
		}
		this.enumeratable = enumeratable;
	}

	@Override
	public EnumerationIterator<Payload> iterator() {
		return new EnumerationIterator<Payload>(enumeratable.enumeration());
	}

}
