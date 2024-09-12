package woori.petmily_card.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import woori.petmily_card.dto.comment.CommentRequest;
import woori.petmily_card.service.CommentService;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody CommentRequest commentRequest) {
        commentService.save(commentRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{commentNo}")
    public ResponseEntity<Void> remove(@PathVariable int commentNo) {
        commentService.remove(commentNo);
        return ResponseEntity.ok().build();
    }
}
