package com.katariasoft.technologies.jpaHibernate.college.data.utils;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.util.Assert;

public class CollectionUtils {

	private static Predicate<Object[]> mapOfInputsValidator = objects -> {
		return Objects.nonNull(objects) && objects.length > 0 && objects.length % 2 == 0;
	};

	public static Map<String, Object> mapOf(Object... objects) {
		Assert.isTrue(mapOfInputsValidator.test(objects), "Invalid input : Even number of input params required.");
		List<SimpleEntry<String, Object>> simpleEntries = new ArrayList<>();
		for (int i = 0; i < objects.length; i = i + 2)
			simpleEntries.add(new SimpleEntry<String, Object>(objects[i].toString(), objects[i + 1]));
		return simpleEntries.stream().collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue));
	}

}
