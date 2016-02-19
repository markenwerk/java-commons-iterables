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

import org.junit.Assert;
import org.junit.Test;

import net.markenwerk.commons.iterables.CharacterArrayIterable;

/**
 * JUnit test for {@link CharacterArrayIterable}.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 */
public class CharacterArrayIterableTests {

	/**
	 * Iterate over a {@code char[]}.
	 */
	@Test
	public void charArray_iterate() {

		char[] values = new char[] { 1, 2 };
		Iterator<Character> iterator = new CharacterArrayIterable(values).iterator();

		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(Character.valueOf(values[0]), iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(Character.valueOf(values[1]), iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}
	
	/**
	 * Iterate over a {@code char[]} twice.
	 */
	@Test
	public void charArray_iterateTwice() {

		char[] values = new char[] { 1 };
		Iterable<Character> iterable = new CharacterArrayIterable(values);
		Iterator<Character> iterator = iterable.iterator();

		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(Character.valueOf(values[0]), iterator.next());
		Assert.assertFalse(iterator.hasNext());
		
		Iterator<Character> iterator2 = iterable.iterator();

		Assert.assertNotSame(iterator, iterator2);

		Assert.assertTrue(iterator2.hasNext());
		Assert.assertEquals(Character.valueOf(values[0]), iterator2.next());
		Assert.assertFalse(iterator2.hasNext());

	}

	/**
	 * Iterate over a {@code null} array.
	 */
	@Test
	public void characterArray_iterateNullArray() {

		Iterator<Character> iterator = new CharacterArrayIterable(null).iterator();

		Assert.assertFalse(iterator.hasNext());

	}

	/**
	 * Remove a value in a {@code char[]}.
	 */
	@Test
	public void charArray_removeWithFallback() {

		char replacement = 0;
		char[] values = new char[] { 1 };
		Iterator<Character> iterator = new CharacterArrayIterable(values, replacement).iterator();

		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(Character.valueOf(values[0]), iterator.next());
		Assert.assertFalse(iterator.hasNext());

		iterator.remove();

		Assert.assertEquals(replacement, values[0]);

	}

	/**
	 * Remove a value in a {@code char[]}.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void charArray_removeWithoutFallback() {

		char[] values = new char[] { 1 };
		Iterator<Character> iterator = new CharacterArrayIterable(values).iterator();

		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(Character.valueOf(values[0]), iterator.next());
		Assert.assertFalse(iterator.hasNext());

		iterator.remove();

	}

}
