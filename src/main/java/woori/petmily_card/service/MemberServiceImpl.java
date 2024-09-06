package woori.petmily_card.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import woori.petmily_card.dto.MemberDto;
import woori.petmily_card.entity.Member;
import woori.petmily_card.exception.PetMilyException;
import woori.petmily_card.repository.MemberRepository;

import static woori.petmily_card.exception.ErrorCode.*;

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
        Member member = memberRepository.findById(memberDto.getId());
        if (member == null) {
            throw new PetMilyException(MEMBER_NOT_FOUND);
        }
        if (!member.getPassword().equals(memberDto.getPassword())) {
            throw new PetMilyException(INVALID_INPUT);
        }
        return member;
    }

    private boolean checkDuplicateId(String id) {
        return memberRepository.findById(id) != null;
    }
}
