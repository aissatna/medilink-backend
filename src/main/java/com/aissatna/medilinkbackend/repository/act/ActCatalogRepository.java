package com.aissatna.medilinkbackend.repository.act;

import com.aissatna.medilinkbackend.model.ActCatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActCatalogRepository extends JpaRepository<ActCatalog, Long> {
}