package kz.axelrodadil.bookstore_samgau.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role_email")
    private String roleEmail;

    @Column(name = "role_first_name")
    private String roleFirstName;

    @Column(name = "role_last_name")
    private String roleLastName;

    @Column(name = "role_password")
    private String rolePassword;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role_name")
    private RoleEnum roleName;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role_status")
    private Status roleStatus;
}
