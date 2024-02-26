package rec.filmrec.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByPostId(long postId);

    List<Post> findByMovieBoardId(long movieBoardId);

    List<Post> findAll();

    Post save(Post post);

    void delete(Post post);

}
