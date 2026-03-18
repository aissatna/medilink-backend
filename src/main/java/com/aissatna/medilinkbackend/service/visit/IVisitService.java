package com.aissatna.medilinkbackend.service.visit;

public interface IVisitService {
    PageDTO<NurseVisitLineDTO> getPaginatedNurseVisits(Pageable pageable);

}
