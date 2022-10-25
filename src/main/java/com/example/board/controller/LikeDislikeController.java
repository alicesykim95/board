package com.example.board.controller;

import com.example.board.service.LikeDislikeService;
import com.example.board.vo.LikeDislikeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class LikeDislikeController {

    private final LikeDislikeService likeDislikeService;
    Map<String, Object> likeResult = new HashMap<>();
    Map<String, Object> dislikeResult = new HashMap<>();

    // 좋아요 처리
    @ResponseBody
    @RequestMapping(value = "/likeInfoUpdate", method = RequestMethod.POST)
    public Map<String, Object> likeInfoUpdate(HttpServletRequest request, LikeDislikeVo ldv, int boardNum) throws Exception{

        Map<String, Object> likeResult = new HashMap<>();

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        ldv.setUserId(userId);
        ldv.setBoardNum(boardNum);

        int likeCheck = 0;
        int likeDataReturn = likeDislikeService.likeCount(ldv);
        int dislikeDateReturn = likeDislikeService.dislikeCount(ldv);

        if (likeDataReturn == 2){
            likeDislikeService.likeDislikeSave(ldv);
            likeDislikeService.likeUpdate(ldv);
            likeCheck = 1;
        } else if (likeDataReturn == 0){
            likeDislikeService.likeUpdate(ldv);
            likeCheck = 1;
        } else if(likeDataReturn == 1) {
            likeDislikeService.likeDelete(ldv);
            likeCheck = 2;
        }

        if (dislikeDateReturn == 1){
            likeDislikeService.dislikeDelete(ldv);
            likeCheck = 3;
        }

        likeResult.put("likeCheck", likeCheck);
        likeResult.put("likeTotalCount", likeDislikeService.likeTotalCount(ldv));
        likeResult.put("dislikeTotalCount", likeDislikeService.dislikeTotalCount(ldv));

        return likeResult;
    }

    // 싫어요 처리
    @ResponseBody
    @RequestMapping(value = "/dislikeInfoUpdate", method = RequestMethod.POST)
    public Map<String, Object> dislikeInfoUpdate(HttpServletRequest request, LikeDislikeVo ldv, int boardNum) throws Exception{

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        ldv.setUserId(userId);
        ldv.setBoardNum(boardNum);

        int dislikeCheck = 0;
        int likeDataReturn = likeDislikeService.likeCount(ldv);
        int dislikeDateReturn = likeDislikeService.dislikeCount(ldv);

       if (dislikeDateReturn == 2){
            likeDislikeService.likeDislikeSave(ldv);
            likeDislikeService.dislikeUpdate(ldv);
           dislikeCheck = 1;
        } else if(dislikeDateReturn == 0){
           likeDislikeService.dislikeUpdate(ldv);
           dislikeCheck = 1;
        } else if (dislikeDateReturn == 1){
            likeDislikeService.dislikeDelete(ldv);
           dislikeCheck = 2;
        }

        if (likeDataReturn == 1){
            likeDislikeService.likeDelete(ldv);
            dislikeCheck = 3;
        }

        dislikeResult.put("dislikeCheck", dislikeCheck);
        dislikeResult.put("dislikeTotalCount", likeDislikeService.dislikeTotalCount(ldv));
        dislikeResult.put("likeTotalCount", likeDislikeService.likeTotalCount(ldv));

        return dislikeResult;
    }

}
