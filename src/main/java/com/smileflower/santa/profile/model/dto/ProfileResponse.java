package com.smileflower.santa.profile.model.dto;

import java.util.List;

public class ProfileResponse {
    private Long userIdx;
    private String name;
    private int level;
    private int flagCount;
    private int postCount;
    private List<FlagResponse> flags;

    public ProfileResponse(Long userIdx, String name, int level, int flagCount, int postCount, List<FlagResponse> flags) {
        this.userIdx = userIdx;
        this.name = name;
        this.level = level;
        this.flagCount = flagCount;
        this.postCount = postCount;
        this.flags = flags;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getFlagCount() {
        return flagCount;
    }

    public void setFlagCount(int flagCount) {
        this.flagCount = flagCount;
    }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    public List<FlagResponse> getFlags() {
        return flags;
    }

    public void setFlags(List<FlagResponse> flags) {
        this.flags = flags;
    }
}
