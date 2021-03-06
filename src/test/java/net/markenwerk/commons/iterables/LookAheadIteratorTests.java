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

import org.junit.Assert;
import org.junit.Test;

import net.markenwerk.commons.iterators.LookAhead;
import net.markenwerk.commons.iterators.LookAheadIterator;

@SuppressWarnings("javadoc")
public class LookAheadIteratorTests {

	@Test(expected = IllegalArgumentException.class)
	public void create_nullIterable() {

		new LookAheadIterator<Object>(null);

	}

	@Test
	public void iterator() {

		Iterable<LookAhead<Object>> iterable = new LookAheadIterable<Object>(new EmptyIterable<Object>());

		Assert.assertNotNull(iterable.iterator());

	}

	@Test
	public void iterator_twice() {

		Iterable<LookAhead<Object>> iterable = new LookAheadIterable<Object>(new EmptyIterable<Object>());

		Assert.assertNotSame(iterable.iterator(), iterable.iterator());

	}

}
