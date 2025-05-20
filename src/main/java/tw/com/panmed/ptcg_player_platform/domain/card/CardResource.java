package tw.com.panmed.ptcg_player_platform.domain.card;

import org.springframework.stereotype.Component;
import com.github.wnameless.spring.boot.up.permission.resource.StringIdResourceAccessRule;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardResource implements StringIdResourceAccessRule<QCard, CardRepository, Card> {

    @Override
    public Class<CardRepository> getResourceFilterRepositoryType() {
        return CardRepository.class;
    }

    @Override
    public QCard q() {
        return QCard.card;
    }

    @Override
    public Predicate getPredicateOfCRUDAbility() {
        return q().id.isNotNull();
    }

}
