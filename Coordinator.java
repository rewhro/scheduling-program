import java.util.ArrayList;

public class Coordinator {
    // variables
    private final Courses COURSES = new Courses();
    private final Groups GROUPS = new Groups();
    private final CourseSchedule COURSE_SCHEDULE = new CourseSchedule(getCOURSES());
    private final Instructors INSTRUCTORS = new Instructors();

    // schedule display
    public void displayGroupSchedule(String groupName) {
        Group group = findGroup(groupName);
        if (group != null) {
            GroupSchedule groupSchedule = new GroupSchedule(group);
            groupSchedule.displaySchedule();
        } else {
            System.out.println("Group does not exist.");
        }
    }

    public void displayCourseSchedule() {
        COURSE_SCHEDULE.displaySchedule();
    }

    // getter methods
    public Courses getCOURSES() {
        return COURSES;
    }

    public Instructors getINSTRUCTORS() {
        return INSTRUCTORS;
    }

    public Groups getGROUPS() {
        return GROUPS;
    }

    public CourseSchedule getCOURSE_SCHEDULE() {
        return COURSE_SCHEDULE;
    }

    // search
    public Course findCourse(String courseId) {
        return COURSES.findCourseById(courseId);
    }

    public Instructor findInstructor(String instructorId) {
        return INSTRUCTORS.findInstructorById(instructorId);
    }

    public Group findGroup(String groupName) {
        return GROUPS.findGroupByName(groupName);
    }

    // maker methods
    public void createCourse(String courseId, String courseName, TimeSlot timeSlot) {
        if (COURSES.findCourseById(courseId) != null) {
            System.out.println("Course with ID " + courseId + " already exists.");
            return;
        }
        Course course = new Course(courseId, courseName, timeSlot);
        System.out.println("Course created: " + courseId + " - " + courseName);
        COURSE_SCHEDULE.addCourse(course);
    }

    // course data management
    public boolean changeCourseID(Course course, String newCourseId) {
        if (course != null && !COURSES.findCourseById(newCourseId).equals(course)) {
            course.setCourseId(newCourseId);
            return true;
        }
        System.out.println("Invalid course or duplicate course ID.");
        return false;
    }

    public boolean changeCourseName(Course course, String newCourseName) {
        if (course != null) {
            course.setCourseName(newCourseName);
            return true;
        }
        System.out.println("Invalid course.");
        return false;
    }

    public boolean deleteCourse(Course course) {
        if (course != null) {
            COURSE_SCHEDULE.removeCourse(course);
            return true;
        }
        System.out.println("Course not found.");
        return false;
    }

    // instructor data management
    public boolean changeInstructorId(Instructor instructor, String newId) {
        if (instructor != null && INSTRUCTORS.findInstructorById(newId) == null) {
            instructor.setId(newId);
            return true;
        }
        System.out.println("Instructor not found or ID already in use.");
        return false;
    }

    public boolean changeInstructorName(Instructor instructor, String newName) {
        if (instructor != null) {
            instructor.setName(newName);
            return true;
        }
        System.out.println("Instructor not found.");
        return false;
    }

    public void addInstructor(Instructor instructor) {
        INSTRUCTORS.addInstructor(instructor);
    }

    public boolean removeInstructor(Instructor instructor) {
        if (instructor != null) {
            INSTRUCTORS.removeInstructor(instructor);
            return true;
        }
        System.out.println("Instructor not found.");
        return false;
    }

    private void assignInstructorToCourseById(Instructor instructor, String courseId) {
        Course course = COURSES.findCourseById(courseId);
        if (course != null && instructor != null) {
            course.setInstructor(instructor);
            instructor.assignCourse(course);
        } else {
            System.out.println("Error assigning instructor to course.");
        }
    }

    // save file management
    public void saveCourses() {
        COURSES.saveAllCourses("courses.txt");
    }

    public void saveInstructors() {
        INSTRUCTORS.saveAllInstructors("instructors.txt");
    }

    public void saveGroups() {
        GROUPS.save("groups.txt");
    }

    public boolean loadCourses() {
        return COURSES.loadAllCourses("courses.txt");
    }

    public boolean loadInstructors() {
        if (INSTRUCTORS.loadAllInstructors("instructors.txt", COURSES)) {
            for (Instructor instructor : INSTRUCTORS.getALL_INSTRUCTORS()) {
                ArrayList<Course> coursesToAssign = new ArrayList<>(instructor.getASSIGNED_COURSES());
                for (Course course : coursesToAssign) {
                    assignInstructorToCourseById(instructor, course.getCourseId());
                }
            }
        } else {
            return false;
        }
        return true;
    }

    public void loadGroups() {
        GROUPS.load("groups.txt", COURSES);
    }

    public boolean clearSaves() {
        return ManageSaves.deleteFile("courses.txt") &&
              ManageSaves.deleteFile("instructors.txt") &&
              ManageSaves.deleteFile("groups.txt");
    }
}