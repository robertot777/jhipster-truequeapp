package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Trueque;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Trueque entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TruequeRepository extends JpaRepository<Trueque, Long> {

}
