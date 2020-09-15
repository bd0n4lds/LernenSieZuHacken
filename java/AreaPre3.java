import javax.swing.JOptionPane;

public class AreaPre2{
    public static void main(String[] args){
        String length1 = JOptionPane.showInputDialog(null,"Enter the length", "Length", JOptionPane.QUESTION_MESSAGE);
        String width1 = JOptionPane.showInputDialog(null, "Enter the width", "Width", JOptionPane.QUESTION_MESSAGE);

        double length2 = Double.parseDouble(length1);
        double width2 = Double.parseDouble(width1);

        Apples(length2, width2);
    }

    public static void Apples(double  length, double width){
        double area = (length * width);
        double perimeter = length + width;
        System.out.println("A rectangle with a length of " + length + " and a width of " + width + " has an area of " + area + " and a perimeter of " + perimeter);
    }
}