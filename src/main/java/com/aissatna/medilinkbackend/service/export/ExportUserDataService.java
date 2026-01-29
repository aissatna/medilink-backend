package com.aissatna.medilinkbackend.service.export;

import com.aissatna.medilinkbackend.configuration.app.AppContext;
import com.aissatna.medilinkbackend.dto.patient.PatientLineDTO;
import com.aissatna.medilinkbackend.dto.user.UserLineDTO;
import com.aissatna.medilinkbackend.model.enums.RoleEnum;
import com.aissatna.medilinkbackend.service.email.IEmailService;
import com.aissatna.medilinkbackend.service.export.commun.ColumnExtractor;
import com.aissatna.medilinkbackend.service.export.commun.ExcelExporter;
import com.aissatna.medilinkbackend.service.export.commun.IExportDataService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ExportUserDataService implements IExportDataService<UserLineDTO> {

    private final IEmailService emailService;
    private final AppContext context;


    @Override
    public List<String> buildHeaderList() {
        return List.of(
                "Gender",
                "First Name",
                "Last Name",
                "Phone",
                "Email"
        );
    }

    @Override
    public List<ColumnExtractor<UserLineDTO>> buildColumnExtractors() {
        return List.of(
                UserLineDTO::getGender,
                UserLineDTO::getFirstName,
                UserLineDTO::getLastName,
                UserLineDTO::getPhone,
                UserLineDTO::getEmail
        );
    }

    public void sendExportAttachment(RoleEnum role, List<UserLineDTO> dataList) {
        ExcelExporter<UserLineDTO> exporter = new ExcelExporter<>(role.getExcelTitle());
        exporter.buildHeader(buildHeaderList());
        exporter.buildBody(dataList, buildColumnExtractors());

        try {
            byte[] fileBytes = exporter.toByteArray();
            emailService.sendExportAttachment(
                    context.getCurrentUser().getEmail(),
                    role.getEmailSubject(),
                    buildFileName(role.getFilePrefix()),
                    fileBytes
            );
        } catch (IOException e) {
            log.error("Error occurred while exporting for role {} ",role, e);
        }
    }

    @Override
    public String buildFileName() {
        return "Export_" + LocalDate.now().format(DateTimeFormatter.ISO_DATE) + ".xlsx";
    }

    @Override
    public void sendExportAttachment(List<UserLineDTO> dataList) {
        throw new UnsupportedOperationException("Use sendExportAttachment(RoleEnum, List<UserLineDTO>)");
    }

    private String buildFileName(String prefix) {
        String date = LocalDate.now().format(DateTimeFormatter.ISO_DATE); // 2026-01-28
        return "%s_%s.xlsx".formatted(prefix, date);
    }
}

