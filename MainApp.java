import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

class ImagePanel extends JPanel {
    private Image backgroundImage;

    public ImagePanel(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            // Resize the image to match the panel size
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

public class MainApp {
    private JFrame mainFrame;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainApp app = new MainApp();
            app.createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        JFrame mainFrame = new JFrame("Game Selection Menu");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon("./cherry-blossom-background.jpeg");
        Image originalImage = icon.getImage();

        ImagePanel backgroundLabel = new ImagePanel(originalImage);
        backgroundLabel.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Games");

        JMenuItem game1Item = new JMenuItem("Multiple Choice Game");
        JMenuItem game2Item = new JMenuItem("Guess the Katakana Game");

        game1Item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openGameFrame(new MultipleChoiceGame());
            }
        });

        game2Item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openGameFrame(new Katakana());
            }
        });

        gameMenu.add(game1Item);
        gameMenu.add(game2Item);
        menuBar.add(gameMenu);

        mainFrame.setJMenuBar(menuBar);
        mainFrame.setSize(400,400);
        mainFrame.setContentPane(backgroundLabel);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    private static void openGameFrame(JFrame gameFrame) {
        gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gameFrame.setSize(600, 400);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
    }
    
}