

package data;


import entities.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import util.ErrorLogger;

public class UsuarioData {
    static Connection coneccion=Conn.connectSQLite();
    static PreparedStatement ConsultaPreparada;
     static ErrorLogger log = new ErrorLogger(CienteData.class.getName());
    public static int crear (Usuario Usuario){
        int RespuestaConsultaPreparada=0;
        String ConsultaSQL="INSERT INTO usuario(Nombre, DNI, InformacionAdicional) VALUES (?, ?, ?)";
        try{
            ConsultaPreparada=coneccion.prepareStatement(ConsultaSQL);
            ConsultaPreparada.setString(1, Usuario.getNombre());
            ConsultaPreparada.setString(2, Usuario.getDNI());
            ConsultaPreparada.setString(3, Usuario.getInformacionAdicional());
            RespuestaConsultaPreparada=ConsultaPreparada.executeUpdate();
            
            }catch(SQLException excepcion){}
                        
        return RespuestaConsultaPreparada;
        
      
    }
    public static List<Usuario> list(String filter) {
        String filtert = null;
        if (filter == null) {
            filtert = "";
        } else {
            filtert = filter;
        }
        System.out.println("list.filtert:" + filtert);

        List<Usuario> ls = new ArrayList();
        String sql = "";
        if (filtert.equals("")) {
            sql = "SELECT * FROM usuario ORDER BY ID";
        } else {
            sql = "SELECT * FROM usuario WHERE (id LIKE'" + filter + "%' OR "
                    + "nombres LIKE'" + filter + "%' OR infoadic LIKE'" + filter + "%' OR "
                    + "id LIKE'" + filter + "%') "
                    + "ORDER BY nombres";
        }
        try {
            Statement st = coneccion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Usuario d = new Usuario();
                d.setID(rs.getInt("ID"));
                d.setNombre(rs.getString("Nombre"));
                d.setInformacionAdicional(rs.getString("InformacionAdicional"));
                ls.add(d);
            }
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "list", ex);
        }
        return ls;
    }
    public static Usuario getByPId(int id) {
        Usuario d = new Usuario();

        String sql = "SELECT * FROM usuario WHERE ID = ? ";
        int i = 0;
        try {
            ConsultaPreparada = coneccion.prepareStatement(sql);
            ConsultaPreparada.setInt(++i, id);
            ResultSet rs = ConsultaPreparada.executeQuery();
            while (rs.next()) {
                d.setID(rs.getInt("ID"));
                d.setNombre(rs.getString("Nombre"));
                d.setDNI(rs.getString("DNI"));
               
                d.setInformacionAdicional(rs.getString("InformacionAdicional"));
            }
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "getByPId", ex);
        }
        return d;
    }
}
