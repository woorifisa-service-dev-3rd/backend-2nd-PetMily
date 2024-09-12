package woori.petmily_card.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import woori.petmily_card.dto.BoardDetailResponse;
import woori.petmily_card.dto.BoardPageResponse;
import woori.petmily_card.dto.BoardResponse;
import woori.petmily_card.entity.Board;
import woori.petmily_card.entity.Member;
import woori.petmily_card.repository.MemberRepository;
import woori.petmily_card.service.BoardService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시글 목록 조회
    @GetMapping
    public BoardPageResponse list(@RequestParam(value = "page") int page){
        return boardService.showAll(page);
    }

    // 게시글 상세 정보
    @GetMapping("/{boardNo}")
    public BoardDetailResponse detail(@PathVariable(value = "boardNo") int boardNo){
        return boardService.show(boardNo);
    }

    // 게시글 생성
    @PostMapping
    public String save(@RequestParam String title,
                               @RequestParam String content) {
//        Optional<Member> member = memberRepository.findById(1); // 예시로 멤버 ID가 1인 경우
//        Board board = Board.builder()
//                .title(title)
//                .content(content)
//                .member(member.orElseThrow(() -> new IllegalArgumentException("Member not found")))
//                .createdAt(LocalDateTime.now())
//                .updatedAt(LocalDateTime.now())
//                .build();
//        boardService.save(board);
        return "redirect:/boards";
    }
//
//    // 게시글 수정 페이지
//    @GetMapping("/edit/{id}")
//    public String updateBoardPage(@PathVariable int id, Model model){
//        Board board = boardService.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid board ID: " + id));
//        model.addAttribute("board", board);
//        return "board-edit";
//    }
//
//    // 게시글 수정
//    @PostMapping("/edit/{id}")
//    public String updateBoard(@PathVariable int id,
//                              @RequestParam String title,
//                              @RequestParam String content) {
//        Board boardToUpdate = boardService.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid board ID: " + id));
//        boardToUpdate.setTitle(title);
//        boardToUpdate.setContent(content);
//        boardToUpdate.setUpdatedAt(LocalDateTime.now());
//        boardService.save(boardToUpdate);
//        return "redirect:/boards";
//    }
//
//    // 게시글 삭제
//    @PostMapping("/delete/{id}")
//    public String deleteBoard(@PathVariable int id){
//        boardService.deleteById(id);
//        return "redirect:/boards";
//    }
//    @GetMapping("/{page}")
//    public String showBoards(@PathVariable(value = "page")int page, Model model){
//        Page<BoardResponse> boards = boardService.showAll(page);
//        model.addAttribute("boards",boards.getContent());
//        model.addAttribute("currentPage",page);
//        model.addAttribute("totalPages",boards.getTotalPages());
//        return "board";
//    }
}