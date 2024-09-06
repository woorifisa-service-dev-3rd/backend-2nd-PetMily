package woori.petmily_card.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @Column(name = "member_no", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "id", nullable = false)
    private String username;

    @Column(name = "birthday", nullable = false)
    private String birthDate;

    @Column(name = "phone", nullable = false)
    private String phoneNumber;

    @Column(name = "job")
    private String occupation;

    @Column(name = "point", nullable = false)
    private int points;

    @OneToMany(mappedBy = "member")
    private List<Board> boards;

    @OneToMany(mappedBy = "member")
    private List<Card> cards;

    @OneToMany(mappedBy = "member")
    private List<Comment> comments;
}
