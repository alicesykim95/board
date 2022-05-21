package com.example.board.controller;

import com.example.board.service.LikeDislikeService;
import com.example.board.vo.LikeDislikeVo;
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

    @ResponseBody
    @RequestMapping(value = "/likeInfoUpdate", method = RequestMethod.POST)
    public int likeInfoUpdate(HttpServletRequest request, LikeDislikeVo ldv)throws Exception{

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        ldv.setUserId(userId);

        if (likeDislikeService.likeInfoUpdate(ldv) == 0){
        }

        return likeDislikeService.likeInfoUpdate(ldv);
    }
}
