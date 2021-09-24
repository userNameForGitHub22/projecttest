package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) throws IOException {
        int result = 0;
        var romResult = "";
        int step=0;
        // получение данных
        Scanner s = new Scanner(System.in);
        System.out.println("Введите выражение");
        String input = s.nextLine();
        input = input.replaceAll(" ","");

        //выяснение операции

        var operations = "+-*/"; //возможные операции
        String operation = "знак";
        while (step != input.length()) {
            if (input.charAt(step) == operations.charAt(0)) {
                operation = "+";
            }
            if (input.charAt(step) == operations.charAt(1)) {
                operation = "-";
            }
            if (input.charAt(step) == operations.charAt(2)) {
                operation = "*";
            }
            if (input.charAt(step) == operations.charAt(3)) {
                operation = "/";
            }
            step += 1;

        }
        int index = input.indexOf(operation); //в дальнейшем для определения чисел до и после знака
        int operationIndex = operations.indexOf(operation); //в дальнейшем для определения вида операции
        step = 0;
        var firstNumber = "";
        var secondNumber = "";

        //получение чисел

        while (step != input.length()) { //получение числа до и после знака
            if (step < index) {
                firstNumber += input.charAt(step);
            }
            if (step > index) {
                secondNumber += input.charAt(step);
            }
            step += 1;
        }
        List<String> rom = new ArrayList(); //список возможных вводных римских чисел
        List<String> arab = new ArrayList(); //список возможных вводных арабских чисел
        List<String> rom2 = new ArrayList(); //список возможных первых цифр для результата римских цифр
        arab.addAll(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
        rom.addAll(Arrays.asList("", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"));
        rom2.addAll(Arrays.asList("", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"));
        var firstNumberType = "?"; //? если число не удовлетворяет условию задания
        var secondNumberType = "?";
        for (step = 0; step < arab.size(); step++) { //определение римское или арабское число
            if (firstNumber.equals(rom.get(step))) {
                firstNumberType = "RomIntNumber";
            }
            if (firstNumber.equals(arab.get(step))) {
                firstNumberType = "ArabIntNumber";
            }
            if (secondNumber.equals(rom.get(step))) {
                secondNumberType = "RomIntNumber";
            }
            if (secondNumber.equals(arab.get(step))) {
                secondNumberType = "ArabIntNumber";
            }
        }
        if (firstNumberType.equals("?")||secondNumberType.equals("?")){ //проверка все ли числа подходят под условия
            throw new IOException();
        }
        if (!(firstNumberType.equals(secondNumberType))){ //проверка одинакового ли типа числа
            throw new IOException();
        }

        // Если числа оказались арабскими

        if (firstNumberType.equals("ArabIntNumber") && secondNumberType.equals("ArabIntNumber")) {
            int newFirstNumber = Integer.parseInt(firstNumber);
            int newSecondNumber = Integer.parseInt(secondNumber);
            //определение вида операции
            if (operationIndex == 0) { //сложение
                result = newFirstNumber + newSecondNumber;
            }
            if (operationIndex == 1) { //вычитание
                result = newFirstNumber - newSecondNumber;
            }
            if (operationIndex == 2) { //умножение
                result = newFirstNumber * newSecondNumber;
            }
            if (operationIndex == 3) { //деление
                result = newFirstNumber / newSecondNumber;
            }
            System.out.println(result);
        }

        //Если числа оказались римскими

        if (firstNumberType.equals("RomIntNumber") && secondNumberType.equals("RomIntNumber")) {
            String firstRomNumber = firstNumber;
            String secondRomNumber = secondNumber;
            int firstArabNumber = rom.indexOf(firstRomNumber); //определение первой цифры числа
            int secondArabNumber = rom.indexOf(secondRomNumber);//определение второй цифры числа
            //определение вида операции
            if (operationIndex == 0) { //сложение
                result = firstArabNumber + secondArabNumber;
            }
            if (operationIndex == 1) { //вычитание
                result = firstArabNumber - secondArabNumber;
            }
            if (operationIndex == 2) { //умножение
                result = firstArabNumber * secondArabNumber;
            }
            if (operationIndex == 3) {//деление
                result = firstArabNumber / secondArabNumber;
            }
            if (result<=0){
                throw new IOException(); //римское число не может быть отрицательным

            }
            String firstString;
            String secondString;
            if (result>0&&result<=10){ //для чисел имеющих одну цифру или равных десяти
                 romResult = rom.get(result);
            }
            if (result>10&&result<100){ //для чисел имеющих две цифры
                var firstCifra = (result+"").charAt(0);
                int firstCifraIndex = Integer.parseInt(Character.toString(firstCifra));
                firstString = rom2.get(firstCifraIndex);
                var secondCifra = (result+"").charAt(1)+"";
                int secondCifraIndex = Integer.parseInt(secondCifra);
                secondString = rom.get(secondCifraIndex);
                romResult = firstString+secondString;
            }
            if (result==100){ //для числа 100
                romResult = "С";
            }
            System.out.println(romResult);
        }
    }
}

