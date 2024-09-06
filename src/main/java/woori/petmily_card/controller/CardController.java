package woori.petmily_card.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import woori.petmily_card.entity.Card;
import woori.petmily_card.service.CardService;

import java.util.Optional;

@Controller
@RequestMapping("/card")
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/management")
    public ModelAndView cardManagementPage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("card/management");
        mav.addObject("card", new Card());
        return mav;
    }

    @GetMapping("/view")
    public ModelAndView viewCard(@RequestParam int serialNo) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("card/management");

        Optional<Card> card = cardService.getCardBySerialNo(serialNo);
        if (card.isPresent()) {
            mav.addObject("card", card.get());
            mav.addObject("message", null); // 카드가 존재할 경우 메시지 초기화
        } else {
            mav.addObject("card", "Card not found");
            mav.addObject("message", "카드를 찾을 수 없습니다.");
        }
        return mav;
    }

    @PostMapping("/issue")
    public ModelAndView issueCard(@ModelAttribute Card card) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("card/management");
        Card issuedCard = cardService.issueCard(card);
        mav.addObject("card", issuedCard);
        mav.addObject("message", "카드가 성공적으로 생성되었습니다.");
        return mav;
    }

    @PostMapping("/cancel")
    public ModelAndView cancelCard(@RequestParam int cardNo) {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("card/management");
        boolean isDeleted = cardService.cancelCard(cardNo);

        if (isDeleted) {
            mav.addObject("cancelMessage", "카드가 해지 되었습니다.");
        } else {
            mav.addObject("cancelMessage", "카드가 존재하지 않습니다.");
        }
        return mav;
    }
}
