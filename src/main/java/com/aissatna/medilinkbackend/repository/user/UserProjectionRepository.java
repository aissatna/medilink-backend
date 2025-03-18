package com.aissatna.medilinkbackend.repository.user;

import com.aissatna.medilinkbackend.dto.user.UserLineDTO;
import com.aissatna.medilinkbackend.model.enums.RoleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface UserProjectionRepository extends UserRepository{

    @Query(value = "SELECT new com.aissatna.medilinkbackend.dto.user.UserLineDTO( " +
            "u.id," +
            "u.firstName," +
            "u.lastName," +
            "u.gender," +
            "u.phone," +
            "u.email," +
            "u.photoUrl" +
            " ) " +
            "FROM User u " +
            "WHERE u.cabinet.id = ?1 AND " +
            "u.role = ?2 AND (CAST (UNACCENT (LOWER(u.firstName)) AS STRING )  LIKE  ('%' || UNACCENT (LOWER(?3) ) || '%')" +
            "OR CAST (UNACCENT (LOWER(u.lastName)) AS STRING )  LIKE  ('%' || UNACCENT (LOWER(?3) ) || '%') " +
            ")"

    )
    Page<UserLineDTO> getUserLines(Pageable pageable, Long currentCabinetId, RoleEnum role, String search);




}
