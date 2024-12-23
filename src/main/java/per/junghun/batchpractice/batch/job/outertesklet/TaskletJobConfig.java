package per.junghun.batchpractice.batch.job.outertesklet;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class TaskletJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Job taskletJob() {
        return new JobBuilder("taskletJob2", jobRepository)
                .start(taskletStep())
                .build();
    }

    @Bean
    public Step taskletStep() {
        return new StepBuilder("taskletStep", jobRepository)
                .tasklet(new BusinessTasklet(), transactionManager)
                .build();
    }
}