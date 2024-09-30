import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Course {
    private final String courseCode;
    private final String title;
    private final String description;
    private final String schedule;
    private final int capacity;
    private int enrolledStudents;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = 0;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailableSlots() {
        return capacity - enrolledStudents;
    }

    public boolean isFull() {
        return enrolledStudents >= capacity;
    }

    public void enrollStudent() {
        if (!isFull()) {
            enrolledStudents++;
        }
    }

    public void dropStudent() {
        if (enrolledStudents > 0) {
            enrolledStudents--;
        }
    }

    public void displayCourseInfo() {
        System.out.println(courseCode + ": " + title + " | " + description + " | Schedule: " + schedule + " | Available Slots: " + getAvailableSlots());
    }
}

class Student {
    private final String studentID;
    private final String name;
    private final ArrayList<Course> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public void registerCourse(Course course) {
        if (!course.isFull()) {
            registeredCourses.add(course);
            course.enrollStudent();
            System.out.println("Successfully registered for " + course.getTitle());
        } else {
            System.out.println("Course " + course.getTitle() + " is full.");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.dropStudent();
            System.out.println("Successfully dropped " + course.getTitle());
        } else {
            System.out.println("You are not registered for this course.");
        }
    }

    public void listRegisteredCourses() {
        if (registeredCourses.isEmpty()) {
            System.out.println("No registered courses.");
        } else {
            System.out.println("Registered courses for " + name + ":");
            for (Course course : registeredCourses) {
                course.displayCourseInfo();
            }
        }
    }
}

class CourseDatabase {
    private final HashMap<String, Course> courses;

    public CourseDatabase() {
        courses = new HashMap<>();
    }

    public void addCourse(Course course) {
        courses.put(course.getCourseCode(), course);
    }

    public void displayAvailableCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses.values()) {
            if (!course.isFull()) {
                course.displayCourseInfo();
            }
        }
    }

    public Course getCourse(String courseCode) {
        return courses.get(courseCode);
    }
}

class StudentDatabase {
    private final HashMap<String, Student> students;

    public StudentDatabase() {
        students = new HashMap<>();
    }

    public void addStudent(Student student) {
        students.put(student.getStudentID(), student);
    }

    public Student getStudent(String studentID) {
        return students.get(studentID);
    }
}

public class studentregistration {
    public static void main(String[] args) {
        CourseDatabase courseDatabase = new CourseDatabase();
        StudentDatabase studentDatabase = new StudentDatabase();
        Scanner scanner = new Scanner(System.in);

        courseDatabase.addCourse(new Course("CS101", "Intro to Computer Science", "Learn the basics of CS.", 30, "MWF 9-10 AM"));
        courseDatabase.addCourse(new Course("MATH201", "Calculus I", "Introduction to calculus.", 25, "TTh 11-12:30 PM"));
        courseDatabase.addCourse(new Course("ENG102", "English Literature", "Study of classic English literature.", 20, "MWF 1-2 PM"));

        Student student = new Student("S123", "John Doe");
        studentDatabase.addStudent(student);

        int choice;
        do {
            System.out.println("\nStudent Course Registration System");
            System.out.println("1. View Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    courseDatabase.displayAvailableCourses();
                    break;
                case 2:
                    System.out.print("Enter your Student ID: ");
                    String studentID = scanner.nextLine();
                    Student studentToRegister = studentDatabase.getStudent(studentID);

                    if (studentToRegister != null) {
                        System.out.print("Enter the Course Code to register: ");
                        String courseCode = scanner.nextLine();
                        Course courseToRegister = courseDatabase.getCourse(courseCode);

                        if (courseToRegister != null) {
                            studentToRegister.registerCourse(courseToRegister);
                        } else {
                            System.out.println("Course not found.");
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter your Student ID: ");
                    String studentIDForDrop = scanner.nextLine();
                    Student studentToDrop = studentDatabase.getStudent(studentIDForDrop);

                    if (studentToDrop != null) {
                        System.out.print("Enter the Course Code to drop: ");
                        String courseCodeToDrop = scanner.nextLine();
                        Course courseToDrop = courseDatabase.getCourse(courseCodeToDrop);

                        if (courseToDrop != null) {
                            studentToDrop.dropCourse(courseToDrop);
                        } else {
                            System.out.println("Course not found.");
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter your Student ID: ");
                    String studentIDForView = scanner.nextLine();
                    Student studentToView = studentDatabase.getStudent(studentIDForView);

                    if (studentToView != null) {
                        studentToView.listRegisteredCourses();
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}