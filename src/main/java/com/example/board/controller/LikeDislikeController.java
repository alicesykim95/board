package com.example.board.controller;

import com.example.board.service.LikeDislikeService;
import com.example.board.vo.LikeDislikeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class LikeDislikeController {

    private final LikeDislikeService likeDislikeService;

    @ResponseBody
    @RequestMapping(value = "/likeInfoUpdate", method = RequestMethod.POST)
    public Map<String, Object> likeInfoUpdate(HttpServletRequest request, LikeDislikeVo ldv, @RequestBody int boardNum) throws Exception{

        Map<String, Object> result = new HashMap<>();

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        ldv.setUserId(userId);
        ldv.setBoardNum(boardNum);

        int likeCheck = 0;

        if (likeDislikeService.likeCount(ldv) == 0){
            likeDislikeService.likeSave(ldv);
            likeDislikeService.likeUpdate(ldv);
        } else if(likeDislikeService.likeCount(ldv) == 1){
            likeDislikeService.likeDelete(ldv);
            likeCheck = 1;
        }

        result.put("likeCheck", likeCheck);
        result.put("likeTotalCount", likeDislikeService.likeTotalCount(ldv));

        return result;
    }
}
