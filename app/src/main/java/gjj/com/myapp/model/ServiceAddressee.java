package gjj.com.myapp.model;

import java.util.List;

/**
 * Created by 高娟娟 on 2017/5/14.
 */

public class ServiceAddressee {
    private List<Addressee> myStudent;
    private List<Addressee> allTutorInDepartment;

    public List<Addressee> getMyStudent() {
        return myStudent;
    }

    public void setMyStudent(List<Addressee> myStudent) {
        this.myStudent = myStudent;
    }

    public List<Addressee> getAllTutorInDepartment() {
        return allTutorInDepartment;
    }

    public void setAllTutorInDepartment(List<Addressee> allTutorInDepartment) {
        this.allTutorInDepartment = allTutorInDepartment;
    }
}
