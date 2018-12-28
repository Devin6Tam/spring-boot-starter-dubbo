package com.mzbloc.springboot;

import com.alibaba.dubbo.config.spring.AnnotationBean;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Created by tanxw on 2018/12/25.
 */
public class DubboConfigurationApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    public DubboConfigurationApplicationContextInitializer() {
    }

    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment env = applicationContext.getEnvironment();
        String scan = env.getProperty("spring.dubbo.scan");
        if(scan != null) {
            AnnotationBean scanner = (AnnotationBean) BeanUtils.instantiate(AnnotationBean.class);
            scanner.setPackage(scan);
            scanner.setApplicationContext(applicationContext);
            applicationContext.addBeanFactoryPostProcessor(scanner);
            applicationContext.getBeanFactory().addBeanPostProcessor(scanner);
            applicationContext.getBeanFactory().registerSingleton("annotationBean", scanner);
        }

    }
}
