package Main;
import java.io.*;
import java.util.Scanner;

public class mi {
public static class Student {
private String stId, Name, Add, city, state;

public Student(String stdId, String fName, String Address, String cityy, String Statee) {
stId = stdId;
Name = fName;
Add = Address;
city = cityy;
state = Statee;
}

public void setFirstName(String fName) {
Name = fName;
}

public void setLastName(String Address) {
Add = Address;
}

public void setBirthday(String cityy) {
city = cityy;
}

public void setAddress(String town) {
state = town;
}

public String getStId() {
return stId;
}

public String getFirstName() {
return Name;
}

public String getAdd() {
return Add;
}

public String getcity() {
return city;
}


public String getState() {
return state;
}
}

public static void stringWrite(RandomAccessFile raf, String str, int fieldLength) throws IOException {
if (str.length() > fieldLength) {
for (int i = 0; i < fieldLength; i++) {
raf.writeChar(str.charAt(i));
}
} else
raf.writeChars(str);

for (int i = 0; i < (fieldLength - str.length()); i++)
raf.writeChar(' ');
}

public static String stringRead(RandomAccessFile raf, int fieldLength) throws IOException {
char[] charArray = new char[fieldLength];
for (int i = 0; i < fieldLength; i++)
charArray[i] = raf.readChar();
String str = new String(charArray);
str.trim();
return str;
}


public static class StudentFile {
private final int RECORD_SIZE = 150;
private RandomAccessFile studentFile;

public StudentFile(String filename) throws FileNotFoundException {
studentFile = new RandomAccessFile(filename, "rw");
}

public void writeStudentInfo(Student stInfo) throws IOException {
stringWrite(studentFile, stInfo.getStId(), 5);
stringWrite(studentFile, stInfo.getFirstName(), 15);
stringWrite(studentFile, stInfo.getAdd(), 15);
stringWrite(studentFile, stInfo.getcity(), 10);
stringWrite(studentFile, stInfo.getState(), 30);
}

public Student readStudentInfo() throws IOException {
String stId = stringRead(studentFile, 5);
String Name = stringRead(studentFile, 15);
String Add = stringRead(studentFile, 15);
String city = stringRead(studentFile, 10);
String state = stringRead(studentFile, 30);

Student st = new Student(stId, Name, Add, city, state);
return st;
}

private long getByteNum(long recordNum) {
return RECORD_SIZE * recordNum;
}

public void moveFilePointer(long recordNum) throws IOException {
studentFile.seek(getByteNum(recordNum));
}

public int getNumberOfRecords() throws IOException {
return (int)(studentFile.length() / RECORD_SIZE);
}

public void close() throws IOException {
studentFile.close();
}
}

public static void createStudent() throws IOException {
final int NUMBER_STUDENT = 100;
int lastRecordNumber;
String stId, Name, Add, city, state;
Student[] student = new Student[NUMBER_STUDENT];

StudentFile stdFile = new StudentFile("student.dat");
lastRecordNumber = stdFile.getNumberOfRecords();
stId = String.format("%05d", lastRecordNumber + 1);

Scanner sc = new Scanner(System.in);
System.out.println();
System.out.println("Enter the student information.");
System.out.print("Name ");
Name = sc.nextLine();
System.out.print("Address ");
Add = sc.nextLine();
System.out.print("city ");
city = sc.nextLine();
System.out.print("state ");
state = sc.nextLine();


student[lastRecordNumber] = new Student(stId, Name, Add, city, state);
stdFile.moveFilePointer(lastRecordNumber);
stdFile.writeStudentInfo(student[lastRecordNumber]);
System.out.println("Student Data has beed added for Student ID " + stId);

stdFile.close();
}

public static void editStudent() throws IOException {
String studentId;
String stId, Name, Add, city, state;
String again, sure;
Student student;
int recordNumber;

Scanner sc = new Scanner(System.in);
StudentFile stFile = new StudentFile("student.dat");

System.out.println("The student.dat file has " + stFile.getNumberOfRecords() + " student IDs.");
do {
System.out.print("Enter the student ID you wish to edit: ");
studentId = studentIdInput(stFile.getNumberOfRecords());
recordNumber = Integer.parseInt(studentId);

stFile.moveFilePointer(recordNumber - 1);
student = stFile.readStudentInfo();

System.out.println("Existing data: \n");
System.out.println("Student ID: " + student.getStId());
System.out.println("Name: " + student.getFirstName());
System.out.println("Address: " + student.getAdd());
System.out.println("city: " + student.getcity());
System.out.println("state: " + student.getState());
System.out.println();
System.out.println("Please enter the new student information.");
System.out.print("Name: ");
Name = sc.nextLine();
System.out.print("Address: ");
Add = sc.nextLine();
System.out.print("city: ");
city = sc.nextLine();
System.out.print("state: ");
state = sc.nextLine();

student.setFirstName(Name);
student.setLastName(Add);
student.setBirthday(city);
student.setAddress(state);
System.out.print("Are you sure your want to save this data? (Y/N) ");
sure = sc.nextLine();
if (sure.charAt(0) == 'Y' || sure.charAt(0) == 'y') {
stFile.moveFilePointer(recordNumber - 1);
stFile.writeStudentInfo(student);
System.out.println("Updated!");
}

System.out.print("\nDo you want to edit another record? (Y/N) ");
again = sc.nextLine();
} while (again.charAt(0) == 'Y' || again.charAt(0) == 'y');


}

public static String studentIdInput(int numberOfRecords) throws IOException {
String recordNumber;

Scanner sc = new Scanner(System.in);
do {
recordNumber = sc.nextLine();
if (recordNumber.length() != 5)
System.out.println("Input Error: ID must be 5 numbers. Ex. 00015");
if (Integer.parseInt(recordNumber) > numberOfRecords)
System.out.println("This sutdent ID does not exist: " + recordNumber +
". Maximum student ID is " + numberOfRecords);
} while (recordNumber.length() != 5 || Integer.parseInt(recordNumber) > numberOfRecords);

return recordNumber;
}


public static void displayStudent() throws IOException {
StudentFile stFile = new StudentFile("student.dat");
Student[] student = new Student[stFile.getNumberOfRecords()];
System.out.println();
System.out.println("StudentId 	Name 		Address		 	city 			state");
for (int i = 0; i < stFile.getNumberOfRecords(); i++) {
stFile.moveFilePointer(i);
student[i] = stFile.readStudentInfo();
System.out.println(student[i].getStId() + " 	  	 " + student[i].getFirstName() + "" +
student[i].getAdd() + "		" + student[i].getcity() +
"		 " + student[i].getState());
}
stFile.close();
}

public static class Course {
private String courseId, courseName, instructor, Department;

public Course(String cID, String cName, String inst, String D) {
courseId = cID;
courseName = cName;
instructor = inst;
Department = D;
}

public void setCourseName(String cName) {
courseName = cName;
}

public void setInstructor(String instrctr) {
instructor = instrctr;
}

public void setClassroom(String Dep) {
Department = Dep;
}


public String getCourseId() {
return courseId;
}

public String getCourseName() {
return courseName;
}

public String getInstructor() {
return instructor;
}

public String getDepartment() {
return Department;
}
}

public static class CourseFile {
private final int RECORD_SIZE = 150;
private RandomAccessFile courseFile;

public CourseFile(String filename) throws FileNotFoundException {
courseFile = new RandomAccessFile(filename, "rw");
}

public void writeCourseInfo(Course csInfo) throws IOException {
stringWrite(courseFile, csInfo.getCourseId(), 5);
stringWrite(courseFile, csInfo.getCourseName(), 15);
stringWrite(courseFile, csInfo.getInstructor(), 30);
stringWrite(courseFile, csInfo.getDepartment(), 30);
}

public Course readCourseInfo() throws IOException {
String csId = new String(stringRead(courseFile, 5));
String cName = new String(stringRead(courseFile, 15));
String instructor = new String(stringRead(courseFile, 30));
String Department = new String(stringRead(courseFile, 30));

Course cs = new Course(csId, cName, instructor, Department);
return cs;
}

private long getByteNum(long recordNum) {
return RECORD_SIZE * recordNum;
}

public void moveFilePointer(long recordNum) throws IOException {
courseFile.seek(getByteNum(recordNum));
}

public int getNumberOfRecords() throws IOException {
return (int)(courseFile.length() / RECORD_SIZE);
}

public void close() throws IOException {
courseFile.close();
}
}

public static void createCourse() throws IOException {
final int NUMBER_COURSE = 30;
int lastRecordNumber;
String csId, cName, instructor, Department;
Course[] course = new Course[NUMBER_COURSE];

CourseFile csFile = new CourseFile("course.dat");
lastRecordNumber = csFile.getNumberOfRecords();
csId = String.format("%05d", lastRecordNumber + 1);

Scanner sc = new Scanner(System.in);
System.out.println();
System.out.println("Enter the course information.");
System.out.print("Enter Course Name: ");
cName = sc.nextLine();
instructor = selectedInstructor();
Department = selectedClassroom();

course[lastRecordNumber] = new Course(csId, cName, instructor, Department);
csFile.moveFilePointer(lastRecordNumber);
csFile.writeCourseInfo(course[lastRecordNumber]);
System.out.println("Course Data has beed added for Course ID " + csId);

csFile.close();
}

public static void editCourse() throws IOException {
int recordNumber;
String csId;
String courseId, courseName, instructor, Department;
String again, sure;
Course course;

Scanner sc = new Scanner(System.in);
CourseFile csFile = new CourseFile("course.dat");

System.out.println("The course.dat file has " + csFile.getNumberOfRecords() + " course IDs.");
do {
csId = courseIdInput(csFile.getNumberOfRecords());
recordNumber = Integer.parseInt(csId);

csFile.moveFilePointer(recordNumber - 1);
course = csFile.readCourseInfo();

System.out.println();
System.out.println("Existing data: \n");
System.out.println("Course ID: " + course.getCourseId());
System.out.println("Course Name: " + course.getCourseName());
System.out.println("Instructor: " + course.getInstructor());
System.out.println("Departments: " + course.getDepartment());

System.out.println();
System.out.println("Please enter the new course information.");
System.out.print("Course Name: ");
courseName = sc.nextLine();
instructor = selectedInstructor();
Department = selectedClassroom();

course.setCourseName(courseName);
course.setInstructor(instructor);
course.setClassroom(Department);
System.out.print("Are you sure your want to save this data? (Y/N) ");
sure = sc.nextLine();
if (sure.charAt(0) == 'Y' || sure.charAt(0) == 'y') {
csFile.moveFilePointer(recordNumber - 1);
csFile.writeCourseInfo(course);
System.out.println("Updated!");
}

System.out.print("\nDo you want to edit another record? (Y/N) ");
again = sc.nextLine();
} while (again.charAt(0) == 'Y' || again.charAt(0) == 'y');

csFile.close();
}

public static String courseIdInput(int numberOfRecords) throws IOException {
String recordNumber;

Scanner sc = new Scanner(System.in);
do {
System.out.print("\nEnter the course ID you wish to edit: ");
recordNumber = sc.nextLine();
if (recordNumber.length() != 5)
System.out.println("Input Error: ID has to be 5 numbers. Ex. 00015");
if (Integer.parseInt(recordNumber) > numberOfRecords)
System.out.println("This course ID does not exist: " + recordNumber +
".\nMaximum student ID is " + numberOfRecords);
} while (recordNumber.length() != 5 || Integer.parseInt(recordNumber) > numberOfRecords);

return recordNumber;
}

public static void displayCourse() throws IOException {
CourseFile csFile = new CourseFile("course.dat");
Course[] course = new Course[csFile.getNumberOfRecords()];

System.out.println();
System.out.println("CourseId	 courseName 	Instructor 		Department");
for (int i = 0; i < csFile.getNumberOfRecords(); i++) {
csFile.moveFilePointer(i);
course[i] = csFile.readCourseInfo();
System.out.println(course[i].getCourseId() + " 		" + course[i].getCourseName() + " " +
course[i].getInstructor() + "" + course[i].getDepartment());
}
csFile.close();
}

public static class Enrollment {
private String studentId, enrollmentId, courseId, courseName, year, semester, grade;

private Enrollment(String stId, String enID, String cID, String cName, String yr, String sm, String gr) {
studentId = stId;
enrollmentId = enID;
courseId = cID;
courseName = cName;
year = yr;
semester = sm;
grade = gr;
}
	
	public void setStudentId(String stdId) {
	studentId = stdId;
	}
	
	public void setEnrollmentId(String enrollId) {
	enrollmentId = enrollId;
	}
	
	public void setCourseId(String courId) {
	courseId = courId;
	}
	
	public void setCourseName(String courName) {
	courseName = courName;
	}
	
	public void setYear(String yr) {
	year = yr;
	}
	
	public void setSemester(String smstr) {
	semester = smstr;
	}
	
	public void setGrade(String gr) {
	grade = gr;
	}
	
	public String getStudentId() {
	return studentId;
	}
	
	public String getEnrollmentId() {
	return enrollmentId;
	}
	
	public String getCourseId() {
	return courseId;
	}
	
	public String getCourseName() {
	return courseName;
	}
	
	public String getYear() {
	return year;
	}
	
	public String getSemester() {
	return semester;
	}
	
	public String getGrade() {
	return grade;
}
}

public static class EnrollmentFile {
private final int RECORD_SIZE = 84;
private RandomAccessFile enrollmentFile;

public EnrollmentFile(String filename) throws FileNotFoundException {
enrollmentFile = new RandomAccessFile(filename, "rw");
}

public void writeEnrollmentInfo(Enrollment enInfo) throws IOException {
stringWrite(enrollmentFile, enInfo.getStudentId(), 5);
stringWrite(enrollmentFile, enInfo.getEnrollmentId(), 5);
stringWrite(enrollmentFile, enInfo.getCourseId(), 5);
stringWrite(enrollmentFile, enInfo.getCourseName(), 15);
stringWrite(enrollmentFile, enInfo.getYear(), 4);
stringWrite(enrollmentFile, enInfo.getSemester(), 6);
stringWrite(enrollmentFile, enInfo.getGrade(), 2);
}

public Enrollment readEnrollmentInfo() throws IOException {
String studentId = stringRead(enrollmentFile, 5);
String enrollmentId = stringRead(enrollmentFile, 5);
String courseId = stringRead(enrollmentFile, 5);
String courseName = stringRead(enrollmentFile, 15);
String year = stringRead(enrollmentFile, 4);
String semester = stringRead(enrollmentFile, 6);
String grade = stringRead(enrollmentFile, 2);

Enrollment en = new Enrollment(studentId, enrollmentId, courseId, courseName, year, semester, grade);
return en;
}

private long getByteNum(long recordNum) {
return RECORD_SIZE * recordNum;
}

public void moveFilePointer(long recordNum) throws IOException {
enrollmentFile.seek(getByteNum(recordNum));
}

public int getNumberOfRecords() throws IOException {
return (int)(enrollmentFile.length() / RECORD_SIZE);
}

public void close() throws IOException {
enrollmentFile.close();
}
}

public static void createEnrollment() throws IOException {
final int NUMBER_ENROLLMENT = 100;
int lastRecordNumber;
String studentId, enrollmentId, courseId, courseName, year, semester;
String grade = new String("*");
Enrollment[] enrollment = new Enrollment[NUMBER_ENROLLMENT];

Student student = selectedStudent();
studentId = student.getStId();
System.out.println("\nStudent ID: " + studentId);
System.out.println("Student Name: " + student.getFirstName() + " " + student.getAdd());

EnrollmentFile enFile = new EnrollmentFile("enrollment.dat");
lastRecordNumber = enFile.getNumberOfRecords();
enrollmentId = String.format("%05d", lastRecordNumber + 1);

System.out.println("\nPlease enter the enrollment information.");
Course course = selectedCourse();
courseId = course.getCourseId();
courseName = course.getCourseName();
year = selectedYear();
semester = selectedSemester();

enrollment[lastRecordNumber] = new Enrollment(studentId, enrollmentId, courseId, courseName, year, semester, grade);
enFile.moveFilePointer(lastRecordNumber);
enFile.writeEnrollmentInfo(enrollment[lastRecordNumber]);
System.out.println("Enrollment Data has beed added for Enrollment ID " + enrollmentId);

enFile.close();
}

public static Student selectedStudent() throws IOException {
int i, selection;
String recordNumber;
Student student;

StudentFile stFile = new StudentFile("student.dat");
Scanner sc = new Scanner(System.in);
System.out.print("Enter the Student ID: ");
recordNumber = studentIdInput(stFile.getNumberOfRecords());

stFile.moveFilePointer(Integer.parseInt(recordNumber) - 1);
student = stFile.readStudentInfo();
return student;
}

public static Course selectedCourse() throws IOException {
int i, selection;
	CourseFile csFile = new CourseFile("course.dat");
	Course[] course = new Course[csFile.getNumberOfRecords()];
	Scanner sc = new Scanner(System.in);
	System.out.println("\nEnter course name: ");
	for (i = 0; i < csFile.getNumberOfRecords(); i++) {
	csFile.moveFilePointer(i);
	course[i] = csFile.readCourseInfo();
	System.out.println((i + 1) + ". " + course[i].getCourseName());
	}
	System.out.print("Please enter a valid choice(1 to " + i + "):");
	
	selection = sc.nextInt();
	sc.nextLine();
	
	return course[selection - 1];
}

public static void editEnrollment() throws IOException {
boolean flag = false;
String recordNumber, dropNumber, again, sure;
String studentId, enrollmentId, courseId, courseName, year, semester, grade;

Scanner sc = new Scanner(System.in);
EnrollmentFile enFile = new EnrollmentFile("enrollment.dat");
Enrollment[] enrollment = new Enrollment[enFile.getNumberOfRecords()];

studentId = selectedStudent().getStId();
do {
	flag = false;
	System.out.println("\nEnrollmentID CourseID Year Semester");
	for (int i = 0; i < enFile.getNumberOfRecords(); i++) {
	enFile.moveFilePointer(i);
	enrollment[i] = enFile.readEnrollmentInfo();
	if (studentId.equals(enrollment[i].getStudentId())) {
	System.out.println(enrollment[i].getEnrollmentId() + " " + enrollment[i].getCourseId() + " " +
	enrollment[i].getYear() + " " + enrollment[i].getSemester());
	flag = true;
}
}
if (flag == false)
System.out.println("\n\nNo record found");

else {
	System.out.print("\nDo you want to dorp an enrollment? (Y/N) ");
	again = sc.nextLine();
	if (again.charAt(0) == 'Y' || again.charAt(0) == 'y') {
	do {
	System.out.print("Please enter the Enrollment ID: ");
	dropNumber = sc.nextLine();
	if (dropNumber.length() != 5)
	System.out.println("Input Error: ID has to be 5 numbers. Ex: 00015");
	} while (dropNumber.length() != 5);

System.out.print("\nAre you sure? (Y/N) ");
sure = sc.nextLine();
if (sure.charAt(0) == 'Y' || sure.charAt(0) == 'y') {
	enFile.moveFilePointer(Integer.parseInt(dropNumber) - 1);
	enrollment[Integer.parseInt(dropNumber) - 1].setStudentId("");
	enrollment[Integer.parseInt(dropNumber) - 1].setEnrollmentId("");
	enrollment[Integer.parseInt(dropNumber) - 1].setCourseId("");
	enrollment[Integer.parseInt(dropNumber) - 1].setCourseName("");
	enrollment[Integer.parseInt(dropNumber) - 1].setYear("");
	enrollment[Integer.parseInt(dropNumber) - 1].setSemester("");
	enrollment[Integer.parseInt(dropNumber) - 1].setGrade("");
	enFile.writeEnrollmentInfo(enrollment[Integer.parseInt(dropNumber) - 1]);
}
} else
flag = false;
}
} while (flag == true);

enFile.close();
}

public static void displayEnrollment() throws IOException {
EnrollmentFile enFile = new EnrollmentFile("enrollment.dat");
Enrollment[] enrollment = new Enrollment[enFile.getNumberOfRecords()];

System.out.println("\nStudentId EnrollmentId 	courseId courseName 	year 	semester 	grade");
for (int i = 0; i < enFile.getNumberOfRecords(); i++) {
enFile.moveFilePointer(i);
enrollment[i] = enFile.readEnrollmentInfo();

if (enrollment[i].getEnrollmentId().charAt(0) == '0') {
System.out.println(enrollment[i].getStudentId() + " 	  " + enrollment[i].getEnrollmentId() +
" 	" + enrollment[i].getCourseId() + "	" + enrollment[i].getCourseName() +
"	" + enrollment[i].getYear() + "	 " + enrollment[i].getSemester() + "		 " +
enrollment[i].getGrade());
}
}
enFile.close();
}

public static void viewGradesByStudent() throws IOException {
String studentId, year, semester;

EnrollmentFile enFile = new EnrollmentFile("enrollment.dat");
Enrollment[] enrollment = new Enrollment[enFile.getNumberOfRecords()];

System.out.print("\nPlease enter the Student ID: ");
studentId = studentIdInput(enFile.getNumberOfRecords());
year = selectedYear();
semester = selectedSemester();

System.out.println("\nSearch Result: " + studentId + "(is unsigned)");
System.out.println("enrollmentId courseId year semester grade");
for (int i = 0; i < enFile.getNumberOfRecords(); i++) {
enFile.moveFilePointer(i);
enrollment[i] = enFile.readEnrollmentInfo();

if (enrollment[i].getStudentId().equals(studentId) && enrollment[i].getYear().equals(year) &&
enrollment[i].getSemester().equals(semester)) {
System.out.println(enrollment[i].getEnrollmentId() + " " + enrollment[i].getCourseId() + " " +
enrollment[i].getYear() + " " + enrollment[i].getSemester() + " " + enrollment[i].getGrade());
}
}

enFile.close();
}

public static void viewGradesByCourse() throws IOException {
String courseName, year, studentName = "";
Student student;

StudentFile stFile = new StudentFile("student.dat");
EnrollmentFile enFile = new EnrollmentFile("enrollment.dat");
Enrollment[] enrollment = new Enrollment[enFile.getNumberOfRecords()];

courseName = selectedCourse().getCourseName();
year = selectedYear();

System.out.println("\nReport for Course: " + courseName + "of" + year + "(is unsigned)");
System.out.println("studentId studentName semester grade");
for (int i = 0; i < enFile.getNumberOfRecords(); i++) {
enFile.moveFilePointer(i);
enrollment[i] = enFile.readEnrollmentInfo();

if (enrollment[i].getCourseName().equals(courseName) && enrollment[i].getYear().equals(year)) {
for (int k = 0; k < stFile.getNumberOfRecords(); k++) {
stFile.moveFilePointer(k);
student = stFile.readStudentInfo();
if (enrollment[i].getStudentId().equals(student.getStId()))
studentName = student.getFirstName() + " " + student.getAdd();
}
System.out.println(enrollment[i].getStudentId() + " " + studentName + " " +
enrollment[i].getSemester() + " " + enrollment[i].getGrade());
}
}

enFile.close();
}

public static void editGradesByStudent() throws IOException {
String enrollmentId, courseId;

viewGradesByStudent();

Scanner sc = new Scanner(System.in);
EnrollmentFile enFile = new EnrollmentFile("enrollment.dat");
Enrollment[] enrollment = new Enrollment[enFile.getNumberOfRecords()];

System.out.print("\nEnter the enrollment ID to edit a grade: ");
enrollmentId = sc.nextLine();

System.out.print("Enter the course ID to edit a grade: ");
courseId = sc.nextLine();

for (int i = 0; i < enFile.getNumberOfRecords(); i++) {
enFile.moveFilePointer(i);
enrollment[i] = enFile.readEnrollmentInfo();

if (enrollment[i].getEnrollmentId().equals(enrollmentId) && enrollment[i].getCourseId().equals(courseId)) {

enrollment[i].setGrade(selectedGrade());

enFile.moveFilePointer(i);
enFile.writeEnrollmentInfo(enrollment[i]);
System.out.println("Grade has been updated.");
}
}

enFile.close();
}

public static void editGradesByCourse() throws IOException {
String studentId, courseName, year;

viewGradesByCourse();

StudentFile stFile = new StudentFile("student.dat");
EnrollmentFile enFile = new EnrollmentFile("enrollment.dat");
Enrollment[] enrollment = new Enrollment[enFile.getNumberOfRecords()];

System.out.print("\nEnter the student ID to edit a grade: ");
studentId = studentIdInput(stFile.getNumberOfRecords());
courseName = selectedCourse().getCourseName();
year = selectedYear();

for (int i = 0; i < enFile.getNumberOfRecords(); i++) {
enFile.moveFilePointer(i);
enrollment[i] = enFile.readEnrollmentInfo();

if (enrollment[i].getStudentId().equals(studentId) && enrollment[i].getCourseName().equals(courseName) &&
	enrollment[i].getYear().equals(year)) {
	enrollment[i].setGrade(selectedGrade());
	enFile.moveFilePointer(i);
	enFile.writeEnrollmentInfo(enrollment[i]);
	System.out.println("Grade has been updated.");
}
}

enFile.close();
}

public static void displayMainMenu() {
	System.out.println("Welcome to University Enrollment");
	System.out.println("1. Create Student");
	System.out.println("2. Create Course");
	System.out.println("3. Create Enrollment");
	System.out.println("4. Edit Student");
	System.out.println("5. Edit Course");
	System.out.println("6. Edit Enrollment");
	System.out.println("7. Display Student(s)");
	System.out.println("8. Display Course(s)");
	System.out.println("9. Display Enrollment(s)");
	System.out.println("10. Grades Sub Menu");
	System.out.println("0. --- Quit ---");
	System.out.print("Please enter a valid choice (1-10, 0 to Quit): ");
}

public static void displayGradesSubMenu() {
	System.out.println("\nGrades Menu");
	System.out.println("1. View Grades by Student");
	System.out.println("2. View Grades by Course");
	System.out.println("3. Edit Grades by Student");
	System.out.println("4. Edit Grades by Course");
	System.out.println("0. -- Exit to Menu --");
	System.out.print("Please enter a valid choice(1-4, 0 to Exit): ");
}

public static String selectedInstructor() {
int selection;
	Scanner sc = new Scanner(System.in);
	System.out.println();
	System.out.println("Enter Instructor Name: ");
	System.out.println("1. Mony ");
	System.out.println("2. Patel ");
	System.out.println("3. Imran ");
	System.out.println("4. Susan ");
	System.out.println("5. Anna ");
	System.out.print("Please enter a valid choice(1-5): ");

selection = sc.nextInt();
sc.nextLine();

	switch (selection) {
	case 1:
	return "Mony ";
	case 2:
	return "Patel ";
	case 3:
	return "Imran ";
	case 4:
	return "Susan ";
	case 5:
	return "Anna ";
	default:
	return "";
	}
}

public static String selectedClassroom() {
int selection;
	Scanner sc = new Scanner(System.in);
	System.out.println();
	System.out.println("Enter Department: ");
	System.out.println("1. Computer Science ");
	System.out.println("2. Business ");
	System.out.println("3. Arts ");
	System.out.println("4. Math ");
	System.out.println("5. English ");
	System.out.print("Please enter a valid choice(1-5): ");

selection = sc.nextInt();
sc.nextLine();

switch (selection) {
	case 1:
	return "Computer Science ";
	case 2:
	return "Business ";
	case 3:
	return "Arts ";
	case 4:
	return "Math ";
	case 5:
	return "English ";
	default:
	return "";
}

}

public static String selectedYear() {
int selection;
	Scanner sc = new Scanner(System.in);
	System.out.println();
	System.out.println("Select School year: ");
	System.out.println("1. 2021");
	System.out.println("2. 2022");
	System.out.println("3. 2023");
	System.out.println("4. 2024");
	System.out.println("5. 2025");
	System.out.print("Please enter a valid choice(1-5): ");

selection = sc.nextInt();
sc.nextLine();
	
	switch (selection) {
	case 1:
	return "2021 ";
	case 2:
	return "2022 ";
	case 3:
	return "2023 ";
	case 4:
	return "2024 ";
	case 5:
	return "2025 ";
	default:
	return "";
}
}

public static String selectedSemester() {
	int selection;
	Scanner sc = new Scanner(System.in);
	System.out.println();
	System.out.println("Choose semester: ");
	System.out.println("1. spring ");
	System.out.println("2. summer ");
	System.out.println("3. fall ");
	System.out.println("4. winter ");
	System.out.print("Please enter a valid choice(1-4): ");

selection = sc.nextInt();
sc.nextLine();

	switch (selection) {
	case 1:
	return "spring ";
	case 2:
	return "summer ";
	case 3:
	return "fall ";
	case 4:
	return "winter ";
	default:
	return "";
}
}

public static String selectedGrade() {
	String selection;
	Scanner sc = new Scanner(System.in);
	System.out.println();
	System.out.println("Enter grade: ");
	System.out.println("A");
	System.out.println("B");
	System.out.println("C");
	System.out.println("D");
	System.out.println("F");
	System.out.print("Please enter a valid choice (A-D, F): ");

selection = sc.nextLine();

	switch (selection) {
	case "a":
	case "A":
	return "A";
	case "b":
	case "B":
	return "B";
	case "c":
	case "C":
	return "C";
	case "d":
	case "D":
	return "D";
	case "f":
	case "F":
	return "F";
	default:
	return "*";
}
}

public static void main(String[] args) throws Exception {
int choice, subChoice;

Scanner sc = new Scanner(System.in);
do {
System.out.println();
displayMainMenu();
choice = sc.nextInt();
sc.hasNextLine();

	switch (choice) {
	case 1:
	createStudent();
	break;
	case 2:
	createCourse();
	break;
	case 3:
	createEnrollment();
	break;
	case 4:
	editStudent();
	break;
	case 5:
	editCourse();
	break;
	case 6:
	editEnrollment();
	break;
	case 7:
	displayStudent();
	break;
	case 8:
	displayCourse();
	break;
	case 9:
	displayEnrollment();
	break;
	case 10:
	displayGradesSubMenu();
	subChoice = sc.nextInt();
	sc.nextLine();
	
	switch (subChoice) {
	case 1:
	viewGradesByStudent();
	break;
	case 2:
	viewGradesByCourse();
	break;
	case 3:
	editGradesByStudent();
	break;
	case 4:
	editGradesByCourse();
	break;
	default:
	break;
	}
	case 0:
	break;

}
} while (choice != 0);
}
}
