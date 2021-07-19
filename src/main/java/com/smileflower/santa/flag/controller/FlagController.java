package com.smileflower.santa.flag.controller;

import com.smileflower.santa.apple.utils.AppleJwtUtils;
import com.smileflower.santa.exception.ApiResult;
import com.smileflower.santa.flag.service.FlagService;
import com.smileflower.santa.profile.model.dto.UploadImageResponse;
import com.smileflower.santa.profile.service.ProfileService;
import com.smileflower.santa.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.smileflower.santa.config.BaseResponseStatus.EMPTY_JWT;

@RequestMapping("/api/flag")
@RestController
public class FlagController {

    @Autowired
    AppleJwtUtils appleJwtUtils;
    @Autowired
    FlagService flagService;
    @Autowired
    JwtService jwtService;

    @PostMapping("/upload/{mountainIdx}")
    public ApiResult<UploadImageResponse> uploadImage(@RequestHeader(value = "REFRESH-TOKEN")
                                                              String token, @RequestPart(required = false) MultipartFile file, @PathVariable("mountainIdx") Long mountainIdx) {

        //int userIdx=jwtService.getUserIdx();

        return ApiResult.OK(
                flagService.uploadImage(file,userIdx, mountainIdx)
        );

    }
}