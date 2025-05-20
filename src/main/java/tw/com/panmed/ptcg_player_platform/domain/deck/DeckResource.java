package tw.com.panmed.ptcg_player_platform.domain.deck;

import org.springframework.stereotype.Component;
import com.github.wnameless.spring.boot.up.permission.resource.StringIdResourceAccessRule;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeckResource implements StringIdResourceAccessRule<QDeck, DeckRepository, Deck> {

    @Override
    public Class<DeckRepository> getResourceFilterRepositoryType() {
        return DeckRepository.class;
    }

    @Override
    public QDeck q() {
        return QDeck.deck;
    }

    @Override
    public Predicate getPredicateOfCRUDAbility() {
        return q().id.isNotNull();
    }

}
