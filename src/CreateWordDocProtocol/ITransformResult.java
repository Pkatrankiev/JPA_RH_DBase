package CreateWordDocProtocol;

/**
 * Result of transform text styling.
 */
public interface ITransformResult
{

    public static final String TEXT_BEFORE_PROPERTY = "textBefore";

    public static final String TEXT_BODY_PROPERTY = "textBody";

    public static final String TEXT_END_PROPERTY = "textEnd";

    public  String getTextBefore();

    public  String getTextBody();

    public  String getTextEnd();

}
