package CreateWordDocProtocol;

import java.io.IOException;



/**
 * Handler to build a document.
 */
public interface IDocumentHandler
    extends ITransformResult
{

    public enum TextLocation
    {
        Before, Body, End;
    }

   
    /**
     * Start Subscript style.
     */
    void startSubscript()
        throws IOException;

    /**
     * End Subscript style.
     */
    void endSubscript()
        throws IOException;

    /**
     * Start Superscript style.
     */
    void startSuperscript()
        throws IOException;

    /**
     * End Superscript style.
     */
    void endSuperscript()
        throws IOException;

    
   
   
    //
    // void endCaption();
    //  void endDocument();
    // void endHeading1();
    // void endHeading2();
    // void endHeading3();
    // void endHeading4();
    // void endHeading5();
    // void endHeading6();
    // void endIndent();
    //  void endItalics();
    // void endLiteral();
    // void endNormalLinkWithCaption();
    //  void endOrderedList();
    // void endOrderedListItem();
    //  void endParagraph();
    // void endPre();
    // void endSmartLinkWithCaption();
    //  void endTable();
    // void endTableData();
    // void endTableHeader();
    // void endTableRecord();
    //  void endUnorderedList();
    // void endUnorderedListItem();
    // void handleNormalLinkWithoutCaption(String string);
    // void handleNowiki(String nowiki);
    // void handleSmartLinkWithoutCaption(String string);
    //  void handleString(String s);
    //
    // void startCaption(AttributeList captionOptions);
    //  void startDocument();
    // void startHeading1();
    // void startHeading2();
    // void startHeading3();
    // void startHeading4();
    // void startHeading5();
    // void startHeading6();
    // void startIndent();
    //  void startItalics();
    // void startLiteral();
    // void startNormalLinkWithCaption(String s);
    // void startOrderedList();
    // void startOrderedListItem();
    // void startParagraph();
    // void startPre();
    // void startSmartLinkWithCaption(String s);
    // void startTable(AttributeList tableOptions);
    // void startTableData(AttributeList options);
    // void startTableHeader(AttributeList list);
    // void startTableRecord(AttributeList rowOptions);
    // void startUnorderedList();
    // void startUnorderedListItem();

}
