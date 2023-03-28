package com.BikkadIt.dto;

import lombok.*;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public  class BaseEntityDto implements Serializable {

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Character isActive;



}
