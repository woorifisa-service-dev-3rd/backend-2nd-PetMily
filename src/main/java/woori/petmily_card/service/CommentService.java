package woori.petmily_card.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import woori.petmily_card.dto.comment.CommentRequest;
import woori.petmily_card.entity.Comment;
import woori.petmily_card.repository.CommentRepository;


public interface CommentService {

    void save(CommentRequest commentRequest);

    void remove(int commentNo);

}
