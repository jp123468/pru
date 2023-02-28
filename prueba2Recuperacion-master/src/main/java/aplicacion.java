import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.util.HashMap;

public class aplicacion {
    PreparedStatement ps;
    private JPanel panel1;
    private JComboBox Comboboxdia;
    private JComboBox Comboboxmes;
    private JComboBox ComboboxAnio;
    private JButton JButtonGuardar;
    private JTextField fecha_ingresada;


    private static int mesInt;

    private HashMap<String, Integer> mesesMap;

    public aplicacion() {

        Connection con = null;
        try{
            con = getConection();
            String query = "SELECT * FROM dias";
            Statement s = con.createStatement();
            ResultSet rs =s.executeQuery(query);
            while(rs.next()){
                Comboboxdia.addItem(rs.getInt(2));
            }
        }catch (HeadlessException | SQLException e){
            System.out.println(e);
        }

        try{
            con = getConection();
            String query = "SELECT * FROM mes";
            Statement s = con.createStatement();
            ResultSet rs =s.executeQuery(query);
            while(rs.next()){
                Comboboxmes.addItem(rs.getString(2));
            }
        }catch (HeadlessException | SQLException e){
            System.out.println(e);
        }

        try{
            con = getConection();
            String query = "SELECT * FROM anio";
            Statement s = con.createStatement();
            ResultSet rs =s.executeQuery(query);
            while(rs.next()){
                ComboboxAnio.addItem(rs.getString(2));            }
        }catch (HeadlessException | SQLException e){
            System.out.println(e);
        }
        JButtonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int diaIng = Integer.parseInt(Comboboxdia.getSelectedItem().toString());
                String mes = Comboboxmes.getSelectedItem().toString();
                int mesInt = mesesMap.get(mes);
                int anioIng = Integer.parseInt(ComboboxAnio.getSelectedItem().toString());
                String mesFinal = String.format("%02d", mesInt);
                String fechaString = anioIng + "-" + mesFinal + "-" + String.format("%02d", diaIng);
                Date fecha = Date.valueOf(fechaString);
                fecha_ingresada.setText(fechaString);
                Connection con;
                try{
                    con = getConection();
                    ps = con.prepareStatement("INSERT INTO fecha (fecha) VALUES (?)");
                    ps.setDate(1, fecha);
                    System.out.println(ps);

                    int cont = ps.executeUpdate();
                    if(cont > 0){
                        JOptionPane.showMessageDialog(null, "Fecha guaradada correctamente");
                    }else{
                        JOptionPane.showMessageDialog(null, "Error no se pudo guardar la fecha");
                    }
                    con.close();
                }catch (SQLException s){
                    System.out.println("Error " + s.getMessage());
                }
            }
        });
        mesesMap = new HashMap< String,Integer>();
        mesesMap.put("Enero",1);
        mesesMap.put("Febrero",2);
        mesesMap.put( "Marzo", 3);
        mesesMap.put( "Abril",4);
        mesesMap.put( "Mayo",5);
        mesesMap.put( "Junio",6);
        mesesMap.put("Julio",7);
        mesesMap.put("Agosto",8);
        mesesMap.put("Septiembre",9);
        mesesMap.put("Octubre",10);
        mesesMap.put( "Noviembre",11);
        mesesMap.put( "Diciembre",12);
// HashMap se utiliza para obtener el número de mes correspondiente a la selección del usuario.

        Comboboxmes.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Connection con;
                String query = null;
                try{
                    String mesStr = Comboboxmes.getSelectedItem().toString();
                    mesInt = mesesMap.get(mesStr);
                    Comboboxdia.removeAllItems();
                    con = getConection();

                    if (mesInt == 4 || mesInt == 6 || mesInt == 9 || mesInt == 11) {
                        query = "SELECT * FROM dias LIMIT 30";
                        Comboboxdia.removeAllItems();
                    }  else if(mesInt == 1 || mesInt ==3 || mesInt == 5 || mesInt == 7
                            || mesInt==8 || mesInt == 10|| mesInt == 12) {
                        query = "SELECT * FROM dias LIMIT 31";
                        Comboboxdia.removeAllItems();
                    }
                    else if ((Comboboxmes.getSelectedItem().equals("Febrero") && (Integer.parseInt(ComboboxAnio.getSelectedItem().toString()) % 4 == 0))){
                        mesInt = 2;
                        query = "SELECT * FROM dias LIMIT 29";
                        Comboboxdia.removeAllItems();
                    }
                    else {
                        mesInt = 2;
                        query = "SELECT * FROM dias LIMIT 28"; //el limite de dias
                        Comboboxdia.removeAllItems();
                    }
                    Statement s = con.createStatement();
                    ResultSet rs = s.executeQuery(query);
                    while(rs.next()){
                        Comboboxdia.addItem(rs.getInt(2));
                    }
                } catch (HeadlessException | SQLException s){
                    System.out.println(s);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Recuperacion");
        frame.setContentPane(new aplicacion().panel1);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static Connection getConection(){
        Connection con = null;
        String nombreBD = "recuperacion";
        String url = "jdbc:mysql://localhost:3306/" + nombreBD;
        String user = "root";
        String password = "Ligacampeon1";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,user,password);
            System.out.println("Conexion Exitosa");
        }catch (ClassNotFoundException | SQLException e){
            System.out.println(e);
            System.out.println("Errror en la conexion..");
        }
        return con;
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
