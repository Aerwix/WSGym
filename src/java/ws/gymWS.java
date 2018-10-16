package ws;

import POJOS.Mensaje;
import POJOS.Progreso;
import POJOS.clientes;
import POJOS.telefono;

import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

@Path("gym")
public class gymWS {

    @Context
    private UriInfo context;

    public gymWS() {
    }
    
    @Path("test")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String HolaMundo(){
        return "Hola mundo";
    }
    
    @Path("getAllClientes")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<clientes> getAll(){
        List<clientes> resultado = null;
        SqlSession conn = MyBatisUtil.getSession();
        if(conn != null){
            try {
                resultado = conn.selectList("clientes.getAllClientes");
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                conn.close();
            }           
        }else{
            System.out.println("Error de Conexion");
        }      
        return resultado;
    }
    
    @Path("getById/{idCliente}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public clientes getById(@PathParam("idCliente") Integer idCliente){
        clientes resultado = new clientes();
        SqlSession conn = MyBatisUtil.getSession();
        
        if (conn != null ){
            try {
                resultado = conn.selectOne("clientes.getById", idCliente);
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                conn.close();
            }
        }
        return resultado;
    }
    
    @Path("getByName/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public clientes getByName(@PathParam("name") String name){
        clientes resultado = new clientes();
        SqlSession conn = MyBatisUtil.getSession();
        
        if (conn != null ){
            try {
                resultado = conn.selectOne("clientes.getByName", name);
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                conn.close();
            }
        }
        return resultado;
    }
    
    
    
    // Registrar un nuevo cliente
    @Path("nuevoCliente")
    @POST 
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registroCliente(
            @FormParam("idCliente") Integer idCliente,
            @FormParam("nombre") String nombre,
            @FormParam("calle") String calle,
            @FormParam("colonia") String colonia,
            @FormParam("numero") String numero,
            @FormParam("correo") String correo,
            @FormParam("fechaNac") String fechaNac,
            @FormParam("estatus") String estatus,
            @FormParam("altura") String altura){
    
        Mensaje resultado = null;
        clientes cat = new clientes(idCliente, nombre,calle, colonia, numero, correo, fechaNac, estatus, altura);
        SqlSession conn = MyBatisUtil.getSession();
        if(conn !=null){
            try {
                int result = conn.insert("clientes.nuevoCliente" , cat);
                if(result ==1){
                    conn.commit();
                    resultado = new Mensaje(false, "Registrado Correctamente");
                }else{
                    conn.rollback();//deshace la operación
                    resultado = new Mensaje(false, "No se pudo registrar");
                }
                //todas las coeprtaciones que tienen que afectar a la base de datos necesitan un comit
                //confirma la operación
                
            } catch (Exception e) {
                e.printStackTrace();
                resultado = new Mensaje(false, e.getMessage());
            }finally{
                conn.close();
            }
        }
        
        return resultado;
    }
    
    //Actualizar 
    
    @Path("actualizarCliente")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje actualizaCatalogo(
            @FormParam("idCliente") Integer idCliente,
            @FormParam("nombre") String nombre,
            @FormParam("calle") String calle,
            @FormParam("colonia") String colonia,
            @FormParam("numero") String numero,
            @FormParam("correo") String correo,
            @FormParam("fechaNac") String fechaNac,
            @FormParam("estatus") String estatus,
            @FormParam("altura") String altura){
    
        Mensaje resultado = null;
        clientes cat = new clientes(idCliente, nombre,calle, colonia, numero, correo, fechaNac, estatus, altura);
        SqlSession conn = MyBatisUtil.getSession();
        if(conn !=null){
            try {
                int result = conn.update("clientes.actualizarCliente" , cat);
                if(result ==1){
                    conn.commit();
                    resultado = new Mensaje(false, "Cliente modificado correctamente.");
                }else{
                    conn.rollback();//deshace la operación
                    resultado = new Mensaje(false, "El cliente no se pudo modificar");
                }
                //todas las coeprtaciones que tienen que afectar a la base de datos necesitan un comit
                //confirma la operación
                
            } catch (Exception e) {
                e.printStackTrace();
                resultado = new Mensaje(false, e.getMessage());
            }finally{
                conn.close();
            }
        }
        
        return resultado;
    }
    
    //Eliminar un cliente
    @Path("eliminarCliente")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminaCliente(@FormParam("idCliente") Integer idCliente){
    
        Mensaje resultado = null;
        SqlSession conn = MyBatisUtil.getSession();
        if(conn !=null){
            try {
                int result = conn.update("clientes.eliminarCliente" ,idCliente);
                if(result ==1){
                    conn.commit();
                    resultado = new Mensaje(false, "Cliente eliminado correctamente.");
                }else{
                    conn.rollback();//deshace la operación
                    resultado = new Mensaje(false, "El cliente no se pudo eliminar");
                }
                //todas las coeprtaciones que tienen que afectar a la base de datos necesitan un comit
                //confirma la operación
                
            } catch (Exception e) {
                e.printStackTrace();
                resultado = new Mensaje(false, e.getMessage());
            }finally{
                conn.close();
            }
        }
        return resultado;
    }
    
    
    // Servicios de Telefono //
    
    // Conseguir numeros registrados en el idCliente
    @Path("getTelefonoById/{idCliente}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public telefono getPhoneById(@PathParam("idCliente") Integer idCliente){
        telefono resultado = new telefono();
        SqlSession conn = MyBatisUtil.getSession();
        
        if (conn != null ){
            try {
                resultado = conn.selectOne("telefono.getById", idCliente);
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                conn.close();
            }
        }
        return resultado;
    }
    
    // Registrar un nuevo cliente
    @Path("nuevoTelefono")
    @POST 
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje nuevoTelefono(
            @FormParam("idTelefono") Integer idTelefono,
            @FormParam("telefonoCasa") String telefonoCasa,
            @FormParam("telefonoCel") String telefonoCel,
            @FormParam("idCliente") Integer idCliente){
    
        Mensaje resultado = null;
        telefono cat = new telefono(idTelefono, telefonoCasa,telefonoCel, idCliente);
        SqlSession conn = MyBatisUtil.getSession();
        if(conn !=null){
            try {
                int result = conn.insert("telefono.nuevoTelefono" , cat);
                if(result ==1){
                    conn.commit();
                    resultado = new Mensaje(false, "Telefono agreado correctamente.");
                }else{
                    conn.rollback();//deshace la operación
                    resultado = new Mensaje(false, "El telefono no se pudo registrar.");
                }
                //todas las coeprtaciones que tienen que afectar a la base de datos necesitan un comit
                //confirma la operación
                
            } catch (Exception e) {
                e.printStackTrace();
                resultado = new Mensaje(false, e.getMessage());
            }finally{
                conn.close();
            }
        }
        
        return resultado;
    }
    
    //Actualizar 
    @Path("actualizarTelefono")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje actualizaTelefono(
            @FormParam("idTelefono") Integer idTelefono,
            @FormParam("telefonoCasa") String telefonoCasa,
            @FormParam("telefonoCel") String telefonoCel,
            @FormParam("idCliente") Integer idCliente){
    
        Mensaje resultado = null;
        telefono cat = new telefono(idTelefono, telefonoCasa, telefonoCel, idCliente);
        SqlSession conn = MyBatisUtil.getSession();
        if(conn !=null){
            try {
                int result = conn.update("telefono.actualizarTelefono" , cat);
                if(result ==1){
                    conn.commit();
                    resultado = new Mensaje(false, "Cliente modificado correctamente.");
                }else{
                    conn.rollback();//deshace la operación
                    resultado = new Mensaje(false, "El cliente no se pudo modificar");
                }
                //todas las coeprtaciones que tienen que afectar a la base de datos necesitan un comit
                //confirma la operación
                
            } catch (Exception e) {
                e.printStackTrace();
                resultado = new Mensaje(false, e.getMessage());
            }finally{
                conn.close();
            }
        }
        
        return resultado;
    }
    
    // Servicio de Progreso
    @Path("getAllProgresoById/{idCliente}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Progreso> getAllProgressById(@PathParam("idCliente") Integer idCliente){
        //List<clientes>
        List<Progreso> resultado = null;
        SqlSession conn = MyBatisUtil.getSession();
        
        if (conn != null ){
            try {
                resultado = conn.selectList("progreso.getAllByIdCliente", idCliente);
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                conn.close();
            }
        }
        return resultado;
    }
    
    // Registrar un nuevo cliente
    @Path("nuevoProgreso")
    @POST 
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje nuevoProgreso(
            @FormParam("idProgreso") Integer idProgreso,
            @FormParam("Peso") String Peso,
            @FormParam("Talla") String Talla,
            @FormParam("mc") String mc,
            @FormParam("fechaRegistro") String fechaRegistro,
            @FormParam("idCliente") Integer idCliente){
    
        Mensaje resultado = null;
        Progreso cat = new Progreso(idProgreso, Peso, Talla, mc, fechaRegistro, idCliente);
        SqlSession conn = MyBatisUtil.getSession();
        if(conn !=null){
            try {
                int result = conn.insert("progreso.nuevoProgreso" , cat);
                if(result ==1){
                    conn.commit();
                    resultado = new Mensaje(false, "Progreso agreado correctamente.");
                }else{
                    conn.rollback();//deshace la operación
                    resultado = new Mensaje(false, "El progreso no se pudo registrar.");
                }
                //todas las coeprtaciones que tienen que afectar a la base de datos necesitan un comit
                //confirma la operación
                
            } catch (Exception e) {
                e.printStackTrace();
                resultado = new Mensaje(false, e.getMessage());
            }finally{
                conn.close();
            }
        }
        return resultado;
    }
    
    //Actualizar un progreso // Se necesita idProgreso + idCliente
    @Path("actualizarProgreso") 
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje actualizarProgreso(
            @FormParam("idProgreso") Integer idProgreso,
            @FormParam("Peso") String Peso,
            @FormParam("Talla") String Talla,
            @FormParam("mc") String mc,
            @FormParam("fechaRegistro") String fechaRegistro,
            @FormParam("idCliente") Integer idCliente){
    
        Mensaje resultado = null;
        Progreso cat = new Progreso(idProgreso, Peso, Talla, mc, fechaRegistro, idCliente);
        SqlSession conn = MyBatisUtil.getSession();
        if(conn !=null){
            try {
                int result = conn.update("progreso.actualizarProgreso" , cat);
                if(result ==1){
                    conn.commit();
                    resultado = new Mensaje(false, "Progreso modificado correctamente.");
                }else{
                    conn.rollback();//deshace la operación
                    resultado = new Mensaje(false, "El progreso no se pudo modificar");
                }
                //todas las coeprtaciones que tienen que afectar a la base de datos necesitan un comit
                //confirma la operación
                
            } catch (Exception e) {
                e.printStackTrace();
                resultado = new Mensaje(false, e.getMessage());
            }finally{
                conn.close();
            }
        }
        
        return resultado;
    }
   
    
}
 // FALTANTES
// CLIENTES: CRUD
// TELEFONO: 
// PROGRESO: 