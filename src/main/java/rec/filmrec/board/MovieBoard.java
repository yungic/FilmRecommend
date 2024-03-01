package rec.filmrec.board;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rec.filmrec.domain.BaseEntity;
import rec.filmrec.post.Post;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "movie_board")
public class MovieBoard extends BaseEntity {

    @Id
    @Column(name = "mb_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mbId;

    @Column(name = "mb_title")
    private String mbTitle;

    @Column(name = "mb_content")
    private String mbContent;

    @OneToMany(mappedBy = "movieBoard")
    private List<WaitBoard> WaitBoards = new ArrayList<>();

    @OneToMany(mappedBy = "movieBoard")
    private List<Post> posts = new ArrayList<>();
}
