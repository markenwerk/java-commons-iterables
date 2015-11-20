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

import net.markenwerk.commons.iterators.EmptyIterator;

/**
 * An {@link NullSaveIterable} is a {@link Iterable} that can be wrapped around
 * a given {@link Iterable} and generates {@link Iterator Iterators} that always
 * behaves correctly, even if the given {@link Iterable} is {@literal null} (by
 * generating an {@link EmptyIterator}).
 * 
 * @param <Payload>
 *            The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public final class NullSaveIterable<Payload> implements Iterable<Payload> {

	private final Iterable<Payload> iterable;

	/**
	 * Creates a new {@link NullSaveIterable} from the given {@link Iterable}.
	 * 
	 * @param iterable
	 *            The {@link Iterable}, around which the new
	 *            {@link NullSaveIterable} will be wrapped.
	 */
	public NullSaveIterable(Iterable<Payload> iterable) {
		this.iterable = iterable;
	}

	@Override
	public Iterator<Payload> iterator() {
		return null == iterator() ? new EmptyIterator<Payload>() : iterable.iterator();
	}

}
