package horsman14.v1ch11.runtimeAnnotations;

@ToString
public record Rectangle(
   @ToString(includeName=false) Point topLeft,
   @ToString int width,
   @ToString int height) 
{}
