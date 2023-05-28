package com.onetoy.onetoy.domain.Project;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Project {
    private Project_Basic project_Basic;
    private List<Project_Class> project_Class;
    private List<Project_Image> project_Image;
    private List<Project_Shape> project_Shape;

}
