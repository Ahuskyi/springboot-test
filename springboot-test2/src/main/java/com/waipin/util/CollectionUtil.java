package com.waipin.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionUtil {

/*
      * List集合转换为数组
      */
@SuppressWarnings("unchecked")
public static <T> T[] toArray(List<T> items, Class<T> tClass) {
if (items == null || items.size() == 0)
return null;
 int size = items.size();
try {
T[] array = (T[]) Array.newInstance(tClass, size);
return items.toArray(array);
} catch (Exception e) {
e.printStackTrace();
 return null;
}
}
/**
      * 数组集合转换为ArrayList集合
      */
public static <T> ArrayList<T> toArrayList(T[] items) {
if (items == null || items.length == 0)
return null;
ArrayList<T> list = new ArrayList<>();
 Collections.addAll(list, items);
 return list;
}
}
