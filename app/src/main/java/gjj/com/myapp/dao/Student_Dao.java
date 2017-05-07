package gjj.com.myapp.dao;

import android.content.Context;

import java.util.List;

import gjj.com.myapp.MyApplication;
import gjj.com.myapp.greendao.gen.StudentDao;
import gjj.com.myapp.model.Student;

/**
 * Created by yyz on 2017/5/3.
 */

public class Student_Dao {
    private static Student_Dao instance;
    private static StudentDao studentDao;

    private Student_Dao() {
    }

    public static Student_Dao getInstance(Context context) {
        if (instance == null){
            //单利模式的线程安全问题
            synchronized (Tutor_Dao.class){
                instance = new Student_Dao();
                studentDao = DBManager.getInstance(context).getDaoSession().getStudentDao();
            }
        }
        return instance;
    }


    public void insert(List<Student> students){
        if (students != null && students.size() !=0){
            studentDao.deleteAll();
            studentDao.insertInTx(students);
        }
    }

    public List<Student> queryDatasByTutorId(long tutorId){
        return studentDao.queryBuilder().where(StudentDao.Properties.TutorId.eq(tutorId)).list();

    }
}
