package rec.filmrec.post;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import rec.filmrec.board.MovieBoard;
import rec.filmrec.comment.PostComment;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="post")
public class Post {

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;

    @Column(name = "post_pass")
    private int postPassword;

    @Column(name = "post_title")
    private String postTitle;

    @Column(name = "post_content")
    private String postContent;

    @ManyToOne
    @JoinColumn(name="p_board_id")
    private MovieBoard pMovieBoard;

    @OneToMany(mappedBy = "post")
    private List<PostComment> comments = new ArrayList<PostComment>();
}
