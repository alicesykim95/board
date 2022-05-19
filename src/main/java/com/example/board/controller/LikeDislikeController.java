package com.example.board.controller;

import com.example.board.dto.LikeDislikeDto;
import com.example.board.service.LikeDislikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class LikeDislikeController {

    private final LikeDislikeService likeDislikeService;

//    // 게시물 좋아요 싫어요
//    @ResponseBody
//    @RequestMapping(value="/likeDislike", method = RequestMethod.POST)
//    public int likeDislike(LikeDislikeDto ldd, HttpServletRequest request) throws Exception{
//
//        HttpSession session = request.getSession();
//        String userId = (String) session.getAttribute("userId");
//        ldd.setUserId(userId);
//        likeDislikeService.likeDislike(ldd);
//
//        return 1;
//    }

}
