package com.example.dpm_project;

import android.content.Context;
import android.net.Network;
import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.dpm_project.dao.ModuleDao;
import com.example.dpm_project.dao.PathwayDao;
import com.example.dpm_project.dao.PathwayModuleCrossRefDao;
import com.example.dpm_project.dao.StudentDao;
import com.example.dpm_project.models.Module;
import com.example.dpm_project.models.Pathway;
import com.example.dpm_project.models.PathwayModuleCrossRef;
import com.example.dpm_project.models.Student;
import com.example.dpm_project.models.StudentPathway;

import java.util.concurrent.Executors;

@Database(entities = {Module.class, Student.class, Pathway.class, PathwayModuleCrossRef.class}, version = 1, exportSchema = false)
public abstract class ModuleDatabase extends RoomDatabase {
    private static ModuleDatabase instance;

    public abstract ModuleDao moduleDao();

    public abstract PathwayDao pathwayDao();

    public abstract StudentDao studentDao();

    public abstract PathwayModuleCrossRefDao pathwayModuleCrossRefDao();

    public static synchronized ModuleDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ModuleDatabase.class, "module_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Executors.newSingleThreadExecutor().execute(() -> {
                //instance.studentDao().insert(new Student(1234,"asdf","asdf@asdf.com","asdfaasdfadf", 12345), "");

                //String code, String title, int isCompleted, String aim, int level, int year, String coRequisite, int semester

                // Modules for all pathways indexing from 1 to 14
                instance.moduleDao().insert(new Module("COMP501", "IT Operations", 0, "To enable students to apply fundamental IT technical support concepts and practice, and configure and administer systems and applications to meet organisational requirements.","5", "15", 1, "", "","CORE",1 ));
                instance.moduleDao().insert(new Module("COMP502", "Fundamentals of Programming and Problem Solving", 0, "To enable students to apply the principles of software development to create simple working applications and use problem-solving and decision-making techniques to provide innovative and timely Information Technology outcomes", "5", "15", 1, "","", "CORE",1 ));
                instance.moduleDao().insert(new Module("INFO501", "Professional Practice", 0, "To enable students to apply professional, legal, and ethical principles and practices in a socially responsible manner as an emerging IT professional, and apply communication, personal and interpersonal skills to enhance effectiveness in an IT role.", "5", "15", 1, "", "","CORE",1));
                instance.moduleDao().insert(new Module("INFO502", "Business Systems Analysis & Design", 0, "The student will be able to apply the fundamentals of information systems concepts and practice to support and enhance organisational processes and systems; and apply the fundamentals of interaction design concepts and practice to enhance interface design", "5", "15", 1, "", "","CORE",1));
                instance.moduleDao().insert(new Module("COMP503", "Introduction to Networks", 0, "To enable students to apply a broad operational knowledge of networking, and associated services and technologies to meet typical organisational requirements", "5", "15", 1, "", "","CORE",2));
                instance.moduleDao().insert(new Module("COMP504", "Operating Systems & Systems Support", 0, "To enable students to select, install, and configure IT hardware and systems software and use graphical (GUI) and command line interfaces (CLI) to meet organisational requirements.", "5", "15", 1, "", "","CORE",2));
                instance.moduleDao().insert(new Module("INFO503", "Database Principles", 0,"To enable students to apply database management systems concepts, data modelling techniques and structured query language in the design, implementation and operation of databases for organisations.", "5", "15", 1, "", "","CORE",2));
                instance.moduleDao().insert(new Module("INFO504", "Technical Support", 0, "To enable students to demonstrate an operational knowledge and understanding of IT service management, identify common issues related to IT security, and troubleshoot and resolve a range of common system problems.","5", "15", 1, "","", "CORE",2));
                instance.moduleDao().insert(new Module("COMP601", "Object-Oriented Programming", 0, "To enable students to gain the skills to develop software applications using Object Oriented Programming techniques. Students will enrich their programming and problem solving skills by applying classes, objects, constructors, methods and properties, inheritance and polymorphism to develop programming applications.", "6","15",2,"","INFO502","CORE",1));
                instance.moduleDao().insert(new Module("INFO601", "Data-modelling and SQL", 0, "To enable students to apply an indepth understanding of database modelling, database design and SQL concepts.","6","15",2,"","INFO503","CORE",1 ));
                instance.moduleDao().insert(new Module("MATH601", "Mathematics for IT", 0, "To enable students to gain mathematical skills and acquire in-depth understanding of mathematics as applied to Information Technology.", "6","15",2,"","","CORE",1));
                instance.moduleDao().insert(new Module("COMP602", "Web Development", 0,"To enable students to gain the in depth knowledge and skills required to be able to write programs in web programming languages that solve various web programming tasks","6","15",2,"","INFO502, COMP502","CORE",1));
                instance.moduleDao().insert(new Module("INFO602", "Business, Interpersonal Communications & Technical Writing", 0, "To enable students to develop an understanding of the principles and processes involved in effective interpersonal communication and technical writing used in managing client relationships.", "6","15",2,"","","CORE",2));
                instance.moduleDao().insert(new Module("BIZM701", "Business Essentials for IT Professionals", 0,"To enable students to develop an understanding of the common principles of business practice whilst focussing on the theoretical and practical concepts of accounting, marketing and management in order to understand the business context within which Information Technology solutions are developed", "7","15",3,"","INFO602","CORE",2 ));
                //id = 15
                instance.moduleDao().insert(new Module("COMP615", "Data Centre Infrastructure", 0, "To enable students to apply a broad operational knowledge of data centres and associated technologies to meet typical organisational requirements", "6","15",2,"","","NETWORK",2));
                //id = 16
                instance.moduleDao().insert(new Module("COMP605", "Data Structures & Algorithms", 0, "To enable students to apply programming and analytical skills to implement and analyze common data structures for computer programs. Students will cover a wide range of computer programming algorithms.", "6","15",2,"","","DATABASE, SOFTWARE",2));
                // id = 17
                instance.moduleDao().insert(new Module("COMP603", "The Web Environment", 0, "To enable students to examine the working environment and applications that are used in the web industry.","6","15",2,"","COMP602","WEB DEVELOPMENT",2));
                // id = 18
                instance.moduleDao().insert(new Module("INFO603", "Systems Administration", 0, "To enable students to gain the skills and knowledge required to effectively plan, install and administer a Microsoft Windows Server.", "6","15",2,"","", "NETWORK",2));
                // id = 19
                instance.moduleDao().insert(new Module("COMP609", "Applications Development", 0,"Students will gain in-depth programming and problem solving skills. They will be able to use a modern development environment and a programming language to implement a working solution. This includes rigorous programming and effective use of built-in data structures and other useful features of the development environment.","6","15",2,"","COMP601, MATH601","SOFTWARE",2));
                // id = 20
                instance.moduleDao().insert(new Module("COMP606", "Web Programming", 0,"To enable students to gain the in depth knowledge and skills required to be able to write programs in web programming languages that solve various web programming tasks.","6","15",2,"","COMP602","DATABASE, WEB DEVELOPMENT",2));
                // id = 21
                instance.moduleDao().insert(new Module("COMP604", "Routing & Switching Essentials", 0,"To enable students to apply a broad operational knowledge of networking, and associated services and technologies to meet typical organisational requirements.","6","15",2,"","INFO503","NETWORK",2));
                // id = 22
                instance.moduleDao().insert(new Module("MATH602", "Mathematics for Programming", 0, "To enable students to obtain the mathematical skills to facilitate an in-depth understanding of advanced programming techniques. Students will be able to solve problems in recurrence functions, asymptotic functions, algorithmic puzzles, combinatorics and graph theory and advanced topics in probability and statistics.","6","15",2,"","MATH601","SOFTWARE",2));
                // id = 23
                instance.moduleDao().insert(new Module("INFO604", "Database Systems", 0,"To enable students to understand and discuss database systems, concepts, modelling, design and administration, and to apply theoretical and practical administrative tasks in a database administrator's role","6","15",2,"INFO601","INFO503","DATABASE",2));
                // id = 24
                instance.moduleDao().insert(new Module("INFO605", "Human-Computer Interaction", 0,"To enable students to understand the supporting theories and principles of user interface design with respect to human computer interaction. They will investigate applications in human computer interaction and apply usability best practices and processes","6","15",2,"","COMP602","WEB DEVELOPMENT",2));
                // id = 25
                instance.moduleDao().insert(new Module("COMP701", "Advanced Networking", 0,"To enable students to investigate and configure advanced system administration tools, advanced network virtualisation and wireless networking technologies. Students will also research emerging networking technologies.","7","15",3,"","INFO603","NETWORK",1));
                // id = 26
                instance.moduleDao().insert(new Module("COMP707", "Principles of Software Testing", 0,"Students will gain comprehensive knowledge of software testing methodologies and software testing tools used in industry and apply fundamental aspects of software testing incorporating system requirements, quality assurance, testing processes, automation, testing types and testing levels. This forms the third part of the Software Engineering Capstone Project.", "7","15",3,"","COMP605","SOFTWARE",1));
                // id = 27
                instance.moduleDao().insert(new Module("INFO704", "Data-Warehousing and Business Intelligence", 0, "To enable students to examine the main components of data warehousing and apply it to business intelligence applications, enabling them to provide solutions which incorporate extracting data from different sources, storing data in a data warehouse and developing applications for business decision-making", "7","15",3,"","INFO601","DATABASE",1));
                // id = 28
                instance.moduleDao().insert(new Module("COMP717", "Advanced Web Technologies", 0, "To enable students to gain an understanding of the architecture, components, and operation of server-based virtualisation infrastructure and meet organisational requirements","7","15",3,"","COMP606","WEB DEVELOPMENT",1 ));
                // id = 29
                instance.moduleDao().insert(new Module("INFO702", "Cyber-Security", 0,"To enable students to investigate computer system attacks and vulnerabilities in relation to operating systems (OS), applications, networking and websites, and investigate the security techniques for protecting a computer system from such attacks.", "7","15",3,"","COMP504 OR INFO603","NETWORK, SOFTWARE, WEB DEVELOPMENT",1));
                // id = 30
                instance.moduleDao().insert(new Module("INFO706", "Database Front-End Applications", 0, "To enable students to understand and apply front-end applications and their interfaces with supporting databases", "7","15",3,"COMP709","INFO601, INFO604", "DATABASE",1));
                // id = 31
                instance.moduleDao().insert(new Module("COMP704", "Network Security", 0, "To enable students to gain core security knowledge and skills needed for installation, troubleshooting, and monitoring of networks to maintain the integrity, confidentiality, and availability of data and devices.", "7","15",3,"","COMP604","NETWORK",1 ));
                // id = 32
                instance.moduleDao().insert(new Module("COMP709", "Mobile Applications Development", 0, "To enable students to design, develop and implement mobile applications on a given platform", "7","15",3,"","COMP601, MATH601","DATABASE, WEB DEVELOPMENT",1));
                // id = 33
                instance.moduleDao().insert(new Module("COMP702", "Scaling Networks", 0, "To enable students to gain an understanding of the architecture, components, security, troubleshooting and operation of large-scale networks","7","15",3,"","COMP604","NETWORK",1 ));
                // id = 34
                instance.moduleDao().insert(new Module("COMP706", "Game Development", 0, "To enable students to understand supporting theories and principles of game design and apply these to the art and science of game design, development and programming.", "7","15",3,"","COMP601, COMP605, MATH602","SOFTWARE",1));
                // id = 35
                instance.moduleDao().insert(new Module("INFO707", "Cloud Server Databases", 0, "To enable students to gain an in-depth knowledge of cloud server database concepts, fundamentals and essentials. They will apply practical skills to install, setup, configure, manage and maintain a cloud server database and deploy cloud database services to support database applications.", "7","15",3,"","INFO601, INFO604","DATABASE",1 ));
                // id = 36
                instance.moduleDao().insert(new Module("COMP710", "Web Applications Development", 0, "To enable students to apply practical knowledge of Model View Controller (MVC) frameworks to plan, design and implement web applications. The core focus will be on architecture design and implementation of a web application that will meet a set of functional requirements and user interface requirements, and address business models","7","15",3,"","COMP602, COMP606","WEB DEVELOPMENT",1));
                // id = 37
                instance.moduleDao().insert(new Module("COMP716", "Virtualisation Essentials", 0,"To enable students to gain an understanding of the architecture, components, and operation of server-based virtualisation infrastructure and meet organisational requirements","7","15",3,"","COMP504, INFO603","NETWORK",2));
                // id = 38
                instance.moduleDao().insert(new Module("INFO703", "Big Data and Analytics", 0, "To enable students to gain the practical knowledge and skills required to store, manage and analyse large amounts of data, using appropriate algorithms.", "7","15",3,"","COMP605, MATH601","DATABASE",2));
                // id = 39
                instance.moduleDao().insert(new Module("INFO708", "Data Visualisation", 0,"To enable students to study and apply visual techniques that transform data into a format efficient for human perception, cognition, and communication, thus allowing for extraction of meaningful information and insight. Students will investigate data visualisation techniques, human visual systems and cognitive perception, and design, construct, and evaluate data visualisations", "7","15",3,"","COMP606, COMP607","WEB DEVELOPMENT",2));
                // id = 40
                instance.moduleDao().insert(new Module("COMP714", "Network Engineering Project", 0,"To enable students to develop a Computer networking solution from a set of requirements documents. This module is the Network Engineering Capstone Project.", "7","15",3,"","COMP701, COMP702, COMP704","NETWORK",2));
                // id = 41
                instance.moduleDao().insert(new Module("INFO710", "Internship", 0,"This module will enable students to demonstrate that they can successfully undertake original work that applies the theoretical and practical knowledge gained in other modules in a workplace environment.Enable student to gain real world experience and build and Industry portfolio. Making professional contacts to build future industry networks.", "7","15",3,"","COMP701, COMP702, COMP704","CORE",2));
                // id = 42
                instance.moduleDao().insert(new Module("DFNZ701", "Design Factory", 0, "This module aims to develop students who can use problem solving skills and human centred design to solve real world problems. It aims to cultivate inquiring, self-aware practitioners who can apply problem solving to a variety of situations and develop the necessary future focused skills that will prepare them for professional contexts Students will experience facilitated learning in interdisciplinary teams in a dedicated holistic learning space. Working in interdisciplinary teams, students will co-create a solution with an Industry Partner and solve a complex real world problem", "7","15",3,"","","CORE",2));
                // id = 43
                instance.moduleDao().insert(new Module("COMP715", "Software Engineering Project", 0, "Students will gain advanced software development skills. They will be able to provide an in depth analysis of prototyping, performance, correctness, software reusability, scalability and quality and maintenance and appropriate documentation. This module is the Software Engineering capstone project.", "7","15",3,"","COMP707","SOFTWARE",2));
                // id = 44
                instance.moduleDao().insert(new Module("INFO712", "Database Architecture Project", 0, "To enable students to further develop their knowledge of Database Architecture by analysing, designing and implementing a database solution. This module is the Database Architecture Capstone Project", "7","15",3,"","INFO707, INFO704, INFO706","DATABASE",2));
                // id = 45
                instance.moduleDao().insert(new Module("COMP713", "Web Application Project", 0, "To enable students to further develop their knowledge of Web Applications by analysing, designing and implementing a web solution. This module is the Web Application Capstone Project.", "7","15",3,"INFO710","COMP606, COMP710","WEB DEVELOPMENT",2));
                // id = 46
                instance.moduleDao().insert(new Module("INFO703", "Big Data and Analytics", 0, "To enable students to gain the practical knowledge and skills required to store, manage and analyse large amounts of data, using appropriate algorithms.", "7","15",3,"","COMP605, MATH602","SOFTWARE",2));
                // id = 47
                instance.moduleDao().insert(new Module("COMP709", "Mobile Applications Development", 0, "To enable students to design, develop and implement mobile applications on a given platform", "7","15",3,"","COMP601, MATH602","SOFTWARE",1));
                // id = 48
                instance.moduleDao().insert(new Module("INFO710", "Internship", 0,"This module will enable students to demonstrate that they can successfully undertake original work that applies the theoretical and practical knowledge gained in other modules in a workplace environment.Enable student to gain real world experience and build and Industry portfolio. Making professional contacts to build future industry networks.", "7","15",3,"","INFO704, INFO706, INFO707","DATABASE",2));
                // id = 49
                instance.moduleDao().insert(new Module("INFO710", "Internship", 0,"This module will enable students to demonstrate that they can successfully undertake original work that applies the theoretical and practical knowledge gained in other modules in a workplace environment.Enable student to gain real world experience and build and Industry portfolio. Making professional contacts to build future industry networks.", "7","15",3,"","COMP707","SOFTWARE",2));
                // id = 50
                instance.moduleDao().insert(new Module("INFO710", "Internship", 0,"This module will enable students to demonstrate that they can successfully undertake original work that applies the theoretical and practical knowledge gained in other modules in a workplace environment.Enable student to gain real world experience and build and Industry portfolio. Making professional contacts to build future industry networks.", "7","15",3,"","COMP606, COMP710","WEB DEVELOPMENT",2));

                //changed COMP602 to MATH602(Maths for Programming), not added INFO701(Project Management),


                instance.pathwayDao().insert(new Pathway("Database Architecture"));
                instance.pathwayDao().insert(new Pathway("Network Engineering"));
                instance.pathwayDao().insert(new Pathway("Software Engineering"));
                instance.pathwayDao().insert(new Pathway("Web Development"));
                for (int i = 1; i <= 4; i++) {
                    for (int j = 1; j <= 14; j++) {
                        instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(i, j));
                    }
                }
                for (int i = 1; i <= 4; i++) {
                        instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(i, 42));
                }
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(2, 15));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(3, 16));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(1, 16));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(4, 17));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(2, 18));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(3, 19));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(1, 20));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(4, 20));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(2, 21));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(3, 22));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(1, 23));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(4, 24));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(2, 25));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(3, 26));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(1, 27));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(4, 28));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(2, 29));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(3, 29));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(4, 29));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(1, 30));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(2, 31));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(1, 32));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(4, 32));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(2, 33));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(3, 34));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(1, 35));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(4, 36));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(2, 37));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(1, 38));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(4, 39));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(2, 40));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(3, 43));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(1, 44));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(4, 45));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(3, 46));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(3, 47));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(2, 41));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(1, 48));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(3, 49));
                instance.pathwayModuleCrossRefDao().insert(new PathwayModuleCrossRef(4, 50));

            });
        }
    };

}
