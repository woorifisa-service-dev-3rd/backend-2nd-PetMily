package woori.petmily_card.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import woori.petmily_card.entity.Card;
import woori.petmily_card.service.CardService;

import java.util.Optional;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/management")
    public ResponseEntity<Page<Card>> cardManagementPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "serialNo") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<Card> cardPage = cardService.findAllCards(pageable);

        return ResponseEntity.ok(cardPage);
    }

    @GetMapping("/view")
    public ResponseEntity<?> viewCard(@RequestParam int serialNo) {
        Optional<Card> card = cardService.getCardBySerialNo(serialNo);

        if (card.isPresent()) {
            return ResponseEntity.ok(card.get()); // 카드 데이터와 함께 200 OK 응답
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("카드를 찾을 수 없습니다.");
        }
    }

    @PostMapping("/cancel")
    public ResponseEntity<String> cancelCard(@RequestParam int cardNo) {
        boolean isDeleted = cardService.cancelCard(cardNo);

        if (isDeleted) {
            return ResponseEntity.ok("카드가 해지 되었습니다."); // 200 OK 응답
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("카드가 존재하지 않습니다.");
        }
    }

    @PostMapping("/issue")
    public ResponseEntity<String> issueCard() {
        Card card = new Card();
        card.setMember(null); // 임의의 member 데이터 처리

        try {
            cardService.issueCard(card);
            return ResponseEntity.ok("카드가 발급되었습니다."); // 200 OK 응답
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("카드 발급 실패: " + e.getMessage());
        }
    }
}