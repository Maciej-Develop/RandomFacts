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
            byte[] file = getImage("flow_poster.jpg");
            Blob blob = new SerialBlob(file);
            Fact f = new Fact();
            f.setImage(blob);
            f.setDescription("test");
            service.create(f);
        }
    }

    private byte[] getImage(String path) throws IOException {
        File file = new File(getClass().getClassLoader().getResource("img/" + path).getPath());
        return Files.readAllBytes(file.toPath());
    }
}
