package scheduler;

import datamodel.Course;
import datamodel.Semester;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;

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
        final HashSet<Course> visited = newHashSet();

        agenda.push(targetCourse);
        while (agenda.size() != 0) {
            final Course current = agenda.pop();
            assert(!visited.contains(current));
            visited.add(current);
            sortedCourses.add(0, current);   // add to front

            for (Course prereq : current.getPrerequisites()) {
                agenda.push(prereq);
            }
        }
    }

    public void organizeSemesters() {
        assert(!sortedCourses.isEmpty());

        for (Course course : sortedCourses) {
            int semesterCount = 0;
            while (semesters[semesterCount].hasConflict(course)) {
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
}
