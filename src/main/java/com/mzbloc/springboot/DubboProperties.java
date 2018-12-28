package com.mzbloc.springboot;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by tanxw on 2018/12/25.
 */
@ConfigurationProperties(
        prefix = "spring.dubbo"
)
public class DubboProperties {
    private String scan;
    private ApplicationConfig application;
    private RegistryConfig registry;
    private ProtocolConfig protocol;

    public DubboProperties() {
    }

    public String getScan() {
        return this.scan;
    }

    public void setScan(String scan) {
        this.scan = scan;
    }

    public ApplicationConfig getApplication() {
        return this.application;
    }

    public void setApplication(ApplicationConfig application) {
        this.application = application;
    }

    public RegistryConfig getRegistry() {
        return this.registry;
    }

    public void setRegistry(RegistryConfig registry) {
        this.registry = registry;
    }

    public ProtocolConfig getProtocol() {
        return this.protocol;
    }

    public void setProtocol(ProtocolConfig protocol) {
        this.protocol = protocol;
    }
}

