package com.weihua.mobile.util.dbhelper;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import android.database.Cursor;

public class BasicRowProcessor implements RowProcessor {

	public static final RowProcessor ROW_PROCESSOR=new BasicRowProcessor();
	
	@Override
	public Map<String, Object> toMap(Cursor cs){
		Map<String, Object> result = new CaseInsensitiveHashMap();
		String[] columnNames = cs.getColumnNames();
		int cols = columnNames.length;
		cs.moveToNext();
		for (int i = 0; i < cols; i++) {
			result.put(cs.getColumnName(i), cs.getString(i));
		}
		return result;
	}

	private static class CaseInsensitiveHashMap extends LinkedHashMap<String, Object> {

		private final Map<String, String> lowerCaseMap = new HashMap<String, String>();

		private static final long serialVersionUID = -2848100435296897392L;

		@Override
		public boolean containsKey(Object key) {
			Object realKey = lowerCaseMap.get(key.toString().toLowerCase(Locale.ENGLISH));
			return super.containsKey(realKey);
		}

		@Override
		public Object get(Object key) {
			Object realKey = lowerCaseMap.get(key.toString().toLowerCase(Locale.ENGLISH));
			return super.get(realKey);
		}

		@Override
		public Object put(String key, Object value) {
			Object oldKey = lowerCaseMap.put(key.toLowerCase(Locale.ENGLISH), key);
			Object oldValue = super.remove(oldKey);
			super.put(key, value);
			return oldValue;
		}

		@Override
		public void putAll(Map<? extends String, ?> m) {
			for (Map.Entry<? extends String, ?> entry : m.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				this.put(key, value);
			}
		}

		@Override
		public Object remove(Object key) {
			Object realKey = lowerCaseMap.remove(key.toString().toLowerCase(Locale.ENGLISH));
			return super.remove(realKey);
		}
	}

}
