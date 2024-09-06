package woori.petmily_card.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/show")
    public String showTransactions(int cardNo, int page) {
        transactionService.show(cardNo, page);
        return "";
    }
}
