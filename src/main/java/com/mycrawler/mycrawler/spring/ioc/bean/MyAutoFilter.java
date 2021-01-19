package com.mycrawler.mycrawler.spring.ioc.bean;

import com.alibaba.fastjson.JSONObject;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * @program: study-demo
 * @description:
 * @author: chenjiale
 * @create: 2020-12-05 12:36
 **/
public class MyAutoFilter implements TypeFilter {
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        System.out.println(JSONObject.toJSONString(classMetadata));
        return false;
    }
}
