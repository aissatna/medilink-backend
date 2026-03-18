package com.aissatna.medilinkbackend.dto.visit.nurse;

import java.time.LocalDate;

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

    public NurseVisitLineDTO (Long id, LocalDate date, String startTime, String endTime, String status, boolean isFirstVisit, Long patientId, String patientFirstName, String patientLastName, String patientAddress, String patientPhone) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.isFirstVisit = isFirstVisit;
        this.patient = new PatientDTO(patientId, patientFirstName, patientLastName, patientAddress, patientPhone);
        
    }
}
