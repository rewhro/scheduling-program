TIMETABLE SCHEDULING TOOL
Description: The Timetable Scheduling Tool is a comprehensive application designed to help educational institutions efficiently manage and organize their courses, instructors, and student groups. The tool allows administrators to create, edit, and delete courses, assign instructors to courses, and manage student groups. Additionally, it enables the generation of course schedules and group-specific schedules, ensuring that there are no conflicts between class times.

Key features of the application include:
- Course Management: Create, edit, and delete courses. Assign time slots to each course and ensure there are no conflicts in the timetable.
- Instructor Management: Add, remove, and edit instructor details. Assign instructors to specific courses.
- Group Management: Create and manage student groups, assign courses to groups, and display schedules for individual groups.
- Scheduling: Automatically generate and display course schedules for all courses and group-specific schedules based on assigned courses.
- Save/Load Functionality: The tool allows for saving and loading course, instructor, and group data, enabling persistent storage of schedules across sessions.
- Conflict Detection: The application checks for scheduling conflicts when adding time slots to courses and ensures that instructors or groups are not double-booked.

Technologies Used:
- Java programming language
- Object-Oriented Design (OOD)
- File handling for saving/loading schedules
- This tool is particularly useful for universities, colleges, and training centers where multiple courses are scheduled across different time slots, and where multiple instructors are managing various groups of students. The software ensures a streamlined process for managing these entities and helps maintain an organized and conflict-free schedule.


****
HOW TO RUN:
- Download and Set Up:
Ensure you have Java JDK installed on your system.
Download or clone the project directory to your local machine.
Navigate to the directory where the program is located using the terminal/command prompt.

- Compiling the Code:
Open a terminal/command prompt in the project folder.
Compile the program by running: javac *.java
This will compile all Java files in the project.

- Running the Program:
After compiling the code, run the program with the following command: java UI
This will start the application and present the main menu.


****
USING THE APPLICATION:
Once the program is running, you will see a main menu displayed on the screen with several options. You can navigate through these options by entering the corresponding number.

Main Menu:
0: Exit the program.
1: Manage courses.
2: Manage instructors.
3: Manage student groups.
4: Display course/group schedules.
5: Save/load course, instructor, and group data.

- Managing Courses (Option 1):
You can create new courses, edit existing ones, or delete them.
You can assign time slots to courses to organize the schedule.
Courses are displayed in a schedule view to ensure no conflicts occur.

- Managing Instructors (Option 2):
You can create new instructors, edit their details, or remove them.
You can assign instructors to courses they will be teaching.

- Managing Student Groups (Option 3):
Create and manage student groups.
Assign courses to groups and generate schedules for them.

- Displaying Schedules (Option 4):
View the schedules of either individual courses or specific student groups.

- Save/Load Schedule (Option 5):
You can save your current course, instructor, and group data to a file for future sessions.
You can also load existing data from saved files to restore the previous state.

- Exit (Option 0):
Select 0 to exit the application when you're done.


****
Saving and loading data:
The program uses text files (courses.txt, instructors.txt, groups.txt) to store all course, instructor, and group data.
After making changes, you can choose to save the data, and it will be written to these text files.
If you want to resume where you left off, simply load the data using the Load option.

Error Handling:
If you try to create a course or group with conflicting time slots, the program will alert you to the conflict.
If no save files are found, the program will notify you and prompt you to either create new data or load existing ones.

Exiting the Program:
When you're finished, simply choose 0 from the main menu to exit the program.
