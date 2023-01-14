import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;
import java.text.ParseException;

/**
 * COMP90041, Sem2, 2022: Final Project
 * @author: Warren Kuah 
 */
public class HRAssistant {

      public static void main(String[] args) throws IOException, ParseException {
            int argsSize = args.length;
            int rFlagNum = 0;
            int aFlagNum = 0;
            int jFlagNum = 0;
            String rFlag = null;
            String aFlag = null;
            String jFlag = null;
            boolean helpFlag = false;
            
            if (argsSize < 1 || argsSize > 6) {
                  helpFlag = true;
            } else if (argsSize == 1 && (args[0].equals("-r") || args[0].equals("--role"))) {
                  System.out.println("ERROR: no role defined.");
                  helpFlag = true;
            } else {
                  for (int argPos = 0; argPos < argsSize; argPos++) {
                        if (argPos % 2 == 0) {
                              if (args[argPos].equals("-h") || args[argPos].equals("--help")) {
                                    helpFlag = true;
                                    break;
                              }else if (args[argPos].equals("-a") || args[argPos].equals("--applications")) {
                                    aFlagNum++;
                                    try {
                                          aFlag = args[argPos+1];
                                    } catch (IndexOutOfBoundsException e1) {
                                          helpFlag = true;
                                    }
                              } else if (args[argPos].equals("-r") || args[argPos].equals("--role")) {
                                    rFlagNum++;
                                    try {
                                          rFlag = args[argPos+1];
                                    } catch (IndexOutOfBoundsException e1) {
                                          helpFlag = true; 
                                          System.out.println("ERROR: no role defined.");
                                    }
                              } else if (args[argPos].equals("-j") || args[argPos].equals("--jobs")) {
                                    jFlagNum++;
                                    try {
                                          jFlag = args[argPos+1];
                                    } catch (IndexOutOfBoundsException e1) {
                                          helpFlag = true;
                                    }
                              } else {
                                    helpFlag = true;
                                    break;
                              }
                        }
                  }
                  if (aFlagNum > 1 || jFlagNum > 1 || rFlagNum > 1 || rFlagNum == 0) {
                        helpFlag = true;
                  }
            }
      

            if (helpFlag) {
                  callHelp();
            } else {
                  switch(rFlag) {
                        case "applicant":
                              runApplicant(jFlag, aFlag);
                              break;
                        case "hr":
                              runHR(jFlag, aFlag);
                              break;
                        case "audit":
                              runAudit(jFlag, aFlag);
                              break;
                        default:
                              System.out.println("ERROR: " + rFlag + " is not a valid role.");
                              callHelp();
                              break;
                  }
            }
      }
      /**
      * @param String jFlag, String aFlag
      * @return void
      * @throws IOException, ParseException
      **/
      public static void runApplicant(String jFlag, String aFlag) throws IOException, ParseException {
            Scanner keyboard = new Scanner(System.in);
            displayWelcomeMessage("welcome_applicant.ascii");
            ApplicantPortal aPortal = new ApplicantPortal(jFlag, aFlag);
            aPortal.displayMenu();
            System.out.print("> ");
            String command;
            command = keyboard.nextLine();
            while (!command.equals("quit") && !command.equals("q")) {
                  if (command.equals("quit") || command.equals("q")) {
                        break;
                  } else if (command.equals("jobs") || command.equals("j")) {
                        aPortal.listJobs(keyboard);
                  } else if (!aPortal.getProfileCreated() && (command.equals("create") || command.equals("c"))) {
                        aPortal.createApplication(keyboard);
                  } else {
                        while (!command.equals("quit") && !command.equals("q") && !command.equals("create") && !command.equals("c") && !command.equals("jobs") && !command.equals("j")) {
                              command = "";
                              System.out.println("Invalid input! Please enter a valid command to continue: ");
                              System.out.print("> ");
                              command = keyboard.nextLine();
                        }
                        continue;
                  }
                  aPortal.displayMenu();
                  System.out.print("> ");
                  command = keyboard.nextLine();
            }
            System.out.println();
            
      }
      /**
      * @param String jFlag, String aFlag
      * @return void
      * @throws IOException, ParseException
      **/
      public static void runHR(String jFlag, String aFlag) throws IOException, ParseException {
            Scanner keyboard = new Scanner(System.in);
            displayWelcomeMessage("welcome_hr.ascii");
            HRStaffPortal HRPortal = new HRStaffPortal(jFlag, aFlag);
            HRPortal.displayMenu();
            System.out.print("> ");
            String command;
            command = keyboard.nextLine();
            while (!command.equals("quit") && !command.equals("q")) {
                  if (command.equals("quit") || command.equals("q")) {
                        break;
                  } else if (command.equals("create") || command.equals("c")) {
                        HRPortal.createJob(keyboard);
                  } else if (command.equals("jobs") || command.equals("j")) {
                        HRPortal.listJobs();
                  } else if (command.equals("applicants") || command.equals("a")) {
                        HRPortal.listApplicants();
                  } else if (command.equals("filter") || command.equals("f")) {
                        HRPortal.filterApplicants(keyboard);
                  } else if (command.equals("match") || command.equals("m")) {
                        HRPortal.matchmake();
                  } else {
                        while (!command.equals("quit") && !command.equals("q") && !command.equals("create") && !command.equals("c") && !command.equals("jobs") && !command.equals("j") && !command.equals("applicants") && !command.equals("a") && !command.equals("filter") && !command.equals("f") && !command.equals("match") && !command.equals("m")) {
                              command = "";
                              System.out.println("Invalid input! Please enter a valid command to continue: ");
                              System.out.print("> ");
                              command = keyboard.nextLine();
                        }
                        continue;
                  }
                  HRPortal.displayMenu();
                  System.out.print("> ");
                  command = keyboard.nextLine();
            }
            System.out.println();
      }
      /**
      * @param String jFlag, String aFlag
      * @return void
      * @throws IOException, ParseException
      **/
      public static void runAudit(String jFlag, String aFlag) {
            System.out.println("Audit role!");
      }
      /**
      * @param 
      * @return void
      * @throws 
      **/
      public static void callHelp() {
            System.out.println("HRAssistant - COMP90041 - Final Project");
            System.out.println();
            System.out.println("Usage: java HRAssistant [arguments]");
            System.out.println();
            System.out.println("Arguments:");
            System.out.println("    -r or --role            Mandatory: determines the user's role");
            System.out.println("    -a or --applications    Optional: path to applications file");
            System.out.println("    -j or --jobs            Optional: path to jobs file");
            System.out.println("    -h or --help            Optional: print Help (this message) and exit");
      }

      /**
      * @param String filename
      * @return void
      * @throws
      **/
      private static void displayWelcomeMessage(String filename) {

            Scanner inputStream = null;

            try{
            inputStream = new Scanner(new FileInputStream(filename));
            }
            catch (FileNotFoundException e)
            {
            System.out.println("Welcome File not found.");
            }

            while(inputStream.hasNextLine())
            {
                  System.out.println(inputStream.nextLine());
            }
      }
}

