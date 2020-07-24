import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ValentiaTemperatures {

    String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};
    float[] temperatures = {7.3f,7.2f,8.1f,9.3f,11.5f,13.6f,15.3f,15.4f,13.9f,11.5f,9.3f,7.8f};
    JFrame jFrameWindow;
    JButton generate, sort;


    public ValentiaTemperatures()
    {
       jFrameWindow = new JFrame("Valentia Temperatures");
       jFrameWindow.setSize(300,100);
       FlowLayout flowLayout = new FlowLayout();
       jFrameWindow.setLayout(flowLayout);
       jFrameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       generate = new JButton("Generate Stats");
       jFrameWindow.add(generate);
       sort = new JButton("Sort Data");
       jFrameWindow.add(sort);
       jFrameWindow.setVisible(true);
       EventHandler handler = new EventHandler();
       generate.addActionListener(handler);
       sort.addActionListener(handler);
    }

    public static void main(String args[])
    {
        ValentiaTemperatures guiApp = new ValentiaTemperatures();
    }

    private class EventHandler implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == generate)
            {
                float sum=0, highest=0, average;
                String above="",highestMonth="";

                for(int i=0; i<temperatures.length;i++)
                {
                    sum += temperatures[i];

                    if(i == 0)
                        highest = temperatures[i];
                    else if(temperatures[i] > highest)
                    {
                        highest = temperatures[i];
                        highestMonth = months[i];
                    }


                }

                average = sum/temperatures.length;

                for(int i=0; i<temperatures.length; i++)
                {
                    if(temperatures[i] > average)
                        above += months[i]+" ";
                }

                JOptionPane.showMessageDialog(null,"Average MMT: " + String.format("%.2f",average) +
                        "\nHighest MMT: " + highest + " Month: " + highestMonth +
                        "\nList of months with above average MMT: " + above);


            }
            else if(e.getSource() == sort)
            {
                String text="";
                selectionSort(temperatures,months);

                JTextArea jTextArea = new JTextArea(14,30);
                Font font = new Font("monospaced",Font.PLAIN,12);
                jTextArea.setFont(font);

                jTextArea.append(String.format("%-10s%-20s\n\n", "MMI", "Month"));

                for(int i = 0; i<temperatures.length;i++)
                {
                    text += String.format("%-10.1f%-20s\n",temperatures[i],months[i]);
                }

                jTextArea.append(text);

                JOptionPane.showMessageDialog(null,jTextArea,"Sorted Temperatures",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public static void selectionSort(float[] temperatures, String[] months)
    {
        float smallest, temp;
        int sub;

        for(int i = 0; i<temperatures.length; i++)
        {
            smallest = temperatures[i];
            sub = i;

            for(int j = i+1; j<temperatures.length; j++)
            {
                if(temperatures[j] < smallest )
                {
                    smallest = temperatures[j];
                    sub = j;
                }

                temp = temperatures[i];
                temperatures[i] = temperatures[sub];
                temperatures[sub] = temp;
            }
        }
    }


}
