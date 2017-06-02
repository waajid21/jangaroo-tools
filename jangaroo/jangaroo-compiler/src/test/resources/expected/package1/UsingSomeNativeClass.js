Ext.define("package1.UsingSomeNativeClass", function(UsingSomeNativeClass) {/*package package1 {
import package1.someOtherPackage.SomeNativeClass;

/**
 * This is an example of a class using a "native" class.
 * /
public class UsingSomeNativeClass {

  public var someNative:package1.SomeNativeClass =*/function someNative_(){return AS3.initVar(this,"someNative", new SomeNativeClass());}/*;
  public var someOtherNative:SomeOtherNativeClass =*/function someOtherNative_(){return AS3.initVar(this,"someOtherNative", new SomeOtherNativeClass());}/*;
  public native function get someNative2():package1.SomeNativeClass;

  public*/ function UsingSomeNativeClass$() {var this$=this;this.someNative;this.someOtherNative;
    new package1.someOtherPackage.SomeNativeClass();
    AS3.setBindable(this.someNative,"baz" , "foo");
    AS3.setBindable(this.someNative2,"baz" , "foo");
    var local = function()/*:void*/ {
      var test/*:String*/ = AS3.getBindable(this$.someNative2,"baz");
    };
    var foo = AS3.getBindable(this,"someNativeAccessor");
    var bar = AS3.getBindable(this,"anotherNativeAccessor");
  }/*

  [Bindable]
  public*/ function get$someNativeAccessor()/*:package1.SomeNativeClass*/ {
    return this.someNative;
  }/*

  [Bindable]
  public*/ function get$anotherNativeAccessor()/*:package1.SomeNativeClass*/ {
    return this.someNative;
  }/*

  [Bindable]
  public*/ function get$monkey()/*:Boolean*/ {
    return false;
  }/*

  [Bindable]
  public*/ function set$monkey(value/*:Boolean*/)/*:void*/ {
  }/*
}
}

============================================== Jangaroo part ==============================================*/
    return {
      constructor: UsingSomeNativeClass$,
      getSomeNativeAccessor: get$someNativeAccessor,
      getAnotherNativeAccessor: get$anotherNativeAccessor,
      getMonkey: get$monkey,
      setMonkey: set$monkey,
      config: {
        someNativeAccessor: undefined,
        anotherNativeAccessor: undefined,
        monkey: undefined
      },
      __accessors__: {
        someNative: {
          get: someNative_,
          set: function(value){this.someNative;return this.someNative=value;},
          configurable: true
        },
        someOtherNative: {
          get: someOtherNative_,
          set: function(value){this.someOtherNative;return this.someOtherNative=value;},
          configurable: true
        }
      },
      uses: [
        "SomeOtherNativeClass",
        "package1.someOtherPackage.SomeNativeClass"
      ]
    };
});
