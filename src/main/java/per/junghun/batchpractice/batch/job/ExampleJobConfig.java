//package per.junghun.batchpractice.batch.job.sam1;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//@Slf4j
//@RequiredArgsConstructor
//public class ExampleJobConfig {
//
//    private final JobRepository jobRepository;
//    private final PlatformTransactionManager transactionManager;
//
//    @Bean
//    public Job exampleJob() {
//        return new JobBuilder("exampleJob", jobRepository)
//                .start(startStep())
//                .next(nextStep())
//                .next(lastStep())
//                .build();
//    }
//
//    @Bean
//    public Step startStep() {
//        return new StepBuilder("startStep", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    log.info("Start Step!");
//                    return RepeatStatus.FINISHED;
//                }, transactionManager)
//                .build();
//    }
//
//    @Bean
//    public Step nextStep() {
//        return new StepBuilder("nextStep", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    log.info("Next Step!");
//                    return RepeatStatus.FINISHED;
//                }, transactionManager)
//                .build();
//    }
//
//    @Bean
//    public Step lastStep() {
//        return new StepBuilder("lastStep", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    log.info("Last Step!!");
//                    return RepeatStatus.FINISHED;
//                }, transactionManager)
//                .build();
//    }
//}