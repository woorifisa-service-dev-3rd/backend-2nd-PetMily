package woori.petmily_card.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import woori.petmily_card.dto.comment.CommentRequest;
import woori.petmily_card.entity.Board;
import woori.petmily_card.entity.Comment;
import woori.petmily_card.entity.Member;
import woori.petmily_card.repository.BoardRepository;
import woori.petmily_card.repository.CommentRepository;
import woori.petmily_card.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Override
    public void save(CommentRequest commentRequest) {
        System.out.println(commentRequest.getMemberNo()+", "+commentRequest.getBoardNo());
        Member member = getMember(commentRequest.getMemberNo());
        Board board = getBoard(commentRequest.getBoardNo());
        Comment comment = commentRequest.toComment(member, board);
        commentRepository.save(comment);
    }

    private Member getMember(int memberNo) {
        return memberRepository.findById(memberNo).orElseThrow(() -> new RuntimeException("member 가 존재하지 않습니다."));
    }

    private Board getBoard(int boardNo) {
        return boardRepository.findById(boardNo).orElseThrow(() -> new RuntimeException("board 가 존재하지 않습니다."));
    }

    @Override
    public void remove(int commentNo) {
        commentRepository.deleteById(commentNo);
    }
}
