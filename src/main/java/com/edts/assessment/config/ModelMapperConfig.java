package com.edts.assessment.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NamingConventions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper mapper = new ModelMapper();

    mapper.getConfiguration()
        .setSkipNullEnabled(true)
        .setSourceNamingConvention(NamingConventions.NONE)
        .setDestinationNamingConvention(NamingConventions.NONE)
        .setMatchingStrategy(MatchingStrategies.STRICT);

    return mapper;
  }
}