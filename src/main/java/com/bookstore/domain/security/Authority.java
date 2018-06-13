package com.bookstore.domain.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class Authority implements GrantedAuthority, Serializable {

    private static final long serialVersionUID = 123L;

    private final String authority;
}
