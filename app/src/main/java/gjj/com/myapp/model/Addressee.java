package gjj.com.myapp.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by GD on 2016/5/4.
 *该类用于提供发送收件人的功能。收件人类
 */
@Entity
public class Addressee {
    //该id是使用sqlite数据库所必须有的属性
    @Id(autoincrement = false)
    private Long id;
    //该联系人所对应发送通知的id
    private Long noticeId;
    //收件人的姓名
    private String addressee_name;
    //收件人有两类，分别是学生和老师。主要用于显示收件人列表的功能。
    private String addresseeType;
    //用于判断该收件人是否被选择
    private boolean isSelected;








    @Generated(hash = 1590162628)
    public Addressee(Long id, Long noticeId, String addressee_name,
            String addresseeType, boolean isSelected) {
        this.id = id;
        this.noticeId = noticeId;
        this.addressee_name = addressee_name;
        this.addresseeType = addresseeType;
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

    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    public String getAddressee_name() {
        return addressee_name;
    }

    public void setAddressee_name(String addressee_name) {
        this.addressee_name = addressee_name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
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
