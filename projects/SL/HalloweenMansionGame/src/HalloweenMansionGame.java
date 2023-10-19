import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class HalloweenMansionGame {

    public static void main(String[] args) {
        // Cargar preguntas y respuestas desde los archivos
        List<String> questions = loadQuestions("fichero.txt");
        List<String> answers = loadAnswers("fichero2.txt");

        if (questions.isEmpty() || answers.isEmpty() || questions.size() != answers.size()) {
            System.out.println("Error al cargar preguntas y respuestas. Asegúrate de que los archivos tengan el formato correcto.");
            return;
        }

        // Representación de la mansión
        String[][] mansion = {
                {"🚪", "⬜", "⬜", "⬜"},
                {"⬜", "👻", "⬜", "⬜"},
                {"⬜", "⬜", "⬜", "👻"},
                {"⬜", "⬜", "🍭", "⬜"}
            };


        // Lógica del juego
        int playerRow = 0;
        int playerCol = 0;
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String currentRoom = mansion[playerRow][playerCol];
            System.out.println("Te encuentras en una habitación " + currentRoom);

            if (currentRoom.compareTo("👻")==0) { 
                // Manejar la aparición de un fantasma con dos preguntas
                if (random.nextDouble() < 0.1) {
                    System.out.println("¡Un fantasma te ha atrapado!");
                    // Preguntas y respuestas del fantasma
                    int questionIndex = random.nextInt(questions.size());
                    String question = questions.get(questionIndex);
                    String correctAnswer = answers.get(questionIndex);

                    System.out.println(question);
                    System.out.print("Tu respuesta: ");
                    String userAnswer = scanner.nextLine();

                    if (userAnswer.compareToIgnoreCase(correctAnswer)==0) {
                        System.out.println("¡Has respondido correctamente! El fantasma te permite avanzar.");
                    } else {
                        System.out.println("Respuesta incorrecta. El fantasma no te dejará avanzar.");
                        continue;
                    }
                }
            } else if (currentRoom.compareTo("🍭")==0) {
                System.out.println("¡Has encontrado la habitación de los dulces! ¡Felicidades, has ganado el juego!");
                break;
            }

            System.out.print("¿Hacia dónde quieres moverte? (norte/sur/este/oeste): ");
            String direction = scanner.nextLine().toLowerCase();

            // Actualizar la posición del jugador en la mansión
            switch (direction) {
                case "norte":
                    if (playerRow > 0) playerRow--;
                    break;
                case "sur":
                    if (playerRow < 3) playerRow++;
                    break;
                case "este":
                    if (playerCol < 3) playerCol++;
                    break;
                case "oeste":
                    if (playerCol > 0) playerCol--;
                    break;
                default:
                    System.out.println("Dirección no válida. Inténtalo de nuevo.");
                    break;
            }
        }
    }

    // Función para cargar preguntas desde un archivo
    private static List<String> loadQuestions(String filename) {
        List<String> questions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                questions.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questions;
    }

    // Función para cargar respuestas desde un archivo
    private static List<String> loadAnswers(String filename) {
        List<String> answers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                answers.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answers;
    }
}
