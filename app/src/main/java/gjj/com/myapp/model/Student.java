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
    @Id
    private Long id;
    private Integer version;                      //版本号
    private String no;                          //学生的学号
    private String phoneNumber;                          //学生的手机号
    private String name;                         //学生的姓名
    private String major;                          //学生所在的专业
    private String classDescription;
    @Transient
    private StudentClass studentClass;                 //学生所在的班级
    private Long tutorId;                     //指导老师的id
    private Long replyGroup_id;               //学生所在的答辩小组的id
    private Long serverId;                    //学生服务器端的id
    private Long replyGraduateProject_id;     //学生所答辩课题的服务器端id
    /*@Column(column = "isAddressee")
    private boolean isAddressee;//学生是否是收件方*/


    @Generated(hash = 343840845)
    public Student(Long id, Integer version, String no, String phoneNumber,
            String name, String major, String classDescription, Long tutorId,
            Long replyGroup_id, Long serverId, Long replyGraduateProject_id) {
        this.id = id;
        this.version = version;
        this.no = no;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.major = major;
        this.classDescription = classDescription;
        this.tutorId = tutorId;
        this.replyGroup_id = replyGroup_id;
        this.serverId = serverId;
        this.replyGraduateProject_id = replyGraduateProject_id;
    }

    @Generated(hash = 1556870573)
    public Student() {
    }


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

    public Long getServerId() {
        return serverId;
    }

    public void setServerId(Long serverId) {
        this.serverId = serverId;
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


    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", version=" + version +
                ", no='" + no + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", name='" + name + '\'' +
                ", major='" + major + '\'' +
                ", classDescription='" + classDescription + '\'' +
                ", studentClass=" + studentClass +
                ", tutorId=" + tutorId +
                ", replyGroup_id=" + replyGroup_id +
                ", serverId=" + serverId +
                ", replyGraduateProject_id=" + replyGraduateProject_id +
                '}';
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private class StudentClass {
        private String description;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return "StudentClass{" +
                    "description='" + description + '\'' +
                    '}';
        }
    }
}
