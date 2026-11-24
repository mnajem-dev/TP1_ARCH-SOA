package mr.supnum.bib.contrat;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
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
 *         <element name="idAdherent" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="isbn" type="{http://supnum.mr/bib}IsbnType"/>
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
    "idAdherent",
    "isbn"
})
@XmlRootElement(name = "emprunterLivre")
public class EmprunterLivre {

    @XmlElement(required = true)
    protected String idAdherent;
    @XmlElement(required = true)
    protected String isbn;

    /**
     * Gets the value of the idAdherent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdAdherent() {
        return idAdherent;
    }

    /**
     * Sets the value of the idAdherent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdAdherent(String value) {
        this.idAdherent = value;
    }

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

}
