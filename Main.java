import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class Main{
	public static void main(String[] args) {
		JFrame frame = new JFrame("Main");
		frame.setPreferredSize(new Dimension(500,525));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout());


		JTextField fileReader = new JTextField(25);
		JButton enter = new JButton("Enter File Path");
		JLabel totalWords = new JLabel("Total Words: ");
		JLabel totalFrequency = new JLabel("Total Frequency: ");
		JLabel totalWordC = new JLabel("0");
		JLabel totalFreqC = new JLabel("0");
		// DefaultTableModel model = new DefaultTableModel();
		// model.addColumn("Headers");
		// model.addColumn("Frequency");
		// JTable table = new JTable(model);
		JTextArea textarea = new JTextArea();
		JScrollPane areaScrollPane = new JScrollPane(textarea);
		areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setPreferredSize(new Dimension(250, 250));
/// /home/icspclab/Desktop/000
		mainPanel.add(fileReader);
		mainPanel.add(enter);
		mainPanel.add(totalWords);
		mainPanel.add(totalWordC);
		mainPanel.add(totalFrequency);
		mainPanel.add(totalFreqC);
		// mainPanel.add(textarea);
		mainPanel.add(areaScrollPane);

		frame.add(mainPanel);

		String directory;
		enter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String directory = fileReader.getText();
				File folder = new File(directory);
				System.out.println("folder: " + folder.getName());
				System.out.println("folderexists: " + folder.exists());
				File[] files = folder.listFiles();
				System.out.println("files: " + files);

				System.out.println(directory);

				String line = "";
				//reads all files in the directory
				for(File f: files){
					BufferedReader br;
					System.out.println(f);
					try{
						String temp;
				 		br = new BufferedReader(new FileReader(f));
				 		while((temp = br.readLine()) != null ){
				 			line += temp + " ";
				 		}
					} catch (FileNotFoundException fileNotFound) {
			            System.err.println("Unable to find the file: fileName");
			        } catch (IOException ioExcept) {
			            System.err.println("Unable to read the file: fileName");
			        }
				}

				Word dictionary = new Word(line);
				System.out.println(dictionary.line);
				dictionary.cleanLine();
			    System.err.println("clean");
				System.out.println(dictionary.line);
				dictionary.createDictionary();
				dictionary.fileWriting();

				
				textarea.append(dictionary.output);
				textarea.setLineWrap(true);
				textarea.setWrapStyleWord(true);
				totalWordC.setText(Integer.toString(dictionary.totalwords));
				totalFreqC.setText(Integer.toString(dictionary.totalfreq));
				}

		});

		//gets directory from argument

		

		frame.setFocusable(true); 
		frame.pack();
		frame.setVisible(true);
	}


}