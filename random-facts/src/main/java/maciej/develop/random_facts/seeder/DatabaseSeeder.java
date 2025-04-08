package maciej.develop.random_facts.seeder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Blob;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import maciej.develop.random_facts.model.Fact;
import maciej.develop.random_facts.service.FactService;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private FactService service;

    @Override
    public void run(String... args) throws Exception {
        long length = service.getAll().spliterator().getExactSizeIfKnown();
        if (length == 0 || length == -1) {
            createFact("flow_poster.jpg",
                    "Thanks to the popularity of the film \"Flow\", in Brazil, there has been an increase in the adoptions of black cats.");
            createFact("blue_whale.jpg",
                    "The blue whale is the largest animal that has ever live on Earth. The second one is the argentinosaurus.");
            createFact("mount_fuji.jpg",
                    "During World War II, the USA considered painting the snow-covered Mount Fuji black as a psychological attack on Japan.");
            createFact("dire_wolf.jpg",
                    "In April 2025, thanks to cloning and gene editing, Colossal Biosciences was able to bring back the Dire Wolf, which went extinct 10,000 years ago.");
        }
    }

    private void createFact(String img, String desc) throws Exception {
        byte[] file = getImage(img);
        Blob blob = new SerialBlob(file);
        Fact f = new Fact();
        f.setImage(blob);
        f.setDescription(desc);
        service.create(f);
    }

    private byte[] getImage(String path) throws IOException {
        File file = new File(getClass().getClassLoader().getResource("img/" + path).getPath());
        return Files.readAllBytes(file.toPath());
    }
}
