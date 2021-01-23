package com.aihaokeji.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Component
@Order(value = 1)   //执行顺序控制
public class AppplicationRunnerImpl implements ApplicationRunner {
//	private static final Logger logger = LoggerFactory.getLogger(IndustryCodeProcess.class);
	@Autowired
	private  ConceptCodeProcess  conceptCodeProcess;
	@Override
	public void run(ApplicationArguments args) throws Exception {
//		logger.info("开始了");
		conceptCodeProcess.start_Task();
	}
}
