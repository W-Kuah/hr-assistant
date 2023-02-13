import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class HRStaffPortal extends Instance {
    private Integer numOfApplicants;


    public HRStaffPortal(String jobListPath, String applicantListPath) throws IOException, ParseException {
        super(jobListPath, applicantListPath);
        this.numOfApplicants = getApplicantList().size();
    }

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
                List<Applicant> applied = new ArrayList<Applicant>();
                for (JobApplied jobAppliedDisplay : jobAppliedList) {
                    if (jobAppliedDisplay.getJobID().equals(createdAtInteger)) {
                        List<Integer> applicantIDsForJobList = jobAppliedDisplay.getApplicantList();
                        for (Applicant applicant: applicantList) {
                           if(applicantIDsForJobList.contains(applicant.getCreatedAtID())) {
                                applied.add(applicant);
                           }
                            
                        }
                        
                    }
                }
                List<Applicant> chosen = chooseApplicant(applied, displayJob);
                if (chosen.size() > 0) {
                    Integer count1 = 1;
                    for (Applicant chosenApplicant : chosen) {
                        String lastName = chosenApplicant.getLastName();
                        String firstName = chosenApplicant.getFirstName();
                        String careerSummary = "n/a";
                        String highestDegree = "n/a";

                        String salaryExpectations = "n/a";
                        String availability = "n/a";
                        if (chosenApplicant.getCareerSummary() != null) {
                            careerSummary = chosenApplicant.getCareerSummary();
                        }
                        if (chosenApplicant.getHighestDegree() != null) {
                            highestDegree = chosenApplicant.getHighestDegree();
                        }
                        
                        if (chosenApplicant.getSalaryExpectations() != null) {
                            salaryExpectations = Integer.toString(chosenApplicant.getSalaryExpectations());
                        }
                        if (chosenApplicant.getAvailability() != null) {
                            availability = formatter.format(chosenApplicant.getAvailability());
                        }

                        System.out.println("    [" + count1 + "] " + lastName + ", " + firstName + " (" + highestDegree + "): " + careerSummary + ". Salary Expectations: " + salaryExpectations + ". Availability: " + availability + ".");
                        count1++;
                    }
                } else {
                    System.out.println("    No valid or viable applicants.");
                }
            }
        }
    }
    
    
    private List <Applicant> chooseApplicant(List<Applicant> applicantList, Job job) {
        Double highestScore = 0.25;
        List <Applicant> chosen = new ArrayList<Applicant>();
        Hashtable<Applicant, Double> scoreboard = new Hashtable<Applicant, Double>();
        for (Applicant applicant: applicantList) {
            scoreboard.put(applicant, (double) 0);
            if (applicant.getAvailability().compareTo(job.getStartDate()) <= 0) {
                scoreboard.put(applicant, scoreboard.get(applicant) + (double) 0.2);
            } else {
                double daysBetween = ChronoUnit.DAYS.between(convertToLocalDate(job.getStartDate()), convertToLocalDate(applicant.getAvailability()));
                if (daysBetween < 28) {
                    double part_score = daysBetween * 0.01071428571;
                    scoreboard.put(applicant, scoreboard.get(applicant) + (double) part_score);
                }
            }
            if (applicant.getCOMP90007() != null) {
                Integer scoreCOMP90007 = applicant.getCOMP90007();
                if (scoreCOMP90007 >= 80) {
                    scoreboard.put(applicant, scoreboard.get(applicant) + (double) 0.0625);
                } else if (scoreCOMP90007 >= 70) {
                    scoreboard.put(applicant, scoreboard.get(applicant) + (double) 0.046875);
                } else if (scoreCOMP90007 >= 60) {
                    scoreboard.put(applicant, scoreboard.get(applicant) + (double) 0.031255);
                } else if (scoreCOMP90007 >= 50) {
                    scoreboard.put(applicant, scoreboard.get(applicant) + (double) 0.015625);
                }
            }

            if (applicant.getCOMP90038() != null) {
                Integer scoreCOMP90038 = applicant.getCOMP90038();
                if (scoreCOMP90038 >= 80) {
                    scoreboard.put(applicant, scoreboard.get(applicant) + (double) 0.0625);
                } else if (scoreCOMP90038 >= 70) {
                    scoreboard.put(applicant, scoreboard.get(applicant) + (double) 0.046875);
                } else if (scoreCOMP90038 >= 60) {
                    scoreboard.put(applicant, scoreboard.get(applicant) + (double) 0.031255);
                } else if (scoreCOMP90038 >= 50) {
                    scoreboard.put(applicant, scoreboard.get(applicant) + (double) 0.015625);
                }
            }

            if (applicant.getCOMP90041() != null) {
                Integer scoreCOMP90041 = applicant.getCOMP90041();
                if (scoreCOMP90041 >= 80) {
                    scoreboard.put(applicant, scoreboard.get(applicant) + (double) 0.0625);
                } else if (scoreCOMP90041 >= 70) {
                    scoreboard.put(applicant, scoreboard.get(applicant) + (double) 0.046875);
                } else if (scoreCOMP90041 >= 60) {
                    scoreboard.put(applicant, scoreboard.get(applicant) + (double) 0.031255);
                } else if (scoreCOMP90041 >= 50) {
                    scoreboard.put(applicant, scoreboard.get(applicant) + (double) 0.015625);
                }
            }

            if (applicant.getINFO90002() != null) {
                Integer scoreINFO90002 = applicant.getINFO90002();
                if (scoreINFO90002 >= 80) {
                    scoreboard.put(applicant, scoreboard.get(applicant) + (double) 0.0625);
                } else if (scoreINFO90002 >= 70) {
                    scoreboard.put(applicant, scoreboard.get(applicant) + (double) 0.046875);
                } else if (scoreINFO90002 >= 60) {
                    scoreboard.put(applicant, scoreboard.get(applicant) + (double) 0.031255);
                } else if (scoreINFO90002 >= 50) {
                    scoreboard.put(applicant, scoreboard.get(applicant) + (double) 0.015625);
                }
            }
            if (applicant.getSalaryExpectations() != null) {
                Integer salaryDifference = applicant.getSalaryExpectations() - job.getSalary();
                if (salaryDifference > 0) {
                    Double salaryDiffDouble = Double.valueOf(salaryDifference);
                    Double pointsAwarded = 0.25 - (0.05 * (salaryDiffDouble / 1500));
                    scoreboard.put(applicant, scoreboard.get(applicant) + pointsAwarded);
                } else {
                    scoreboard.put(applicant, scoreboard.get(applicant) + (double) 0.25);
                }
            } else {
                scoreboard.put(applicant, scoreboard.get(applicant) + (double) 0.25);
            }
        }

        for (Applicant applicant : applicantList) {
            Double applicantScore = scoreboard.get(applicant);
            if (applicantScore > highestScore) {
                highestScore = applicantScore;
                chosen = new ArrayList<Applicant>();
                chosen.add(applicant);
            } else if (applicantScore == highestScore) {
                chosen.add(applicant);
            }
        }
        return chosen;
    }


    public LocalDate convertToLocalDate(Date dateToConvert) {
        return LocalDate.ofInstant(
          dateToConvert.toInstant(), ZoneId.systemDefault());
    }
    
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
    public void setApplicantsNum() {
        this.numOfApplicants = getApplicantList().size();
    }

    // Getter methods for HRStaffPortal
    public Integer getApplicantsNum() {
        return numOfApplicants;
    }
}
