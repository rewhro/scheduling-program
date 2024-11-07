import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

/*
 * testing schedule

public class Driver {
   public static void main(String[] args) {
      CourseSchedule schedule = new CourseSchedule();
      GroupSchedule groupSchedule;
      if (schedule.courses.loadAllCourses("courses.txt")) {
         if (Instructors.instructors.loadAllInstructors("instructors.txt", schedule.courses)) {
            for (Instructor instructor : Instructors.instructors.getALL_INSTRUCTORS()) {
               ArrayList<Course> coursesToAssign = new ArrayList<>(instructor.getASSIGNED_COURSES()); // Create a copy of the list

               for (Course course : coursesToAssign) {
                  assignInstructorToCourseById(instructor, schedule.courses, course.getCourseId());
               }
            }
         }
         Group group = Group.load("group.txt", schedule.courses);
         schedule.displaySchedule();
         groupSchedule = new GroupSchedule(group);
         groupSchedule.displaySchedule();
         return;
      }

      // defining a group
      Group group = new Group("cs");

      // defining instructors
      Instructor instructor = new Instructor("J. Tasse", "12345");
      Instructor instructor2 = new Instructor("J. McNally", "98765");
      Instructor instructor3 = new Instructor("Dr. Neil McKay", "72892");

      // defining time slots
      TimeSlot ts1 = new TimeSlot(DayOfWeek.MONDAY, LocalTime.of(14, 0), LocalTime.of(15, 50));
      TimeSlot ts2 = new TimeSlot(DayOfWeek.WEDNESDAY, LocalTime.of(13, 30), LocalTime.of(14, 20));
      TimeSlot ts3 = new TimeSlot(DayOfWeek.TUESDAY, LocalTime.of(12, 0), LocalTime.of(13, 50));
      TimeSlot ts4 = new TimeSlot(DayOfWeek.TUESDAY, LocalTime.of(14, 0), LocalTime.of(15, 50));
      TimeSlot ts5 = new TimeSlot(DayOfWeek.MONDAY, LocalTime.of(11, 30), LocalTime.of(12, 20));
      TimeSlot ts6 = new TimeSlot(DayOfWeek.WEDNESDAY, LocalTime.of(11, 30), LocalTime.of(12, 20));
      TimeSlot ts7 = new TimeSlot(DayOfWeek.FRIDAY, LocalTime.of(11, 30), LocalTime.of(12, 20));

      // defining courses
      Course course = new Course("CS2043", "Software Engineering 1", ts1);
      Course course2 = new Course("CS2263", "...", ts3);
      Course course3 = new Course("MATH1003", "Intro to Calculus I", ts5);

      // defining a course schedule
      schedule = new CourseSchedule();

      // defining group schedules
      groupSchedule = new GroupSchedule(group);

      // adding timeslots
      course.addTimeSlot(ts2);
      course.addTimeSlot(ts4);

      course3.addTimeSlot(ts6);
      course3.addTimeSlot(ts7);

      // setting groups
      group.addCourse(course);
      group.addCourse(course2);

      // setting instructor
      course.setInstructor(instructor);
      course2.setInstructor(instructor2);
      instructor.assignCourse(course);
      instructor2.assignCourse(course2);
      instructor3.assignCourse(course3);

      // adding a course
      schedule.addCourse(course);

      // displaying course schedule
      schedule.displaySchedule();

      // adding other courses and displaying schedule
      schedule.addCourse(course2);
      schedule.addCourse(course3);
      schedule.displaySchedule();

      // displaying group schedules
      groupSchedule.displaySchedule();

      schedule.courses.saveAllCourses("courses.txt");

      Instructors instructors = new Instructors();
      instructors.addInstructor(instructor);
      instructors.addInstructor(instructor2);
      instructors.addInstructor(instructor3);

      group.save("group.txt");

      instructors.saveAllInstructors("instructors.txt");
   }

   public static void assignInstructorToCourseById(Instructor instructor, Courses courses, String courseId) {
      // Find the course and instructor by their IDs
      Course course = courses.findCourseById(courseId);;

      // Check if the course exists
      if (course != null) {
         // Assign the instructor to the course
         course.setInstructor(instructor);

         // Also assign the course to the instructor
         instructor.assignCourse(course);

         System.out.println("Instructor " + instructor.getName() + " assigned to course " + course.getCourseName() + " (" + course.getCourseId() + ").");
      } else {
         System.out.println("Save file corrupted.");
      }
   }
}
*/