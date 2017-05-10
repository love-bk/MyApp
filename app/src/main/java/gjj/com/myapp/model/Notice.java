package gjj.com.myapp.model;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Created by Administrator on 2016/1/21.
 */
@Entity
public class Notice {
    @Id(autoincrement = false)
    private Long id;
    private String title;                       //通知的题目
    private String content;                     //通知的内容
    private long addressTime;                   //通知的时间
    private Integer addressor_id;               //发送者的id
    private String addressor_name;              //发送者的姓名
    @Transient
    private List<Addressee> addressees;         //收件人,只有发送者是登陆用户时收件人才被赋值，如果发送者是不是登陆用户，那么收件人肯定是登陆用户


    @Generated(hash = 199123806)
    public Notice(Long id, String title, String content, long addressTime, Integer addressor_id,
            String addressor_name) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.addressTime = addressTime;
        this.addressor_id = addressor_id;
        this.addressor_name = addressor_name;
    }

    @Generated(hash = 1880392847)
    public Notice() {
    }


    public String getAddressor_name() {
        return addressor_name;
    }

    public void setAddressor_name(String addressor_name) {
        this.addressor_name = addressor_name;
    }

    public Integer getAddressor_id() {
        return addressor_id;
    }

    public void setAddressor_id(Integer addressor_id) {
        this.addressor_id = addressor_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getAddressTime() {
        return addressTime;
    }

    public void setAddressTime(long addressTime) {
        this.addressTime = addressTime;
    }

    public List<Addressee> getAddressees() {
        return addressees;
    }

    public void setAddressees(List<Addressee> addressees) {
        this.addressees = addressees;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }




    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
