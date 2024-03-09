import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    private String questionText;
    private List<String> options;
    private int correctOption;

    public Question(String questionText, List<String> options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }
}

class Quiz {
    private List<Question> questions;
    private int currentQuestionIndex;
    private int score;
    private Timer timer;

    public Quiz(List<Question> questions) {
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.score = 0;
        this.timer = new Timer();
    }

    public void startQuiz() {
        if (currentQuestionIndex < questions.size()) {
            displayQuestion();
            startTimer();
        } else {
            endQuiz();
        }
    }
public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }
    private void displayQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        System.out.println(currentQuestion.getQuestionText());

        List<String> options = currentQuestion.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    private void startTimer() {
timer =new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime is  up! Moving to the next question.");
                checkAnswer(-1); // Assume -1 as no answer submitted
                currentQuestionIndex++;
                startQuiz();
            }
        }, 10000); // 10 seconds timer
    }

    public void submitAnswer(int selectedOption) {
        checkAnswer(selectedOption - 1); // Adjusting index
        currentQuestionIndex++;
        timer.cancel(); // Cancel the timer for the current question
        startQuiz();
    }

    private void checkAnswer(int selectedOption) {
        Question currentQuestion = questions.get(currentQuestionIndex);
        if (selectedOption == currentQuestion.getCorrectOption()) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Incorrect! The correct answer was: " + (currentQuestion.getCorrectOption() + 1));
        }
    }

    private void endQuiz() {
        System.out.println("\nQuiz completed!");
        System.out.println("Your final score: " + score + "/" + questions.size());
        System.out.println("Thank you for playing!");
    }
}

public class QuizApplication {
    public static void main(String[] args) {
        // Create quiz questions
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?", List.of("Berlin", "Paris", "London"), 1));
        questions.add(new Question("What is the largest mammal?", List.of("Elephant", "Blue Whale", "Giraffe"), 2));
        questions.add(new Question("Which planet is known as the Red Planet?", List.of("Mars", "Venus", "Jupiter"), 0));
questions.add(new Question("Which planet is known as the GasGiants?", List.of("Mars", "Venus", "Jupiter"), 2));

        // Create a quiz
        Quiz quiz = new Quiz(questions);

        // Start the quiz
        quiz.startQuiz();

        // Simulate user input (for demonstration purposes)
        Scanner scanner = new Scanner(System.in);
        while (quiz!= null && quiz.getCurrentQuestionIndex() < questions.size()) {
            System.out.print("\nEnter your answer (1-3) or 0 to exit: ");
            int selectedOption = scanner.nextInt();

            if (selectedOption == 0) {
                System.out.println("Quiz aborted.");
                break;
            }

            if (selectedOption >= 1 && selectedOption <= 3) {
                quiz.submitAnswer(selectedOption);
            } else {
                System.out.println("Invalid option. Please enter a number between 1 and 3.");
            }
        }
    }
}
