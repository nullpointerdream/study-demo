package com.mycrawler.mycrawler.spring.ioc.bean;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @program: study-demo
 * @description:
 * @author: chenjiale
 * @create: 2020-12-06 10:51
 **/
public class MyImportSelector implements ImportSelector {
    //返回带import注解类的注解信息
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {

        return new String[]{"com.mycrawler.mycrawler.spring.ioc.bean.Yellow"};
    }
}
