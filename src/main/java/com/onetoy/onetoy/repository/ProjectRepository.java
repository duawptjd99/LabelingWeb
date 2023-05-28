package com.onetoy.onetoy.repository;

import com.onetoy.onetoy.domain.Project.Project_Basic;
import com.onetoy.onetoy.domain.Project.Project_Class;
import com.onetoy.onetoy.domain.Project.Project_Image;
import com.onetoy.onetoy.domain.Project.Project_Shape;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class ProjectRepository {
    private final EntityManager em;

    public Project_Basic findOneBasicByName(String name) {
        List<Project_Basic> allList = findAllBasic();
        for(int i = 0; i<allList.size();i++) {
            if(name.equals(allList.get(i).getProjectname())){
                return allList.get(i);
            }
        }
        return null;
    }

    public List<Project_Class> findAllProjectClass(){
        return em.createQuery("select c from Project_Class c", Project_Class.class).getResultList();
    }

    public List<Project_Image> findAllProjectImage() {
        return em.createQuery("select c from Project_Image c", Project_Image.class).getResultList();
    }

    public List<Project_Shape> findAllProjectShape() {
        return em.createQuery("select c from Project_Shape c", Project_Shape.class).getResultList();
    }
    ///////////////////Setting Proejct//////////////////
    public List<Project_Class> findProjectClassById(Long pid) {
        List<Project_Class> allList = findAllProjectClass();
        List<Project_Class> result = new ArrayList<Project_Class>();
        for(int i = 0; i<allList.size();i++) {
            if(pid.equals(allList.get(i).getPid())){
                result.add(allList.get(i));
            }
        }
        return result;
    }

    public List<Project_Image> findProjectImageById(Long pid) {
        List<Project_Image> allList = findAllProjectImage();
        List<Project_Image> result = new ArrayList<Project_Image>();
        for(int i = 0; i<allList.size();i++) {
            if(pid.equals(allList.get(i).getPid())){
                result.add(allList.get(i));
            }
        }
        return result;
    }

    public List<Project_Shape> findProjectShapeById(Long pid) {
        List<Project_Shape> allList = findAllProjectShape();
        List<Project_Shape> result = new ArrayList<Project_Shape>();
        for(int i = 0; i<allList.size();i++) {
            if(pid.equals(allList.get(i).getPid())) {
                result.add(allList.get(i));
            }
        }
        return result;
    }
/////////////////////////////////////
    public List<Project_Basic> findAllBasic() {
        return em.createQuery("select c from Project_Basic c", Project_Basic.class).getResultList();
    }

    @Transactional
    public void saveBasic(Project_Basic projectBasic) {
        em.persist(projectBasic);
    }
    @Transactional
    public void saveClass(Project_Class projectClass) {
        em.persist(projectClass);
    }
    @Transactional
    public void saveShape(Project_Shape projectShape) {
        em.persist(projectShape);
    }
    @Transactional
    public void saveImage(Project_Image projectImage) {
        em.persist(projectImage);
    }
}
