package com.erp.controller.employee;

import com.erp.bean.QueryVO;
import com.erp.bean.device.Info;
import com.erp.bean.employee.Employee;
import com.erp.bean.material.Material;
import com.erp.service.employee.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: xu
 * @Date: 2019/5/18 9:45
 */
@Controller
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("list")
    @ResponseBody
    public QueryVO findList(int page, int rows){
        return employeeService.getEmployeeInPage(page,rows);
    }

    @RequestMapping("get/{id}")
    @ResponseBody
    public Employee getEmployeeDataById(@PathVariable("id")String id){
        return employeeService.getDataById(id);
    }

    @RequestMapping("get_data")
    public @ResponseBody
    List<Employee> departmentGetData(){
        return employeeService.getData();
    }

    @RequestMapping("get")
    @ResponseBody
    public List<Employee> getData(){
        return employeeService.getData();
    }

    @RequestMapping("find")
    public String findEmployee(){
        return "employee_list";
    }

    //增加employee部分
    @RequestMapping("add_judge")
    @ResponseBody
    public String add_judge(){
        return "";
    }

    @RequestMapping("add")
    public String add(){
        return "employee_add";
    }

    @RequestMapping("insert")
    @ResponseBody
    public Info insertEmployee(@ModelAttribute("employee") Employee employee){
        Info info = new Info();
        Employee dataById = employeeService.getDataById(employee.getEmpId());
        if (dataById!=null){
            info.setStatus(0);
            info.setMsg("该员工编号已存在，请更换员工编号！");
            return info;
        }
        boolean b = employeeService.insertEmployee(employee);
        if(b){
            info.setStatus(200);
            info.setMsg("插入成功！");
        }else {
            info.setStatus(0);
            info.setMsg("插入异常！");
        }
        return info;
    }

    //删除employee部分
    @RequestMapping("delete_judge")
    @ResponseBody
    public String  deleteJudge(){
        return "";
    }

    @RequestMapping("delete_batch")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
    public Info delete_batch(@ModelAttribute("ids") String[] ids) {

        Info info = new Info();
        for (String id:ids) {
            boolean b = employeeService.deleteEmployee(id);
            if (b) {
                info.setStatus(200);
                info.setMsg("删除成功");
            } else {
                info.setStatus(0);
                info.setMsg("删除失败");
            }
        }

        return info;
    }

    //修改部分
    @RequestMapping("edit_judge")
    @ResponseBody
    public String edit_judge(){
        return "";
    }

    @RequestMapping("edit")
    public String edit(){
        return "employee_edit";
    }

    @RequestMapping("update_all")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
    public Info updateEmployee(@ModelAttribute Employee employee){
        Info info = new Info();
        boolean b = employeeService.updateEmployee(employee);
        if(b){
            info.setMsg("更新成功！");
            info.setStatus(200);
        }else {
            info.setMsg("更新失败！");
            info.setStatus(0);
        }
        return info;
    }

    //查询部分
    @RequestMapping("search_employee_by_departmentName")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
    public QueryVO<Employee> searchByDepartment(String searchValue, int page, int rows){
        QueryVO<Employee> employeeQueryVO = employeeService.queryByDepartment(page,rows,searchValue);
        return employeeQueryVO;
    }

    @RequestMapping("search_employee_by_employeeName")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
    public QueryVO<Employee> searchByName(String searchValue, int page, int rows){
        QueryVO<Employee> employeeQueryVO = employeeService.queryByName(page,rows,searchValue);
        return employeeQueryVO;
    }

    @RequestMapping("search_employee_by_employeeId")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
    public QueryVO<Employee> searchById(String searchValue, int page, int rows){
        QueryVO<Employee> employeeQueryVO = employeeService.queryByID(page,rows,searchValue);
        return employeeQueryVO;
    }
}
