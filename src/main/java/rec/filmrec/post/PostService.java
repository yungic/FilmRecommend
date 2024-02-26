package rec.filmrec.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    PostService(PostRepository postRepository) {
        this.postRepository=postRepository;
    }

    // PostId로 조회
    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    //movieId로 조회
    public List<Post> getPostByMbId(long movieBoardId) {
        return postRepository.findByMovieBoardId(movieBoardId);
    }

    // 전체 포스트 조회
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(Post post) {
        return postRepository.save(post);
    }

    public void deletePost(Post post) {
        postRepository.delete(post);
    }


}