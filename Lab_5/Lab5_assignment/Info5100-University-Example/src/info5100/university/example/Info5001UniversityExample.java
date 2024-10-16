
package info5100.university.example;

import info5100.university.example.CourseCatalog.Course;
import info5100.university.example.CourseCatalog.CourseCatalog;
import info5100.university.example.CourseSchedule.CourseLoad;
import info5100.university.example.CourseSchedule.CourseOffer;
import info5100.university.example.CourseSchedule.CourseSchedule;
import info5100.university.example.CourseSchedule.SeatAssignment;
import info5100.university.example.Department.Department;
import info5100.university.example.Persona.Faculty.FacultyProfile;
import info5100.university.example.Persona.Person;
import info5100.university.example.Persona.PersonDirectory;
import info5100.university.example.Persona.StudentDirectory;
import info5100.university.example.Persona.StudentProfile;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Info5001UniversityExample {

    public static void main(String[] args) {

        Department department = new Department("Information Systems");
        CourseCatalog coursecatalog = department.getCourseCatalog();

        Course course = coursecatalog.newCourse("app eng", "info 5100", 4);
        Course course2 = coursecatalog.newCourse("web design", "info 6100", 5);

        CourseSchedule courseschedule = department.newCourseSchedule("Fall2020");

        CourseOffer courseoffer = courseschedule.newCourseOffer("info 5100");
        if (courseoffer == null) {
            return;
        }
        courseoffer.generatSeats(10);
        PersonDirectory pd = department.getPersonDirectory();
        Person person = pd.newPerson("0112303");
        StudentDirectory sd = department.getStudentDirectory();
        StudentProfile student = sd.newStudentProfile(person);
        CourseLoad courseload = student.newCourseLoad("Fall2020");

        courseload.newSeatAssignment(courseoffer); //register student in class

        int total = department.calculateRevenuesBySemester("Fall2020");
        System.out.println("Total: " + total);

        // Creating scanner object to read user input
        Scanner scanner = new Scanner(System.in);

         //Generate 6-10 courses (1 core and 5-9 electives)
        generateCourses(coursecatalog);

        // Generate 10 students
        generateStudents(department);

        // Generate 5-10 professors
        generateProfessors(department);

        // Generate 10 course offers with minimum 20 student registrations
        generateCourseOffers(department);

   

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Manage Course Catalog");
            System.out.println("2. Manage Course Schedule");
            System.out.println("3. Manage Student Registrations");
            System.out.println("4. Generate Report for Fall2020 Semester");
            System.out.println("Please select an option:");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    manageCourseCatalog(coursecatalog, scanner);
                    break;
                case 2:
                    manageCourseSchedule(coursecatalog, department, scanner);
                    break;
                case 3:
                    manageStudentRegistrations(department, scanner);
                    break;
                case 4:
                    generateReport(department, scanner);
                    break;
                default:
                    System.out.println("Invalid option selected.");
            }
        }
    }

    // Method to generate 6-10 courses (1 core and 5-9 electives)
    public static void generateCourses(CourseCatalog coursecatalog) {
        Random random = new Random();
        int numElectives = random.nextInt(5) + 5; // Generate between 5 and 9 electives
        int numCourses = numElectives + 1; // Include 1 core course

        coursecatalog.newCourse("Core Course", "CORE101", 3); // Example core course

        for (int i = 0; i < numElectives; i++) {
            String courseName = "Elective Course " + (i + 1);
            String courseNumber = "ELEC" + (i + 101); // Example elective course numbers
            int credits = random.nextInt(3) + 2; // Random credits between 2 and 4
            coursecatalog.newCourse(courseName, courseNumber, credits);
        }

        System.out.println("Generated " + numCourses + " courses.");
    }

    // Method to generate 10 students
    public static void generateStudents(Department department) {
        for (int i = 0; i < 10; i++) {
            String studentId = "S" + (i + 1);
            Person person = department.getPersonDirectory().newPerson(studentId);
            StudentProfile student = department.getStudentDirectory().newStudentProfile(person);
        }
        System.out.println("Generated 10 students.");
    }

    // Method to generate 5-10 professors
    public static void generateProfessors(Department department) {
        Random random = new Random();
        int numProfessors = random.nextInt(6) + 5; // Generate between 5 and 10 professors

        for (int i = 0; i < numProfessors; i++) {
            String professorId = "P" + (i + 1);
            Person person = department.getPersonDirectory().newPerson(professorId);
            FacultyProfile facultyProfile = department.getFacultyDirectory().newFacultyProfile(person);
        }
        System.out.println("Generated " + numProfessors + " professors.");
    }

//    // Method to generate 10 course offers with minimum 20 student registrations
//    public static void generateCourseOffers(Department department) {
//        List<Course> courses = department.getCourseCatalog().getCourseList();
//        Random random = new Random();
//
//        for (int i = 0; i < 10; i++) {
//            Course course = courses.get(random.nextInt(courses.size()));
//            CourseSchedule courseSchedule = department.newCourseSchedule("Fall2020");
//            CourseOffer courseOffer = courseSchedule.newCourseOffer(course.getCOurseNumber());
//            courseOffer.generatSeats(20); // Ensure minimum 20 student registrations
//        }
//        System.out.println("Generated 10 course offers with minimum 20 student registrations.");
//    }
   public static void generateCourseOffers(Department department) {
    List<Course> courses = department.getCourseCatalog().getCourseList();
    List<FacultyProfile> faculties = department.getFacultyDirectory().getTeacherlist();
    Random random = new Random();

    for (int i = 0; i < 10; i++) {
        Course course = courses.get(random.nextInt(courses.size()));
        CourseSchedule courseSchedule = department.newCourseSchedule("Fall2020");
        CourseOffer courseOffer = courseSchedule.newCourseOffer(course.getCOurseNumber());
        courseOffer.generatSeats(20); // Ensure minimum 20 student registrations

        // Assign a random faculty to the course offer
        FacultyProfile faculty = faculties.get(random.nextInt(faculties.size()));
        courseOffer.AssignAsTeacher(faculty); // Assign faculty to the course offer
    }
    System.out.println("Generated 10 course offers with minimum 20 student registrations.");
}



    public static void manageCourseCatalog(CourseCatalog courseCatalog, Scanner scanner) {
        System.out.println("Manage the course catalog:");
        System.out.println("1. Add Courses");
        System.out.println("2. Browse Courses");
        System.out.println("Please select an option:");

        int option = scanner.nextInt();

        switch (option) {
            case 1:
                addCourse(courseCatalog, scanner);
                break;
            case 2:
                browseCourse(courseCatalog);
                break;
            default:
                System.out.println("Invalid option selected.");
        }
    }

    public static void manageCourseSchedule(CourseCatalog courseCatalog, Department department, Scanner scanner) {
        System.out.println("Manage the course schedule:");
        System.out.println("1. Create Course Schedule for New Semester");
        System.out.println("2. Add Course Offers");
        System.out.println("3. Assign Teachers");
        System.out.println("Please select an option:");

        int option = scanner.nextInt();

        switch (option) {
            case 1:
                createCourseSchedule(department, scanner);
                break;
            case 2:
                addCourseOffers(courseCatalog, department, scanner);
                break;
            case 3:
                assignTeachers(department, scanner);
                break;
            default:
                System.out.println("Invalid option selected.");
        }
    }

    public static void createCourseSchedule(Department department, Scanner scanner) {
        System.out.println("Enter the details for the new semester:");
        System.out.println("Semester name:");
        String semesterName = scanner.next();
        // Add more details as needed

        CourseSchedule newSchedule = department.newCourseSchedule(semesterName);
        System.out.println("New course schedule created for " + semesterName);
    }

    public static void addCourseOffers(CourseCatalog courseCatalog, Department department, Scanner scanner) {
        System.out.println("Select a course to add an offer:");
        browseCourse(courseCatalog);

        System.out.println("Enter the course number:");
        String courseNumber = scanner.next();

        Course course = courseCatalog.getCourseByNumber(courseNumber);
        if (course != null) {
            CourseOffer courseOffer = department.getCourseSchedule(courseNumber).newCourseOffer(courseNumber);

            if (courseOffer != null) {
                System.out.println("Course offer added for " + courseNumber);
            } else {
                System.out.println("Failed to add course offer.");
            }
        } else {
            System.out.println("Course not found.");
        }
    }

    public static void assignTeachers(Department department, Scanner scanner) {
        System.out.println("Select a course offer to assign a teacher:");
        System.out.println("Enter the course offer ID:");
        String offerId = scanner.next();

        CourseOffer courseOffer = department.getCourseSchedule(offerId).getCourseOfferById(offerId);
        if (courseOffer != null) {
            System.out.println("Enter the teacher ID:");
            String teacherId = scanner.next();

            if (department.getFacultyDirectory() != null && department.getFacultyDirectory().findTeachingFaculty(teacherId) != null) {
                courseOffer.setInstructor(teacherId);
                System.out.println("Teacher assigned to course offer.");
            } else {
                System.out.println("Teacher not found.");
            }
        } else {
            System.out.println("Course offer not found.");
        }
    }

    public static void browseCourse(CourseCatalog coursecatalog) {
        System.out.println("You selected: Browse Courses");
        ArrayList<Course> courseList = coursecatalog.getCourseList();
        System.out.println("Available Courses:");
        for (Course course : courseList) {
            System.out.println(course.getCOurseNumber() + "  " + course.getCOurseNumber()
                    + " (" + course.getCredits() + " credits)");
        }
    }

    public static void addCourse(CourseCatalog coursecatalog, Scanner scanner) {
        System.out.println("You selected: Add Courses");
        System.out.println("Enter the course name:");
        String cname = scanner.next();
        System.out.println("Enter the course credits:");
        int credits = scanner.nextInt();
        System.out.println("Enter the course number:");
        String cnumber = scanner.next();

        Course newCourse = coursecatalog.newCourse(cname, cnumber, credits);
        System.out.println("Course Added");
    }

    public static void manageStudentRegistrations(Department department, Scanner scanner) {
        System.out.println("Manage student course registrations:");
        System.out.println("Enter student ID:");
        String studentId = scanner.next();

        StudentProfile student = department.getStudentDirectory().findStudent(studentId);
        if (student != null) {
            System.out.println("Student found: " + studentId);
            System.out.println("Select a course offer to register for:");
            browseCourse(department.getCourseCatalog());

            System.out.println("Enter the course number:");
            String courseNumber = scanner.next();

            CourseOffer courseOffer = department.getCourseSchedule(courseNumber).getCourseOfferByNumber(courseNumber);
            if (courseOffer != null) {
                if (courseOffer.assignEmptySeat(student.getCurrentCourseLoad())) {
                    System.out.println("Student registered for course: " + courseNumber);
                } else {
                    System.out.println("Failed to register student for course.");
                }
            } else {
                System.out.println("Course offer not found.");
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    public static void generateReport(Department department, Scanner scanner) {
//        System.out.println("Report for Fall2020 Semester");
//        System.out.println("--------------------------------------");
//
//        // Iterate over all students in the student directory
//        for (StudentProfile student : department.getStudentDirectory().getStudentList()) {
//            CourseLoad courseLoad = student.getCourseLoadBySemester("Fall2020");
//
//            if (courseLoad != null) {
//                for (SeatAssignment seatAssignment : courseLoad.getSeatAssignments()) {
//                    CourseOffer courseOffer = seatAssignment.getCourseOffer();
//                    Course course = courseOffer.getSubjectCourse();
//
//                    System.out.println("Course Number: " + course.getCOurseNumber());
//                    System.out.println("Course Name: " + course.getName());
//
//                    FacultyProfile facultyProfile = courseOffer.getFacultyProfile();
//                    if (facultyProfile != null) {
//                        System.out.println("Professor: " + facultyProfile.getPerson().getPersonId());
//                    } else {
//                        System.out.println("Professor: Not assigned");
//                    }
//
//                    Float grade = seatAssignment.getGrade();
//                    System.out.println("Grade: " + (grade != null ? grade : "Not available"));
//
//                    // Get tuition fees per credit from the user
//                    System.out.println("Enter tuition fees per credit for " + course.getCredits() + " credits:");
//                    double tuitionPerCredit = scanner.nextDouble();
//                    double tuition = course.getCredits() * tuitionPerCredit;
//                    System.out.println("Tuition Fees: $" + tuition);
//
//                    System.out.println("--------------------------------------");
//                }
//
//                double semesterGPA = student.calculateGPABySemester("Fall2020");
//                System.out.println("Average GPA for Semester: " + semesterGPA);
//                System.out.println("--------------------------------------");
//            } else {
//                System.out.println("No courses registered for Fall2020 semester.");
//                System.out.println("--------------------------------------");
//            }
//        }
//    }
System.out.println("Report for Fall2020 Semester");
    System.out.println("--------------------------------------");

    // Iterate over all students in the student directory
    for (StudentProfile student : department.getStudentDirectory().getStudentList()) {
        CourseLoad courseLoad = student.getCourseLoadBySemester("Fall2020");

        if (courseLoad != null) {
            for (SeatAssignment seatAssignment : courseLoad.getSeatAssignments()) {
                CourseOffer courseOffer = seatAssignment.getCourseOffer();
                Course course = courseOffer.getSubjectCourse();

                System.out.println("Course Number: " + course.getCOurseNumber());
                System.out.println("Course Name: " + course.getName());

                // Check if faculty is assigned to the course offer
                FacultyProfile facultyProfile = courseOffer.getFacultyProfile();
                if (facultyProfile != null) {
                    System.out.println("Professor: " + facultyProfile.getPerson().getPersonId());
                } else {
                    System.out.println("Professor: Not assigned");
                }

                Float grade = seatAssignment.getGrade();
                System.out.println("Grade: " + (grade != null ? grade : "Not available"));

                // Get tuition fees per credit from the user
                System.out.println("Enter tuition fees per credit for " + course.getCredits() + " credits:");
                double tuitionPerCredit = scanner.nextDouble();
                double tuition = course.getCredits() * tuitionPerCredit;
                System.out.println("Tuition Fees: $" + tuition);

                System.out.println("--------------------------------------");
            }

            double semesterGPA = student.calculateGPABySemester("Fall2020");
            System.out.println("Average GPA for Semester: " + semesterGPA);
            System.out.println("--------------------------------------");
        } else {
            System.out.println("No courses registered for Fall2020 semester.");
            System.out.println("--------------------------------------");
        }
    }
    }
}


