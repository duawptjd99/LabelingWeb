package com.onetoy.onetoy.service;

import ch.qos.logback.classic.spi.PackagingDataCalculator;
import com.onetoy.onetoy.domain.Project.*;
import com.onetoy.onetoy.repository.ProjectRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public String[] getProjectNameList() {
        List<Project_Basic> projectList = this.projectRepository.findAllBasic();
        String[] projectNameList = new String[projectList.size()];
        for(int i = 0; i<projectList.size();i++) {
               projectNameList[i] = projectList.get(i).getProjectname();
        }
        return projectNameList;
    }

    ///////////////Task//////////////////
    public Project_Basic getSelectProject(String name) {
        return this.projectRepository.findOneBasicByName(name);
    }

    public Project getSelectAllProject(Project_Basic project_Basic) {
        Project project = new Project();
        project.setProject_Basic(project_Basic);
        project.setProject_Class(this.projectRepository.findProjectClassById(project_Basic.getPid()));
        project.setProject_Image(this.projectRepository.findProjectImageById(project_Basic.getPid()));
        project.setProject_Shape(this.projectRepository.findProjectShapeById(project_Basic.getPid()));
        return project;
    }

    ///////////////Create Project///////////////
    public boolean createProject(Long uid, String projectName, String simpleExplain, ArrayList<String> classNameList, ArrayList<String> shapeList, MultipartFile zipFile)  {
        try {
        ///////////////////베이직 등록//////////////////
            this.projectRepository.saveBasic(createBasic(uid, projectName, simpleExplain));
        ///////////////////데이터 세팅/////////////////
        Long pid = this.projectRepository.findOneBasicByName(projectName).getPid();

        ArrayList<Project_Class> project_Class = this.createClass(pid, classNameList);

        ArrayList<Project_Shape> project_Shape = this.createShape(pid, shapeList);

         ArrayList<Project_Image> project_Image = this.createImage((long)3,zipFile);


        ///////////////////데이터 저장/////////////////
        for (int i = 0; i < project_Class.size(); i++) {
            this.projectRepository.saveClass(project_Class.get(i));
        }


        for (int i = 0; i < project_Shape.size(); i++) {
            this.projectRepository.saveShape(project_Shape.get(i));
        }

        for(int i = 0 ; i<project_Image.size();i++) {
            this.projectRepository.saveImage(project_Image.get(i));
        }

            return true;
        } catch (IOException e) {
           System.out.println(e);
           return false;
          //  throw new RuntimeException(e);
      }
    }

    public Project_Basic createBasic(Long uid, String projectName, String simpleExplain) {
        Project_Basic projectbasic = new Project_Basic();
        projectbasic.setUid(uid);
        projectbasic.setProjectname(projectName);
        projectbasic.setSimpleexplain(simpleExplain);
        return projectbasic;
    }

    public ArrayList<Project_Class> createClass(Long pid, ArrayList<String> classNameList) {
        ArrayList<Project_Class> projectClassList = new ArrayList<Project_Class>();
        for (int i = 0; i < classNameList.size(); i++) {
            Project_Class project_Class = new Project_Class();
            project_Class.setPid(pid);
            project_Class.setClassname(classNameList.get(i));
            projectClassList.add(project_Class);
        }
        return projectClassList;
    }

    public ArrayList<Project_Shape> createShape(Long pid, ArrayList<String> shapeList) {
        ArrayList<Project_Shape> projectShapeList = new ArrayList<Project_Shape>();
        for (int i = 0; i < shapeList.size(); i++) {
            Project_Shape project_Shape = new Project_Shape();
            project_Shape.setPid(pid);
            project_Shape.setShape(shapeList.get(i));
            projectShapeList.add(project_Shape);
        }
        return projectShapeList;
    }

    public ArrayList<Project_Image> createImage(Long pid, MultipartFile zipFile) throws IOException {
        ArrayList<Project_Image> projectImageList = new ArrayList<Project_Image>();
        //임시 파일이름으로 대체
        ArrayList<byteName> fileList = unZipFile(zipFile);
        for(int i = 0 ; i<fileList.size();i++) {
            Project_Image project_Image = new Project_Image();
            project_Image.setPid(pid);
            project_Image.setImagename(fileList.get(i).getName());
            project_Image.setImage(fileList.get(i).getBytes());
            projectImageList.add(project_Image);
        }
        return projectImageList;
    }

    public ArrayList<byteName> unZipFile(MultipartFile zipFile) throws IOException {
        //File file = (File) zipFile;
       // zipFile.transferTo(file);
        File convFile = new File("junk/"+zipFile.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(zipFile.getBytes());
        fos.close();

        BufferedInputStream in = new BufferedInputStream(new FileInputStream(convFile));
        ZipInputStream zipInputStream = new ZipInputStream(in);
        ZipEntry zipEntry = null;
        ArrayList<byteName> unzipList = new ArrayList<byteName>();
        while((zipEntry = zipInputStream.getNextEntry())!=null){

            if(!zipEntry.getName().contains("MACOSX")){
                byteName bn = new byteName();
                bn.setName(zipEntry.getName());
                File imageFile = new File("junk/"+zipEntry.getName());
                imageFile.createNewFile();
                FileOutputStream imagefos = new FileOutputStream(imageFile);
                byte[] buffer = new byte[256];
                int size = 0;
              //   Zip스트림으로부터 byte뽑아내기
                while ((size = zipInputStream.read(buffer)) > 0) {
                    // byte로 파일 만들기
                    imagefos.write(buffer, 0, size);
                }
                imagefos.close();
               ByteArrayOutputStream baos = new ByteArrayOutputStream();
               FileInputStream fis = new FileInputStream(imageFile);

               int read =0;
               while((read=fis.read(buffer,0,buffer.length))!=-1){
                   baos.write(buffer,0,read);
               }
               bn.setBytes(baos.toByteArray());
               unzipList.add(bn);
                imageFile.delete();
            }
        }
        zipInputStream.close();
        convFile.delete();
        return unzipList;
    }
    @Getter
    @Setter
    private class byteName{
        private byte[] bytes;
        private String name;

    }


}