import java.io.*;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

abstract class Instance {
    
    // Intialise Instance global values
    private static final String defaultJobListPath = "jobs.csv";
    private static final String defaultApplicantListPath = "applications.csv";
    private static final String jobAppliedListPath = "jobApplied.csv";
    private static Pattern DATE_PATTERN = Pattern.compile("^\\d{2}/\\d{2}/\\d{2}$");


    // Instantiate Instance variables
    private String jobListPath;
    private String applicantListPath;
    private List<Job> jobList;
    private List<Applicant> applicantList;
    private List<JobApplied> jobAppliedList;
    

    // Constructors for Instance
    /**
    * @param  jobListPath, applicantListPath
    * @return 
    * @throws IOException, ParseException
    **/
    public Instance(String jobListPath, String applicantListPath) throws IOException, ParseException {
        if (applicantListPath == null) {
            this.applicantListPath = defaultApplicantListPath;
        } else {
            this.applicantListPath = applicantListPath;
        }
        File applicantListFile = returnFile(this.applicantListPath);
        readApplicantFile(applicantListFile);
        
        if (jobListPath == null) {
            this.jobListPath = defaultJobListPath;
        } else {
            this.jobListPath = jobListPath;
        }
        File jobListFile = returnFile(this.jobListPath);
        this.jobList = new ArrayList<Job>();
        readJobFile(jobListFile);


        File jobAppliedFile = returnFile(jobAppliedListPath);
        readJobAppliedFile(jobAppliedFile);
        
    }
    

    // Method to check file exist. If file doesn't exist, create a new file. 
    // If file exists, simply return file.
    /**
      * @param String Path
      * @return File file
      * @throws IOException
      **/
    public File returnFile(String path) throws IOException {
        File file = new File(path);
        
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }
    // Method to transform the applicant file into a list of applicant objects. 
    // Throws errors, skip rows, and skip fields when applicable.
    /**
      * @param (File file
      * @return void
      * @throws IOException, ParseException
      **/
    public void readJobFile(File file) throws IOException, ParseException {
        jobList = new ArrayList<Job>();
        String row;
        int lineCount;
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        bufferedReader.readLine();
        row = bufferedReader.readLine();
        lineCount = 2;
        while (row != null && !row.equals("")){
            String[] rowList = row.split("");
            List<String> parts = new ArrayList<String>();
            boolean displayIDE = false;
            boolean displayNFE = false;
            boolean displayICE = false;
            
            if (rowList[rowList.length-1].equals(",")) {
                    row+= " ";     
            }
            
            try {
                if (row.contains("\"")) {
                    
                    String[] separateQuotes = row.split("\"");
                    String[] separateComma1 = separateQuotes[0].split(",");
                    for (String part : separateComma1) {
                        parts.add(part);
                    }
                    parts.add(separateQuotes[1]);
                    String[] separateComma2 = separateQuotes[2].split(",");
                    for (int partNum = 1; partNum < separateComma2.length; partNum++) {
                        parts.add(separateComma2[partNum]);
                    }

                    
                } else {
                    parts = new ArrayList<String>(Arrays.asList(row.split(",")));
                }
                if (parts.size() != 6) {
                    throw new ArrayIndexOutOfBoundsException();
                }
                Job job = new Job();
                

                try {
                    Integer createdAtIDInteger = Integer.valueOf(parts.get(0));

                    String titleString = parts.get(1);
                    if (titleString.equals("") || titleString.split(" ").length == 0) {
                        throw new NumberFormatException();
                    }
                    
                    String startDateString = parts.get(5);
                    if (!checkDatePattern(startDateString)) {
                        throw new NumberFormatException();
                    } else if (!checkDateValidity(startDateString)) {
                        throw new InvalidCharacteristicException();
                    }

                    job.setCreatedAtID(createdAtIDInteger);
                    job.setTitle(titleString);
                    job.setStartDate(startDateString);

                } catch (NumberFormatException e1) {
                    throw new NFException();
                } 

                String descriptionString = parts.get(2);
                if (!descriptionString.equals("") && descriptionString.split(" ").length != 0) {
                    job.setDescription(descriptionString);
                }

                String degreeString = parts.get(3);
                if (degreeString.equals("Bachelor") || degreeString.equals("Master") || degreeString.equals("PHD")) {
                    job.setDegree(degreeString);
                } else {
                    displayICE = true;
                }
                
                try {
                    if (!parts.get(4).equals("")) {
                        Integer salaryInteger = Integer.valueOf(parts.get(4));
                        if (salaryInteger>0) {
                            job.setSalary(salaryInteger);
                        } else {
                            displayICE = true;
                        }
                    }
                } catch (NumberFormatException e4) {
                    displayNFE = true;
                }
               jobList.add(job);

            } catch (ArrayIndexOutOfBoundsException e) {
                displayIDE = true;
            } catch (NFException e2) {
                displayNFE = true;
            } catch (InvalidCharacteristicException e3) {
                displayICE = true;
            }

            if (displayIDE == true) {
                InvalidDataFormatException errorIDE = new InvalidDataFormatException();
                errorIDE.jobError(Integer.toString(lineCount));
            } else {
                if (displayNFE == true) {
                    NFException errorNFE = new NFException();
                    errorNFE.jobError(Integer.toString(lineCount));
                }
                if (displayICE == true) {
                    InvalidCharacteristicException errorICE = new InvalidCharacteristicException();
                    errorICE.jobError(Integer.toString(lineCount));
                }
            }
            row = bufferedReader.readLine();
            lineCount++;
        }

    bufferedReader.close();
    fileReader.close();
    }

    // Method to transform the applicant file into a list of applicant objects. 
    // Throws errors, skip rows, and skip fields when applicable.
    /**
      * @param File file
      * @return void
      * @throws IOException, ParseException
      **/
    public void readApplicantFile(File file) throws IOException, ParseException {
        applicantList = new ArrayList<Applicant>();
        String row;
        int lineCount;
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        bufferedReader.readLine();
        row = bufferedReader.readLine();
        lineCount = 2;
        while (row != null && !row.equals("")){
            String[] rowList = row.split("");
            List<String> parts = new ArrayList<String>();
            boolean displayIDE = false;
            boolean displayNFE = false;
            boolean displayICE = false;
            
            if (rowList[rowList.length-1].equals(",")) {
                    row+= " ";     
            }
            
            try {
                if (row.contains("\"")) {
                    
                    String[] separateQuotes = row.split("\"");
                    String[] separateComma1 = separateQuotes[0].split(",");
                    for (String part : separateComma1) {
                        parts.add(part);
                    }
                    parts.add(separateQuotes[1]);
                    String[] separateComma2 = separateQuotes[2].split(",");
                    for (int partNum = 1; partNum < separateComma2.length; partNum++) {
                        parts.add(separateComma2[partNum]);
                    }

                    
                } else {
                    parts = new ArrayList<String>(Arrays.asList(row.split(",")));
                }
                if (parts.size() != 13) {
                    throw new ArrayIndexOutOfBoundsException();
                }
                Applicant applicant = new Applicant();
                

                try {
                    Integer createdAtIDInteger = Integer.valueOf(parts.get(0));

                    String lastNameString = parts.get(1);
                    if (lastNameString.equals("") || lastNameString.split(" ").length == 0) {
                        throw new NumberFormatException();
                    }
                    String firstNameString = parts.get(2);
                    if (firstNameString.equals("") || firstNameString.split(" ").length == 0) {
                        throw new NumberFormatException();
                    }
                    Integer ageInteger = Integer.valueOf(parts.get(4));
                    if (ageInteger<=18 || ageInteger>=100) {
                        throw new InvalidCharacteristicException();
                    }

                    applicant.setCreatedAtID(createdAtIDInteger);
                    applicant.setLastName(lastNameString);
                    applicant.setFirstName(firstNameString);
                    applicant.setAge(ageInteger);

                } catch (NumberFormatException e1) {
                    throw new NFException();
                } 

                String careerSummaryString = parts.get(3);
                if (!careerSummaryString.equals("") && careerSummaryString.split(" ").length != 0) {
                    applicant.setCareerSummary(careerSummaryString);
                }

                String genderString = parts.get(5);
                if (genderString.equals("female") || genderString.equals("male") || genderString.equals("other")) {
                    applicant.setGender(genderString);
                } else {
                    displayICE = true;
                }

                String highestDegreeString = parts.get(6);
                if (highestDegreeString.equals("Bachelor") || highestDegreeString.equals("Master") || highestDegreeString.equals("PHD")) {
                    applicant.setHighestDegree(highestDegreeString);
                } else {
                    displayICE = true;
                }


                try {
                    Integer COMP90041Integer = Integer.valueOf(parts.get(7));
                    if (COMP90041Integer>=49 && COMP90041Integer<=100) {
                        applicant.setCOMP90041(COMP90041Integer);
                    } else {
                        displayICE = true;
                    }
                } catch (NumberFormatException e4) {
                    displayNFE = true;
                }

                try {
                    Integer COMP90038Integer = Integer.valueOf(parts.get(8));
                    if (COMP90038Integer>=49 && COMP90038Integer<=100) {
                        applicant.setCOMP90038(COMP90038Integer);
                    } else {
                        displayICE = true;
                    }
                } catch (NumberFormatException e4) {
                    displayNFE = true;
                }

                try {
                    Integer COMP90007Integer = Integer.valueOf(parts.get(9));
                    if (COMP90007Integer>=49 && COMP90007Integer<=100) {
                        applicant.setCOMP90007(COMP90007Integer);
                    } else {
                        displayICE = true;
                    }
                } catch (NumberFormatException e4) {
                    displayNFE = true;
                }

                try {
                    Integer INFO90002Integer = Integer.valueOf(parts.get(10));
                    if (INFO90002Integer>=49 && INFO90002Integer<=100) {
                        applicant.setINFO90002(INFO90002Integer);
                    } else {
                        displayICE = true;
                    }
                } catch (NumberFormatException e4) {
                    displayNFE = true;
                }
                
                try {
                    Integer salaryExpectationsInteger = Integer.valueOf(parts.get(11));
                    if (salaryExpectationsInteger>0) {
                        applicant.setSalaryExpectations(salaryExpectationsInteger);
                    } else {
                        displayICE = true;
                    }
                } catch (NumberFormatException e4) {
                    displayNFE = true;
                }

                String availabilityString = parts.get(12);
                if (checkDatePattern(availabilityString)) {
                    if (checkDateValidity(availabilityString)) {
                        applicant.setAvailability(availabilityString);
                    } else {
                        displayICE = true;
                    }
                } else {
                    displayNFE = true;
                }
                applicantList.add(applicant);

            } catch (ArrayIndexOutOfBoundsException e) {
                displayIDE = true;
            } catch (NFException e2) {
                displayNFE = true;
            } catch (InvalidCharacteristicException e3) {
                displayICE = true;
            }

            if (displayIDE == true) {
                InvalidDataFormatException errorIDE = new InvalidDataFormatException();
                errorIDE.applicationError(Integer.toString(lineCount));
            } else {
                if (displayNFE == true) {
                    NFException errorNFE = new NFException();
                    errorNFE.applicationError(Integer.toString(lineCount));
                }
                if (displayICE == true) {
                    InvalidCharacteristicException errorICE = new InvalidCharacteristicException();
                    errorICE.applicationError(Integer.toString(lineCount));
                }
            }
            row = bufferedReader.readLine();
            lineCount++;
        }

    bufferedReader.close();
    fileReader.close();
    }

    // Method to transform the job applied file into a list of job Applied objects.
    /**
      * @param File file
      * @return void
      * @throws IOException, ParseException
      **/
    public void readJobAppliedFile(File file) throws IOException, ParseException {
        jobAppliedList = new ArrayList<JobApplied>();
        String row;
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        row = bufferedReader.readLine();
        while (row != null && !row.equals("")){
            String[] rowSplit = row.split("\"");
            String[] jobSplit = rowSplit[0].split(",");
            JobApplied jobApplied = new JobApplied(Integer.valueOf(jobSplit[0]));
            String[] appliedSplit = rowSplit[1].split(",");
            for (String ID : appliedSplit) {
                Integer IDInteger = Integer.valueOf(ID);
                jobApplied.addApplicant(IDInteger);
            }
            jobAppliedList.add(jobApplied);

            row = bufferedReader.readLine();
        }

    bufferedReader.close();
    fileReader.close();
    }

    // Add new applicant the the correct jobApplied ID
    /**
      * @param Integer newApplicantID, Integer destJobID
      * @return void
      * @throws
      **/
    public void addApplicantTo(Integer newApplicantID, Integer destJobID) {
        List<JobApplied> temp = new ArrayList<JobApplied>();
        boolean exist = false;

        for (JobApplied oldJobApplied : getJobAppliedList()) {
            if (oldJobApplied.getJobID().equals(destJobID)) {
                oldJobApplied.addApplicant(newApplicantID);
                exist = true;
            }
            temp.add(oldJobApplied);
        }
        if (!exist) {
            JobApplied newJobApp = new JobApplied(destJobID);
            // System.out.println("Created new ID:" + Integer.toString(destJobID));
            // System.out.println("Adding Applicant ID:" + Integer.toString(newApplicantID));
            newJobApp.addApplicant(newApplicantID);
            temp.add(newJobApp);
        }

        this.jobAppliedList.clear();
        for (JobApplied newJobApplied : temp) {
            this.jobAppliedList.add(newJobApplied);
        }

    }
    // Create the matchmaking function for the HRStaffPortal and the Audit
    
    // Check if date is a valid pattern
    /**
      * @param String dateString
      * @return boolean
      * @throws
      **/
    public boolean checkDatePattern(String dateString) {
        if (DATE_PATTERN.matcher(dateString).matches()) {
            return true;
        }
        return false; 
    }

    // Check if date is valid
    /**
      * @param String dateString
      * @return boolean
      * @throws
      **/
    public boolean checkDateValidity(String dateString) {
        SimpleDateFormat datePattern = new SimpleDateFormat("dd/MM/yy");
        datePattern.setLenient(false);
        try {
            datePattern.parse(dateString);
            return true;
        } catch(ParseException e) {
            return false;
        }
    }

    /**
      * @param Job job
      * @return void
      * @throws
      **/
    public void addJob(Job job) {
        jobList.add(job);
    }

    /**
      * @param Applicant applicant
      * @return void
      * @throws
      **/
    public void addApplicant(Applicant applicant) {
        applicantList.add(applicant);
    }
    
    // Getter Instance variables
    /**
      * @param
      * @return String jobListPath
      * @throws 
      **/
    public String getJobListPath() {
        return jobListPath;
    }

    /**
      * @param
      * @return String applicantListPath
      * @throws
      **/
    public String getApplicantListPath() {
        return applicantListPath;
    }

    /**
      * @param
      * @return String jobAppliedListPath
      * @throws
      **/
    public String getJobAppliedListPath() {
        return jobAppliedListPath;
    }

    /**
      * @param
      * @return List<Job> jobList
      * @throws
      **/
    public List<Job> getJobList() {
        return jobList;
    }

    /**
      * @param
      * @return List<Applicant>
      * @throws
      **/
    public List<Applicant> getApplicantList() {
        return applicantList;
    }

    /**
      * @param
      * @return List<JobApplied>
      * @throws
      **/
    public List<JobApplied> getJobAppliedList() {
        return jobAppliedList;
    }


}
