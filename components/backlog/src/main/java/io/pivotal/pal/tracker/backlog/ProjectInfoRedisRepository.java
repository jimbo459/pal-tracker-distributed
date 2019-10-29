package io.pivotal.pal.tracker.backlog;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectInfoRedisRepository extends CrudRepository<ProjectInfo, Long> {
}
