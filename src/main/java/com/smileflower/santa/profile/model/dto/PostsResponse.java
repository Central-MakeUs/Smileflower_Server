package com.smileflower.santa.profile.model.dto;

import java.util.List;

public class PostsResponse {
    private Long userIdx;
    private String name;
    private List<FlagResponse> flags;
    private List<PictureResponse> pictures;

    public PostsResponse(Long userIdx, String name, List<FlagResponse> flags, List<PictureResponse> pictures) {
        this.userIdx = userIdx;
        this.name = name;
        this.flags = flags;
        this.pictures = pictures;
    }

    public Long getUserIdx() {
        return userIdx;
    }

    public void setUserIdx(Long userIdx) {
        this.userIdx = userIdx;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FlagResponse> getFlags() {
        return flags;
    }

    public void setFlags(List<FlagResponse> flags) {
        this.flags = flags;
    }

    public List<PictureResponse> getPictures() {
        return pictures;
    }

    public void setPictures(List<PictureResponse> pictures) {
        this.pictures = pictures;
    }
}
