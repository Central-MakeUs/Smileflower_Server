package com.smileflower.santa.profile.model.dto;

public class FlagsForMapResponse {
    private Long userIdx;
    private Long mountainIdx;
    private String imageUrl;
    private double latitude;
    private double longitude;
    private int flagCount;
    private String address;

    public FlagsForMapResponse(Long userIdx, Long mountainIdx, String imageUrl, double latitude, double longitude, int flagCount,String address) {
        this.userIdx = userIdx;
        this.mountainIdx = mountainIdx;
        this.imageUrl = imageUrl;
        this.latitude = latitude;
        this.longitude = longitude;
        this.flagCount = flagCount;
        this.address = address;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
