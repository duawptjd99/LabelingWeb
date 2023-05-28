package com.onetoy.onetoy.domain.Project;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table  // 테이블 지정
@Data
@Getter
@Setter
public class Project_Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iid",nullable = false)
    private Long iid;

    private Long pid;

    private String imagename;
    private byte[] image;
}
