package gjj.com.myapp.dao;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import gjj.com.myapp.greendao.gen.AddresseeDao;
import gjj.com.myapp.greendao.gen.DaoSession;
import gjj.com.myapp.model.Addressee;
import gjj.com.myapp.model.Tutor;

/**
 * Created by yyz on 2017/5/7.
 */

public class Addressee_Dao {
    private static Addressee_Dao instance;
    private static AddresseeDao addresseeDao;
    private static final String SQL_DISTINCT_ENAME = "SELECT DISTINCT "+AddresseeDao.Properties.Id.columnName+" FROM "+AddresseeDao.TABLENAME;
    private static DaoSession daoSession;

    public Addressee_Dao(Context context) {
    }
    //使用了单例模式-->懒汉模式
    public static Addressee_Dao getInstance(Context context) {
        if (instance == null){
            //单利模式的线程安全问题
            synchronized (Tutor_Dao.class){
                instance = new Addressee_Dao(context);
                daoSession = DBManager.getInstance(context).getDaoSession();
                addresseeDao = daoSession.getAddresseeDao();
            }
        }
        return instance;
    }

    /**
     * 插入一条数据
     * @param addressee
     */
    public void insert(Addressee addressee){
        if (addressee != null){
            List<Addressee> addressees = new ArrayList<>();
            if (addressee.getNoticeId() != null){
                addressees = addresseeDao.queryBuilder().where(
                        AddresseeDao.Properties.Id.eq(addressee.getId()),
                        AddresseeDao.Properties.NoticeId.eq(addressee.getNoticeId()))
                        .list();
            }else {
                addressees = addresseeDao.queryBuilder().where(
                        AddresseeDao.Properties.Id.eq(addressee.getId()))
                        .list();
            }

            if (addressees!=null&&addressees.size()!=0){
                addresseeDao.delete(addressees.get(0));
            }
            addresseeDao.insert(addressee);
        }
    }


//    /**
//     * 插入多条条数据
//     * @param addressees
//     */
//    public void insertAddressees(List<Addressee>  addressees){
//        if (addressees != null){
//            addresseeDao.deleteAll();
//            addresseeDao.insertInTx(addressees);
//        }
//    }

    /**
     * 获取所有接收者
     * @return
     */
    public List<Addressee> getAllAddressees(){
        List<Addressee> addressees = new ArrayList<>();
        List<Long> allAddresseeIds = getAllAddresseeIds();
        if (allAddresseeIds != null && allAddresseeIds.size() != 0){
            for (Long allAddresseeId : allAddresseeIds) {
                addressees.add(addresseeDao.queryBuilder().where(AddresseeDao.Properties.Id.eq(allAddresseeId)).list().get(0));
            }
        }
        return addressees;
    }

    /**
     * 获取通知数据,去除掉id重复的
     */
    public List<Long> getAllAddresseeIds(){
        List<Long> result = new ArrayList<Long>();
        Cursor c = daoSession.getDatabase().rawQuery(SQL_DISTINCT_ENAME, null);
        try{
            if (c.moveToFirst()) {
                do {
                    result.add(c.getLong(0));
                } while (c.moveToNext());
            }
        } finally {
            c.close();
        }
        return result;
    }
    /**
     * 删除数据库中的数据
     */
    public void deleteAll(){
        addresseeDao.deleteAll();
    }

    public Addressee queryAddresseeById(long id){
        List<Addressee> addressees = addresseeDao.queryBuilder().where(AddresseeDao.Properties.Id.eq(id)).list();
        if (addressees != null&&addressees.size()>0) {
            return addressees.get(0);
        }
        return null;
    }

    public List<Addressee> queryAddresseeByNoticeId(long noticeId) {
       return addresseeDao.queryBuilder().where(AddresseeDao.Properties.NoticeId.eq(noticeId)).list();

    }
}
