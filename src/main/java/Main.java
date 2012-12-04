import datamodel.Course;
import scheduler.Schedule;

import java.util.HashSet;

import static com.google.common.collect.Sets.newHashSet;

public class Main {
    public static void main(String[] args) {
        // TODO: create easier way of loading data

        // create courses
        Course intro1 = new Course("6.01", new HashSet<Course>());
        Course intro2 = new Course("6.02", newHashSet(intro1));
        Course math = new Course("6.042", newHashSet(intro1));
        Course arch = new Course("6.004", newHashSet(intro1));
        Course software = new Course("6.005", newHashSet(intro1));
        Course algo1 = new Course("6.006", newHashSet(intro1, math));
        Course algo2 = new Course("6.046", newHashSet(algo1));
        Course perf = new Course("6.172", newHashSet(arch, software, algo1));

        // create graph of courses (not necessary, just use adjacency list representation)

        // given target class, generate schedule
        // TODO: adapt Schedule to schedule all the courses
        Schedule schedule = new Schedule(perf, 8);
        schedule.sortDependencies();
        schedule.organizeSemesters();

        System.out.print(schedule.toString());
    }
}
