package com.shopping.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
@Getter @Setter
public abstract class BaseTimeEntity {

    @CreatedDate // Entity 생성시 자동으로 시간 기록
    @Column(updatable = false) // Entity 수정시 나는 갱신 안 한다.
    private LocalDateTime regDate ;

    @LastModifiedDate // Entity 수정시 자동으로 시간을 기록
    private LocalDateTime updateDate ;

}
