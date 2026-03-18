package com.aissatna.medilinkbackend.repository.visit;
import com.aissatna.medilinkbackend.model.VisitSupplement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitSupplemetRepository extends JpaRepository<VisitSupplement, Long> {

}
