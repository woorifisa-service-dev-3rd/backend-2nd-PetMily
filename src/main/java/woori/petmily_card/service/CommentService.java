package woori.petmily_card.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import woori.petmily_card.entity.Comment;
import woori.petmily_card.repository.CommentRepository;


public interface CommentService {

    void addComment(int boardId, String content);

    void deleteCommentByBoardId(int boardId);


}
