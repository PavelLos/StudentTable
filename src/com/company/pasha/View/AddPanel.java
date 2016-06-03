package com.company.pasha.View;

import com.company.pasha.Model.BaseForPage;
import com.company.pasha.Model.MyTableBase;
import com.company.pasha.Model.MyTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by pasha on 01.05.2016.
 */
public class AddPanel extends JFrame{
    private MyTableModel myTableModel;
    private BaseForPage baseForPage;

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

    MyTableBase myTableBase;

    public AddPanel(MyTableBase base, BaseForPage baseForPage1, MyTableModel tableModel) {
        myTableBase = base;
        baseForPage = baseForPage1;
        myTableModel = tableModel;
        setName("Введите данные студента");
        Box boxPanel = Box.createVerticalBox();
        name = "";
        secondName = "";
        middleName = "";
        numberOfCourse = 1;
        numberOfGroup = 1;
        numberOfProjects = 1;
        numberOfSolvedProjects = 1;
        programLanguage = "Java";

        Box nameBox = Box.createHorizontalBox();
        JLabel nameLabel = new JLabel("Имя:");
        nameLabel.setPreferredSize(new Dimension(200, nameLabel.getHeight()));
        nameTextField = new JTextField(20);
        nameTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name = nameTextField.getText();

            }
        });
        nameBox.add(nameLabel);
        nameBox.add(Box.createHorizontalStrut(6));
        nameBox.add(nameTextField);
        boxPanel.add(nameBox);
        boxPanel.add(Box.createVerticalStrut(12));

        Box secondNameBox = Box.createHorizontalBox();
        JLabel secondNameLabel = new JLabel("Фамилия:");
        secondNameLabel.setPreferredSize(new Dimension(200, nameLabel.getHeight()));
        secondNameTextField = new JTextField(20);
        secondNameTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondName = secondNameTextField.getText();
                System.out.println(secondName);
            }
        });
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

        Box courseBox = Box.createHorizontalBox();
        JLabel courseLabel = new JLabel("Курс:");
        courseLabel.setPreferredSize(new Dimension(200, nameLabel.getHeight()));
        String []courses = {"1", "2", "3", "4", "5"};
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
        groupLabel.setPreferredSize(new Dimension(200, nameLabel.getHeight()));
        String []groups = {"1", "2", "3"};
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

        Box numberOfProjectsBox = Box.createHorizontalBox();
        JLabel numberOfProjectsLabel = new JLabel("Общее число работ:");
        numberOfProjectsLabel.setPreferredSize(new Dimension(200, nameLabel.getHeight()));
        String []numberOfProjectsMas = new String[99];
        Box numberOfSolvedProjectsBox = Box.createHorizontalBox();
        JLabel numberOfSolvedProjectsLabel = new JLabel("Количество выполненных работ:");
        numberOfSolvedProjectsLabel.setPreferredSize(new Dimension(200, nameLabel.getHeight()));
        String []numberOfSolvedProjectsMas = new String[99];
        for(int i =1; i<100; i++ ){
            numberOfProjectsMas[i-1] = Integer.toString(i);
            numberOfSolvedProjectsMas[i-1]= Integer.toString(i);
        }
        numberOfProjectsList = new JComboBox(numberOfProjectsMas);
        numberOfProjectsList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberOfProjects = Integer.parseInt((String)numberOfProjectsList.getSelectedItem());

            }
        });
        numberOfSolvedProjectsList = new JComboBox(numberOfSolvedProjectsMas);
        numberOfSolvedProjectsList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberOfSolvedProjects = Integer.parseInt((String)numberOfSolvedProjectsList.getSelectedItem());

            }
        });

        numberOfProjectsBox.add(numberOfProjectsLabel);
        numberOfProjectsBox.add(Box.createHorizontalStrut(6));
        numberOfProjectsBox.add(numberOfProjectsList);
        boxPanel.add(numberOfProjectsBox);
        boxPanel.add(Box.createVerticalStrut(12));

        numberOfSolvedProjectsBox.add(numberOfSolvedProjectsLabel);
        numberOfSolvedProjectsBox.add(Box.createHorizontalStrut(6));
        numberOfSolvedProjectsBox.add(numberOfSolvedProjectsList);
        boxPanel.add(numberOfSolvedProjectsBox);
        boxPanel.add(Box.createVerticalStrut(12));

        Box languageBox = Box.createHorizontalBox();
        JLabel languageLabel = new JLabel("Язык программирования:");
        languageLabel.setPreferredSize(new Dimension(200, nameLabel.getHeight()));
        String []languages = {"Java", "C++", "C#", "JavaScript", "Python", "Ruby"};
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

        Box okBox = Box.createHorizontalBox();
        JButton okButton = new JButton("Ok");

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name = nameTextField.getText();
                secondName = secondNameTextField.getText();
                middleName = middleNameTextField.getText();
                myTableBase.setFirstName(name);
                myTableBase.setSecondName(secondName);
                myTableBase.setMiddleName(middleName);
                myTableBase.setNumberOfCourse(numberOfCourse);
                myTableBase.setNumberOfGroup(numberOfGroup);
                myTableBase.setNumberOfProjects(numberOfProjects);
                myTableBase.setNumberOfSolvedProjects(numberOfSolvedProjects);
                myTableBase.setProgramLanguage(programLanguage);
                myTableBase.addToBase();
                baseForPage.refresh();
                myTableModel.fireTableDataChanged();

            }
        });
        JButton cancelButton = new JButton("Отмена");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        okBox.add(Box.createHorizontalGlue());
        okBox.add(okButton);
        okBox.add(Box.createHorizontalStrut(12));
        okBox.add(cancelButton);
        boxPanel.add(okBox);
        boxPanel.add(Box.createVerticalStrut(12));

        boxPanel.setBorder(new EmptyBorder(12, 12, 12, 12));
        setContentPane(boxPanel);
        pack();
        setResizable(false);
        setPreferredSize(new Dimension(600, boxPanel.getHeight()));
        setSize(new Dimension(600, boxPanel.getHeight() + 20));
    }

}
