package org.example;

import org.example.controllers.UserController;
import org.example.models.*;
import org.example.views.UserView;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        DataService dataService = new DataService();
        StudentGroupService studentGroupService = new StudentGroupService();
        UserView userView = new UserView();
        UserController userController = new UserController(dataService, studentGroupService, userView);
        FlowOfGroups flowOfGroups1 = new FlowOfGroups();
        FlowOfGroups flowOfGroups2 = new FlowOfGroups();

        Student student1 =  userController.createStudent("Гирдюк", "Юлия", "Владимировна", LocalDate.now());
        Student student2 = userController.createStudent("Антонов", "Николай", "Александрович", LocalDate.now());
        Student student3 = userController.createStudent("Седых", "Игорь", "Вадимович", LocalDate.now());
        Student student4 = userController.createStudent("Григорьева", "Елена", "Тимофеевна", LocalDate.now());
        Student student5 = userController.createStudent("Петров", "Егор", "Витальевич", LocalDate.now());
        List<Student> students = new ArrayList<>(List.of(new Student[]{student1, student3, student5}));
        List<Student> students2 = new ArrayList<>(List.of(new Student[]{student4, student2}));
        StudentGroup flow = userController.createStudentGroup(new Teacher("Казак", "Дмитрий", "Леонидович", LocalDate.now()),
                students);
        StudentGroup flow2 = userController.createStudentGroup(new Teacher("Попов", "Георгий", "Александрович", LocalDate.now()),
                students2);
        flowOfGroups1.addFlowOfGroups(flow);
        System.out.println("=== поток ===");
        System.out.println(flowOfGroups1);
        flowOfGroups2.addFlowOfGroups(flow2);
        System.out.println("=== поток ===");
        System.out.println(flowOfGroups2);
        flowOfGroups2.addFlowOfGroups(flow2);
        userController.StreamComparator(flowOfGroups2, flowOfGroups1);
    }
}

