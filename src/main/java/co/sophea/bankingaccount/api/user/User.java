package co.sophea.bankingaccount.api.user;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "uuid")
    private UUID uuid;
    @Column(name = "name" , nullable = true , length = 50)
    private String name;
    @Column(name = "gender")
    private String gender;
    @Column(name = "one_signal_id")
    private String oneSignalId;
    @Column(name = "is_deleted")
    private Boolean isDeleted;
    @Column(name = "is_student")
    private Boolean isStudent;
    @Column(name = "student_card_id")
    private String studentCardId;
    @Column(name = "email" , length = 50)
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "is_verified")
    private Boolean isVerified;
    @Column(name = "verified_code")
    private String verifiedCode;
    @Column(name = "phone_number" , length = 12)
    private String phoneNumber;

    @OneToMany(mappedBy = "user")
    private List<UserRole> userRoles;
}
