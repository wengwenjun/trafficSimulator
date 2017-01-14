# trafficSimulator
A traffic simulator model controlled by UI Panel
run ant compile
or run in eclipse

Design Pattern:

1. Singleton 
Specific problem solved: It makes an object in the class become the only and unique instance in the traffic show class. There is only one instance in the traffic show class. This will avoid logic error because of multiply instances.
Example:
public TrafficShow(){
super();
if(singleton==null)
singleton=this; ……}

2. MVC lite
Specific problem solved: separate business logic and view, using controller to keep them in the same path; I put all logic of building car, traffic and road in model package, put all class to visualize in UI package. 
Example: model package, main package, ui package

3. Static class
Specific problem solved: a set of global variables and functions. So other class can call the variables or functions. 
Example:
public class Parameters {	
public static int timeStep=10;//in million seconds
public static double runTime=10;//in seconds ……}

4. Static Factory
Specific problem solved: I can create the instance directly from factory class. Since each factory has name, it clarify the codes. So I create each factory for car, road and traffic light. Then I need use the object, I call the factory class and use the method of this class. I do not create a new object again.
Example:
public class CarImageFactory {
public static ImageIcon MakeCarImage(Direction direction){
return new CarImage().CreateImage(direction);	}}
final class Carobj implements Car{
_image=CarImageFactory.MakeCarImage(direction);……}}

5. Builder
Specific problem solved: separate the build code and express code. Each build part is independent, easy to be edited.
Example:
UIFormBuilder and UIForm Class   
StringBuffer

6. Strategy 
Specific problem solved: do not need rewrite the same strategy for several times. Put strategy in interface, when I need use it, I implement this interface. So using same strategy, I can realize different functions. For example: there is a interface--- image, which getting the direction of image. Then CarImage, RoadImage, and TrafficLightImage all implement this interface and create give different direction of image. Then context can switch them to implement different functions.
Example
public interface Image {
public ImageIcon CreateImage(Direction direction);//CreateImage
}
final class CarImage implements Image{
public ImageIcon CreateImage(Direction direction) {…}}
final class RoadImage implements Image{
public ImageIcon CreateImage(Direction direction) {…}}
final class TrafficLightImage implements Image{
public ImageIcon CreateImage(Direction direction){…}}

7. Composite 
Specific problem solved: Make collections of objects have the same interface as single object. Car, Road and Traffic all has the same interface --- model. In traffic show class, I add these three object to be a single object. Both for the control class, it contains traffic show object, UIForm object and UIMenu.
Example:
public interface Car extends Model{…}}
public interface Road extends Model{…}}
public interface TrafficLight extends Model{…}}
public   void  paintComponent(Graphics page) {		
super.paintComponent(page);
paintArrayList(road,this,page);
……
Iterator<ArrayList<Car>> it=cars.iterator();
……
paintArrayList(light,this,page);
……
}
//
Public void Control{
…
New TrafficShow();
New UIForm();
…
}
