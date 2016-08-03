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
import java.util.StringTokenizer;

import net.markenwerk.commons.interfaces.Producer;
import net.markenwerk.commons.interfaces.Provider;
import net.markenwerk.commons.iterators.StringTokenizerIterator;

/**
 * An {@link StringTokenizerIterable} is a {@link ProtectedIterable} that can be
 * wrapped around a given {@link Producer} for {@link StringTokenizer
 * StringTokenizers} and generates {@link Iterator Iterators} that yield all
 * strings from a produced {@link StringTokenizer}.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.1.6
 */
public final class StringTokenizerIterable implements ProtectedIterable<String> {

	private final Provider<StringTokenizer> provider;

	/**
	 * Creates a new {@link StringTokenizerIterable}.
	 * 
	 * @param producer
	 *            The {@link Producer} for {@link StringTokenizer
	 *            StringTokenizers} to iterate over.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link Producer} for {@link StringTokenizer
	 *             StringTokenizers} is {@literal null}.
	 */
	public StringTokenizerIterable(Provider<StringTokenizer> producer) throws IllegalArgumentException {
		if (null == producer) {
			throw new IllegalArgumentException("The given provider is null");
		}
		this.provider = producer;
	}

	@Override
	public StringTokenizerIterator iterator() {
		return new StringTokenizerIterator(provider.provide());
	}

}
