package gcs.moi.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String token;
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role = Role.MEMBER;

    private Member(String token, String name) {
        this.token = token;
        this.name = name;
    }

    public static Member of(String token, String name) {
        return new Member(token, name);
    }
}
