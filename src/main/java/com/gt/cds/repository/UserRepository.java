package com.gt.cds.repository;

import com.gt.cds.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User findById(long id);

    @Query(nativeQuery = true)
    List<User> findBySalaryRange(@Param("min") float min, @Param("max") float max);

}
