/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;




import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author Jessele Durán
 */
public class PDFGenerator 
{
    public static String createPDFFromHashMapList(JFrame parent,List<LinkedHashMap<String, Object>> list, String title)
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Files","pdf"));
        fileChooser.setDialogTitle("Especifica una carpeta");   
        
        int userSelection = fileChooser.showSaveDialog(parent);

        if (userSelection == JFileChooser.APPROVE_OPTION) 
        {
            File fileToSave = fileChooser.getSelectedFile();
            String path = fileToSave.getAbsolutePath();
            if(!path.contains(".pdf"))
                path+= ".pdf";
            boolean saved = createPDFFromHashMapList(list, path, title);
            if(!saved)
            {
                JOptionPane.showMessageDialog(parent, "El archivo no pudo ser guardado", "Error", JOptionPane.ERROR_MESSAGE);
                return "";
            }
            return path;
        }
        return "";
    }
    
    public static boolean createPDFFromHashMapList(List<LinkedHashMap<String, Object>> list, String out, String title)
    {
        Document document = new Document();
        try
        {
            PdfWriter.getInstance(document, new FileOutputStream(out));
            document.open();
            LinkedHashMap<String, Object> modelo = list.get(0);
            int size = modelo.size();
            // Este codigo genera una tabla de 3 columnas
            PdfPTable table = new PdfPTable(size);                
            
            // addCell() agrega una celda a la tabla, el cambio de fila
            // ocurre automaticamente al llenar la fila
            for(String key : modelo.keySet())
            {
                table.addCell(key);
            }
              
            for(LinkedHashMap<String, Object> hash : list)
            {
                for(Map.Entry<String, Object> o : hash.entrySet())
                {
                    table.addCell(o.getValue().toString());
                }
            }
            document.addTitle("Reporte de "+title);
            
            document.add(table);
            document.close();
            return true;
        }catch(Exception e)
        {
            System.err.println("Ocurrio un error al crear el archivo");
            System.exit(-1);
        }
        return false;
    }
    
    
}
