import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
public class Main {
public  static class TextEditor implements ActionListener {
   JFrame frame = new JFrame();
   JMenuBar menuBar = new JMenuBar();
   JMenu file = new JMenu("File");
   JMenu edit = new JMenu("Edit");
   JMenuItem newFile = new JMenuItem("New window");
   JMenuItem saveFile = new JMenuItem("Save File");
   JMenuItem openFile = new JMenuItem("Open File");
   JMenuItem cut = new JMenuItem("Cut");
   JMenuItem copy = new JMenuItem("Copy");
   JMenuItem paste = new JMenuItem("Paste");
   JMenuItem selectAll = new JMenuItem("Select All");
   JMenuItem close = new JMenuItem("Close window");
   JTextArea textArea = new JTextArea();

   TextEditor() {
      this.newFile.addActionListener(this);
      this.openFile.addActionListener(this);
      this.saveFile.addActionListener(this);
      this.cut.addActionListener(this);
      this.copy.addActionListener(this);
      this.paste.addActionListener(this);
      this.selectAll.addActionListener(this);
      this.close.addActionListener(this);
      this.file.add(this.newFile);
      this.file.add(this.openFile);
      this.file.add(this.saveFile);
      this.edit.add(this.cut);
      this.edit.add(this.copy);
      this.edit.add(this.paste);
      this.edit.add(this.selectAll);
      this.edit.add(this.close);
      this.menuBar.add(this.file);
      this.menuBar.add(this.edit);
      this.frame.setJMenuBar(this.menuBar);
      JPanel panel = new JPanel();
      panel.setBorder(new EmptyBorder(5, 5, 5, 5));
      panel.setLayout(new BorderLayout(0, 0));
      panel.add(this.textArea, "Center");
      JScrollPane scrollPane = new JScrollPane(this.textArea, 20, 30);
      panel.add(scrollPane);
      this.frame.add(panel);
      this.frame.setBounds(100, 100, 800, 500);
      this.frame.setVisible(true);
   }

   public void actionPerformed(ActionEvent actionEvent) {
      if (actionEvent.getSource() == this.cut) {
         this.textArea.cut();
      }

      if (actionEvent.getSource() == this.copy) {
         this.textArea.copy();
      }

      if (actionEvent.getSource() == this.paste) {
         this.textArea.paste();
      }

      if (actionEvent.getSource() == this.selectAll) {
         this.textArea.selectAll();
      }

      if (actionEvent.getSource() == this.close) {
         System.exit(0);
      }

      if (actionEvent.getSource() == this.newFile) {
         new TextEditor();
      }

      JFileChooser fileChooser;
      int chooseOption;
      File file;
      if (actionEvent.getSource() == this.openFile) {
         fileChooser = new JFileChooser("C:");
         chooseOption = fileChooser.showOpenDialog((Component)null);
         if (chooseOption == 0) {
            file = fileChooser.getSelectedFile();
            String filePath = file.getPath();

            try {
               FileReader fileReader = new FileReader(filePath);
               BufferedReader bufferedReader = new BufferedReader(fileReader);
               String intermediate = "";

               String output;
               for(output = ""; (intermediate = bufferedReader.readLine()) != null; output = output + intermediate + "\n") {
               }

               bufferedReader.close();
               this.textArea.setText(output);
            } catch (Exception var11) {
               var11.printStackTrace();
            }
         }
      }

      if (actionEvent.getSource() == this.saveFile) {
         fileChooser = new JFileChooser("C:");
         chooseOption = fileChooser.showSaveDialog((Component)null);
         if (chooseOption == 0) {
            file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");

            try {
               BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
               this.textArea.write(bufferedWriter);
               bufferedWriter.close();
            } catch (Exception var10) {
               var10.printStackTrace();
            }
         }
      }

   }

}
public static void main(String[] args) {
   TextEditor textEditor = new TextEditor();
}
}
