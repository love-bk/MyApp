package gjj.com.myapp.model;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ${高娟娟} on 2016/4/30.
 * 答辩教师评分表，用于保存老师给学生打的分数
 */
@Entity
public class Scores {
    @Id(autoincrement = false)
    private Long id;
    //学生的id
    private Integer studentId;
    //学生导师的id
    private Integer tutor_id;
    //学生课题的id
    private Integer graduateProjectId;
    private Integer completenessScoreByGroup;      //完成任务规定的要求与水平评分
    private Integer correctnessScoreByGroup;       //回答问题的正确性评分
    private Integer qualityScoreByGroup;           //论文与实务的质量评分
    private Integer replyScoreByGroup;             //论文内容的答辩陈述评分
    private int version;                        //每次修改分数时改变
    private String scoresState;              //答辩打分的状态
    private String remarkByGroupTutor;       //答辩老师的评语
    private Integer replyGroup_id;

    @Generated(hash = 324944342)
    public Scores(Long id, Integer studentId, Integer tutor_id,
            Integer graduateProjectId, Integer completenessScoreByGroup,
            Integer correctnessScoreByGroup, Integer qualityScoreByGroup,
            Integer replyScoreByGroup, int version, String scoresState,
            String remarkByGroupTutor, Integer replyGroup_id) {
        this.id = id;
        this.studentId = studentId;
        this.tutor_id = tutor_id;
        this.graduateProjectId = graduateProjectId;
        this.completenessScoreByGroup = completenessScoreByGroup;
        this.correctnessScoreByGroup = correctnessScoreByGroup;
        this.qualityScoreByGroup = qualityScoreByGroup;
        this.replyScoreByGroup = replyScoreByGroup;
        this.version = version;
        this.scoresState = scoresState;
        this.remarkByGroupTutor = remarkByGroupTutor;
        this.replyGroup_id = replyGroup_id;
    }

    @Generated(hash = 1245187606)
    public Scores() {
    }

    public Integer getReplyGroup_id() {
        return replyGroup_id;
    }

    @Override
    public String toString() {
        return "Scores{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", tutor_id=" + tutor_id +
                ", graduateProjectId=" + graduateProjectId +
                ", completenessScoreByGroup=" + completenessScoreByGroup +
                ", correctnessScoreByGroup=" + correctnessScoreByGroup +
                ", qualityScoreByGroup=" + qualityScoreByGroup +
                ", replyScoreByGroup=" + replyScoreByGroup +
                ", version=" + version +
                ", scoresState='" + scoresState + '\'' +
                ", remarkByGroupTutor='" + remarkByGroupTutor + '\'' +
                ", replyGroup_id=" + replyGroup_id +
                '}';
    }

    public void setReplyGroup_id(Integer replyGroup_id) {
        this.replyGroup_id = replyGroup_id;
    }

    public String getRemarkByGroupTutor() {
        return remarkByGroupTutor;
    }

    public void setRemarkByGroupTutor(String remarkByGroupTutor) {
        this.remarkByGroupTutor = remarkByGroupTutor;
    }

    public Integer getTutor_id() {
        return tutor_id;
    }

    public void setTutor_id(Integer tutor_id) {
        this.tutor_id = tutor_id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getGraduateProjectId() {
        return graduateProjectId;
    }

    public void setGraduateProjectId(Integer graduateProjectId) {
        this.graduateProjectId = graduateProjectId;
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

    public Integer getQualityScoreByGroup() {
        return qualityScoreByGroup;
    }

    public void setQualityScoreByGroup(Integer qualityScoreByGroup) {
        this.qualityScoreByGroup = qualityScoreByGroup;
    }

    public Integer getReplyScoreByGroup() {
        return replyScoreByGroup;
    }

    public void setReplyScoreByGroup(Integer replyScoreByGroup) {
        this.replyScoreByGroup = replyScoreByGroup;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getScoresState() {
        return scoresState;
    }

    public void setScoresState(String scoresState) {
        this.scoresState = scoresState;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
