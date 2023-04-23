package com.juno.hyugibatch.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class RestClient {
    private final Environment env;

    @Bean
    public WebClient webClient(){
        ExchangeFilterFunction errorFilter = ExchangeFilterFunction
                .ofResponseProcessor( clientResponse -> exchangeFilterResponseProcessor(clientResponse));

        return WebClient.builder().baseUrl(env.getProperty("rest-area.url"))
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(-1))   //unlimited size
                        .build())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .filter(errorFilter)
                .build();
    }

    private Mono<ClientResponse> exchangeFilterResponseProcessor(ClientResponse response) {
        HttpStatusCode status = response.statusCode();
        if (status.is5xxServerError()) {
            return response.bodyToMono(String.class)
                    .flatMap(body -> Mono.error(new RuntimeException("server error!")));
        }
        if (status.is4xxClientError()) {
            return response.bodyToMono(String.class)
                    .flatMap(body -> {
                        log.error("body = {}",body);
                        return Mono.error(new RuntimeException("4xx error!"));
                    });
        }
        return Mono.just(response);
    }
}
