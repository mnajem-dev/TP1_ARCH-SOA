
package mr.supnum.bib.contrat;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mr.supnum.bib.contrat package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mr.supnum.bib.contrat
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EmprunterLivre }
     * 
     * @return
     *     the new instance of {@link EmprunterLivre }
     */
    public EmprunterLivre createEmprunterLivre() {
        return new EmprunterLivre();
    }

    /**
     * Create an instance of {@link EmprunterLivreResponse }
     * 
     * @return
     *     the new instance of {@link EmprunterLivreResponse }
     */
    public EmprunterLivreResponse createEmprunterLivreResponse() {
        return new EmprunterLivreResponse();
    }

    /**
     * Create an instance of {@link EmpruntType }
     * 
     * @return
     *     the new instance of {@link EmpruntType }
     */
    public EmpruntType createEmpruntType() {
        return new EmpruntType();
    }

    /**
     * Create an instance of {@link LivreIndisponible }
     * 
     * @return
     *     the new instance of {@link LivreIndisponible }
     */
    public LivreIndisponible createLivreIndisponible() {
        return new LivreIndisponible();
    }

}
