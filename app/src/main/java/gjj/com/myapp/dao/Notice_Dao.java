package gjj.com.myapp.dao;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import gjj.com.myapp.greendao.gen.NoticeDao;
import gjj.com.myapp.model.Notice;
import gjj.com.myapp.utils.Constants;
import gjj.com.myapp.utils.SPUtil;

/**
 * Created by yyz on 2017/5/10.
 */

public class Notice_Dao {

    private static Notice_Dao instance;
    private static NoticeDao noticeDao;
    private static Context mContext;
    public Notice_Dao(Context context) {
    }
    //使用了单例模式-->懒汉模式
    public static Notice_Dao getInstance(Context context) {
        if (instance == null){
            //单利模式的线程安全问题
            synchronized (Tutor_Dao.class){
                mContext = context;
                instance = new Notice_Dao(context);
                noticeDao = DBManager.getInstance(context).getDaoSession().getNoticeDao();
            }
        }
        return instance;
    }

    /**
     * 插入一条数据
     * @param notice
     */
    public void insert(Notice notice){
        if (notice != null){
            List<Notice> notices = noticeDao.queryBuilder().where(NoticeDao.Properties.Id.eq(notice.getId())).list();
            if (notices != null&&notices.size()>0) {
                noticeDao.delete(notices.get(0));
            }
            noticeDao.insert(notice);
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


    public List<Notice> queryNoticesByFlag(String flag) {
        List<Notice> notices = new ArrayList<>();
        switch (flag){
            case Constants.FORWARD_MESSAGE:
                notices = noticeDao.queryBuilder().where(NoticeDao.Properties.Addressor_id.
                        eq(SPUtil.getTutorIdfromSP(mContext))).list();
                break;
            case Constants.GET_MESSAGE:
                notices = noticeDao.queryBuilder().where(NoticeDao.Properties.Addressor_id.
                notIn(SPUtil.getTutorIdfromSP(mContext))).list();
                break;
        }

        return notices;
    }

    public void insertNoticesByFlag(List<Notice> notices, String flag) {
        List<Notice> deleteNotices = new ArrayList<>();
        if (notices != null){
            switch (flag){
                case Constants.FORWARD_MESSAGE:
                    deleteNotices = noticeDao.queryBuilder().where(NoticeDao.Properties.Addressor_id.
                            eq(SPUtil.getTutorIdfromSP(mContext))).list();
                    break;
                case Constants.GET_MESSAGE:
                    deleteNotices = noticeDao.queryBuilder().where(NoticeDao.Properties.Addressor_id.
                            notIn(SPUtil.getTutorIdfromSP(mContext))).list();
                    break;
            }
            if (deleteNotices.size()>0){
                noticeDao.deleteInTx(deleteNotices);
            }
            noticeDao.insertInTx(notices);
        }
    }
}
