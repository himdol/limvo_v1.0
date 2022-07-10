package com.limvo.front.web;

import com.limvo.front.config.auth.LoginUser;
import com.limvo.front.config.auth.dto.SessionUser;
import com.limvo.front.web.service.posts.PostsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
public class IndexController {

    private final PostsService postsService;

    public IndexController(PostsService postsService) {
        this.postsService = postsService;
    }

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser sessionUser){
        model.addAttribute("posts", postsService.findAllDesc());
//        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");

        if(!Objects.isNull(sessionUser)){
            model.addAttribute("userName", sessionUser.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }
}
