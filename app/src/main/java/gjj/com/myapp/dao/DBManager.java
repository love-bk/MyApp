package gjj.com.myapp.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.database.Database;

import gjj.com.myapp.greendao.gen.DaoMaster;
import gjj.com.myapp.greendao.gen.DaoSession;
import gjj.com.myapp.greendao.gen.StudentDao;

/**
 * Created by yyz on 2017/5/3.
 */

public class DBManager {
    private final static String dbName = "bysj-db";
    private static DBManager mInstance;
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    public DBManager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
    }

    /**
     * 获取单例引用
     *
     * @param context
     * @return
     */
    public static DBManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager(context);
                }
            }
        }
        return mInstance;
    }

    public DaoSession getDaoSession() {
        db = openHelper.getWritableDatabase();
        if (mDaoMaster == null){
            mDaoMaster = new DaoMaster(db);
        }
        if (mDaoSession == null){
            mDaoSession = mDaoMaster.newSession();
        }
        return mDaoSession;
    }



}
