package woori.petmily_card.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import woori.petmily_card.dto.TransactionResponse;
import woori.petmily_card.service.TransactionService;

@Controller
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping
    public String toSave() {
        return "transaction/saveTransaction";
    }

    @PostMapping("/{hospitalNo}")
    public String saveTransaction(@PathVariable(value = "hospitalNo") int hospitalNo, int amount, Model model) {
        int memberNo = 1; // 회원 기능 연결 후 수정
        model.addAttribute("amount", transactionService.save(memberNo, hospitalNo, amount));
        return "transaction/showReceipt";
    }

    @GetMapping("/{page}")
    public String showTransactions(@PathVariable(value="page") int page, Model model) {
        int memberNo = 1; // 회원 기능 연결 후 수정
        Page<TransactionResponse> transactions = transactionService.show(memberNo, page);
        model.addAttribute("transactions", transactions.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", transactions.getTotalPages());
        return "transaction/showTransaction";
    }
}
