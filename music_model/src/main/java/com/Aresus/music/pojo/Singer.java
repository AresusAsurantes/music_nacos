package com.Aresus.music.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class Singer implements Serializable {

    private Integer id;
    private String name;
    private Byte sex;
    private String pic;
    private Date birth;
    private String location;
    private String introduction;


    public Singer(String name, Byte sex, String pic, Date birth, String location, String introduction) {
        this.name = name;
        this.sex = sex;
        this.pic = pic;
        this.birth = birth;
        this.location = location;
        this.introduction = introduction;
    }
}
