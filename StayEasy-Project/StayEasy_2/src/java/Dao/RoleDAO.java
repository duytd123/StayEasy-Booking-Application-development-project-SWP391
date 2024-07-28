/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Connect.DBContext;
import Model.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class RoleDAO {
    Connection con;
    public RoleDAO(){
        DBContext dbcontext = new DBContext();
        try {
            con = dbcontext.connection;
            System.out.println("Successful");
        } catch (Exception e) {
            System.out.println("error: "+e);
        }
    }
    
     public List<Role> getRole(){
        String sql = "select * from dbo.Role";
        List<Role> list = new ArrayList<>();
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while(resultSet.next()){
                // lấy value theo từng cột
                int roleid = resultSet.getInt(1);
                String rolename = resultSet.getString(2);
                Role r = new Role(roleid, rolename);
                list.add(r);
            }
        } catch (Exception e) {
            System.out.println("error: "+e);
        }
        
        return list;
     }
      public static void main(String[] args) {
        RoleDAO roleDAO = new RoleDAO();
        List<Role> roles = roleDAO.getRole();
        
        if (!roles.isEmpty()) {
            System.out.println("Roles fetched successfully:");
            for (Role role : roles) {
                System.out.println("Role ID: " + role.getId() + ", Role Name: " + role.getName());
            }
        } else {
            System.out.println("No roles found or there was an error fetching roles.");
        }
    }
}
