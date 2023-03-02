package com.Aresus.music.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

@Data
public class RankList implements Serializable {
    private Long id;
    @TableField("songListId")
    private Long songListId;
    @TableField("consumerId")
    private Long consumerId;
    @TableField("score")
    private Integer score;
}
