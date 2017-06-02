Ext.define("package1.mxml.ScriptCdataMxmlClass", function(ScriptCdataMxmlClass) {/*package package1.mxml{
import package1.*;
import package1.mxml.SimpleInterface;
import net.jangaroo.ext.Exml;
public class ScriptCdataMxmlClass extends ConfigClass implements package1.mxml.SimpleInterface{

    import package1.someOtherPackage.SomeOtherClass;

    private var field1:SomeOtherClass = null;
    protected var field2:Vector.<String> =*/function field2_(){return AS3.initVar(this,"field2",/* new <String>*/["a", "b"]);}/*;
    public var field3:Vector.<int> =*/function field3_(){return AS3.initVar(this,"field3",/* new <int>*/[1, 2, 3]);}/*;

    public*/ function doIt(/*...values*/)/*:void*/ {var values=Array.prototype.slice.call(arguments);
      for (var v/*:Object*/ in values) {
        throw "cannot do it with " + v;
      }
    }/*public*/function ScriptCdataMxmlClass$(config/*:ScriptCdataMxmlClass=null*/){package1.ConfigClass.prototype.constructor.call(this);this.field2;this.field3;if(arguments.length<=0)config=null; net.jangaroo.ext.Exml.apply(this,{
             foo: "bar"});
}/*}}

============================================== Jangaroo part ==============================================*/
    return {
      extend: "package1.ConfigClass",
      mixins: ["package1.mxml.SimpleInterface"],
      field1$3: null,
      doIt: doIt,
      constructor: ScriptCdataMxmlClass$,
      __accessors__: {
        field2: {
          get: field2_,
          set: function(value){this.field2;return this.field2=value;},
          configurable: true
        },
        field3: {
          get: field3_,
          set: function(value){this.field3;return this.field3=value;},
          configurable: true
        }
      },
      requires: [
        "package1.ConfigClass",
        "package1.mxml.SimpleInterface"
      ],
      uses: ["net.jangaroo.ext.Exml"]
    };
});
