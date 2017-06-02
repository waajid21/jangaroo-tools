package net.jangaroo.jooc.mxml.ast;

import net.jangaroo.jooc.JooSymbol;
import net.jangaroo.jooc.Scope;
import net.jangaroo.jooc.ast.AstNode;
import net.jangaroo.jooc.ast.AstVisitor;
import net.jangaroo.jooc.ast.Ide;
import net.jangaroo.jooc.ast.NamespacedIde;
import net.jangaroo.jooc.ast.NodeImplBase;
import org.w3c.dom.Node;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class XmlTag extends NodeImplBase {

  private final Map<String, String> xmlNamespaces = new HashMap<>();

  private final String defaultXmlNamespace;

  private final JooSymbol lt;
  private final Ide tagName;
  private final List<XmlAttribute> attributes;
  private final JooSymbol gt;

  private XmlElement xmlElement;
  private JooSymbol id;

  public XmlTag(JooSymbol lt, Ide tagName, List<XmlAttribute> attributes, JooSymbol gt) {
    this.lt = lt;
    this.tagName = tagName;
    this.attributes = attributes;
    this.gt = gt;

    // extract namespace definitions
    String defaultNamespace = null;
    if (attributes != null) {
      for (XmlAttribute attribute : attributes) {
        String attributeValue = (String) attribute.getValue().getJooValue();
        if (attribute.isNamespacePrefixDefinition()) {
          xmlNamespaces.put(attribute.getLocalName(), attributeValue);
        } else if (attribute.isDefaultNamespaceDefinition()) {
          defaultNamespace = attributeValue;
        } else if (attribute.isId()) {
          id = attribute.getValue();
        }
      }
    }
    defaultXmlNamespace = defaultNamespace;
  }

  @Override
  public JooSymbol getSymbol() {
    return lt;
  }

  public JooSymbol getClosingSymbol() {
    return gt;
  }

  @Override
  public List<? extends AstNode> getChildren() {
    return Collections.emptyList();
  }

  @Override
  public void scope(Scope scope) {

  }

  @Override
  public void analyze(AstNode parentNode) {

  }

  @Override
  public AstNode getParentNode() {
    return null;
  }

  @Override
  public void visit(AstVisitor visitor) throws IOException {

  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append(lt.getSourceCode());
    if(tagName instanceof NamespacedIde) {
      NamespacedIde namespacedIde = (NamespacedIde) tagName;
      builder.append(namespacedIde.getNamespace().getSymbol().getSourceCode());
      builder.append(namespacedIde.getSymNamespaceSep().getSourceCode());
    }
    builder.append(tagName.getIde().getSourceCode());
    if (attributes != null) {
      for (XmlAttribute attribute : attributes) {
        builder.append(attribute);
      }
    }
    builder.append(gt.getSourceCode());
    return builder.toString();
  }

  public String getLocalName() {
    return tagName.getIde().getText();
  }

  /**
   * @see Node#getPrefix()
   */
  public String getPrefix() {
    if(tagName instanceof NamespacedIde) {
      return ((NamespacedIde)tagName).getNamespace().getName();
    }
    return null;
  }

  XmlAttribute getAttribute(final String name) {
    return attributes.stream().filter(input -> null != input && Objects.equals(name, input.getSymbol().getText())).findFirst().orElse(null);
  }

  XmlAttribute getAttribute(final String namespaceUri, final String localName) {
    return attributes.stream().filter(input -> null != input && Objects.equals(namespaceUri, xmlElement.getNamespaceUri(input.getPrefix())) && Objects.equals(localName, input.getLocalName())).findFirst().orElse(null);
  }

  public List<XmlAttribute> getAttributes() {
    return attributes;
  }

  String getNamespaceUri(@Nullable String prefix) {
    if(null != prefix) {
      return xmlNamespaces.get(prefix);
    }
    return defaultXmlNamespace;
  }

  void setElement(XmlElement xmlElement) {
    this.xmlElement = xmlElement;
  }

  public JooSymbol getIdSymbol() {
    return id;
  }
}
