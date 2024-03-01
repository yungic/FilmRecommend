package rec.filmrec.comment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rec.filmrec.exception.ExceptionCode;
import rec.filmrec.exception.ServiceLogicException;
import rec.filmrec.post.Post;
import rec.filmrec.post.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Autowired
    CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public List<Comment> findCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    public List<Comment> findComments() {
        return commentRepository.findAll();
    }

    public Comment findComment(long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.COMMENT_NOT_FOUND));

    }

    public void createComment(Long postId, Comment comment) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ServiceLogicException(ExceptionCode.POST_NOT_FOUND));
        log.info(post.getPostTitle());
        comment.setPost(post);
        commentRepository.save(comment);
    }

    public Comment updateComment(Long commentId, Comment comment) {
        Comment foundComment = commentRepository.findById(comment.getCommentId())
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.COMMENT_NOT_FOUND));

        Optional.ofNullable(comment.getCommentContent())
                .ifPresent(content -> foundComment.setCommentContent(content));

        return commentRepository.save(foundComment);
    }

    public void deleteComment(long commentId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        commentRepository.delete(comment);
    }
}
