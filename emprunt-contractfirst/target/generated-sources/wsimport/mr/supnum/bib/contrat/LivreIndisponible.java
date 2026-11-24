
package mr.supnum.bib.contrat;

import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType>
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="isbn" type="{http://supnum.mr/bib}IsbnType"/>
 *         <element name="retourPrevu" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "isbn",
    "retourPrevu"
})
@XmlRootElement(name = "LivreIndisponible")
public class LivreIndisponible {

    @XmlElement(required = true)
    protected String isbn;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar retourPrevu;

    /**
     * Gets the value of the isbn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Sets the value of the isbn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsbn(String value) {
        this.isbn = value;
    }

    /**
     * Gets the value of the retourPrevu property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRetourPrevu() {
        return retourPrevu;
    }

    /**
     * Sets the value of the retourPrevu property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRetourPrevu(XMLGregorianCalendar value) {
        this.retourPrevu = value;
    }

}
