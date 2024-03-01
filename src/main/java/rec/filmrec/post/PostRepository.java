package rec.filmrec.post;

import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rec.filmrec.board.MovieBoard;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByMovieBoardAndPostTitleContaining(MovieBoard movieBoard, String keyword, Pageable pageable);

    Page<Post> findAllByMovieBoardOrderByCreatedAtDesc(MovieBoard movieBoard, Pageable pageable);
}
