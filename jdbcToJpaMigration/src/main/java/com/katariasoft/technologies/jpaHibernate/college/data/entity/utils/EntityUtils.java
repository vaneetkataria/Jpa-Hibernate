package com.katariasoft.technologies.jpaHibernate.college.data.entity.utils;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor;

public class EntityUtils {

	private static Supplier<Instructor> singleInstructorSupplier = singleInstructorSupplierDef();

	private static Supplier<Instructor> singleInstructorSupplierDef() {
		return () -> {
			Instructor instructor = new Instructor("Vaneet", "Naresh", "Neemal", "Pankhon wali gali.", null,
					BigDecimal.valueOf(3.456), null, 0, LocalTime.now(), LocalTime.now(), null, null);
			instructor.setBirthDateTime(
					OffsetDateTime.parse("1990-12-30T08:57:30.1+05:30", DateTimeFormatter.ISO_OFFSET_DATE_TIME));
			return instructor;
		};
	}

	public static Supplier<Instructor> singleInstructorSupplier() {
		return singleInstructorSupplier;
	}

}
