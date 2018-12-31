package com.katariasoft.technologies.jpaHibernate.entity.utilities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

import org.junit.Test;

import com.katariasoft.technologies.jpaHibernate.college.data.utils.CollectionUtils;

public class CollectionUtilsTests {

	@Test
	public void test() {
		Map<String, Object> map = CollectionUtils.mapOf("id", 1, "bds", "red", "ks", new ArrayList<>(),
				new ArrayList<>(), new ArrayList<>());
		System.out.println(map);
	}

	@Test
	public void testForOddNumberOfParams() {
		Map<String, Object> map = CollectionUtils.mapOf("id", 1, "bds", "red", "ks", new ArrayList<>(),
				new ArrayList<>());
		System.out.println(map);
	}

	@Test
	public void testForNullParams() {
		Map<String, Object> map = CollectionUtils.mapOf(null);
		System.out.println(map);
	}

	@Test
	public void goodParamsTest() {
		Map<String, Object> map = CollectionUtils.mapOf("ids", new ArrayList<Integer>(), "salary",
				BigDecimal.valueOf(123.456));
		System.out.println(map);
	}

}
