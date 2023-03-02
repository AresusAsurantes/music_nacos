package com.Aresus.music.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.sql.rowset.serial.SerialArray;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment implements Serializable {
    private Integer id;
    private Integer userId;
    private Integer songId;
    private Integer songListId;
    private String content;
    private Date createTime;
    private Byte type;
    private Integer up;


    public Comment(Integer userId, Integer songId, Integer songListId, String content, Date createTime, Byte type, Integer up) {
        this.userId = userId;
        this.songId = songId;
        this.songListId = songListId;
        this.content = content;
        this.createTime = createTime;
        this.type = type;
        this.up = up;
    }
}
