package net.markenwerk.commons.iterables;

import java.util.List;

import net.markenwerk.commons.iterators.ListIterator;
import net.markenwerk.commons.iterators.Reiterator;

public class ListIterable<Payload> implements Reiterable<Payload> {

	private final List<Payload> list;

	public ListIterable(List<Payload> list) throws IllegalArgumentException {
		if (null == list) {
			throw new IllegalArgumentException("The given list is null");
		}
		this.list = list;
	}

	@Override
	public Reiterator<Payload> iterator() {
		return new ListIterator<Payload>(list);
	}

}
