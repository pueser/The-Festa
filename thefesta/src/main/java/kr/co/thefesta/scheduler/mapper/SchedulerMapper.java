package kr.co.thefesta.scheduler.mapper;

import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface SchedulerMapper {
	public int getFestaCnt(int date) throws Exception;
}
