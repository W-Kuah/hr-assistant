import java.io.*;
import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;


public class ApplicantPortal extends Instance {
    
    private boolean profileCreated;
    private Integer createdAtID;
    private Integer availableJobs;
    private Integer applicationsSubmitted;

    /**
    * @param String jobListPath, String applicantListPath
    * @return 
    * @throws IOException, ParseException
    **/
    public ApplicantPortal(String jobListPath, String applicantListPath) throws IOException, ParseException {
        super(jobListPath, applicantListPath);
        this.profileCreated = false;
        this.createdAtID = null;
        this.availableJobs = getJobList().size();
        this.applicationsSubmitted = 0;
    }

    /**
    * @param Scanner keyboard
    * @return void
    * @throws IOException, ParseException
    **/
    public void createApplication(Scanner keyboard) throws IOException, ParseException {
        String createdAt = "";
        String lastName = "";
        String firstName = "";
        String careerSummary = "";
        String age = "";
        String gender = "";
        String highestDegree = "";
        String COMP90041 = "";
        String COMP90038 = "";
        String COMP90007 = "";
        String INFO90002 = "";
        String salaryExpectations = "";
        String availability = "";
        
        

        Applicant profile = new Applicant();
        System.out.println("# Create new Application");

        String command = "";
        System.out.print("Lastname: ");
        while (command.equals("") || command.split(" ").length == 0) {
            command = keyboard.nextLine();
            if ((command.equals("") || command.split(" ").length == 0)) {
                System.out.print("Ooops! Lastname must be provided: ");
            } else {
                profile.setLastName(command);
                lastName = command;
            }
        }
        command = "";
        System.out.print("Firstname: ");
        while (command.equals("") || command.split(" ").length == 0) {
            command = keyboard.nextLine();
            if ((command.equals("") || command.split(" ").length == 0)) {
                System.out.print("Ooops! Firstname must be provided: ");
            } else {
                profile.setFirstName(command);
                firstName = command;
            }
        }

        command = "";
        System.out.print("Career Summary: ");
        command = keyboard.nextLine();
        if (!command.equals("") && command.split(" ").length != 0) {
            profile.setCareerSummary(command);
            careerSummary = command;
        } else {
            careerSummary = "";
        }

        command = "";
        Integer commandInteger = -1;
        System.out.print("Age: ");
        while (commandInteger <= 18 || commandInteger >= 100) {
            command = keyboard.nextLine();
            try {
                commandInteger = Integer.valueOf(command);
                if (commandInteger <= 18 || commandInteger >= 100) {
                    System.out.print("Ooops! A valid age between 18 and 100 must be provided: ");
                } else {
                    profile.setAge(commandInteger);
                    age = command;
                }
            } catch (NumberFormatException e) {
                System.out.print("Ooops! A valid age between 18 and 100 must be provided: ");
            }
            
        }

        command = "";
        System.out.print("Gender: ");
        while (!command.equals("male") && !command.equals("female") && !command.equals("other")) {
            command = keyboard.nextLine();
            if (!command.equals("male") && !command.equals("female") && !command.equals("other")) {
                System.out.print("Invalid input! Please specify gender: ");
            } else {
                profile.setGender(command);
                gender = command;
            }
        }

        command = "";
        System.out.print("Highest Degree: ");
        while (!command.equals("Bachelor") && !command.equals("Master") && !command.equals("PHD")) {
            command = keyboard.nextLine();
            if (!command.equals("Bachelor") && !command.equals("Master") && !command.equals("PHD")) {
                System.out.print("Invalid input! Please specify Highest Degree: ");
            } else {
                profile.setHighestDegree(command);
                highestDegree = command;
            }
        }

        System.out.println("Coursework: ");

        command = "";
        commandInteger = -1;
        System.out.print("- COMP90041: ");
        while (commandInteger < 49 || commandInteger > 100) {
            command = keyboard.nextLine();
            try {
                commandInteger = Integer.valueOf(command);
                if (commandInteger < 49 || commandInteger > 100) {
                    System.out.print("Invalid input! Please specify COMP90041: ");
                } else {
                    profile.setCOMP90041(commandInteger);
                    COMP90041 = command;
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please specify COMP90041: ");
            }
        }

        command = "";
        commandInteger = -1;
        System.out.print("- COMP90038: ");
        while (commandInteger < 49 || commandInteger > 100) {
            command = keyboard.nextLine();
            try {
                commandInteger = Integer.valueOf(command);
                if (commandInteger < 49 || commandInteger > 100) {
                    System.out.print("Invalid input! Please specify COMP90038: ");
                } else {
                    profile.setCOMP90038(commandInteger);
                    COMP90038 = command;
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please specify COMP90038: ");
            }
        }

       
        command = "";
        commandInteger = -1;
        System.out.print("- COMP90007: ");
        while (commandInteger < 49 || commandInteger > 100) {
            command = keyboard.nextLine();
            try {
                commandInteger = Integer.valueOf(command);
                if (commandInteger < 49 || commandInteger > 100) {
                    System.out.print("Invalid input! Please specify COMP90007: ");
                } else {
                    profile.setCOMP90007(commandInteger);
                    COMP90007 = command;
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please specify COMP90007: ");
            }  
        }

        command = "";
        commandInteger = -1;
        System.out.print("- INFO90002: ");
        while (commandInteger < 49 || commandInteger > 100) {
            command = keyboard.nextLine();
            try {
                commandInteger = Integer.valueOf(command);
                if (commandInteger < 49 || commandInteger > 100) {
                    System.out.print("Invalid input! Please specify INFO90002: ");
                } else {
                    profile.setINFO90002(commandInteger);
                    INFO90002 = command;
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please specify INFO90002: ");
            }  
        }

        command = "";
        commandInteger = -1;
        System.out.print("Salary Expectations ($ per annum): ");
        while (commandInteger <= 0) {
            command = keyboard.nextLine();
            try {
                commandInteger = Integer.valueOf(command);
                if (commandInteger <= 0) {
                    System.out.print("Invalid input! Please specify Salary Expectations ($ per annum): ");
                } else {
                    profile.setSalaryExpectations(commandInteger);
                    salaryExpectations = command;
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please specify Salary Expectations ($ per annum): ");
            }  
        }

        command = "";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String currentDateString = formatter.format(System.currentTimeMillis());
        Date currentDate = formatter.parse(currentDateString);
        System.out.print("Availability: ");
        while (true) {
            command = keyboard.nextLine();
            if (checkDatePattern(command)) {
                if (checkDateValidity(command)) {
                    Date date = formatter.parse(command);
                    if (date.compareTo(currentDate) > 0) {
                        profile.setAvailability(command);
                        availability = command;
                        break;
                    }
                }
            }
            System.out.print("Invalid input! Please specify availability: ");
        }

        long createdAtLong = System.currentTimeMillis() / 1000L;
        createdAt = Long.toString(createdAtLong);
        Integer createdAtInteger = Integer.valueOf(createdAt);
        profile.setCreatedAtID(createdAtInteger);

        if (careerSummary.contains(",")) {
            careerSummary = "\"" + careerSummary + "\"";
        }
        PrintWriter outputStream = new PrintWriter(new FileOutputStream(getApplicantListPath(), true));
        outputStream.println(createdAt + "," + lastName + "," + firstName + "," + careerSummary + "," + age + "," + gender + "," + highestDegree + "," + COMP90041 + "," + COMP90038 + "," + COMP90007 + "," + INFO90002 + "," + salaryExpectations + "," + availability);
        outputStream.flush();
        outputStream.close();
        
        addApplicant(profile);

        profileCreated = true;
        
    }
    /**
    * @param Scanner keyboard
    * @return void
    * @throws
    **/
    public void listJobs(Scanner keyboard) {
        List<Job> jobList = getJobList();
        List<JobApplied> jobAppliedList = getJobAppliedList();
        List<Integer> createdIDSortList = new ArrayList<Integer>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        
        System.out.println("# List Jobs");
        //int len = 0;
        for (int pos = 0; pos < jobList.size(); pos++) {
            if (profileCreated) {
                int jobID = jobList.get(pos).getCreatedAtID();
                boolean exist = false;
                for (JobApplied jobApp : jobAppliedList) {
                    if (jobApp.getJobID() == jobID) {
                        exist = true;
                        if (!jobApp.applicantExist(createdAtID)) {
                            createdIDSortList.add(jobID);
                        }
                    }
                }
                if (!exist) {
                    createdIDSortList.add(jobID);
                }
            } else {
                createdIDSortList.add(jobList.get(pos).getCreatedAtID());
            }
        }
        if (createdIDSortList.size() == 0) {
            System.out.println("No jobs available.");
        } else {
            Integer[] createdIDSort = new Integer[createdIDSortList.size()];
            createdIDSortList.toArray(createdIDSort);
            Arrays.sort(createdIDSort);
            int count = 0;
            for (int jobID : createdIDSort) {
                count ++;
                String title;
                String description;
                String degree;
                String salary;
                String startDate;
                for (Job job : jobList) {
                    if (job.getCreatedAtID() == jobID) {
                        title = job.getTitle();
                        if (job.getDescription() == null) {
                            description = "n/a";
                        } else {
                            description = job.getDescription();
                        }

                        if (job.getDegree() == null) {
                            degree = "n/a";
                        } else {
                            degree = job.getDegree();
                        }
                        
                        if (job.getSalary() == null) {
                            salary = "n/a";
                        } else {
                            salary = Integer.toString(job.getSalary());
                        }

                        if (job.getStartDate() == null) {
                            startDate = "n/a";
                        } else {
                            startDate = formatter.format(job.getStartDate());
                        }
                        System.out.println("[" + count + "] " + title + " (" + description + "). " + degree + ". Salary: " + salary + ". Start Date: " + startDate + ".");
                    }  
                }
            }
            if (profileCreated) {
                String command;
                boolean commandIsValid = false;
                System.out.print("Please enter the jobs you would like to apply for (multiple options are possible): ");

                
                while (!commandIsValid) {
                    command = keyboard.nextLine();
                    String[] jobSplit = command.split(",");
                    try {
                        for (String jobNum : jobSplit) {
                            Integer jobNumInt = Integer.valueOf(jobNum);
                            jobNumInt--;
                            Integer jobIDInteger = createdIDSort[jobNumInt];
                            addApplicantTo(createdAtID, jobIDInteger);
                            applicationsSubmitted++;
                            availableJobs--;
                        }
                        
                        File oldFile = new File(getJobAppliedListPath());
                        oldFile.delete();
                        File newFile = new File(getJobAppliedListPath());
                        newFile.createNewFile();
                        PrintWriter outputStream = new PrintWriter(new FileOutputStream(getJobAppliedListPath(), true));
                        for (JobApplied newJobApplied : getJobAppliedList()) {
                            String line = newJobApplied.getJobID() + ",\"";
                            List<Integer> applicantListToWrite = newJobApplied.getApplicantList();
                            for (int pos = 0; pos < applicantListToWrite.size(); pos++) {
                                line += Integer.toString(applicantListToWrite.get(pos));
                                if (pos < applicantListToWrite.size()-1) {
                                    line += ",";
                                }
                            }
                            line += "\"";
                            outputStream.println(line);
                            
                        }      
                    outputStream.flush();
                    outputStream.close();
                    commandIsValid = true;
                    } catch (NumberFormatException e) {
                        System.out.print("Invalid Input! Please enter a valid number to continue: ");
                    } catch (ArrayIndexOutOfBoundsException e1) {
                        System.out.print("Invalid Input! Please enter a valid number to continue: ");
                    }  catch (IOException e3) {
                            System.out.println("Can't overwrite file");
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
        System.out.println(Integer.toString(getAvailableJobs()) + " jobs available. " + Integer.toString(getApplicationsSubmitted()) + " applications submitted.");
        System.out.println("Please enter one of the following commands to continue:");
        if (!profileCreated) {
            System.out.println("- create new application: [create] or [c]");
        }
        System.out.println("- list available jobs: [jobs] or [j]");
        System.out.println("- quit the program: [quit] or [q]");
    }

    // Getter variables for the Applicant Portal
    /**
    * @param
    * @return Integer availableJobs
    * @throws
    **/
    public Integer getAvailableJobs() {
        return availableJobs;
    }
    /**
    * @param
    * @return Integer applicationsSubmitted
    * @throws
    **/
    public Integer getApplicationsSubmitted() {
        return applicationsSubmitted;
    }
    /**
    * @param keyboard
    * @return boolean profileCreated
    * @throws IOException, ParseException
    **/
    public boolean getProfileCreated() {
        return profileCreated;
    }


}
