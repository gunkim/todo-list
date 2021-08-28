package dev.gunlog.domain.entity.common;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {
    @NotNull
    @Column(name = "created_date")
    @CreatedDate
    private LocalDateTime createdDate;
    @NotNull
    @Column(name = "modified_date")
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
