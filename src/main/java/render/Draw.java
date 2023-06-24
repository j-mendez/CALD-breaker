package render;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.rendering.PDFRenderer;
import java.awt.Color;
import java.io.IOException;

// draw shapes
public class Draw extends PDFRenderer {
    public static final PDDocument document = new PDDocument();
    public static final String lang = "en";
    public Boolean saved;
    private PDRectangle mediaBox;
    private PDPage page;

    // start drawing a pdf with title
    public Draw(String name) {
            super(document);
            this.page = new PDPage();
            document.addPage(this.page);

            renderTitle(name, document);

            try {
                document.save("./pdfs/" + name.replace(".cald", ".pdf"));
            } catch (IOException e) {
                e.printStackTrace();
                saved = false;
            }
    }

    // draw the pdf title
    public void renderTitle(String name, PDDocument document) {
            try (PDPageContentStream contentStream = new PDPageContentStream(document, this.page)) {
                PDRectangle mediaBox = page.getMediaBox();
                float pageWidth = mediaBox.getWidth();
                float pageHeight = mediaBox.getHeight();

                float margin = 24;
                float width = pageWidth - 2 * margin;

                float startX = mediaBox.getLowerLeftX() + margin;
                float startY = mediaBox.getUpperRightY() - margin;

                contentStream.setNonStrokingColor(Color.BLACK);
                contentStream.addRect(0, 0, pageWidth, pageHeight);
                contentStream.fill();
                contentStream.setNonStrokingColor(Color.LIGHT_GRAY);
                contentStream.addRect(10, 10, 100, 100);
                contentStream.fill();

                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 16);
                contentStream.newLineAtOffset(startX, startY);
                contentStream.showText("Render Chart: " + name);
                contentStream.endText();

                contentStream.close();
            }  catch (IOException e) {
                e.printStackTrace();
            }
    }
}