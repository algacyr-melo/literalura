package br.com.alura.literalura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.literalura.repository.BookRepository;
import br.com.alura.literalura.view.MenuDisplay;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner
{
    private final MenuDisplay menuDisplay;

    @Autowired
    public LiteraluraApplication(MenuDisplay menuDisplay)
    {
        this.menuDisplay = menuDisplay;
    }

    public static void main(String[] args)
    {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        menuDisplay.start();
    }
}
