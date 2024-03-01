package rec.filmrec.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rec.filmrec.board.MovieBoardService;
import rec.filmrec.comment.Comment;
import rec.filmrec.comment.CommentService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final MovieBoardService movieBoardService;
    private final CommentService commentService;

    @Autowired
    PostController(PostService postService, MovieBoardService movieBoardService, CommentService commentService) {
        this.postService = postService;
        this.movieBoardService = movieBoardService;
        this.commentService = commentService;
    }

    @GetMapping("/create")
    public String moveToCreateForm(@RequestParam(value = "mbId") long mbId, Model model) {
        model.addAttribute("createPostsMbId", mbId);
        return "post/createpost";
    }

    @PostMapping("/create")
    public String createPost(@RequestParam(value = "mbId") long mbId, @ModelAttribute Post post) {
        post.setMovieBoard(movieBoardService.getByMbId(mbId));
        postService.createPost(post);

        return "redirect:/mbs/" + mbId;
    }

    @GetMapping("/{postId}")
    public String getPostById(@PathVariable(value = "postId") long postId, Model model) {
        Post fPost = postService.getPostById(postId);
        model.addAttribute("fPost", fPost);
        List<Comment> comments = commentService.findCommentsByPostId(postId);
        model.addAttribute("comments", comments);
        return "post/post";
    }

    @GetMapping("/{postId}/edit")
    public String editPost(@PathVariable(value = "postId") Long postId, Model model) {
        Post post = postService.getPostById(postId);
        model.addAttribute("post", post);
        return "post/editpost";
    }

    @PostMapping("/{postId}/edit")
    public String editPost(@PathVariable(value = "postId") Long postId, @ModelAttribute Post post, RedirectAttributes redirectAttributes) {
        Post updatedPost = postService.updatePost(post, postId);

        redirectAttributes.addAttribute("postId", updatedPost.getId());
        redirectAttributes.addFlashAttribute("message", "게시글이 수정되었습니다.");
        return "redirect:/posts/{postId}";
    }

    @DeleteMapping("/{postId}")
    public String deletePost(@PathVariable(value = "postId") Long postId, RedirectAttributes redirectAttributes) {
        postService.deletePost(postId);
        redirectAttributes.addFlashAttribute("message", "게시글이 삭제되었습니다.");
        return "redirect:/mbs";
    }


}
