package com.behrouztakhti.security.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.util.*;

/**
 * The ROLE represents the high-level roles of the user in the system. Each role will have a set of low-level permission.
 * @author behrouz.takhti@gmail.com
 * @version 1.0-SNAPSHOT
 */

@Entity
@Table(name = "ROLE")
@SequenceGenerator(name = "roleSequence", sequenceName = "ROLE_SEQ", initialValue = 1, allocationSize = 1)
public class Role extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleSequence")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME",length = 50, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    @ManyToMany
    @JoinTable(
            name="ROLE_PERMISSIONS",
            joinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="PERMISSION_ID", referencedColumnName="id")})
    private Collection<Permission> permissions;

    public Role() {
    }
    public Role(String name, String description, Collection<Permission> permissions) {
        this.name = name;
        setDescription(description);
        this.permissions = permissions;
    }

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

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public Collection<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Collection<Permission> permissions) {
        this.permissions = permissions;
    }
}
