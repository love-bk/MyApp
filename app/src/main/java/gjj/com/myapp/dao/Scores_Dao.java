package gjj.com.myapp.dao;

import android.content.Context;

import java.util.List;

import gjj.com.myapp.greendao.gen.DaoSession;
import gjj.com.myapp.greendao.gen.NoticeDao;
import gjj.com.myapp.greendao.gen.ScoresDao;
import gjj.com.myapp.model.Notice;
import gjj.com.myapp.model.Scores;

/**
 * Created by yyz on 2017/5/23.
 */

public class Scores_Dao {
    private static Scores_Dao instance;
    private static ScoresDao scoresDao;
    private static DaoSession daoSession;

    public Scores_Dao(Context context) {
    }
    //使用了单例模式-->懒汉模式
    public static Scores_Dao getInstance(Context context) {
        if (instance == null){
            //单利模式的线程安全问题
            synchronized (Tutor_Dao.class){
                instance = new Scores_Dao(context);
                daoSession = DBManager.getInstance(context).getDaoSession();
                scoresDao = daoSession.getScoresDao();
            }
        }
        return instance;
    }

    /**
     * 插入一条数据
     * @param scores
     */
    public void insert(Scores scores){
        if (scores != null){
            List<Scores> scoresList = scoresDao.queryBuilder().where(ScoresDao.Properties.ProjectId.eq(scores.getProjectId())).list();
            if (scoresList != null && scoresList.size()!=0){
                scoresDao.delete(scoresList.get(0));
            }
            scoresDao.insert(scores);
        }
    }


    public Scores queryDatasByProjectId(long projectId){

        List<Scores> scores = scoresDao.queryBuilder().where(ScoresDao.Properties.ProjectId.eq(projectId)).list();
        if (scores!=null && scores.size()!=0){
            return scores.get(0);
        }
        return null;

    }

    /**
     * 删除数据库中的数据
     */
    public void deleteAllData(){
        scoresDao.deleteAll();
    }
}
