package tw.com.panmed.ptcg_player_platform.domain.deck;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import com.github.wnameless.apt.NamedResource;
import com.github.wnameless.apt.NamedResource.Constant;
import com.github.wnameless.apt.NamedResource.InjectType;
import com.github.wnameless.apt.NamedResource.IntConstant;
import com.github.wnameless.spring.boot.up.data.mongodb.cascade.annotation.CascadeRef;
import com.github.wnameless.spring.boot.up.data.mongodb.cascade.CascadeType;
import com.github.wnameless.spring.boot.up.jsf.JsfPOJO;
import com.github.wnameless.spring.boot.up.web.RestfulItem;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import tw.com.panmed.ptcg_player_platform.domain.card.Card;
import tw.com.panmed.ptcg_player_platform.model.jsondata.Deck.DeckSchemaData;
import tw.com.panmed.ptcg_player_platform.service.NavBarService;

@NamedResource(injectable = InjectType.JAKARTA,
    constants = {@Constant(name = NavBarService.GROUP_NAME, value = "PTCG"),
        @Constant(name = NavBarService.ITEM_NAME, value = "navbar.deck"),
        @Constant(name = NavBarService.ITEM_ICON, value = "fas fa-deck fa-fw")},
    intConstants = {@IntConstant(name = NavBarService.GROUP_ORDER, value = 1),
        @IntConstant(name = NavBarService.ITEM_ORDER, value = 1)})
@Document
@FieldNameConstants
@EqualsAndHashCode(of = "id")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Deck implements JsfPOJO<DeckSchemaData>, RestfulItem<String> {

  @Id
  String id;

  String deckNo;

  String deckName;

  String deckDesc;

  @CascadeRef({CascadeType.CREATE, CascadeType.UPDATE})
  @DBRef
  List<Card> cards;

  DeckSchemaData pojo = new DeckSchemaData();

}
