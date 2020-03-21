package com.example.antidoping;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile NadaDAO _nadaDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Takings` (`uid` TEXT NOT NULL, `Name` TEXT, `Desc` TEXT, PRIMARY KEY(`uid`))");
        _db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_Takings_uid` ON `Takings` (`uid`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Substances` (`uid` TEXT NOT NULL, `Name` TEXT, PRIMARY KEY(`uid`))");
        _db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_Substances_uid` ON `Substances` (`uid`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'e51e60122dd6151ee26149941f05dbf8')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Takings`");
        _db.execSQL("DROP TABLE IF EXISTS `Substances`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsTakings = new HashMap<String, TableInfo.Column>(3);
        _columnsTakings.put("uid", new TableInfo.Column("uid", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTakings.put("Name", new TableInfo.Column("Name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTakings.put("Desc", new TableInfo.Column("Desc", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTakings = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTakings = new HashSet<TableInfo.Index>(1);
        _indicesTakings.add(new TableInfo.Index("index_Takings_uid", true, Arrays.asList("uid")));
        final TableInfo _infoTakings = new TableInfo("Takings", _columnsTakings, _foreignKeysTakings, _indicesTakings);
        final TableInfo _existingTakings = TableInfo.read(_db, "Takings");
        if (! _infoTakings.equals(_existingTakings)) {
          return new RoomOpenHelper.ValidationResult(false, "Takings(com.example.antidoping.entities.Takings).\n"
                  + " Expected:\n" + _infoTakings + "\n"
                  + " Found:\n" + _existingTakings);
        }
        final HashMap<String, TableInfo.Column> _columnsSubstances = new HashMap<String, TableInfo.Column>(2);
        _columnsSubstances.put("uid", new TableInfo.Column("uid", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubstances.put("Name", new TableInfo.Column("Name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSubstances = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSubstances = new HashSet<TableInfo.Index>(1);
        _indicesSubstances.add(new TableInfo.Index("index_Substances_uid", true, Arrays.asList("uid")));
        final TableInfo _infoSubstances = new TableInfo("Substances", _columnsSubstances, _foreignKeysSubstances, _indicesSubstances);
        final TableInfo _existingSubstances = TableInfo.read(_db, "Substances");
        if (! _infoSubstances.equals(_existingSubstances)) {
          return new RoomOpenHelper.ValidationResult(false, "Substances(com.example.antidoping.entities.Substances).\n"
                  + " Expected:\n" + _infoSubstances + "\n"
                  + " Found:\n" + _existingSubstances);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "e51e60122dd6151ee26149941f05dbf8", "347698bf032448bb651e4f3eea5033d5");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "Takings","Substances");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Takings`");
      _db.execSQL("DELETE FROM `Substances`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public NadaDAO getNadaDAO() {
    if (_nadaDAO != null) {
      return _nadaDAO;
    } else {
      synchronized(this) {
        if(_nadaDAO == null) {
          _nadaDAO = new NadaDAO_Impl(this);
        }
        return _nadaDAO;
      }
    }
  }
}
