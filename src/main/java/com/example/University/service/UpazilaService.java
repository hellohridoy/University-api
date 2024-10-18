package com.example.University.service;

import com.example.University.entity.Upazila;

import java.util.List;

public interface UpazilaService {
    Upazila saveUpazila(Upazila upazila);

    List<Upazila> getAllUpazilas();
}
