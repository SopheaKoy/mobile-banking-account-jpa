package co.sophea.bankingaccount.api.user;

import co.sophea.bankingaccount.api.authority.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "users_roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private User user;

    @ManyToOne
    private Role role;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
