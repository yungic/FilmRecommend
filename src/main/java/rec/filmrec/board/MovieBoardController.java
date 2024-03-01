package rec.filmrec.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rec.filmrec.post.Post;
import rec.filmrec.post.PostService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/mbs")
public class MovieBoardController {

    private final MovieBoardService movieBoardService;
    private final PostService postService;

    @Autowired
    MovieBoardController(MovieBoardService movieBoardService, PostService postService) {
        this.movieBoardService = movieBoardService;
        this.postService = postService;
    }

    @GetMapping("")
    public String getAllMbs(Model model) {
        List<MovieBoard> mbs = movieBoardService.getAllMb();
        model.addAttribute("allMbs", mbs);
        return "board/movieboards";
    }

    @GetMapping("/{mbId}")
    public String getByMbId(@PathVariable(value = "mbId") String mbId,
                            @RequestParam(name = "page", defaultValue = "0") int page,
                            @RequestParam(name = "size", defaultValue = "10") int size,
                            @RequestParam(name = "keyword", required = false) String keyword,
                            Model model) {

        long mbIdAsLong = Long.parseLong(mbId);

        MovieBoard movieBoard = movieBoardService.getByMbId(mbIdAsLong);
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Post> postPage = postService.findPostsByMovieBoardAndKeyword(movieBoard, keyword, pageRequest);

        model.addAttribute("fMb", movieBoard);
        model.addAttribute("keyword", keyword);
        model.addAttribute("postPage", postPage);

        return "board/movieboard";
    }

    @GetMapping("/{mbId}/edit")
    public String moveToEditForm(@PathVariable(value = "mbId") long mbId, Model model) {
        MovieBoard movieBoard = movieBoardService.getByMbId(mbId);
        model.addAttribute("foundMb",movieBoard);

        return "/board/editmovieboard";
    }

    @PostMapping("/{mbId}/edit")
    public String editMb(@PathVariable("mbId") long mbId, @ModelAttribute MovieBoard mb) {
        mb.setMbId(mbId);
        movieBoardService.editMbById(mb);
        return "redirect:/mbs";
    }

}
