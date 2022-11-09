package com.jobster.website.services;

import com.jobster.website.models.Resume;
import com.jobster.website.repositories.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;


@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;

    @Autowired
    public ResumeService(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    @Transactional
    public List<Resume> findAll() {
       return resumeRepository.findAll();
    }

    @Transactional
    public Page<Resume> findResumeListByFilter(Resume resume, int pageNumber, int resumeCount, String fieldName, Sort.Direction direction) {
        Page<Resume> resumes = null;
        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withMatcher("description", contains().ignoreCase());
        try {
            Sort sort = Sort.by(new Sort.Order(direction, fieldName));
            resumes = resumeRepository.findAll(Example.of(resume, matcher), PageRequest.of(pageNumber, resumeCount, sort));
        } catch (Exception ignored) {

        }
        return resumes;
    }
}
