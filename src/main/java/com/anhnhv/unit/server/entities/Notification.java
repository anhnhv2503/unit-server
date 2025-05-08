package com.anhnhv.unit.server.entities;

import com.anhnhv.unit.server.enums.NotificationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String content;
    private boolean isRead = false;
    private String location;
    @Enumerated(EnumType.STRING)
    private NotificationType type;
    private Long relatedId; //interact user
    private Long postId;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @ManyToOne
    private User user;

}
