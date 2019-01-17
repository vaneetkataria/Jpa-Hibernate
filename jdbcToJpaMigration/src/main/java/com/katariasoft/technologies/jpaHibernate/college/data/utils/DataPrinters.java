package com.katariasoft.technologies.jpaHibernate.college.data.utils;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class DataPrinters {

	public static Consumer<List<?>> listDataPrinter = DataPrinters::printList;

	public static <T> void print(List<T> dataList) {
		if (Objects.nonNull(dataList) && !dataList.isEmpty())
			dataList.stream().forEach(System.out::println);
	}

	public static void printList(List<?> list) {
		if (Objects.nonNull(list) && !list.isEmpty())
			list.stream().forEach(System.out::println);
	}

}
