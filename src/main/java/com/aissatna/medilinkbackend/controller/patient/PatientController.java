package com.aissatna.medilinkbackend.controller.patient;


import com.aissatna.medilinkbackend.dto.patient.PatientDTO;
import com.aissatna.medilinkbackend.dto.patient.PatientLineDTO;
import com.aissatna.medilinkbackend.dto.shared.PageDTO;
import com.aissatna.medilinkbackend.mapper.PatientMapper;
import com.aissatna.medilinkbackend.service.patient.IPatientService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
@AllArgsConstructor
public class PatientController {

    private IPatientService patientService;
    private PatientMapper patientMapper;

    @GetMapping("/table")
    public ResponseEntity<PageDTO<PatientLineDTO>> getPatientsTable (Pageable pageable, @RequestParam(required = false) String search){
            return ResponseEntity.ok(patientService.getPatientsTable(pageable,search));
    }

    @PostMapping("/add")
    public ResponseEntity<PatientLineDTO> addNewPatient(@RequestBody PatientDTO patientDTO) {
        PatientLineDTO  patientLineDTO = patientMapper.mapToDTO(patientService.addPatient(patientDTO));
        return new ResponseEntity<>(patientLineDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PatientLineDTO> updatePatient(@PathVariable Long id, @RequestBody PatientDTO patientDTO) {
        PatientLineDTO patientLineDTO = patientMapper.mapToDTO(patientService.updatePatient(id, patientDTO));
        return new ResponseEntity<>(patientLineDTO, HttpStatus.OK);
    }

    
    //TODO: implement export end point

}
