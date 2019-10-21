package vipera;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

public class FXMLDocumentController implements Initializable 
{
    @FXML
    private Label info, src;
    
    private static final int BUFFER = 8192;
    
    @FXML
    private void zip()
    {
        FileChooser fileChooser = new FileChooser();
        List<File> fileList = fileChooser.showOpenMultipleDialog(null);
        String zipFile = fileList.get(0).getParent() + "/compressed.zip";
 
        try 
        {
            FileOutputStream fOutS = new FileOutputStream(zipFile);
            ZipOutputStream zOutS = new ZipOutputStream(fOutS);

            for (File f : fileList) 
            {
                String source = f.getPath();
                String entryName = f.getName();
                ZipEntry zipEntry = new ZipEntry(entryName);
                zOutS.putNextEntry(zipEntry);
                byte[] buffer = new byte[BUFFER];
                
                try (FileInputStream fInS = new FileInputStream(source)) 
                {
                    int numberOfByte;

                    while ((numberOfByte = fInS.read(buffer, 0, BUFFER))!= -1) 
                    {
                        zOutS.write(buffer, 0, numberOfByte);
                    }
                }
            }
            zOutS.close();
            info.setText("Ready! Check it here: ");
            src.setText(zipFile);
        } catch (IOException ex) {
            System.out.println("Error");
        }
    }
    
    @FXML
    private void close()
    {
      System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }    
    
}
