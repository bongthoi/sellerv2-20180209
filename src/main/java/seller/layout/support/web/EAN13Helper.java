package seller.layout.support.web;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.krysalis.barcode4j.ChecksumMode;
import org.krysalis.barcode4j.impl.upcean.EAN13Bean;
import org.krysalis.barcode4j.impl.upcean.UPCEANLogicImpl;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;



/**
 * @version 1
 * @author ChloeWake
 *
 */
public class EAN13Helper {

	public static void main(String[] args) throws Exception{
        //Create the barcode bean
		
		System.out.println(calculateCodeWithcheckSum("893000100009"));
		EAN13Bean bean = new EAN13Bean();
 
        final int dpi = 150;
 
        //Configure the barcode generator
        bean.setModuleWidth(UnitConv.in2mm(2.0f / dpi)); //makes the narrow bar, width exactly one pixel
       // bean.set
        bean.doQuietZone(false);
        bean.setChecksumMode(ChecksumMode.CP_ADD);
        //Open output file
        File outputFile = new File("out121.png");
        OutputStream out = new FileOutputStream(outputFile);
 
        try {
 
            //Set up the canvas provider for monochrome PNG output
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
 
            //Generate the barcode
            bean.generateBarcode(canvas, "893000100009");
          //  System.out.println(bean.toString());
            //Signal end of generation
            canvas.finish();
        } finally {
            out.close();
        }
    }
	
	public static String calculateCodeWithcheckSum(String codigo){
		   codigo += UPCEANLogicImpl.calcChecksum(codigo);
		   return codigo;
		}
	
	

}