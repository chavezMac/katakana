import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Katakana extends JFrame {
    private JLabel cardLabel;
    private JButton exitButton;
    private JTextField guessField;
    private JLabel scoreLabel;
    private JLabel highScoreLabel;

    private int currentCard;
    private int nextCard;
    private int score = 0;
    private int highScore = 0;

    // Map card values to their corresponding Katakana symbols
    private Map<Integer, String> katakanaMap;
    private Map<String, String> phoneticMap;

    public Katakana() {
        // Set up the frame
        setTitle("Katakana Card Guessing Game");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the background color of the frame
        getContentPane().setBackground(new Color(255, 183, 197));

        setLayout(new GridLayout(2, 2, 5, 5));

        // Initialize components
        cardLabel = new JLabel("Card: ");
        guessField = new JTextField(2);
        scoreLabel = new JLabel("Your Score: " + score);
        highScoreLabel = new JLabel("Your High-Score: " + highScore);
        exitButton = new JButton("Exit");

        // Set font for Katakana characters
        Font katakanaFont = new Font("MS Gothic", Font.BOLD, 24);
        cardLabel.setFont(katakanaFont);

        exitButton.setBackground(new Color(255, 183, 197)); // Set the background color of the button
        exitButton.setFont(new Font("Arial", Font.BOLD, 14));

        // Add components to the frame
        add(cardLabel);
        add(scoreLabel);
        //Add a blank label to fill the space
        add(highScoreLabel);
        add(new JLabel("Your Guess: "));
        add(guessField);
        add(exitButton);

        // Register event listeners
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        guessField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGuess(true);
            }
        });

        // Initialize game state
        initializeKatakanaMap();
        initializePhoneticMap();
        currentCard = drawCard();
        updateCardLabel();

        // Display the frame
        setVisible(true);
    }

    private int drawCard() {
        Random random = new Random();
        return random.nextInt(44) + 1; //Gets a random card 
    }

    private void dealCard() {
        nextCard = drawCard();
        currentCard = nextCard;
        updateCardLabel();
        guessField.setText(""); // Clear the guess field for the new card
    }

    private void updateScoreLabel() {
        scoreLabel.setText("Your Score: " + score);
        repaint(); // Repaint the frame to reflect the updated score
    }


    private void handleGuess(boolean isTextField) {
        String guess = guessField.getText();
        if (guess.equals(phoneticMap.get(katakanaMap.get(currentCard)))) {
            JOptionPane.showMessageDialog(this, "Correct!");
            score++;
            updateScoreLabel();
            if(score > highScore) {
                highScore = score;
                highScoreLabel.setText("Your High-Score: " + highScore);
            }
            if (isTextField) {
                dealCard();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect!");
            score = 0;
            updateScoreLabel();
            if(isTextField) {
                dealCard();
            }
        }
    }

    private void updateCardLabel() {
        cardLabel.setText("Card: " + katakanaMap.get(currentCard));
    }

    private void initializeKatakanaMap() {
        katakanaMap = new HashMap<>();
        // Add mappings for card values to Katakana symbols
        katakanaMap.put(1, "ア");
        katakanaMap.put(2, "イ");
        katakanaMap.put(3, "ウ");
        katakanaMap.put(4, "エ");
        katakanaMap.put(5, "オ");
        katakanaMap.put(6, "カ");
        katakanaMap.put(7, "キ");
        katakanaMap.put(8, "ク");
        katakanaMap.put(9, "ケ");
        katakanaMap.put(10, "コ");
        katakanaMap.put(11, "サ");
        katakanaMap.put(12, "シ");
        katakanaMap.put(13, "ス");
        katakanaMap.put(14, "セ");
        katakanaMap.put(15, "ソ");
        katakanaMap.put(16, "タ");
        katakanaMap.put(17, "チ");
        katakanaMap.put(18, "ツ");
        katakanaMap.put(19, "テ");
        katakanaMap.put(20, "ト");
        katakanaMap.put(21, "ナ");
        katakanaMap.put(22, "ニ");
        katakanaMap.put(23, "ヌ");
        katakanaMap.put(24, "ネ");
        katakanaMap.put(25, "ノ");
        katakanaMap.put(26, "ハ");
        katakanaMap.put(27, "ヒ");
        katakanaMap.put(28, "フ");
        katakanaMap.put(29, "ヘ");
        katakanaMap.put(30, "ホ");
        katakanaMap.put(31, "マ");
        katakanaMap.put(32, "ミ");
        katakanaMap.put(33, "ム");
        katakanaMap.put(34, "メ");
        katakanaMap.put(35, "モ");
        katakanaMap.put(36, "ヤ");
        katakanaMap.put(37, "ユ");
        katakanaMap.put(38, "ヨ");
        katakanaMap.put(39, "ラ");
        katakanaMap.put(40, "リ");
        katakanaMap.put(41, "ル");
        katakanaMap.put(42, "レ");
        katakanaMap.put(43, "ロ");
        katakanaMap.put(44, "ワ");
        katakanaMap.put(45, "ヲ");
        katakanaMap.put(46, "ン");
    }

    //Create phonetic map
    private void initializePhoneticMap() {
        phoneticMap = new HashMap<>();
        // Add mappings for card values to Katakana symbols
        phoneticMap.put("ア", "a");
        phoneticMap.put("イ", "i");
        phoneticMap.put("ウ", "u");
        phoneticMap.put("エ", "e");
        phoneticMap.put("オ", "o");
        phoneticMap.put("カ", "ka");
        phoneticMap.put("キ", "ki");
        phoneticMap.put("ク", "ku");
        phoneticMap.put("ケ", "ke");
        phoneticMap.put("コ", "ko");
        phoneticMap.put("サ", "sa");
        phoneticMap.put("シ", "shi");
        phoneticMap.put("ス", "su");
        phoneticMap.put("セ", "se");
        phoneticMap.put("ソ", "so");
        phoneticMap.put("タ", "ta");
        phoneticMap.put("チ", "chi");
        phoneticMap.put("ツ", "tsu");
        phoneticMap.put("テ", "te");
        phoneticMap.put("ト", "to");
        phoneticMap.put("ナ", "na");
        phoneticMap.put("ニ", "ni");
        phoneticMap.put("ヌ", "nu");
        phoneticMap.put("ネ", "ne");
        phoneticMap.put("ノ", "no");
        phoneticMap.put("ハ", "ha");
        phoneticMap.put("ヒ", "hi");
        phoneticMap.put("フ", "fu");
        phoneticMap.put("ヘ", "he");
        phoneticMap.put("ホ", "ho");
        phoneticMap.put("マ", "ma");
        phoneticMap.put("ミ", "mi");
        phoneticMap.put("ム", "mu");
        phoneticMap.put("メ", "me");
        phoneticMap.put("モ", "mo");
        phoneticMap.put("ヤ", "ya");
        phoneticMap.put("ユ", "yu");
        phoneticMap.put("ヨ", "yo");
        phoneticMap.put("ラ", "ra");
        phoneticMap.put("リ", "ri");
        phoneticMap.put("ル", "ru");
        phoneticMap.put("レ", "re");
        phoneticMap.put("ロ", "ro");
        phoneticMap.put("ワ", "wa");
        phoneticMap.put("ヲ", "wo");
        phoneticMap.put("ン", "n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Katakana game = new Katakana();
            }
        });
    }
}
