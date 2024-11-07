public class ManageInstructor {
   private static final Coordinator REQUEST = UI.getCoordinator();

   /**
    * Manages instructor operations including creating, editing details,
    * assigning/removing courses, and removing an instructor.
    */
   public static void manageInstructor() {
      while (true) {
         Menu.editInstructorsMenu();
         int choice = Menu.getChoice(3);
         switch (choice) {
            case 0:
               return;
            case 1:
               createInstructor();
               break;
            case 2:
               handleInstructorEditing();
               break;
            default:
               removeInstructor();
               break;
         }
      }
   }

   /**
    * Creates a new instructor and adds them to the coordinator.
    */
   private static void createInstructor() {
      String instructorId = Menu.get("Enter instructor ID: ").toUpperCase();
      if (instructorId.isEmpty()) {
         System.out.println("Instructor ID cannot be empty.");
         return;
      }

      String instructorName = Menu.get("Enter instructor name: ");
      if (instructorName.isEmpty()) {
         System.out.println("Instructor name cannot be empty.");
         return;
      }

      Instructor instructor = new Instructor(instructorName, instructorId);
      REQUEST.addInstructor(instructor);
      System.out.println("Instructor created successfully!");
   }

   /**
    * Handles the process of editing an existing instructor's details.
    */
   private static void handleInstructorEditing() {
      if (REQUEST.getINSTRUCTORS().isEmpty()) {
         System.out.println("Instructor list is empty.");
         return;
      }

      String instructorId = Menu.get("Enter instructor ID: ");
      Instructor instructor = REQUEST.findInstructor(instructorId);
      if (instructor != null) {
         editInstructorDetails(instructor);
      } else {
         System.out.println("Instructor not found.");
      }
   }

   /**
    * Edits the details of the instructor (ID, name, assigned courses).
    */
   private static void editInstructorDetails(Instructor instructor) {
      while (true) {
         Menu.editInstructorDetailsMenu();
         int choice = Menu.getChoice(3);
         switch (choice) {
            case 0:
               return;
            case 1:
               editInstructorId(instructor);
               break;
            case 2:
               editInstructorName(instructor);
               break;
            default:
               editAssignedCourses(instructor);
               break;
         }
      }
   }

   /**
    * Edits the instructor's ID.
    */
   private static void editInstructorId(Instructor instructor) {
      String newId = Menu.get("Enter new instructor ID: ");
      if (REQUEST.changeInstructorId(instructor, newId)) {
         System.out.println("Instructor ID updated successfully.");
      } else {
         System.out.println("Failed to update instructor ID.");
      }
   }

   /**
    * Edits the instructor's name.
    */
   private static void editInstructorName(Instructor instructor) {
      String newName = Menu.get("Enter new instructor name: ");
      if (REQUEST.changeInstructorName(instructor, newName)) {
         System.out.println("Instructor name updated successfully.");
      } else {
         System.out.println("Failed to update instructor name.");
      }
   }

   /**
    * Removes an instructor from the coordinator.
    */
   private static void removeInstructor() {
      if (REQUEST.getINSTRUCTORS().isEmpty()) {
         System.out.println("No instructors available to remove.");
         return;
      }

      String instructorId = Menu.get("Enter instructor ID: ");
      Instructor instructor = REQUEST.findInstructor(instructorId);
      if (instructor != null) {
         if (REQUEST.removeInstructor(instructor)) {
            System.out.println("Instructor removed successfully.");
         } else {
            System.out.println("Failed to remove instructor.");
         }
      } else {
         System.out.println("Instructor not found.");
      }
   }

   /**
    * Edits the assigned courses for the instructor (assign or remove).
    */
   private static void editAssignedCourses(Instructor instructor) {
      while (true) {
         Menu.editAssignedCoursesMenu();
         int choice = Menu.getChoice(2);
         switch (choice) {
            case 0:
               return;
            case 1:
               assignCourseToInstructor(instructor);
               break;
            default:
               removeCourseFromInstructor(instructor);
               break;
         }
      }
   }

   /**
    * Assigns a course to the instructor.
    */
   private static void assignCourseToInstructor(Instructor instructor) {
      String courseId = Menu.get("Enter course ID to assign to instructor: ");
      if (courseId.isEmpty()) {
         System.out.println("Course ID cannot be empty.");
         return;
      }

      Course course = REQUEST.findCourse(courseId);
      if (course == null) {
         System.out.println("Course not found.");
         return;
      }
      instructor.assignCourse(course);
      System.out.println("Course assigned to instructor.");
   }

   /**
    * Removes a course from the instructor.
    */
   private static void removeCourseFromInstructor(Instructor instructor) {
      String courseId = Menu.get("Enter course ID to remove from instructor: ");
      if (courseId.isEmpty()) {
         System.out.println("Course ID cannot be empty.");
         return;
      }

      Course course = REQUEST.findCourse(courseId);
      if (course == null) {
         System.out.println("Course not found.");
         return;
      }

      instructor.getASSIGNED_COURSES().remove(course);
      System.out.println("Course removed from instructor.");
   }
}