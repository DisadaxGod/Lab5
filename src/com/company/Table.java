package com.company;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class Table extends AbstractTableModel {
    private Library data=new Library();
    public Table(){
        data.add(new Book("Есенин", "Горгород", 100));
        data.add(new Magazine("Красные","Мурзилка","Игра",23));
        data.add(new TextBook("Осбарн","Вероятность","Математика",43));
    }
    public void delete(int ind){
        this.data.remove(ind);
        this.fireTableDataChanged();
    }
    public void setValueAt(PrintE p, int rowIndex) {
        data.set(rowIndex, p);
        this.fireTableDataChanged();
    }

    public void AddSign(PrintE E){
        data.add(E);
        this.fireTableDataChanged();
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return Integer.class;
            case 5:
                return String.class;
            case 6:
                return String.class;

        }
        return String.class;
    }
    ArrayList <PrintE> printE = new ArrayList<PrintE>();
    String[] columnNames = {"Id", "Тип", "Имя автора", "Название", "Количество страниц", "Жанр", "Дисциплина"};

    public PrintE getValueAt(int rowIndex){
        return data.getPapers(rowIndex);
    }


    @Override
    public int getRowCount() {
        return data.getCount();
    }
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return true;
        }
        return false;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0: return data.getPapers(rowIndex).getId();
            case 1:{
                PrintE p = data.getPapers(rowIndex);
                if(p instanceof Book){
                    return "Книга";
                }
                else if(p instanceof Magazine){
                    return "Журнал";
                }
                else {
                    return "Учебник";
                }
            }
            case 2: return data.getPapers(rowIndex).getAuthorName();
            case 3: return data.getPapers(rowIndex).getName();
            case 4: return data.getPapers(rowIndex).getPages();
            case 5:{
                PrintE p = data.getPapers(rowIndex);
                if(p instanceof Magazine){
                    return ((Magazine) p).getGenre();
                }
                else {
                    return " ";
                }

            }
            case 6: {
                PrintE p = data.getPapers(rowIndex);
                if(p instanceof TextBook){
                    return ((TextBook) p).getSubject();
                }
                else {
                    return " ";
                }
            }
        }
        return " ";
    }
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}