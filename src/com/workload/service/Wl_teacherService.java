package com.workload.service;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

import com.workload.db.DBHelper;
import com.workload.domain.Workload;

public class Wl_teacherService {
    /**
     * ��ѯwl_teacher0�������е�����
     * @return 
     */
    public static List<Workload> getAllByDb(){
        List<Workload> list=new ArrayList<Workload>();
        try {
            DBHelper db=new DBHelper();
            String sql="select * from wl_teacher0";
            ResultSet rs= db.Search(sql, null);
            while (rs.next()) {
                int id=rs.getInt("id");
                String major=rs.getString("major");
                int t_id=rs.getInt("t_id");
                String t_name=rs.getString("t_name");
                String name=rs.getString("name");
                String time=rs.getString("time");
                String classes=rs.getString("classes");
                int amount=rs.getInt("amount");
                float wl=rs.getFloat("wl");
                String type=rs.getString("type");
                String remark=rs.getString("remark");
                String term=rs.getString("term");
                
                /*System.out.println(id+" "+major+" "+t_id+ " "+t_name+" "
                 * +name+" "+time+" "+classes+" "+amount+" "+wl+" "+type
                 * +" "+remark+" "+term);*/
                list.add(new Workload(id, major, t_id, t_name,name,time,
                		classes,amount,wl,type,remark,term));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    /**
     * ��ѯָ��Ŀ¼�е��ӱ�������е�����
     * @param file �ļ�����·��
     * @return
     */
    public static List<Workload> getAllByExcel(String file){
        List<Workload> list=new ArrayList<Workload>();
        try {
            Workbook rwb=Workbook.getWorkbook(new File(file));
            Sheet rs=rwb.getSheet("Test Shee 1");//����rwb.getSheet(0)
            int clos=rs.getColumns();//�õ����е���
            int rows=rs.getRows();//�õ����е���
            
            System.out.println(clos+" rows:"+rows);
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //��һ�����������ڶ���������
                    String id=rs.getCell(j++, i).getContents();//Ĭ������߱��Ҳ��һ�� ���������j++
                    String major=rs.getCell(j++, i).getContents();
                    String t_id=rs.getCell(j++, i).getContents();
                    String t_name=rs.getCell(j++, i).getContents();
                    String name=rs.getCell(j++, i).getContents();
                    String time=rs.getCell(j++, i).getContents();
                    String classes=rs.getCell(j++, i).getContents();
                    String amount=rs.getCell(j++, i).getContents();
                    String wl=rs.getCell(j++, i).getContents();
                    String type=rs.getCell(j++, i).getContents();
                    String remark=rs.getCell(j++, i).getContents();
                    String term=rs.getCell(j++, i).getContents();
                    
                    System.out.println(id+" "+major+" "+t_id+ " "+t_name+" "
                      +name+" "+time+" "+classes+" "+amount+" "+wl+" "+type
                      +" "+remark+" "+term);
                    list.add(new Workload(Integer.parseInt(id), major, 
                    		Integer.parseInt(t_id), t_name,name,
                    		time,classes,
                    		Integer.parseInt(amount),Float.parseFloat(wl),
                    		type,remark,term));
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        return list;
        
    }
    
    /**
     * ͨ��Id�ж��Ƿ����
     * @param id
     * @return
     */
    public static boolean isExist(int id){
        try {
        	DBHelper db=new DBHelper();
            ResultSet rs=db.Search("select * from wl_teacher0 where id=?", new String[]{id+""});
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static void main(String[] args) {
        /*List<Workload> all=getAllByDb();
        for (Workload workload : all) {
            System.out.println(workload.toString());
        }*/
        
        System.out.println(isExist(1));
        
    }
    
}