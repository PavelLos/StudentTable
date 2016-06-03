package com.company.pasha.View;

import com.company.pasha.Controller.SearchingByCourseAndProgramLanguage;
import com.company.pasha.Controller.SearchingByNameAndGroup;
import com.company.pasha.Controller.SearchingByProjectsAndCourse;
import com.company.pasha.Controller.SearchingBySolvedProjectsGroupCourse;
import com.company.pasha.Model.BaseForPage;
import com.company.pasha.Model.MyTableBase;
import com.company.pasha.Model.MyTableModel;
import com.company.pasha.Model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by pasha on 20.05.2016.
 */
public class DeletePanel extends JFrame {
    /*private String firstName;
    private String secondName;
    private int numberOfCourse;
    private int numberOfGroup;
    private String programLanguage;
    private int numberOfSolvedProjects;
    private int numberOfProjects;*/
    private MyTableBase myTableBase;
    private MyTableBase tableBase;
    private BaseForPage baseForPage;
    private MyTableModel myTableModel;

    private int deleteIndex;

    private JTextField nameTextField;
    private JTextField secondNameTextField;
    private JTextField middleNameTextField;
    private String firstName, secondName, middleName;


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
    private java.util.List<Student> studentListForDelete;
    private Box boxPanel;

    public DeletePanel(MyTableBase myTableBase1, BaseForPage bPage, MyTableModel tableModel) {
        setName("Введите данные студента");
        myTableBase = myTableBase1;
        tableBase = new MyTableBase();
        studentListForDelete = new ArrayList<>();
        baseForPage = bPage;
        myTableModel = tableModel;

        firstName = "";
        secondName = "";
        numberOfCourse = 1;
        numberOfGroup = 1;
        numberOfProjects = 1;
        numberOfSolvedProjects = 1;
        programLanguage = "Java";
        deleteIndex = 0;

        Box mainBox = Box.createVerticalBox();

        boxPanel = Box.createVerticalBox();
        deleteField();

        Box deleteBox = Box.createHorizontalBox();
        String[] criteria = {"Удаление по имени и группе", "Удаление по курсу и языку программирования",
                "Удаление по количеству выполненных работ, курсу и группе", "Удаление по курсу и общему числу работ"};
        JComboBox deleteCriteria = new JComboBox(criteria);
        deleteCriteria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteIndex = deleteCriteria.getSelectedIndex();
                deleteField();

            }
        });
        deleteBox.add(deleteCriteria);

        Box okBox = Box.createHorizontalBox();
        JButton okButton = new JButton("Удалить");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (deleteIndex) {
                    case 0: {
                        firstName = nameTextField.getText();
                        secondName = secondNameTextField.getText();
                        middleName = middleNameTextField.getText();
                        numberOfGroup = Integer.parseInt((String) groupComboBox.getSelectedItem());
                        SearchingByNameAndGroup searchingByNameAndGroup = new SearchingByNameAndGroup(firstName, secondName, middleName, numberOfGroup, myTableBase, tableBase);
                        studentListForDelete= searchingByNameAndGroup.getStudentListForDelete();
                        myTableBase.removeFromBase(studentListForDelete);
                        baseForPage.refresh();
                        myTableModel.fireTableDataChanged();
                        JOptionPane.showMessageDialog(okButton, "Удалено: "+studentListForDelete.size()+" записей");
                        break;
                    }
                    case 1: {
                        numberOfCourse = Integer.parseInt((String) courseComboBox.getSelectedItem());
                        programLanguage = (String) programLanguageList.getSelectedItem();
                        SearchingByCourseAndProgramLanguage searchingByCourseAndProgramLanguage = new SearchingByCourseAndProgramLanguage(numberOfCourse, programLanguage, myTableBase, tableBase);
                        studentListForDelete= searchingByCourseAndProgramLanguage.getStudentListForDelete();
                        myTableBase.removeFromBase(studentListForDelete);
                        baseForPage.refresh();
                        myTableModel.fireTableDataChanged();
                        JOptionPane.showMessageDialog(okButton, "Удалено: "+studentListForDelete.size()+" записей");
                        break;
                    }
                    case 2: {
                        numberOfGroup = Integer.parseInt((String) groupComboBox.getSelectedItem());
                        numberOfCourse = Integer.parseInt((String) courseComboBox.getSelectedItem());
                        numberOfSolvedProjects = Integer.parseInt((String) numberOfSolvedProjectsList.getSelectedItem());
                        SearchingBySolvedProjectsGroupCourse searchingBySolvedProjectsGroupCourse = new SearchingBySolvedProjectsGroupCourse(numberOfSolvedProjects, numberOfCourse, numberOfGroup, myTableBase, tableBase);
                        studentListForDelete= searchingBySolvedProjectsGroupCourse.getStudentListForDelete();
                        myTableBase.removeFromBase(studentListForDelete);
                        baseForPage.refresh();
                        myTableModel.fireTableDataChanged();
                        JOptionPane.showMessageDialog(okButton, "Удалено: "+studentListForDelete.size()+" записей");
                        break;
                    }
                    case 3: {
                        numberOfProjects = Integer.parseInt((String) numberOfProjectsList.getSelectedItem());
                        numberOfCourse = Integer.parseInt((String) courseComboBox.getSelectedItem());
                        SearchingByProjectsAndCourse searchingByProjectsAndCourse = new SearchingByProjectsAndCourse(numberOfProjects, numberOfCourse, myTableBase, tableBase);
                        studentListForDelete= searchingByProjectsAndCourse.getStudentListForDelete();
                        myTableBase.removeFromBase(studentListForDelete);
                        baseForPage.refresh();
                        myTableModel.fireTableDataChanged();
                        JOptionPane.showMessageDialog(okButton, "Удалено: "+studentListForDelete.size()+" записей");
                        break;
                    }
                }
            }
        });
        okBox.add(Box.createHorizontalGlue());
        okBox.add(okButton);
        okBox.add(Box.createHorizontalStrut(12));

        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(deleteBox);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(boxPanel);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(okBox);
        mainBox.add(Box.createVerticalStrut(12));
        add(mainBox);
        setSize(new Dimension(600, 270));
        setVisible(true);

    }

    void deleteField() {
        switch (deleteIndex) {
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
                middleNameTextField.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        middleName = middleNameTextField.getText();
                    }
                });
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
                numberOfGroup = Integer.parseInt((String) groupComboBox.getSelectedItem());
                groupComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        numberOfGroup = Integer.parseInt((String) groupComboBox.getSelectedItem());
                    }
                });
                groupBox.add(groupLabel);
                groupBox.add(Box.createHorizontalStrut(6));
                groupBox.add(groupComboBox);
                boxPanel.add(groupBox);
                boxPanel.add(Box.createVerticalStrut(12));
                firstName = nameTextField.getText();
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
                courseComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        numberOfCourse = Integer.parseInt((String) courseComboBox.getSelectedItem());

                    }
                });
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
                programLanguageList.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        programLanguage = (String) programLanguageList.getSelectedItem();

                    }
                });
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
                numberOfSolvedProjectsList.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        numberOfSolvedProjects = Integer.parseInt((String) numberOfSolvedProjectsList.getSelectedItem());

                    }
                });
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
                courseComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        numberOfCourse = Integer.parseInt((String) courseComboBox.getSelectedItem());

                    }
                });
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
                groupComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        numberOfGroup = Integer.parseInt((String) groupComboBox.getSelectedItem());

                    }
                });
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
                courseComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        numberOfCourse = Integer.parseInt((String) courseComboBox.getSelectedItem());

                    }
                });
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
                numberOfProjectsList.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        numberOfProjects = Integer.parseInt((String) numberOfProjectsList.getSelectedItem());

                    }
                });

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
}
