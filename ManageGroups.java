public class ManageGroups {
   private static final Coordinator REQUEST = UI.getCoordinator();

   /**
    * Manages the group creation, renaming, adding courses, and deletion process.
    * Displays group management menu and processes user choices.
    */
   public static void manageGroups() {
      while (true) {
         Menu.manageGroupsMenu();  // Display the manage groups menu
         int choice = Menu.getChoice(4);  // Get user's choice from the menu
         switch (choice) {
            case 0:  // Exit case
               return;
            case 1:  // Create new group
               createGroup();
               break;
            case 2:  // Change group name
               changeGroupName();
               break;
            case 3:  // Add course to group
               addCourseToGroup();
               break;
            default:  // Delete group
               deleteGroup();
               break;
         }
      }
   }

   /**
    * Creates a new group by prompting the user for the group name.
    * Ensures the group name is unique before creation.
    */
   private static void createGroup() {
      String groupName = Menu.get("Enter new group name: ").trim();

      if (REQUEST.findGroup(groupName) != null) {
         System.out.println("Group already exists.");
         return;
      }

      Group group = new Group(groupName);
      REQUEST.getGROUPS().addGroup(group);
      System.out.println("Group " + groupName + " created.");
   }

   /**
    * Changes the name of an existing group.
    * Ensures the new name does not conflict with an existing group.
    */
   private static void changeGroupName() {
      if (REQUEST.getGROUPS().isEmpty()) {
         System.out.println("No groups available to rename.");
         return;
      }

      String currentName = Menu.get("Enter current group name: ").trim();
      Group group = REQUEST.findGroup(currentName);

      if (group == null) {
         System.out.println("Group not found.");
         return;
      }

      String newName = Menu.get("Enter new group name: ").trim();
      if (REQUEST.findGroup(newName) != null) {
         System.out.println("A group with this name already exists.");
         return;
      }

      group.updateName(newName);
      System.out.println("Group name updated.");
   }

   /**
    * Adds a course to an existing group.
    * Ensures both group and course exist before adding.
    */
   private static void addCourseToGroup() {
      if (REQUEST.getGROUPS().isEmpty()) {
         System.out.println("No groups available to add courses.");
         return;
      }
      if (REQUEST.getCOURSES().isEmpty()) {
         System.out.println("No courses available.");
         return;
      }

      String groupName = Menu.get("Enter group name: ").trim();
      Group group = REQUEST.findGroup(groupName);

      if (group == null) {
         System.out.println("Group not found.");
         return;
      }

      String courseId = Menu.get("Enter course ID: ").trim();
      Course course = REQUEST.findCourse(courseId);

      if (course == null) {
         System.out.println("Course not found.");
         return;
      }

      group.addCourse(course);
      System.out.println("Course added to group.");
   }

   /**
    * Deletes an existing group after checking its existence.
    */
   private static void deleteGroup() {
      if (REQUEST.getGROUPS().isEmpty()) {
         System.out.println("No groups available to delete.");
         return;
      }

      String groupName = Menu.get("Enter group name: ").trim();
      Group group = REQUEST.findGroup(groupName);

      if (group == null) {
         System.out.println("Group not found.");
         return;
      }

      REQUEST.getGROUPS().remove(group);
      System.out.println("Group deleted.");
   }
}