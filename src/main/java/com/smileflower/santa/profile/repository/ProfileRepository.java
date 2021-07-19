package com.smileflower.santa.profile.repository;

import com.smileflower.santa.profile.model.domain.Email;
import com.smileflower.santa.profile.model.domain.Flag;
import com.smileflower.santa.profile.model.domain.Picture;
import com.smileflower.santa.profile.model.domain.Profile;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository {
    Optional<Profile> findByEmail(String email);
    Optional<String> findNameByIdx(Long userIdx);
    Optional<List<Flag>> findFlagsByIdx(Long userIdx);
    Optional<List<Picture>> findPicturesByIdx(Long userIdx);
    int findFlagCountByIdx(Long userIdx);
    int createPicture(Long userIdx,String imageUrl);
    boolean deleteFlagByIdx(Long flagIdx);
    int report(Long flagIdx, Long userIdx);
    int reportCountByIdx(Long flagIdx);

    int updateImageUrlByEmail(String email,String filename);
    int deleteImageUrlByEmail(String email);
}