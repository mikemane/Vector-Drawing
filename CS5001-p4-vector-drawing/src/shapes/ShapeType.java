package shapes;

/**
 * Created by un4 on 04/11/16.
 */
public enum ShapeType {
    LINE, ELLIPSE, RECTANGLE, HEXAGON, PATH;

    public static ShapeType getTypeFromString(String value) {
        switch (value.toLowerCase()) {
            case "hexagon":
                return ShapeType.HEXAGON;
            case "rectangle":
                return RECTANGLE;
            case "ellipse":
                return ELLIPSE;
            case "line":
                return LINE;
            default:
                return null;
        }
    }
}
