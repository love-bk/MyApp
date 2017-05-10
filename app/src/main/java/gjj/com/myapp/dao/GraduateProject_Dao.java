package gjj.com.myapp.dao;

import android.content.Context;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import gjj.com.myapp.greendao.gen.GraduateProjectDao;
import gjj.com.myapp.model.GraduateProject;

/**
 * Created by yyz on 2017/5/7.
 */

public class GraduateProject_Dao {

        private static GraduateProject_Dao instance;
        private static GraduateProjectDao graduateProjectDao;

        public GraduateProject_Dao(Context context) {
        }
        //使用了单例模式-->懒汉模式
        public static GraduateProject_Dao getInstance(Context context) {
            if (instance == null){
                //单利模式的线程安全问题
                synchronized (gjj.com.myapp.dao.Tutor_Dao.class){
                    instance = new GraduateProject_Dao(context);
                    graduateProjectDao = DBManager.getInstance(context).getDaoSession().getGraduateProjectDao();
                }
            }
            return instance;
        }

        /**
         * 插入多条条数据
         * @param projects
         */
        public void insertProjectList(List<GraduateProject> projects){
            if (projects != null){
                graduateProjectDao.deleteAll();
                graduateProjectDao.insertInTx(projects);
            }
        }

    /**
     * 插入1条条数据
     * @param project
     */
    public void insertProject(GraduateProject project){
        if (project != null){
            List<GraduateProject> projects = graduateProjectDao.queryBuilder().where(GraduateProjectDao.Properties.Id.eq(project.getId())).list();
            if (projects!=null&&projects.size()!=0){
                graduateProjectDao.delete(projects.get(0));
            }
            graduateProjectDao.insert(project);
        }
    }


        public GraduateProject queryProjectById(long id){
            List<GraduateProject> projects = graduateProjectDao.queryBuilder().where(GraduateProjectDao.Properties.Id.eq(id)).list();
            if (projects != null&&projects.size()>0) {
                return projects.get(0);
            }
            return null;
        }

    /**
     * 删除数据库中的数据
     */
    public void deleteAll(){
        graduateProjectDao.deleteAll();
    }

    public List<GraduateProject> queryProjectListByTutorId(Long tutorId) {
         return graduateProjectDao.queryBuilder().where(GraduateProjectDao.Properties.Tutor_id.eq(tutorId)).list();

    }
    public List<GraduateProject> queryProjectListByCategory(String category,long tutorId) {
        return  graduateProjectDao.queryBuilder().where(
                GraduateProjectDao.Properties.Tutor_id.eq(tutorId),
                GraduateProjectDao.Properties.Category.eq(category)).list();

    }
}
