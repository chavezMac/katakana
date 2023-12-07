import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MultipleChoiceGame extends JFrame {
    private JLabel questionLabel;
    private ButtonGroup choiceGroup;
    private JRadioButton choice1, choice2, choice3, choice4;
    private int currentQuestionIndex = 0;
    private JLabel correctnessLabel;

    private JLabel scoreLabel;
    private int score = 0;
    private JLabel highScoreLabel;
    private int highScore = 0;

    private QuestionSet[] questionsAndAnswers = {
            new QuestionSet("Onamae wa?", new String[]{"Where is your hometown?", "What is your birth month?", "Which academic year?", "What is your name?"}, 4),
            new QuestionSet("Sumimasen", new String[]{"Good evening", "Nice to meet you", "Excuse me", "Good night"}, 3),
            new QuestionSet("Kyoo no otenki wa?", new String[]{"Today's weather is?", "Where are you from?", "What do you like?", "What academic year are you?"}, 1),
            new QuestionSet("What number is Juuyon?", new String[]{"19", "14", "16", "15"}, 2),
            new QuestionSet("What time is Kuji han?", new String[]{"8:30", "9:45", "8:45", "9:30"}, 4),
            new QuestionSet("372 in Japanese is?", new String[]{"Nanahyaku san ju ni", "Sanbyaku ni ju nana", "Yonhyaku nana ju ni", "Sanbyaku nana ju ni"}, 4),
            new QuestionSet("What is the meaning of コーヒー?", new String[]{"Rokku", "Koohii", "Hanbaagaa", "Yotto"}, 2),
            new QuestionSet("What is the meaning of ロック?", new String[]{"Yatto", "Kohii", "Rokku", "Rappu"}, 3),
            new QuestionSet("What is the meaning of ハンドバッグ?", new String[]{"Nihongo", "Hanbaagaa", "Handobaggu", "Furenchi doresshingu"}, 3),
            new QuestionSet("What is the meaning of こんばんは?", new String[]{"Good morning", "Good afternoon", "Good evening", "Good night"}, 3),
            new QuestionSet("What is the meaning of こんにちは?", new String[]{"Good morning", "Good afternoon", "Good evening", "Good night"}, 2),
            new QuestionSet("What is the meaning of おやすみなさい?", new String[]{"Good morning", "Good afternoon", "Good evening", "Good night"}, 4),
            new QuestionSet("What is the meaning of さようなら?", new String[]{"Good morning", "Good afternoon", "Good evening", "Good night"}, 3),
            new QuestionSet("What is the meaning of お元気ですか?", new String[]{"How are you?", "What is your name?", "Where are you from?", "What is your birth month?"}, 1),
            new QuestionSet("What is the meaning of お名前は?", new String[]{"How are you?", "What is your name?", "Where are you from?", "What is your birth month?"}, 2),
            new QuestionSet("What is the meaning of どこから来ましたか?", new String[]{"How are you?", "What is your name?", "Where are you from?", "What is your birth month?"}, 3),
            new QuestionSet("What is the meaning of 何月生まれですか?", new String[]{"How are you?", "What is your name?", "Where are you from?", "What is your birth month?"}, 4),
            new QuestionSet("What is the meaning of 何歳ですか?", new String[]{"How old are you?", "What is your name?", "Where are you from?", "What is your birth month?"}, 1),
            new QuestionSet("What is the meaning of 何年生ですか?", new String[]{"What academic year are you?", "What is your name?", "Where are you from?", "What is your birth month?"}, 1),
            new QuestionSet("What is the meaning of 一年生です。", new String[]{"I am a first year student.", "I am a second year student.", "I am a third year student.", "I am a fourth year student."}, 1),
            new QuestionSet("What is the meaning of 二年生です。", new String[]{"I am a first year student.", "I am a second year student.", "I am a third year student.", "I am a fourth year student."}, 2),
            new QuestionSet("What does 'Ohayou gozaimasu' mean?", new String[]{"Good morning", "Good evening", "Good night", "Hello"}, 1),
            new QuestionSet("How do you say 'Goodbye' in Japanese?", new String[]{"Arigato", "Sayonara", "Konnichiwa", "Sumimasen"}, 2),
            new QuestionSet("What is the Japanese word for 'water'?", new String[]{"Mizu", "Kaze", "Hi", "Tsuchi"}, 1),
            new QuestionSet("How do you ask 'What is your name?' in Japanese?", new String[]{"Nan desu ka?", "O-namae wa?", "Ikura desu ka?", "Kore wa nan desu ka?"}, 2),
            new QuestionSet("What does 'Hajimemashite' mean?", new String[]{"Goodbye", "Nice to meet you", "Excuse me", "Thank you"}, 2),
            new QuestionSet("Which hiragana character represents the sound 'ka'?", new String[]{"か", "き", "く", "け"}, 1),
            new QuestionSet("What is the Japanese word for 'cat'?", new String[]{"Inu", "Neko", "Tori", "Sakana"}, 2),
            new QuestionSet("How do you say 'I like sushi' in Japanese?", new String[]{"Sushi ga suki desu", "Sushi wa kirai desu", "Sushi wa daisuki desu", "Sushi wa amai desu"}, 3),
            new QuestionSet("What is the meaning of 'Arigato gozaimasu'?", new String[]{"Excuse me", "Thank you", "I'm sorry", "Goodbye"}, 2),
            new QuestionSet("How do you say 'yes' in Japanese?", new String[]{"Iie", "Hai", "Sumimasen", "Onegaishimasu"}, 2)
    };

    public MultipleChoiceGame() {
        setTitle("Multiple Choice Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initUI();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initUI() {
        JPanel questionPanel = new JPanel();
        questionLabel = new JLabel(getCurrentQuestion());
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        Font katakanaFont = new Font("Arial Unicode MS", Font.PLAIN, 20);
        questionLabel.setFont(katakanaFont);
        questionPanel.setLayout(new BorderLayout());
        questionPanel.add(questionLabel, BorderLayout.CENTER);

        // Initialize correctness label
        correctnessLabel = new JLabel();
        correctnessLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Initialize score label
        scoreLabel = new JLabel("Your Score: " + score);
        scoreLabel.setHorizontalAlignment(SwingConstants.NORTH_EAST);
        questionPanel.add(scoreLabel, BorderLayout.NORTH);

        highScoreLabel = new JLabel("High-Score: " + highScore);
        highScoreLabel.setHorizontalAlignment(SwingConstants.NORTH_EAST);
        questionPanel.add(highScoreLabel, BorderLayout.SOUTH);

        choiceGroup = new ButtonGroup();

        // Panel for choices
        JPanel choicesPanel = new JPanel();
        choice1 = new JRadioButton(getCurrentChoice(1));
        choice2 = new JRadioButton(getCurrentChoice(2));
        choice3 = new JRadioButton(getCurrentChoice(3));
        choice4 = new JRadioButton(getCurrentChoice(4));

        choiceGroup.add(choice1);
        choiceGroup.add(choice2);
        choiceGroup.add(choice3);
        choiceGroup.add(choice4);

        choicesPanel.setLayout(new GridLayout(4, 1));
        choicesPanel.add(choice1);
        choicesPanel.add(choice2);
        choicesPanel.add(choice3);
        choicesPanel.add(choice4);

        choice1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    checkAnswer();
                }
            }
        });
    
        choice2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    checkAnswer();
                }
            }
        });
    
        choice3.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    checkAnswer();
                }
            }
        });
    
        choice4.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    checkAnswer();
                }
            }
        });

        

        // Main frame layout
        setLayout(new BorderLayout());
        add(questionPanel, BorderLayout.NORTH);
        add(correctnessLabel, BorderLayout.SOUTH);
        add(choicesPanel, BorderLayout.CENTER);
    }

    private String getCurrentQuestion() {
        return questionsAndAnswers[currentQuestionIndex].getQuestion();
    }

    private String getCurrentChoice(int index) {
        return questionsAndAnswers[currentQuestionIndex].getChoices()[index - 1];
    }

    private void checkAnswer() {
        int correctAnswerIndex = questionsAndAnswers[currentQuestionIndex].getCorrectAnswerIndex();
        String correctAnswer = getCurrentChoice(correctAnswerIndex);
        String userAnswer = getSelectedChoice();

        if (userAnswer.equals(correctAnswer)) {
            correctnessLabel.setText("Correct!");
            score++;
            scoreLabel.setText("Your Score: " + score);
            if (score > highScore) {
                highScore = score;
                highScoreLabel.setText("High-Score: " + highScore);
            }
            goToNextQuestion();
        } else {
            correctnessLabel.setText("Incorrect!");
            score = 0;
        }
    }

    private void goToNextQuestion() {
        correctnessLabel.setText("");
        // Move to the next question
        currentQuestionIndex = (currentQuestionIndex + 1) % questionsAndAnswers.length;
        updateUI();
        choiceGroup.clearSelection();
    }

    private String getSelectedChoice() {
        if (choice1.isSelected()) {
            return choice1.getText();
        } else if (choice2.isSelected()) {
            return choice2.getText();
        } else if (choice3.isSelected()) {
            return choice3.getText();
        } else if (choice4.isSelected()) {
            return choice4.getText();
        } else {
            return "";
        }
    }

    private void updateUI() {
        questionLabel.setText(getCurrentQuestion());
        choice1.setText(getCurrentChoice(1));
        choice2.setText(getCurrentChoice(2));
        choice3.setText(getCurrentChoice(3));
        choice4.setText(getCurrentChoice(4));
        choiceGroup.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MultipleChoiceGame();
            }
        });
    }

    public class QuestionSet {
        private String question;
        private String[] choices;
        private int correctAnswerIndex;

        public QuestionSet(String question, String[] choices, int correctAnswerIndex) {
            this.question = question;
            this.choices = choices;
            this.correctAnswerIndex = correctAnswerIndex;
        }

        public String getQuestion() {
            return question;
        }

        public String[] getChoices() {
            return choices;
        }

        public int getCorrectAnswerIndex() {
            return correctAnswerIndex;
        }
    }
}