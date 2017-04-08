package com.weihua.mobile.util.dbhelper.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.weihua.mobile.util.dbhelper.BasicRowProcessor;
import com.weihua.mobile.util.dbhelper.CursorHandler;
import com.weihua.mobile.util.dbhelper.RowProcessor;

import android.database.Cursor;


public class MapListHandler implements CursorHandler<List<Map<String, Object>>> {

	private final RowProcessor convert;

	public MapListHandler() {
		this(BasicRowProcessor.ROW_PROCESSOR);
	}

	public MapListHandler(RowProcessor convert) {
		super();
		this.convert = convert;
	}

	@Override
	public List<Map<String, Object>> handle(Cursor cs) {
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		while (!cs.isLast()) {
			rows.add(this.handleRow(cs));
		}
		return rows;
	}

	public Map<String, Object> handleRow(Cursor cs) {
		return this.convert.toMap(cs);
	}

}
