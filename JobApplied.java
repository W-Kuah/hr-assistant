import java.util.*;

public class JobApplied {

    private Integer JobID;
    private List<Integer> applicantList;


    public JobApplied(Integer JobID) {
        this.JobID = JobID;
        this.applicantList = new ArrayList<Integer>();
    }


    public void addApplicant(Integer applicantNum) {
        List<Integer> temp = new ArrayList<Integer>();
        for (Integer applicant : this.applicantList) {
            temp.add(applicant);
        }
        temp.add(applicantNum);
        this.applicantList.clear();
        for (Integer newApplicant : temp) {
            this.applicantList.add(newApplicant);
        }
    }

    public boolean applicantExist(Integer applicantNum) {
        for (Integer ID : getApplicantList()) {
            if (applicantNum.equals(ID)) {
                return true;
            }
        }
        return false;
    }

    // Getter variables for Job Applied
    public Integer getJobID() {
        return JobID;
    }

    public List<Integer> getApplicantList() {
        return applicantList;
    }
}
