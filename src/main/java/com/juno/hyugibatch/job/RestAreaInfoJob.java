package com.juno.hyugibatch.job;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.juno.hyugibatch.config.RestClient;
import com.juno.hyugibatch.domain.entity.RestAreaInfoEntity;
import com.juno.hyugibatch.repository.RestAreaInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.HashMap;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class RestAreaInfoJob {
    private final RestClient restClient;
    private final Environment env;
    private final ObjectMapper objectMapper;
    private final RestAreaInfoRepository restAreaInfoRepository;

    @Bean
    public Job restAreaInfoJobStart(JobRepository jobRepository, Step restAreaInfoStep){
        return new JobBuilder("restAreaInfoJobStart", jobRepository)
                .start(restAreaInfoStep)
                .build();
    }

    @Bean
    public Step restAreaInfoStep(JobRepository jobRepository, Tasklet restAreaTasklet, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("restAreaInfoStep", jobRepository)
                .tasklet(restAreaTasklet, platformTransactionManager).build();
    }

    @Bean
    public Tasklet restAreaTasklet(JobRepository jobRepository){
        return (contribution, chunkContext) -> {
            String key = env.getProperty("rest-area.key");
            // api 호출
            ResponseEntity<String> response = restClient.webClient().get().uri(uriBuilder ->
                    uriBuilder.path("/restinfo/hiwaySvarInfoList")
                            .queryParam("key", key)
                            .queryParam("type", "json")
                            .build()
            ).retrieve()
                    .toEntity(String.class)
                    .block();

            HashMap hashMap = objectMapper.readValue(response.getBody(), HashMap.class);
            List<RestAreaInfoEntity> list = (List<RestAreaInfoEntity>) hashMap.get("list");

            // 내용 먼저 삭제
            restAreaInfoRepository.delete();
            // 호출한 내용 저장
            restAreaInfoRepository.save(list);

            return RepeatStatus.FINISHED;
        };
    }
}
