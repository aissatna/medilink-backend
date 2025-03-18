package com.aissatna.medilinkbackend.controller.patient;


import com.aissatna.medilinkbackend.dto.patient.PatientDTO;
import com.aissatna.medilinkbackend.dto.patient.PatientLineDTO;
import com.aissatna.medilinkbackend.dto.shared.PageDTO;
import com.aissatna.medilinkbackend.mapper.PatientMapper;
import com.aissatna.medilinkbackend.service.patient.IPatientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
@AllArgsConstructor
@Slf4j
public class PatientController {

    private final IPatientService patientService;
    private final PatientMapper patientMapper;

    @PreAuthorize("hasAnyRole('ROLE_SECRETARY')")
    @GetMapping("/table")
    public ResponseEntity<PageDTO<PatientLineDTO>> getCabinetPatientsTable(Pageable pageable, @RequestParam(required = false) String search){
            return ResponseEntity.ok(patientService.getPaginatedPatients(pageable,search));
    }

    @PreAuthorize("hasAnyRole('ROLE_SECRETARY')")
    @PostMapping("/add")
    public ResponseEntity<PatientLineDTO> addNewPatient(@RequestBody PatientDTO patientDTO) {
        PatientLineDTO  patientLineDTO = patientMapper.mapToDTO(patientService.addPatient(patientDTO));
        return new ResponseEntity<>(patientLineDTO, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_SECRETARY')")
    @DeleteMapping("/delete/{patientId}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long patientId) {
        patientService.deletePatient(patientId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAnyRole('ROLE_SECRETARY')")
    @PutMapping("/update/{patientId}")
    public ResponseEntity<PatientLineDTO> updatePatient(@PathVariable Long patientId, @RequestBody PatientDTO patientDTO) {
        PatientLineDTO patientLineDTO = patientMapper.mapToDTO(patientService.updatePatient(patientId, patientDTO));
        return new ResponseEntity<>(patientLineDTO, HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('ROLE_SECRETARY')")
    @PostMapping("/export")
    public ResponseEntity<Void> sendPatientsExportByEmail(@RequestParam(required = false) String search) {
        log.info("search: {}", search);
        patientService.sendPatientsExportByEmail(search);
        return ResponseEntity.ok().build();
    }

}
