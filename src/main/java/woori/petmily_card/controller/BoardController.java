package woori.petmily_card.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import woori.petmily_card.dto.board.BoardDetailResponse;
import woori.petmily_card.dto.board.BoardPageResponse;
import woori.petmily_card.dto.board.BoardRequest;
import woori.petmily_card.service.BoardService;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public BoardPageResponse list(@RequestParam(value = "page") int page){
        return boardService.showAll(page);
    }

    @GetMapping("/{boardNo}")
    public BoardDetailResponse detail(@PathVariable(value = "boardNo") int boardNo){
        return boardService.show(boardNo);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody BoardRequest boardRequest) {
        boardService.save(boardRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{boardNo}")
    public ResponseEntity<Void> modify(@PathVariable(value = "boardNo") int boardNo, @RequestBody BoardRequest boardRequest) {
        boardService.modify(boardNo, boardRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{boardNo}")
    public ResponseEntity<Void> remove(@PathVariable(value = "boardNo") int boardNo) {
        boardService.remove(boardNo);
        return ResponseEntity.ok().build();
    }
}