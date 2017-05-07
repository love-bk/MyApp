package gjj.com.myapp.model;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Created by Administrator on 2016/1/21.
 * 答辩小组，与老师是多对一的关系。
 */
@Entity
public class ReplyGroup {
    @Id(autoincrement = false)
    private Long id;
    private String description;                                    //答辩小组名称
    private String location;                                       //答辩的地点
    private Integer leader_id;                                     //答辩小组组长的id
    private String leader_name;                                     //答辩小组组长的id
    @Transient
    private ReplyTime replyTime;
    private long beginTime;                                     //答辩的开始时间
    private long endTime;                                       //答辩的结束时间





    public String getLeader_name() {
        return leader_name;
    }

    public void setLeader_name(String leader_name) {
        this.leader_name = leader_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Integer getLeader_id() {
        return leader_id;
    }

    public void setLeader_id(Integer leader_id) {
        this.leader_id = leader_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public ReplyTime getReplyTime() {
        return replyTime;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public void setReplyTime(ReplyTime replyTime) {
        this.replyTime = replyTime;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private class ReplyTime {
        private long beginTime;                                     //答辩的开始时间
        private long endTime;                                       //答辩的结束时间


        public long getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(long beginTime) {
            this.beginTime = beginTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }
    }
}
