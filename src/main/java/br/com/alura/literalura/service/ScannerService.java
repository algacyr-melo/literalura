package br.com.alura.literalura.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

public class ScannerService
{
    private final Scanner scanner = new Scanner(System.in);

    public Scanner getScanner()
    {
        return this.scanner;
    }
}
