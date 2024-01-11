package org.courseAPI.cli;

import org.courseAPI.cli.service.CourseRetrievalService;
import org.courseAPI.cli.service.CourseStorageService;
import org.courseAPI.cli.service.PluralsightCourse;
import org.courseAPI.repository.CourseReopsitory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CourseRetriever {
    private static final Logger LOG = LoggerFactory.getLogger(CourseRetriever.class);

    public static void main(String... args) {
        LOG.info("course retriever starting .. ");

        if(args.length == 0){
            LOG.warn("please provide author name");
            return;
        }

        try {
            retrieveCourses(args[0]);
        } catch (Exception e){
            LOG.error("Unexpected error ", e);
        }
    }

    private static void retrieveCourses(String authorId) {
        LOG.info("the name of the author is '{}'", authorId);
        CourseRetrievalService courseRetrievalService = new CourseRetrievalService();
        CourseReopsitory courseReopsitory = CourseReopsitory.openCourseRepository();
        CourseStorageService courseStorageService = new CourseStorageService(courseReopsitory);
        List<PluralsightCourse> coursesToStore = courseRetrievalService.getCoursesFor(authorId);
        LOG.info("the courses results {} course of following {}", coursesToStore.size(), coursesToStore);
        courseStorageService.storeCourses(coursesToStore);
        LOG.info("courses are successfully stored..");
    }
}
