package kr.co.thefesta.admin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
@EnableScheduling

public class AdminScheduler implements SchedulingConfigurer{
	
	private final int POOL_SIZE = 10;//최대 스레드 10까지사용

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		ThreadPoolTaskScheduler taskScheduler = threadPoolTaskScheduler();

		taskRegistrar.setTaskScheduler(taskScheduler);
		
	}
	
	@Bean 
	public ThreadPoolTaskScheduler threadPoolTaskScheduler() { 

		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler(); 
		taskScheduler.setThreadNamePrefix("schduler-task-pool-");
		taskScheduler.initialize();
		taskScheduler.setPoolSize(POOL_SIZE);

		return taskScheduler; 

	}
		
}
