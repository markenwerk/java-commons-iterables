# Some common iterables for Java

[![Build Status](https://travis-ci.org/markenwerk/java-commons-iterables.svg?branch=master)](https://travis-ci.org/markenwerk/java-commons-iterables)
[![Coverage Status](https://coveralls.io/repos/markenwerk/java-commons-iterables/badge.svg?branch=master&service=github)](https://coveralls.io/github/markenwerk/java-commons-iterables?branch=master)
[![Dependency Status](https://www.versioneye.com/user/projects/56323f1636d0ab0016001bc4/badge.svg)](https://www.versioneye.com/user/projects/56323f1636d0ab0016001bc4)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/net.markenwerk/java-commons-iterables/badge.svg)](https://maven-badges.herokuapp.com/maven-central/net.markenwerk/java-commons-iterables)

This is a collection of companion iterators for [some commonly useful `Iterable` implementations](https://github.com/markenwerk/java-commons-iterators).

## Overview

This library is hosted in the [Maven Central Repositoy](http://search.maven.org/#artifactdetails|net.markenwerk|java-commons-iterables|1.1.0|jar). You can use it with the following coordinates:

```xml
<dependency>
	<groupId>net.markenwerk</groupId>
	<artifactId>java-commons-iterables</artifactId>
	<version>1.1.0</version>
</dependency>
```

Consult the [usage description](#usage) and [Javadoc](http://markenwerk.github.io/java-commons-iterables/javadoc/1.1.0/index.html) for further information.

## Usage

### Filtering and Converting

This library provides the generic [`FilteringIterable`][FilteringIterable] that takes an existing [`Iterable`][Iterable] and a [`Predicate`][Predicate] and filters out all values yielded by the given [`Iterable`][Iterable] that don't satisfy the given [Predicate][`Predicate`].

```java
Iterable<Foo> iterable = ...
Iterable<Foo> filteringIterable = new filteringIterable<Foo>(iterable, new Predicate<Foo>(){

	@Override
	public boolean test(Object object) throws PredicateException {
		return ...; // some condition
	}

});
```

This library provides the generic [`ConvertingIterable`][ConvertingIterable] that takes an existing [`Iterable`][Iterable] and a [`Converter`][Converter] and converts all values yielded by the given [`Iterable`][Iterable] and yields the converted values. 

```java
Iterable<Foo> iterable = ...
Iterable<Bar> convertingIterable = new ConvertingIterable<Foo, Bar>(iterable, new Converter<Foo, Bar>(){

	@Override
	public Bar convert(Foo foo) throws ConverterException {
		return ...; // some conversion
	}

});
```

### Combining multiple Iterators

This library provides the generic [`CombinedIterable`][CombinedIterable] that takes multiple existing [`Iterables`][Iterable] and combines them into a single [`Iterable`][Iterable] that yields all values of all given [`Iterables`][Iterable].

It can be constructed from an array or an arbitrary amount of known [`Iterables`][Iterable].

```java
Iterable<Foo> iterable1 = ...
Iterable<Foo> iterable2 = ...
Iterable<Foo> combinedIterable = new CombinedIterable<Foo>(iterable1, iterable2);
```

It can be constructed from an [`Iterable`][Iterable] of [`Iterables`][Iterable] (i.e. a [`List`][List]).

```java
List<Iterable<Foo>>[] iterables = ...
Iterable<Foo> combinedIterable = new CombinedIterable<Foo>(iterables);
```

### Counting

This library provides the [`CountDownIterable`][CountDownIterable] and [`CountUpIterable`][CountUpIterable] that take two integer values as bounds and yield every integer value between the given bounds, going downwards or upwards respectively.

```java
Iterable<Integer> = new CountDownIterable(10, 0); // yields 10, 9, ..., 1, 0
Iterable<Integer> = new CountUpIterable(0, 10); // yields 0, 1, ..., 9, 10
```

### Handling of `null` values

For situations where it is necessary to provide an [`Iterable`][Iterable], but no meaningful [`Iterable`][Iterable] is available, it might be useful to create an [`EmptyIterable`][EmptyIterable]. An [`EmptyIterable`][EmptyIterable] generates [`Iterators`][Iterator] that don't yield any values, buf fulfil the [`Iterator`][Iterator] contract.

```java
Iterable<Foo> = new EmptyIterable<Foo>();
```

For situations where some [`Iterable`][Iterable] is obtained and passed along, it might be useful to wrap the obtained [`Iterable`][Iterable] in a [`NullSaveIterable`][NullSaveIterable]. A [`NullSaveIterable`][NullSaveIterable] always generates an [`Iterator`][Iterator], even if it is constructed from a `null` [`Iterable`][Iterable].

```java
Iterable<Foo> iterable = ...
iterable = new NullSaveIterable(iterable);
```

For situations where some [`Iterable`][Iterable] is obtained, it might be useful to wrap the obtained [`Iterable`][Iterable] in a [`NullFreeIterable`][NullFreeIterable]. A [`NullFreeIterable`][NullFreeIterable] generates [`Iterators`][Iterators] that tilter out all `null` values yielded by the obtained [`Iterable`][Iterable].

```java
Iterable<Foo> iterable = ...
iterable = new NullFreeIterable(iterable);
```

### Iterating over primitive Arrays

This library provides the following [`Iterables`][Iterable] that takes an existing primitive array and generate [`Iterators`][Iterator] that yield all elements of the given array:

- [`BooleanArrayIterable`][BooleanArrayIterable]
- [`ByteArrayIterable`][ByteArrayIterable]
- [`CharacterArrayIterable`][CharacterArrayIterable]
- [`DoubleArrayIterable`][DoubleArrayIterable]
- [`FloatArrayIterable`][FloatArrayIterable]
- [`IntegerArrayIterable`][IntegerArrayIterable]
- [`LongArrayIterable`][LongArrayIterable]
- [`ShortArrayIterable`][ShortArrayIterable]

```java
boolean[] booleans = ...
Iterable<Boolean> = new BooleanArrayIterable(booleans);
```

### Iterating over non-primitive Arrays

This library provides the generic [`ArrayIterable`][ArrayIterable] that takes an existing array and generate [`Iterators`][Iterator] that yield all elements of the given array:

```java
Foo[] foos = ...
Iterable<Foo> = new ArrayIterable(foos);
```

[ArrayIterable]: http://markenwerk.github.io/java-commons-iterables/javadoc/1.1.0/index.html?net/markenwerk/commons/iterables/ArrayIterable.html
[BooleanArrayIterable]: http://markenwerk.github.io/java-commons-iterables/javadoc/1.1.0/index.html?net/markenwerk/commons/iterables/BooleanArrayIterable.html
[ByteArrayIterable]: http://markenwerk.github.io/java-commons-iterables/javadoc/1.1.0/index.html?net/markenwerk/commons/iterables/ByteArrayIterable.html
[CharacterArrayIterable]: http://markenwerk.github.io/java-commons-iterables/javadoc/1.1.0/index.html?net/markenwerk/commons/iterables/CharacterArrayIterable.html
[CombinedIterable]: http://markenwerk.github.io/java-commons-iterables/javadoc/1.1.0/index.html?net/markenwerk/commons/iterables/CombinedIterable.html
[ConvertingIterable]: http://markenwerk.github.io/java-commons-iterables/javadoc/1.1.0/index.html?net/markenwerk/commons/iterables/ConvertingIterable.html
[CountDownIterable]: http://markenwerk.github.io/java-commons-iterables/javadoc/1.1.0/index.html?net/markenwerk/commons/iterables/CountDownIterable.html
[CountUpIterable]: http://markenwerk.github.io/java-commons-iterables/javadoc/1.1.0/index.html?net/markenwerk/commons/iterables/CountUpIterable.html
[DoubleArrayIterable]: http://markenwerk.github.io/java-commons-iterables/javadoc/1.1.0/index.html?net/markenwerk/commons/iterables/DoubleArrayIterable.html
[EmptyIterable]: http://markenwerk.github.io/java-commons-iterables/javadoc/1.1.0/index.html?net/markenwerk/commons/iterables/EmptyIterable.html
[FilteringIterable]: http://markenwerk.github.io/java-commons-iterables/javadoc/1.1.0/index.html?net/markenwerk/commons/iterables/FilteringIterable.html
[FloatArrayIterable]: http://markenwerk.github.io/java-commons-iterables/javadoc/1.1.0/index.html?net/markenwerk/commons/iterables/FloatArrayIterable.html
[IntegerArrayIterable]: http://markenwerk.github.io/java-commons-iterables/javadoc/1.1.0/index.html?net/markenwerk/commons/iterables/IntegerArrayIterable.html
[LongArrayIterable]: http://markenwerk.github.io/java-commons-iterables/javadoc/1.1.0/index.html?net/markenwerk/commons/iterables/LongArrayIterable.html
[NullFreeIterable]: http://markenwerk.github.io/java-commons-iterables/javadoc/1.1.0/index.html?net/markenwerk/commons/iterables/NullFreeIterable.html
[NullSaveIterable]: http://markenwerk.github.io/java-commons-iterables/javadoc/1.1.0/index.html?net/markenwerk/commons/iterables/NullSaveIterable.html
[ShortArrayIterable]: http://markenwerk.github.io/java-commons-iterables/javadoc/1.1.0/index.html?net/markenwerk/commons/iterables/ShortArrayIterable.html

[Converter]: http://markenwerk.github.io/java-commons-interfaces/javadoc/1.1.0/index.html?net/markenwerk/commons/interfaces/Converter.html
[Predicate]: http://markenwerk.github.io/java-commons-interfaces/javadoc/1.1.0/index.html?net/markenwerk/commons/interfaces/Predicate.html

[Iterable]: http://docs.oracle.com/javase/6/docs/api/index.html?java/lang/Iterable.html
[Iterator]: http://docs.oracle.com/javase/6/docs/api/index.html?java/util/Iterator.html
[List]: http://docs.oracle.com/javase/6/docs/api/index.html?java/util/List.html
