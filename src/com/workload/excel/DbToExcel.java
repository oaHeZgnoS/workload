package com.workload.excel;

import java.io.File;
import java.util.List;

import com.workload.domain.Workload;
import com.workload.service.Wl_teacherService;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class DbToExcel {

    public static void main(String[] args) {
        try {
            WritableWorkbook wwb = null;
             
               // 创建可写入的Excel工作簿
               String fileName = "D://wl_teacher0.xlsx";
               File file=new File(fileName);
               if (!file.exists()) {
                   file.createNewFile();
               }
               //以fileName为文件名来创建一个Workbook
               wwb = Workbook.createWorkbook(file);

               // 创建工作表
               WritableSheet ws = wwb.createSheet("Test Shee 1", 0);
               
               //查询数据库中所有的数据
               List<Workload> list= Wl_teacherService.getAllByDb();
               //要插入到的Excel表格的行号，默认从0开始
               Label labelId= new Label(0, 0, "编号(id)");//表示第
               Label labelDepartment= new Label(1, 0, "系(department)");
               Label labelT_name= new Label(2, 0, "教师姓名(t_name)");
               Label labelName= new Label(3, 0, "工作量名(name)");
               Label labelTime= new Label(4, 0, "用时(time)");
               Label labelClasses= new Label(5, 0, "班级(classes)");
               Label labelAmount= new Label(6, 0, "人数(amount)");
               Label labelWl= new Label(7, 0, "工作量(wl)");
               Label labelType= new Label(8, 0, "类别(type)");
               Label labelRemark= new Label(9, 0, "备注(remark)");
               Label labelTerm= new Label(10, 0, "学期(term)");
               
               ws.addCell(labelId);
               ws.addCell(labelDepartment);
               ws.addCell(labelT_name);
               ws.addCell(labelName);
               ws.addCell(labelTime);
               ws.addCell(labelClasses);
               ws.addCell(labelAmount);
               ws.addCell(labelWl);
               ws.addCell(labelType);
               ws.addCell(labelRemark);
               ws.addCell(labelTerm);
               for (int i = 0; i < list.size(); i++) {
                   
                   Label labelId_i= new Label(0, i+1, list.get(i).getId()+"");
                   Label labelDepartment_i= new Label(1, i+1, list.get(i).getDepartment());
                   Label labelT_name_i= new Label(2, i+1, list.get(i).getT_name());
                   Label labelName_i= new Label(3, i+1, list.get(i).getName());
                   Label labelTime_i= new Label(4, i+1, list.get(i).getTime());
                   Label labelClasses_i= new Label(5, i+1, list.get(i).getClasses());
                   Label labelAmount_i= new Label(6, i+1, list.get(i).getAmount()+"");
                   Label labelWl_i= new Label(7, i+1, list.get(i).getWl()+"");
                   Label labelType_i= new Label(8, i+1, list.get(i).getType()+"");
                   Label labelRemark_i= new Label(9, i+1, list.get(i).getRemark());
                   Label labelTerm_i= new Label(10, i+1, list.get(i).getTerm());
                   
                   ws.addCell(labelId_i);
                   ws.addCell(labelDepartment_i);
                   ws.addCell(labelT_name_i);
                   ws.addCell(labelName_i);
                   ws.addCell(labelTime_i);
                   ws.addCell(labelClasses_i);
                   ws.addCell(labelAmount_i);
                   ws.addCell(labelWl_i);
                   ws.addCell(labelType_i);
                   ws.addCell(labelRemark_i);
                   ws.addCell(labelTerm_i);
               }
             
              //写进文档
               wwb.write();
              // 关闭Excel工作簿对象
               wwb.close();
             
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}
