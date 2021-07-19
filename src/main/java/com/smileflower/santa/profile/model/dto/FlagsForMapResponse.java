package com.smileflower.santa.profile.model.dto;

public class FlagsForMapResponse {
    private Long userIdx;
    private Long mountainIdx;
    private double latitude;
    private double longitude;
    private int flagCount;

    public FlagsForMapResponse(Long userIdx, Long mountainIdx, double latitude, double longitude, int flagCount) {
        this.userIdx = userIdx;
        this.mountainIdx = mountainIdx;
        this.latitude = latitude;
        this.longitude = longitude;
        this.flagCount = flagCount;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getFlagCount() {
        return flagCount;
    }

    public void setFlagCount(int flagCount) {
        this.flagCount = flagCount;
    }
}
