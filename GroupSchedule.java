import java.time.DayOfWeek;

public class GroupSchedule extends CourseSchedule {
   private final Group GROUP;  // The group for which this schedule is created

   /**
    * Constructs a GroupSchedule for the specified group.
    *
    * @param group The Group object representing the group to schedule
    */
   public GroupSchedule(Group group) {
      this.GROUP = group;
   }

   /**
    * Displays the full schedule for the group, including courses and their time slots.
    * This method prints the schedule for each day of the week.
    */
   public void displaySchedule() {
      System.out.format("\n%s SCHEDULE\n*************************************************\n", GROUP.getGroupName().toUpperCase());
      for (DayOfWeek day : daysOfWeek) {
         this.displayDay(day); // Display schedule for each day of the week
      }
      System.out.println("*************************************************");
   }

   /**
    * Displays the courses for a specific day of the week.
    *
    * @param day The day of the week for which the schedule should be displayed
    */
   protected void displayDay(DayOfWeek day) {
      System.out.println(day + ":");

      // Iterate through the courses assigned to the group
      for (Course course : GROUP.getCourses()) {
         // Print the time slots for each course on the given day
         printCourse(course, day);
      }
      System.out.println();
   }
}