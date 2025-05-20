package tw.com.panmed.ptcg_player_platform.domain.deck;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.github.wnameless.spring.boot.up.permission.resource.ResourceFilterRepository;

public interface DeckRepository
        extends MongoRepository<Deck, String>, ResourceFilterRepository<Deck, String> {
}
