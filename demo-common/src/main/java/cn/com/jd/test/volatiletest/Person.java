package cn.com.jd.test.volatiletest;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by jihaixiao on 2016/9/23.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person implements Serializable{

    private static final long serialVersionUID = 7605007996474228805L;

    private Long id;

    private String name;

    private Integer flag;

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

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", flag=" + flag +
                '}';
    }

//    @Override
//    public int hashCode() {
//        return 32;
//    }
}
