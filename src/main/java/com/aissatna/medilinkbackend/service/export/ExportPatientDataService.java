package com.aissatna.medilinkbackend.service.export;

import com.aissatna.medilinkbackend.configuration.app.AppContext;
import com.aissatna.medilinkbackend.dto.patient.PatientLineDTO;
import com.aissatna.medilinkbackend.service.email.EmailService;
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
public class ExportPatientDataService implements IExportDataService<PatientLineDTO> {

    private final IEmailService emailService;
    private final AppContext context;


    @Override
    public List<String> buildHeaderList() {
        return List.of(
                "Gender",
                "First Name",
                "Last Name",
                "Medical Number",
                "Birth Date",
                "Phone");
    }

    @Override
    public List<ColumnExtractor<PatientLineDTO>> buildColumnExtractors() {
        return List.of(
                PatientLineDTO::getGender,
                PatientLineDTO::getFirstName,
                PatientLineDTO::getLastName,
                PatientLineDTO::getMedicalNumber,
                PatientLineDTO::getBirthDate,
                PatientLineDTO::getPhone
        );
    }

    @Override
    public String buildFileName() {
        return "Export_Patients_%s.xlsx".formatted(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
    }


    @Override
    public void sendExportAttachment(List<PatientLineDTO> dataList) {
        ExcelExporter<PatientLineDTO> exporter = new ExcelExporter<>("Export_Patients");
        exporter.buildHeader(buildHeaderList());
        exporter.buildBody(dataList, buildColumnExtractors());

        try {
            byte[] fileBytes = exporter.toByteArray();
            emailService.sendExportAttachment(
                    context.getCurrentUser().getEmail(),
                    "Patients Export",
                    buildFileName(),
                    fileBytes
            );
        } catch (IOException e) {
            log.error("Error occurred while exporting patients", e);
        }
    }

}

