package tw.com.panmed.ptcg_player_platform.domain.deck.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.github.wnameless.spring.boot.up.jsf.JsfPOJOConverter;
import tw.com.panmed.ptcg_player_platform.domain.card.mapper.CardId2CardMapper;
import tw.com.panmed.ptcg_player_platform.domain.deck.Deck;
import tw.com.panmed.ptcg_player_platform.model.jsondata.Deck.DeckSchemaData;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {CardId2CardMapper.class})
public interface DeckSchemaDataMapper extends JsfPOJOConverter<DeckSchemaData, Deck> {

  @Override
  Deck convert(DeckSchemaData schemaData);

}
