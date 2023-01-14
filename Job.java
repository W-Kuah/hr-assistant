
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;


public class Job {
    
    // Instantiate Job variables
    private Integer createdAtID;
    private String title;
    private String description;
    private String degree;
    private Integer salary;
    private Date startDate;

    // Constructor for Job class
    /**
    * @param
    * @return 
    * @throws
    **/
    public Job() {
        super();
        this.createdAtID = null;
        this.title = null;
        this.description = null;
        this.degree = null;
        this.salary = null;
        this.startDate = null;
    }


    // Setter Instance variables
    /**
    * @param Integer createdAtID
    * @return void
    * @throws
    **/
    public void setCreatedAtID(Integer createdAtID) {
        this.createdAtID = createdAtID;
    }
    /**
    * @param String title
    * @return void
    * @throws
    **/
    public void setTitle(String title) {
        this.title = title;
    }

    /**
    * @param String description
    * @return void
    * @throws
    **/
    public void setDescription(String description) {
        this.description = description;
    }

    /**
    * @param String degree
    * @return void
    * @throws
    **/
    public void setDegree(String degree) {
        this.degree = degree;
    }

    /**
    * @param Integer salary
    * @return void
    * @throws
    **/
    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    /**
    * @param String startDate
    * @return void
    * @throws ParseException
    **/
    public void setStartDate(String startDate) throws ParseException {
        SimpleDateFormat datePattern = new SimpleDateFormat("dd/MM/yy");
        datePattern.setLenient(false);
        this.startDate = datePattern.parse(startDate);
    }


    // Getter Instance variables
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
    * @return String title
    * @throws
    **/
    public String getTitle() {
        return title;
    }

    /**
    * @param 
    * @return String description
    * @throws
    **/
    public String getDescription() {
        return description;
    }

    /**
    * @param 
    * @return String degree
    * @throws
    **/
    public String getDegree() {
        return degree;
    }

    /**
    * @param 
    * @return Integer salary
    * @throws
    **/
    public Integer getSalary() {
        return salary;
    }

    /**
    * @param 
    * @return Date startDate
    * @throws
    **/
    public Date getStartDate() {
        return startDate;
    }



}
