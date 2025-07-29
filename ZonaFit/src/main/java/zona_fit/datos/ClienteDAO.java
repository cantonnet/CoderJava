package zona_fit.datos;

import zona_fit.conexion.Conexion;
import zona_fit.dominio.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements IClienteDAO{

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = Conexion.getConexion();
        var sql = "SELECT * FROM cliente ORDER By id";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                var cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                clientes.add(cliente);

            }
        }
        catch (Exception e)
        {
            System.out.println("Error al listar clientes"+e.getMessage());
        }
        finally{
            try {
                con.close();
            }catch (Exception e)
            {
                System.out.println("Error al cerrar conexion"+e.getMessage());
            }
        }

        return clientes;
    }

    @Override
    public boolean buscarClientePorId(Cliente cliente) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = Conexion.getConexion();
        String sql = "SELECT * FROM cliente WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,cliente.getId());
            rs = ps.executeQuery();
            if ( rs.next() ){
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido((rs.getString("apellido")));
                cliente.setMembresia((rs.getInt("membresia")));
                return true;

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar sesion");
            }
        }
        return false;
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = Conexion.getConexion();
        String sql = "INSERT INTO cliente(nombre, apellido, membresia) " + " VALUES(?, ?, ?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al agregar cliente");;
        }
        finally {
            try{
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexion");
            }
        }
        return false;
    }

    @Override
    public boolean modificarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = Conexion.getConexion();
        String sql = "UPDATE cliente SET nombre=?, apellido=?, membresia=? "+"WHERE id = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.setInt(4, cliente.getId());
            ps.execute();
            return true;
        }catch (Exception e)
        {
            System.out.println("Error al actualizar cliente "+ e.getMessage());
        }
        finally{
            try {
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error al intentar cerrar la conexion");
            }

        }
        return false;
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = Conexion.getConexion();
        String sql = "DELETE FROM cliente WHERE id = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            ps.execute();
            return true;
        }catch (Exception e)
        {
            System.out.println("Error al eliminar cliente"+e.getMessage());
        }
        finally {
            try {
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error al cerrar conexion"+e.getMessage());
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //Listar Clientes
//        System.out.println("*** prueba listar clientes ***");
//        IClienteDAO clienteDAO = new ClienteDAO();
//        var clientes = clienteDAO.listarClientes();
//        clientes.forEach(System.out::println);

        IClienteDAO clienteDAO = new ClienteDAO();
        //Buscar cliente
        var cliente1 = new Cliente(2);
//        System.out.println("Cliente antes de la busqueda: "+cliente1);
//        var encontrado = clienteDAO.buscarClientePorId(cliente1);
//        if (encontrado)
//        {
//            System.out.println("Cliente encontrado: "+cliente1);
//        }
//        else
//        {
//            System.out.println("Cliente no encontrado "+cliente1.getId());
//        }

        //Agregar Cliente
//        var nuevoCliente = new Cliente("Daniel", "Ortiz", 400);
//        var agregado = clienteDAO.agregarCliente(nuevoCliente);
//        if(agregado)
//        {
//            System.out.println("Se agrego cliente "+ nuevoCliente);
//        }
//        else{
//            System.out.println("no se pudo agregar el cliente "+nuevoCliente);
//        }
//             System.out.println("*** prueba listar clientes ***");
//       var clientes = clienteDAO.listarClientes();
//           clientes.forEach(System.out::println);

        //Modificar Cliente
//        var modificarCliente = new Cliente(4, "Carlos Daniel", "Ortiz", 400);
//        var modificado = clienteDAO.modificarCliente(modificarCliente);
//        if(modificado)
//        {
//            System.out.println("Cliente modificado " + modificarCliente);
//
//        }
//        else {
//            System.out.println("No se pudo modificar el cliente "+modificarCliente.getNombre() +" "+ modificarCliente.getId());
//        }

        //Eliminar Cliente
        var clienteEliminar = new Cliente(4);
        var eliminado = clienteDAO.eliminarCliente(clienteEliminar);
        if (eliminado){
            System.out.println("Cliente eliminado "+clienteEliminar);
        }
        else {
            System.out.println("No se pudo eliminar el cliente");
        }


        //Listar los clientes
        System.out.println();
        System.out.println("Listando los clientes");
        var clientes = clienteDAO.listarClientes();
        clientes.forEach(System.out::println);
    }
}
