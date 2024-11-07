public class UI {
    private static final Coordinator REQUEST = new Coordinator();

    public static void main(String[] args) {
        UI ui = new UI();
        ui.run();
    }

    public static Coordinator getCoordinator() {
        return REQUEST;
    }

    public void run() {
        int choice;
        System.out.println("Welcome to the Timetable Scheduling Tool!");

        while (true) {
            // Display the main menu and get the user's choice
            Menu.displayMenu();
            choice = Menu.getChoice(5);

            switch (choice) {
                case 0:
                    System.out.println("Exiting the program. Goodbye!");
                    return;
                case 1:
                    ManageCourse.manageCourse();  // Manage courses
                    break;
                case 2:
                    ManageInstructor.manageInstructor();  // Manage instructors
                    break;
                case 3:
                    ManageGroups.manageGroups();  // Manage groups
                    break;
                case 4:
                    ManageSchedule.displaySchedule();  // Display schedule
                    break;
                case 5:
                    ManageSaves.editFile();  // Save/load schedule
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
    }
}