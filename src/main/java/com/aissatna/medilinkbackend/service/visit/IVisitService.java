package com.aissatna.medilinkbackend.service.visit;
import com.aissatna.medilinkbackend.dto.shared.PageDTO;
import com.aissatna.medilinkbackend.dto.visit.nurse.NurseVisitLineDTO;
import org.springframework.data.domain.Pageable;


public interface IVisitService {
    PageDTO<NurseVisitLineDTO> getPaginatedNurseVisits(Pageable pageable);

}
