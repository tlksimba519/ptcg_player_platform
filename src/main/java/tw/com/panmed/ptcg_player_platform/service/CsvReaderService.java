package tw.com.panmed.ptcg_player_platform.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import tw.com.panmed.ptcg_player_platform.domain.card.Card;
import tw.com.panmed.ptcg_player_platform.domain.card.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvReaderService {

    @Autowired
    private CardRepository cardRepository;

    public void readCsv(Resource resource) throws IOException, CsvException {
        try (CSVReader reader = new CSVReader(new InputStreamReader(resource.getInputStream()))) {
            List<String[]> records = reader.readAll();
            List<Card> cards = new ArrayList<>();
            for (int i = 1; i < records.size(); i++) {
                String[] record = records.get(i);
                Card card = new Card();
                card.setId(record[0]);
                card.setCardNo(record[1]);
                card.setCardType(record[2]);
                card.setCardName(record[3]);
                card.setCardDesc(record[4]);
                card.setImg(record[5]);
                card.setAceSpec(Boolean.parseBoolean(record[6]));
                card.setPokemonType(record[7]);
                card.setEvolveStage(record[8]);
                card.setWeakness(record[9]);
                card.setResistance(record[10]);
                card.setResistancePoint(Integer.parseInt(record[11]));
                card.setRetreatCost(Integer.parseInt(record[12]));
                card.setAbility(Boolean.parseBoolean(record[13]));
                card.setAbilityDesc(record[14]);
                card.setSkill1(record[15]);
                card.setSkill1Danmage(Integer.parseInt(record[16]));
                card.setSkill2(record[17]);
                card.setSkill2Danmage(Integer.parseInt(record[18]));
                cards.add(card);
            }
            cardRepository.saveAll(cards);
        }
    }
}

