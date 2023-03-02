package com.Aresus.music.model.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ElasticModelUtils <T> {
    public static <T> List<T> copyProperties(List<?> source, Class<T> clazz){
        assert source != null;
        List<T> target = new ArrayList<>();
        for (Object obj : source) {         // 拿到每个SearchHit
            try {
                T targ = clazz.getDeclaredConstructor().newInstance();
                Field[] targetFields = targ.getClass().getDeclaredFields();
                try {
                    Field content = obj.getClass().getDeclaredField("content");
                    content.setAccessible(true);
                    Object obj1 = content.get(obj);         // 拿到SongEs
                    Field[] sourceFields = obj1.getClass().getDeclaredFields();
                    Arrays.stream(targetFields).forEach(a->a.setAccessible(true));
                    Arrays.stream(sourceFields).forEach(a->a.setAccessible(true));
                    for(int i = 0; i < targetFields.length; i++){           // 赋值
                        targetFields[i].set(targ, sourceFields[i].get(obj1));
                    }
                    target.add(targ);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return target;
    }
}
