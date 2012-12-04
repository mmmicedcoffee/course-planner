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
