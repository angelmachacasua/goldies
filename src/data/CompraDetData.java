/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import entities.CompraDet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sqlite.SQLiteConfig;

/**
 *
 * @author Asullom
 */
public class CompraDetData {
    static Connection cn = Conn.connectSQLite();
    static PreparedStatement ps;
    static Date dt = new Date();
    static SimpleDateFormat sdf = new SimpleDateFormat(SQLiteConfig.DEFAULT_DATE_STRING_FORMAT);
    static String currentTime = sdf.format(dt);
    
    public static List<CompraDet> listByCompra(int comp_id) {
        String sql = "";
        List<CompraDet> ls = new ArrayList<CompraDet>();

        sql = " SELECT * FROM compra_det "
                + " WHERE comp_id = " + comp_id + " "
                + " ORDER BY id ";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                CompraDet d = new CompraDet();
                d.setId(rs.getInt("id"));
                String fechax = rs.getString("fecha");
                System.out.println("list.fecha:" + fechax);
                try {
                    Date datex = sdf.parse(fechax);
                    System.out.println("list.date:" + datex);
                    d.setFecha(datex);
                } catch (Exception e) {
                }
                d.setComp_id(rs.getInt("comp_id"));
                d.setGlosa(rs.getString("glosa"));
                d.setCant_gr(rs.getDouble("cant_gr"));
                d.setEsdolares(rs.getInt("esdolares"));

                d.setOnza(rs.getDouble("onza"));
                d.setPorc(rs.getDouble("porc"));
                d.setLey(rs.getDouble("ley"));
                d.setSistema(rs.getDouble("sistema"));
                d.setTc(rs.getDouble("tc"));
                d.setPrecio_do(rs.getDouble("precio_do"));
                d.setPrecio_so(rs.getDouble("precio_so"));

                d.setTotal_do(rs.getDouble("total_do"));
                d.setTotal_so(rs.getDouble("total_so"));
                d.setUser_id(rs.getInt("user_id"));
                d.setActivo(rs.getInt("activo"));
                ls.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompraDetData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }
}
