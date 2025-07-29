package org.calendar.api.service.implementation;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.calendar.api.model.dto.QueryFilter;
import org.calendar.api.model.dto.WeatherDTO;
import org.calendar.api.model.enums.FilterType;
import org.calendar.api.model.projection.WeatherProjection;
import org.calendar.api.populator.WeatherMapper;
import org.calendar.api.repository.WeatherRepository;
import org.calendar.api.service.iface.WeatherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class WeatherServiceImplementation implements WeatherService {

    private final WeatherRepository weatherRepository;
    private final WeatherMapper weatherMapper;

    @Override
    public List<WeatherDTO> getAllWeatherData() {

        var weather = weatherRepository.findAll();

        return weatherMapper.toDTOList(weather);
    }

    @Override
    public List<WeatherProjection> findByCity(final String city) {

        return weatherRepository.findByCity(city);
    }

    @Override
    public void deleteAllWeatherData() {

        weatherRepository.deleteAll();
    }

    @Override
    public Page<WeatherProjection> getWeatherByFilters(final Pageable pageable, final List<QueryFilter> queryFilters) {

        final Specification<WeatherProjection> templateSpecification = (root, query, criteriaBuilder) -> {

            final var predicateList = new ArrayList<Predicate>();

            for (final var filter : queryFilters) {
                final var predicate = computePredicate(root, criteriaBuilder, filter);
                predicateList.add(predicate);
            }
            return criteriaBuilder.and(predicateList.toArray(Predicate[]::new));
        };

        return weatherRepository.findBy(templateSpecification, q -> q.as(WeatherProjection.class).page(pageable));
    }

    private <T> Predicate computePredicate(final Root<T> root, final CriteriaBuilder criteriaBuilder,
                                           final QueryFilter filter) {

        return switch (FilterType.get(filter.getCondition())) {
            case EQUALS -> criteriaBuilder.equal(root.get(filter.getKey()), filter.getValue());
            case NOT_EQUALS -> criteriaBuilder.notEqual(root.get(filter.getKey()), filter.getValue());
            case LIKE -> criteriaBuilder.like(root.get(filter.getKey()), "%" + filter.getValue() + "%");
            case NOT_LIKE -> criteriaBuilder.notLike(root.get(filter.getKey()), "%" + filter.getValue() + "%");
        };
    }
}
