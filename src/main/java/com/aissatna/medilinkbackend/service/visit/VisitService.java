package com.aissatna.medilinkbackend.service.visit;

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
