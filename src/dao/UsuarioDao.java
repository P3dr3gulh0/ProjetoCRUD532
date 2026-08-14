package dao;

import controller.ConnectionMySql;
import model.UsuarioModel;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class UsuarioDao {

    private Connection connection;

    public UsuarioDao(Connection connection) {
        this.connection = new ConnectionMySql().getConnection();
    }

    public void adicionar(UsuarioModel usuario) {
        String sql = "INSERT INTO usuario(nome,cpf,email,telefone,nascimento)VALUES(?,?,?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getCpf());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getTelefone());
            ps.setDate(5, usuario.getNascimento());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar! Classe DAO");
            throw new RuntimeException(e);
        }

    }

    public List<UsuarioModel> leitura() {
        connection = new ConnectionMySql().getConnection();

        PreparedStatement ps = null;

        ResultSet rs = null;

        List<UsuarioModel> usuarioArray = new ArrayList<>();

        try {
            ps = connection.prepareStatement(
                    "SELECT * FROM usuario");
            rs = ps.executeQuery();

            while (rs.next()) {
                UsuarioModel u = new UsuarioModel();

                u.setIdUsuario(rs.getInt("idUsuario"));
                u.setNome(rs.getString("nome"));
                u.setCpf(rs.getString("cpf"));
                u.setEmail(rs.getString("email"));
                u.setTelefone(rs.getString("telefone"));
                u.setNascimento(rs.getDate("nascimento"));

                usuarioArray.add(u);

            }
//            JOptionPane.showMessageDialog(null, "Lista DAO Funcionou");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lista DAO NÂO Funcionou");

            throw new RuntimeException(e);
        }

        return usuarioArray;

    }

    public void deletar(UsuarioModel usuario) {
        String sql = "DELETE FROM usuario WHERE idUsuario = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, usuario.getIdUsuario());

            ps.execute();

            JOptionPane.showMessageDialog(null, "Exclusão DAO concluída!");

            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Exclusão DAO não concluida!");

            throw new RuntimeException(e);
        }
    }

    public void editar(UsuarioModel usuario) {
        String sql = "UPDATE usuario SET nome = ?, cpf = ?, email = ?, telefone = ?, nascimento = ? WHERE idUsuario = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getCpf());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getTelefone());
            ps.setDate(5, usuario.getNascimento());
            ps.setInt(6, usuario.getIdUsuario());
            
            
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Edição do Usuario concluida");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar! Classe DAO");
            throw new RuntimeException(e);
        }

    }
}
