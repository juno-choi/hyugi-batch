package com.juno.hyugibatch.repository;

import com.juno.hyugibatch.domain.entity.RestAreaInfoEntity;

import java.util.List;

public interface RestAreaInfoRepository {
    void save(List<RestAreaInfoEntity> list);
    void delete();
}
