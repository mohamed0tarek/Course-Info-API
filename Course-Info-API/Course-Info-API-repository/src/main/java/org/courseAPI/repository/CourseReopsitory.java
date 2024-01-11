package org.courseAPI.repository;

import org.courseAPI.domain.Course;

import java.util.List;

public interface CourseReopsitory {
    void saveCourse(Course course);
    List<Course> getAllCourses();

    void addNotes(String id, String notes);
    void deleteCourses(String id);
    static CourseReopsitory openCourseRepository(){
        return new CourseJdbcRepository();
    }
}
