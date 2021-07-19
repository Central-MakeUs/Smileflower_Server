package com.smileflower.santa.profile.model.dto;

import java.time.LocalDateTime;

public class FlagResponse {
    private Long flagIdx;
    private Long userIdx;
    private Long mountainIdx;
    private LocalDateTime createdAt;
    private String pictureUrl;

    public FlagResponse(Long flagIdx, Long userIdx, Long mountainIdx, LocalDateTime createdAt, String pictureUrl) {
        this.flagIdx = flagIdx;
        this.userIdx = userIdx;
        this.mountainIdx = mountainIdx;
        this.createdAt = createdAt;
        this.pictureUrl = pictureUrl;
    }

    public Long getFlagIdx() {
        return flagIdx;
    }

    public void setFlagIdx(Long flagIdx) {
        this.flagIdx = flagIdx;
    }

    public Long getUserIdx() {
        return userIdx;
    }

    public void setUserIdx(Long userIdx) {
        this.userIdx = userIdx;
    }

    public Long getMountainIdx() {
        return mountainIdx;
    }

    public void setMountainIdx(Long mountainIdx) {
        this.mountainIdx = mountainIdx;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
