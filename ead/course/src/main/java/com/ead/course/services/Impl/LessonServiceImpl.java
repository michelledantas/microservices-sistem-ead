package com.ead.course.services.Impl;

import com.ead.course.repositories.LessonRepositoy;
import com.ead.course.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    LessonRepositoy lessonRepositoy;
}
