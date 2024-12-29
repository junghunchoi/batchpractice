package per.junghun.batchpractice.batch.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Slf4j
public class Example4 {

    /*
    이 Job은 세 개의 Step으로 구성됩니다:

    1. playerLoad: 선수 정보를 로드 (기본 설정)
    2. gameLoad: 게임 정보를 로드 (allowStartIfComplete=true)
    3. playerSummarization: 통계 요약 (startLimit=2)

    ### 실행 시나리오

    **1차 실행**

    - playerLoad: 400명의 선수 정보 로드 성공
    - gameLoad: 11개 게임 데이터 파일 처리
    - playerSummarization: 5분 후 실패

    **2차 실행**

    - playerLoad: 이미 완료되어 실행 안됨
    - gameLoad: 2개의 추가 파일 처리
    - playerSummarization: 30분 후 다시 실패

    **3차 실행**

    - playerLoad: 이미 완료되어 실행 안됨
    - gameLoad: 2개의 추가 파일 처리
    - playerSummarization: 시작 제한(2)을 초과하여 Job 즉시 종료

    이처럼 각 Step의 특성에 맞게 재시작 관련 설정을 적절히 구성할 수 있습니다.
     */
//    @Bean
//    public Job footballJob(JobRepository jobRepository, Step playerLoad,
//                           Step gameLoad, Step playerSummarization) {
//        return new JobBuilder("footballJob", jobRepository)
//                .start(playerLoad)
//                .next(gameLoad)
//                .next(playerSummarization)
//                .build();
//    }
//
//    @Bean
//    public Step playerLoad(JobRepository jobRepository,
//                           PlatformTransactionManager transactionManager) {
//        return new StepBuilder("playerLoad", jobRepository)
//                .<String, String>chunk(10, transactionManager)
//                .reader(playerFileItemReader())
//                .writer(playerWriter())
//                .build();
//    }
//
//    @Bean
//    public Step gameLoad(JobRepository jobRepository,
//                         PlatformTransactionManager transactionManager) {
//        return new StepBuilder("gameLoad", jobRepository)
//                .allowStartIfComplete(true)
//                .<String, String>chunk(10, transactionManager)
//                .reader(gameFileItemReader())
//                .writer(gameWriter())
//                .build();
//    }
//
//    @Bean
//    public Step playerSummarization(JobRepository jobRepository,
//                                    PlatformTransactionManager transactionManager) {
//        return new StepBuilder("playerSummarization", jobRepository)
//                .startLimit(2)
//                .<String, String>chunk(10, transactionManager)
//                .reader(playerSummarizationSource())
//                .writer(summaryWriter())
//                .build();
//    }

}
