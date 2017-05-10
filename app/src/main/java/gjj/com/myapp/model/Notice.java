package gjj.com.myapp.model;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2016/1/21.
 */
@Entity
public class Notice {
    @Id(autoincrement = false)
    private Long id;
    private String title;                          //通知的题目
    private String content;                   //通知的内容
    private String noticeDate;                    //通知的时间
    private  Integer version;                     //版本号
    private Integer addressor_id;                     //发送通知的id
    private String addressor_name;                     //发送通知的姓名



    @Generated(hash = 1106525684)
    public Notice(Long id, String title, String content, String noticeDate,
            Integer version, Integer addressor_id, String addressor_name) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.noticeDate = noticeDate;
        this.version = version;
        this.addressor_id = addressor_id;
        this.addressor_name = addressor_name;
    }

    @Generated(hash = 1880392847)
    public Notice() {
    }



//    private List<Integer> addressee_id;   //该属性用于发送通知，一个通知可以对应多个收件人

//    public List<Integer> getAddressee_id() {
//        return addressee_id;
//    }
//
//    public void setAddressee_id(List<Integer> addressee_id) {
//        this.addressee_id = addressee_id;
//    }

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

    public String getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(String noticeDate) {
        this.noticeDate = noticeDate;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }



    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
