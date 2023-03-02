package com.Aresus.music.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Song implements Serializable {
    private Integer id;
    private Integer singerId;
    private String name;
    private String introduction;
    private Date createTime;
    private Date updateTime;
    private String pic;
    private String lyric;
    private String url;
}
