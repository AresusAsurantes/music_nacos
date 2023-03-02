package com.Aresus.music.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("list_song")
public class ListSong implements Serializable {
    private Integer id;
    private Integer songId;
    private Integer songListId;
}
