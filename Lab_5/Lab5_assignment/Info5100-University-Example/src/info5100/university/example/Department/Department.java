///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package info5100.university.example.Department;
//
//import info5100.university.example.CourseCatalog.Course;
//import info5100.university.example.CourseCatalog.CourseCatalog;
//import info5100.university.example.CourseSchedule.CourseLoad;
//import info5100.university.example.CourseSchedule.CourseOffer;
//import info5100.university.example.CourseSchedule.CourseSchedule;
//import info5100.university.example.Degree.Degree;
//import info5100.university.example.Employer.EmployerDirectory;
//import info5100.university.example.Persona.Faculty.FacultyDirectory;
//import info5100.university.example.Persona.PersonDirectory;
//import info5100.university.example.Persona.StudentDirectory;
//import info5100.university.example.Persona.StudentProfile;
//import java.util.HashMap;
//
///**
// *
// * @author kal bugrara
// */
//public class Department {
//
//    String name;
//    CourseCatalog coursecatalog;
//    PersonDirectory persondirectory;
//    StudentDirectory studentdirectory;
//    FacultyDirectory facultydirectory;
//    EmployerDirectory employerdirectory;
//    Degree degree;
//
//    HashMap<String, CourseSchedule> mastercoursecatalog;
//
//    public Department(String n) {
//        name = n;
//        mastercoursecatalog = new HashMap<>();
//        coursecatalog = new CourseCatalog(this);
//        studentdirectory = new StudentDirectory(this); //pass the department object so it stays linked to it
//        persondirectory = new PersonDirectory();
//        degree = new Degree("MSIS");
//        
//    }
//    public void addCoreCourse(Course c){
//        degree.addCoreCourse(c);
//        
//    }
//public void addElectiveCourse(Course c){
//        degree.addElectiveCourse(c);
//        
//    }
//    public PersonDirectory getPersonDirectory() {
//
//        return persondirectory;
//
//    }
//
//    public StudentDirectory getStudentDirectory() {
//    return studentdirectory;
//    }
//
//    public CourseSchedule newCourseSchedule(String semester) {
//
//        CourseSchedule cs = new CourseSchedule(semester, coursecatalog);
//        mastercoursecatalog.put(semester, cs);
//        return cs;
//    }
//
//    public CourseSchedule getCourseSchedule(String semester) {
//
//        return mastercoursecatalog.get(semester);
//
//    }
//
//    public CourseCatalog getCourseCatalog() {
//
//        return coursecatalog;
//
//    }
//
//    public Course newCourse(String n, String nm, int cr) {
//
//        Course c = coursecatalog.newCourse(n, nm, cr);
//        return c;
//    }
//
//    public int calculateRevenuesBySemester(String semester) {
//
//        CourseSchedule css = mastercoursecatalog.get(semester);
//
//        return css.calculateTotalRevenues();
//
//    }
//
//    public void RegisterForAClass(String studentid, String cn, String semester) {
//
//        StudentProfile sp = studentdirectory.findStudent(studentid);
//
//        CourseLoad cl = sp.getCurrentCourseLoad();
//
//        CourseSchedule cs = mastercoursecatalog.get(semester);
//
//        CourseOffer co = cs.getCourseOfferByNumber(cn);
//
//        co.assignEmptySeat(cl);
//
//    }
//}
package info5100.university.example.Department;

import info5100.university.example.CourseCatalog.Course;
import info5100.university.example.CourseCatalog.CourseCatalog;
import info5100.university.example.CourseSchedule.CourseLoad;
import info5100.university.example.CourseSchedule.CourseOffer;
import info5100.university.example.CourseSchedule.CourseSchedule;
import info5100.university.example.Degree.Degree;
import info5100.university.example.Employer.EmployerDirectory;
import info5100.university.example.Persona.Faculty.FacultyDirectory;
import info5100.university.example.Persona.PersonDirectory;
import info5100.university.example.Persona.StudentDirectory;
import info5100.university.example.Persona.StudentProfile;

import java.util.HashMap;

/**
 * Represents a department in a university.
 */
public class Department {

    private String name;
    private CourseCatalog courseCatalog;
    private PersonDirectory personDirectory;
    private StudentDirectory studentDirectory;
    private FacultyDirectory facultyDirectory;
    private EmployerDirectory employerDirectory;
    private Degree degree;
    

    private HashMap<String, CourseSchedule> masterCourseCatalog;

    /**
     * Constructs a Department object with the given name.
     * @param name The name of the department.
     */
    public Department(String name) {
        this.name = name;
        this.masterCourseCatalog = new HashMap<>();
        this.courseCatalog = new CourseCatalog(this);
        this.studentDirectory = new StudentDirectory(this);
        this.personDirectory = new PersonDirectory();
        this.facultyDirectory = new FacultyDirectory(this); 
        this.degree = new Degree("MSIS");
    }

    /**
     * Creates a new course schedule for the specified semester.
     * @param semester The semester for which to create the course schedule.
     * @return The newly created CourseSchedule object.
     */
    public CourseSchedule newCourseSchedule(String semester) {
        CourseSchedule courseSchedule = new CourseSchedule(semester, courseCatalog);
        masterCourseCatalog.put(semester, courseSchedule);
        return courseSchedule;
    }

    /**
     * Retrieves the course schedule for the specified semester.
     * If the course schedule does not exist, it creates a new one.
     * @param semester The semester for which to retrieve the course schedule.
     * @return The CourseSchedule object for the specified semester.
     */
    public CourseSchedule getCourseSchedule(String semester) {
        return masterCourseCatalog.computeIfAbsent(semester, s -> new CourseSchedule(s, courseCatalog));
    }

    public CourseCatalog getCourseCatalog() {
        return courseCatalog;
    }

    public Course newCourse(String n, String nm, int cr) {
        return courseCatalog.newCourse(n, nm, cr);
    }

    public int calculateRevenuesBySemester(String semester) {
        CourseSchedule css = masterCourseCatalog.get(semester);
        return css.calculateTotalRevenues();
    }

    public void addCoreCourse(Course c) {
        degree.addCoreCourse(c);
    }

    public void addElectiveCourse(Course c) {
        degree.addElectiveCourse(c);
    }

    public PersonDirectory getPersonDirectory() {
        return personDirectory;
    }

    public StudentDirectory getStudentDirectory() {
        return studentDirectory;
    }
    
     public void RegisterForAClass(String studentid, String cn, String semester) {

        StudentProfile sp = studentDirectory.findStudent(studentid);

        CourseLoad cl = sp.getCurrentCourseLoad();

        CourseSchedule cs = masterCourseCatalog.get(semester);

        CourseOffer co = cs.getCourseOfferByNumber(cn);

        co.assignEmptySeat(cl);

    }
     public FacultyDirectory getFacultyDirectory() {
        return facultyDirectory;
    }
}
