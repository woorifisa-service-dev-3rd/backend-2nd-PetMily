package woori.petmily_card.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import woori.petmily_card.entity.Board;
import woori.petmily_card.entity.Member;
import woori.petmily_card.repository.BoardRepository;
import woori.petmily_card.repository.MemberRepository;
import woori.petmily_card.service.BoardService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/boards")
public class BoardController {
//    @Autowired
//    BoardRepository boardRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BoardService boardService;

    //보드 목록 조회
    @GetMapping
    public String list(Model model){
        List<Board> boards =  boardService.findAll();
        model.addAttribute("boards", boards); //html 타임리프 "boards" 에 boards를 넘겨줌
        return "board"; // board.html 렌더링
    }

    // 게시글 상세 정보
    @GetMapping("/{id}")
    public String detail(@PathVariable int id, Model model){
        Optional<Board> board = boardService.findById(id);
        //model.addAttribute("board",board); // 이렇게 하면 오류났음.board 대신 board.orElseThrow()하니까 해결됨.
        model.addAttribute("board",board.orElseThrow());
        return "board-detail";
    }

    // 게시글 생성, 수정 페이지
    @GetMapping("/edit")
    public String createBoard(Model model){
        Board board = Board.builder().build();
        model.addAttribute("board",board);
        return "board-edit";
    }

    // 게시글 생성
    @PostMapping("/edit")
    public String saveNewBoard(//@PathVariable int id,
                               @RequestParam String content,
                               @RequestParam String title){
        Optional<Member> member = memberRepository.findById(1);

        Board board = Board.builder().title(title).content(content)
                .member(member.orElseThrow())
                .createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).build();

//        Board board = new Board();
//        board.setTitle(title);
//        board.setContent(content);
//        board.setCreatedAt(LocalDateTime.now());
//        board.setUpdatedAt(LocalDateTime.now());
//        board.setMember(member.orElseThrow());
//        board.setTitle(title);
        boardService.save(board);
        return "redirect:/boards";
    }

    @PostMapping("/delete/{id}")
    public String deleteBoard(@PathVariable int id){
        boardService.deleteById(id);
        return "redirect:/boards";
    }




}
