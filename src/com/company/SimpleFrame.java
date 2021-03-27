package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;
import javax.swing.JMenuBar;

public class SimpleFrame extends JFrame {
    private static Table table = new Table();;
    private static JButton Delete;
    private static JTable tab = new JTable(table);

    SimpleFrame(String s) {
        super(s);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setDefaultCloseOperation(3);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table);
        tab.setRowSorter(sorter);
        JTextField filterText = new JTextField(10);
        add(new JScrollPane(tab));
        JPanel North = new JPanel();
        JPanel East = new JPanel(new BorderLayout());
        GridLayout gl = new GridLayout(3,1,1,3);
        JPanel EastUp = new JPanel(gl);
        JMenuBar AddE = new JMenuBar();
        JMenu Add = new JMenu("Добавить");
        JMenuItem NewBook = new JMenuItem("Добавить книгу");
        NewBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateDialogAddBook();
            }
        });
        JMenuItem NewMagazine = new JMenuItem("Добавить журнал");
        NewMagazine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateDialogAddMagazine();
            }
        });
        JMenuItem NewTextBook = new JMenuItem("Добавить учебник");
        NewTextBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateDialogAddTextBook();
            }
        });
        Add.add(NewBook);
        Add.add(NewMagazine);
        Add.add(NewTextBook);
        AddE.add(Add);
        North.add(AddE);
        North.setLayout(new FlowLayout(FlowLayout.LEFT));
        Delete = new JButton("Удалить");

        Delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tab.getSelectedRow();
                if (row == -1){
                    JOptionPane.showMessageDialog(North,
                            "Вы ничего не выбрали",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE
                            );
                    return;
                }
                table.delete(row);
            }
        });
        JMenuBar menu = new JMenuBar();
        menu.add(MenuChange());
        North.add(menu);
        EastUp.add(Delete);

        JButton search = new JButton("Поиск");
        EastUp.add(search);
        EastUp.add(filterText);
        East.add(EastUp, BorderLayout.NORTH);
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = filterText.getText();
                if (text.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(text));
                }
            }
        });
        add(North, BorderLayout.NORTH);
        add(East, BorderLayout.EAST);
        setVisible(true);
    }
    private JMenu MenuChange(){
        JMenu menu=new JMenu("Изменить");
        JMenuItem B=new JMenuItem("Книга");
        JMenuItem M=new JMenuItem("Журнал");
        JMenuItem T=new JMenuItem("Учебник");
        B.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tab.getSelectedRow();
                if (row == -1){
                    JOptionPane.showMessageDialog(tab,
                            "Вы ничего не выбрали",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                PrintE p = table.getValueAt(tab.getSelectedRow());
                if(p instanceof Book){
                    new Change();
                }
                else {
                    JOptionPane.showMessageDialog(tab,
                            "Вы выбрали неправильный тип",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
            }
        });

        M.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tab.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(tab,
                            "Вы ничего не выбрали",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                try{
                    new Change1();
                }
                catch (ClassCastException ex){
                    JOptionPane.showMessageDialog(tab,
                            "Вы выбрали неправильный тип",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
            }
        });
        T.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tab.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(tab,
                            "Вы ничего не выбрали",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                try{
                    new Change2();
                }
                catch (ClassCastException ex){
                    JOptionPane.showMessageDialog(tab,
                            "Вы выбрали неправильный тип",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
            }
        });

        menu.add(B);
        menu.add(M);
        menu.add(T);
        return menu;
    }
    class Change extends JFrame{
        public Change(){
            super("Изменение");
            setSize(600, 300);
            this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            PrintE p = table.getValueAt(tab.getSelectedRow());
            JPanel panel=new JPanel();
            JButton button=new JButton("Изменить");
            GridLayout gl = new GridLayout(3, 2, 1, 3);
            panel.setLayout(gl);
            JTextField AuthorName = new JTextField(20); AuthorName.setText(p.getAuthorName());
            JTextField Name = new JTextField(10);  Name.setText(p.getName());
            JTextField Pages = new JTextField(20); Pages.setText(String.valueOf(p.getPages()));
            int id = tab.getSelectedRow();
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    table.setValueAt(new Book(AuthorName.getText(),Name.getText(),Integer.parseInt(Pages.getText())), id);
                    dispose();
                }
            });
            panel.add(AuthorName); panel.add(Name); panel.add(Pages);
            add(button, BorderLayout.SOUTH);
            add(panel);
            this.pack();
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        }

    }
    class Change1 extends JFrame{
        public Change1(){
            super("Изменение");
            setSize(600, 300);
            this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            PrintE p = table.getValueAt(tab.getSelectedRow());
            JPanel panel=new JPanel();
            JButton button=new JButton("Изменить");
            GridLayout gl = new GridLayout(3, 3, 1, 3);
            panel.setLayout(gl);
            JTextField AuthorName = new JTextField(20); AuthorName.setText(p.getAuthorName());
            JTextField Name = new JTextField(10);  Name.setText(p.getName());
            JTextField Pages = new JTextField(20); Pages.setText(String.valueOf(p.getPages()));
            JTextField Genre = new JTextField(10);  Genre.setText(((Magazine) p).getGenre());

            int id=tab.getSelectedRow();
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    table.setValueAt(new Magazine(AuthorName.getText(),Name.getText(), Genre.getText(),Integer.parseInt(Pages.getText())), id);
                    dispose();
                }
            });
            panel.add(AuthorName); panel.add(Name); panel.add(Genre);panel.add(Pages);
            add(button, BorderLayout.SOUTH);
            add(panel);
            this.pack();
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        }

    }
    class Change2 extends JFrame{
        public Change2(){
            super("Изменение");
            setSize(600, 300);
            this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            PrintE p = table.getValueAt(tab.getSelectedRow());
            JPanel panel=new JPanel();
            JButton button=new JButton("Изменить");
            GridLayout gl = new GridLayout(3, 3, 1, 3);
            panel.setLayout(gl);
            JTextField AuthorName = new JTextField(20); AuthorName.setText(p.getAuthorName());
            JTextField Name = new JTextField(10);  Name.setText(p.getName());
            JTextField Pages = new JTextField(20); Pages.setText(String.valueOf(p.getPages()));
            JTextField Subject = new JTextField(10);  Subject.setText(((TextBook) p).getSubject());

            int id=tab.getSelectedRow();
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    table.setValueAt(new TextBook(AuthorName.getText(),Name.getText(), Subject.getText(),Integer.parseInt(Pages.getText())), id);
                    dispose();
                }
            });
            panel.add(AuthorName); panel.add(Name); panel.add(Subject);panel.add(Pages);
            add(button, BorderLayout.SOUTH);
            add(panel);
            this.pack();
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        }

    }

    public static void CreateDialogAddBook(){
        JDialog dialog=new JDialog();
        dialog.setModal(true);
        dialog.setTitle("Добавление книги");
        dialog.setSize(350, 250);
        GridLayout Grid = new GridLayout(3,2,1,3);
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        panel.setLayout(Grid);
        JLabel AuthorNameLab = new JLabel("Имя автора");
        JTextField AuthorNameField = new JTextField();
        JLabel NameLab = new JLabel("Название книги");
        JTextField NameField = new JTextField();
        JLabel PagesLab = new JLabel("Количество страниц");
        JTextField PagesField = new JTextField();
        JButton AddBookNew = new JButton("Добавить запись");
        panel.add(AuthorNameLab);
        panel.add(AuthorNameField);
        panel.add(NameLab);
        panel.add(NameField);
        panel.add(PagesLab);
        panel.add(PagesField);
        panel2.add(AddBookNew);
        AddBookNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if (Integer.parseInt(PagesField.getText())>=0){
                        PrintE E = new Book((String)AuthorNameField.getText(),(String) NameField.getText(),Integer.parseInt(PagesField.getText()));
                        table.AddSign(E);
                    }
                    else {
                        JOptionPane.showMessageDialog(panel,
                                " Можно вводить только положительные числа",
                                "Ошибка",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel,
                            " Можно вводить только целые числа",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(panel2, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
    public static void CreateDialogAddMagazine(){
        JDialog dialog=new JDialog();
        dialog.setModal(true);
        dialog.setTitle("Добавление журнала");
        dialog.setSize(350, 250);
        GridLayout Grid = new GridLayout(4,2,1,3);
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        panel.setLayout(Grid);
        JLabel AuthorNameLab = new JLabel("Имя автора");
        JTextField AuthorNameField = new JTextField();
        JLabel NameLab = new JLabel("Название журнала");
        JTextField NameField = new JTextField();
        JLabel PagesLab = new JLabel("Количество страниц");
        JTextField PagesField = new JTextField();
        JLabel GenreLab = new JLabel("Жанр");
        JTextField GenreField = new JTextField();
        JButton AddBookNew = new JButton("Добавить запись");
        panel.add(AuthorNameLab);
        panel.add(AuthorNameField);
        panel.add(NameLab);
        panel.add(NameField);
        panel.add(PagesLab);
        panel.add(PagesField);
        panel.add(GenreLab);
        panel.add(GenreField);
        panel2.add(AddBookNew);
        AddBookNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if (Integer.parseInt(PagesField.getText())>=0){
                        PrintE E = new Magazine((String)AuthorNameField.getText(),(String) NameField.getText(),(String) GenreField.getText(),Integer.parseInt(PagesField.getText()));
                        table.AddSign(E);
                    }
                    else {
                        JOptionPane.showMessageDialog(panel,
                                " Можно вводить только положительные числа",
                                "Ошибка",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel,
                            " Можно вводить только целые числа",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(panel2, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
    public static void CreateDialogAddTextBook(){
        JDialog dialog=new JDialog();
        dialog.setModal(true);
        dialog.setTitle("Добавление учебника");
        dialog.setSize(350, 250);
        GridLayout Grid = new GridLayout(4,2,1,3);
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        panel.setLayout(Grid);
        JLabel AuthorNameLab = new JLabel("Имя автора");
        JTextField AuthorNameField = new JTextField();
        JLabel NameLab = new JLabel("Название учебника");
        JTextField NameField = new JTextField();
        JLabel PagesLab = new JLabel("Количество страниц");
        JTextField PagesField = new JTextField();
        JLabel SubjectLab = new JLabel("Дисциплина");
        JTextField SubjectField = new JTextField();
        JButton AddBookNew = new JButton("Добавить запись");
        panel.add(AuthorNameLab);
        panel.add(AuthorNameField);
        panel.add(NameLab);
        panel.add(NameField);
        panel.add(PagesLab);
        panel.add(PagesField);
        panel.add(SubjectLab);
        panel.add(SubjectField);
        panel2.add(AddBookNew);
        AddBookNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if (Integer.parseInt(PagesField.getText())>=0){
                        PrintE E = new TextBook((String)AuthorNameField.getText(),(String) NameField.getText(),(String) SubjectField.getText(),Integer.parseInt(PagesField.getText()));
                        table.AddSign(E);
                    }
                    else {
                        JOptionPane.showMessageDialog(panel,
                                " Можно вводить только положительные числа",
                                "Ошибка",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel,
                            " Можно вводить только целые числа",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(panel2, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
}



