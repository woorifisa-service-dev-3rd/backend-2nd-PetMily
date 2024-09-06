package woori.petmily_card.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import woori.petmily_card.service.TransactionService;

@Controller
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("/save")
    public String save() {
        return "saveTransaction";
    }

    @PostMapping
    public String saveTransaction(int cardNo, int hospitalNo, int amount) {
        transactionService.save(cardNo, hospitalNo, amount);
        return "tmp";
    }

    @GetMapping("/show/{cardNo}/{page}")
    public String showTransactions(@PathVariable(value="cardNo") int cardNo,
                                   @PathVariable(value="page") int page, Model model) {
        model.addAttribute("transactions", transactionService.show(cardNo, page));
        return "showTransaction";
    }
}
