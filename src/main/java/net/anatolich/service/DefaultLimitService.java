package net.anatolich.service;

import net.anatolich.db.LimitRepository;
import net.anatolich.model.Limit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultLimitService {

    private final LimitRepository limitRepository;

    @Autowired
    public DefaultLimitService(LimitRepository limitRepository) {
        this.limitRepository = limitRepository;
    }

    public void registerLimit(Limit limit) {
        limitRepository.save(limit);
    }
}
