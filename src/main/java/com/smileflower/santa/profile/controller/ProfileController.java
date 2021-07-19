package com.smileflower.santa.profile.controller;

import com.smileflower.santa.apple.utils.AppleJwtUtils;
import com.smileflower.santa.exception.ApiResult;
import com.smileflower.santa.profile.model.dto.*;
import com.smileflower.santa.profile.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/api/profile")
@RestController
public class ProfileController {

    @Autowired
    AppleJwtUtils appleJwtUtils;
    @Autowired
    ProfileService profileService;

    @PatchMapping("/upload")
    public ApiResult<UploadImageResponse> uploadImage(@RequestHeader(value = "REFRESH-TOKEN")
    String token,@RequestPart(required = false) MultipartFile file){
        String email = appleJwtUtils.getEmailByRefreshToken(token).getEmail();
        if(file != null){
            return ApiResult.OK(
                    profileService.uploadImage(file,email)
            );
        }
        else {
            return ApiResult.OK(
                    profileService.deleteImage(email)
            );
        }
    }
    @GetMapping("/upload")
    public ApiResult<UploadImageResponse> uploadImage(@RequestHeader(value = "REFRESH-TOKEN")
                                                              String token){
        String email = appleJwtUtils.getEmailByRefreshToken(token).getEmail();
        return ApiResult.OK(
                profileService.getUploadImage(email)
        );

    }



    @GetMapping("/{userIdx}")
    public ApiResult<ProfileResponse> profile(@PathVariable("userIdx") Long userIdx) {

        return ApiResult.OK(
                profileService.findProfile(userIdx)
        );

    }

    @GetMapping("/{userIdx}/posts")
    public ApiResult<PostsResponse> flags(@PathVariable("userIdx") Long userIdx) {
        return ApiResult.OK(
                profileService.findFlags(userIdx)
        );
    }

    @GetMapping("/{userIdx}/result")
    public ApiResult<ResultResponse> result(@PathVariable("userIdx") Long userIdx) {
        return ApiResult.OK(
                profileService.findResult(userIdx)
        );
    }

    @GetMapping("/{userIdx}/flags/map")
    public ApiResult<List<FlagsForMapResponse>> flagsForMap(@PathVariable("userIdx") Long userIdx) {

        return ApiResult.OK(
                profileService.findFlagsForMap(userIdx)
        );

    }

    @DeleteMapping("/{userIdx}/flags/{flagIdx}")
    public ApiResult<DeleteFlagResponse> deleteFlag(@PathVariable("flagIdx") Long flagIdx) {

        return ApiResult.OK(
                profileService.deleteFlag(flagIdx)
        );

    }

    @DeleteMapping("/{userIdx}/pictures/{pictureIdx}")
    public ApiResult<DeletePictureResponse> deletePicture(@PathVariable("pictureIdx") Long pictureIdx) {

        return ApiResult.OK(
                profileService.deletePicture(pictureIdx)
        );

    }

    @PostMapping("/picture")
    public ApiResult<PictureResponse> createPicture(@RequestBody CreatePictureRequest imageUrl) {

        return ApiResult.OK(
                profileService.createPicture(userIdx,imageUrl)
        );

    }

    @PostMapping("/{userIdx}/flags/{flagIdx}/report")
    public ApiResult<ReportFlagResponse> reportFlag(@PathVariable("userIdx") Long userIdx, @PathVariable("flagIdx") Long flagIdx) {

        return ApiResult.OK(
                profileService.reportFlag(userIdx,flagIdx)
        );

    }




}
