package woori.petmily_card.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import woori.petmily_card.dto.BoardResponse;
import woori.petmily_card.entity.Board;
import woori.petmily_card.entity.Member;
import woori.petmily_card.repository.MemberRepository;
import woori.petmily_card.service.BoardService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Controller
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BoardService boardService;

    // 게시글 목록 조회
    @GetMapping
    public String list(Model model){
        List<Board> boards = boardService.findAll();
        model.addAttribute("boards", boards);
        return "redirect:/boards/0"; // board.html 렌더링
    }

    // 게시글 상세 정보
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable int id, Model model){
        Board board = boardService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid board ID: " + id));
        model.addAttribute("board", board);
        return "board-detail";
    }

    // 게시글 생성, 수정 페이지
    @GetMapping("/edit")
    public String createBoard(Model model){
        model.addAttribute("board", new Board()); // 새로운 Board 객체를 생성
        return "board-edit";
    }

    // 게시글 생성
    @PostMapping("/edit")
    public String saveNewBoard(@RequestParam String title,
                               @RequestParam String content) {
        Optional<Member> member = memberRepository.findById(1); // 예시로 멤버 ID가 1인 경우
        Board board = Board.builder()
                .title(title)
                .content(content)
                .member(member.orElseThrow(() -> new IllegalArgumentException("Member not found")))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        boardService.save(board);
        return "redirect:/boards";
    }

    // 게시글 수정 페이지
    @GetMapping("/edit/{id}")
    public String updateBoardPage(@PathVariable int id, Model model){
        Board board = boardService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid board ID: " + id));
        model.addAttribute("board", board);
        return "board-edit";
    }

    // 게시글 수정
    @PostMapping("/edit/{id}")
    public String updateBoard(@PathVariable int id,
                              @RequestParam String title,
                              @RequestParam String content) {
        Board boardToUpdate = boardService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid board ID: " + id));
        boardToUpdate.setTitle(title);
        boardToUpdate.setContent(content);
        boardToUpdate.setUpdatedAt(LocalDateTime.now());
        boardService.save(boardToUpdate);
        return "redirect:/boards";
    }

    // 게시글 삭제
    @PostMapping("/delete/{id}")
    public String deleteBoard(@PathVariable int id){
        boardService.deleteById(id);
        return "redirect:/boards";
    }
    @GetMapping("/{page}")
    public String showBoards(@PathVariable(value = "page")int page, Model model){
        Page<BoardResponse> boards = boardService.showAll(page);
        model.addAttribute("boards",boards.getContent());
        model.addAttribute("currentPage",page);
        model.addAttribute("totalPages",boards.getTotalPages());
        return "board";
    }
}