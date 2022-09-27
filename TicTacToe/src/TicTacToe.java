import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class TicTacToe implements ActionListener {
    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_turn;
    TicTacToe(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.getContentPane().setBackground(new Color(50, 50 , 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(25, 25 ,25 ));
        textfield.setForeground(new Color(25, 255 ,0));
        textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,800,100);

        button_panel.setLayout(new GridLayout(3,3));
        button_panel.setBackground(new Color(150, 150 ,150 ));

        for (int i = 0; i < 9; i++){
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.ITALIC, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        title_panel.add(textfield);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);

        firstTurn();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++){
            if (e.getSource() == buttons[i]){
                if (player1_turn){
                    if (buttons[i].getText().equals("")){
                        buttons[i].setForeground(new Color(255,0,0));
                        buttons[i].setText("X");
                        player1_turn = false;
                        textfield.setText("O turn");
                        check();
                    }
                } else {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setForeground(new Color(0, 0, 255));
                        buttons[i].setText("O");
                        player1_turn = true;
                        textfield.setText("X turn");
                        check();
                    }
                }
            }
        }
    }

    public void firstTurn(){
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e){
//            e.printStackTrace();
//        }

        if (random.nextInt(2) == 0){
            player1_turn = true;
            textfield.setText("X Turn");

        } else {
            player1_turn = false;
            textfield.setText("O Turn");
        }
    }

    public void check(){
        checkWin("X");
        checkWin("O");
    }

    public void checkWin(String s){
        HashSet<Integer> nums = new HashSet<>();
        // check row
        for (int i = 0; i < 9; i+= 3){
            for (int j = i; j < i + 3; j++){
                if (buttons[j].getText().equals(s)){
                    nums.add(j);
                }
                if (nums.size() == 3){
                    winner(nums, s);
                }
            }
            nums.clear();
        }
        // check col
        for (int i = 0; i < 3; i++){
            for (int j = i; j < i + 9; j+= 3){
                if (buttons[j].getText().equals(s)){
                    nums.add(j);
                }
                if (nums.size() == 3){
                    winner(nums, s);
                }
            }
            nums.clear();
        }

        // check diag

        if((buttons[0].getText().equals(s)) &&
                (buttons[4].getText().equals(s)) &&
                (buttons[8].getText().equals(s))
        ) {
            nums.add(0);
            nums.add(4);
            nums.add(8);
            winner(nums, s);
        }
        if((buttons[2].getText().equals(s)) &&
                (buttons[4].getText().equals(s)) &&
                (buttons[6].getText().equals(s))
        ) {
            nums.add(2);
            nums.add(4);
            nums.add(6);
            winner(nums, s);        }
    }

    public void winner(HashSet<Integer> nums, String s) {
        for (int n : nums) {
            buttons[n].setBackground(Color.GREEN);
        }

        for(int i = 0;i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        textfield.setText(s + " wins");
    }

}
