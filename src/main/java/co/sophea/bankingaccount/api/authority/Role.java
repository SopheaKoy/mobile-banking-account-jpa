package co.sophea.bankingaccount.api.authority;

import co.sophea.bankingaccount.api.user.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "roles")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "role")
    private List<UserRole> userRoles;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Authority> authorities;
}
