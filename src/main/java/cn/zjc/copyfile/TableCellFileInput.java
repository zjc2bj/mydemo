package cn.zjc.copyfile;

import javax.swing.JFileChooser;

public class TableCellFileInput extends javax.swing.JPanel {
    
    //用于过滤的文件后缀
    private String extensions[];
    //...按钮
    private javax.swing.JButton fileButton;
    //文件路径输入框
    private javax.swing.JTextField valueField;
    //文件对话框
    private static final String DESCRIPTION = "WTK目录";

    private java.awt.Component self;
    public TableCellFileInput(String fileExtensions[]) {
        extensions = fileExtensions;
        self = this;
        initComponents();
    }

    private void initComponents() {
        valueField = new javax.swing.JTextField();
        fileButton = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        valueField.setText("jTextField1");
        add(valueField, java.awt.BorderLayout.CENTER);

        fileButton.setText("...");
        fileButton.setMargin(new java.awt.Insets(0, 2, 0, 2));
        fileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileButtonActionPerformed(evt);
            }
        });

        add(fileButton, java.awt.BorderLayout.EAST);
    }
    
    //文件路径选择
    private void fileButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String filePath = valueField.getText();
        if (filePath != null && filePath.length() > 0) {
            filePath = new java.io.File(filePath).getParent();
        }
        javax.swing.JFileChooser chooser = new javax.swing.JFileChooser(
                filePath);
        if(extensions!=null){
            chooser.setFileFilter(new MyFileFilter());
        }else{
            //只是用于目录选择
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        }
        
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
            valueField.setText(chooser.getSelectedFile().getAbsolutePath());
        }
    }

    //内部FileFilter
    class MyFileFilter extends javax.swing.filechooser.FileFilter {
        public boolean accept(java.io.File f) {
            if (f.isDirectory())
                return true;
            String fName = f.getName();
            if (extensions != null) {
                for (int i = 0; i < extensions.length; i++) {
                    if (fName.endsWith(extensions[i]))
                        return true;
                }
            }
            return false;
        }

        public String getDescription() {
            return DESCRIPTION;
        }
    }
    
    //内部定制的TableCellEditor
    class MyCustomCellEditor extends javax.swing.AbstractCellEditor implements
            javax.swing.table.TableCellEditor {
        public Object getCellEditorValue() {
            return valueField.getText();
        }

        public java.awt.Component getTableCellEditorComponent(
                javax.swing.JTable table, Object value, boolean isSelected,
                int row, int column) {
            if (value == null)
                valueField.setText("");
            else
                valueField.setText(value.toString());
            return self;
        }
    }
    
    //对外的接口方法
    public void setColumn(javax.swing.table.TableColumn column) {
        column.setCellEditor(null);
        column.setCellEditor(new MyCustomCellEditor());
    }
}

