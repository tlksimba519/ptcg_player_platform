package tw.com.panmed.ptcg_player_platform;

import com.opencsv.exceptions.CsvException;
import tw.com.panmed.ptcg_player_platform.service.CsvReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CsvReaderService csvReaderService;

    @Value("classpath:cards.csv")
    private Resource resource;

    @Override
    public void run(String... args) throws Exception {
        try {
            csvReaderService.readCsv(resource);
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }
}
