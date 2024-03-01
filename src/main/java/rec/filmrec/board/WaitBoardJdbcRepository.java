package rec.filmrec.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import rec.filmrec.domain.Movie;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class WaitBoardJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WaitBoardJdbcRepository(DataSource datasource) {
        jdbcTemplate = new JdbcTemplate(datasource);
    }

    // @Builder를 이용해서 만든 RowMapper
    private RowMapper<WaitBoard> wbRowMapper() {
        return (rs, rowNum) -> {
            return WaitBoard.builder()
                    .wbId(rs.getLong("wb_id"))
                    .wbTitle(rs.getString("wb_title"))
                    .wbContent(rs.getString("wb_content"))
                    .build();
        };
    }

    public List<WaitBoard> findAll() {
        String sql = "SELECT wb_id, wb_title, wb_content FROM wait_board";
        return jdbcTemplate.query(sql, wbRowMapper());
    }

    public Optional<WaitBoard> findByWbId(long wbId) {
        try {
            String sql = "SELECT wb_id, wb_title, wb_content FROM wait_board WHERE wb_id = ?";
            WaitBoard waitBoard = jdbcTemplate.queryForObject(sql, wbRowMapper(), wbId);
            return Optional.ofNullable(waitBoard);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<WaitBoard> findByMovieId(long movieId) {
        String sql = "SELECT w.wb_id, w.wb_title, w.wb_content FROM wait_board, WHERE movie_id = ?";
        return jdbcTemplate.query(sql, wbRowMapper(), movieId);
    }

    // 내 생각에는 void로 가도 상관 없을 것 같은데? 뷰에다가 추가할것도 없을 것 같고, 또 리다이렉트로 가버릴 수 있으니.
    // 빌더로 만들어주기까지만 하고, 따로 저장해서 쓸 필요는 없을 것 같다. 써야된다면 void -> 다른객체로 바꿔주자.
    public WaitBoard saveWaitBoard(long movieId, WaitBoard waitBoard) {
        String sql = "INSERT INTO wait_board (movie_id, wb_title, wb_content) VALUES ("+ movieId + ", ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, waitBoard.getWbTitle());
            ps.setString(2, waitBoard.getWbContent());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();

        if(key == null) return waitBoard;
        return waitBoard.toBuilder()
                .wbId(key.longValue())
                .build();
    }

    public WaitBoard updateWaitBoard(WaitBoard waitBoard) {
        String updateSql = "UPDATE wait_board SET wb_title = ?, wb_content = ? WHERE wb_id = ?";
        jdbcTemplate.update(updateSql,
                waitBoard.getWbTitle(), waitBoard.getWbContent(), waitBoard.getWbId());

        return waitBoard;
    }
    public void deleteWaitBoard(long wbId) {
        String sql = "DELETE FROM wait_board WHERE wb_id = ?";
        jdbcTemplate.update(sql, wbId);
    }
}
