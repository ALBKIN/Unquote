// MainActivity based on my prev exercises modified to check if my methods were correct

package com.codecademy.unquote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // TODO #1: add integer member variables here
    int currentQuestionIndex;
    int totalCorrect;
    int totalQuestions;
    ArrayList<Question> questions;

    // TODO 3-A: Declare View member variables
    ImageView questionImageView;
    TextView questionTextView;
    TextView questionsRemainingTextView;
    Button answer0Button;
    Button answer1Button;
    Button answer2Button;
    Button answer3Button;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO 2-G: Show app icon in ActionBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_unquote_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setElevation(0);

        setContentView(R.layout.activity_main);

        // TODO 3-B: assign View member variables
        questionImageView = findViewById(R.id.iv_main_question_image);
        questionTextView = findViewById(R.id.tv_main_question_title);
        questionsRemainingTextView = findViewById(R.id.tv_main_questions_remaining_count);
        answer0Button = findViewById(R.id.btn_main_answer_0);
        answer1Button = findViewById(R.id.btn_main_answer_1);
        answer2Button = findViewById(R.id.btn_main_answer_2);
        answer3Button = findViewById(R.id.btn_main_answer_3);
        submitButton = findViewById(R.id.btn_main_submit_answer);

        // TODO 4-E: set onClickListener for each answer Button

        answer0Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAnswerSelected(0);
            }
        });

        answer1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAnswerSelected(1);
            }
        });

        answer2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAnswerSelected(2);
            }
        });

        answer3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAnswerSelected(3);
            }
        });

        // TODO 5-A: set onClickListener for the submit answer Button

        startNewGame();
    }

    // TODO 3-F: displayQuestion(Question question) {...}
    void displayQuestion(Question question) {
        questionImageView.setImageResource(question.imageId);
        questionTextView.setText(question.questionText);
        answer0Button.setText(question.answer0);
        answer1Button.setText(question.answer1);
        answer2Button.setText(question.answer2);
        answer3Button.setText(question.answer3);

    }

    // TODO 3-C: displayQuestionsRemaining(int questionRemaining) {...}
    void displayQuestionsRemaining(int questionRemaining) {
        questionsRemainingTextView.setText(String.valueOf(questionRemaining));
    }

    // TODO 4-A: onAnswerSelected(int answerSelected) {...}
    void onAnswerSelected(int answerSelected) {
        Question currentQuestion = getCurrentQuestion();
        currentQuestion.playerAnswer = answerSelected;
        if (answerSelected == 0) {
            answer0Button.setText("✔ " + currentQuestion.answer0);
        } else if (answerSelected == 1) {
            answer1Button.setText("✔ " + currentQuestion.answer1);
        } else if (answerSelected == 2) {
            answer2Button.setText("✔ " + currentQuestion.answer2);
        } else {
            answer3Button.setText("✔ " + currentQuestion.answer3);
        }
    }


    // TODO #3 add startNewGame() here
    void startNewGame() {
        questions = new ArrayList<>();
        // TODO 2-H: Provide actual drawables for each of these questions!
        Question Pierwsze = new Question(R.drawable.img_quote_0, "Pretty good advice, and perhaps a scientist did say it… Who actually did??", "Albert Einstein", "Isaac Newton", "Rita Mae Brown", "Rosalind Franklin", 3);
        Question Drugie = new Question(R.drawable.img_quote_1, "Was honest Abe honestly quoted? Who authored this pithy bit of wisdom?", "Edward Stieglitz", "Maya Angelou", "Abraham Lincoln", "Ralph Waldo Emerson", 0);
        Question Trzecie = new Question(R.drawable.img_quote_2, "Easy advice to read, difficult advice to follow — who actually said it?", "Martin Luther King Jr.", "Mother Teresa", "Fred Rogers", "Oprah Winfrey", 1);
        Question Czwarte = new Question(R.drawable.img_quote_3, "Insanely inspiring, insanely incorrect (maybe). Who is the true source of this inspiration", "Nelson Mandela", "Harriet Tubman", "Mahatma Gandhi", "Nicholas Klein", 3);
        Question Piate = new Question(R.drawable.img_quote_4, "A peace worth striving for — who actually reminded us of this?", "Malala Yousafzai", "Martin Luther King Jr.", "Liu Xiaobo", "Dalai Lama", 1);
        Question Szoste = new Question(R.drawable.img_quote_5, "Unfortunately, true — but did Marilyn Monroe convey it or did someone else?", "Laurel Thatcher Ulrich", "Eleanor Roosevelt", "Marilyn Monroe", "Queen Victoria", 0);
        questions.add(Pierwsze);
        questions.add(Drugie);
        questions.add(Trzecie);
        questions.add(Czwarte);
        questions.add(Piate);
        questions.add(Szoste);
        totalCorrect = 0;
        totalQuestions = questions.size();
        Question firstQuestion = chooseNewQuestion();

        // TODO 3-D.ii: Uncomment the line below after implementing displayQuestionsRemaining(int)
        displayQuestionsRemaining(questions.size());

        // TODO 3-H.ii: Uncomment after implementing displayQuestion(Question)
        displayQuestion(firstQuestion);
    }

    // TODO #4 add chooseNewQuestion() here
    Question chooseNewQuestion() {
        int nrPytania = generateRandomNumber(questions.size());
        // może tak zamiast next: currentQuestionIndex = questions.indexOf(nrPytania);
        currentQuestionIndex = nrPytania;
        return questions.get(currentQuestionIndex);
    }

    // TODO #5 add getCurrentQuestion() here
    Question getCurrentQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        return currentQuestion;
    }

    // TODO #6 add onAnswerSubmission() here
    public void onAnswerSubmission() {
        Question currentQuestion = getCurrentQuestion();
        if (currentQuestion.isCorrect()) {
//                totalCorrect++;
            totalCorrect = totalCorrect + 1;
        }
        questions.remove(currentQuestion);
        // TODO 3-D.i: Uncomment the line below after implementing displayQuestionsRemaining(int)
        displayQuestionsRemaining(questions.size());
        if (questions.size() == 0) {

            // TODO 5-D: Show a popup instead
            System.out.println(getGameOverMessage(totalCorrect, totalQuestions));
        } else {
            chooseNewQuestion();
            // TODO 3-H.i: uncomment after implementing displayQuestion(Question)
            displayQuestion(getCurrentQuestion());
        }
    }

    int generateRandomNumber(int max) {
        double randomNumber = Math.random();
        double result = max * randomNumber;
        return (int) result;
    }

    String getGameOverMessage(int totalCorrect, int totalQuestions) {
        if (totalCorrect == totalQuestions) {
            return "You got all " + totalQuestions + " right! You won!";
        } else {
            return "You got " + totalCorrect + " right out of " + totalQuestions + ". Better luck next time!";
        }
    }
}