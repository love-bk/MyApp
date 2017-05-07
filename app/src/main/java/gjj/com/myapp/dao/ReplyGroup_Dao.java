package gjj.com.myapp.dao;

import android.content.Context;

import java.util.List;

import gjj.com.myapp.greendao.gen.ReplyGroupDao;
import gjj.com.myapp.model.ReplyGroup;

/**
 * Created by yyz on 2017/5/7.
 */

public class ReplyGroup_Dao {
    private static ReplyGroup_Dao instance;
    private static ReplyGroupDao replyGroupDao;

    public ReplyGroup_Dao(Context context) {
    }
    //使用了单例模式-->懒汉模式
    public static ReplyGroup_Dao getInstance(Context context) {
        if (instance == null){
            //单利模式的线程安全问题
            synchronized (gjj.com.myapp.dao.Tutor_Dao.class){
                instance = new ReplyGroup_Dao(context);
                replyGroupDao = DBManager.getInstance(context).getDaoSession().getReplyGroupDao();
            }
        }
        return instance;
    }

    /**
     * 插入多条条数据
     * @param replyGroups
     */
    public void insert(List<ReplyGroup> replyGroups){
        if (replyGroups != null){
            replyGroupDao.deleteAll();
            replyGroupDao.insertInTx(replyGroups);
        }
    }


    public ReplyGroup queryProjectById(long id){
        List<ReplyGroup> replyGroups = replyGroupDao.queryBuilder().where(ReplyGroupDao.Properties.Id.eq(id)).list();
        if (replyGroups != null&&replyGroups.size()>0) {
            return replyGroups.get(0);
        }
        return null;
    }
}
