package com.usertracker.modal.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@SuperBuilder(toBuilder = true)
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
@SQLDelete(sql = "UPDATE roles SET status = 0 WHERE role_id=?")
@SQLRestriction("status <> 0")
@DynamicUpdate
public class Role extends BaseEntity{
    @Id
    @Column(name = "role_id", length = 36)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String systemName;

    private int level;

    @Builder.Default
    @Column(name = "is_primary")
    private boolean isPrimary = false;

    @ToString.Exclude
    @Builder.Default
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_parent_role_id", foreignKey = @ForeignKey(name = "fk_roles_parent_role_id"))
    private Role parentRole;

    @ToString.Exclude
    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentRole")
    private Set<Role> childRoles = new HashSet<>();

}
