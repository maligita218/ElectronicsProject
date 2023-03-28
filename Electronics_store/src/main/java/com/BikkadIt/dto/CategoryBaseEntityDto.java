package com.BikkadIt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class CategoryBaseEntityDto implements Serializable {

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Character isActive;
}
