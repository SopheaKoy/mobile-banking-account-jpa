package co.sophea.bankingaccount.api.authority;

import co.sophea.bankingaccount.api.user.UserRole;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "roles")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "role")
    @JsonBackReference
    private List<UserRole> userRoles;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Authority> authorities;

    @Override
    public String getAuthority() {
        return "ROLE_" + name;
    }
}
