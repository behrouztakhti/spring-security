package com.behrouztakhti.security.domain;

import com.behrouztakhti.security.types.TokenType;
import jakarta.persistence.*;


/**
 * The TOKENS entity.
 * @author behrouz.takhti@gmail.com
 * @version 1.0-SNAPSHOT
 */
@Entity
@Table(name = "TOKENS")
@SequenceGenerator(name = "tokenSequence", sequenceName = "TOKENS_SEQ", initialValue = 1, allocationSize = 1)
public class Tokens {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tokenSequence")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "TOKEN", unique = true)
    private String token;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType = TokenType.BEARER;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TOKEN_USR_ID", nullable = false)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
