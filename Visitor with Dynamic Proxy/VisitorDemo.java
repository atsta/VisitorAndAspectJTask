import java.util.List;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface CarElement {
    void accept(CarElementVisitor visitor);
}

interface CarElementVisitor {
    void visit(Body body);
    void visit(Car car);
    void visit(Engine engine);
    void visit(Wheel wheel);
}

class Wheel implements CarElement {
    private final String name;

    public Wheel(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(CarElementVisitor visitor) {
        visitor.visit(this);
    }
}

class Body implements CarElement {
    @Override
    public void accept(CarElementVisitor visitor) {
        visitor.visit(this);
    }
}

class Engine implements CarElement {
    @Override
    public void accept(CarElementVisitor visitor) {
        visitor.visit(this);
    }
}

class Car implements CarElement {
    private final List<CarElement> elements;

    public Car() {
        this.elements = List.of(
                new Wheel("front left"), new Wheel("front right"),
                new Wheel("back left"), new Wheel("back right"),
                new Body(), new Engine()
        );
    }

    @Override
    public void accept(CarElementVisitor visitor) {
        for (CarElement element : elements) {
            element.accept(visitor);
        }
        visitor.visit(this);
    }
}

class VisitorHandler implements InvocationHandler {
    private Car a;
    public VisitorHandler(Car a) { this.a = a; }

    public Object invoke(Object proxy, Method method, Object[] fargs) throws Throwable {
        if (fargs[0] instanceof Wheel) {
            System.out.println("Kicking my " + ((Wheel) fargs[0]).getName() + " wheel");
        } else if (fargs[0] instanceof Body){
            System.out.println("Moving my body");
        } else if (fargs[0] instanceof Engine) {
            System.out.println("Starting my engine");
        } else if (fargs[0] instanceof Car) {
            System.out.println("Starting my car");
        } else {
            System.err.println("Unsupported class instance");
        }
        return null;
    }
}

public class VisitorDemo {
    public static void main(final String[] args) {
        Car car = new Car();
        //implement visitor with dynamic proxy

        Class[] interfaces = new Class[1];
        interfaces[0] = CarElementVisitor.class;
        CarElementVisitor proxy = (CarElementVisitor) Proxy.newProxyInstance(CarElementVisitor.class.getClassLoader(),
                interfaces, new VisitorHandler(car));

        car.accept(proxy);
    }
}