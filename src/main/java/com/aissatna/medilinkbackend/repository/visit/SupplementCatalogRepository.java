package com.aissatna.medilinkbackend.repository.visit;
import com.aissatna.medilinkbackend.model.SupplementCatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplementCatalogRepository extends JpaRepository<SupplementCatalog, Long> {

}
