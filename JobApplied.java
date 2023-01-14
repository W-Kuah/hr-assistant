import java.util.*;

public class JobApplied {

    private Integer JobID;
    private List<Integer> applicantList;

    /**
    * @param Integer JobID
    * @return 
    * @throws
    **/
    public JobApplied(Integer JobID) {
        this.JobID = JobID;
        this.applicantList = new ArrayList<Integer>();
    }

    /**
      * @param Integer applicantNum
      * @return void
      * @throws
    **/
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
    /**
      * @param Integer applicantNum
      * @return boolean
      * @throws
      **/
    public boolean applicantExist(Integer applicantNum) {
        for (Integer ID : applicantList) {
            if (applicantNum.equals(ID)) {
                return true;
            }
        }
        return false;
    }

    // Getter variables for Job Applied
    /**
      * @param
      * @return Integer JobID
      * @throws
      **/
    public Integer getJobID() {
        return JobID;
    }
    /**
      * @param
      * @return List<Integer> getApplicantList
      * @throws
      **/
    public List<Integer> getApplicantList() {
        return applicantList;
    }
}
