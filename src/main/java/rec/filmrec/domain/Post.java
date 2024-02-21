package rec.filmrec.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OneToMany(mappedBy = "post")
    private Long id;
    private String title;
    private String content;
    private int password;

    @ManyToOne
    @JoinColumn(name="board_id")
    private MovieBoard movieBoard;

}
