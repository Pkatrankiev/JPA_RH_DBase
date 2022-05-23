package CreateWordDocProtocol;

/*
*  Copyright 2007-2008, Plutext Pty Ltd.
*   
*  This file is part of docx4j.

    docx4j is licensed under the Apache License, Version 2.0 (the "License"); 
    you may not use this file except in compliance with the License. 

    You may obtain a copy of the License at 

        http://www.apache.org/licenses/LICENSE-2.0 

    Unless required by applicable law or agreed to in writing, software 
    distributed under the License is distributed on an "AS IS" BASIS, 
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
    See the License for the specific language governing permissions and 
    limitations under the License.

*/


import java.io.File;
import java.math.BigInteger;
import java.util.List;

import javax.xml.bind.JAXBContext;


import org.docx4j.TraversalUtil;
import org.docx4j.XmlUtils;
import org.docx4j.jaxb.Context;
import org.docx4j.TraversalUtil.Callback;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.samples.AbstractSample;
import org.docx4j.wml.Body;
import org.docx4j.wml.CTVerticalAlignRun;

import org.docx4j.wml.STVerticalAlignRun;

import org.docx4j.wml.UnderlineEnumeration;

import GlobalVariable.ResourceLoader;

/**
* This sample is useful if you want to see what objects are used in your
* document.xml.
* 
* This shows a general approach for traversing the JAXB object tree in the Main
* Document part. It can also be applied to headers, footers etc.
* 
* It is an alternative to XSLT, and doesn't require marshalling/unmarshalling.
* 
* If many cases, the method getJAXBNodesViaXPath may be more convenient.
* 
* @author jharrop
* 
*/
public class OpenMainDocumentAndTraverse extends AbstractSample
{
   
   public static JAXBContext context = org.docx4j.jaxb.Context.jc;
   
  
   public static void main(String[] args) throws Exception
   {
      
      try
      {
         getInputFilePath(args);
      }
      catch (IllegalArgumentException e)
      {
    	  ResourceLoader.appendToFile(e);
         inputfilepath = System.getProperty("user.dir")
               + "/sample-docs/sample-docx.xml";
      }
      
      WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(new java.io.File(inputfilepath));
      MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
      
      org.docx4j.wml.Document wmlDocumentEl = (org.docx4j.wml.Document) documentPart.getJaxbElement();
      Body body = wmlDocumentEl.getBody();
      
      new TraversalUtil(body,new Callback()
      {
         
         String indent = "";
         
         org.docx4j.wml.ObjectFactory factory=new org.docx4j.wml.ObjectFactory();
         
         org.docx4j.wml.R tmpR =factory.createR();
         
         
         
         public List<Object> apply(Object o)
         {
            
            String text = "";
            if (o instanceof org.docx4j.wml.Text)
            {
               org.docx4j.wml.Text t = (org.docx4j.wml.Text) o;
               
               
               
               org.docx4j.wml.Color color =factory.createColor();
            
               color.setVal("FF0000");
               
               org.docx4j.wml.U u1=factory.createU();
               
               org.docx4j.wml.HpsMeasure meature=factory.createHpsMeasure();
               
               text = t.getValue();
               
               if (text.equals("[Name]"))
               {
                  /**看看tmpR下是否有rPr即格式设置,没有就创建因为格式这些东西只能在rPr中进行设置**/
                  if (tmpR.getRPr() == null)
                  {
                     org.docx4j.wml.RPr t1 = factory.createRPr();                                          
                                          
                     t1.setColor(color);
                     
                     CTVerticalAlignRun vAlign = Context.getWmlObjectFactory().createCTVerticalAlignRun();
                     vAlign.setVal(STVerticalAlignRun.SUPERSCRIPT);			
         			
         			
                     t1.setVertAlign(vAlign);
                     
                     /**切记,对word操作一定要明白word的格式,比如加下划线对应的xml为
                      * <w:r w:rsidR="00CF4853" w:rsidRPr="00313AF6">
                        <w:rPr>
                          <w:rFonts w:hint="eastAsia" /> 
                          <w:b /> 
                          <w:u w:val="single" />  注意这里,这里就是增加了下划线,不只是标签w:u,他的值设置是属性w:wal决定的 
                          </w:rPr>
                          <w:t>sasasass</w:t> 
                        </w:r>
                      * 
                      * **/
                     if(t1.getU()==null)
                     {
                        u1.setColor("FF0000");
                        u1.setVal(UnderlineEnumeration.SINGLE);
                        t1.setU(u1);
                     }
                     if(t1.getSz()==null)
                     {
                        meature.setVal(BigInteger.valueOf(36));
                        t1.setSz(meature);
                     }
                     tmpR.setRPr(t1);                                       
                  }
                  else
                  {
                     if(tmpR.getRPr().getU()==null)
                     {
                        u1.setColor("FF0000");
                        u1.setVal(UnderlineEnumeration.SINGLE);
                        tmpR.getRPr().setU(u1);
                     }
                     else
                     {
                        tmpR.getRPr().getU().setColor("FF0000");
                        tmpR.getRPr().getU().setVal(UnderlineEnumeration.SINGLE);
                     }
                     
                     if(tmpR.getRPr().getSz()==null)
                     {
                        meature.setVal(BigInteger.valueOf(36));                        
                        tmpR.getRPr().setSz(meature);
                     }
                     else
                     {
                        tmpR.getRPr().getSz().setVal(BigInteger.valueOf(36));
                        
                        
                     }
                     
                     tmpR.getRPr().setColor(color);
                  }
                  
                  t.setValue("Arnold");
               }
            }
            
            if (o instanceof org.docx4j.wml.R)
            {
               tmpR = (org.docx4j.wml.R)o;
            }
            
            System.out.println(indent + o.getClass().getName() + "  \""
                  + text + "\"");
            return null;
         }
         
         public boolean shouldTraverse(Object o)
         {
            return true;
         }
         
         // Depth first
         
         public void walkJAXBElements(Object parent)
         {
            
            indent += "    ";
            
            List<?> children = getChildren(parent);
            if (children != null)
            {
               
               for (Object o : children)
               {
                  
                  // if its wrapped in javax.xml.bind.JAXBElement, get its
                  // value
                  o = XmlUtils.unwrap(o);
                  
                  this.apply(o);
                  
                  if (this.shouldTraverse(o))
                  {
                     walkJAXBElements(o);
                  }
                  
               }
            }
            
            indent = indent.substring(0, indent.length() - 4);
         }
         
         public List<Object> getChildren(Object o)
         {
            return TraversalUtil.getChildrenImpl(o);
         }
         
      }

      );
      
      wordMLPackage.save(new File("c:\\workspace\\test_out.docx"));
      
   }
   
}