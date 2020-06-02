package ru.vlsu.animalcertification.repository;

import ru.vlsu.animalcertification.domain.Request;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Request entity.
 */
@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    @Query("select request from Request request where request.creater.login = ?#{principal.username}")
    List<Request> findByCreaterIsCurrentUser();

    @Query("select request from Request request where request.veterinarian.login = ?#{principal.username}")
    List<Request> findByVeterinarianIsCurrentUser();

    @Query("select request from Request request where request.rshInspector.login = ?#{principal.username}")
    List<Request> findByRshInspectorIsCurrentUser();

    @Query(value = "select distinct request from Request request left join fetch request.animals",
        countQuery = "select count(distinct request) from Request request")
    Page<Request> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct request from Request request left join fetch request.animals")
    List<Request> findAllWithEagerRelationships();

    @Query("select request from Request request left join fetch request.animals where request.id =:id")
    Optional<Request> findOneWithEagerRelationships(@Param("id") Long id);
}
