public class ManageSchedule {
   private static final Coordinator REQUEST = UI.getCoordinator();

   /**
    * Displays the schedule management menu and handles user input.
    */
   public static void displaySchedule() {
      while (true) {
         Menu.displayScheduleMenu();
         int choice = Menu.getChoice(2);
         switch (choice) {
            case 0:
               return; // Exit the menu
            case 1:
               displayCourseSchedule();
               return; // After displaying course schedule, exit
            default:
               displayGroupSchedule();
               break;
         }
      }
   }

   /**
    * Displays the schedule for all courses.
    */
   private static void displayCourseSchedule() {
      REQUEST.displayCourseSchedule();
   }

   /**
    * Displays the schedule for a specific group.
    * Prompts the user to enter the group name if groups are available.
    */
   private static void displayGroupSchedule() {
      if (!REQUEST.getGROUPS().isEmpty()) {
         String groupName = Menu.get("Enter group name: ");
         REQUEST.displayGroupSchedule(groupName);
      } else {
         System.out.println("You have not created any groups.");
      }
   }
}