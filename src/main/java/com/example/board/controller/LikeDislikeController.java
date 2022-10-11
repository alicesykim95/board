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

        likeResult = new HashMap<>();
        dislikeResult = new HashMap<>();

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        ldv.setUserId(userId);
        ldv.setBoardNum(boardNum);

        int likeCheck = 0;

        if (likeDislikeService.dislikeCount(ldv) == 1){
            likeCheck = 1;
        }

        if (likeDislikeService.likeCount(ldv) == 2){
            likeDislikeService.likeDislikeSave(ldv);
            likeDislikeService.likeUpdate(ldv);
        } else if(likeDislikeService.likeCount(ldv) == 1){
            likeDislikeService.likeDelete(ldv);
            likeCheck = 2;
        } else if (likeDislikeService.likeCount(ldv) == 0){
            likeDislikeService.likeUpdate(ldv);
        }

        likeResult.put("likeCheck", likeCheck);
        likeResult.put("likeTotalCount", likeDislikeService.likeTotalCount(ldv));

        return likeResult;
    }

    // 싫어요 처리
    @ResponseBody
    @RequestMapping(value = "/dislikeInfoUpdate", method = RequestMethod.POST)
    public Map<String, Object> dislikeInfoUpdate(HttpServletRequest request, LikeDislikeVo ldv, int boardNum) throws Exception{

        likeResult = new HashMap<>();
        dislikeResult = new HashMap<>();

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        ldv.setUserId(userId);
        ldv.setBoardNum(boardNum);

        int dislikeCheck = 0;

        if (likeDislikeService.likeCount(ldv) == 1){
            dislikeCheck = 1;
        }

       if (likeDislikeService.dislikeCount(ldv) == 2){
            likeDislikeService.likeDislikeSave(ldv);
            likeDislikeService.dislikeUpdate(ldv);
        } else if(likeDislikeService.dislikeCount(ldv) == 1){
            likeDislikeService.dislikeDelete(ldv);
            dislikeCheck = 2;
        } else if (likeDislikeService.dislikeCount(ldv) == 0){
            likeDislikeService.dislikeUpdate(ldv);
        }

        dislikeResult.put("dislikeCheck", dislikeCheck);
        dislikeResult.put("dislikeTotalCount", likeDislikeService.dislikeTotalCount(ldv));

        return dislikeResult;
    }

}
