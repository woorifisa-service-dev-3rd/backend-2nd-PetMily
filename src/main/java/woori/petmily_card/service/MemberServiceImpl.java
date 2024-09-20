package woori.petmily_card.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import woori.petmily_card.dto.MemberDto;
import woori.petmily_card.entity.Member;
import woori.petmily_card.exception.PetMilyException;
import woori.petmily_card.repository.MemberRepository;

import java.util.Optional;

import static woori.petmily_card.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public void register(MemberDto memberDto) {
        checkDuplicateId(memberDto.getId());
        memberRepository.save(memberDto.toMember());
    }

    @Override
    public Member login(MemberDto memberDto) {
        Member member = findMemberById(memberDto.getId());
        validateCorrectPassword(memberDto, member);
        return member;
    }

    private void checkDuplicateId(String id) {
        if (memberRepository.findById(id).isPresent()) throw new PetMilyException(ALREADY_EXIST_ID);
    }

    private Member findMemberById(String id) {
        return memberRepository.findById(id).orElseThrow(() -> new PetMilyException(MEMBER_NOT_FOUND));
    }

    private void validateCorrectPassword(MemberDto memberDto, Member member) {
        if (!memberDto.getPassword().equals(member.getPassword())) throw new PetMilyException(INVALID_PASSWORD);
    }
}
