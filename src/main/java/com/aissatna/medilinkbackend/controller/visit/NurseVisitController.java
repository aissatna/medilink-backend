package com.aissatna.medilinkbackend.controller.visit;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aissatna.medilinkbackend.dto.shared.PageDTO;
import com.aissatna.medilinkbackend.dto.visit.nurse.NurseVisitLineDTO;
import com.aissatna.medilinkbackend.service.visit.IVisitService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/nurse/visits")
@AllArgsConstructor
public class NurseVisitController {
    private final IVisitService visitService;

    public ResponseEntity<PageDTO<NurseVisitLineDTO>> getPaginatedNurseVisits(Pageable pageable) {
        return ResponseEntity.ok(visitService.getPaginatedNurseVisits(pageable));
    }
    
    
}