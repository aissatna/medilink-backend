package com.aissatna.medilinkbackend.service.visit;
import com.aissatna.medilinkbackend.configuration.app.AppContext;
import com.aissatna.medilinkbackend.dto.shared.PageDTO;
import com.aissatna.medilinkbackend.dto.visit.nurse.NurseVisitLineDTO;
import com.aissatna.medilinkbackend.repository.visit.VisitProjectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

@Service
@AllArgsConstructor
@Transactional
public class VisitService implements IVisitService {
    
    private final VisitProjectionRepository visitProjectionRepository;
    private final AppContext appContext;

    @Override
    public PageDTO<NurseVisitLineDTO> getPaginatedNurseVisits(Pageable pageable) {
        return new PageDTO<>(visitProjectionRepository.getNurseVisitLines(pageable, appContext.getCurrentUser().getCabinet().getId()));// Implementation to retrieve nurse visits for today
    }

    
}
