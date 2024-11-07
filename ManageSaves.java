import java.io.File;
import java.time.format.DateTimeParseException;

public class ManageSaves {
   private static final Coordinator REQUEST = UI.getCoordinator();

   /**
    * Deletes a file at the given file path.
    *
    * @param filePath The path to the file to be deleted.
    * @return true if the file was deleted successfully, false otherwise.
    */
   public static boolean deleteFile(String filePath) {
      File file = new File(filePath);
      return file.delete();
   }

   /**
    * Checks if a file exists at the given file path.
    *
    * @param filePath The path to the file to check.
    * @return true if the file exists, false otherwise.
    */
   public static boolean fileExists(String filePath) {
      File file = new File(filePath);
      return file.exists();
   }

   /**
    * Main menu for managing the save files, including saving, loading, or clearing data.
    */
   public static void editFile() {
      while (true) {
         Menu.saveMenu();
         int choice = Menu.getChoice(3);
         switch (choice) {
            case 0:
               return; // Exit the menu
            case 1:
               saveData();
               break;
            case 2:
               loadData();
               break;
            default:
               clearSaves();
               break;
         }
      }
   }

   /**
    * Handles the saving of courses, instructors, and groups.
    */
   private static void saveData() {
      saveCourses();
      saveInstructors();
      saveGroups();
   }

   /**
    * Saves the course data.
    */
   private static void saveCourses() {
      REQUEST.saveCourses();
      System.out.println("Courses saved successfully.");
   }

   /**
    * Saves the instructor data.
    */
   private static void saveInstructors() {
      REQUEST.saveInstructors();
      System.out.println("Instructors saved successfully.");
   }

   /**
    * Saves the group data.
    */
   private static void saveGroups() {
      REQUEST.saveGroups();
      System.out.println("Groups saved successfully.");
   }

   /**
    * Handles the loading of courses, instructors, and groups.
    */
   private static void loadData() {
      try {
         loadCourses();
         loadInstructorsAndGroups();
      } catch (DateTimeParseException dtpe) {
         handleSaveFileError();
      }
   }

   /**
    * Loads course data from the file.
    */
   private static void loadCourses() {
      if (REQUEST.loadCourses()) {
         System.out.println("Courses loaded successfully.");
      } else {
         System.out.println("Courses save file not found.");
      }
   }

   /**
    * Loads instructor and group data after loading courses.
    */
   private static void loadInstructorsAndGroups() {
      if (!REQUEST.loadInstructors()) {
         handleSaveFileError();
      }
      REQUEST.loadGroups();
      System.out.println("Instructors and Groups loaded successfully.");
   }

   /**
    * Handles errors that occur while loading save files.
    * Clears existing data and informs the user of corruption.
    */
   private static void handleSaveFileError() {
      REQUEST.clearSaves();
      System.out.println("Save files corrupted. Data cleared.");
   }

   /**
    * Clears all saved data and provides feedback to the user.
    */
   private static void clearSaves() {
      if (fileExists("courses.txt")) {
         if (REQUEST.clearSaves()) {
            System.out.println("Save files cleared.");
         } else {
            System.out.println("Could not clear saves.");
         }
      } else {
         System.out.println("No save files found.");
      }
   }
}