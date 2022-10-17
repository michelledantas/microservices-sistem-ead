package com.ead.course.repositories;

import com.ead.course.models.LessonModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LessonRepositoy extends JpaRepository<LessonModel, UUID> {
}
