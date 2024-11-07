public class ManageCourse {
   private static final Coordinator REQUEST = UI.getCoordinator();

   /**
    * Manages the course creation, editing, and deletion process.
    * Displays course management menu and processes user choices.
    */
   public static void manageCourse() {
      while (true) {
         Menu.courseMenu();  // Display the main course menu
         int choice = Menu.getChoice(2);  // Get user's choice from the menu
         switch (choice) {
            case 0:  // Exit case
               return;
            case 1:  // Create new course
               createCourse();
               break;
            default:  // Edit or delete course if courses are available
               if (!REQUEST.getCOURSES().isEmpty()) {
                  editCourse();
               } else {
                  System.out.println("Course list is empty");
               }
               break;
         }
      }
   }

   /**
    * Creates a new course by prompting the user for course details.
    */
   private static void createCourse() {
      String courseId = Menu.get("Enter course ID: ").toUpperCase();
      String courseName = Menu.get("Enter course name: ");
      TimeSlot timeSlot = ManageTimeSlot.createTimeSlot(null);  // Create a timeslot for the course
      REQUEST.createCourse(courseId, courseName, timeSlot);  // Call coordinator to create the course
   }

   /**
    * Allows editing of existing courses, including updating course details, adding time slots, and deleting courses.
    */
   private static void editCourse() {
      while (true) {
         Menu.editCourseMenu();  // Display the edit menu
         int choice = Menu.getChoice(3);  // Get user's choice from the edit menu
         switch (choice) {
            case 0:  // Exit case
               return;
            case 1:  // Edit course details
               Course course = findCourseById();  // Retrieve course by ID
               if (course != null) {
                  editCourseDetails(course);  // Edit course details
               } else {
                  System.out.println("Course not found");
               }
               break;
            case 2:  // Add a time slot to the course
               ManageTimeSlot.addTimeSlot();
               break;
            default:  // Delete a course
               deleteCourse();
               break;
         }
      }
   }

   /**
    * Prompts the user for a course ID and retrieves the corresponding course.
    *
    * @return The course with the given ID, or null if not found
    */
   private static Course findCourseById() {
      String courseId = Menu.get("Enter course ID: ");  // Prompt for course ID
      return REQUEST.findCourse(courseId);  // Find course by ID
   }

   /**
    * Allows the user to edit the details (ID or name) of an existing course.
    *
    * @param course The course to be edited
    */
   private static void editCourseDetails(Course course) {
      while (true) {
         Menu.editCourseDetailsMenu();  // Display the menu for editing course details
         int choice = Menu.getChoice(2);  // Get user's choice from the menu
         switch (choice) {
            case 0:  // Exit case
               return;
            case 1:  // Edit course ID
               String newCourseId = Menu.get("Enter new course ID: ");
               if (REQUEST.changeCourseID(course, newCourseId)) {
                  System.out.println("Course ID has been updated");
               } else {
                  System.out.println("Course ID could not be updated");
               }
               break;
            default:  // Edit course name
               String newCourseName = Menu.get("Enter new course name: ");
               if (REQUEST.changeCourseName(course, newCourseName)) {
                  System.out.println("Course name has been updated");
               } else {
                  System.out.println("Course name could not be updated");
               }
               break;
         }
      }
   }

   /**
    * Deletes a course after confirming it exists.
    */
   private static void deleteCourse() {
      Course course = findCourseById();  // Retrieve course by ID
      if (course == null) {
         System.out.println("Course not found");
         return;
      }
      if (REQUEST.deleteCourse(course)) {
         System.out.println("Course has been deleted");
      } else {
         System.out.println("Course could not be deleted");
      }
   }
}