package com.weihua.mobile.util.dbhelper.handler;

import java.util.Map;

import com.weihua.mobile.util.dbhelper.BasicRowProcessor;
import com.weihua.mobile.util.dbhelper.CursorHandler;
import com.weihua.mobile.util.dbhelper.RowProcessor;

import android.database.Cursor;

public class MapHandler implements CursorHandler<Map<String, Object>> {

	private final RowProcessor convert;

	public MapHandler() {
		this(BasicRowProcessor.ROW_PROCESSOR);
	}

	public MapHandler(RowProcessor convert) {
		super();
		this.convert = convert;
	}

	@Override
	public Map<String, Object> handle(Cursor cs) {
		return this.convert.toMap(cs);
	}

}
