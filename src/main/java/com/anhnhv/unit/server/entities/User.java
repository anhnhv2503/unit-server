package com.anhnhv.unit.server.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Username is required")
    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    private String username;
    @Email(message = "Email is invalid")
    @NotEmpty(message = "Email is required")
    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    private String email;
    @JsonIgnore
    @NotEmpty
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
    @JsonIgnore
    @NotEmpty
    @Size(min = 6)
    private String confirmPassword;
    @Column(nullable = true)
    private Date dateOfBirth;
    private String avatar;
    @CreatedDate
    private Date createdAt;
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.MERGE,
    })
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

}
