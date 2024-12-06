package org.example;

import java.util.*;

// Ta klasa odpowiada za generowanie hasła
public class PasswordGenerator {
    // Definicja zestawów znaków dla różnych kategorii
    public static final String LOWERCASE_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
    public static final String UPPERCASE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMBERS = "0123456789";
    public static final String SPECIAL_SYMBOLS = "!@#$%^&*()-_=+[]{};:,.<>/?";

    // Pole do przechowywania generatora losowego
    private final Random random;

    // Mapa do mapowania opcji (wielkie litery, cyfry itp.) na odpowiednie zestawy znaków
    private final Map<String, String> characterPools;

    // Konstruktor klasy
    public PasswordGenerator() {
        random = new Random();

        // Inicjalizacja mapy, która będzie przechowywać dostępne zestawy znaków
        characterPools = new HashMap<>();
        characterPools.put("lowercase", LOWERCASE_CHARACTERS);
        characterPools.put("uppercase", UPPERCASE_CHARACTERS);
        characterPools.put("numbers", NUMBERS);
        characterPools.put("symbols", SPECIAL_SYMBOLS);
    }

    // Funkcja generująca hasło w oparciu o wybrane opcje
    public String generatePassword(int length, boolean includeUppercase, boolean includeLowercase, boolean includeNumbers,
                                   boolean includeSpecialSymbols) {

        StringBuilder passwordBuilder = new StringBuilder();

        // Używamy zestawu znaków do generowania hasła
        List<String> validCharacterSets = new ArrayList<>();
        if (includeUppercase) validCharacterSets.add(characterPools.get("uppercase"));
        if (includeLowercase) validCharacterSets.add(characterPools.get("lowercase"));
        if (includeNumbers) validCharacterSets.add(characterPools.get("numbers"));
        if (includeSpecialSymbols) validCharacterSets.add(characterPools.get("symbols"));

        // Jeśli żaden zestaw nie jest wybrany, zwróć pusty ciąg
        if (validCharacterSets.isEmpty()) {
            return "";
        }

        // Budowanie hasła
        for (int i = 0; i < length; i++) {
            // Wybór losowego zestawu znaków
            String randomSet = validCharacterSets.get(random.nextInt(validCharacterSets.size()));

            // Losowanie indeksu w tym zestawie
            int randomIndex = random.nextInt(randomSet.length());

            // Dodanie losowego znaku do hasła
            passwordBuilder.append(randomSet.charAt(randomIndex));
        }

        return passwordBuilder.toString();
    }
}
