package com.onetoy.onetoy.domain.Project;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table// 테이블 지정
@Data
@Getter
@Setter
public class Project_Shape {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid",nullable = false)
    private Long sid;

    private Long pid;

    private String shape;
}
