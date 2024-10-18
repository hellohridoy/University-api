package com.example.University.service;

import com.example.University.entity.Upazila;
import com.example.University.repository.UpazilaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UpazilaServiceImpl implements UpazilaService {

    private final UpazilaRepository upazilaRepository;

    @Override
    public Upazila saveUpazila(Upazila upazila) {
        return upazilaRepository.save(upazila);
    }

    @Override
    public List<Upazila> getAllUpazilas() {
        return upazilaRepository.findAll();
    }
}
