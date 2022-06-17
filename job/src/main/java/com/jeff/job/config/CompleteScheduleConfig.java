package com.jeff.job.config;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * @program: tools
 * @description: 动态配置使用方法：读取数据库中的cron表达式值，实现动态执行定时任务
 * @author: Jeff Hu 2022/06/17 14:29
 */
@Configuration
@EnableScheduling
public class CompleteScheduleConfig implements SchedulingConfigurer {

    @Autowired
    @SuppressWarnings("all")
    private CronMapper cronMapper;

    @Mapper
    public interface CronMapper {
        @Select("select cron from cron where cron_id = #{index}")
        String getCron(@Param("index") Integer index);
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        // 多个定时任务需要执行的情况下
        for (int i = 1; i < 3; i++) {
            int value = i;
            taskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> System.out.println(Thread.currentThread().getName() + " 执行定时任务2: " + LocalDateTime.now().toLocalTime()),
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库获取执行周期
                    String cron = cronMapper.getCron(value);
                    System.out.println("cron: " + cron);
                    //2.2 合法性校验.
                    if (StringUtils.isEmpty(cron)) {
                        // Omitted Code ..
                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
            );
        }
    }
}
