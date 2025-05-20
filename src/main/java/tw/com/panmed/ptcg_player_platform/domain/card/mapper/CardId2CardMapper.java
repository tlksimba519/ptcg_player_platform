package tw.com.panmed.ptcg_player_platform.domain.card.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import tw.com.panmed.ptcg_player_platform.domain.card.Card;
import tw.com.panmed.ptcg_player_platform.domain.card.CardRepository;

@Mapper(componentModel = "spring")
public abstract class CardId2CardMapper implements Converter<String, Card> {

    @Autowired
    CardRepository cardRepository;

    public Card convert(String id) {
        return cardRepository.findById(id).orElse(null);
    }

}

