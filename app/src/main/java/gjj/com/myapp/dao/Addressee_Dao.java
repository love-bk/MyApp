package gjj.com.myapp.dao;

import android.content.Context;

import java.util.List;

import gjj.com.myapp.greendao.gen.AddresseeDao;
import gjj.com.myapp.model.Addressee;
import gjj.com.myapp.model.Tutor;

/**
 * Created by yyz on 2017/5/7.
 */

public class Addressee_Dao {
    private static Addressee_Dao instance;
    private static AddresseeDao addresseeDao;

    public Addressee_Dao(Context context) {
    }
    //使用了单例模式-->懒汉模式
    public static Addressee_Dao getInstance(Context context) {
        if (instance == null){
            //单利模式的线程安全问题
            synchronized (Tutor_Dao.class){
                instance = new Addressee_Dao(context);
                addresseeDao = DBManager.getInstance(context).getDaoSession().getAddresseeDao();
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
            addresseeDao.deleteAll();
            addresseeDao.insert(addressee);
        }
    }


    /**
     * 插入多条条数据
     * @param addressees
     */
    public void insertAddressees(List<Addressee>  addressees){
        if (addressees != null){
            addresseeDao.deleteAll();
            addresseeDao.insertInTx(addressees);
        }
    }

    public Addressee queryAddresseeById(long id){
        List<Addressee> addressees = addresseeDao.queryBuilder().where(AddresseeDao.Properties.Id.eq(id)).list();
        if (addressees != null&&addressees.size()>0) {
            return addressees.get(0);
        }
        return null;
    }
}
