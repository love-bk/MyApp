package gjj.com.myapp.model;

import java.util.List;

/**
 * Created by yyz on 2017/5/7.
 */

public class ProjectAndReply {
    private List<GraduateProject> graduateProjectList;
    private List<ReplyGroup> replyGroups;

    public List<GraduateProject> getGraduateProjectList() {
        return graduateProjectList;
    }

    public void setGraduateProjectList(List<GraduateProject> graduateProjectList) {
        this.graduateProjectList = graduateProjectList;
    }

    public List<ReplyGroup> getReplyGroups() {
        return replyGroups;
    }

    public void setReplyGroups(List<ReplyGroup> replyGroups) {
        this.replyGroups = replyGroups;
    }

    @Override
    public String toString() {
        return "ProjectAndReply{" +
                "graduateProjectList=" + graduateProjectList +
                ", replyGroups=" + replyGroups +
                '}';
    }
}
