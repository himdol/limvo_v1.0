package com.limvo.front.web;

import com.limvo.front.config.auth.dto.SessionUser;
import com.limvo.front.web.dto.PostsResponseDto;
import com.limvo.front.web.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;


@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    public IndexController(PostsService postsService, HttpSession httpSession) {
        this.postsService = postsService;
        this.httpSession = httpSession;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts", postsService.findAllDesc());

        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null){
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto postsResponseDto = postsService.findById(id);
        model.addAttribute("post",postsResponseDto);
        return "posts-update";
    }
    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }
}
