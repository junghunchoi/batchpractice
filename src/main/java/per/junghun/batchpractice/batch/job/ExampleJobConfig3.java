//package per.junghun.batchpractice.batch.job;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.ExitStatus;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobScope;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import java.lang.reflect.Member;
//import java.sql.SQLException;
//
//@Slf4j
//@Configuration
//@RequiredArgsConstructor
//public class ExampleJobConfig3 {
//    private final JobRepository jobRepository;
//    private final PlatformTransactionManager transactionManager;
//
//    @Bean
//    public Job exampleJob3(){
//        return new JobBuilder("exampleJob3", jobRepository)
//                .start(startStep3())
//                .on("FAILED") //startStep3의 ExitStatus가 FAILED일 경우
//                .to(failOverStep()) //failOver Step을 실행 시킨다.
//                .on("*") //failOver Step의 결과와 상관없이
//                .to(writeStep()) //write Step을 실행 시킨다.
//                .on("*") //write Step의 결과와 상관없 이
//                .end() //Flow를 종료시킨다.
//
//                .from(startStep3()) //startStep3이 FAILED가 아니고
//                .on("COMPLETED") //COMPLETED일 경우
//                .to(processStep()) //process Step을 실행 시킨다
//                .on("*") //process Step의 결과와 상관없이
//                .to(writeStep()) // write Step을 실행 시킨다.
//                .on("*") //wrtie Step의 결과와 상관없이
//                .end() //Flow를 종료 시킨다.
//
//                .from(startStep3()) //startStep3의 결과가 FAILED, COMPLETED가 아닌
//                .on("*") //모든 경우
//                .to(writeStep()) //write Step을 실행시킨다.
//                .on("*") //write Step의 결과와 상관없이
//                .end() //Flow를 종료시킨다.
//                .end()
//                .build();
//    }
//
//    @Bean
//    public Step startStep3() {
//        return new StepBuilder("startStep3", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    log.info("Start Step!");
//
//                    String result = "COMPLETED";
//                    //String result = "FAIL";
//                    //String result = "UNKNOWN";
//
//                    //Flow에서 on은 RepeatStatus가 아닌 ExitStatus를 바라본다.
//                    if(result.equals("COMPLETED"))
//                        contribution.setExitStatus(ExitStatus.COMPLETED);
//                    else if(result.equals("FAIL"))
//                        contribution.setExitStatus(ExitStatus.FAILED);
//                    else if(result.equals("UNKNOWN"))
//                        contribution.setExitStatus(ExitStatus.UNKNOWN);
//
//                    return RepeatStatus.FINISHED;
//                }, transactionManager)
//                .build();
//    }
//
//    @Bean
//    public Step failOverStep() {
//        return new StepBuilder("failOverStep", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    log.info("FailOver Step!");
//                    return RepeatStatus.FINISHED;
//                }, transactionManager)
//                .build();
//    }
//
//    @Bean
//    public Step processStep() {
//        return new StepBuilder("processStep", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    log.info("Process Step!");
//                    return RepeatStatus.FINISHED;
//                }, transactionManager)
//                .build();
//    }
//
//    @Bean
//    public Step writeStep() {
//        return new StepBuilder("writeStep", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    log.info("Write Step!");
//                    return RepeatStatus.FINISHED;
//                }, transactionManager)
//                .build();
//    }
//
//    // Skip 설정
//    // 특정 예외 발생 시 해당 아이템을 건너뛰고 계속 진행
////    @Bean
////    @JobScope
////    public Step step() throws Exception {
////        return new StepBuilder("step", jobRepository)
////                .<Member, Member>chunk(10, transactionManager)
////                .reader(reader(null))
////                .processor(processor(null))
////                .writer(writer(null))
////                .faultTolerant()
////                .skipLimit(1)  // skip 허용 횟수
////                .skip(NullPointerException.class)  // NullPointerException은 스킵
////                .noSkip(SQLException.class)  // SQLException은 스킵하지 않음
////                //.skipPolicy(new CustomSkipPolicy())  // 커스텀 스킵 정책 설정
////                .build();
////    }
//
//    // Retry 설정
//    // 특정 예외 발생 시 재시도
////    @Bean
////    @JobScope
////    public Step step() throws Exception {
////        return new StepBuilder("step", jobRepository)
////                .<Member, Member>chunk(10, transactionManager)
////                .reader(reader(null))
////                .processor(processor(null))
////                .writer(writer(null))
////                .faultTolerant()
////                .retryLimit(1)  // retry 시도 횟수
////                .retry(SQLException.class)  // SQLException 발생시 retry
////                .noRetry(NullPointerException.class)  // NullPointerException은 retry하지 않음
////                //.retryPolicy(new CustomRetryPolicy())  // 커스텀 retry 정책 설정
////                .build();
////    }
//
//    // Rollback 설정
//    // 특정 예외 발생 시 트랜잭션을 롤백하지 않음
////    @Bean
////    @JobScope
////    public Step step() throws Exception {
////        return new StepBuilder("step", jobRepository)
////                .<Member, Member>chunk(10, transactionManager)
////                .reader(reader(null))
////                .processor(processor(null))
////                .writer(writer(null))
////                .faultTolerant()
////                .noRollback(NullPointerException.class)  // NullPointerException 발생시 롤백하지 않음
////                .build();
////    }
//}