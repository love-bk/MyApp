package gjj.com.myapp.model;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

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
    private String studentClass;                 //学生所在的班级
    private Integer tutorId;                     //指导老师的id
    private Integer replyGroup_id;               //学生所在的答辩小组的id
    private Integer serverId;                    //学生服务器端的id
    private Integer replyGraduateProject_id;     //学生所答辩课题的服务器端id
    /*@Column(column = "isAddressee")
    private boolean isAddressee;//学生是否是收件方*/







    @Generated(hash = 1224611842)
    public Student(Long id, Integer version, String no, String phoneNumber,
            String name, String major, String studentClass, Integer tutorId,
            Integer replyGroup_id, Integer serverId,
            Integer replyGraduateProject_id) {
        this.id = id;
        this.version = version;
        this.no = no;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.major = major;
        this.studentClass = studentClass;
        this.tutorId = tutorId;
        this.replyGroup_id = replyGroup_id;
        this.serverId = serverId;
        this.replyGraduateProject_id = replyGraduateProject_id;
    }

    @Generated(hash = 1556870573)
    public Student() {
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

    public Integer getReplyGraduateProject_id() {
        return replyGraduateProject_id;
    }

    public void setReplyGraduateProject_id(Integer replyGraduateProject_id) {
        this.replyGraduateProject_id = replyGraduateProject_id;
    }

    public Integer getReplyGroup_id() {
        return replyGroup_id;
    }

    public void setReplyGroup_id(Integer replyGroup_id) {
        this.replyGroup_id = replyGroup_id;
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public Integer getTutorId() {
        return tutorId;
    }

    public void setTutorId(Integer tutorId) {
        this.tutorId = tutorId;
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
                ", studentClass='" + studentClass + '\'' +
                ", tutorId=" + tutorId +
                ", replyGroup_id=" + replyGroup_id +
                ", serverId=" + serverId +
                ", replyGraduateProject_id=" + replyGraduateProject_id +
                '}';
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
}
