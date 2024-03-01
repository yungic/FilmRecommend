package rec.filmrec.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rec.filmrec.domain.Movie;
import rec.filmrec.domain.MovieService;

import java.util.List;

@Controller
@RequestMapping("/waitboards")
public class WaitBoardController {

    private final WaitBoardService waitBoardService;
    private final MovieService movieService;

    @Autowired
    WaitBoardController(WaitBoardService waitBoardService, MovieService movieService) {
        this.waitBoardService = waitBoardService;
        this.movieService = movieService;
    }

    @GetMapping("")
    public String getAllWbs(Model model) {
        List<WaitBoard> result = waitBoardService.findAllWb();
        model.addAttribute("waitBoards", result);
        return "board/waitboards";
    }

    // waitboards/ 로 들어오면
    // waitBoards 라는 변수 안에는 리스트가 들어있고, 얘내들을 꺼내가지고 waitboards를 만들어야 하는 것.

    @GetMapping("/{wbId}")
    public String getWbsByWbId(@PathVariable("wbId") long wbId, Model model) {
        WaitBoard result = waitBoardService.findWbById(wbId);
            if(result == null) {
                return "static/error";
            }
        model.addAttribute("findByWbId", result);
        return "board/waitboard";
    }

    // waitboards/wbId 로 들어오면
    // findByWbId 라는 변수 안에는 WaitBoard 라는 객체가 하나 들어있고, 얘로 waitboard를 만들어야 하는 것.

    @GetMapping("/movies/{movieId}")
    public String getWbsByMovieId(@PathVariable("movieId") long movieId, Model model) {
        List<WaitBoard> result = waitBoardService.findWbByMovie(movieId);
        model.addAttribute("mWaitBoardList", result);
        return "board/waitboardbymovieid";
    }

    // waitboards/movieId 로 들어오면
    // mWaitBoardList 라는 변수 안에는 WaitBoard 리스트가 들어있고, 얘내들을 꺼내가지고 waitboardbymovieid 만들어야 하는 것.

    @GetMapping("/add")
    public String wbAddForm(Model model) {
        List<Movie> list = movieService.getAllMovie();
        model.addAttribute("list", list);
        return "board/createwaitboard";
    }

    // waitboards/add/movieId

    @PostMapping("/add")
    public String createWbs(@RequestParam("movieId") long movieId, @ModelAttribute WaitBoard waitBoard) {
        waitBoardService.saveWb(movieId, waitBoard);
        waitBoard.setMovie(movieService.getMovieById(movieId));
        return "redirect:/waitboards";
    }

    @GetMapping("/{wbId}/edit")
    public String getEditWb(@PathVariable("wbId") long wbId, Model model) {
        WaitBoard findWaitBoard = waitBoardService.findWbById(wbId);
        model.addAttribute("findWb", findWaitBoard);
        return "board/editwaitboard";
    }

    @PostMapping("/{wbId}/edit")
    public String editWb(@PathVariable(value = "wbId") long wbId, @ModelAttribute WaitBoard waitBoard) {
        WaitBoard wb = waitBoard.toBuilder().wbId(wbId).build();
        waitBoardService.updateWaitBoard(wb);
        return "redirect:/waitboards";
    }

    @DeleteMapping("/{wbId}/delete")
    public String deleteWb(@PathVariable("wbId") long wbId) {
        waitBoardService.deleteWaitBoard(wbId);
        return "redirect:/waitboards";
    }


}
