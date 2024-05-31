package com.catering.manager.api.admin.model;

import com.thebooknation.admin.common.enums.Sexe;
import com.thebooknation.admin.model.audit.DateAudit;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

import static com.thebooknation.admin.common.util.AdminConstants.ADMIN_SCHEMA;
import static com.thebooknation.admin.common.util.AdminConstants.USER_TABLE;

@Entity
@Table(schema = ADMIN_SCHEMA, name = USER_TABLE,  uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
@XmlRootElement
@Data
@NoArgsConstructor
public class UserEntity extends DateAudit {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank
    @Size(max = 40)
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Size(max = 40)
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "sexe", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private Sexe sexe;

    @NaturalId
    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(max = 15)
    private String username;

    private Integer numberOfPoints;

    @NotBlank
    @Size(max = 100)
    private String password;

    @Size(max = 15)
    @Column(name = "phone_number")
    private String phoneNumber;

    @Size(max = 20)
    private String country;

    @Lob
    private byte[] picture;

    private String facebookURL;
    private String instagramURL;
    private String twitterURL;
    private String goodreadsURL;
    private String youtubeURL;

    private boolean active;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "join_user_roles", schema = ADMIN_SCHEMA,
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<RoleEntity> roleEntities = new ArrayList();
}
