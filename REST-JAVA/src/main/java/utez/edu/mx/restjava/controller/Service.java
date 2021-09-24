package utez.edu.mx.restjava.controller;

import utez.edu.mx.restjava.model.Employee;
import utez.edu.mx.restjava.model.EmployeeDao;

import javax.jws.WebParam;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/employee")
public class Service {

    @POST
    @Path("/employee")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean creteEmployee(int employeeNumber, String lastName, String firstName, String extension, String email, int officeCode, int reportsTo, String jobTitle){
        boolean flag = false;
        try {
            flag = new EmployeeDao().registrarEmpleado(employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    @POST
    @Path("/employee")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteEmployee(int employeeNumber){
        boolean flag = false;
        try {
            flag = new EmployeeDao().eliminarEmpleado(employeeNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }


    @GET
    @Path("/employee")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        try {
            employees = new EmployeeDao().consultaEmpleados();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employees;
    }

    @GET
    @Path("/employee{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> getEmployee(@PathParam("id") int employeeNumber) throws Exception {
        List<Employee> employees = new ArrayList<>();
        try {
            employees = new EmployeeDao().consultarEmpleado();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employees;
    }

    @POST
    @Path("/employee")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Employee saveEmployee(@WebParam Employee employee) {
        Employee empleado = new Employee();

        return empleado;
    }
}
