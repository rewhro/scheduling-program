import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

public class Courses {
   private final ArrayList<Course> allCourses; // List of all courses

   /**
    * Constructor to initialize the Courses object with an empty course list.
    */
   public Courses() {
      this.allCourses = new ArrayList<>();
   }

   /**
    * Adds a course to the list of courses.
    *
    * @param course The course to add
    */
   public void addCourse(Course course) {
      allCourses.add(course);
   }

   /**
    * Removes a specified course from the list.
    *
    * @param course The course to remove
    */
   public void removeCourse(Course course) {
      allCourses.remove(course);
   }

   /**
    * Retrieves the list of all courses.
    *
    * @return The list of all courses
    */
   public ArrayList<Course> getAllCourses() {
      return allCourses;
   }

   /**
    * Finds a course by its course ID.
    *
    * @param courseId The ID of the course to find
    * @return The Course object if found, otherwise null
    */
   public Course findCourseById(String courseId) {
      for (Course course : allCourses) {
         if (course.getCourseId().equals(courseId)) {
            return course;
         }
      }
      return null;
   }

   /**
    * Checks if the course list is empty.
    *
    * @return true if no courses are in the list, false otherwise
    */
   public boolean isEmpty() {
      return allCourses.isEmpty();
   }

   /**
    * Saves all courses to a specified text file. Each course is saved on a new line.
    *
    * @param filePath The path to the file where courses will be saved
    */
   public void saveAllCourses(String filePath) {
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
         for (Course course : allCourses) {
            writer.write(course.toString());
            writer.newLine();
         }
         //System.out.println("All courses saved to file.");
      } catch (IOException e) {
         System.out.println("An error occurred while saving courses.");
      }
   }

   /**
    * Parses a string to create a TimeSlot object. Expected format: "DAY-START-END".
    *
    * @param timeSlotString A string representing a time slot (e.g., "MONDAY-09:00-10:00")
    * @return The TimeSlot object created from the string
    */
   private TimeSlot fromString(String timeSlotString) {
      String[] parts = timeSlotString.split("-");
      DayOfWeek day = DayOfWeek.valueOf(parts[0].toUpperCase()); // Parse day
      LocalTime startTime = LocalTime.parse(parts[1]);           // Parse start time
      LocalTime endTime = LocalTime.parse(parts[2]);             // Parse end time
      return new TimeSlot(day, startTime, endTime);
   }

   /**
    * Loads all courses from a specified text file, clearing the current list.
    * Each line in the file represents a course and its time slots.
    *
    * @param filePath The path to the file from which courses will be loaded
    * @return true if the file was loaded successfully, false otherwise
    */
   public boolean loadAllCourses(String filePath) {
      boolean success = true;
      allCourses.clear(); // Clear current list before loading

      try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
         String line;
         while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            String courseId = data[0];
            String courseName = data[1];
            TimeSlot timeSlot = fromString(data[2]); // Parse first time slot
            Course course = new Course(courseId, courseName, timeSlot);

            // Add additional time slots if available
            for (int i = 3; i < data.length; i++) {
               course.addTimeSlot(fromString(data[i]));
            }

            allCourses.add(course); // Add course to the list
         }
         //System.out.println("All courses loaded from file.");
      } catch (IOException ioe) {
         success = false;
         System.out.println("An error occurred while loading courses.");
      }
      return success;
   }
}
