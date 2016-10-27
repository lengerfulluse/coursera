package me.hengwei.t.javaee.spring.beans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Created by hengwei on 27/10/2016.
 */
@Component(value="serviceBean")
public class ServiceBean {
    @Setter
    @Getter
    private String serviceName;

    @Setter
    @Getter
    private long WAIT_TIME;
}
