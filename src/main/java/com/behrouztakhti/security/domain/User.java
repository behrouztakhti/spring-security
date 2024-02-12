package com.behrouztakhti.security.domain;

import jakarta.persistence.*;
import java.util.*;
/**
 * The USER entity.
 * @author behrouz.takhti@gmail.com
 * @version 1.0-SNAPSHOT
 */



@Entity
@Table(name = "USERS")
@SequenceGenerator(name = "usersSequence", sequenceName = "USERS_SEQ", initialValue = 1, allocationSize = 1)
public class User {

    public User() {
    }
    public User(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usersSequence")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "FAMILY", length = 100, nullable = false)
    private String family;

    @Column(name = "USERNAME", unique = true, length = 40, nullable = false)
    private String username;

    @Column(name = "PASSWORD", length = 100, nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Tokens> tokens;

    @ManyToMany
    @JoinTable(
            name="USER_ROLES",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="id")})
    private Collection<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Tokens> getTokens() {
        return tokens;
    }

    public void setTokens(List<Tokens> tokens) {
        this.tokens = tokens;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
