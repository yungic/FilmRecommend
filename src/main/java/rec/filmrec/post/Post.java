package rec.filmrec.post;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rec.filmrec.board.MovieBoard;
import rec.filmrec.comment.Comment;
import rec.filmrec.domain.BaseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="post")
public class Post extends BaseEntity {

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name = "post_title")
    private String postTitle;

    @Column(name = "post_content")
    private String postContent;

    @ManyToOne
    @JoinColumn(name="p_board_id")
    private MovieBoard movieBoard;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    private LocalDateTime createdAt;

}
