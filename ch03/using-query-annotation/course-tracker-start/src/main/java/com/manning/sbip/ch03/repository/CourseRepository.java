package com.manning.sbip.ch03.repository;

import com.manning.sbip.ch03.model.Course;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.beans.Transient;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

    @Query("select c from Course c where c.category=?1")
   Iterable<Course> findAllByCategory(String category);

    @Query(value = "select * from COURSES where rating=:rating", nativeQuery = true)
    Iterable<Course> findCourseByRating(@Param("rating") int rating);

    @Query(value = "select c from Course c where c.category=:category and c.rating>:rating")
    Iterable<Course> findAllByCategoryAndRatingGreaterThan(@Param("category")String category, @Param("rating") int rating);

    @Modifying
    @Transactional
    @Query("update Course c set c.rating=:rating where c.name=:name")
    int updateCourseRatingByName(@Param("rating") int rating, @Param("name") String name);
}
