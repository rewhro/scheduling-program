import java.io.*;
import java.util.ArrayList;

public class Groups {
   private final ArrayList<Group> GROUPS; // List of all groups

   /**
    * Constructs a Groups object with an empty list of groups.
    */
   public Groups() {
      this.GROUPS = new ArrayList<>();
   }

   /**
    * Adds a group to the list of groups.
    *
    * @param group The Group to add
    */
   public void addGroup(Group group) {
      GROUPS.add(group);
   }

   /**
    * Finds a group by its name.
    *
    * @param name The name of the group to find
    * @return The Group if found, otherwise null
    */
   public Group findGroupByName(String name) {
      for (Group group : GROUPS) {
         if (group.getGroupName().equals(name)) {
            return group;
         }
      }
      return null;
   }

   /**
    * Saves all groups and their associated courses to a file.
    *
    * @param filePath The file path where groups will be saved
    */
   public void save(String filePath) {
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
         for (Group group : GROUPS) {
            writer.write(group.getGroupName()); // Write group name
            writer.newLine();
            for (Course course : group.getCourses()) {
               writer.write(course.getCourseId()); // Write course IDs under each group
               writer.newLine();
            }
            writer.write("---"); // Separator for groups
            writer.newLine();
         }
         //System.out.println("All groups saved to file.");
      } catch (IOException e) {
         System.out.println("An error occurred while saving groups.");
         // e.printStackTrace();
      }
   }

   /**
    * Loads groups and their courses from a file.
    * Expects each group followed by its course IDs and separated by "---".
    *
    * @param filePath The file path from which groups will be loaded
    * @param courses The Courses object to find Course objects by ID
    * @return true if loaded successfully, false otherwise
    */
   public boolean load(String filePath, Courses courses) {
      if (!new File(filePath).exists()) {
         System.out.println("File " + filePath + " does not exist.");
         return false;
      }

      try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
         String line;
         Group group = null;
         while ((line = reader.readLine()) != null) {
            if (line.equals("---")) {
               // End of current group, add to list
               if (group != null) {
                  GROUPS.add(group);
               }
               group = null; // Reset for next group
            } else if (group == null) {
               // New group begins
               group = new Group(line);
            } else {
               // Adding courses to the current group
               Course course = courses.findCourseById(line);
               if (course != null) {
                  group.addCourse(course);
               } else {
                  System.out.println("Course ID " + line + " not found in provided courses.");
               }
            }
         }
         // Add the last group if it was not added
         if (group != null) {
            GROUPS.add(group);
         }
         //System.out.println("Groups loaded from file.");
      } catch (IOException e) {
         System.out.println("An error occurred while loading groups.");
      }

      return true;
   }

   /**
    * Removes a group from the list of groups.
    *
    * @param group The Group to remove
    */
   public void remove(Group group) {
      GROUPS.remove(group);
   }

   /**
    * Checks if the list of groups is empty.
    *
    * @return true if there are no groups, false otherwise
    */
   public boolean isEmpty() {
      return GROUPS.isEmpty();
   }
}