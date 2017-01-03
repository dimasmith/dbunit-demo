package net.anatolich.db;

import net.anatolich.model.Limit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LimitRepository extends CrudRepository<Limit, String> {

    List<Limit> findAllByEntityTypeAndEntityReference(String entityType, String entityReference);
}
