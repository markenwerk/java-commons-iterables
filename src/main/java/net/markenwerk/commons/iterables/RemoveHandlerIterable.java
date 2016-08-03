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

import java.util.Iterator;

import net.markenwerk.commons.interfaces.Handler;
import net.markenwerk.commons.interfaces.Predicate;
import net.markenwerk.commons.iterators.RemoveHandlerIterator;

/**
 * A {@link RemoveHandlerIterable} is an {@link Iterable} that can be wrapped
 * around a given {@link Iterable} and generates {@link Iterator Iterators} that
 * filters out values according to a given {@link Predicate}.
 * 
 * @param <Payload>
 *            The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 2.2.0
 */
public final class RemoveHandlerIterable<Payload> implements Iterable<Payload> {

	private final Iterable<? extends Payload> iterable;

	private final Handler<? super Payload> removeHandler;

	/**
	 * Creates a new {@link RemoveHandlerIterable}.
	 * 
	 * @param iterable
	 *            The {@link Iterable} to iterate over.
	 * @param removeHandler
	 *            The {@link Handler} to be used.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link Iterable} is {@literal null} or if the
	 *             given {@link Handler} is {@literal null}.
	 */
	public RemoveHandlerIterable(Iterable<? extends Payload> iterable, Handler<? super Payload> removeHandler)
			throws IllegalArgumentException {
		if (null == iterable) {
			throw new IllegalArgumentException("The given iterable is null");
		}
		if (null == removeHandler) {
			throw new IllegalArgumentException("The given handler is null");
		}
		this.iterable = iterable;
		this.removeHandler = removeHandler;
	}

	@Override
	public RemoveHandlerIterator<Payload> iterator() {
		return new RemoveHandlerIterator<Payload>(iterable.iterator(), removeHandler);
	}

}
