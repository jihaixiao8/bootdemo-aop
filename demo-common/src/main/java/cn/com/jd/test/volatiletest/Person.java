package cn.com.jd.test.volatiletest;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by jihaixiao on 2016/9/23.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person implements Serializable{

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}