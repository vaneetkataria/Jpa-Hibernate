package com.katariasoft.technologies.jpaHibernate.entity.utilities;

public class ThreadUtil {

	public static void executeAsync(Runnable runnable) {
		new Thread(runnable).start();
	}

	public static void WAIT_MS(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
