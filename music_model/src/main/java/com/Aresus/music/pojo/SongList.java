package com.Aresus.music.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SongList implements Serializable {
    private Integer id;
    private String title;
    private String pic;
    private String style;
    private String introduction;
}
