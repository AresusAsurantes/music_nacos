package com.Aresus.music.pojo.es;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@Document(indexName = "singer")
public class SingerEs {

    @Id
    private Integer id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Byte)
    private Byte sex;

    @Field(type = FieldType.Text)
    private String pic;

    @Field(type = FieldType.Date)
    private Date birth;

    @Field(type = FieldType.Text)
    private String location;

    @Field(type = FieldType.Text)
    private String introduction;

}
