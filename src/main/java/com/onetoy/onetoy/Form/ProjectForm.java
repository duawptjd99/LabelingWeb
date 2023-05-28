package com.onetoy.onetoy.Form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProjectForm {
    private String projectName;
    private String simpleExplain;

    private ArrayList<String> classNameList;
    //shape는 아직 정의하지 않았음으로 임시 string 정의
    private ArrayList<String> shapeList;

    private MultipartFile imageZipFile;
    public ProjectForm() {
        this.classNameList = new ArrayList<String>();
        this.shapeList = new ArrayList<String>();
    }

}
