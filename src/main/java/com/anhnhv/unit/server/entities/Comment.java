package com.anhnhv.unit.server.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String createdAt;
    @JsonIgnore
    @ManyToOne
    private Post post;
    @JsonIgnore
    @ManyToOne
    private User user;
}
