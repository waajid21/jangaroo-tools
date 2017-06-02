Ext.define("package2.TestEventListener", function(TestEventListener) {/*package package2 {
import ext.Panel;
import ext.events.PanelEvent;

public class TestEventListener {

  private var panel:Panel =*/function panel_(){return AS3.initVar(this,"panel$1", AS3.cast(ext.Panel,{}));}/*;
  [ArrayElementType("ext.Panel")]
  private var panels:Array =*/function panels_(){return AS3.initVar(this,"panels$1", []);}/*;

  [ArrayElementType("ext.Panel")]
  private*/ function getPanels()/*:Array*/ {
    return this.panels$1;
  }/*

  public*/ function TestEventListener$() {var this$=this;this.panel$1;this.panels$1;
    AS3.setBindable(this.panel$1,"title" , "not yet clicked.");
    AS3.addEventListener(panel, ext.events.PanelEvent,"FLOPS", function(event/*:PanelEvent*/)/*:void*/ {
      AS3.setBindable(this$.getThis().getPanel(),"title" , "clicked!");
      AS3.setBindable(this$.panel$1.layout.getOwner(),"title" , "clicked!");
      this$.panels$1.push(this$.panel$1);
      AS3.setBindable(this$.getPanels$1()[0],"title" , "yes, clicked!");
    } );
  }/*

  public*/ function getThis()/*:TestEventListener*/ {
    return this;
  }/*

  public*/ function getPanel()/*:Panel*/ {
    return this.panel$1;
  }/*
}
}

============================================== Jangaroo part ==============================================*/
    return {
      getPanels$1: getPanels,
      constructor: TestEventListener$,
      getThis: getThis,
      getPanel: getPanel,
      __accessors__: {
        panel$1: {
          get: panel_,
          set: function(value){this.panel$1;return this.panel$1=value;},
          configurable: true
        },
        panels$1: {
          get: panels_,
          set: function(value){this.panels$1;return this.panels$1=value;},
          configurable: true
        }
      },
      uses: [
        "ext.Panel",
        "ext.events.PanelEvent"
      ]
    };
});
