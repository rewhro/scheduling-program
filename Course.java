public class Course {
   private String courseId;          // Unique identifier for the course (e.g., "CS101")
   private String courseName;        // Name of the course (e.g., "Introduction to Computer Science")
   private Instructor instructor;    // Instructor associated with the course
   private final TimeSlots timeSlots; // List of time slots assigned to this course

   /**
    * Constructs a Course object with the specified course ID, course name, and initial time slot.
    *
    * @param courseId   The unique identifier of the course
    * @param courseName The name of the course
    * @param timeSlot   The initial time slot for the course
    */
   public Course(String courseId, String courseName, TimeSlot timeSlot) {
      this.courseId = courseId;
      this.courseName = courseName;
      this.timeSlots = new TimeSlots();
      this.timeSlots.addTimeSlot(timeSlot); // Add initial time slot
   }

   /**
    * Returns the course ID.
    *
    * @return The course ID
    */
   public String getCourseId() {
      return courseId;
   }

   /**
    * Returns the name of the course.
    *
    * @return The course name
    */
   public String getCourseName() {
      return courseName;
   }

   /**
    * Returns the instructor of the course.
    *
    * @return The instructor of the course, or null if none is assigned
    */
   public Instructor getInstructor() {
      return instructor;
   }

   /**
    * Returns the time slots assigned to this course.
    *
    * @return The TimeSlots object containing all time slots for this course
    */
   public TimeSlots getTimeSlots() {
      return timeSlots;
   }

   /**
    * Sets the course ID to a new value.
    *
    * @param courseId The new course ID
    */
   public void setCourseId(String courseId) {
      this.courseId = courseId;
   }

   /**
    * Sets the course name to a new value.
    *
    * @param newCourseName The new name for the course
    */
   public void setCourseName(String newCourseName) {
      courseName = newCourseName;
   }

   /**
    * Adds a new time slot to the course if it doesn't already exist.
    *
    * @param timeSlot The time slot to add
    */
   public void addTimeSlot(TimeSlot timeSlot) {
      if (!this.timeSlots.addTimeSlot(timeSlot)) {
         System.out.println("Time slot already exists"); // Message if time slot is a duplicate
      } else {
         System.out.println("Time slot added"); // Message if time slot is successfully added
      }
   }

   /**
    * Sets the instructor for the course.
    *
    * @param instructor The instructor to assign to this course
    */
   public void setInstructor(Instructor instructor) {
      this.instructor = instructor;
   }

   /**
    * Returns a string representation of the course, including course ID, name, and all time slots.
    * Format: courseId,courseName,day,startTime,endTime,...
    *
    * @return A formatted string representing the course details
    */
   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(courseId).append(",").append(courseName);

      // Append each TimeSlot in the format day,startTime,endTime
      for (TimeSlot timeSlot : timeSlots.getAllTimeSlots()) {
         sb.append(",").append(timeSlot.toString());
      }

      return sb.toString();
   }
}
