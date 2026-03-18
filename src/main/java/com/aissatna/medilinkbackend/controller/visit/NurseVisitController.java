package com.aissatna.medilinkbackend.controller.visit;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/nurse/visits")
@AllArgsConstructor
public class NurseVisitController {
    private final IVisitService visitService;

    public ResponseEntity<PageDTO<NurseVisitLineDTO>> getPaginatedNurseVisits(Pageable pageable) {
        return ResponseEntity.ok(visitService.getPaginatedNurseVisits(pageable));
    }
    
    
}