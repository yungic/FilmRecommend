package rec.filmrec.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import rec.filmrec.domain.Movie;
import rec.filmrec.domain.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class WaitBoardJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WaitBoardJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper
    private static class waitBoardRowMapper implements RowMapper<WaitBoard> {

        @Override
        public WaitBoard mapRow(ResultSet rs, int rowNum) throws SQLException {
            WaitBoard waitBoard = new WaitBoard();
            waitBoard.setWbTitle(rs.getString("wb_id"));
            waitBoard.setWbContent(rs.getString("wb_content"));

            Movie movie = new Movie();
            movie.setMovieId(rs.getLong("movie_id"));
            movie.setMovieTitle(rs.getString("movie_title"));
            movie.setMovieInfo(rs.getString("movie_info"));

            User user = new User();
            user.setUserId(rs.getLong("user_id"));
            user.setUserNickname(rs.getString("user_nick"));

            waitBoard.setMovie(movie);
            waitBoard.setUser(user);

            return waitBoard;
        }
    }

    public List<WaitBoard> findWaitBoardWithMovie(long movieId) {
        String sql = "SELECT w.wb_id, w.wb_title, w.wb_content, " +
                "m.id AS movie_id, m.title AS movie_title, m.info AS movie_info, " +
                "u.id AS user_id, u.ni AS user_nick " +
                "FROM wait_board w " +
                "JOIN movie m ON w.m_id = m.m_id " +
                "JOIN user u ON w.u_id = u.u_id " +
                "WHERE w.m_id = ?";

        return jdbcTemplate.query(sql, new waitBoardRowMapper());
    }

    public List<WaitBoard> findWaitBoardWithUser(long userId) {
        String sql = "SELECT w.wb_id, w.wb_title, w.wb_content, " +
                "m.id AS movie_id, m.title AS movie_title, m.info AS movie_info, " +
                "u.id AS user_id, u.ni AS user_nick " +
                "FROM wait_board w " +
                "JOIN movie m ON w.m_id = m.m_id " +
                "JOIN user u ON w.u_id = u.u_id " +
                "WHERE u.u_id = ?";

        return jdbcTemplate.query(sql, new waitBoardRowMapper());
    }

    public List<WaitBoard> findAllWaitBoard() {
        String sql = "SELECT w.wb_id, w.wb_title, w.wb_content, " +
                "m.id AS movie_id, m.title AS movie_title, m.info AS movie_info, " +
                "u.id AS user_id, u.ni AS user_nick " +
                "FROM wait_board w " +
                "JOIN movie m ON w.m_id = m.m_id " +
                "JOIN user u ON w.u_id = u.u_id";

        return jdbcTemplate.query(sql, new waitBoardRowMapper());
    }

    public WaitBoard saveWaitBoard(WaitBoard waitBoard) {
        String sql = "INSERT INTO wait_board (wb_title, wb_content, m_id, u_id) VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, waitBoard.getWbTitle());
            ps.setString(2, waitBoard.getWbContent());
            ps.setLong(3, waitBoard.getMovie().getMovieId());
            ps.setLong(4, waitBoard.getUser().getUserId());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            long generatedId = keyHolder.getKey().longValue();
            waitBoard.setWbId(generatedId);
            return waitBoard;
        } else {
            throw new DataAccessException("Failed to get generated key.") {}; // 예외 처리 필요
        }
    }

    public void updateWaitBoard(WaitBoard waitBoard) {
        String sql = "UPDATE wait_board SET wb_title = ?, wb_content = ?, m_id = ?, u_id = ? WHERE wb_id = ?";
        jdbcTemplate.update(sql,
                waitBoard.getWbTitle(),
                waitBoard.getWbContent(),
                waitBoard.getMovie().getMovieId(),
                waitBoard.getUser().getUserId(),
                waitBoard.getWbId());
    }

    public void deleteWaitBoard(long wbId) {
        String sql = "DELETE FROM wait_board WHERE wb_id = ?";
        jdbcTemplate.update(sql, wbId);
    }

}
