
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Carro;

	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.sql.Timestamp;
	import java.sql.Date;


 public class CarroDAO  extends DAO {	
		public CarroDAO() {
			super();
			conectar();
		}
		
		
		public void finalize() {
			close();
		}
		
		
		public boolean insert(Carro produto) {
			boolean status = false;
			try {
				String sql = "INSERT INTO produto (descricao, preco, quantidade, datafabricacao, datavalidade) "
			               + "VALUES ('" + produto.getDescricao() + "', "
			               + produto.getPreco() + ", " + produto.getQuantidade() + ", ?, ?);";
				PreparedStatement st = conexao.prepareStatement(sql);
			    st.setTimestamp(1, Timestamp.valueOf(produto.getDataFabricacao()));
				st.setDate(2, Date.valueOf(produto.getDataValidade()));
				st.executeUpdate(sql);
				st.close();
				status = true;
			} catch (SQLException u) {  
				throw new RuntimeException(u);
			}
			return status;
		}

		
		public Carro get(int id) {
			Carro produto = null;
			
			try {
				Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				String sql = "SELECT * FROM produto WHERE id="+id;
				ResultSet rs = st.executeQuery(sql);	
		        if(rs.next()){            
		        	 produto = new Carro(rs.getInt("id"), rs.getString("descricao"), (float)rs.getDouble("preco"), 
		                				   rs.getInt("quantidade"), 
		        			               rs.getTimestamp("datafabricacao").toLocalDateTime()
		        			               rs.getDate("datavalidade").toLocalDate());
		        }
		        st.close();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			return produto;
		}
		
		
		public List<Carro> get() {
			return get("");
		}

		
		public List<Carro> getOrderByID() {
			return get("id");		
		}
		
		
		public List<Carro> getOrderByDescricao() {
			return get("descricao");		
		}
		
		
		public List<Carro> getOrderByPreco() {
			return get("preco");		
		}
		
		
		private List<Carro> get(String orderBy) {
			List<Carro> produtos = new ArrayList<Carro>();
			
			try {
				Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				String sql = "SELECT * FROM produto" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
				ResultSet rs = st.executeQuery(sql);	           
		        while(rs.next()) {	            	
		        	Carro p = new Carro(rs.getInt("id"), rs.getString("descricao"), (float)rs.getDouble("preco"), 
		        			                rs.getInt("quantidade"),
		        			                rs.getTimestamp("datafabricacao").toLocalDateTime(),
		        			                rs.getDate("datavalidade").toLocalDate());
		            produtos.add(p);
		        }
		        st.close();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			return produtos;
		}
		
		
		public boolean update(Carro produto) {
			boolean status = false;
			try {  
				String sql = "UPDATE produto SET descricao = '" + produto.getDescricao() + "', "
						   + "preco = " + produto.getPreco() + ", " 
						   + "quantidade = " + produto.getQuantidade() + ","
						   + "datafabricacao = ?, " 
						   + "datavalidade = ? WHERE id = " + produto.getID();
				PreparedStatement st = conexao.prepareStatement(sql);
			    st.setTimestamp(1, Timestamp.valueOf(produto.getDataFabricacao()));
				st.setDate(2, Date.valueOf(produto.getDataValidade()));
				st.executeUpdate(sql);
				st.close();
				status = true;
			} catch (SQLException u) {  
				throw new RuntimeException(u);
			}
			return status;
		}
		
		
		public boolean delete(int id) {
			boolean status = false;
			try {  
				Statement st = conexao.createStatement();
				st.executeUpdate("DELETE FROM produto WHERE id = " + id);
				System.out.println(sql);
				st.executeUpdate(sql);
				st.close();
				status = true;
			} catch (SQLException u) {  
				throw new RuntimeException(u);
			}
			return status;
		}
	}