import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
   private static final Scanner userInput = new Scanner(System.in);

   // main menu
   public static void displayMenu() {
      String[] menu = {
            "To exit enter 0",
            "To manage courses enter 1",
            "To manage instructors enter 2",
            "To manage groups enter 3",
            "To display schedule enter 4",
            "To save/load schedule enter 5"
      };
      printMenu(menu);
   }

   // course menu
   public static void courseMenu () {
      String[] menu = {
            "To go back enter 0",
            "To create course enter 1",
            "To edit course enter 2"
      };
      printMenu(menu);
   }

   // edit course menu
   public static void editCourseMenu () {
      String[] menu = {
            "To go back enter 0",
            "To edit course details enter 1",
            "To add time slot enter 2",
            "To delete course enter 3"
      };
      printMenu(menu);
   }

   // edit course details menu
   public static void editCourseDetailsMenu () {
      String[] menu = {
            "To go back enter 0",
            "To edit course ID enter 1",
            "To edit course name enter 2"
      };
      printMenu(menu);
   }

   // edit instructor menu
   public static void editInstructorsMenu () {
      String[] menu = {
            "To go back enter 0",
            "To create instructor enter 1",
            "To edit instructor details enter 2",
            "To remove instructor enter 3"
      };
      printMenu(menu);
   }

   // edit instructor details menu
   public static void editInstructorDetailsMenu () {
      String[] menu = {
            "To go back enter 0",
            "To edit instructor ID enter 1",
            "To edit instructor name enter 2",
            "To edit assigned courses enter 3"
      };
      printMenu(menu);
   }

   // edit assigned courses menu
   public static void editAssignedCoursesMenu () {
      String[] menu = {
            "To go back enter 0",
            "To assign course enter 1",
            "To remove an assigned course enter 2"
      };
      printMenu(menu);
   }

   // manage groups menu
   public static void manageGroupsMenu () {
      String[] menu = {
            "To go back enter 0",
            "To create a group enter 1",
            "To change group name enter 2",
            "To add a course to a group enter 3",
            "To delete group enter 4"
      };
      printMenu(menu);
   }

   // display schedule menu
   public static void displayScheduleMenu () {
      String[] menu = {
            "To go back enter 0",
            "To display the course schedule enter 1",
            "To display a group schedule enter 2"
      };
      printMenu(menu);
   }

   // save/load schedule menu
   public static void saveMenu () {
      String[] menu = {
            "To go back enter 0",
            "To save schedule enter 1",
            "To load saved schedule enter 2",
            "To clear save files enter 3"
      };
      if (ManageSaves.fileExists("courses.txt")) {
         menu[1] = "To overwrite current save file";
      }
      printMenu(menu);
   }

   // print menu
   private static void printMenu(String[] menu) {
      System.out.println();
      for (int i = 1; i < menu.length; i++) {
         System.out.println(menu[i]);
      }
      System.out.println(menu[0]);
   }

   // get user input
   public static String get(String message) {
      userInput.nextLine();  // Consume newline left by previous input
      String input = null;
      boolean validInput = true;

      do {
         try {
            System.out.print(message);
            input = userInput.nextLine();
         } catch (InputMismatchException ime) {
            validInput = false;
            System.out.println("Invalid input, please try again.");
         }
      } while (!validInput);
      return input;
   }

   // get user's choice
   public static int getChoice(int inputMax) {
      int choice = 0;
      boolean validInput;

      do {
         System.out.print("\nEnter choice: ");
         validInput = true;
         try {
            choice = userInput.nextInt();
            if (choice < 0 || choice > inputMax) {
               throw new InputMismatchException();
            }
         } catch (InputMismatchException ime) {
            validInput = false;
            System.out.println("Invalid input, please try again.");
            userInput.nextLine();  // Clear invalid input
         }
      } while (!validInput);
      return choice;
   }
}