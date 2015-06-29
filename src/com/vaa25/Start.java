package com.vaa25;

import com.vaa25.postgis.Points;
import com.vaa25.postgis.SelectRadiusPointsParameter;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.postgis.Point;

import java.io.Reader;
import java.util.List;

public class Start {

    public static void main(String[] args) {
        System.out.println("started");
        try {

            // Считываем конфигурационный файл

            Reader reader = org.apache.ibatis.io.Resources.getResourceAsReader("conf.xml");
            // Инициализируем SqlSessionFactory
            SqlSessionFactory sqlSessionFactory =  new SqlSessionFactoryBuilder().build(reader);
            // Инициализируем сессию
            SqlSession session = sqlSessionFactory.openSession();

//            printMaster(session);
            printOnePoint(session);
            System.out.println();
            printAllPoints(session);
            System.out.println();
            printRadiusPoints(session);

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

//    private static void printMaster(SqlSession session) {
//        // Выполняем запрос
//        Master master = session.selectOne("masterMapper.selectMaster",1);
//
////            Выводим результаты на консоль
//        System.out.println("ID: "+master.getId()+", Name: "+master.getName());
//        List<Detail> list = master.getDetails();
//        for (Detail detail : list)
//        {
//            System.out.println("  detail: "+detail.getId()+" - "+detail.getName());
//            List<Subdetail> list1 = detail.getSubdetails();
//            for (Subdetail subdetail : list1) System.out.println("    subdetail: "+subdetail.getName());
//        }
//    }

    private static void printOnePoint(SqlSession session) {
        Points points= session.selectOne("postgisMapper.selectPoints", 2);
        printPoint(points);
    }

    private static void printAllPoints(SqlSession session) {
        List<Points> pointsList=session.selectList("postgisMapper.selectAllPoints");
        printPointList(pointsList);
    }

    private static void printPoint(Points points1) {
        System.out.println("ID="+points1.getId()+": "+points1.getDescription()+" - "+points1.getPoint());
    }

    private static void printRadiusPoints(SqlSession session) {
        Point center= new Point(30.4, 50.4);
        double radius=0.069;
        List<Points> pointsList=session.selectList("postgisMapper.selectRadiusPoints",new SelectRadiusPointsParameter(center,radius));
        printPointList(pointsList);
    }

    private static void printPointList(List<Points> pointsList) {
        for (Points points1 : pointsList) {
            printPoint(points1);
        }
    }


}