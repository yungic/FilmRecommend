package rec.filmrec.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import rec.filmrec.board.MovieBoard;
import rec.filmrec.exception.ExceptionCode;
import rec.filmrec.exception.ServiceLogicException;

import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void createPost(Post post) {
        postRepository.save(post);
    }

    public Post getPostById(long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.POST_NOT_FOUND));
    }

    public Page<Post> findPostsByMovieBoardAndKeyword(MovieBoard movieBoard, String keyword, PageRequest pageRequest) {
        if (keyword != null && !keyword.isEmpty()) {
            return postRepository.findAllByMovieBoardAndPostTitleContaining(movieBoard, keyword, pageRequest);
        } else {
            return postRepository.findAllByMovieBoardOrderByCreatedAtDesc(movieBoard, pageRequest);
        }
    }

    public Post updatePost(Post post, Long postId) {
        post.setId(postId);
        Post foundPost = postRepository.findById(post.getId()).orElse(null);
//                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.POST_NOT_FOUND));

        Optional.ofNullable(post.getPostTitle())
                .ifPresent(title -> foundPost.setPostTitle(title));
        Optional.ofNullable(post.getPostContent())
                .ifPresent(content -> foundPost.setPostContent(content));

        return postRepository.save(foundPost);
    }

    public void deletePost(Long id) {
        Post foundPost = postRepository.findById(id).orElse(null);

        postRepository.delete(foundPost);
    }

}