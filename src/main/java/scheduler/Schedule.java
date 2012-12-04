package scheduler;

import datamodel.Course;
import datamodel.Semester;

import java.util.ArrayList;
import java.util.Stack;

import static com.google.common.collect.Lists.newArrayList;

public class Schedule {
    private final Course targetCourse;
    private final Semester[] semesters;
    private final ArrayList<Course> sortedCourses;

    public Schedule(Course targetCourse, int numSemesters) {
        this.targetCourse = targetCourse;
        this.semesters = new Semester[numSemesters];
        this.sortedCourses = newArrayList();
        initSemesters(numSemesters);
    }

    private void initSemesters(int numSemesters) {
        for (int i = 0; i < numSemesters; i++) {
            semesters[i] = new Semester(new ArrayList<Course>());
        }
    }

    public void sortDependencies() {
        sortedCourses.clear();
        final Stack<Course> agenda = new Stack<Course>();
        final Stack<Course> finished = new Stack<Course>();

        agenda.push(targetCourse);
        targetCourse.setVisited(true);

        // dfs
        while (!agenda.isEmpty()) {
            // TODO: need some kind of cycle detection!
            final Course current = agenda.peek();
            final Course prereq = current.getNextUnvisitedPrereq();
            if (prereq != null) {
                prereq.setVisited(true);
                agenda.push(prereq);
            } else {
                finished.push(agenda.pop());

            }
        }

        // topo sort by reversing the dfs order
        while (!finished.isEmpty()) {
            sortedCourses.add(0, finished.pop());
        }
    }

    public void organizeSemesters() {
        assert(!sortedCourses.isEmpty());

        int semesterCount = 0;
        for (Course course : sortedCourses) {
            while (semesters[semesterCount].hasImmediateConflict(course)) {
                semesterCount++;
            }
            semesters[semesterCount].addCourse(course);
        }
    }

    public Course getTargetCourse() {
        return targetCourse;
    }

    public Semester getSemester(int semesterNum) {
        return semesters[semesterNum];
    }

    public Semester[] getSemesters() {
        return semesters;
    }

    public ArrayList<Course> getSortedCourses() {
        return sortedCourses;
    }

    @Override
    public String toString() {
        String scheduleString = "MY LOVELY SCHEDULE\n";
        for (Semester s : semesters) {
            if (s.getSize() == 0) {
                break;
            }
            scheduleString += "-----\n";
            for (Course c : s.getCourses()) {
                scheduleString += c.toString();
            }
        }
        return scheduleString;
    }
}
