package woori.petmily_card.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor

@Builder
@Getter
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardNo;

    @ManyToOne
    @JoinColumn(name = "member_no", nullable = false)
    private Member member;

    @Column(name = "card_number", nullable = false)
    private int serialNo;

    @Column(name = "expiration_date", nullable = false)
    private Date expirationDate;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}