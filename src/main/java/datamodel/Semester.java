package datamodel;

import java.util.ArrayList;
import java.util.Iterator;

public class Semester {
    private final ArrayList<Course> courses;

    public Semester(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public boolean hasConflict(Course course) {
        for (Course c : courses) {
            if (course.getPrerequisites().contains(c) || c.getPrerequisites().contains(course)) {
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        return courses.size();
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public Iterator<Course> iterator() {
        return courses.iterator();
    }
}
