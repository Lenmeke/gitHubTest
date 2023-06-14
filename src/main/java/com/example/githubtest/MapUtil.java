package com.example.githubtest;

import com.google.common.collect.Maps;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public final class MapUtil {
    private MapUtil() { }

    public static <V, K> Map<K, V> listToMap(List<V> list, Function<V, K> keyExtractor) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyMap();
        }
        Map<K, V> res = Maps.newHashMap();
        for (V v : list) {
            K k = keyExtractor.apply(v);
            if (k == null) {
                continue;
            }
            res.put(k, v);
        }
        return res;
    }
}