package com.Aresus.music.pojo.es;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@Document(indexName = "song")
public class SongEs {

    @Id
    private Integer id;

    @Field(type = FieldType.Integer)
    private Integer singerId;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Text)
    private String introduction;

    @Field(type = FieldType.Date)
    private Date createTime;

    @Field(type = FieldType.Date)
    private Date updateTime;

    @Field(type = FieldType.Text)
    private String pic;

    @Field(type = FieldType.Text)
    private String lyric;

    @Field(type = FieldType.Text)
    private String url;
}
