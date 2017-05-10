package gjj.com.myapp.model;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/1/21.
 * 课题类，该课题类与老师是多对一的
 */
@Entity
public class GraduateProject {
    @Id(autoincrement = false)
    private Long id;
    private String year;
    private String title;                 //题目名称
    private String subTitle;              //副标题
    private String category;              //题目类别   不需要
    private String projectType;           //题目类型
    private String projectFidelity;       //题目性质
    private String projectFrom;           //题目来源
    private Long replyGroup_id;        //该课题所在的答辩小组
    private String content;               //工作内容
    private String basicRequirement;      //基本要求
    private String basicSkill;            //基本技能
    private String reference;            //参考文献
    private String major;                 //所在专业
    private Double completenessScoreByGroup;      //完成任务规定的要求与水平评分
    private Double correctnessScoreByGroup;       //回答问题的正确性评分
    private Double qualityScoreBtGroup;           //论文与实务的质量评分
    private Double replyScoreByGroup;             //论文内容的答辩陈述评分
    private String remark;                         //指导老师的评语
    private Long student_id;                     //选该题目学生的id
    @Transient
    private Student student_name;                     //选该题目学生的姓名
    private Long tutor_id;                      //申报该题目老师的id
    private boolean isAllow;                     //是否允许答辩 在commentByTutor中可以找到
    private boolean isSubmit;                    //是否提交 在commentByTutor中可以找到
    private boolean isSave;                      //是否保存 暂定
    private boolean auditByDirector;           //审核状态
    private String replyStation;





    @Generated(hash = 1800086101)
    public GraduateProject() {
    }









    @Generated(hash = 518391813)
    public GraduateProject(Long id, String year, String title, String subTitle,
            String category, String projectType, String projectFidelity,
            String projectFrom, Long replyGroup_id, String content,
            String basicRequirement, String basicSkill, String reference,
            String major, Double completenessScoreByGroup,
            Double correctnessScoreByGroup, Double qualityScoreBtGroup,
            Double replyScoreByGroup, String remark, Long student_id, Long tutor_id,
            boolean isAllow, boolean isSubmit, boolean isSave,
            boolean auditByDirector, String replyStation) {
        this.id = id;
        this.year = year;
        this.title = title;
        this.subTitle = subTitle;
        this.category = category;
        this.projectType = projectType;
        this.projectFidelity = projectFidelity;
        this.projectFrom = projectFrom;
        this.replyGroup_id = replyGroup_id;
        this.content = content;
        this.basicRequirement = basicRequirement;
        this.basicSkill = basicSkill;
        this.reference = reference;
        this.major = major;
        this.completenessScoreByGroup = completenessScoreByGroup;
        this.correctnessScoreByGroup = correctnessScoreByGroup;
        this.qualityScoreBtGroup = qualityScoreBtGroup;
        this.replyScoreByGroup = replyScoreByGroup;
        this.remark = remark;
        this.student_id = student_id;
        this.tutor_id = tutor_id;
        this.isAllow = isAllow;
        this.isSubmit = isSubmit;
        this.isSave = isSave;
        this.auditByDirector = auditByDirector;
        this.replyStation = replyStation;
    }









    public void setAllow(boolean allow) {
        isAllow = allow;
    }

    public void setSubmit(boolean submit) {
        isSubmit = submit;
    }

    public void setSave(boolean save) {
        isSave = save;
    }


    public String getReplyStation() {
        return replyStation;
    }

    public void setReplyStation(String replyStation) {
        this.replyStation = replyStation;
    }


    public String getBasicRequirement() {
        return basicRequirement;
    }

    public void setBasicRequirement(String basicRequirement) {
        this.basicRequirement = basicRequirement;
    }

    public String getBasicSkill() {
        return basicSkill;
    }

    public void setBasicSkill(String basicSkill) {
        this.basicSkill = basicSkill;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }




    public boolean isAllow() {
        return isAllow;
    }

    public void setIsAllow(boolean isAllow) {
        this.isAllow = isAllow;
    }



    public boolean isSave() {
        return isSave;
    }

    public void setIsSave(boolean isSave) {
        this.isSave = isSave;
    }

    public boolean isSubmit() {
        return isSubmit;
    }

    public void setIsSubmit(boolean isSubmit) {
        this.isSubmit = isSubmit;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getProjectFidelity() {
        return projectFidelity;
    }

    public void setProjectFidelity(String projectFidelity) {
        this.projectFidelity = projectFidelity;
    }

    public String getProjectFrom() {
        return projectFrom;
    }

    public void setProjectFrom(String projectFrom) {
        this.projectFrom = projectFrom;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }


    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }



    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getReplyGroup_id() {
        return replyGroup_id;
    }

    public void setReplyGroup_id(Long replyGroup_id) {
        this.replyGroup_id = replyGroup_id;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public Long getTutor_id() {
        return tutor_id;
    }

    public void setTutor_id(Long tutor_id) {
        this.tutor_id = tutor_id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getIsAllow() {
        return this.isAllow;
    }

    public boolean getIsSubmit() {
        return this.isSubmit;
    }

    public boolean getIsSave() {
        return this.isSave;
    }

    public Student getStudent_name() {
        return student_name;
    }

    public void setStudent_name(Student student_name) {
        this.student_name = student_name;
    }

    public boolean isAuditByDirector() {
        return auditByDirector;
    }

    public void setAuditByDirector(boolean auditByDirector) {
        this.auditByDirector = auditByDirector;
    }

    public boolean getAuditByDirector() {
        return this.auditByDirector;
    }

    public Double getCompletenessScoreByGroup() {
        return completenessScoreByGroup;
    }

    public void setCompletenessScoreByGroup(Double completenessScoreByGroup) {
        this.completenessScoreByGroup = completenessScoreByGroup;
    }

    public Double getCorrectnessScoreByGroup() {
        return correctnessScoreByGroup;
    }

    public void setCorrectnessScoreByGroup(Double correctnessScoreByGroup) {
        this.correctnessScoreByGroup = correctnessScoreByGroup;
    }

    public Double getQualityScoreBtGroup() {
        return qualityScoreBtGroup;
    }

    public void setQualityScoreBtGroup(Double qualityScoreBtGroup) {
        this.qualityScoreBtGroup = qualityScoreBtGroup;
    }

    public Double getReplyScoreByGroup() {
        return replyScoreByGroup;
    }

    public void setReplyScoreByGroup(Double replyScoreByGroup) {
        this.replyScoreByGroup = replyScoreByGroup;
    }
}
