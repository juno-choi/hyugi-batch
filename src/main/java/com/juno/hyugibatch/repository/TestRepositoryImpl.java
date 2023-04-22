package com.juno.hyugibatch.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TestRepositoryImpl implements TestRepository{
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save() {
        jdbcTemplate.update("insert into test (str) values ('test')");
    }
}
