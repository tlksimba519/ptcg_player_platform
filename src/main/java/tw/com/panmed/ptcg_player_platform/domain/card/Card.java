package tw.com.panmed.ptcg_player_platform.domain.card;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;
import com.github.wnameless.apt.NamedResource;
import com.github.wnameless.apt.NamedResource.Constant;
import com.github.wnameless.apt.NamedResource.InjectType;
import com.github.wnameless.apt.NamedResource.IntConstant;
import com.github.wnameless.spring.boot.up.jsf.JsfPOJO;
import com.github.wnameless.spring.boot.up.web.RestfulItem;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import tw.com.panmed.ptcg_player_platform.model.jsondata.Card.CardSchemaData;
import tw.com.panmed.ptcg_player_platform.service.NavBarService;


@NamedResource(injectable = InjectType.JAKARTA,
    constants = {@Constant(name = NavBarService.GROUP_NAME, value = "PTCG"),
        @Constant(name = NavBarService.ITEM_NAME, value = "navbar.card"),
        @Constant(name = NavBarService.ITEM_ICON, value = "fas fa-oil-can fa-fw")},
    intConstants = {@IntConstant(name = NavBarService.GROUP_ORDER, value = 1),
        @IntConstant(name = NavBarService.ITEM_ORDER, value = 2)})
@Document
@FieldNameConstants
@EqualsAndHashCode(of = "id")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Card implements JsfPOJO<CardSchemaData>, RestfulItem<String> {

  @Id
  String id;

  // basic info

  @Indexed(unique = true)
  String cardNo;

  String cardType;

  String cardName;

  String cardDesc;

  String img;

  Boolean aceSpec;

  // type & evolve

  String pokemonType;

  String evolveStage;

  // weakness & resistance

  String weakness;

  String resistance;

  int resistancePoint;

  // retreat

  int retreatCost;

  // ability

  boolean ability;

  String abilityDesc;

  // skill

  String skill1;

  int skill1Danmage;

  String skill2;

  int skill2Danmage;

  CardSchemaData pojo = new CardSchemaData();
}
