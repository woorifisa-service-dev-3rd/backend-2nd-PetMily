package woori.petmily_card.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import woori.petmily_card.dto.MemberDto;
import woori.petmily_card.entity.Member;
import woori.petmily_card.exception.PetMilyException;
import woori.petmily_card.repository.MemberRepository;

import static woori.petmily_card.exception.ErrorCode.ALREADY_EXIST_ID;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public void register(MemberDto memberDto) {
        if (checkDuplicateId(memberDto.getId())) {
            throw new PetMilyException(ALREADY_EXIST_ID);
        }

        Member member = Member.builder()
                .name(memberDto.getName())
                .id(memberDto.getId())
                .password(memberDto.getPassword())
                .phoneNumber(memberDto.getPhoneNumber())
                .point(0)
                .build();
        memberRepository.save(member);
    }

    @Override
    public Member login(MemberDto memberDto) {
        return memberRepository.findByIdAndPassword(memberDto.getId(), memberDto.getPassword());
    }

    @Override
    public boolean checkDuplicateId(String id) {
        return memberRepository.findById(id) != null;
    }
}
