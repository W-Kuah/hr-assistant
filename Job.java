
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
    public void setCreatedAtID(Integer createdAtID) {
        this.createdAtID = createdAtID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setDegree(String degree) {
        this.degree = degree;
    }


    public void setSalary(Integer salary) {
        this.salary = salary;
    }


    public void setStartDate(String startDate) throws ParseException {
        SimpleDateFormat datePattern = new SimpleDateFormat("dd/MM/yy");
        datePattern.setLenient(false);
        this.startDate = datePattern.parse(startDate);
    }


    // Getter Instance variables
    public Integer getCreatedAtID() {
        return createdAtID;
    }


    public String getTitle() {
        return title;
    }


    public String getDescription() {
        return description;
    }


    public String getDegree() {
        return degree;
    }


    public Integer getSalary() {
        return salary;
    }

    public Date getStartDate() {
        return startDate;
    }



}
