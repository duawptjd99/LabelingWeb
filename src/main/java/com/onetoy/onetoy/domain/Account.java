package com.onetoy.onetoy.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

@Entity
@Table // 테이블 지정
@Data
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "uid", nullable = false)
    private Long uid;
    private String id;
    private String pw;

    private String name;


}
