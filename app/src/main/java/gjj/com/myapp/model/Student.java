package gjj.com.myapp.model;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Created by Administrator on 2016/1/21.
 */
@Entity
public class Student {
    @Id(autoincrement = false)
    private Long id;
    private Integer version;                      //版本号
    private String no;                          //学生的学号
    private String contact;                          //学生的手机号
    private String name;                         //学生的姓名
    @Transient
    private Major major;                          //学生所在的专业
    private String classDescription;
    @Transient
    private StudentClass studentClass;                 //学生所在的班级
    private Long tutorId;                     //指导老师的id
    private Long replyGroup_id;               //学生所在的答辩小组的id
    private Long replyGraduateProject_id;     //学生所答辩课题的服务器端id
    /*@Column(column = "isAddressee")
    private boolean isAddressee;//学生是否是收件方*/







    public Long getTutorId() {
        return tutorId;
    }

    public void setTutorId(Long tutorId) {
        this.tutorId = tutorId;
    }

    public Long getReplyGroup_id() {
        return replyGroup_id;
    }

    public void setReplyGroup_id(Long replyGroup_id) {
        this.replyGroup_id = replyGroup_id;
    }

    public Long getReplyGraduateProject_id() {
        return replyGraduateProject_id;
    }

    public void setReplyGraduateProject_id(Long replyGraduateProject_id) {
        this.replyGraduateProject_id = replyGraduateProject_id;
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


    public StudentClass getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(StudentClass studentClass) {
        this.studentClass = studentClass;
    }


    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }




    public String getClassDescription() {
        return classDescription;
    }

    public void setClassDescription(String classDescription) {
        this.classDescription = classDescription;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    private class StudentClass {
        private long id;
        private String description;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "StudentClass{" +
                    "description='" + description + '\'' +
                    '}';
        }
    }

    private class Major{
        private long id;
        private String description;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
