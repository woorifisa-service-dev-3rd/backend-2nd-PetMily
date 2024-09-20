package woori.petmily_card.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import woori.petmily_card.dto.HospitalPageResponse;
import woori.petmily_card.dto.HospitalResponse;
import woori.petmily_card.service.HospitalService;

@RestController
@RequestMapping("/hospitals")
@RequiredArgsConstructor
public class HospitalController {
    private final HospitalService hospitalService;

    @GetMapping
    public HospitalPageResponse showHospitals(@RequestParam(value = "page") int page) {
        return hospitalService.showAll(page);
    }

    @GetMapping("/{hospitalNo}")
    public HospitalResponse showHospital(@PathVariable(value = "hospitalNo") int hospitalNo) {
        return hospitalService.show(hospitalNo);
    }
}
