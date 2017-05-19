package gjj.com.myapp.model;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

/**
 * Created by Administrator on 2016/1/20.
 */
@Entity
public class Tutor {
    @Id(autoincrement = false)
    private Long id;
    private String name;             //姓名
    private String no;               //职工号
    private String sex;              //性别
    private String departmentName;       //部门
    @Transient
    private List<Student> studentList;       //学生

    @Generated(hash = 380252782)
    public Tutor() {
    }

    @Generated(hash = 1704961349)
    public Tutor(Long id, String name, String no, String sex,
            String departmentName) {
        this.id = id;
        this.name = name;
        this.no = no;
        this.sex = sex;
        this.departmentName = departmentName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }



    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}

