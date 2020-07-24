import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class HieghtProcessor {
    String[] names = {"Anne Shaw", "David Wood", "Liam Lynch", "Mary Kelly", "Pat Harty"};
    float[] heights = {1.62f, 1.75f, 1.91f, 1.69f, 1.78f};
    JButton statsButton, findButton;
    JFrame jFrameWindow;
    FlowLayout flowLayout;

    public HieghtProcessor(){
        jFrameWindow = new JFrame("Heights Processor");
        jFrameWindow.setSize(300,100);
        flowLayout = new FlowLayout();
        jFrameWindow.setLayout(flowLayout);
        jFrameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        statsButton = new JButton("Stats");
        jFrameWindow.add(statsButton);
        findButton = new JButton("Find Height");
        jFrameWindow.add(findButton);
        ButtonEventHandler handler = new ButtonEventHandler();
        statsButton.addActionListener(handler);
        findButton.addActionListener(handler);
        jFrameWindow.setVisible(true);

    }

    public static void main(String[] args)
    {
        HieghtProcessor guiApp = new HieghtProcessor();
    }

    private class ButtonEventHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == statsButton)
            {
                float tallest=0, sum=0, average=0;
                int atleast=0, percentage;
                String below="", tallname="";

                for(int i = 0; i<heights.length; i++)
                {
                    if(i ==0)
                        tallest = heights[i];
                    if(heights[i] > tallest)
                        {
                            tallest = heights[i];
                            tallname = names[i];
                        }
                    if(heights[i] >= 1.7)
                        atleast++;

                    sum += heights[i];

                }

                average = sum/heights.length;

                for(int i =0; i<heights.length;i++)
                {
                    if(heights[i] < average)
                        below += names[i] + " ";
                }

                percentage = (atleast/heights.length) * 100;

                JOptionPane.showMessageDialog(null,"The tallest person is " + tallname + " at " + tallest + "m\n" +
                        "The average height is " + String.format("%.2f",average) + " m\n" +
                        "The Percentage of people whose height is atleast 1.7m is " + percentage + "%\n" +
                        "The list of names of people whose height is below the average is: " + below, "Height Stats", JOptionPane.INFORMATION_MESSAGE);

            }

            if(e.getSource() == findButton)
            {
                String search;
                int found;

                search = JOptionPane.showInputDialog("Please enter the name of the person whose height you seek");

                found = binarySearch(search, names);

                if(found == -1)
                {
                    JOptionPane.showMessageDialog(null, "The name you searched for, " + search + ", was not found!","Name not found",JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "The height of this person is " + heights[found] + "m" ,"Name found",JOptionPane.INFORMATION_MESSAGE);

                }
            }

        }
    }

    public static int binarySearch(String search, String[] names)
    {
        int lower =0, higher=names.length-1, middle;

        while(lower<=higher)
        {
            middle = (lower+higher)/2;

            if(names[middle]!=null)
            {
                if(names[middle].compareTo(search) < 0)
                    lower = middle+1;
                else if(names[middle].compareTo(search) > 0)
                    higher = middle-1;
                else return middle;
            }
            else
                higher = middle-1;

        }
        return -1;
    }
}
