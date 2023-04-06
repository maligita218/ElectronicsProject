package com.BikkadIt.entities;


import jdk.jfr.Timestamp;
import lombok.*;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Builder
public class ProductBaseEntity {

@CreationTimestamp
@Column(name="Added_Date",nullable = false,updatable = false)
    private LocalDateTime addedDate;
@Column(name="is_live")
    private boolean live;

@Column(name="Stoke")
    private boolean stock;
}
