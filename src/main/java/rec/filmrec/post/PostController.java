package rec.filmrec.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Post")
public class PostController {

    private final PostService postService;

    @Autowired
    PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{postID}")
    public String getPostById(@RequestParam("postId") long postId, Model model) {
        Post getPostById = postService.getPostById(postId);
        model.addAttribute("Post", getPostById);

        return "viewPost1";
    }

    @GetMapping("/{movieId}")
    public String getPostsByMovieId(@PathVariable("movieId") long movieId, Model model) {
        List<Post> postsFindByMovieId = postService.getPostByMbId(movieId);
        model.addAttribute("postsFindByMovieId", postsFindByMovieId);

        return "viewPost2";
    }

    @GetMapping("/allPost")
    public String getAllPost(Model model) {
        List<Post> getAllPost = postService.getAllPosts();
        model.addAttribute("allPost", getAllPost);

        return "viewPost3";
    }

    @PostMapping("/add/{movieId}")
    public String savePost(@PathVariable("movieId") long movieBoardId, @ModelAttribute Post post) {
        post.getPMovieBoard().setMbId(movieBoardId);
        postService.updatePost(post);

        return "redirect:/Post/allPost";
    }

    @PostMapping("/update/{postId}")
    public String updatePost(@PathVariable("postId") long postId, @ModelAttribute Post post, Model model) {
        // postId에 해당하는 기존 Post를 가져와 업데이트
        Post updatedPost = postService.getPostById(postId);

        if (updatedPost != null) {
            // 업데이트할 내용으로 기존 Post를 업데이트
            updatedPost.setPostTitle(updatedPost.getPostTitle());
            updatedPost.setPostContent(updatedPost.getPostContent());

            // 업데이트된 Post를 저장
            postService.savePost(post);
            model.addAttribute("Post", updatedPost);
        } else {
           //예외처리
        }

        return "viewPost4"; // 업데이트된 Post를 보여주는 뷰 페이지로 이동
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@RequestParam long postId) {
        Post foundPost = postService.getPostById(postId);
        postService.deletePost(foundPost);
    }
}
