package com.riqra.assessment.security;

public class SecurityConstants {
    public static final String SECRET = "!z%C*F-JaNcRfUjXn2r5u8x/A?D(G+KbPeSgVkYp3s6v9y$B&E)H@McQfTjWmZq4";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final Long EXPIRATION_TIME = 1000L*60*30;

    private SecurityConstants () {}
}
