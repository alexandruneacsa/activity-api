package org.calendar.api.repository;

import org.calendar.api.model.entity.WeatherEntity;
import org.calendar.api.model.projection.WeatherProjection;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Function;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, Long>, JpaSpecificationExecutor<WeatherProjection> {

    @Query(value = "SELECT * FROM weather WHERE city = :city", nativeQuery = true)
    List<WeatherProjection> findByCity(String city);

    <S extends WeatherProjection, R> R findBy(
            Specification<WeatherProjection> spec, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
}
