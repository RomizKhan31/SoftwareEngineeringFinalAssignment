package com.imanbooster.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ImanTest {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer score;
    @ManyToOne
    private User user;
    private LocalDateTime dateTaken;
    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public LocalDateTime getDateTaken() { return dateTaken; }
    public void setDateTaken(LocalDateTime dateTaken) { this.dateTaken = dateTaken; }
}
