package CreateWordDocProtocol;

import java.io.IOException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import GlobalVariable.ResourceLoader;



/**
 * SAX content handler used to parse HTML content and call the right method of {@link IDocumentHandler} according the
 * HTML content.
 */
public class HTMLTextStylingContentHandler
    extends DefaultHandler
{


    // HTML elements for subscript
    private static final String SUB_ELT = "sub";

    // HTML elements for superscript
    private static final String SUP_ELT = "sup";

    

    private final IDocumentHandler documentHandler;

    public  HTMLTextStylingContentHandler( IDocumentHandler visitor )
    {
        this.documentHandler = visitor;
        new StringBuilder();
    }

   

    @Override
    public  void startElement( String uri, String localName, String name, Attributes attributes )
        throws SAXException
    {
        try
        {
                 
            if ( SUB_ELT.equals( name ) )
            {
                // Subscript
                documentHandler.startSubscript();
            }
            else if ( SUP_ELT.equals( name ) )
            {
                // Superscript
                documentHandler.startSuperscript();
            }
         
        } 
            
           
        catch ( IOException e )
        {
        	ResourceLoader.appendToFile(e);
            throw new SAXException( e );
        }
        super.startElement( uri, localName, name, attributes );
    }

    @Override
    public  void endElement( String uri, String localName, String name )
        throws SAXException
    {
        try
        {
           if ( SUB_ELT.equals( name ) )
            {
                // Subscript
                documentHandler.endSubscript();
            }
            else if ( SUP_ELT.equals( name ) )
            {
                // Superscript
                documentHandler.endSuperscript();
            }
           
        }
        catch ( IOException e )
        {
        	ResourceLoader.appendToFile(e);
            throw new SAXException( e );
        }
        super.endElement( uri, localName, name );
    }

 

 

 
}
