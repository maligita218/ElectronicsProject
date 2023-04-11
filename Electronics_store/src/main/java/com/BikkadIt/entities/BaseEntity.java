package com.BikkadIt.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class BaseEntity implements Serializable {

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at",insertable = false)
    private LocalDateTime updatedAt;
    @Column(name = "is_active")
    private Character isActive;
}
