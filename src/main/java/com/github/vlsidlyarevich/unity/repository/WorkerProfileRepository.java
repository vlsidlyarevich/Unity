package com.github.vlsidlyarevich.unity.repository;

import com.github.vlsidlyarevich.unity.model.Name;
import com.github.vlsidlyarevich.unity.model.Speciality;
import com.github.vlsidlyarevich.unity.model.WorkerProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WorkerProfileRepository extends MongoRepository<WorkerProfile, String> {

    WorkerProfile findById(String id);

    WorkerProfile findByName(Name name);

    List<WorkerProfile> findAllByAge(Integer age);

    List<WorkerProfile> findAllBySpeciality(Speciality speciality);
}
