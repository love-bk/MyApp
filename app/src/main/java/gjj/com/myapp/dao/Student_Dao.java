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


    public void insertStudentList(List<Student> students){
        if (students != null && students.size() !=0){
            studentDao.deleteAll();
            studentDao.insertInTx(students);
        }
    }

    public void insertStudent(Student student){
        List<Student> students = studentDao.queryBuilder().where(StudentDao.Properties.Id.eq(student.getId())).list();
        if (students!=null && students.size()!=0){
            Long replyGroup_id = students.get(0).getReplyGroupId();
            if (replyGroup_id != null){
                student.setReplyGroupId(replyGroup_id);
            }
            studentDao.delete(students.get(0));
        }
        studentDao.insert(student);
    }
    public List<Student> queryDatasByTutorId(long tutorId){
        return studentDao.queryBuilder().where(StudentDao.Properties.TutorId.eq(tutorId)).list();

    }
     public Student queryDatasByProjectId(long projectId){

         List<Student> students = studentDao.queryBuilder().where(StudentDao.Properties.ProjectId.eq(projectId)).list();
         if (students!=null && students.size()!=0){
             return students.get(0);
         }
         return null;

    }

    /**
     * 删除数据库中的数据
     */
    public void deleteAllData(){
        studentDao.deleteAll();
    }
}
