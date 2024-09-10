package woori.petmily_card.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import woori.petmily_card.entity.Card;
import woori.petmily_card.service.CardService;

import java.util.Optional;

@RestController
@RequestMapping("petmily/cards")
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/management")
    public ResponseEntity<Card> getCardManagement() {
        Card card = new Card();
        return new ResponseEntity<>(card, HttpStatus.OK);
    }

    // 카드 조회
    @GetMapping("/view")
    public ResponseEntity<Object> viewCard(@RequestParam int serialNo) {
        Optional<Card> card = cardService.getCardBySerialNo(serialNo);
        if (card.isPresent()) {
            return new ResponseEntity<>(card.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("카드를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
        }
    }

    // 카드 해지
    @PostMapping("/cancel")
    public ResponseEntity<String> cancelCard(@RequestParam int cardNo) {
        boolean isDeleted = cardService.cancelCard(cardNo);
        if (isDeleted) {
            return new ResponseEntity<>("카드가 해지 되었습니다.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("카드가 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        }
    }

    // 카드 발급
    @PostMapping("/issue")
    public ResponseEntity<String> issueCard() {
        Card card = new Card();
        card.setMember(null);

        try {
            cardService.issueCard(card);
            return new ResponseEntity<>("카드가 발급되었습니다.", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("카드 발급 실패: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}