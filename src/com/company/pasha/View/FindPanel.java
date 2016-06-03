package com.company.pasha.View;

import com.company.pasha.Controller.SearchingByCourseAndProgramLanguage;
import com.company.pasha.Controller.SearchingByNameAndGroup;
import com.company.pasha.Controller.SearchingByProjectsAndCourse;
import com.company.pasha.Controller.SearchingBySolvedProjectsGroupCourse;
import com.company.pasha.Model.BaseForPage;
import com.company.pasha.Model.MyTableBase;
import com.company.pasha.Model.MyTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by pasha on 20.05.2016.
 */
public class FindPanel extends JFrame {

    private JTable jTable;
    private MyTableBase tableBase;
    private MyTableBase myTableBase;
    private int selectedIndex;
    private BaseForPage pageTableBase;

    private MyTableModel tableModel;

    private JTextField nameTextField;
    private JTextField secondNameTextField;
    private JTextField middleNameTextField;
    private String name, secondName, middleName;


    private JComboBox courseComboBox;
    private int numberOfCourse;
    private JComboBox groupComboBox;
    private int numberOfGroup;

    private JComboBox numberOfSolvedProjectsList;
    private int numberOfSolvedProjects;
    private JComboBox numberOfProjectsList;
    private int numberOfProjects;

    private JComboBox programLanguageList;
    private String programLanguage;
    private Box boxPanel;
    private Box pageBox;

    public FindPanel(MyTableBase myTableBase1) {
        setName("Find");
        selectedIndex = 0;
        myTableBase = myTableBase1;
        Box mainBox = Box.createVerticalBox();
        Box tableBox = Box.createHorizontalBox();


        tableBase = new MyTableBase();
        pageTableBase = new BaseForPage(tableBase);
        tableModel = new MyTableModel(pageTableBase.getTableBase());
        jTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(jTable);
        jTable.setPreferredSize(new Dimension(700, 700));


        tableBox.add(scrollPane);
        setSize(500, 500);

        name = "";
        secondName = "";
        middleName = "";
        numberOfCourse = 1;
        numberOfGroup = 1;
        numberOfProjects = 1;
        numberOfSolvedProjects = 1;
        programLanguage = "Java";

        boxPanel = Box.createVerticalBox();

        searchingField();

        Box findBox = Box.createHorizontalBox();
        String[] criteria = {"Поиск по имени и группе", "Поиск по курсу и языку программирования",
                "Поиск по количеству выполненных работ, курсу и группе", "Поиск по курсу и общему числу работ"};
        JComboBox searchingCriteria = new JComboBox(criteria);
        searchingCriteria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedIndex = searchingCriteria.getSelectedIndex();
                searchingField();
            }
        });
        findBox.add(searchingCriteria);


        Box okBox = Box.createHorizontalBox();
        JButton okButton = new JButton("Найти");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (selectedIndex) {
                    case 0: {
                        name = nameTextField.getText();
                        secondName = secondNameTextField.getText();
                        middleName = middleNameTextField.getText();
                        numberOfGroup = Integer.parseInt((String) groupComboBox.getSelectedItem());
                        SearchingByNameAndGroup searchingByNameAndGroup = new SearchingByNameAndGroup(name, secondName, middleName, numberOfGroup, myTableBase, tableBase);
                        pageTableBase.refresh();
                        tableModel.fireTableDataChanged();
                        break;
                    }
                    case 1: {
                        numberOfCourse = Integer.parseInt((String) courseComboBox.getSelectedItem());
                        programLanguage = (String) programLanguageList.getSelectedItem();
                        SearchingByCourseAndProgramLanguage searchingByCourseAndProgramLanguage = new SearchingByCourseAndProgramLanguage(numberOfCourse, programLanguage, myTableBase, tableBase);
                        pageTableBase.refresh();
                        tableModel.fireTableDataChanged();
                        break;
                    }
                    case 2: {
                        numberOfGroup = Integer.parseInt((String) groupComboBox.getSelectedItem());
                        numberOfCourse = Integer.parseInt((String) courseComboBox.getSelectedItem());
                        numberOfSolvedProjects = Integer.parseInt((String) numberOfSolvedProjectsList.getSelectedItem());
                        SearchingBySolvedProjectsGroupCourse searchingBySolvedProjectsGroupCourse = new SearchingBySolvedProjectsGroupCourse(numberOfSolvedProjects, numberOfCourse, numberOfGroup, myTableBase, tableBase);
                        pageTableBase.refresh();
                        tableModel.fireTableDataChanged();
                        break;
                    }
                    case 3: {
                        numberOfProjects = Integer.parseInt((String) numberOfProjectsList.getSelectedItem());
                        numberOfCourse = Integer.parseInt((String) courseComboBox.getSelectedItem());
                        SearchingByProjectsAndCourse searchingByProjectsAndCourse = new SearchingByProjectsAndCourse(numberOfProjects, numberOfCourse, myTableBase, tableBase);
                        pageTableBase.refresh();
                        tableModel.fireTableDataChanged();
                        break;
                    }
                }
            }
        });
        okBox.add(Box.createHorizontalGlue());
        okBox.add(okButton);
        okBox.add(Box.createHorizontalStrut(12));

        pageBox = Box.createHorizontalBox();
        pageView();

        mainBox.add(findBox);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(boxPanel);
        mainBox.add(okBox);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(tableBox);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(pageBox);
        mainBox.add(Box.createVerticalStrut(12));
        add(mainBox);
        setVisible(true);
    }

    void searchingField() {
        switch (selectedIndex) {
            case 0: {
                boxPanel.removeAll();
                boxPanel.revalidate();

                Box nameBox = Box.createHorizontalBox();
                JLabel nameLabel = new JLabel("Имя:");
                nameLabel.setPreferredSize(new Dimension(200, nameLabel.getHeight()));
                nameTextField = new JTextField(20);
                nameBox.add(nameLabel);
                nameBox.add(Box.createHorizontalStrut(6));
                nameBox.add(nameTextField);
                boxPanel.add(nameBox);
                boxPanel.add(Box.createVerticalStrut(12));

                Box secondNameBox = Box.createHorizontalBox();
                JLabel secondNameLabel = new JLabel("Фамилия:");
                secondNameLabel.setPreferredSize(new Dimension(200, nameLabel.getHeight()));
                secondNameTextField = new JTextField(20);
                secondNameBox.add(secondNameLabel);
                secondNameBox.add(Box.createHorizontalStrut(6));
                secondNameBox.add(secondNameTextField);
                boxPanel.add(secondNameBox);
                boxPanel.add(Box.createVerticalStrut(12));

                Box middleNameBox = Box.createHorizontalBox();
                JLabel middleNameLabel = new JLabel("Отчество:");
                middleNameLabel.setPreferredSize(new Dimension(200, middleNameBox.getHeight()));
                middleNameTextField = new JTextField(20);
                middleNameBox.add(middleNameLabel);
                middleNameBox.add(Box.createHorizontalStrut(6));
                middleNameBox.add(middleNameTextField);
                boxPanel.add(middleNameBox);
                boxPanel.add(Box.createVerticalStrut(12));

                Box groupBox = Box.createHorizontalBox();
                JLabel groupLabel = new JLabel("Группа:");
                groupLabel.setPreferredSize(new Dimension(200, nameLabel.getHeight()));
                String[] groups = {"1", "2", "3"};
                groupComboBox = new JComboBox(groups);
                groupBox.add(groupLabel);
                groupBox.add(Box.createHorizontalStrut(6));
                groupBox.add(groupComboBox);
                boxPanel.add(groupBox);
                boxPanel.add(Box.createVerticalStrut(12));
                name = nameTextField.getText();
                secondName = secondNameTextField.getText();
                break;
            }
            case 1: {
                boxPanel.removeAll();
                boxPanel.revalidate();
                Box courseBox = Box.createHorizontalBox();
                JLabel courseLabel = new JLabel("Курс:");
                courseLabel.setPreferredSize(new Dimension(200, courseLabel.getHeight()));
                String[] courses = {"1", "2", "3", "4", "5"};
                courseComboBox = new JComboBox(courses);
                courseBox.add(courseLabel);
                courseBox.add(Box.createHorizontalStrut(6));
                courseBox.add(courseComboBox);
                boxPanel.add(courseBox);
                boxPanel.add(Box.createVerticalStrut(12));

                Box languageBox = Box.createHorizontalBox();
                JLabel languageLabel = new JLabel("Язык программирования:");
                languageLabel.setPreferredSize(new Dimension(200, languageLabel.getHeight()));
                String[] languages = {"Java", "C++", "C#", "JavaScript", "Python", "Ruby"};
                programLanguageList = new JComboBox(languages);
                languageBox.add(languageLabel);
                languageBox.add(Box.createHorizontalStrut(6));
                languageBox.add(programLanguageList);
                boxPanel.add(languageBox);
                boxPanel.add(Box.createVerticalStrut(12));

                break;
            }
            case 2: {
                boxPanel.removeAll();
                boxPanel.revalidate();
                Box numberOfSolvedProjectsBox = Box.createHorizontalBox();
                JLabel numberOfSolvedProjectsLabel = new JLabel("Количество выполненных работ:");
                numberOfSolvedProjectsLabel.setPreferredSize(new Dimension(200, numberOfSolvedProjectsLabel.getHeight()));
                String[] numberOfSolvedProjectsMas = new String[99];
                for (int i = 1; i < 100; i++) {
                    numberOfSolvedProjectsMas[i - 1] = Integer.toString(i);
                }
                numberOfSolvedProjectsList = new JComboBox(numberOfSolvedProjectsMas);
                numberOfSolvedProjectsBox.add(numberOfSolvedProjectsLabel);
                numberOfSolvedProjectsBox.add(Box.createHorizontalStrut(6));
                numberOfSolvedProjectsBox.add(numberOfSolvedProjectsList);
                boxPanel.add(numberOfSolvedProjectsBox);
                boxPanel.add(Box.createVerticalStrut(12));

                Box courseBox = Box.createHorizontalBox();
                JLabel courseLabel = new JLabel("Курс:");
                courseLabel.setPreferredSize(new Dimension(200, courseLabel.getHeight()));
                String[] courses = {"1", "2", "3", "4", "5"};
                courseComboBox = new JComboBox(courses);
                courseBox.add(courseLabel);
                courseBox.add(Box.createHorizontalStrut(6));
                courseBox.add(courseComboBox);
                boxPanel.add(courseBox);
                boxPanel.add(Box.createVerticalStrut(12));

                Box groupBox = Box.createHorizontalBox();
                JLabel groupLabel = new JLabel("Группа:");
                groupLabel.setPreferredSize(new Dimension(200, groupLabel.getHeight()));
                String[] groups = {"1", "2", "3"};
                groupComboBox = new JComboBox(groups);
                groupBox.add(groupLabel);
                groupBox.add(Box.createHorizontalStrut(6));
                groupBox.add(groupComboBox);
                boxPanel.add(groupBox);
                boxPanel.add(Box.createVerticalStrut(12));
                boxPanel.revalidate();
                break;
            }
            case 3: {
                boxPanel.removeAll();
                boxPanel.revalidate();
                Box courseBox = Box.createHorizontalBox();
                JLabel courseLabel = new JLabel("Курс:");
                courseLabel.setPreferredSize(new Dimension(200, courseLabel.getHeight()));
                String[] courses = {"1", "2", "3", "4", "5"};
                courseComboBox = new JComboBox(courses);
                courseBox.add(courseLabel);
                courseBox.add(Box.createHorizontalStrut(6));
                courseBox.add(courseComboBox);
                boxPanel.add(courseBox);
                boxPanel.add(Box.createVerticalStrut(12));

                Box numberOfProjectsBox = Box.createHorizontalBox();
                JLabel numberOfProjectsLabel = new JLabel("Общее число работ:");
                numberOfProjectsLabel.setPreferredSize(new Dimension(200, numberOfProjectsLabel.getHeight()));
                String[] numberOfProjectsMas = new String[99];
                for (int i = 1; i < 100; i++) {
                    numberOfProjectsMas[i - 1] = Integer.toString(i);
                }
                numberOfProjectsList = new JComboBox(numberOfProjectsMas);
                numberOfProjectsBox.add(numberOfProjectsLabel);
                numberOfProjectsBox.add(Box.createHorizontalStrut(6));
                numberOfProjectsBox.add(numberOfProjectsList);
                boxPanel.add(numberOfProjectsBox);
                boxPanel.add(Box.createVerticalStrut(12));
                boxPanel.revalidate();
                break;
            }
        }
    }

    void pageView(){
        JButton firstPageButton = new JButton("First Page");
        firstPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageTableBase.firstPage();
                tableModel.fireTableDataChanged();
            }
        });
        pageBox.add(firstPageButton);
        JButton previousPageButton = new JButton("Previous Page");
        previousPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageTableBase.previousPage();
                tableModel.fireTableDataChanged();

            }
        });
        pageBox.add(previousPageButton);
        JButton nextPageButton = new JButton("Next Page");
        nextPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageTableBase.nextPage();
                tableModel.fireTableDataChanged();

            }
        });
        pageBox.add(nextPageButton);
        JButton lastPageButton = new JButton("Last Page");
        lastPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageTableBase.lastPage();
                tableModel.fireTableDataChanged();
            }
        });
        pageBox.add(lastPageButton);
        String[] numberOfRows = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        JComboBox numberOfRowsComboBox = new JComboBox(numberOfRows);
        numberOfRowsComboBox.setSelectedIndex(1);
        numberOfRowsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numberOfRows = Integer.parseInt((String) numberOfRowsComboBox.getSelectedItem());
                pageTableBase.setNumberOfRows(numberOfRows);
                tableModel.fireTableDataChanged();

            }
        });

        pageBox.add(numberOfRowsComboBox);
    }
}
