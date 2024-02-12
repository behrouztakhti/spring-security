package com.behrouztakhti.security.domain;

import jakarta.persistence.*;
import java.util.Collection;

/**
 * The PERMISSION represents a low-level, granular permission/authority in the system.
 * @author behrouz.takhti@gmail.com
 * @version 1.0-SNAPSHOT
 */

@Entity
@Table(name = "PERMISSION")
@SequenceGenerator(name = "permissionSequence", sequenceName = "PERMISSION_SEQ", initialValue = 1, allocationSize = 1)
public class Permission extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permissionSequence")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME",length = 50, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "permissions")
    private Collection<Role> roles;


    public Permission() {
    }
    public Permission(String name, String description) {
        this.name = name;
        setDescription(description);
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

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
