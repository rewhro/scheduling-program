import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class ManageTimeSlot {
   private static final Coordinator REQUEST = UI.getCoordinator();

   /**
    * Prompts the user to create a new time slot for a course.
    * @param course The course for which the time slot is being created.
    * @return A newly created TimeSlot or null if the time slot could not be created.
    */
   public static TimeSlot createTimeSlot(Course course) {
      DayOfWeek day = getDayOfWeek();
      LocalTime startTime = getTime("Enter start time (HH:MM): ");
      LocalTime endTime = getTime("Enter end time (HH:MM): ");

      if (startTime != null && endTime != null && !isTimeSlotConflict(course)) {
         return new TimeSlot(day, startTime, endTime);
      }
      return null;
   }

   /**
    * Adds a time slot to a course after validating and checking for conflicts.
    */
   public static void addTimeSlot() {
      String courseId = Menu.get("Enter course ID: ").toUpperCase();
      Course course = REQUEST.getCOURSES().findCourseById(courseId);

      if (course == null) {
         System.out.println("Course " + courseId + " does not exist.");
         return;
      }

      TimeSlot newTimeSlot = createTimeSlot(course);
      if (newTimeSlot != null) {
         course.addTimeSlot(newTimeSlot);
         System.out.println("Time slot added successfully.");
      }
   }

   /**
    * Prompts the user to input a valid day of the week.
    * @return A valid DayOfWeek enum value or null if invalid input.
    */
   private static DayOfWeek getDayOfWeek() {
      DayOfWeek day = null;
      boolean valid;
      do {
         valid = true;
         try {
            day = DayOfWeek.valueOf(Menu.get("Enter day of the week in full (e.g., monday): ").toUpperCase());
         } catch (IllegalArgumentException iae) {
            valid = false;
            System.out.println("Invalid input, please try again.");
         }
      } while (!valid);
      return day;
   }

   /**
    * Prompts the user to input a valid time in HH:MM format.
    * @param prompt The prompt message to show to the user.
    * @return A LocalTime object representing the entered time or null if invalid input.
    */
   private static LocalTime getTime(String prompt) {
      LocalTime time = null;
      boolean valid;
      do {
         valid = true;
         try {
            time = LocalTime.parse(Menu.get(prompt));
         } catch (DateTimeParseException dtpe) {
            valid = false;
            System.out.println("Invalid input, please try again.");
         }
      } while (!valid);
      return time;
   }

   /**
    * Checks if there is a conflict with the existing course schedule for the new time slot.
    * @param course The course to check against.
    * @return True if there is a conflict, false otherwise.
    */
   private static boolean isTimeSlotConflict(Course course) {
      Course conflict = REQUEST.getCOURSE_SCHEDULE().isConflict(course);
      if (conflict != null) {
         System.out.println("Timeslot conflicting with " + conflict.getCourseId() + ", not added.");
         return true;
      }
      return false;
   }
}
