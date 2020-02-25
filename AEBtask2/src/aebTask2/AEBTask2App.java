package aebTask2;
//Main класс приложения

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AEBTask2App {
        private JLabel description;
        private JButton button;
        private JPanel panelMain;
        private JLabel finalMessage;

        public static void main(String[] args) throws IOException {
                //Ининциализация приложения
                JFrame frame = new JFrame("AEBTask2App");
                frame.setContentPane(new AEBTask2App().panelMain);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
        }

        public AEBTask2App() throws IOException {
                //Инициализация программы
                finalMessage.setVisible(false);
                XlsCorrector XlsCorrector = new XlsCorrector(this);
                button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                //Инициализация выбора файла при клике на кнопку
                                JFileChooser chooser = new JFileChooser();
                                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                                        "xsl файл c данными", "xls");
                                chooser.setFileFilter(filter);
                                int returnVal = chooser.showOpenDialog(null);
                                if (returnVal == JFileChooser.APPROVE_OPTION) {
                                        try {
                                                //Обработка и создание исправленной копии
                                                XlsCorrector.parseXls(chooser.getSelectedFile().getAbsolutePath(), chooser);
                                        } catch (IOException ex) {
                                                ex.printStackTrace();
                                        }
                                }
                        }
                });
        }

        public void finish() {
                description.setVisible(false);
                button.setVisible(false);
                finalMessage.setVisible(true);
        }
}
