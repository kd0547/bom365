package com.bom365.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.ScheduledMethodRunnable;

import com.bom365.scheduler.RegularSupportScheduler;

@SpringBootTest
public class SchedulerTest {
	@Test
	void schedulerTest() {
		
	}
	
	
	@TestConfiguration
	static class SchedulingTestConfig implements SchedulingConfigurer {

		@Override
		public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
			
			List<CronTask> cronTaskList = taskRegistrar.getCronTaskList();
			List<CronTask> newCronTaskList = new ArrayList<>();
			
			for(CronTask cronTask : cronTaskList) {
				ScheduledMethodRunnable runnable = (ScheduledMethodRunnable) cronTask.getRunnable();
				Method method = runnable.getMethod();
				
				String methodName = method.getName();
				Object target = runnable.getTarget();
				
				if(target instanceof RegularSupportScheduler) {
					if(methodName.equals("subscription")) {
						newCronTaskList.add(new CronTask(runnable, cronTask.getExpression()));
					}
				}
				
			}
			
			taskRegistrar.setCronTasksList(newCronTaskList);
			
		}
	}
}
