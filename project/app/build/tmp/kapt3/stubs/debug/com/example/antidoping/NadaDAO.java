package com.example.antidoping;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\'J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\'\u00a8\u0006\b"}, d2 = {"Lcom/example/antidoping/NadaDAO;", "", "getAllTakings", "", "Lcom/example/antidoping/entities/Takings;", "getSpecUid", "uid", "", "app_debug"})
public abstract interface NadaDAO {
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM Takings")
    public abstract java.util.List<com.example.antidoping.entities.Takings> getAllTakings();
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM Takings where Uid LIKE :uid")
    public abstract com.example.antidoping.entities.Takings getSpecUid(@org.jetbrains.annotations.NotNull()
    java.lang.String uid);
}