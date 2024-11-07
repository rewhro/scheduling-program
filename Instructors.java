import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

public class Instructors {
   private final ArrayList<Instructor> ALL_INSTRUCTORS;  // List to store all instructors
   public static Instructors instructors = new Instructors(); // Singleton instance of Instructors

   /**
    * Constructs an Instructors object and initializes the list of instructors.
    */
   public Instructors() {
      this.ALL_INSTRUCTORS = new ArrayList<>();
   }

   /**
    * Adds an instructor to the list of all instructors.
    *
    * @param instructor The Instructor object to be added
    */
   public void addInstructor(Instructor instructor) {
      ALL_INSTRUCTORS.add(instructor);
   }

   /**
    * Removes an instructor from the list of all instructors.
    *
    * @param instructor The Instructor object to be removed
    */
   public void removeInstructor(Instructor instructor) {
      ALL_INSTRUCTORS.remove(instructor);
   }

   /**
    * Retrieves the list of all instructors.
    *
    * @return A list of all Instructor objects
    */
   public ArrayList<Instructor> getALL_INSTRUCTORS() {
      return ALL_INSTRUCTORS;
   }

   /**
    * Finds an instructor by their unique ID.
    *
    * @param instructorId The ID of the instructor to find
    * @return The Instructor object if found, or null if not found
    */
   public Instructor findInstructorById(String instructorId) {
      for (Instructor instructor : ALL_INSTRUCTORS) {
         if (instructor.getId().equals(instructorId)) {
            return instructor;
         }
      }
      return null;  // Instructor not found
   }

   /**
    * Saves the list of all instructors to a text file.
    *
    * @param filePath The file path where the instructors will be saved
    */
   public void saveAllInstructors(String filePath) {
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
         for (Instructor instructor : ALL_INSTRUCTORS) {
            writer.write(instructor.toString());
            writer.newLine();  // Write each instructor's details to a new line
         }
         //System.out.println("All instructors saved to file.");
      } catch (IOException e) {
         System.out.println("An error occurred while saving instructors.");
      }
   }

   /**
    * Loads all instructors from a text file.
    * The method parses instructor data, including their availability and assigned courses.
    *
    * @param filePath The file path from which to load instructors
    * @param courses The Courses object to find courses by their IDs
    * @return True if instructors were successfully loaded, false otherwise
    */
   public boolean loadAllInstructors(String filePath, Courses courses) {
      boolean success = true;
      ALL_INSTRUCTORS.clear();  // Clear existing instructors before loading new ones
      try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
         String line;
         while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            String name = data[0];
            String id = data[1];
            Instructor instructor = new Instructor(name, id);

            int i = 2;

            // Parse availability time slots
            while (i < data.length && data[i].matches("[A-Z]+")) {  // Check if data[i] is a day of the week
               DayOfWeek day = DayOfWeek.valueOf(data[i]);
               LocalTime startTime = LocalTime.parse(data[i + 1]);
               LocalTime endTime = LocalTime.parse(data[i + 2]);
               instructor.addAvailability(new TimeSlot(day, startTime, endTime));
               i += 3;  // Move to the next time slot
            }

            // Parse assigned courses by their IDs
            while (i < data.length) {
               String courseId = data[i];
               Course course = courses.findCourseById(courseId);
               if (course != null) {
                  instructor.assignCourse(course);
               }
               i++;
            }

            ALL_INSTRUCTORS.add(instructor);  // Add the instructor to the list
         }
         //System.out.println("All instructors loaded from file.");
      } catch (IOException e) {
         // Handle file read error
         success = false;
      }
      return success;
   }

   /**
    * Checks if the list of instructors is empty.
    *
    * @return True if the list is empty, false otherwise
    */
   public boolean isEmpty() {
      return ALL_INSTRUCTORS.isEmpty();
   }
}