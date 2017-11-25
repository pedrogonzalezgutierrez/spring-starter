package com.kiesoft.sstarter.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Mapper {

    @Bean
    public DozerBeanMapper dozerBeanMapper() {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

//        List<String> mappingFiles = Arrays.asList("classpath:/dozer/publish.xml");
//        List<String> mappingFiles = Arrays.asList("file:///home/pedrola/workspace/mvc/mvc-webapp/src/main/resources/dozer/publish.xml");
//        dozerBeanMapper.setMappingFiles(mappingFiles);

        return dozerBeanMapper;
    }
}
