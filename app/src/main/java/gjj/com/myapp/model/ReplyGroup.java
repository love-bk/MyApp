package gjj.com.myapp.model;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2016/1/21.
 * 答辩小组，与老师是多对一的关系。
 */
@Entity
public class ReplyGroup {
    @Id
    private Long id;
    private String description;                                    //答辩小组名称
    private String location;                                       //答辩的地点
    private Integer leader_id;                                     //答辩小组组长的id
    private String leader_name;                                     //答辩小组组长的id
    private Integer version;                                       //版本号
    private String start_time;                                     //答辩的开始时间
    private String end_time;                                       //答辩的结束时间
    private Integer serverId;                                      //服务器端的id


    @Generated(hash = 1175486910)
    public ReplyGroup(Long id, String description, String location,
            Integer leader_id, String leader_name, Integer version,
            String start_time, String end_time, Integer serverId) {
        this.id = id;
        this.description = description;
        this.location = location;
        this.leader_id = leader_id;
        this.leader_name = leader_name;
        this.version = version;
        this.start_time = start_time;
        this.end_time = end_time;
        this.serverId = serverId;
    }

    @Generated(hash = 21646655)
    public ReplyGroup() {
    }


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

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
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

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }





    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "ReplyGroup{" +
                "description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", leader_id=" + leader_id +
                ", leader_name='" + leader_name + '\'' +
                ", version=" + version +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", serverId=" + serverId +
                '}';
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
