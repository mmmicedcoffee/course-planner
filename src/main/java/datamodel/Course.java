package datamodel;

import java.util.HashSet;

public class Course {
    private final String name;
    private final HashSet<Course> prerequisites;
    private boolean visited;

    public Course(String name, HashSet<Course> prerequisites) {
        this.name = name;
        this.prerequisites = prerequisites;
        this.visited = false;
    }

    public Course getNextUnvisitedPrereq() {
        for (Course c : prerequisites) {
            if (!c.visited) {
                return c;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public HashSet<Course> getPrerequisites() {
        return prerequisites;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public String toString() {
        return name + "\n";
    }
}
