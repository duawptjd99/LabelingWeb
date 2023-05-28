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
public class Project_Basic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid",nullable = false)
    private Long pid;
    private Long uid;
    private String projectname;
    private String simpleexplain;

}
