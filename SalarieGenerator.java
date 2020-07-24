//ElizabethOchanyaIdoko.Java
/*This program Generates 100 salaries that we will be able to view and sort*/

import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class SalarieGenerator {
    int[] salaries = new int[100];
    JFrame jFrameWindow;
    JButton view,stats;
    FlowLayout flowlayout;
    boolean viewed = false;


    public SalarieGenerator()
    {
        jFrameWindow = new JFrame("Salaries");
        flowlayout = new FlowLayout();
        jFrameWindow.setSize(300,150);
        jFrameWindow.setLayout(flowlayout);
        jFrameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view = new JButton("View Salaries");
        jFrameWindow.add(view);
        stats = new JButton("Salary Stats");
        jFrameWindow.add(stats);
        jFrameWindow.setVisible(true);
        EventHandler handler = new EventHandler();
        view.addActionListener(handler);
        stats.addActionListener(handler);
    }

    public static void main(String args[])
    {
        SalarieGenerator guiApp = new SalarieGenerator();
    }

    private class EventHandler implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {


            if(e.getSource() == view)
            {
                Arrays.toString(salaries);
                viewed = true;
                JTextArea jTextArea = new JTextArea(14,30);
                String text="";
                int count=1;
               for(int i = 0; i<salaries.length; i++)
               {
                   salaries[i] = (int)(Math.random()*60000 + 20000);

                   text += String.format("%-10d",salaries[i]);


                   if(count%10==0)
                       text += "\n";

                   count++;
               }

               jTextArea.append(text);

                JOptionPane.showMessageDialog(null,jTextArea,"Salary Data",JOptionPane.INFORMATION_MESSAGE);




            }
            else
            {
                if(!viewed)
                    JOptionPane.showMessageDialog(null,"The array has not yet been populated with random salary values", "No Salary Data",JOptionPane.ERROR_MESSAGE);
                else
                {
                    int largest=0,smallest=0,range=0,sum=0;
                    float average;
                    sortSalaries(salaries);

                    for(int i = 0; i<salaries.length; i++)
                    {
                        sum+=salaries[i];

                        if(salaries[i] >= 35000 && salaries[i] <= 45000)
                        {
                            range++;
                        }
                    }

                    average = (float) (sum/100);

                    JTextArea jTextArea = new JTextArea(14,30);
                    String text="";
                    int count=1;
                    for(int i = 0; i<salaries.length; i++)
                    {
                        text += String.format("%-10d",salaries[i]);

                        if(count%10==0)
                            text += "\n";

                        count++;
                    }

                    jTextArea.append(text);
                    jTextArea.append("\n\nLargest salary in the array: $"+salaries[99] + "\nSmallest salary in the array: $" + salaries[0] +
                            "\nAverage Salary: $" + String.format("%.2f",average) + "\nMedian salary: $" + salaries[49] +
                            "\n% Salaries in the range $35000-$45000: " + range + "%");

                    JOptionPane.showMessageDialog(null,jTextArea,"Salary Stats",JOptionPane.INFORMATION_MESSAGE);




                }
            }
        }
    }

    public static void sortSalaries(int[] salaries)
    {
        int smallest, temp;
        int sub;

        for (int i = 0; i < salaries.length-1; i++)
        {
            smallest = salaries[i];
            sub = i;

            for (int j = i+1; j < salaries.length; j++)
                if (salaries[j] < smallest)
                {
                    smallest = salaries[j];
                    sub = j;
                }

            temp = salaries[i];
            salaries[i] = salaries[sub];
            salaries[sub] = temp;
        }

    }


}
