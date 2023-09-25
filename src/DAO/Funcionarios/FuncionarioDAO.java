package DAO.Funcionarios;

import DAO.ConexaoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import DTO.FuncionarioDTO;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class FuncionarioDAO {

    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<FuncionarioDTO> lista = new ArrayList<>();

    public void cadastrarFuncionario(FuncionarioDTO objfuncionariodto) {

        String sql = "INSERT INTO funcionarios (nome_funcionario, endereco_funcionario) VALUES ( ?, ? )";

        conn = new ConexaoDAO().conectaBD();

        try {

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objfuncionariodto.getNome_funcionario());
            pstm.setString(2, objfuncionariodto.getEndereco_funcionario());

            pstm.execute();
            pstm.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO" + e.getMessage());
        }

    };

    

    public ArrayList<FuncionarioDTO> ListarFuncionario() {

        String sql = "SELECT * FROM funcionarios ";
        conn = new ConexaoDAO().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {

                FuncionarioDTO objfuncionarioDTO = new FuncionarioDTO();
                objfuncionarioDTO.setId_funcionario(rs.getInt("id_funcionario"));
                objfuncionarioDTO.setNome_funcionario(rs.getString("nome_funcionario"));
                objfuncionarioDTO.setEndereco_funcionario(rs.getString("endereco_funcionario"));

                lista.add(objfuncionarioDTO);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO Pesquisar" + e.getMessage());
        }
        return lista;
    };
    
    
    public void editarFuncionario(FuncionarioDTO objfuncionariodto){
        
        String sql = "UPDATE funcionarios SET nome_funcionario = ?, endereco_funcionario = ? WHERE id_funcionario = ? ";
        
         conn = new ConexaoDAO().conectaBD();

        try {

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objfuncionariodto.getNome_funcionario());
            pstm.setString(2, objfuncionariodto.getEndereco_funcionario());
            pstm.setInt(3, objfuncionariodto.getId_funcionario());

            pstm.execute();
            pstm.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO Editar" + e.getMessage());
        }
    }
    
    public void excluirFuncionario(FuncionarioDTO objfuncionariodto){
        
        String sql = "DELETE FROM funcionarios WHERE id_funcionario = ?";
        
        conn = new ConexaoDAO().conectaBD();

        try {

            pstm = conn.prepareStatement(sql);
          
            pstm.setInt(1, objfuncionariodto.getId_funcionario());

            pstm.execute();
            pstm.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO Excluir" + e.getMessage());
        }
        
        
    }
    
    
    
};


