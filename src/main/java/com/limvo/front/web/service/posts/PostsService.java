package com.limvo.front.web.service.posts;

import com.limvo.front.web.domain.posts.Posts;
import com.limvo.front.web.domain.posts.PostsRepository;
import com.limvo.front.web.dto.PostsResponseDto;
import com.limvo.front.web.dto.PostsSaveRequestDto;
import com.limvo.front.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    @Transactional
    public Long save(PostsSaveRequestDto postsSaveRequestDto) {
        return postsRepository.save(postsSaveRequestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto postsUpdateRequestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id :: "+ id));
        posts.update(postsUpdateRequestDto.getTitle(), postsUpdateRequestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id :: "+id));
        return new PostsResponseDto(entity);
    }
}
