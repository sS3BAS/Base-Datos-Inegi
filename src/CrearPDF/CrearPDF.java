package CrearPDF;

/**
 *
 * @author s_SEBAS
 */
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Image;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import vistainegi.conexion;

public class CrearPDF {

    private String correo;
    private String usuario;
    private String contrasena;
    private String rol;
    
    public CrearPDF(String correo) {
        this.correo = correo;
        setUserData(correo);
    }

    private void setUserData(String correo) {
        try {
            Connection connection = new conexion().getConnection();
            String query = "SELECT usuario, correo, contrasena, rol FROM usuarios WHERE correo = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, correo);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                this.correo = resultSet.getString("correo");
                this.usuario = resultSet.getString("usuario");
                this.contrasena = resultSet.getString("contrasena");
                this.rol = resultSet.getString("rol");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void prueba() {
        // step 1: creation of a document-object        
        Document document = new Document(PageSize.LETTER);

        try {
            // step 2: creation of the writer
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Bienvenida.pdf"));

            // step 3: we open the document
            document.open();

            // step 4: we grab the ContentByte and do some stuff with it
            PdfContentByte cb = writer.getDirectContent();

            // Añadir imágenes de encabezado
            Image logoINEGI = Image.getInstance("C:\\Users\\marle\\OneDrive\\Documentos\\NetBeansProjects\\VistaInegi\\VistaInegi\\src\\imagenes\\INEGI.png");
            logoINEGI.setAbsolutePosition(50, PageSize.LETTER.getHeight() - 100);
            logoINEGI.scaleAbsolute(50, 50);
            document.add(logoINEGI);

            Image escudoMexico = Image.getInstance("C:\\Users\\marle\\OneDrive\\Documentos\\NetBeansProjects\\VistaInegi\\VistaInegi\\src\\imagenes\\Escudo.png");
            escudoMexico.setAbsolutePosition(PageSize.LETTER.getWidth() - 100, PageSize.LETTER.getHeight() - 100);
            escudoMexico.scaleAbsolute(50, 50);
            document.add(escudoMexico);

            // Añadir título de encabezado
            Paragraph titulo = new Paragraph("Instituto Nacional de Estadística y Geografía",
                    new Font(Font.HELVETICA, 18, Font.BOLD));
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);

            // Espacio después del título
            document.add(new Paragraph("\n\n"));

            // Contenido del cuerpo del documento
            Paragraph mensaje = new Paragraph("\n\nTe has registrado correctamente este es tu usuario, correo y tu tipo de usuario.\n",
                    new Font(Font.HELVETICA, 14, Font.NORMAL));
            mensaje.setAlignment(Element.ALIGN_LEFT);
            document.add(mensaje);

            // Añadir más detalles al cuerpo para mejorar la presentación
            Paragraph detalles = new Paragraph("\n\nCorreo: "+correo+"\nUsuario: "+usuario+"\n"+"\nRol: "+rol+"\n",
                    new Font(Font.HELVETICA, 14, Font.BOLD));
            detalles.setAlignment(Element.ALIGN_LEFT);
            document.add(detalles);

            // Espacio después del mensaje
            document.add(new Paragraph("\n\n"));

            // Añadir un pie de página o algún otro contenido adicional
            Paragraph footer = new Paragraph("¡No los olvides! \nGracias por registrarte en el INEGI.",
                    new Font(Font.HELVETICA, 12, Font.ITALIC));
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);

        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // step 5: we close the document
        document.close();

        JOptionPane.showMessageDialog(null, "Se creó el archivo 'Bienvenida.pdf' en la carpeta del proyecto");
    }

    
}
