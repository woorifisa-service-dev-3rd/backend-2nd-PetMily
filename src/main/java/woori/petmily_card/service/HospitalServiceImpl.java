package woori.petmily_card.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woori.petmily_card.dto.HospitalResponse;
import woori.petmily_card.entity.Hospital;
import woori.petmily_card.exception.PetMilyException;
import woori.petmily_card.repository.HospitalRepository;

import java.util.List;
import java.util.stream.Collectors;

import static woori.petmily_card.exception.ErrorCode.HOSPITAL_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional
public class HospitalServiceImpl implements HospitalService {
    private final HospitalRepository hospitalRepository;
    private final int SIZE = 5;

    @Override
    @Transactional(readOnly = true)
    public List<HospitalResponse> showAll(int page) {
        return getHospitals(page).stream().map(HospitalResponse::from).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public HospitalResponse show(int hospitalNo) {
        return HospitalResponse.from(getHospital(hospitalNo));
    }

    private Page<Hospital> getHospitals(int page) {
        Pageable pageable = PageRequest.of(page, SIZE);
        return hospitalRepository.findAll(pageable);
    }

    private Hospital getHospital(int hospitalNo) {
        return hospitalRepository.findById(hospitalNo).orElseThrow(() -> new PetMilyException(HOSPITAL_NOT_FOUND));
    }
}
