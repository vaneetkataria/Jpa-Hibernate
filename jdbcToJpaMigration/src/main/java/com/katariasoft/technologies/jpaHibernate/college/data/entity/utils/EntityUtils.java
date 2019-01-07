package com.katariasoft.technologies.jpaHibernate.college.data.entity.utils;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.katariasoft.technologies.jpaHibernate.college.data.entity.IdProof;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Vehicle;
import com.katariasoft.technologies.jpaHibernate.college.data.enums.VechicleType;

public class EntityUtils {

	public static Supplier<Instructor> SINGLE_INSTRUCTOR_PROVIDER = EntityUtils::singleInstructorSupplierDef;
	public static Function<String, IdProof> SINGLE_ID_PROOF_PROVIDER = EntityUtils::idProofProviderDef;
	public static Function<String, Set<Vehicle>> MULTIPLE_VEHICLES_PROVIDER = EntityUtils::multiVehiclesProviderDef;
	private static final int numVehicles = 20;

	private static Instructor singleInstructorSupplierDef() {
		Instructor instructor = new Instructor("Vaneet", "Naresh", "Neemal", "Pankhon wali gali.", null,
				BigDecimal.valueOf(3.456), null, 0, LocalTime.now(), LocalTime.now(), null, null);
		instructor.setBirthDateTime(
				OffsetDateTime.parse("1990-12-30T08:57:30.1+05:30", DateTimeFormatter.ISO_OFFSET_DATE_TIME));
		return instructor;
	}

	private static IdProof idProofProviderDef(String proofSequenceNo) {
		Objects.requireNonNull(proofSequenceNo, "Proof Sequence Number cannot be null or empty.");
		return new IdProof(proofSequenceNo, "Name:" + proofSequenceNo, "Father Name:" + proofSequenceNo,
				"Mother Name:" + proofSequenceNo, "Address:" + proofSequenceNo, 'M', false, Instant.now(),
				Instant.now());
	}

	private static Set<Vehicle> multiVehiclesProviderDef(String vehicleNo) {
		return IntStream.iterate(0, i -> i + 1).limit(numVehicles)
				.mapToObj(i -> new Vehicle(VechicleType.TWO_WHEELER, vehicleNo + i,
						Instant.now().minus(10, ChronoUnit.DAYS), 19800, Instant.now(), Instant.now()))
				.collect(Collectors.toSet());
	}

}
