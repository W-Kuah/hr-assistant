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
    public void setCreatedAtID(Integer createdAtID) {
        this.createdAtID = createdAtID;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public void setCareerSummary(String careerSummary) {
        this.careerSummary = careerSummary;
    }
    

    public void setAge(Integer age) {
        this.age = age;
    }


    public void setGender(String gender) {
        this.gender = gender;
    }


    public void setHighestDegree(String highestDegree) {
        this.highestDegree = highestDegree;
    }


    public void setCOMP90041(Integer COMP90041) {
        this.COMP90041 = COMP90041;
    }


    public void setCOMP90038(Integer COMP90038) {
        this.COMP90038 = COMP90038;
    }


    public void setCOMP90007(Integer COMP90007) {
        this.COMP90007 = COMP90007;
    }


    public void setINFO90002(Integer INFO90002) {
        this.INFO90002 = INFO90002;
    }


    public void setSalaryExpectations(Integer salaryExpectations) {
        this.salaryExpectations = salaryExpectations;
    }

    public void setAvailability(String availability) throws ParseException {
        SimpleDateFormat datePattern = new SimpleDateFormat("dd/MM/yy");
        datePattern.setLenient(false);
        this.availability = datePattern.parse(availability);
    }


    // Getter Applicant variables
    public Integer getCreatedAtID() {
        return createdAtID;
    }
 
    public String getLastName() {
        return lastName;
    }


    public String getFirstName() {
        return firstName;
    }


    public String getCareerSummary() {
        return careerSummary;
    }


    public Integer getAge() {
        return age;
    }


    public String getGender() {
        return gender;
    }


    public String getHighestDegree() {
        return highestDegree;
    }

    public Integer getCOMP90041() {
        return COMP90041;
    }


    public Integer getCOMP90038() {
        return COMP90038;
    }


    public Integer getCOMP90007() {
        return COMP90007;
    }



    public Integer getINFO90002() {
        return INFO90002;
    }


    public Integer getSalaryExpectations() {
        return salaryExpectations;
    }


    public Date getAvailability() {
        return availability;
    }



}
