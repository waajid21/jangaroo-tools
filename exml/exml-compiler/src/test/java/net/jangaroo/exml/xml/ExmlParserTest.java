package net.jangaroo.exml.xml;

import net.jangaroo.exml.json.JsonArray;
import net.jangaroo.exml.json.JsonObject;
import net.jangaroo.exml.model.ConfigClassRegistry;
import net.jangaroo.exml.model.ExmlModel;
import net.jangaroo.jooc.input.FileInputSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;

public class ExmlParserTest {
  private FileInputSource sourcePathInputSource;
  private FileInputSource classpathInputSource;
  private ConfigClassRegistry registry;

  @Rule
  public TemporaryFolder outputFolder = new TemporaryFolder();

  @Before
  public void setUp() throws Exception {
  }

  private void setUp(String configClassPackage) throws Exception {
    sourcePathInputSource = new FileInputSource(new File(getClass().getResource("/").toURI()));
    classpathInputSource = new FileInputSource(new File(getClass().getResource("/").toURI()));
    registry = new ConfigClassRegistry(sourcePathInputSource, classpathInputSource, configClassPackage, outputFolder.getRoot());
  }

  @Test
  public void testParseAllElements() throws Exception {
    setUp("exmlparser.config");
    ExmlParser exmlParser = new ExmlParser(registry);

    InputStream inputStream = getClass().getResourceAsStream("/exmlparser/AllElements.exml");
    ExmlModel model = exmlParser.parse(inputStream);
    Assert.assertEquals(new HashSet<String>(Arrays.asList("ext.config.panel", "ext.config.button", "ext.config.menuitem", "ext.MessageBox")),
            model.getImports());
    Assert.assertEquals("ext.config.panel", model.getParentClassName());

    JsonObject expectedJsonObject = new JsonObject(
            "layout", "{config.myLayout}",
            "title", "I am a panel",
            "defaults", new JsonObject("layout","border"),
            "layoutConfig", new JsonObject(
                    "bla", "blub",
                    "anchor", new JsonObject("style", "test"),
                    "border", new JsonObject("type", "solid")
            ),
            "items", new JsonArray(
                    new JsonObject(
                            "text", "Save"
                    ).settingWrapperClass("ext.config.button"),
                    "{{xtype: \"editortreepanel\"}}"
            ),
            "menu", new JsonArray(
                    new JsonObject(
                            "text", "juhu1"
                    ).settingWrapperClass("ext.config.menuitem"),
                    new JsonObject(
                            "text", "juhu2"
                    ).settingWrapperClass("ext.config.menuitem"),
                    new JsonObject(
                            "text", "juhu3"
                    ).settingWrapperClass("ext.config.menuitem")
            ),
            "tools", new JsonArray(
                     new JsonObject(
                         "handler", "{function(x){return ''+x;}}",
                         "id", "gear"
                     )
            )
    );
    System.out.println(model.getJsonObject().toString(2));
    Assert.assertEquals(expectedJsonObject.toString(2), model.getJsonObject().toString(2));
  }

  @Test
  public void testParseTestNumber() throws Exception{
    setUp("exmlparser.config");
    ExmlParser exmlParser = new ExmlParser(registry);

    InputStream inputStream = getClass().getResourceAsStream("/exmlparser/TestNumber.exml");
    ExmlModel model = exmlParser.parse(inputStream);
    Assert.assertEquals("ext.config.panel", model.getParentClassName());

    JsonObject expectedJsonObject = new JsonObject(
            "items", new JsonArray(
                    new JsonObject(
                            "id", "foo",
                             "x", 100
                    ).settingWrapperClass("ext.config.panel"),
                    new JsonObject(
                            "id", "foo",
                            "x", 1.5
                    ).settingWrapperClass("ext.config.panel"),
                    new JsonObject(
                            "id", "foo",
                            "x", 1.0
                    ).settingWrapperClass("ext.config.panel"),
                    new JsonObject(
                            "id", "foo",
                            "x", -1.5
                    ).settingWrapperClass("ext.config.panel"),
                    new JsonObject(
                            "id", "foo",
                            "x", 3.0
                    ).settingWrapperClass("ext.config.panel")
            )
    );
    System.out.println(model.getJsonObject().toString(2));
    Assert.assertEquals(expectedJsonObject.toString(2), model.getJsonObject().toString(2));
  }

  @Test
  public void testParseTestTrueFalse() throws Exception{
    setUp("exmlparser.config");
    ExmlParser exmlParser = new ExmlParser(registry);

    InputStream inputStream = getClass().getResourceAsStream("/exmlparser/TestTrueFalse.exml");
    ExmlModel model = exmlParser.parse(inputStream);
    Assert.assertEquals("ext.config.panel", model.getParentClassName());

    JsonObject expectedJsonObject = new JsonObject(
            "items", new JsonArray(
                    new JsonObject(
                            "id", "foo",
                             "visible", true
                    ).settingWrapperClass("ext.config.panel"),
                    new JsonObject(
                            "id", "foo",
                            "visible", false
                    ).settingWrapperClass("ext.config.panel"),
                    new JsonObject(
                            "id", "foo",
                            "visible", true
                    ).settingWrapperClass("ext.config.panel"),
                    new JsonObject(
                            "id", "foo",
                            "visible", false
                    ).settingWrapperClass("ext.config.panel")
            )
    );
    System.out.println(model.getJsonObject().toString(2));
    Assert.assertEquals(expectedJsonObject.toString(2), model.getJsonObject().toString(2));
  }

  @Test
  public void testInheritProperties() throws Exception{
    setUp("testNamespace.config");
    ExmlParser exmlParser = new ExmlParser(registry);

    InputStream inputStream = getClass().getResourceAsStream("/testPackage/TestComponent2.exml");
    ExmlModel model = exmlParser.parse(inputStream);
    Assert.assertEquals("testNamespace.config.TestComponent", model.getParentClassName());

    JsonObject expectedJsonObject = new JsonObject(
            "items", new JsonArray(
                    new JsonObject(
                            "propertyThree", "3"
                    ).settingWrapperClass("testNamespace.config.TestComponent2")
            )
    );
    System.out.println(model.getJsonObject().toString(2));
    Assert.assertEquals(expectedJsonObject.toString(2), model.getJsonObject().toString(2));
  }
}