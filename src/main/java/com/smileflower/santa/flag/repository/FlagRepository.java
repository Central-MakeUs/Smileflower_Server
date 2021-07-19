package com.smileflower.santa.flag.repository;

public interface FlagRepository {
    int updateImageUrlByIdx(Long userIdx, Long mountainIdx, String filename);
}
