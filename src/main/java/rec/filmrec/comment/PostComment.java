package rec.filmrec.comment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import rec.filmrec.board.MovieBoard;
import rec.filmrec.post.Post;

@Entity
@Getter
@Setter
@Table(name = "post_comment")
public class PostComment {

    @Id
    @Column(name="pc_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postCommentId;

    @Column(name = "pc_pass")
    private int postCommentPassword;

    @Column(name = "pc_title")
    private String postCommentTitle;

    @Column(name = "pc_content")
    private String postCommentContent;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;
}
