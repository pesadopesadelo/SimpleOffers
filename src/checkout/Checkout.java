package checkout;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Checkout {

    private static final int PRECO_BANANA = 80;    // price in pence
    private static final int PRECO_MORANGO = 150; // price in pence

    public static int calcularTotal(List<String> itens) {
        // Count items (case-insensitive)
        Map<String, Long> contagemItens = itens.stream()
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(item -> item, Collectors.counting()));

        long bananas = contagemItens.getOrDefault("banana", 0L);
        long morangos = contagemItens.getOrDefault("strawberry", 0L);

        // Apply promotions
        long bananasPagar = (bananas / 3) * 2 + (bananas % 3); // 3 for 2 promotion
        long morangosPagar = (morangos / 2) + (morangos % 2); // Buy one, get one free

        return (int) ((bananasPagar * PRECO_BANANA) + (morangosPagar * PRECO_MORANGO));
    }

    public static String formatarPreco(int pence) {
        return String.format("£%.2f", pence / 100.0);
    }

    public static void main(String[] args) {
        List<String> carrinho = List.of("Banana", "Banana", "Strawberry", "Banana", "Strawberry");
        int total = calcularTotal(carrinho);
        System.out.println("Total with promotions: " + formatarPreco(total)); 
        // Example:
        // Banana, Banana, Strawberry, Banana, Strawberry
        // Bananas: 3 -> pay for 2 (3 for 2 promotion)
        // Strawberries: 2 -> pay for 1 (buy one get one free)
        // Total = (2 * 80p) + (1 * 150p) = £3.10
    }
}