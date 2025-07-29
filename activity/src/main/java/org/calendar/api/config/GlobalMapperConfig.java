package org.calendar.api.config;

import org.mapstruct.Builder;
import org.mapstruct.MapperConfig;

@MapperConfig(builder = @Builder(disableBuilder = true), componentModel = "spring")
public interface GlobalMapperConfig {}
