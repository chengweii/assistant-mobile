package com.weihua.mobile.util.dbhelper;

import android.database.Cursor;

public interface CursorHandler<T> {
	T handle(Cursor cs);
}
