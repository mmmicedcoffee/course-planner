package datamodel;

import java.util.ArrayList;

public class Course {
    private final String name;
    private final ArrayList<Course> prerequisites;

    public Course(String name, ArrayList<Course> prerequisites) {
        this.name = name;
        this.prerequisites = prerequisites;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Course> getPrerequisites() {
        return prerequisites;
    }
}
