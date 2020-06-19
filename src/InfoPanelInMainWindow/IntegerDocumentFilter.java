package InfoPanelInMainWindow;

import javax.swing.text.*;
import java.awt.*;

public class IntegerDocumentFilter extends DocumentFilter
{
    private AbstractDocument abstractDocument;

    private IntegerDocumentFilter(AbstractDocument abstractDocument)
    {
        this.abstractDocument = abstractDocument;
    }

    @Override
    public void replace(FilterBypass filterBypass, int offset,
                        int length, String input, AttributeSet attributeSet)
            throws BadLocationException
    {
        int inputLength = input.length();

        String text = abstractDocument.getText(0, abstractDocument.getLength());
        int newLength = text.length() + inputLength;

        if (isNumeric(input) && newLength <= 8)
        {
            super.replace(filterBypass, offset, length, input, attributeSet);
        } else
        {
            Toolkit.getDefaultToolkit().beep();
        }
    }
    private boolean isNumeric(String input)
    {
        String regularExpression = "[0-9]+";
        return input.matches(regularExpression);
    }

    public static void addTo(JTextComponent textComponent)
    {
        AbstractDocument abstractDocument = (AbstractDocument) textComponent.getDocument();
        IntegerDocumentFilter integerDocumentFilter = new IntegerDocumentFilter(abstractDocument);
        abstractDocument.setDocumentFilter(integerDocumentFilter);
    }
}
