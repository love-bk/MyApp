package gjj.com.myapp.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by GD on 2016/5/4.
 *该类用于提供发送收件人的功能。
 */
@Entity
public class Addressee {
    //该id是使用sqlite数据库所必须有的属性
    @Id
    private Long id;
    //该联系人所对应发送通知的id
    private Integer noticeId;
    //用于存储从服务器上获取下来的id
    private Integer serverId;
    //收件人的姓名
    private String addressee_name;
    //收件人有两类，分别是学生和老师。主要用于显示收件人列表的功能。
    private String addresseeType;
    //好像没用到，待定
    private Integer version;
    //用于判断该收件人是否被选择
    private boolean isSelected;




    @Generated(hash = 192603945)
    public Addressee(Long id, Integer noticeId, Integer serverId,
            String addressee_name, String addresseeType, Integer version,
            boolean isSelected) {
        this.id = id;
        this.noticeId = noticeId;
        this.serverId = serverId;
        this.addressee_name = addressee_name;
        this.addresseeType = addresseeType;
        this.version = version;
        this.isSelected = isSelected;
    }

    @Generated(hash = 1999156007)
    public Addressee() {
    }




    public String getAddresseeType() {
        return addresseeType;
    }

    public void setAddresseeType(String addresseeType) {
        this.addresseeType = addresseeType;
    }

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    public String getAddressee_name() {
        return addressee_name;
    }

    public void setAddressee_name(String addressee_name) {
        this.addressee_name = addressee_name;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public String toString() {
        return "Addressee{" +
                "id=" + id +
                ", noticeId=" + noticeId +
                ", serverId=" + serverId +
                ", addressee_name='" + addressee_name + '\'' +
                ", addresseeType='" + addresseeType + '\'' +
                ", version=" + version +
                '}';
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getIsSelected() {
        return this.isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
