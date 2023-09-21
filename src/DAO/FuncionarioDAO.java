package DAO;

import DTO.FuncionarioDTO;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FuncionarioDAO {

    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<FuncionarioDTO> lista = new ArrayList<>();

    public void cadastrarFuncionario(FuncionarioDTO objfuncionariodto) {

        String sql = "insert into funcionarios (nome, endereco, telefone) values (?,?,?)";

        conn = new ConexaoDAO().conectaBD();

        try {

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objfuncionariodto.getNome());
            pstm.setString(2, objfuncionariodto.getEndereco());
            pstm.setString(3, objfuncionariodto.getTelefone());

            pstm.execute();
            pstm.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO Cadastrar: " + e);
        }

    }

    public ArrayList<FuncionarioDTO> PesquisarFuncionario() {

        String sql = "SELECT * FROM funcionarios ";
        conn = new ConexaoDAO().conectaBD();

        try {

            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                FuncionarioDTO objfuncionarioDTO = new FuncionarioDTO();
                objfuncionarioDTO.setId(rs.getInt("id"));
                objfuncionarioDTO.setNome(rs.getString("nome"));
                objfuncionarioDTO.setEndereco(rs.getString("endereco"));
                objfuncionarioDTO.setTelefone(rs.getString("telefone"));

                lista.add(objfuncionarioDTO);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO Pesquisar: " + e);
        }

        return lista;
    }
    
    public void alterarFuncionario(FuncionarioDTO objfuncionariodto){
       String sql = "UPDATE funcionarios set nome = ?, endereco = ?, telefone = ? where id = ?";
       
       conn = new ConexaoDAO().conectaBD();

        try {

            pstm = conn.prepareStatement(sql);
            
            pstm.setString(1, objfuncionariodto.getNome());
            pstm.setString(2, objfuncionariodto.getEndereco());
            pstm.setString(3, objfuncionariodto.getTelefone());
            pstm.setInt(4, objfuncionariodto.getId());
            
            pstm.execute();
            pstm.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO Alterar: " + e);
        }
    }
    
     public void excluir(FuncionarioDTO objfuncionariodto){
         
         String sql = "DELETE FROM funcionarios where id = ?";
         
         conn = new ConexaoDAO().conectaBD();

        try {

            pstm = conn.prepareStatement(sql);
            
            
            pstm.setInt(1, objfuncionariodto.getId());
            
            pstm.execute();
            pstm.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO Exclui: " + e);
        }
         
         
     }
     
     
     public ResultSet listarCargo(){
         conn = new ConexaoDAO().conectaBD();
         String sql = "SELECT * FROM cargos ORDER BY descricao;";
         
         try {
             pstm = conn.prepareStatement(sql);
             return pstm.executeQuery();
             
         } catch(SQLException e){
             JOptionPane.showMessageDialog(null, "ListarCargo FuncionarioDAO" + e.getMessage());
             return null;
         }      
         
     }

}
