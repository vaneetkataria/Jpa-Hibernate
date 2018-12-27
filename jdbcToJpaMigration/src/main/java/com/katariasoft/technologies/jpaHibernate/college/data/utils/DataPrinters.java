package com.katariasoft.technologies.jpaHibernate.college.data.utils;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class DataPrinters {

	public static Consumer<List<?>> listDataPrinter = l -> {
		if (Objects.nonNull(l) && !l.isEmpty())
			l.stream().forEach(System.out::println);
	};

	public static <T> void print(List<T> dataList) {
		if (Objects.nonNull(dataList) && !dataList.isEmpty())
			dataList.stream().forEach(System.out::println);
	}

}
