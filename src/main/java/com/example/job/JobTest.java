package com.example.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class JobTest implements Job {

    public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.sss";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD_WITH_SLASH = "yyyy/MM/dd";
    public static final String YYYY_MM_DD_WITH_STRIKE = "yyyy-MM-dd";

    @Override
    public void execute(JobExecutionContext jobContext) throws JobExecutionException {
        // 目的：获取当前调度前一个小时的数据，oprDate >= startDate and oprDate < EndDate
        // startDate开始时间：上一次框架的调度时间
        // endDate 结束时间：获取当前框架的调度时间 取小时数

        // 获取上一次调度的时间
        Date prevTime = jobContext.getPreviousFireTime();
        if (Objects.isNull(prevTime)) {
            // 从数据库中获取
        }
        // 获取当前框架的调度时间
        Date jobNowDate = jobContext.getFireTime();
        // 设置下？上？次框架调度时间
//        jobContext.setResult();


        LocalDateTime now = LocalDateTime.now();
        System.out.println("格式化前:" + now);
        String format = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        System.out.println("格式化后:" + format);

        LocalDateTime parse = LocalDateTime.parse(format, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        System.out.println("反格式化:" + parse);
    }
}
