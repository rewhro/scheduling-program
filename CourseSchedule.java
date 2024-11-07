import java.time.DayOfWeek;

public class CourseSchedule {
   public Courses courses;                            // Collection of all courses in the schedule
   protected final DayOfWeek[] daysOfWeek = DayOfWeek.values(); // Array of days in a week

   /**
    * Default constructor initializing an empty Courses object.
    */
   public CourseSchedule() {
      courses = new Courses();
   }

   /**
    * Constructor initializing CourseSchedule with an existing Courses object.
    *
    * @param courses The Courses object containing initial list of courses
    */
   public CourseSchedule(Courses courses) {
      this.courses = courses;
   }

   /**
    * Adds a new course to the schedule, checking for any conflicts with existing courses.
    *
    * @param newCourse The course to add
    */
   public void addCourse(Course newCourse) {
      System.out.println("Adding course: " + newCourse.getCourseId() + "...");
      Course conflict = isConflict(newCourse);

      if (conflict != null) {
         System.out.println("Conflict found with " + conflict.getCourseId() + ", not added.");
      } else {
         courses.addCourse(newCourse);
         System.out.println("Added successfully.");
      }
   }

   /**
    * Removes a course from the schedule.
    *
    * @param course The course to remove
    */
   public void removeCourse(Course course) {
      courses.removeCourse(course);
   }

   /**
    * Checks for conflicts between the specified course and the existing courses in the schedule.
    * A conflict exists if any time slot of the new course overlaps with time slots of an existing course.
    *
    * @param newCourse The course to check for conflicts
    * @return The conflicting Course if found, otherwise null
    */
   public Course isConflict(Course newCourse) {
      if (newCourse == null) {
         return null;
      }

      // Iterate over all existing courses
      for (Course course : this.courses.getAllCourses()) {
         // Check each time slot of the new course
         for (TimeSlot newTs : newCourse.getTimeSlots().getAllTimeSlots()) {
            // Check for a conflict with the current course's time slots
            for (TimeSlot existingTs : course.getTimeSlots().getAllTimeSlots()) {
               if (newTs.isEqual(existingTs)) {
                  return course;
               }
            }
         }
      }
      return null; // No conflicts found
   }

   /**
    * Displays the full course schedule organized by day of the week.
    */
   public void displaySchedule() {
      System.out.println("\nCOURSE SCHEDULE\n*************************************************");
      for (DayOfWeek day : daysOfWeek) {
         displayDay(day);
      }
      System.out.println("*************************************************");
   }

   /**
    * Displays all courses scheduled on a specific day.
    *
    * @param day The day of the week to display courses for
    */
   protected void displayDay(DayOfWeek day) {
      System.out.println(day + ":");

      for (Course course : courses.getAllCourses()) {
         printCourse(course, day);
      }
      System.out.println();
   }

   /**
    * Prints details of a course's time slots that occur on a specific day.
    *
    * @param course The course to display
    * @param day    The day of the week for which to display time slots
    */
   protected void printCourse(Course course, DayOfWeek day) {
      // Get all time slots for the course
      for (TimeSlot timeSlot : course.getTimeSlots().getAllTimeSlots()) {
         // Check if the time slot corresponds with the given day
         if (timeSlot.getDay().equals(day)) {
            // Print the course details for the matching time slot
            if (course.getInstructor() != null) {
               System.out.format("%s, %s, %s-%s\n",
                     course.getCourseId(),
                     course.getInstructor().getName(),
                     timeSlot.getStartTime(),
                     timeSlot.getEndTime());
            } else {
               System.out.format("%s, %s, %s-%s\n",
                     course.getCourseId(),
                     "Unassigned",
                     timeSlot.getStartTime(),
                     timeSlot.getEndTime());
            }
         }
      }
   }
}