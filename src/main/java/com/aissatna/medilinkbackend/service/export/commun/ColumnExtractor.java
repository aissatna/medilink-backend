package com.aissatna.medilinkbackend.service.export.commun;

@FunctionalInterface
public interface ColumnExtractor<T> {
    String extract(T item);
}