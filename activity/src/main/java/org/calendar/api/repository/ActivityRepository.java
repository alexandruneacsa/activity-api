package org.calendar.api.repository;

import org.calendar.api.model.entity.ActivityEntity;
import org.calendar.api.model.projection.ActivityProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityEntity, Long>, JpaSpecificationExecutor<ActivityProjection> {

    Optional<ActivityProjection> findByName(String name);

    @Query(value = "SELECT name FROM activity where id = :id", nativeQuery = true)
    Optional<ActivityProjection> findActivityNameById(Long id);

    Page<ActivityProjection> findAllByOrderByNameAsc(Pageable pageable);
}
