package com.kevin.arrange.store.sqllite.orm;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class MapToEntity {
	public static <T> T toEntity(Map<String, Object> data, Class<T> t) throws IllegalArgumentException, IllegalAccessException, InstantiationException {
		
		Map<String, Field> fieldMap = new HashMap<String, Field>();
		
		T instance = t.newInstance();
		
		for (Field f : t.getDeclaredFields()) {
			fieldMap.put(f.getName(), f);
		}
		
		for (String key : data.keySet()) {
			
			Field f = fieldMap.get(key);
			
			if (f == null)
				continue;
			
			f.setAccessible(true);
			
			Object value = data.get(key);
			f.set(instance, value);
			
		}
		
		return instance;
		
	}
}
