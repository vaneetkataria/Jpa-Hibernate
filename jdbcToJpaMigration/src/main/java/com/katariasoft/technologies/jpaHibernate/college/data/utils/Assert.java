package com.katariasoft.technologies.jpaHibernate.college.data.utils;

import java.util.function.Function;

import org.junit.Test;

public class Assert {

	public static <X extends Throwable> void isTrue(boolean expression, Function<String, ? extends X> execptionSupplier,
			String message) throws X {
		if (expression)
			throw execptionSupplier.apply(message);
	}

	@Test
	public void assertTest() {
		Assert.isTrue(5 < 3, RuntimeException::new, "Input must be less than three");
	}

}
