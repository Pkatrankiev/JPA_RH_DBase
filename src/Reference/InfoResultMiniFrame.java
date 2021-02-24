package Reference;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CreateWordDocProtocol.FunctionForGenerateWordDocFile;
import DBase_Class.Results;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import WindowView.RequestMiniFrame;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Color;
import javax.swing.border.MatteBorder;

public class InfoResultMiniFrame extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	
	public InfoResultMiniFrame(JFrame parent, String frame_name,Results result) {
		super(parent, frame_name, true);
		
		String yes = ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("Yes_Btn_Text");
		
		String no = ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("No_Btn_Text");
		
		int maxWidth = 0;
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{80, 80, 80, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 15, 21, 0, 0, 0, 0, 0, 0, 0, 13, 0, 0, 15, 0, 16, 0, 18, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblCodeRequest = new JLabel(ReadFileWithGlobalTextVariable.
					getGlobalTextVariableMap().get("Reference_LabelText_CodeRequest"));
			
			GridBagConstraints gbc_lblCodeRequest = new GridBagConstraints();
			gbc_lblCodeRequest.anchor = GridBagConstraints.EAST;
			gbc_lblCodeRequest.insets = new Insets(0, 0, 5, 5);
			gbc_lblCodeRequest.gridx = 0;
			gbc_lblCodeRequest.gridy = 0;
			contentPanel.add(lblCodeRequest, gbc_lblCodeRequest);
		}
		{
			JLabel lblValueCodeRequest = new JLabel(result.getRequest().getRecuest_code());
			GridBagConstraints gbc_lblValueCodeRequest = new GridBagConstraints();
			gbc_lblValueCodeRequest.anchor = GridBagConstraints.WEST;
			gbc_lblValueCodeRequest.gridwidth = 4;
			gbc_lblValueCodeRequest.insets = new Insets(0, 0, 5, 0);
			gbc_lblValueCodeRequest.gridx = 1;
			gbc_lblValueCodeRequest.gridy = 0;
			contentPanel.add(lblValueCodeRequest, gbc_lblValueCodeRequest);
			maxWidth = RequestMiniFrame.getMaxWidth(maxWidth,lblValueCodeRequest);
		}
		{
			JLabel lblCountSample = new JLabel(ReadFileWithGlobalTextVariable.
					getGlobalTextVariableMap().get("Reference_LabelText_CountSample"));
			GridBagConstraints gbc_lblCountSample = new GridBagConstraints();
			gbc_lblCountSample.anchor = GridBagConstraints.EAST;
			gbc_lblCountSample.insets = new Insets(0, 0, 5, 5);
			gbc_lblCountSample.gridx = 0;
			gbc_lblCountSample.gridy = 1;
			contentPanel.add(lblCountSample, gbc_lblCountSample);
		}
		{
			JLabel lblValueCountSample = new JLabel(result.getRequest().getCounts_samples()+"");
			GridBagConstraints gbc_lblValueCountSample = new GridBagConstraints();
			gbc_lblValueCountSample.anchor = GridBagConstraints.WEST;
			gbc_lblValueCountSample.gridwidth = 3;
			gbc_lblValueCountSample.insets = new Insets(0, 0, 5, 5);
			gbc_lblValueCountSample.gridx = 1;
			gbc_lblValueCountSample.gridy = 1;
			contentPanel.add(lblValueCountSample, gbc_lblValueCountSample);
			maxWidth = RequestMiniFrame.getMaxWidth(maxWidth,lblValueCountSample);
		}
		{
			JLabel lblDateRequest = new JLabel(ReadFileWithGlobalTextVariable.
					getGlobalTextVariableMap().get("Reference_LabelText_DateRequest"));
			GridBagConstraints gbc_lblDateRequest = new GridBagConstraints();
			gbc_lblDateRequest.anchor = GridBagConstraints.EAST;
			gbc_lblDateRequest.insets = new Insets(0, 0, 5, 5);
			gbc_lblDateRequest.gridx = 0;
			gbc_lblDateRequest.gridy = 2;
			contentPanel.add(lblDateRequest, gbc_lblDateRequest);
		}
		{
			JLabel lblValueDateRequest = new JLabel(result.getRequest().getDate_request());
			GridBagConstraints gbc_lblValueDateRequest = new GridBagConstraints();
			gbc_lblValueDateRequest.anchor = GridBagConstraints.WEST;
			gbc_lblValueDateRequest.gridwidth = 4;
			gbc_lblValueDateRequest.insets = new Insets(0, 0, 5, 0);
			gbc_lblValueDateRequest.gridx = 1;
			gbc_lblValueDateRequest.gridy = 2;
			contentPanel.add(lblValueDateRequest, gbc_lblValueDateRequest);
			maxWidth = RequestMiniFrame.getMaxWidth(maxWidth,lblValueDateRequest);
		}
		{
			JLabel lblApplicant = new JLabel(ReadFileWithGlobalTextVariable.
					getGlobalTextVariableMap().get("Reference_LabelText_Applicant"));
			GridBagConstraints gbc_lblApplicant = new GridBagConstraints();
			gbc_lblApplicant.anchor = GridBagConstraints.EAST;
			gbc_lblApplicant.insets = new Insets(0, 0, 5, 5);
			gbc_lblApplicant.gridx = 0;
			gbc_lblApplicant.gridy = 3;
			contentPanel.add(lblApplicant, gbc_lblApplicant);
		}
		{
			JLabel lblValueApplicant = new JLabel(getAplicnt(result));
			GridBagConstraints gbc_lblValueApplicant = new GridBagConstraints();
			gbc_lblValueApplicant.anchor = GridBagConstraints.WEST;
			gbc_lblValueApplicant.gridwidth = 4;
			gbc_lblValueApplicant.insets = new Insets(0, 0, 5, 0);
			gbc_lblValueApplicant.gridx = 1;
			gbc_lblValueApplicant.gridy = 3;
			contentPanel.add(lblValueApplicant, gbc_lblValueApplicant);
			maxWidth = RequestMiniFrame.getMaxWidth(maxWidth,lblValueApplicant);
		}
		{
			JLabel lblInAcredition = new JLabel(ReadFileWithGlobalTextVariable.
					getGlobalTextVariableMap().get("Reference_LabelText_InAcredition"));
			GridBagConstraints gbc_lblInAcredition = new GridBagConstraints();
			gbc_lblInAcredition.anchor = GridBagConstraints.EAST;
			gbc_lblInAcredition.insets = new Insets(0, 0, 5, 5);
			gbc_lblInAcredition.gridx = 0;
			gbc_lblInAcredition.gridy = 4;
			contentPanel.add(lblInAcredition, gbc_lblInAcredition);
		}
		{
			JLabel lblValueInAcredition = new JLabel(result.getRequest().getAccreditation()? no : yes );
			GridBagConstraints gbc_lblValueInAcredition = new GridBagConstraints();
			gbc_lblValueInAcredition.anchor = GridBagConstraints.WEST;
			gbc_lblValueInAcredition.gridwidth = 4;
			gbc_lblValueInAcredition.insets = new Insets(0, 0, 5, 0);
			gbc_lblValueInAcredition.gridx = 1;
			gbc_lblValueInAcredition.gridy = 4;
			contentPanel.add(lblValueInAcredition, gbc_lblValueInAcredition);
			maxWidth = RequestMiniFrame.getMaxWidth(maxWidth,lblValueInAcredition);
		}
		{
			JLabel lblComment = new JLabel(ReadFileWithGlobalTextVariable.
					getGlobalTextVariableMap().get("Reference_LabelText_Comment"));
			GridBagConstraints gbc_lblComment = new GridBagConstraints();
			gbc_lblComment.anchor = GridBagConstraints.EAST;
			gbc_lblComment.insets = new Insets(0, 0, 5, 5);
			gbc_lblComment.gridx = 0;
			gbc_lblComment.gridy = 5;
			contentPanel.add(lblComment, gbc_lblComment);
		}
		{
			JLabel lblValuelComment = new JLabel(result.getRequest().getDescription_sample_group());
			GridBagConstraints gbc_lblValuelComment = new GridBagConstraints();
			gbc_lblValuelComment.anchor = GridBagConstraints.WEST;
			gbc_lblValuelComment.gridwidth = 4;
			gbc_lblValuelComment.insets = new Insets(0, 0, 5, 0);
			gbc_lblValuelComment.gridx = 1;
			gbc_lblValuelComment.gridy = 5;
			contentPanel.add(lblValuelComment, gbc_lblValuelComment);
			maxWidth = RequestMiniFrame.getMaxWidth(maxWidth,lblValuelComment);
		}
		{
			JLabel lblSeparator1 = new JLabel("");
			lblSeparator1.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
			GridBagConstraints gbc_lblSeparator1 = new GridBagConstraints();
			gbc_lblSeparator1.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblSeparator1.gridwidth = 5;
			gbc_lblSeparator1.insets = new Insets(0, 0, 5, 0);
			gbc_lblSeparator1.gridx = 0;
			gbc_lblSeparator1.gridy = 6;
			contentPanel.add(lblSeparator1, gbc_lblSeparator1);
		}
		{
			JLabel lblSampleCode = new JLabel(ReadFileWithGlobalTextVariable.
					getGlobalTextVariableMap().get("Reference_LabelText_SampleCode"));
			GridBagConstraints gbc_lblSampleCode = new GridBagConstraints();
			gbc_lblSampleCode.anchor = GridBagConstraints.EAST;
			gbc_lblSampleCode.insets = new Insets(0, 0, 5, 5);
			gbc_lblSampleCode.gridx = 0;
			gbc_lblSampleCode.gridy = 7;
			contentPanel.add(lblSampleCode, gbc_lblSampleCode);
		}
		{
			JLabel lblValueSampleCode = new JLabel(result.getRequest().getRecuest_code()+"-"+result.getSample().getSample_code());
			GridBagConstraints gbc_lblValueSampleCode = new GridBagConstraints();
			gbc_lblValueSampleCode.anchor = GridBagConstraints.WEST;
			gbc_lblValueSampleCode.gridwidth = 3;
			gbc_lblValueSampleCode.insets = new Insets(0, 0, 5, 5);
			gbc_lblValueSampleCode.gridx = 1;
			gbc_lblValueSampleCode.gridy = 7;
			contentPanel.add(lblValueSampleCode, gbc_lblValueSampleCode);
		}
		{
			JLabel lblDescripSample = new JLabel(ReadFileWithGlobalTextVariable.
					getGlobalTextVariableMap().get("Reference_LabelText_CommentSample"));
			GridBagConstraints gbc_lblDescripSample = new GridBagConstraints();
			gbc_lblDescripSample.anchor = GridBagConstraints.EAST;
			gbc_lblDescripSample.insets = new Insets(0, 0, 5, 5);
			gbc_lblDescripSample.gridx = 0;
			gbc_lblDescripSample.gridy = 8;
			contentPanel.add(lblDescripSample, gbc_lblDescripSample);
		}
		{
			JLabel lblValueDeskripSample = new JLabel(result.getSample().getDescription_sample());
			GridBagConstraints gbc_lblValueDeskripSample = new GridBagConstraints();
			gbc_lblValueDeskripSample.anchor = GridBagConstraints.WEST;
			gbc_lblValueDeskripSample.gridwidth = 4;
			gbc_lblValueDeskripSample.insets = new Insets(0, 0, 5, 5);
			gbc_lblValueDeskripSample.gridx = 1;
			gbc_lblValueDeskripSample.gridy = 8;
			contentPanel.add(lblValueDeskripSample, gbc_lblValueDeskripSample);
		}
		{
			JLabel lblMetod = new JLabel(ReadFileWithGlobalTextVariable.
					getGlobalTextVariableMap().get("LabelText_Metod"));
			GridBagConstraints gbc_lblMetod = new GridBagConstraints();
			gbc_lblMetod.anchor = GridBagConstraints.EAST;
			gbc_lblMetod.insets = new Insets(0, 0, 5, 5);
			gbc_lblMetod.gridx = 0;
			gbc_lblMetod.gridy = 9;
			contentPanel.add(lblMetod, gbc_lblMetod);
		}
		{
			JLabel lblValueMetod = new JLabel(result.getMetody().getName_metody());
			GridBagConstraints gbc_lblValueMetod = new GridBagConstraints();
			gbc_lblValueMetod.anchor = GridBagConstraints.WEST;
			gbc_lblValueMetod.gridwidth = 4;
			gbc_lblValueMetod.insets = new Insets(0, 0, 5, 0);
			gbc_lblValueMetod.gridx = 1;
			gbc_lblValueMetod.gridy = 9;
			contentPanel.add(lblValueMetod, gbc_lblValueMetod);
			maxWidth = RequestMiniFrame.getMaxWidth(maxWidth,lblValueMetod);
		}
		{
			JLabel lblTSI = new JLabel(ReadFileWithGlobalTextVariable.
					getGlobalTextVariableMap().get("Reference_LabelText_TSI"));
			GridBagConstraints gbc_lblTSI = new GridBagConstraints();
			gbc_lblTSI.anchor = GridBagConstraints.EAST;
			gbc_lblTSI.insets = new Insets(0, 0, 5, 5);
			gbc_lblTSI.gridx = 0;
			gbc_lblTSI.gridy = 10;
			contentPanel.add(lblTSI, gbc_lblTSI);
		}
		{
			JLabel lblValueTSI = new JLabel(result.getTsi().getName());
			GridBagConstraints gbc_lblValueTSI = new GridBagConstraints();
			gbc_lblValueTSI.anchor = GridBagConstraints.WEST;
			gbc_lblValueTSI.gridwidth = 4;
			gbc_lblValueTSI.insets = new Insets(0, 0, 5, 0);
			gbc_lblValueTSI.gridx = 1;
			gbc_lblValueTSI.gridy = 10;
			contentPanel.add(lblValueTSI, gbc_lblValueTSI);
			maxWidth = RequestMiniFrame.getMaxWidth(maxWidth,lblValueTSI);
		}
		{
			JLabel lblHimOperUser =  new JLabel(ReadFileWithGlobalTextVariable.
					getGlobalTextVariableMap().get("LabelText_IzvarshilHimObrabotka"));
			GridBagConstraints gbc_lblHimOperUser = new GridBagConstraints();
			gbc_lblHimOperUser.anchor = GridBagConstraints.EAST;
			gbc_lblHimOperUser.insets = new Insets(0, 0, 5, 5);
			gbc_lblHimOperUser.gridx = 0;
			gbc_lblHimOperUser.gridy = 11;
			contentPanel.add(lblHimOperUser, gbc_lblHimOperUser);
		}
		{
			JLabel lblValueHimOperUser = new JLabel(result.getUser_chim_oper().getName_users()+" "+result.getUser_chim_oper().getFamily_users());
			GridBagConstraints gbc_lblValueHimOperUser = new GridBagConstraints();
			gbc_lblValueHimOperUser.anchor = GridBagConstraints.WEST;
			gbc_lblValueHimOperUser.gridwidth = 4;
			gbc_lblValueHimOperUser.insets = new Insets(0, 0, 5, 0);
			gbc_lblValueHimOperUser.gridx = 1;
			gbc_lblValueHimOperUser.gridy = 11;
			contentPanel.add(lblValueHimOperUser, gbc_lblValueHimOperUser);
			maxWidth = RequestMiniFrame.getMaxWidth(maxWidth,lblValueHimOperUser);
		}
		{
			JLabel lblDateHimOper = new JLabel(ReadFileWithGlobalTextVariable.
					getGlobalTextVariableMap().get("Reference_LabelText_DateHimOper"));
			GridBagConstraints gbc_lblDateHimOper = new GridBagConstraints();
			gbc_lblDateHimOper.anchor = GridBagConstraints.EAST;
			gbc_lblDateHimOper.insets = new Insets(0, 0, 5, 5);
			gbc_lblDateHimOper.gridx = 0;
			gbc_lblDateHimOper.gridy = 12;
			contentPanel.add(lblDateHimOper, gbc_lblDateHimOper);
		}
		{
			JLabel lblValueDateHimOper = new JLabel(result.getDate_chim_oper());
			GridBagConstraints gbc_lblValueDateHimOper = new GridBagConstraints();
			gbc_lblValueDateHimOper.anchor = GridBagConstraints.WEST;
			gbc_lblValueDateHimOper.gridwidth = 4;
			gbc_lblValueDateHimOper.insets = new Insets(0, 0, 5, 0);
			gbc_lblValueDateHimOper.gridx = 1;
			gbc_lblValueDateHimOper.gridy = 12;
			contentPanel.add(lblValueDateHimOper, gbc_lblValueDateHimOper);
			maxWidth = RequestMiniFrame.getMaxWidth(maxWidth,lblValueDateHimOper);
		}
		{
			JLabel lblMeasurUser = new JLabel(ReadFileWithGlobalTextVariable.
					getGlobalTextVariableMap().get("LabelText_IzvarshilAnaliza"));
			GridBagConstraints gbc_lblMeasurUser = new GridBagConstraints();
			gbc_lblMeasurUser.anchor = GridBagConstraints.EAST;
			gbc_lblMeasurUser.insets = new Insets(0, 0, 5, 5);
			gbc_lblMeasurUser.gridx = 0;
			gbc_lblMeasurUser.gridy = 13;
			contentPanel.add(lblMeasurUser, gbc_lblMeasurUser);
		}
		{
			JLabel lblValueMeasurUser = new JLabel(result.getUser_measur().getName_users()+" "+result.getUser_measur().getFamily_users());
			GridBagConstraints gbc_lblValueMeasurUser = new GridBagConstraints();
			gbc_lblValueMeasurUser.anchor = GridBagConstraints.WEST;
			gbc_lblValueMeasurUser.gridwidth = 4;
			gbc_lblValueMeasurUser.insets = new Insets(0, 0, 5, 0);
			gbc_lblValueMeasurUser.gridx = 1;
			gbc_lblValueMeasurUser.gridy = 13;
			contentPanel.add(lblValueMeasurUser, gbc_lblValueMeasurUser);
			maxWidth = RequestMiniFrame.getMaxWidth(maxWidth,lblValueMeasurUser);
		}
		{
			JLabel lblDateMeasurOper = new JLabel(ReadFileWithGlobalTextVariable.
					getGlobalTextVariableMap().get("Reference_LabelText_DateMeasurOper"));
			GridBagConstraints gbc_lblDateMeasurOper = new GridBagConstraints();
			gbc_lblDateMeasurOper.anchor = GridBagConstraints.EAST;
			gbc_lblDateMeasurOper.insets = new Insets(0, 0, 5, 5);
			gbc_lblDateMeasurOper.gridx = 0;
			gbc_lblDateMeasurOper.gridy = 14;
			contentPanel.add(lblDateMeasurOper, gbc_lblDateMeasurOper);
		}
		{
			JLabel lblValueDateMeasurOper = new JLabel(result.getDate_measur());
			GridBagConstraints gbc_lblValueDateMeasurOper = new GridBagConstraints();
			gbc_lblValueDateMeasurOper.anchor = GridBagConstraints.WEST;
			gbc_lblValueDateMeasurOper.gridwidth = 4;
			gbc_lblValueDateMeasurOper.insets = new Insets(0, 0, 5, 0);
			gbc_lblValueDateMeasurOper.gridx = 1;
			gbc_lblValueDateMeasurOper.gridy = 14;
			contentPanel.add(lblValueDateMeasurOper, gbc_lblValueDateMeasurOper);
			maxWidth = RequestMiniFrame.getMaxWidth(maxWidth,lblValueDateMeasurOper);
		}
		{
			JLabel lblSeparator2 = new JLabel("");
			lblSeparator2.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
			GridBagConstraints gbc_lblSeparator2 = new GridBagConstraints();
			gbc_lblSeparator2.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblSeparator2.gridwidth = 5;
			gbc_lblSeparator2.insets = new Insets(0, 0, 5, 0);
			gbc_lblSeparator2.gridx = 0;
			gbc_lblSeparator2.gridy = 15;
			contentPanel.add(lblSeparator2, gbc_lblSeparator2);
		}
		{
			JLabel lblNuclide = new JLabel(ReadFileWithGlobalTextVariable.
					getGlobalTextVariableMap().get("Reference_Table_Column_Nuclide"));
			GridBagConstraints gbc_lblNuclide = new GridBagConstraints();
			gbc_lblNuclide.insets = new Insets(0, 0, 5, 5);
			gbc_lblNuclide.gridx = 0;
			gbc_lblNuclide.gridy = 16;
			contentPanel.add(lblNuclide, gbc_lblNuclide);
		}
		{
			JLabel lblResult = new JLabel(ReadFileWithGlobalTextVariable.
					getGlobalTextVariableMap().get("Reference_LabelRadioButton_Activnost"));
			GridBagConstraints gbc_lblResult = new GridBagConstraints();
			gbc_lblResult.insets = new Insets(0, 0, 5, 5);
			gbc_lblResult.gridx = 1;
			gbc_lblResult.gridy = 16;
			contentPanel.add(lblResult, gbc_lblResult);
		}
		{
			JLabel lblMDA = new JLabel(ReadFileWithGlobalTextVariable.
					getGlobalTextVariableMap().get("Reference_LabelRadioButton_MDA"));
			GridBagConstraints gbc_lblMDA = new GridBagConstraints();
			gbc_lblMDA.insets = new Insets(0, 0, 5, 5);
			gbc_lblMDA.gridx = 2;
			gbc_lblMDA.gridy = 16;
			contentPanel.add(lblMDA, gbc_lblMDA);
		}
		{
			JLabel lblInProtokol =  new JLabel(ReadFileWithGlobalTextVariable.
					getGlobalTextVariableMap().get("Reference_LabelText_InProtokol"));
			GridBagConstraints gbc_lblInProtokol = new GridBagConstraints();
			gbc_lblInProtokol.insets = new Insets(0, 0, 5, 5);
			gbc_lblInProtokol.gridx = 3;
			gbc_lblInProtokol.gridy = 16;
			contentPanel.add(lblInProtokol, gbc_lblInProtokol);
		}
		{
			JLabel lblValueNucklide = new JLabel(result.getNuclide().getSymbol_nuclide());
			GridBagConstraints gbc_lblValueNucklide = new GridBagConstraints();
			gbc_lblValueNucklide.insets = new Insets(0, 0, 5, 5);
			gbc_lblValueNucklide.gridx = 0;
			gbc_lblValueNucklide.gridy = 17;
			contentPanel.add(lblValueNucklide, gbc_lblValueNucklide);
		}
		{
			JLabel lblValueResult = new JLabel(getResult(result));
			GridBagConstraints gbc_lblValueResult = new GridBagConstraints();
			gbc_lblValueResult.insets = new Insets(0, 0, 5, 5);
			gbc_lblValueResult.gridx = 1;
			gbc_lblValueResult.gridy = 17;
			contentPanel.add(lblValueResult, gbc_lblValueResult);
		}
		{
			JLabel lblValueMDA = new JLabel(getMDA(result));
			GridBagConstraints gbc_lblValueMDA = new GridBagConstraints();
			gbc_lblValueMDA.insets = new Insets(0, 0, 5, 5);
			gbc_lblValueMDA.gridx = 2;
			gbc_lblValueMDA.gridy = 17;
			contentPanel.add(lblValueMDA, gbc_lblValueMDA);
		}
		{
			JLabel lblValueInProtokol = new JLabel(result.getInProtokol()? yes : no );
			GridBagConstraints gbc_lblValueInProtokol = new GridBagConstraints();
			gbc_lblValueInProtokol.insets = new Insets(0, 0, 5, 5);
			gbc_lblValueInProtokol.gridx = 3;
			gbc_lblValueInProtokol.gridy = 17;
			contentPanel.add(lblValueInProtokol, gbc_lblValueInProtokol);
		}
		{
			JLabel lblSeparator3 = new JLabel("");
			lblSeparator3.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
			GridBagConstraints gbc_lblSeparator3 = new GridBagConstraints();
			gbc_lblSeparator3.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblSeparator3.gridwidth = 5;
			gbc_lblSeparator3.insets = new Insets(0, 0, 5, 5);
			gbc_lblSeparator3.gridx = 0;
			gbc_lblSeparator3.gridy = 18;
			contentPanel.add(lblSeparator3, gbc_lblSeparator3);
		}
		{
			JLabel lblFileLokation = new JLabel(ReadFileWithGlobalTextVariable.
					getGlobalTextVariableMap().get("Reference_LabelText_FileLokation"));
			GridBagConstraints gbc_lblFileLokation = new GridBagConstraints();
			gbc_lblFileLokation.anchor = GridBagConstraints.EAST;
			gbc_lblFileLokation.insets = new Insets(0, 0, 5, 5);
			gbc_lblFileLokation.gridx = 0;
			gbc_lblFileLokation.gridy = 19;
			contentPanel.add(lblFileLokation, gbc_lblFileLokation);
		}
		{
			JLabel lblValueFileLokation = new JLabel(result.getBasic_value());
			GridBagConstraints gbc_lblValueFileLokation = new GridBagConstraints();
			gbc_lblValueFileLokation.anchor = GridBagConstraints.WEST;
			gbc_lblValueFileLokation.gridwidth = 4;
			gbc_lblValueFileLokation.insets = new Insets(0, 0, 5, 0);
			gbc_lblValueFileLokation.gridx = 1;
			gbc_lblValueFileLokation.gridy = 19;
			contentPanel.add(lblValueFileLokation, gbc_lblValueFileLokation);
			maxWidth = RequestMiniFrame.getMaxWidth(maxWidth,lblValueFileLokation);
		}
		{
			JLabel lblSeparator4 = new JLabel("");
			lblSeparator4.setBorder(new MatteBorder(1, 0, 0, 0, (Color) Color.BLACK));
			GridBagConstraints gbc_lblSeparator4 = new GridBagConstraints();
			gbc_lblSeparator4.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblSeparator4.gridwidth = 5;
			gbc_lblSeparator4.insets = new Insets(0, 0, 5, 0);
			gbc_lblSeparator4.gridx = 0;
			gbc_lblSeparator4.gridy = 20;
			contentPanel.add(lblSeparator4, gbc_lblSeparator4);
		}
		{
			JLabel lblDobivName = new JLabel(ReadFileWithGlobalTextVariable.
					getGlobalTextVariableMap().get("LabelText_Dobiv"));
			GridBagConstraints gbc_lblDobivName = new GridBagConstraints();
			gbc_lblDobivName.anchor = GridBagConstraints.EAST;
			gbc_lblDobivName.insets = new Insets(0, 0, 5, 5);
			gbc_lblDobivName.gridx = 0;
			gbc_lblDobivName.gridy = 21;
			contentPanel.add(lblDobivName, gbc_lblDobivName);
		}
		{
			JLabel lblValueDobivName = new JLabel(result.getDobiv()!=null ? result.getDobiv().getCode_Standart():"");
			GridBagConstraints gbc_lblValueDobivName = new GridBagConstraints();
			gbc_lblValueDobivName.anchor = GridBagConstraints.WEST;
			gbc_lblValueDobivName.gridwidth = 4;
			gbc_lblValueDobivName.insets = new Insets(0, 0, 5, 0);
			gbc_lblValueDobivName.gridx = 1;
			gbc_lblValueDobivName.gridy = 21;
			contentPanel.add(lblValueDobivName, gbc_lblValueDobivName);
			maxWidth = RequestMiniFrame.getMaxWidth(maxWidth,lblValueDobivName);
		}
		{
			JLabel lblValueDobivNuclide = new JLabel(result.getDobiv()!=null ? result.getDobiv().getNuclide().getSymbol_nuclide():"");
			GridBagConstraints gbc_lblValueDobivNuclide = new GridBagConstraints();
			gbc_lblValueDobivNuclide.anchor = GridBagConstraints.EAST;
			gbc_lblValueDobivNuclide.insets = new Insets(0, 0, 0, 5);
			gbc_lblValueDobivNuclide.gridx = 0;
			gbc_lblValueDobivNuclide.gridy = 22;
			contentPanel.add(lblValueDobivNuclide, gbc_lblValueDobivNuclide);
		}
		{
			JLabel lblValueDobivValue = new JLabel(result.getDobiv()!=null ? result.getDobiv().getValue_result().toString() : "");
			GridBagConstraints gbc_lblValueDobivValue = new GridBagConstraints();
			gbc_lblValueDobivValue.anchor = GridBagConstraints.WEST;
			gbc_lblValueDobivValue.gridwidth = 4;
			gbc_lblValueDobivValue.insets = new Insets(0, 0, 0, 5);
			gbc_lblValueDobivValue.gridx = 1;
			gbc_lblValueDobivValue.gridy = 22;
			contentPanel.add(lblValueDobivValue, gbc_lblValueDobivValue);
		}
		
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				
				

				
				btnCancelListener(cancelButton);
			}
			int width = 165+maxWidth;
			int height	= 550;
			int[] koordinates = RequestMiniFrame.getCurentKoordinates(width, height);
			setBounds(koordinates[0], koordinates[1], width , height);

			setVisible(true);
		
	}

	private void btnCancelListener(JButton btnCancel) {
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
	}

	private String getMDA(Results result) {
		
		return  FunctionForGenerateWordDocFile.formatter(result.getMda());
	}


	private String getResult(Results result) {
		return  FunctionForGenerateWordDocFile.formatter(result.getValue_result()) + " ± "
				+FunctionForGenerateWordDocFile. alignExpon(result.getValue_result(), result.getUncertainty());
		
	}


	private String getAplicnt(Results result) {
		String aplicant = "";
		if(result.getRequest().getInd_num_doc().getId_ind_num_doc()>1){
			aplicant = result.getRequest().getInd_num_doc().getName();
		}else{
			if(result.getRequest().getExtra_module()!=null&&result.getRequest().getExtra_module().getInternal_applicant()!=null){
				aplicant = result.getRequest().getExtra_module().getInternal_applicant().getInternal_applicant_organization();
			}
		}
		return aplicant;
	}

}
