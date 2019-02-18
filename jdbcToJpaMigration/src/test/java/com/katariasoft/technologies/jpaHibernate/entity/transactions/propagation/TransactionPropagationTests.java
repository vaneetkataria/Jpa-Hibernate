package com.katariasoft.technologies.jpaHibernate.entity.transactions.propagation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.katariasoft.technologies.jpaHibernate.college.data.transaction.propagation.TransactionPropagationStarters;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionPropagationTests {

	@Autowired
	private TransactionPropagationStarters txStarters;

	@Test
	public void testRequiredWithSelfSucceedSubsequentSucceed() {
		txStarters.testRequiredWithSelfSucceedSubsequentSucceed();
	}

	@Test
	public void testRequiredWithSelfSucceedSubsequentFail() {
		txStarters.testRequiredWithSelfSucceedSubsequentFail();
	}

	@Test
	public void testRequiredWithSelfFailSubsequentSucceed() {
		txStarters.testRequiredWithSelfFailSubsequentSucceed();
	}

	@Test
	public void testRequiredWithSelfFailSubsequentFail() {
		txStarters.testRequiredWithSelfFailSubsequentFail();
	}

}
