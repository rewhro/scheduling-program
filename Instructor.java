import java.util.ArrayList;

public class Instructor {
   private String name;                    // Instructor's name
   private String id;                      // Instructor's ID
   private final ArrayList<TimeSlot> availability;  // List of time slots the instructor is available
   private final ArrayList<Course> ASSIGNED_COURSES; // List of courses assigned to the instructor

   /**
    * Constructs an Instructor object with the specified name and ID.
    * Initializes the availability and assigned courses lists.
    *
    * @param name The name of the instructor
    * @param id The ID of the instructor
    */
   public Instructor(String name, String id) {
      this.name = name;
      this.id = id;
      this.availability = new ArrayList<>();
      this.ASSIGNED_COURSES = new ArrayList<>();
   }

   /**
    * Retrieves the name of the instructor.
    *
    * @return The name of the instructor
    */
   public String getName() {
      return name;
   }

   /**
    * Retrieves the ID of the instructor.
    *
    * @return The ID of the instructor
    */
   public String getId() {
      return id;
   }

   /**
    * Retrieves the list of time slots when the instructor is available.
    *
    * @return A list of TimeSlot objects indicating the instructor's availability
    */
   public ArrayList<TimeSlot> getAvailability() {
      return availability;
   }

   /**
    * Retrieves the list of courses assigned to the instructor.
    *
    * @return A list of Course objects assigned to the instructor
    */
   public ArrayList<Course> getASSIGNED_COURSES() {
      return ASSIGNED_COURSES;
   }

   /**
    * Sets the name of the instructor.
    *
    * @param name The new name of the instructor
    */
   public void setName(String name) {
      this.name = name;
   }

   /**
    * Sets the ID of the instructor.
    *
    * @param id The new ID of the instructor
    */
   public void setId(String id) {
      this.id = id;
   }

   /**
    * Adds a time slot to the instructor's availability.
    *
    * @param timeSlot The TimeSlot object to be added to the availability list
    */
   public void addAvailability(TimeSlot timeSlot) {
      this.availability.add(timeSlot);
   }

   /**
    * Assigns a course to the instructor.
    *
    * @param course The Course object to be assigned to the instructor
    */
   public void assignCourse(Course course) {
      this.ASSIGNED_COURSES.add(course);
   }

   /**
    * Returns a string representation of the instructor, including their name, ID,
    * availability time slots, and assigned courses.
    *
    * @return A formatted string representing the instructor's details
    */
   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(name).append(",").append(id);

      // Append each TimeSlot in the format day,startTime,endTime
      for (TimeSlot timeSlot : availability) {
         sb.append(",").append(timeSlot.toString());
      }

      // Append each assigned course's ID
      for (Course course : ASSIGNED_COURSES) {
         sb.append(",").append(course.getCourseId());
      }
      return sb.toString();
   }
}
