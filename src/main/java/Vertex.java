import java.util.ArrayList;
import java.util.List;

class Vertex {
    String name;
    List<Vertex> neighbors = new ArrayList<>();
    Vertex parent = null;
    int discoveryTime = 0;
    int finishTime = 0;
    Main.Color color = Main.Color.WHITE;

    Vertex(String name) {
        this.name = name;
    }
}
