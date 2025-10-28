package com.TVP.Turismo.Repository;

import com.TVP.Turismo.entity.EmailLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Alias de EmailLogRepository. Preferir EmailLogRepository.
 */
@Repository
public interface EmailRepository extends JpaRepository<EmailLog, Long> { }
