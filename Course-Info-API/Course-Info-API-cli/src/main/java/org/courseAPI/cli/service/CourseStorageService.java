package org.courseAPI.cli.service;

import org.courseAPI.domain.Course;
import org.courseAPI.repository.CourseReopsitory;

import java.util.List;
import java.util.Optional;

public class CourseStorageService {
    private static final String PS_BASE_URL = "https://app.pluralsight.com";
    private final CourseReopsitory courseReopsitory;

    public CourseStorageService(CourseReopsitory courseReopsitory ){
        this.courseReopsitory = courseReopsitory;
    }

    public void storeCourses(List<PluralsightCourse> psCourses){
        for (PluralsightCourse psCourse : psCourses){
            Course course = new Course(
                    psCourse.id(),
                    psCourse.title(),
                    psCourse.duration(),
                    psCourse.level(),
                    PS_BASE_URL + psCourse.contentUrl(),
                    Optional.empty()
            );
            courseReopsitory.saveCourse(course);
        }
    }

}
