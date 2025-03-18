package com.aissatna.medilinkbackend.service.export.commun;

import java.util.List;

public interface IExportDataService<T> {
    List<String> buildHeaderList();
    List<ColumnExtractor<T>> buildColumnExtractors();
    String buildFileName();
    void sendExportAttachment(List<T> dataList);
}