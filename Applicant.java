import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;



public class Applicant {

    // Instantiate Applicant variables
    private Integer createdAtID;
    private String lastName;
    private String firstName;
    private String careerSummary;
    private Integer age;
    private String gender;
    private String highestDegree;
    private Integer COMP90041;
    private Integer COMP90038;
    private Integer COMP90007;
    private Integer INFO90002;
    private Integer salaryExpectations;
    private Date availability;

    /**
    * @param 
    * @return 
    * @throws
    **/
    public Applicant() {
        super();
        this.createdAtID = null;
        this.lastName = null;
        this.firstName = null;
        this.careerSummary = null;
        this.age = null;
        this.gender = null;
        this.highestDegree = null;
        this.COMP90041 = null;
        this.COMP90038 = null;
        this.INFO90002 = null;
        this.salaryExpectations = null;
        this.availability = null;
    }



    // Setter Applicant variables
    /**
      * @param Integer createdAtID
      * @return void
      * @throws
      **/
    public void setCreatedAtID(Integer createdAtID) {
        this.createdAtID = createdAtID;
    }

    /**
      * @param String lastName
      * @return void
      * @throws
      **/
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
      * @param String firstName
      * @return void
      * @throws
      **/
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
      * @param String careerSummary
      * @return void
      * @throws
      **/
    public void setCareerSummary(String careerSummary) {
        this.careerSummary = careerSummary;
    }
    
    /**
      * @param Integer age
      * @return void
      * @throws
      **/
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
      * @param String gender
      * @return void
      * @throws
      **/
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
      * @param String highestDegree
      * @return void
      * @throws
      **/
    public void setHighestDegree(String highestDegree) {
        this.highestDegree = highestDegree;
    }

    /**
      * @param Integer COMP90041
      * @return void
      * @throws
      **/
    public void setCOMP90041(Integer COMP90041) {
        this.COMP90041 = COMP90041;
    }

    /**
      * @param Integer COMP90038
      * @return void
      * @throws
      **/
    public void setCOMP90038(Integer COMP90038) {
        this.COMP90038 = COMP90038;
    }

    /**
      * @param Integer COMP90007
      * @return void
      * @throws
      **/
    public void setCOMP90007(Integer COMP90007) {
        this.COMP90007 = COMP90007;
    }

    /**
      * @param Integer INFO90002
      * @return void
      * @throws
      **/
    public void setINFO90002(Integer INFO90002) {
        this.INFO90002 = INFO90002;
    }

     /**
      * @param Integer salaryExpectations
      * @return void
      * @throws
      **/
    public void setSalaryExpectations(Integer salaryExpectations) {
        this.salaryExpectations = salaryExpectations;
    }

    /**
      * @param String availability
      * @return void
      * @throws ParseException
      **/
    public void setAvailability(String availability) throws ParseException {
        SimpleDateFormat datePattern = new SimpleDateFormat("dd/MM/yy");
        datePattern.setLenient(false);
        this.availability = datePattern.parse(availability);
    }


    // Getter Applicant variables
    /**
      * @param
      * @return Integer createdAtID
      * @throws
      **/
    public Integer getCreatedAtID() {
        return createdAtID;
    }
    /**
      * @param
      * @return String lastName
      * @throws
      **/
    public String getLastName() {
        return lastName;
    }

    /**
      * @param
      * @return String firstName
      * @throws
      **/
    public String getFirstName() {
        return firstName;
    }

    /**
      * @param
      * @return String careerSummary
      * @throws
      **/
    public String getCareerSummary() {
        return careerSummary;
    }

    /**
      * @param
      * @return Integer age
      * @throws
      **/
    public Integer getAge() {
        return age;
    }

    /**
      * @param
      * @return String gender
      * @throws
      **/
    public String getGender() {
        return gender;
    }

    /**
      * @param
      * @return String highestDegree
      * @throws
      **/
    public String getHighestDegree() {
        return highestDegree;
    }

    /**
      * @param
      * @return Integer COMP90041
      * @throws
      **/
    public Integer getCOMP90041() {
        return COMP90041;
    }

    /**
      * @param
      * @return Integer COMP90038
      * @throws
      **/
    public Integer getCOMP90038() {
        return COMP90038;
    }

    /**
      * @param
      * @return Integer COMP90007
      * @throws
      **/
    public Integer getCOMP90007() {
        return COMP90007;
    }


    /**
      * @param
      * @return Integer INFO90002
      * @throws
      **/
    public Integer getINFO90002() {
        return INFO90002;
    }

    /**
      * @param
      * @return Integer salaryExpectations
      * @throws
      **/
    public Integer getSalaryExpectations() {
        return salaryExpectations;
    }

    /**
      * @param
      * @return Integer availability
      * @throws
      **/
    public Date getAvailability() {
        return availability;
    }



}
