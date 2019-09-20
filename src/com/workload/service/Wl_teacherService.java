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
     * 查询wl_teacher0表中所有的数据
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
     * 查询指定目录中电子表格中所有的数据
     * @param file 文件完整路径
     * @return
     */
    public static List<Workload> getAllByExcel(String file){
        List<Workload> list=new ArrayList<Workload>();
        try {
            Workbook rwb=Workbook.getWorkbook(new File(file));
            Sheet rs=rwb.getSheet("Test Shee 1");//或者rwb.getSheet(0)
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行
            
            System.out.println(clos+" rows:"+rows);
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数
                    String id=rs.getCell(j++, i).getContents();//默认最左边编号也算一列 所以这里得j++
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
     * 通过Id判断是否存在
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