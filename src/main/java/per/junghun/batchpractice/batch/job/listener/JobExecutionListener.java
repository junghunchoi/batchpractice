package per.junghun.batchpractice.batch.job.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JobExecutionListener {
    @BeforeJob
    public void beforeJob(JobExecution jobExecution) {
        log.info("Job 시작 전 실행: {}", jobExecution.getJobInstance().getJobName());
        // 작업 시작 전 필요한 로직 구현
    }

    @AfterJob
    public void afterJob(JobExecution jobExecution) {
        log.info("Job 종료 후 실행: {}", jobExecution.getJobInstance().getJobName());
        // 작업 종료 후 필요한 로직 구현

        // 작업 상태 확인
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            // 성공 시 처리
        } else if (jobExecution.getStatus() == BatchStatus.FAILED) {
            // 실패 시 처리
        }
    }
}
