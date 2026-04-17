package com.aissatna.medilinkbackend.dto.visit.nurse;

import com.aissatna.medilinkbackend.model.enums.VisitStatusEnum;
import java.time.LocalDate;
import java.time.LocalTime;
import com.aissatna.medilinkbackend.util.DateUtil;
import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;

public class NurseVisitLineDTO {
    private Long id;
    private LocalDate date;
    private String startTime;
    private String endTime;
    private String status;
    private boolean isFirstVisit;
    private PatientDTO patient;
    private List<ActVisitDTO> acts = new ArrayList<>();

    public record PatientDTO(
            Long id,
            String firstName,
            String lastName,
            String address,
            String phone
    ) {}

    public record ActDTO (
            Long id,
            String description
    ){}

    public record ActVisitDTO(
        Long id,
        ActDTO act,
        int quantity,
        BigDecimal priceAtTime
    ){}

    public NurseVisitLineDTO (Long id, LocalDate date, LocalTime startTime, LocalTime endTime, VisitStatusEnum status, Long patientId, String patientFirstName, String patientLastName, String patientAddress, String patientPhone) {
        this.id = id;
        this.date = date;
        this.startTime = DateUtil.formatLocalTimeToString(startTime);
        this.endTime = DateUtil.formatLocalTimeToString(endTime);
        this.status = status.name();
        this.isFirstVisit = false;
        this.patient = new PatientDTO(patientId, patientFirstName, patientLastName, patientAddress, patientPhone);
    }
}
