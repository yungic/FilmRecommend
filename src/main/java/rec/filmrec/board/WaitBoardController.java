package rec.filmrec.board;

import net.sf.jsqlparser.statement.select.Wait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rec.filmrec.domain.Movie;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/waitboards")
public class WaitBoardController {

    private final WaitBoardService waitBoardService;

    @Autowired
    WaitBoardController(WaitBoardService waitBoardService) {
        this.waitBoardService=waitBoardService;
    }

    @GetMapping("/{movieId}")
    public String getWbsByMovieID(@RequestParam("movieId") Long movieId, Model model) {
        List<WaitBoard> result = waitBoardService.findWbWithMovie(movieId);
        model.addAttribute("mWaitBoardList", result);
        return "view1";
    }

    @GetMapping("/{userId}")
    public String getWbsByUserID(@RequestParam("userId") Long userId, Model model) {
        List<WaitBoard> result = waitBoardService.findWbWithMovie(userId);
        model.addAttribute("uWaitBoardList", result);
        return "view2";
    }

    @GetMapping("")
    public String getAllWbs(Model model) {
        List<WaitBoard> result = waitBoardService.findAllWaitBoard();
        model.addAttribute("waitBoards", result);
        return "view3";
    }

    @GetMapping("/add")
    public String wbAddForm() {
        return "view4";
    }

    @PostMapping("/add")
    public String putWbs(@ModelAttribute WaitBoard waitBoard, RedirectAttributes redirectAttributes) {
        WaitBoard createdWaitBoard = waitBoardService.saveWb(waitBoard);
        redirectAttributes.addAttribute("waitBoardId", createdWaitBoard.getWbId());
        return "redirect:/waitboards";

    }

    @GetMapping("/{wbId}/edit")
    public String getEditWb(@PathVariable long wbId, RedirectAttributes redirectAttributes) {
        Optional<WaitBoard> findWaitBoard = waitBoardService.findWbById(wbId);
        redirectAttributes.addAttribute("wbfindById", findWaitBoard);
        return "redirect:/{wbId}/edit"; // 수정창으로 이동
    }

    @PostMapping("/{wbId}/edit")
    public String editWb(@ModelAttribute WaitBoard waitBoard) {
        waitBoardService.updateWaitBoard(waitBoard);
        return "redirect:/waitboards";
    }

    @DeleteMapping("/{wbId}")
    public String deleteWb(@PathVariable("wbID") long wbId) {
        waitBoardService.deleteWaitBoard(wbId);
        return "redirect:/waitboards";
    }


}
