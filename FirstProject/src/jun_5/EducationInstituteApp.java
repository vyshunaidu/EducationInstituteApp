package jun_5;
//Class representing a Course
class Course {
 private int courseId;
 private String courseName;
 private double courseFee;

 // Parameterized constructor to initialize the instance variables
 public Course(int courseId, String courseName, double courseFee) {
     this.courseId = courseId;
     this.courseName = courseName;
     this.courseFee = courseFee;
 }

 // Getters for the fields
 public int getCourseId() {
     return courseId;
 }

 public String getCourseName() {
     return courseName;
 }

 public double getCourseFee() {
     return courseFee;
 }

 // Override toString() method
 @Override
 public String toString() {
     return courseId + ". " + courseName + " - Fee: Rs." + courseFee;
 }
}

//Class representing an Offer
class Offer {
 private String offerText;

 // Constructor to initialize the offer description
 public Offer(String offerText) {
     this.offerText = offerText;
 }

 // Method to get the offer description
 public String getOfferText() {
     return offerText;
 }
}

//Class representing the Education Institute
class EducationInstitute {
 private Course[] courses;
 private Offer[] offers;

 // Constructor to initialize courses and offers
 public EducationInstitute(Course[] courses, Offer[] offers) {
     this.courses = courses;
     this.offers = offers;
 }

 // Method to get the array of available courses
 public Course[] getCourses() {
     return courses;
 }

 // Method to get the array of ongoing offers
 public Offer[] getOffers() {
     return offers;
 }

 // Method to simulate the enrollment process
 public void enrollStudentInCourse(int courseId, String studentName) {
     for (Course course : courses) {
         if (course.getCourseId() == courseId) {
             System.out.println(studentName + " has enrolled in the course: " + course.getCourseName());
             return;
         }
     }
     System.out.println("Course with ID " + courseId + " not found.");
 }
}

//Class representing a Student
class Student extends Thread {
 private String name;
 private EducationInstitute institute;

 // Constructor to initialize the student with their name and the education institute reference
 public Student(String name, EducationInstitute institute) {
     this.name = name;
     this.institute = institute;
 }

 // Method to display the available courses and their fees
 public void viewCoursesAndFees() {
     System.out.println("Available Courses:");
     for (Course course : institute.getCourses()) {
         System.out.println(course);
     }
 }

 // Method to display the ongoing offers
 public void viewOffers() {
     System.out.println("Ongoing Offers:");
     for (Offer offer : institute.getOffers()) {
         System.out.println(offer.getOfferText());
     }
 }

 // Method to enroll the student in the specified course using the education institute's enrollment process
 public void enrollInCourse(int courseId) {
     institute.enrollStudentInCourse(courseId, name);
 }

 @Override
 public void run() {
     viewCoursesAndFees();
     viewOffers();
     // Example enrollment (could be modified to take dynamic input or other logic)
     enrollInCourse(1); // Hardcoded courseId for demonstration
 }
}

//Main class to simulate concurrent student interactions using threads
public class EducationInstituteApp {
 public static void main(String[] args) {
     // Initialize courses
     Course[] courses = {
         new Course(1, "Mathematics", 1000.0),
         new Course(2, "Science", 1200.0),
         new Course(3, "English", 900.0)
     };

     // Initialize offers
     Offer[] offers = {
         new Offer("Special discount: Get 20% off on all courses!"),
         new Offer("Limited time offer: Enroll in any two courses and get one course free!")
     };

     // Create an instance of EducationInstitute
     EducationInstitute institute = new EducationInstitute(courses, offers);

     // Create two students: Virat and Dhoni
     Student virat = new Student("Virat", institute);
     Student dhoni = new Student("Dhoni", institute);

     // Start both threads
     virat.start();
     dhoni.start();

     // Wait for both threads to finish
     try {
         virat.join();
         dhoni.join();
     } catch (InterruptedException e) {
         System.out.println("Main thread interrupted");
     }

     System.out.println("All tasks are completed.");
 }
}
