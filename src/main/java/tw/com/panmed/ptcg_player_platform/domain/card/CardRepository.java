package tw.com.panmed.ptcg_player_platform.domain.card;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.github.wnameless.spring.boot.up.permission.resource.ResourceFilterRepository;


public interface CardRepository
                extends MongoRepository<Card, String>, ResourceFilterRepository<Card, String> {
}

