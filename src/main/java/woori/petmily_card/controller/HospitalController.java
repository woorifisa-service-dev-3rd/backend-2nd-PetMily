package woori.petmily_card.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import woori.petmily_card.service.HospitalService;

@Controller
@RequestMapping("/hospitals")
@RequiredArgsConstructor
public class HospitalController {
    private final HospitalService hospitalService;

    @GetMapping("/{page}")
    public String showHospitals(@PathVariable(value = "page") int page, Model model) {
        model.addAttribute("hospitals", hospitalService.showAll(page));
        return "hospital/showHospital";
    }

    @GetMapping("/detail/{hospitalNo}")
    public String showHospital(@PathVariable(value = "hospitalNo") int hospitalNo, Model model) {
        model.addAttribute("hospital", hospitalService.show(hospitalNo));
        return "hospital/showHospitalDetail";
    }
}
