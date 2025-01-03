package per.junghun.batchpractice.batch.job.outertesklet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

@Slf4j
public class BusinessTasklet implements Tasklet, StepExecutionListener {

    @Override
    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        log.info("Before Step Start!");
    }

    @Override
    @AfterStep
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("After Step Start!");
        return ExitStatus.COMPLETED;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        //비즈니스 로직
        for (int idx = 0; idx < 10; idx++) {
            log.info("[idx] = " + idx);
        }
        return RepeatStatus.FINISHED;
    }
}