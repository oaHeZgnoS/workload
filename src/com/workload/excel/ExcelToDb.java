package com.workload.excel;

import java.util.List;

import com.workload.db.DBHelper;
import com.workload.domain.Workload;
import com.workload.service.Wl_teacherService;

public class ExcelToDb {
    public static void main(String[] args) {
        //得到表格中所有的数据
        List<Workload> listExcel=Wl_teacherService.getAllByExcel("f://wl_teacher0.xlsx");
        /*//得到数据库表中所有的数据
        List<Workload> listDb=Wl_teacherService.getAllByDb();*/
        
        DBHelper db=new DBHelper();
        
        for (Workload wl_teacher : listExcel) {
            int id=wl_teacher.getId();
            if (!Wl_teacherService.isExist(id)) {
                //不存在就添加
                String sql="insert into wl_teacher0 (department,t_id,t_name,name,time,classes,amount,wl,type,remark,term) values(?,?,?,?,?,?,?,?,?,?,?)";
                String[] str=new String[]{wl_teacher.getDepartment(),wl_teacher.getT_id()+"",
                		wl_teacher.getT_name(),wl_teacher.getName(),wl_teacher.getTime(),
                		wl_teacher.getClasses(),wl_teacher.getAmount()+"",wl_teacher.getWl()+"",
                		wl_teacher.getType(),wl_teacher.getRemark(),wl_teacher.getTerm()};
                db.AddU(sql, str);
            }else {
                //存在就更新
                String sql="update wl_teacher0 set department=?,t_id=?,t_name=?,name=?,time=?,classes=?,amount=?,wl=?,type=?,remark=?,term=? where id=?";
                String[] str=new String[]{wl_teacher.getDepartment(),wl_teacher.getT_id()+"",
                		wl_teacher.getT_name(),wl_teacher.getName(),wl_teacher.getTime(),
                		wl_teacher.getClasses(),wl_teacher.getAmount()+"",wl_teacher.getWl()+"",
                		wl_teacher.getType(),wl_teacher.getRemark(),wl_teacher.getTerm(),id+""};
                db.AddU(sql, str);
            }
        }
    }
}
