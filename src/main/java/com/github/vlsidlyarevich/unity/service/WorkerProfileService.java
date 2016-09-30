package com.github.vlsidlyarevich.unity.service;

import com.github.vlsidlyarevich.unity.models.Name;
import com.github.vlsidlyarevich.unity.models.WorkerProfile;
import com.github.vlsidlyarevich.unity.repository.WorkerProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by vlad on 28.09.16.
 */
@Service
public class WorkerProfileService {

    @Autowired
    private WorkerProfileRepository repository;

    public void save(WorkerProfile workerProfile) {
        workerProfile.setCreatedAt(String.valueOf(LocalDateTime.now()));
        repository.save(workerProfile);
    }

    public WorkerProfile findById(String id) {
        return repository.findById(id);
    }

    public WorkerProfile findByName(Name name) {
        return repository.findByName(name);
    }

    public List<WorkerProfile> findAllByAge(Integer age) {
        return repository.findAllByAge(age);
    }

    public List<WorkerProfile> findAll() {
        return repository.findAll();
    }

    //TODO:// FIXME: 30.09.16
    public List<WorkerProfile> findByFilters(MultiValueMap<String, String> filters) {
        return null;
    }

    public void updateWorkerProfileById(WorkerProfile workerProfile) {
        workerProfile.setUpdatedAt(String.valueOf(LocalDateTime.now()));
        repository.save(workerProfile);
    }

    public void deleteWorkerProfileById(Long id) {
        repository.delete(id.toString());
    }
}
