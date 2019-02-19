package com.katariasoft.technologies.jpaHibernate.entity.transactions.propagation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;

import com.katariasoft.technologies.jpaHibernate.college.data.transaction.propagation.TransactionPropagationStarters;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionPropagationTests {

	public static final int revision = 17;
	private static Propagation propagation = Propagation.NOT_SUPPORTED;

	@Autowired
	private TransactionPropagationStarters txStarters;

	// 00
	@Test
	public void testRequiredWithSelfFailSubsequentFail() {
		try {
			txStarters.testRequiredWithSelfFailSubsequentFail(revision, propagation);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 01
	@Test
	public void testRequiredWithSelfFailSubsequentSucceed() {
		try {
			txStarters.testRequiredWithSelfFailSubsequentSucceed(revision, propagation);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 10
	@Test
	public void testRequiredWithSelfSucceedSubsequentFail() {
		try {
			txStarters.testRequiredWithSelfSucceedSubsequentFail(revision, propagation);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 11
	@Test
	public void testRequiredWithSelfSucceedSubsequentSucceed() {
		try {
			txStarters.testRequiredWithSelfSucceedSubsequentSucceed(revision, propagation);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
