package net.jangaroo.jooc.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A model of a field of an ActionScript class.
 */
public class AnnotationModel extends DocumentedModel {

  private List<AnnotationPropertyModel> properties = new ArrayList<AnnotationPropertyModel>();

  public AnnotationModel() {
  }

  public AnnotationModel(String name) {
    super(name);
  }

  public AnnotationModel(String name, AnnotationPropertyModel... properties) {
    super(name);
    this.properties = Arrays.asList(properties);
  }

  public List<AnnotationPropertyModel> getProperties() {
    return Collections.unmodifiableList(properties);
  }

  public void setProperties(List<AnnotationPropertyModel> properties) {
    this.properties = properties;
  }

  public void addProperty(AnnotationPropertyModel annotationProperty) {
    properties.add(annotationProperty);
  }

  public void visit(ModelVisitor visitor) {
    visitor.visitAnnotation(this);
  }
}
