Ext.define("package2.TestPrototypeConstants", function(TestPrototypeConstants) {/*package package2 {

public class TestPrototypeConstants {

  public const foo:String = "FOO";
  public const bar:Object =*/function bar_(){return AS3.initConst(this,"bar", {});}/*;

}*/function TestPrototypeConstants$() {this.bar;}/*
}

============================================== Jangaroo part ==============================================*/
    return {
      foo: "FOO",
      constructor: TestPrototypeConstants$,
      __accessors__: {bar: {
        get: bar_,
        configurable: true
      }}
    };
});
