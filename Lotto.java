import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Lotto {
    int[] lottoNumbers = new int[6];
    int bonusball;
    boolean[] alreadyPicked = new boolean[43];
    JFrame jFrameWindow;
    JButton drawButton;
    JTextField checkField;
    JLabel checkLabel;
    FlowLayout flowLayout;


    public Lotto()
    {
        jFrameWindow = new JFrame("Lotto");
        jFrameWindow.setSize(235,100);
        flowLayout = new FlowLayout();
        jFrameWindow.setLayout(flowLayout);
        drawButton = new JButton("Draw Numbers");
        jFrameWindow.add(drawButton);
        checkLabel = new JLabel("Check numbers");
        jFrameWindow.add(checkLabel);
        checkField = new JTextField(10);
        jFrameWindow.add(checkField);
        ButtonEventHandler handler = new ButtonEventHandler();
        drawButton.addActionListener(handler);
        TextFieldEventHandler handle = new TextFieldEventHandler();
        checkField.addActionListener(handle);
        jFrameWindow.setVisible(true);
        jFrameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args)
    {
        Lotto guiApp = new Lotto();
    }

    private class ButtonEventHandler implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            int lottonum;
            for(int i = 0;i<lottoNumbers.length;i++)
            {

                lottonum = (int)(Math.random()*42+1);
                 while(alreadyPicked[lottonum])
                 {
                     lottonum = (int)(Math.random()*42+1);
                 }

                 alreadyPicked[lottonum] = true;

                 lottoNumbers[i] = lottonum;

            }

            bonusball = (int)(Math.random()*42+1);

            while(alreadyPicked[bonusball])
                bonusball = (int)(Math.random()*42+1);

            alreadyPicked = new boolean[43];
            Arrays.sort(lottoNumbers);

            int even=0, odd=0, high=0,low=0, consecutive=0;

            for(int i = 0; i<lottoNumbers.length; i++)
            {
                if(lottoNumbers[i]%2 == 0)
                {
                    even++;
                }
                else
                    odd++;

                if(lottoNumbers[i] >= 22)
                {
                    high++;
                }
                else
                {
                    low++;
                }

                if( i < lottoNumbers.length-1 && (lottoNumbers[i+1] - lottoNumbers[i] == 1))
                    consecutive++;
            }

            JOptionPane.showMessageDialog(null,"Lotto Numbers Drawn are: " + Arrays.toString(lottoNumbers) + " Bonus Ball: " + bonusball +
                    "\n\n\n#Even lotto numbers: " + even +
                    "\n#odd lotto numbers: " + odd +
                    "\nPercentage of high lotto numbers:" + String.format("%.2f",(float)(high/6*100)) +
                    "\nPercentage of low lotto numbers:" + String.format("%.2f",(float)(low/6*100)) +
                    "\n#Pairs of consecutive lotto numbers: " + consecutive);
        }
    }

    private class TextFieldEventHandler implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            String checktext,bonus = "No";
            int match = 0, number;
            boolean linear;

            checktext = checkField.getText();

            for(int i = 0; i<checktext.length(); i += 3)
            {
                number = Integer.parseInt(checktext.substring(i,i+2))  ;

                linear = linearSearch(number, lottoNumbers);

                if(linear)
                       match++;

                if(number == bonusball)
                    bonus = "yes";

            }

            JOptionPane.showMessageDialog(null,"Lotto Numbers Matched: " + match + "  Bonus Ball Match: " + bonus);
            
        }

    }

    public static boolean linearSearch(int number, int lotto[])
    {

        for(int i=0; i<6; i++)
        {
            if(number == lotto[i])
                return true;
        }
        return false;

    }


}
