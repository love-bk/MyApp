package gjj.com.myapp.dao;

import android.content.Context;

import java.util.List;

import gjj.com.myapp.MyApplication;
import gjj.com.myapp.baseframework.mvp.BaseView;
import gjj.com.myapp.greendao.gen.DaoSession;
import gjj.com.myapp.greendao.gen.TutorDao;
import gjj.com.myapp.model.Tutor;

/**
 * Created by yyz on 2017/5/3.
 */

public class Tutor_Dao {

    private static Tutor_Dao instance;
    private static TutorDao tutorDao;

    public Tutor_Dao(Context context) {
    }
    //使用了单例模式-->懒汉模式
    public static Tutor_Dao getInstance(Context context) {
        if (instance == null){
            //单利模式的线程安全问题
            synchronized (Tutor_Dao.class){
                instance = new Tutor_Dao(context);
                tutorDao = DBManager.getInstance(context).getDaoSession().getTutorDao();
            }
        }
        return instance;
    }

    /**
     * 插入一条数据
     * @param tutor
     */
    public void insert(Tutor tutor){
        if (tutor != null){
            List<Tutor> tutors = tutorDao.queryBuilder().where(TutorDao.Properties.Id.eq(tutor.getId())).list();
            if (tutors!=null &&tutors.size()!=0){
                tutorDao.delete(tutors.get(0));
            }
            tutorDao.insert(tutor);
        }
    }



    public Tutor queryByTutorId(long tutorId){
        List<Tutor> tutors = tutorDao.queryBuilder().where(TutorDao.Properties.Id.eq(tutorId)).list();
        if (tutors != null&&tutors.size()>0) {
            return tutors.get(0);
        }
        return null;
    }


    /**
     * 删除数据库中的数据
     */
    public void deleteAll(){
        tutorDao.deleteAll();
    }
}
