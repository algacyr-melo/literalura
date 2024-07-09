package br.com.alura.literalura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.literalura.controller.MenuController;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner
{
    private final MenuController menuController;

    @Autowired
    public LiteraluraApplication(MenuController menuController)
    {
        this.menuController = menuController;
    }

    public static void main(String[] args)
    {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        menuController.start();
    }
}
