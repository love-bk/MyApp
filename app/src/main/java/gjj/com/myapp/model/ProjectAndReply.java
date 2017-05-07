package gjj.com.myapp.model;

import java.util.List;

/**
 * Created by yyz on 2017/5/7.
 */

public class ProjectAndReply {
    private List<GraduateProject> proposerGraduateProject;
    private List<ReplyGroup> replyGroups;

    public List<GraduateProject> getProposerGraduateProject() {
        return proposerGraduateProject;
    }

    public void setProposerGraduateProject(List<GraduateProject> proposerGraduateProject) {
        this.proposerGraduateProject = proposerGraduateProject;
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
                "proposerGraduateProject=" + proposerGraduateProject +
                ", replyGroups=" + replyGroups +
                '}';
    }
}
