package view;

import controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DeleteView extends JPanel {
    private DefaultTableModel dm;
    private JTable table;
    private ButtonEditor btDelete;
    private ButtonRenderer brDelte;
    private JButton jbExit;
    public DeleteView(){
        JFrame frame = new JFrame("JButtonTable Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        dm = new DefaultTableModel();
        dm.setDataVector(new Object[][]{
                        {"delete", "Mary", 1,1,1, "28/6/98", "28/6/98"},
                        {"delete", "Lhucas", 1,1,1, "28/6/98", "28/6/98"},
                        {"delete", "Kathya", 1,1,1, "28/6/98", "28/6/98"},
                        {"delete", "Marcus", 1,1,1, "28/6/98", "28/6/98"},
                        {"delete", "Angela", 1,1,1, "28/6/98", "28/6/98"}},
                new Object[]{"", "Nikname", "Points x2", "Points x4", "Tournament", "Register", "Modification"});

        table = new JTable(dm){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return colIndex == 0 ;
            }
        };

        brDelte = new ButtonRenderer();
        table.getColumn("").setCellRenderer(brDelte);
        btDelete = new ButtonEditor(new JCheckBox(),this);

        table.getColumn("").setCellEditor( btDelete );

        // for (iint)

        JScrollPane scroll = new JScrollPane(table);
        this.setLayout(new BorderLayout());
        jbExit = new JButton("Exit");


        //table.setPreferredScrollableViewportSize(10,10);//thanks mKorbel +1 http://stackoverflow.com/questions/10551995/how-to-set-jscrollpane-layout-to-be-the-same-as-jtable

        table.getColumnModel().getColumn(0).setPreferredWidth(100);//so buttons will fit and not be shown butto..


        this.add(scroll);
        this.add(jbExit,BorderLayout.PAGE_END);

    }

    public ButtonRenderer getBrDelte() {
        return brDelte;
    }

    public void setBrDelte(ButtonRenderer brDelte) {
        this.brDelte = brDelte;
    }

    public ButtonEditor getBtDelete() {
        return btDelete;
    }

    public void setBtDelete(ButtonEditor btDelete) {
        this.btDelete = btDelete;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public DefaultTableModel getDm() {
        return dm;
    }

    public void setDm(DefaultTableModel dm) {
        this.dm = dm;
    }

    public void registerControllerDeleteView(Controller controller){
        btDelete.getButton().addActionListener(controller);
        btDelete.getButton().setActionCommand("Delete");
        jbExit.addActionListener(controller);
        jbExit.setActionCommand("ExitDelete");

    }

    public void deleteUser() {
        if (table.getSelectedRow() != -1) {
            // remove selected row from the model
            dm.removeRow(table.getSelectedRow());
        }
    }
}
