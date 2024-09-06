package woori.petmily_card.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import woori.petmily_card.entity.Board;
import woori.petmily_card.entity.Member;
import woori.petmily_card.repository.BoardRepository;
import woori.petmily_card.repository.MemberRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/boards")
public class BoardController {
    @Autowired
    BoardRepository boardRepository;

    @Autowired
    MemberRepository memberRepository;

    //보드 목록 조회
    @GetMapping
    public String list(Model model){
        List<Board> boards =  boardRepository.findAll();
        model.addAttribute("boards", boards); //html 타임리프 "boards" 에 boards를 넘겨줌
        return "board"; // board.html 렌더링
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable int id, Model model){
        Optional<Board> board = boardRepository.findById(id);
        //model.addAttribute("board",board); // 이렇게 하면 오류났음.board 대신 board.orElseThrow()하니까 해결됨.
        model.addAttribute("board",board.orElseThrow());
        return "board-detail";
    }

    @GetMapping("/edit")
    public String createBoard(Model model){
        Board board = Board.builder().build();
        model.addAttribute("board",board);
        return "board-edit";
    }

    @PostMapping("/edit")
    public String saveNewBoard(@ModelAttribute Board board){
        Optional<Member> member = memberRepository.findById(1);

        Board newBoard = Board.builder().title(board.getTitle()).content(board.getContent())
                .member(member.orElseThrow())
                .createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).comments(null).build();

        boardRepository.save(newBoard);
        return "redirect:/boards";

    }




}
