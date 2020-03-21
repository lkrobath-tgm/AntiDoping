package com.example.antidoping;

import android.database.Cursor;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import com.example.antidoping.entities.Takings;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class NadaDAO_Impl implements NadaDAO {
  private final RoomDatabase __db;

  public NadaDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
  }

  @Override
  public List<Takings> getAllTakings() {
    final String _sql = "SELECT * FROM Takings";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "Name");
      final int _cursorIndexOfDesc = CursorUtil.getColumnIndexOrThrow(_cursor, "Desc");
      final List<Takings> _result = new ArrayList<Takings>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Takings _item;
        final String _tmpUid;
        _tmpUid = _cursor.getString(_cursorIndexOfUid);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final String _tmpDesc;
        _tmpDesc = _cursor.getString(_cursorIndexOfDesc);
        _item = new Takings(_tmpUid,_tmpName,_tmpDesc);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Takings getSpecUid(final String uid) {
    final String _sql = "SELECT * FROM Takings where Uid LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (uid == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, uid);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "Name");
      final int _cursorIndexOfDesc = CursorUtil.getColumnIndexOrThrow(_cursor, "Desc");
      final Takings _result;
      if(_cursor.moveToFirst()) {
        final String _tmpUid;
        _tmpUid = _cursor.getString(_cursorIndexOfUid);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final String _tmpDesc;
        _tmpDesc = _cursor.getString(_cursorIndexOfDesc);
        _result = new Takings(_tmpUid,_tmpName,_tmpDesc);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
