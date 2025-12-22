package org.pdfclown.jada.examples.biblio;

/**
 * In this Javadoc comment, a summary of all the kinds of bibliographic references applicable.
 * <ul>
 * <li>Normative reference (with version): {@biblio.spec PDF:1.7 8.1}</li>
 * <li>Normative reference (with alternate version): {@biblio.spec PDF:1.7~ADB 8.1}</li>
 * <li>Multi-section normative reference (with version): {@biblio.spec PDF:1.7 8.1;10.5-8}</li>
 * <li>Normative reference (with part): {@biblio.spec CSS/SYNTAX-3 6.1-5}</li>
 * <li>Normative reference (with part and version): {@biblio.spec CSS/PAGE-3:2023 6.1-5}</li>
 * <li>Non-normative reference: {@biblio.doc ITA 5.3}</li>
 * <li>Multi-section non-normative reference: {@biblio.doc ITA 5.3;7;8.4.2}</li>
 * <li>Simple reference to parametric resource: {@biblio.ref PDF-ISSUE 343}</li>
 * </ul>
 */
public class BiblioExample {
  /**
   * Entry point example {@biblio.spec CSS/PAGE-3:2018 5.3.3} (just a random demonstrative
   * bibliographic reference).
   */
  public static void main(String[] args) {
    /*
     * NOTE: This comment demonstrates that Jada Biblio extension detects bibliographic references
     * also inside simple non-Javadoc comments {@biblio.spec XMP/2~ADB}.
     */
  }
}
