package utez.edu.mx.restjava.model;

import utez.edu.mx.restjava.conexion.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;

    private final String CONSULTAR_EMPLEADOS = "SELECT * FROM employees";
    private final String CONSULTAR_EMPLEADO = "SELECT * FROM employees WHERE employeNumber =";

    public boolean registrarEmpleado(int employeeNumber, String lastname, String firstName, String extension, String email, int officeCode, int reportsTo, String jobTitle){
        boolean flag = false;
        try{
            con = Conexion.getConnection();
            pstm = con.prepareCall("INSERT INTO employees (employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle)VALUES(?,?,?,?,?,?,?,?);");
            pstm.setInt(1, employeeNumber);
            pstm.setString(2, lastname);
            pstm.setString(3, firstName);
            pstm.setString(4, extension);
            pstm.setString(5, email);
            pstm.setInt(6, officeCode);
            pstm.setInt(7, reportsTo);
            pstm.setString(8, jobTitle);

            flag = pstm.executeUpdate() == 1;
        }catch(SQLException e){
            System.out.println("Error en el metodo registrarEmpleado en la clase EmployeeDao");
        }finally {
            try {
                rs.close();
                pstm.close();
                con.close();
            }catch (SQLException e){
                System.out.println("Error al cerrar conexiones");
            }
        }

        return flag;
    }

    public boolean eliminarEmpleado(int employeeNumber){
        boolean flag = false;
        try{
            con = Conexion.getConnection();
            pstm = con.prepareCall("DELETE FROM `employees` WHERE employeeNumber = ?;");
            pstm.setInt(1, employeeNumber);

            flag = pstm.executeUpdate() == 1;
        }catch(SQLException e){
            System.out.println("Error en el metodo eliminarEmpleado en la clase EmployyeDao");
        }finally {
            try {
                rs.close();
                pstm.close();
                con.close();
            }catch (SQLException e){
                System.out.println("Error al cerrar conexiones");
            }
        }

        return flag;
    }

    public List<Employee> consultaEmpleados() {
        List<Employee> empleados = new ArrayList<Employee>();
        try {
            con = Conexion.getConnection();
            pstm = con.prepareStatement(CONSULTAR_EMPLEADOS);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Employee empleado = new Employee();
                empleado.setEmployeeNumber(rs.getInt("employeeNumber"));
                empleado.setLastName(rs.getString("lastName"));
                empleado.setFirstName(rs.getString("firstName"));
                empleado.setExtension(rs.getString("extension"));
                empleado.setEmail(rs.getString("email"));
                empleado.setOfficeCode(rs.getInt("officeCode"));
                empleado.setReportsTo(rs.getInt("reportsTo"));
                empleado.setJobTittle(rs.getString("jobTitle"));
                empleados.add(empleado);
            }
        } catch (SQLException e) {
            System.out.println("Error en el metodo consultarEmpleados en la clae EmployeeDao");
        } finally {
            try {
                rs.close();
                pstm.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar las conexiones");
            }
        }
        return empleados;
    }

    public List<Employee> consultarEmpleado() {
        List<Employee> empleados = new ArrayList<Employee>();
        try {
            con = Conexion.getConnection();
            pstm = con.prepareStatement(CONSULTAR_EMPLEADO);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Employee empleado = new Employee();
                empleado.setEmployeeNumber(rs.getInt("employeeNumber"));
                empleado.setLastName(rs.getString("lastName"));
                empleado.setFirstName(rs.getString("firstName"));
                empleado.setExtension(rs.getString("extension"));
                empleado.setEmail(rs.getString("email"));
                empleado.setOfficeCode(rs.getInt("officeCode"));
                empleado.setReportsTo(rs.getInt("reportsTo"));
                empleado.setJobTittle(rs.getString("jobTitle"));
                empleados.add(empleado);
            }
        } catch (SQLException e) {
            System.out.println("Error en el metodo consultarEmpleados en la clae EmployeeDao");
        } finally {
            try {
                rs.close();
                pstm.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar las conexiones");
            }
        }
        return empleados;
    }
}
