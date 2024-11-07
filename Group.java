import java.io.*;
import java.util.ArrayList;

public class Group {
   private String name;                        // Name of the group (e.g., "Computer Science Department")
   private final ArrayList<Course> courses;    // List of courses that belong to this group

   /**
    * Constructs a Group with the specified name and an empty course list.
    *
    * @param name The name of the group
    */
   public Group(String name) {
      this.name = name;
      this.courses = new ArrayList<>();
   }

   /**
    * Retrieves the name of the group.
    *
    * @return The name of the group
    */
   public String getGroupName() {
      return name;
   }

   /**
    * Updates the name of the group.
    *
    * @param newName The new name for the group
    */
   public void updateName(String newName) {
      this.name = newName;
   }

   /**
    * Retrieves the list of courses in this group.
    *
    * @return An ArrayList of courses belonging to this group
    */
   public ArrayList<Course> getCourses() {
      return courses;
   }

   /**
    * Adds a course to this group's course list.
    *
    * @param course The course to add
    */
   public void addCourse(Course course) {
      this.courses.add(course);
   }
}
