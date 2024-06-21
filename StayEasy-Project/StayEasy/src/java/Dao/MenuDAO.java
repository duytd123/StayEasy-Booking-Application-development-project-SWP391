/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Connect.DBContext;
import Model.Menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class MenuDAO {
    Connection con;
    public MenuDAO(){
        DBContext dbcontext = new DBContext();
        try {
            con = dbcontext.connection;
            System.out.println("Successful");
        } catch (Exception e) {
            System.out.println("error: "+e);
        }
    }
    
    public List<Menu> getMenu(){
        String sql = "select * from dbo.Menu";
        List<Menu> list = new ArrayList<>();
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while(resultSet.next()){
                // lấy value theo từng cột
                int menuid = resultSet.getInt(1);
                String menuname = resultSet.getString(2);
                Menu m = new Menu(menuid, menuname);
                list.add(m);
            }
        } catch (Exception e) {
            System.out.println("error: "+e);
        }
        
        return list;
    }
    
     public Menu getMenuById(int id) {
        String sql = "SELECT * FROM dbo.Menu WHERE menu_id = ?";
        Menu menu = null;
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet resultSet = pre.executeQuery();
            if (resultSet.next()) {
                int menuId = resultSet.getInt("id");
                String menuName = resultSet.getString("name");
                menu = new Menu(menuId, menuName);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return menu;
    }
}
