package datamodel;

import java.util.HashSet;

public class Course {
    private final String name;
    private final HashSet<Course> prerequisites;

    public Course(String name, HashSet<Course> prerequisites) {
        this.name = name;
        this.prerequisites = prerequisites;
    }

    public String getName() {
        return name;
    }

    public HashSet<Course> getPrerequisites() {
        return prerequisites;
    }
}
