package package1.someOtherPackage {
import package1.NoPrimitiveInit;
import package1.ParameterInitializers;

public class ImportReduction {
  public var initializers:ParameterInitializers;

  public var fun:Function;

  public var vec:Vector.<NoPrimitiveInit>;

  public native function call(callback:Function = null):void;
}
}