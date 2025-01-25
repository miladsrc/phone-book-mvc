package com.beans;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String role;  // "USER" or "ADMIN"

    // Getter & Setter
}
