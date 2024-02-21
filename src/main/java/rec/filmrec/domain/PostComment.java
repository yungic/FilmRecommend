package rec.filmrec.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PostComment extends Comment {
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public PostComment(Long id, int password, String content) {
        super(id, password, content);
    }
}
