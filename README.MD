# Some common iterables for Java

[![Build Status](https://travis-ci.org/markenwerk/java-commons-iterables.svg?branch=master)](https://travis-ci.org/markenwerk/java-commons-iterables)
[![Coverage Status](https://coveralls.io/repos/markenwerk/java-commons-iterables/badge.svg?branch=master&service=github)](https://coveralls.io/github/markenwerk/java-commons-iterables?branch=master)
[![Dependency Status](https://www.versioneye.com/user/projects/564f37bbff016c0033000471/badge.svg)](https://www.versioneye.com/user/projects/564f37bbff016c0033000471)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/net.markenwerk/commons-iterables/badge.svg)](https://maven-badges.herokuapp.com/maven-central/net.markenwerk/commons-iterables)
[![Java SE 6](https://img.shields.io/badge/java-SE_6-brightgreen.svg)](http://docs.oracle.com/javase/6/docs/api/)
[![MIT License](https://img.shields.io/badge/license-MIT-brightgreen.svg)](https://github.com/markenwerk/java-commons-iterables/blob/master/LICENSE)

## Overview

This is a collection of companion iterables for [some commonly useful `Iterator` implementations](https://github.com/markenwerk/java-commons-iterators) for the following use cases:

- [Filtering and Converting](#filtering-and-converting)
- [Combining multiple `Iterators`](#combining-multiple-iterators)
- [Interspersing infix value](#interspersing-infix-value)
- [Looking ahead](#looking-ahead)
- [Handling of null values](#handling-of-null-values)
- [Iterating over single values](#iterating-over-single-values)
- [Iterating over primitive arrays](#iterating-over-non-primitive-arrays)
- [Iterating over non-primitive arrays](#iterating-over-primitive-arrays)
- [Counting up and down](#counting-up-and-down)

This library is hosted in the [Maven Central Repository](https://maven-badges.herokuapp.com/maven-central/net.markenwerk/commons-iterables). You can use it with the following coordinates:

```xml
<dependency>
	<groupId>net.markenwerk</groupId>
	<artifactId>java-commons-iterables</artifactId>
	<version>1.1.6</version>
</dependency>
```

Consult the [usage description](#usage) and [Javadoc](http://markenwerk.github.io/java-commons-iterables/index.html) for further information.

## Usage

### Filtering and Converting

This library provides the generic [`FilteringIterable`][FilteringIterable] that takes an existing [`Iterable`][Iterable] and a [`Predicate`][Predicate] and filters out all values yielded by the given [`Iterable`][Iterable] that don't satisfy the given [`Predicate`][Predicate].

```java
Iterable<Foo> iterable = ...

// generated iterable yield every foo from from iterator that matches the condition
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

// generated iterable yield a bar for every foo from from iterator
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

// generated iterable yield every foo from every iterator1 and iterator2
Iterable<Foo> combinedIterable = new CombinedIterable<Foo>(iterable1, iterable2);
```

It can be constructed from an [`Iterable`][Iterable] of [`Iterables`][Iterable] (i.e. a [`List`][List]).

```java
List<Iterable<Foo>>[] iterables = ...

// generated iterable yield every foo from every iterator from iterators
Iterable<Foo> combinedIterable = new CombinedIterable<Foo>(iterables);
```

### Interspersing infix value

This library provides the generic [`InfixedIterable`][InfixedIterable] that takes an existing [`Iterable`][Iterable] and a single value that is used as an infix and interspersed between all values yielded by the given [`Iterable`][Iterable]

```java
Foo infix = ...
Iterable<Foo> iterable = ...

// generated iterable yields every foo from iterator, interspersed with the infix
Iterable<Foo> infixedIterable = new InfixedIterable<Foo>(iterator, infix);
```

### Looking ahead

This library provides the generic [`LookAheadIterable`][LookAheadIterable] that takes an existing [`Iterable`][Iterable] and yield every value yielded by the given [`Iterable`][Iterable] wrapped in a [`LookAhead`][LookAhead] that also contains the next value. This allows to peak into the future, while iterating through the given [`Iterable`][Iterable].

```java
Iterable<Foo> iterable = ...

// generated iterator yields a look ahead for every foo from iterator
Iterable<LookAhead<Foo>> lookAheadIterable = new LookAheadIterable<Foo>(iterator);
```

A [`LookAheadIterable`][LookAheadIterable] can be used to easily perform actions between every element yielded by the original [`Iterable`][Iterable].

```java
for (LookAhead<Foo> lookAhead : lookAheadIterable) {
    // returns the current foo
    Foo currentFoo = lookAhead.get() 
    doForEveryFoo(currentFoo);
    // returns whether the look ahead has a next value
    if (lookAhead.hasNext()) {  
        // returns the next foo
        Foo nextFoo = lookAhead.getNext();
        doBetweenFoos(currentFoo, nextFoo);
    }  
}
```

### Handling of `null` values

For situations where it is necessary to provide an [`Iterable`][Iterable], but no meaningful [`Iterable`][Iterable] is available, it might be useful to create an [`EmptyIterable`][EmptyIterable]. An [`EmptyIterable`][EmptyIterable] generates [`Iterators`][Iterator] that don't yield any values, but fulfill the [`Iterator`][Iterator] contract.

```java
// generated iterable yield nothing
Iterable<Foo> emptyIterable = new EmptyIterable<Foo>();
```

For situations where some [`Iterable`][Iterable] is obtained and passed along, it might be useful to wrap the obtained [`Iterable`][Iterable] in a [`NullSaveIterable`][NullSaveIterable]. A [`NullSaveIterable`][NullSaveIterable] always generates an [`Iterator`][Iterator], even if it is constructed from a `null` [`Iterable`][Iterable].

```java
Iterable<Foo> iterable = ...

// generated iterable yield every foo from iterator of nothing, if iterator is null
Iterable<Foo> nullSaveIterable = new NullSaveIterable(iterable);
```

For situations where some [`Iterable`][Iterable] is obtained, it might be useful to wrap the obtained [`Iterable`][Iterable] in a [`NullFreeIterable`][NullFreeIterable]. A [`NullFreeIterable`][NullFreeIterable] generates [`Iterators`][Iterator] that tilter out all `null` values yielded by the obtained [`Iterable`][Iterable].

```java
Iterable<Foo> iterable = ...

// generated iterable yield every non-null foo from iterator
Iterable<Foo> nullFreeIterable = new NullFreeIterable(iterable);
```

### Iterating over single values

This library provides the [`ObjectIterable`][ObjectIterable] and [`OptionalIterable`][OptionalIterable] that take an existing object and generate [`Iterators`][Iterator] that yield the given object. An [`ObjectIterable`][ObjectIterable] generates [`Iterators`][Iterator] that always yields the given object, whereas an [`OptionalIterable`][OptionalIterable] generates [`Iterators`][Iterator] that only yields the given object, if it isn't `null`. 

```java
Foo foo = ...

// generated iterable yield foo, even if foo is null
Iterable<Foo> objectIterable = new ObjectIterable(foo); 

// generated iterable yield foo or nothing, if foo is null
Iterable<Foo> optionalIterable = new OptionalIterable(foo); 
```

### Iterating over primitive Arrays

This library provides the following [`Iterables`][Iterable] that take existing primitive arrays and generate [`Iterators`][Iterator] that yield all elements of the given array:

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

// generated iterables yield every boolean from booleans
Iterable<Boolean> arrayIterable = new BooleanArrayIterable(booleans);
```

### Iterating over non-primitive Arrays

This library provides the generic [`ArrayIterable`][ArrayIterable] that takes an existing array and generate [`Iterators`][Iterator] that yield all elements of the given array:

```java
Foo[] foos = ...

// generated iterables yield every foo from foos
Iterable<Foo> arrayIterable = new ArrayIterable(foos);
```

### Counting

This library provides the [`CountDownIterable`][CountDownIterable] and [`CountUpIterable`][CountUpIterable] that take two integer values as bounds and yield every integer value between the given bounds, going downwards or upwards respectively.

```java
// generated iterables yield 10, 9, ..., 1, 0
Iterable<Integer> countUpIterable = new CountDownIterable(10, 0);

// generated iterables yield 0, 1, ..., 9, 10
Iterable<Integer> countDownIterable = new CountUpIterable(0, 10); 
```

[ArrayIterable]: https://markenwerk.github.io/java-commons-iterables/index.html?net/markenwerk/commons/iterables/ArrayIterable.html
[BooleanArrayIterable]: https://markenwerk.github.io/java-commons-iterables/index.html?net/markenwerk/commons/iterables/BooleanArrayIterable.html
[ByteArrayIterable]: https://markenwerk.github.io/java-commons-iterables/index.html?net/markenwerk/commons/iterables/ByteArrayIterable.html
[CharacterArrayIterable]: https://markenwerk.github.io/java-commons-iterables/index.html?net/markenwerk/commons/iterables/CharacterArrayIterable.html
[CombinedIterable]: https://markenwerk.github.io/java-commons-iterables/index.html?net/markenwerk/commons/iterables/CombinedIterable.html
[ConvertingIterable]: https://markenwerk.github.io/java-commons-iterables/index.html?net/markenwerk/commons/iterables/ConvertingIterable.html
[CountDownIterable]: https://markenwerk.github.io/java-commons-iterables/index.html?net/markenwerk/commons/iterables/CountDownIterable.html
[CountUpIterable]: https://markenwerk.github.io/java-commons-iterables/index.html?net/markenwerk/commons/iterables/CountUpIterable.html
[DoubleArrayIterable]: https://markenwerk.github.io/java-commons-iterables/index.html?net/markenwerk/commons/iterables/DoubleArrayIterable.html
[EmptyIterable]: https://markenwerk.github.io/java-commons-iterables/index.html?net/markenwerk/commons/iterables/EmptyIterable.html
[FilteringIterable]: https://markenwerk.github.io/java-commons-iterables/index.html?net/markenwerk/commons/iterables/FilteringIterable.html
[FloatArrayIterable]: https://markenwerk.github.io/java-commons-iterables/index.html?net/markenwerk/commons/iterables/FloatArrayIterable.html
[InfixedIterable]: https://markenwerk.github.io/java-commons-iterables/index.html?net/markenwerk/commons/iterables/InfixedIterable.html
[IntegerArrayIterable]: https://markenwerk.github.io/java-commons-iterables/index.html?net/markenwerk/commons/iterables/IntegerArrayIterable.html
[LongArrayIterable]: https://markenwerk.github.io/java-commons-iterables/index.html?net/markenwerk/commons/iterables/LongArrayIterable.html
[LookAheadIterable]: https://markenwerk.github.io/java-commons-iterables/index.html?net/markenwerk/commons/iterables/LookAheadIterable.html
[NullFreeIterable]: https://markenwerk.github.io/java-commons-iterables/index.html?net/markenwerk/commons/iterables/NullFreeIterable.html
[NullSaveIterable]: https://markenwerk.github.io/java-commons-iterables/index.html?net/markenwerk/commons/iterables/NullSaveIterable.html
[ObjectIterable]: https://markenwerk.github.io/java-commons-iterables/index.html?net/markenwerk/commons/iterables/ObjectIterable.html
[OptionalIterable]: https://markenwerk.github.io/java-commons-iterables/index.html?net/markenwerk/commons/iterables/OptionalIterable.html
[ShortArrayIterable]: https://markenwerk.github.io/java-commons-iterables/index.html?net/markenwerk/commons/iterables/ShortArrayIterable.html

[LookAhead]: https://markenwerk.github.io/java-commons-iterators/index.html?net/markenwerk/commons/iterators/LookAhead.html

[Converter]: https://markenwerk.github.io/java-commons-interfaces/index.html?net/markenwerk/commons/interfaces/Converter.html
[Predicate]: https://markenwerk.github.io/java-commons-interfaces/index.html?net/markenwerk/commons/interfaces/Predicate.html

[Iterable]: http://docs.oracle.com/javase/8/docs/api/index.html?java/lang/Iterable.html
[Iterator]: http://docs.oracle.com/javase/8/docs/api/index.html?java/util/Iterator.html
[List]: http://docs.oracle.com/javase/8/docs/api/index.html?java/util/List.html
