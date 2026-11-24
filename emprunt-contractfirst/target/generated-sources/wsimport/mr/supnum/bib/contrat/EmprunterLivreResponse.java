
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
 *         <element name="emprunt" type="{http://supnum.mr/bib}EmpruntType"/>
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
    "emprunt"
})
@XmlRootElement(name = "emprunterLivreResponse")
public class EmprunterLivreResponse {

    @XmlElement(required = true)
    protected EmpruntType emprunt;

    /**
     * Gets the value of the emprunt property.
     * 
     * @return
     *     possible object is
     *     {@link EmpruntType }
     *     
     */
    public EmpruntType getEmprunt() {
        return emprunt;
    }

    /**
     * Sets the value of the emprunt property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmpruntType }
     *     
     */
    public void setEmprunt(EmpruntType value) {
        this.emprunt = value;
    }

}
