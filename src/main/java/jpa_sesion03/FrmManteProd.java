package jpa_sesion03;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Categoria;
import model.Producto;
import model.Proveedor; 

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List; 
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class FrmManteProd extends JFrame {
 
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	private JTextArea txtSalida;
	private JTextField txtCódigo;
	JComboBox<String> cboCategorias;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	JComboBox<String> cboProveedor;
	private JTextField txtPrecio;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteProd frame = new FrmManteProd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmManteProd() {
		setTitle("Mantenimiento de Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnNewButton.setBounds(324, 11, 89, 23);
		contentPane.add(btnNewButton);
		 
		JButton btnBuscar = new JButton("Buscar"); 
		btnBuscar.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				buscarProducto();  
			} 
		}); 
		btnBuscar.setBounds(324, 40, 89, 23); 
		contentPane.add(btnBuscar);
	 
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 414, 143);
		contentPane.add(scrollPane);
		
		txtSalida = new JTextArea();
		scrollPane.setViewportView(txtSalida);
		
		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(177, 322, 89, 23);
		contentPane.add(btnListado);
		
		txtCódigo = new JTextField();
		txtCódigo.setBounds(122, 11, 86, 20);
		contentPane.add(txtCódigo);
		txtCódigo.setColumns(10);
		
		JLabel lblCodigo = new JLabel("Id. Producto :");
		lblCodigo.setBounds(10, 14, 102, 14);
		contentPane.add(lblCodigo);
		
		cboCategorias = new JComboBox<String>();
		cboCategorias.setBounds(122, 70, 86, 22);
		contentPane.add(cboCategorias);
		
		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 74, 102, 14);
		contentPane.add(lblCategora);
		
		JLabel lblNomProducto = new JLabel("Nom. Producto :");
		lblNomProducto.setBounds(10, 45, 102, 14);
		contentPane.add(lblNomProducto);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(122, 42, 144, 20);
		contentPane.add(txtDescripcion);
		
		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(10, 106, 102, 14);
		contentPane.add(lblStock);
		
		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(122, 103, 77, 20);
		contentPane.add(txtStock);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(10, 134, 102, 14);
		contentPane.add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(122, 131, 77, 20);
		contentPane.add(txtPrecio);
		
		JLabel lblProveedor = new JLabel("Proveedor:");
		lblProveedor.setBounds(220, 106, 102, 14);
		contentPane.add(lblProveedor);
		
		cboProveedor = new JComboBox<String>();
		cboProveedor.setBounds(322, 106, 86, 22);
		contentPane.add(cboProveedor);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarProducto();
			}
		});
		btnModificar.setBounds(328, 73, 85, 21);
		contentPane.add(btnModificar);
		
		llenaCombo();
		setCorrelativo();
	}

	void llenaCombo() {

		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql"); 
		EntityManager em = fabrica.createEntityManager(); 
		 
		TypedQuery<Categoria> cntCategoria =  em.createQuery("SELECT c FROM Categoria c", Categoria.class);
		
		List<Categoria> lstCategoria= cntCategoria.getResultList();
		cboCategorias.addItem("--SELECCCIONE--");
		for (Categoria c : lstCategoria) {
			cboCategorias.addItem(c.getDescripcion());
		}

		TypedQuery<Proveedor> cntProveedor =  em.createQuery("SELECT p FROM Proveedor p", Proveedor.class);		
		List<Proveedor> lstProveedor= cntProveedor.getResultList();
		cboProveedor.addItem("--SELECCCIONE--");
		for (Proveedor p : lstProveedor) {
			cboProveedor.addItem(p.getNombre_rs());
		} 
		
		em.close();
	}
	
	void listado() { 
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql"); 
		EntityManager em = fabrica.createEntityManager(); 
		TypedQuery<Producto> cntProducto =  em.createQuery("SELECT c FROM Producto c", Producto.class); 
		List<Producto> lstProductos= cntProducto.getResultList();
		txtSalida.setText(""); 
		txtSalida.append("******************************************\n"); 
		for (Producto p : lstProductos) { 
			txtSalida.append("Id Prod.......: " + p.getId_prod() + "\n"); 
			txtSalida.append("Descripción...: " + p.getDes_prod() + "\n"); 
			txtSalida.append("Stock.........: " + p.getStk_prod() + "\n"); 
			txtSalida.append("Precio........: " + p.getPre_prod() + "\n"); 
			txtSalida.append("Categoria.....: " + p.getCategoria().getDescripcion() + "\n"); 
			txtSalida.append("Estado........: " + p.getEst_prod() + "\n"); 
			txtSalida.append("Proveedor.....: " + p.getProveedor().getNombre_rs() + "\n"); 
			txtSalida.append("******************************************\n"); 
		} 
		em.close();
	}
	
	void registrar() {
		try { 
		Producto prod = new Producto();
		prod.setId_prod(txtCódigo.getText());
		prod.setDes_prod(txtDescripcion.getText());
		prod.setStk_prod(Integer.parseInt(txtStock.getText()));
		prod.getCategoria().setIdcategoria(cboCategorias.getSelectedIndex());
		prod.setEst_prod(1);
		prod.getProveedor().setIdprovedor(cboProveedor.getSelectedIndex());
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql"); 
		EntityManager em = fabrica.createEntityManager();  
		
		em.getTransaction().begin(); 
		em.merge(prod);
		em.getTransaction().commit(); 
		em.close(); 
		setCorrelativo();
		}catch (Exception e) {
			txtCódigo.setText("P0001");
		}
	}
	
	void setCorrelativo() {
		try {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql"); 
		EntityManager em = fabrica.createEntityManager(); 
		Query  query =  em.createQuery("SELECT max(c.id_prod) FROM Producto c");  
		String result = (String) query.getResultList().get(0); 
		result = "P"+String.format("%04d",(Integer.parseInt(result.replaceAll("[^0-9]", ""))+1)); 
		txtCódigo.setText(result);
		em.close();
		}catch (Exception e) {
			System.err.println(e);
			txtCódigo.setText("P0001");
		}
	}
	
	void buscarProducto() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql"); 
		EntityManager em = fabrica.createEntityManager();
 
		// proceso --> búsquedas / consultas / listados 
		
		// select .... from .... where ....   

		// devuelve un Objeto si existe el ID, sino devuelve null
		/*
		if (u == null) 
			System.err.println("ID no existe"); 
		else 
			System.out.println(u); */
		em.close();
	}
	
	void ModificarProducto() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql"); 
		EntityManager em = fabrica.createEntityManager();
 
		// proceso --> búsquedas / consultas / listados 
		
		// select .... from .... where ....   

		// devuelve un Objeto si existe el ID, sino devuelve null
		/*
		if (u == null) 
			System.err.println("ID no existe"); 
		else 
			System.out.println(u); */
		em.close();
	}
} 
