package gjj.com.myapp.dao;

import android.content.Context;

import java.util.List;

import gjj.com.myapp.greendao.gen.NoticeDao;
import gjj.com.myapp.model.Notice;

/**
 * Created by yyz on 2017/5/10.
 */

public class Notice_Dao {

    private static Notice_Dao instance;
    private static NoticeDao noticeDao;

    public Notice_Dao(Context context) {
    }
    //使用了单例模式-->懒汉模式
    public static Notice_Dao getInstance(Context context) {
        if (instance == null){
            //单利模式的线程安全问题
            synchronized (Tutor_Dao.class){
                instance = new Notice_Dao(context);
                noticeDao = DBManager.getInstance(context).getDaoSession().getNoticeDao();
            }
        }
        return instance;
    }

    /**
     * 插入一条数据
     * @param addressee
     */
    public void insert(Notice addressee){
        if (addressee != null){
            noticeDao.deleteAll();
            noticeDao.insert(addressee);
        }
    }


    /**
     * 插入多条条数据
     * @param notices
     */
    public void insertNotices(List<Notice> notices){
        if (notices != null){
            noticeDao.deleteAll();
            noticeDao.insertInTx(notices);
        }
    }


    public Notice queryNoticeById(long id){
        List<Notice> notices = noticeDao.queryBuilder().where(NoticeDao.Properties.Id.eq(id)).list();
        if (notices != null&&notices.size()>0) {
            return notices.get(0);
        }
        return null;
    }

    public void deleteAllData(){
        noticeDao.deleteAll();
    }
}
