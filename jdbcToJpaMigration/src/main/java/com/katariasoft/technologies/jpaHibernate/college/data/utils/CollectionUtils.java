package com.katariasoft.technologies.jpaHibernate.college.data.utils;

import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.util.Assert;

public class CollectionUtils {

	private static Predicate<Object[]> mapOfInputsValidator = objects -> {
		return Objects.nonNull(objects) && objects.length > 0 && objects.length % 2 == 0;
	};

	public static Map<String, Object> mapOf(Object... objects) {
		Assert.isTrue(mapOfInputsValidator.test(objects), "Invalid input : Even number of input params required.");
		return IntStream.iterate(0, i -> i + 2).limit((objects.length / 2)).peek(System.out::println)
				.peek(System.out::println)
				.mapToObj(i -> new SimpleEntry<String, Object>(objects[i].toString(), objects[i + 1]))
				.peek(s -> System.out.println(s.getKey() + "- " + s.getValue())).collect(Collectors
						.toMap(SimpleEntry::getKey, SimpleEntry::getValue, (o1, o2) -> new Object[] { o1, o2 }));
	}

}
