import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class HRStaffPortal extends Instance {
    private Integer numOfApplicants;

    /**
    * @param  jobListPath, applicantListPath
    * @return 
    * @throws IOException, ParseException
    **/
    public HRStaffPortal(String jobListPath, String applicantListPath) throws IOException, ParseException {
        super(jobListPath, applicantListPath);
        this.numOfApplicants = getApplicantList().size();
    }

    /**
      * @param Scanner keyboard
      * @return void
      * @throws IOException, ParseException
      **/
    public void createJob(Scanner keyboard) throws IOException, ParseException {
        
        String createdAt = "";
        String title = "";
        String description = "";
        String degree = "";
        String salary = "";
        String startDate = "";
        
        

        Job job = new Job();
        System.out.println("# Create new Job");

        String command = "";
        System.out.print("Position Title: ");
        while (command.equals("") || command.split(" ").length == 0) {
            command = keyboard.nextLine();
            if ((command.equals("") || command.split(" ").length == 0)) {
                System.out.print("Ooops! Position Title must be provided: ");
            } else {
                job.setTitle(command);
                title = command;
            }
        }

        command = "";
        System.out.print("Position Description: ");
        command = keyboard.nextLine();
        if (!command.equals("") && command.split(" ").length != 0) {
            job.setDescription(command);
        description = command;
        } else {
            description = "";
        }

        command = "";
        System.out.print("Minimum Degree Requirement: ");
        while (!command.equals("Bachelor") && !command.equals("Master") && !command.equals("PHD")) {
            command = keyboard.nextLine();
            if (!command.equals("Bachelor") && !command.equals("Master") && !command.equals("PHD")) {
                System.out.print("Invalid input! Please specify Minimum Degree Requirement: ");
            } else {
                job.setDegree(command);
                degree = command;
            }
        }

        command = "";
        Integer commandInteger = -1;
        System.out.print("Salary ($ per annum): ");
        while (commandInteger <= 0) {
            command = keyboard.nextLine();
            if (command.equals("") || command.split(" ").length == 0){
                salary = "";
                break;
            }
            try {
                commandInteger = Integer.valueOf(command);
                if (commandInteger <= 0) {
                    System.out.print("Invalid input! Please specify Salary ($ per annum): ");
                } else {
                    job.setSalary(commandInteger);
                    salary = command;
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please specify Salary ($ per annum): ");
            }  
        }

        command = "";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        System.out.print("Start Date: ");
        while (true) {
            command = keyboard.nextLine();
            if (checkDatePattern(command)) {
                if (checkDateValidity(command)) {
                    job.setStartDate(command);
                    startDate = command;
                    break;
                    
                }
            }
            System.out.print("Invalid input! Please specify Start Date: ");
        }

        long createdAtLong = System.currentTimeMillis() / 1000L;
        createdAt = Long.toString(createdAtLong);
        Integer createdAtInteger = Integer.valueOf(createdAt);
        job.setCreatedAtID(createdAtInteger);

        if (description.contains(",")) {
            description = "\"" + description + "\"";
        }
        PrintWriter outputStream = new PrintWriter(new FileOutputStream(getJobListPath(), true));
        outputStream.println(createdAt + "," + title + "," + description + "," + degree + "," + salary + "," + startDate);
        outputStream.flush();
        outputStream.close();
        
        addJob(job);

    }
    /**
      * @param
      * @return void
      * @throws
      **/
    public void listJobs() {
        List<Job> jobList = getJobList();
        List<Applicant> applicantList = getApplicantList();
        List<JobApplied> jobAppliedList = getJobAppliedList();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");

        System.out.println("# List Jobs");

        if (jobList.size() == 0) {
            System.out.println("No jobs available");
        } else {
            int count = 0;
            for (Job displayJob : jobList) {
                count ++;
                Integer createdAtInteger = displayJob.getCreatedAtID();
                String title = displayJob.getTitle();
                String description = "n/a";
                String degree = "n/a";
                String salary = "n/a";
                String startDate = formatter.format(displayJob.getStartDate());
                if (displayJob.getDescription() != null) {
                    description = displayJob.getDescription();
                }
                if (displayJob.getDegree() != null) {
                    degree = displayJob.getDegree();
                }
                if (displayJob.getSalary() != null) {
                    salary = Integer.toString(displayJob.getSalary());
                }
                System.out.println("[" + count + "] " + title + " (" + description + "). " + degree + ". Salary: " + salary + ". Start Date: " + startDate + ".");
                int count1 = 97;
                int extra = 1;
                boolean overCount = false;
                for (JobApplied jobAppliedDisplay : jobAppliedList) {
                    if (jobAppliedDisplay.getJobID().equals(createdAtInteger)) {
                        for (Integer applicantID : jobAppliedDisplay.getApplicantList()) {                                        
                            for (Applicant displayApplicant : applicantList) {
                                //System.out.println(displayApplicant.getCreatedAtID() + " = " + applicantID);
                                if (displayApplicant.getCreatedAtID().equals(applicantID)) {
                                    char countChar1 = (char)count1;
                                    String lastName = displayApplicant.getLastName();
                                    String firstName = displayApplicant.getFirstName();
                                    String careerSummary = "n/a";
                                    String age = Integer.toString(displayApplicant.getAge());
                                    String gender = "n/a";
                                    String highestDegree = "n/a";
                                    String COMP90041 = "n/a";
                                    String COMP90038 = "n/a";
                                    String COMP90007 = "n/a";
                                    String INFO90002 = "n/a";
                                    String salaryExpectations = "n/a";
                                    String availability = "n/a";
                                    if (displayApplicant.getCareerSummary() != null) {
                                        careerSummary = displayApplicant.getCareerSummary();
                                    }
                                    if (displayApplicant.getGender() != null) {
                                        gender = displayApplicant.getGender();
                                    }
                                    if (displayApplicant.getHighestDegree() != null) {
                                        highestDegree = displayApplicant.getHighestDegree();
                                    }
                                    if (displayApplicant.getCOMP90041() != null) {
                                        COMP90041 = Integer.toString(displayApplicant.getCOMP90041());
                                    }
                                    if (displayApplicant.getCOMP90038() != null) {
                                        COMP90038 = Integer.toString(displayApplicant.getCOMP90038());
                                    }
                                    if (displayApplicant.getCOMP90007() != null) {
                                        COMP90007 = Integer.toString(displayApplicant.getCOMP90007());
                                    }
                                    if (displayApplicant.getINFO90002() != null) {
                                        INFO90002 = Integer.toString(displayApplicant.getINFO90002());
                                    }
                                    if (displayApplicant.getSalaryExpectations() != null) {
                                        salaryExpectations = Integer.toString(displayApplicant.getSalaryExpectations());
                                    }
                                    if (displayApplicant.getAvailability() != null) {
                                        availability = formatter.format(displayApplicant.getAvailability());
                                    }
                                    if (!overCount) {
                                        System.out.println("    [" + Character.toString(countChar1) + "] " + lastName + ", " + firstName + " (" + highestDegree + "): " + careerSummary + ". Salary Expectations: " + salaryExpectations + ". Availability: " + availability + ".");
                                    } else {
                                        System.out.println("    [" + Character.toString(countChar1) + Integer.toString(extra) + "] " + lastName + ", " + firstName + " (" + highestDegree + "): " + careerSummary + ". Salary Expectations: " + salaryExpectations + ". Availability: " + availability + ".");
                                    }
                                    
                                    count1++;
                                    if (count1 > 122) {
                                        count1 = 97;
                                        overCount = true;
                                        extra ++;
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
    }
    /**
      * @param
      * @return void
      * @throws
      **/
    public void listApplicants(){
        List<Applicant> applicantList = getApplicantList();
        
        List<Date> startDateSortList = new ArrayList<Date>();
        List<Applicant> displayList = new ArrayList<Applicant>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("# List Applications");

        if (applicantList.size() == 0){
            System.out.println("No applicants available.");
        } else {
            for (int pos = 0; pos < applicantList.size(); pos++) {
                Applicant applicant = applicantList.get(pos);
                if (applicant.getAvailability() != null) {
                    startDateSortList.add(applicant.getAvailability());
                }

            }
            List<Date> uniqueStartDate = new ArrayList<Date>(new HashSet<Date>(startDateSortList));
            Collections.sort(uniqueStartDate);

            List<Applicant> tempListNA = new ArrayList<Applicant>();
            for (Applicant tempApplicant5 : applicantList) {
                if (tempApplicant5.getAvailability() == null){
                    tempListNA.add(tempApplicant5);
                }
            }
            for (Date date : uniqueStartDate) {
                //System.out.println(date);
                List<Applicant> tempList = new ArrayList<Applicant>();
                for (Applicant tempApplicant : applicantList) {
                    if (tempApplicant.getAvailability() != null){
                        if (tempApplicant.getAvailability().equals(date)) {
                            tempList.add(tempApplicant);
                        }
                    }
                }
                if (tempList.size() == 1) {
                    displayList.add(tempList.get(0));
                } else {
                    List<String> nameSortList = new ArrayList<String>();
                    for (Applicant tempApplicant1 : tempList) {
                        nameSortList.add(tempApplicant1.getLastName()+tempApplicant1.getFirstName());
                    }
                    
                    List<String> uniqueName = new ArrayList<String>(new HashSet<String>(nameSortList));
                    Collections.sort(uniqueName, String.CASE_INSENSITIVE_ORDER);
                    for (String name : uniqueName) {
                        for (Applicant tempApplicant2 : tempList) {
                            if ((tempApplicant2.getLastName()+tempApplicant2.getFirstName()).equals(name)) {
                                displayList.add(tempApplicant2);
                            }
                        }
                    }
                }
            }
            List<String> tempListNameNA = new ArrayList<String>();
            for (Applicant tempApplicant3 : tempListNA) {
                tempListNameNA.add(tempApplicant3.getLastName()+tempApplicant3.getFirstName());
            }
            List<String> uniqueNameNA = new ArrayList<String>(new HashSet<String>(tempListNameNA));
            Collections.sort(uniqueNameNA);
            for (String nameNA : uniqueNameNA) {
                for (Applicant tempApplicant4 : tempListNA) {
                    if ((tempApplicant4.getLastName()+tempApplicant4.getFirstName()).equals(nameNA)) {
                        displayList.add(tempApplicant4);
                    }
                }
            }
            int count = 1;
            for (Applicant displayApplicant : displayList){
                String lastName = displayApplicant.getLastName();
                String firstName = displayApplicant.getFirstName();
                String careerSummary = "n/a";
                String highestDegree = "n/a";

                String salaryExpectations = "n/a";
                String availability = "n/a";
                if (displayApplicant.getCareerSummary() != null) {
                    careerSummary = displayApplicant.getCareerSummary();
                }
                if (displayApplicant.getHighestDegree() != null) {
                    highestDegree = displayApplicant.getHighestDegree();
                }
                
                if (displayApplicant.getSalaryExpectations() != null) {
                    salaryExpectations = Integer.toString(displayApplicant.getSalaryExpectations());
                }
                if (displayApplicant.getAvailability() != null) {
                    availability = formatter.format(displayApplicant.getAvailability());
                }

                System.out.println("[" + count + "] " + lastName + ", " + firstName + " (" + highestDegree + "): " + careerSummary + ". Salary Expectations: " + salaryExpectations + ". Availability: " + availability + ".");
                count++;
            }
        }
    }
    /**
      * @param Scanner keyboard
      * @return void
      * @throws
      **/
    public void filterApplicants(Scanner keyboard) {
        List<Applicant> applicantList = getApplicantList();
        List<Applicant> displayApplicants = new ArrayList<Applicant>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String command = "";
        System.out.println("# Filtered Applications!");
        if (applicantList.size() == 0) {
            System.out.println("No applicants available.");
        } else {
            System.out.print("Filter by: [lastname], [degree] or [wam]: ");
            while (!command.equals("lastname") && !command.equals("degree") && !command.equals("wam")) {
                command = keyboard.nextLine();
                if (!command.equals("lastname") && !command.equals("degree") && !command.equals("wam")) {
                    System.out.print("Invalid Input! Please enter a valid command to continue: ");
                }
                
            }
            switch(command) {
                case "lastname":
                    List<String> sortLastname = new ArrayList<String>();
                    for (Applicant tempApplicant : applicantList) {
                        sortLastname.add(tempApplicant.getLastName()+Integer.toString(tempApplicant.getCreatedAtID()));
                    }
                    Collections.sort(sortLastname, String.CASE_INSENSITIVE_ORDER);
                    for (String nameComposite : sortLastname) {
                        for (Applicant tempApplicant2: applicantList) {
                            if (nameComposite.equals(tempApplicant2.getLastName()+Integer.toString(tempApplicant2.getCreatedAtID()))) {
                                displayApplicants.add(tempApplicant2);
                            }
                        }
                    }
                    break;

                case "degree":
                    List<Applicant> listPHD = new ArrayList<Applicant>();
                    List<Applicant> listMaster = new ArrayList<Applicant>();
                    List<Applicant> listBachelor = new ArrayList<Applicant>();
                    List<Applicant> listNA = new ArrayList<Applicant>();
                    for (Applicant tempApplicant : applicantList) {
                        if (tempApplicant.getHighestDegree() == null) {
                            listNA.add(tempApplicant);
                        } else if (tempApplicant.getHighestDegree().equals("PHD")) {
                            listPHD.add(tempApplicant);
                        } else if (tempApplicant.getHighestDegree().equals("Master")) {
                            listMaster.add(tempApplicant);
                        } else if (tempApplicant.getHighestDegree().equals("Bachelor")) {
                            listBachelor.add(tempApplicant);
                        }
                    }
                    for (Applicant PHDApplicant : listPHD) {
                        displayApplicants.add(PHDApplicant);
                    }

                    for (Applicant masterApplicant : listMaster) {
                        displayApplicants.add(masterApplicant);
                    }

                    for (Applicant bachelorApplicant : listBachelor) {
                        displayApplicants.add(bachelorApplicant);
                    }

                    for (Applicant NAApplicant : listNA) {
                        displayApplicants.add(NAApplicant);
                    }

                    break;

                case "wam":
                    ArrayList<ArrayList<Double>> wamArray = new ArrayList<>();
                    for (Applicant tempApplicant : applicantList) {
                        ArrayList<Double> wamID = new ArrayList<>();
                        ArrayList<Double> gradeList = new ArrayList<>();
                        if (tempApplicant.getCOMP90041() != null) {
                            double doubleCOMP90041 = (double) tempApplicant.getCOMP90041();
                            gradeList.add(doubleCOMP90041);
                        }
                        if (tempApplicant.getCOMP90038() != null) {
                            double doubleCOMP90038 = (double) tempApplicant.getCOMP90038();
                            gradeList.add(doubleCOMP90038);
                        }
                        if (tempApplicant.getCOMP90007() != null) {
                            double doubleCOMP90007 = (double) tempApplicant.getCOMP90007();
                            gradeList.add(doubleCOMP90007);
                        }
                        if (tempApplicant.getINFO90002() != null) {
                            double doubleINFO90002 = (double) tempApplicant.getINFO90002();
                            gradeList.add(doubleINFO90002);
                        }
                        wamID.add(calculateWAM(gradeList));
                        wamID.add( (double) tempApplicant.getCreatedAtID());
                        wamArray.add(wamID);
                    }
                    final Comparator<ArrayList<Double>> comparator = new Comparator<ArrayList<Double>>() {
                        public int compare(ArrayList<Double> dList1, ArrayList<Double> dList2) {
                            return dList2.get(0).compareTo(dList1.get(0));
                        }
                    };
                    Collections.sort(wamArray, comparator);
                    for (ArrayList<Double> wamIDUnit : wamArray){
                        for (Applicant tempApplicant2 : applicantList) {
                            double createdAtDouble = (double) tempApplicant2.getCreatedAtID();
                            if (createdAtDouble == wamIDUnit.get(1)) {
                                displayApplicants.add(tempApplicant2);
                                break;
                            }
                        }
                    }     
            }
            int count = 1;
            for (Applicant displayApplicant : displayApplicants) {
                String lastName = displayApplicant.getLastName();
                String firstName = displayApplicant.getFirstName();
                String careerSummary = "n/a";
                String highestDegree = "n/a";
                String salaryExpectations = "n/a";
                String availability = "n/a";
                if (displayApplicant.getCareerSummary() != null) {
                    careerSummary = displayApplicant.getCareerSummary();
                }
                if (displayApplicant.getHighestDegree() != null) {
                    highestDegree = displayApplicant.getHighestDegree();
                }
                if (displayApplicant.getSalaryExpectations() != null) {
                    salaryExpectations = Integer.toString(displayApplicant.getSalaryExpectations());
                }
                if (displayApplicant.getAvailability() != null) {
                    availability = formatter.format(displayApplicant.getAvailability());
                }

                System.out.println("[" + count + "] " + lastName + ", " + firstName + " (" + highestDegree + "): " + careerSummary + ". Salary Expectations: " + salaryExpectations + ". Availability: " + availability + ".");
                count++;
            }


        }
        
        
    }
    /**
      * @param ArrayList<Double> numList
      * @return double
      * @throws
      **/
    public double calculateWAM(ArrayList<Double> numList) {
        if (numList.size() == 0) {
            return 0.0;
        } else {
            double count = 0.0;
            double total = 0.0;
            for (double grade : numList) {
                count++;
                total += grade;
            }
            return total / count;
        }
    }
    /**
      * @param
      * @return void
      * @throws
      **/
    public void matchmake() {
        List<JobApplied> jobAppliedList = getJobAppliedList();
        List<Job> jobList = getJobList();
        List<Applicant> applicantList = getApplicantList();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("# Matchmake");

        if (jobList.size() == 0) {
            System.out.println("No jobs available.");
        } else if (applicantList.size() == 0) {
            System.out.println("No applicants available.");
        } else {
            int count = 0;
            for (Job displayJob : jobList) {
                count ++;
                Integer createdAtInteger = displayJob.getCreatedAtID();
                String title = displayJob.getTitle();
                String description = "n/a";
                String degree = "n/a";
                String salary = "n/a";
                String startDate = formatter.format(displayJob.getStartDate());
                if (displayJob.getDescription() != null) {
                    description = displayJob.getDescription();
                }
                if (displayJob.getDegree() != null) {
                    degree = displayJob.getDegree();
                }
                if (displayJob.getSalary() != null) {
                    salary = Integer.toString(displayJob.getSalary());
                }
                System.out.println("[" + count + "] " + title + " (" + description + "). " + degree + ". Salary: " + salary + ". Start Date: " + startDate + ".");
                for (JobApplied jobAppliedDisplay : jobAppliedList) {
                    if (jobAppliedDisplay.getJobID().equals(displayJob)) {
                        double[] score = new double[jobAppliedDisplay.getApplicantList().size()];
                        
                    }
                }
            }
        }
    }
    /**
      * @param 
      * @return void
      * @throws
      **/
    public void displayMenu() {
        System.out.println(Integer.toString(getApplicantsNum()) + " applications received.");
        System.out.println("Please enter one of the following commands to continue:");
        System.out.println("- create new job: [create] or [c]");
        System.out.println("- list available jobs: [jobs] or [j]");
        System.out.println("- list applicants: [applicants] or [a]");
        System.out.println("- filter applications: [filter] or [f]");
        System.out.println("- matchmaking: [match] or [m]");
        System.out.println("- quit the program: [quit] or [q]");
    }

    // Setter methods for HRStaffPortal
    /**
      * @param 
      * @return void
      * @throws
      **/
    public void setApplicantsNum() {
        this.numOfApplicants = getApplicantList().size();
    }

    // Getter methods for HRStaffPortal
    /**
      * @param 
      * @return Integer numOfApplicants
      * @throws
      **/
    public Integer getApplicantsNum() {
        return numOfApplicants;
    }
}
