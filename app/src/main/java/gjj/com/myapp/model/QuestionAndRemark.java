package gjj.com.myapp.model;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ${高娟娟} on 2016/6/4.
 * 该类表示学生的问题和导师的评语
 */
@Entity
public class QuestionAndRemark {
    @Id
    private Integer id;
    //该学生的课提
    private Integer graduateProjectId;
    //老师提问的问题
    private String question;
    //老师的评语
    private String remark;

    @Generated(hash = 1381750250)
    public QuestionAndRemark(Integer id, Integer graduateProjectId, String question,
            String remark) {
        this.id = id;
        this.graduateProjectId = graduateProjectId;
        this.question = question;
        this.remark = remark;
    }

    @Generated(hash = 329059675)
    public QuestionAndRemark() {
    }

    public Integer getGraduateProjectId() {
        return graduateProjectId;
    }

    public void setGraduateProjectId(Integer graduateProjectId) {
        this.graduateProjectId = graduateProjectId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
