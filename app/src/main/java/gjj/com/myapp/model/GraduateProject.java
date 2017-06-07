package gjj.com.myapp.model;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;



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
    private String category;              //题目类别
    private String projectType;           //题目类型
    private String projectFidelity;       //题目性质
    private String projectFrom;           //题目来源
    private String content;               //工作内容
    private String basicRequirement;      //基本要求
    private String basicSkill;            //基本技能
    private String reference;            //参考文献
    private String major;                 //所在专业
    private Integer completenessScoreByGroup;      //完成任务规定的要求与水平评分
    private Integer correctnessScoreByGroup;       //回答问题的正确性评分
    private Integer qualityScoreBtGroup;           //论文与实务的质量评分
    private Integer replyScoreByGroup;             //论文内容的答辩陈述评分
    private String remark;                         //指导老师的评语
    @Transient
    private Student student;                     //选该题目学生
    @Transient
    private Scores scores;
    private Long tutorId;                       //申报该题目老师的id
    private Long replyGroupId;                 //该课题所在的答辩小组
    private boolean auditByDirector;           //审核状态
    private int scoresState;                    //登陆用户对该学生的答辩打分

    private long userId;

    @Generated(hash = 144197301)
    public GraduateProject(Long id, String year, String title, String subTitle,
            String category, String projectType, String projectFidelity,
            String projectFrom, String content, String basicRequirement,
            String basicSkill, String reference, String major,
            Integer completenessScoreByGroup, Integer correctnessScoreByGroup,
            Integer qualityScoreBtGroup, Integer replyScoreByGroup, String remark,
            Long tutorId, Long replyGroupId, boolean auditByDirector,
            int scoresState, long userId) {
        this.id = id;
        this.year = year;
        this.title = title;
        this.subTitle = subTitle;
        this.category = category;
        this.projectType = projectType;
        this.projectFidelity = projectFidelity;
        this.projectFrom = projectFrom;
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
        this.tutorId = tutorId;
        this.replyGroupId = replyGroupId;
        this.auditByDirector = auditByDirector;
        this.scoresState = scoresState;
        this.userId = userId;
    }

    @Generated(hash = 1800086101)
    public GraduateProject() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Long getReplyGroupId() {
        return replyGroupId;
    }

    public void setReplyGroupId(Long replyGroupId) {
        this.replyGroupId = replyGroupId;
    }



    public int getScoresState() {
        return scoresState;
    }

    public void setScoresState(int scoresState) {
        this.scoresState = scoresState;
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

    public Long getTutorId() {
        return tutorId;
    }

    public void setTutorId(Long tutorId) {
        this.tutorId = tutorId;
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


    public boolean isAuditByDirector() {
        return auditByDirector;
    }

    public void setAuditByDirector(boolean auditByDirector) {
        this.auditByDirector = auditByDirector;
    }

    public boolean getAuditByDirector() {
        return this.auditByDirector;
    }

    public Integer getCompletenessScoreByGroup() {
        return completenessScoreByGroup;
    }

    public void setCompletenessScoreByGroup(Integer completenessScoreByGroup) {
        this.completenessScoreByGroup = completenessScoreByGroup;
    }

    public Integer getCorrectnessScoreByGroup() {
        return correctnessScoreByGroup;
    }

    public void setCorrectnessScoreByGroup(Integer correctnessScoreByGroup) {
        this.correctnessScoreByGroup = correctnessScoreByGroup;
    }

    public Integer getQualityScoreBtGroup() {
        return qualityScoreBtGroup;
    }

    public void setQualityScoreBtGroup(Integer qualityScoreBtGroup) {
        this.qualityScoreBtGroup = qualityScoreBtGroup;
    }

    public Integer getReplyScoreByGroup() {
        return replyScoreByGroup;
    }

    public void setReplyScoreByGroup(Integer replyScoreByGroup) {
        this.replyScoreByGroup = replyScoreByGroup;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Scores getScores() {
        return scores;
    }

    public void setScores(Scores scores) {
        this.scores = scores;
    }
}
