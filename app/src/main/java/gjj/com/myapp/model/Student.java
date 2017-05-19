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
    private String no;                          //学生的学号
    private String contact;                     //学生的手机号
    private String name;                        //学生的姓名
    private String majorDecription;             //学生所在的专业
    private String studentClass;                //学生所在的班级名称
    private Long tutorId;                       //指导老师的id
    private Long projectId;                     //学生答辩课题的id
    private Long replyGroupId;                     //学生答辩小组的id





    @Generated(hash = 1302890017)
    public Student(Long id, String no, String contact, String name,
            String majorDecription, String studentClass, Long tutorId,
            Long projectId, Long replyGroupId) {
        this.id = id;
        this.no = no;
        this.contact = contact;
        this.name = name;
        this.majorDecription = majorDecription;
        this.studentClass = studentClass;
        this.tutorId = tutorId;
        this.projectId = projectId;
        this.replyGroupId = replyGroupId;
    }

    @Generated(hash = 1556870573)
    public Student() {
    }





    public Long getReplyGroupId() {
        return replyGroupId;
    }

    public void setReplyGroupId(Long replyGroupId) {
        this.replyGroupId = replyGroupId;
    }

    public Long getTutorId() {
        return tutorId;
    }

    public void setTutorId(Long tutorId) {
        this.tutorId = tutorId;
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


    public String getMajorDecription() {
        return majorDecription;
    }

    public void setMajorDecription(String majorDecription) {
        this.majorDecription = majorDecription;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
