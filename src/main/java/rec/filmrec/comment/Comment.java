package rec.filmrec.comment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import rec.filmrec.domain.BaseEntity;
import rec.filmrec.post.Post;

@Entity
@Getter
@Setter
@Table(name = "comment")
public class Comment extends BaseEntity {

    @Id
    @Column(name="comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentId;

    @Column(name = "comment_title")
    private String commentTitle;

    @Column(name = "comment_content")
    private String commentContent;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;
}
