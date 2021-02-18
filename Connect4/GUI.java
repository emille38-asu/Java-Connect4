/**
 * This program is the GUI for a connect 4 game.
 * 8 Hours
 * 
 * @Edward_Miller
 * @version_1.0
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Connect4GUI extends Application 
{

private boolean playerX = true;
private Disc[][] grid = new Disc[COLUMNS][ROWS];
private Pane discRoot = new Pane();

private static final int COLUMNS=7;
private static final int BOARD=80;
private static final int ROWS=6;

public Parent draw() 
{
Pane root = new Pane();
root.getChildren().add(discRoot);
Shape gridShape = makeGrid();
root.getChildren().add(gridShape);
root.getChildren().addAll(makecolumns());

return root;
}

private Shape drawGrid()
{
Shape shape = new Rectangle((COLUMNS +1)* BOARD, (ROWS + 1)* BOARD);
for(int y=0 ;y< ROWS; y++)
{
for(int x=0 ;x< COLUMNS; x++)
{
Circle circle =new Circle (BOARD/2);
circle.setCenterX(BOARD/2);
circle.setCenterY(BOARD/2);
circle.setTranslateX(x * (BOARD +5)+ BOARD/4);
circle.setTranslateY(y * (BOARD +5)+ BOARD/4);
shape = Shape.subtract(shape, circle);
}
}
Light.Distant light = new Light.Distant();
light.setAzimuth(45.0);
light.setElevation(30.0);
Lighting lighting =new Lighting();
lighting.setLight(light);
lighting.setSurfaceScale(5.0);
shape.setFill(Color.BLACK);
shape.setEffect(lighting);
return shape;
}
private List<Rectangle> makecolumns()
{
List<Rectangle> list = new ArrayList<>();
for(int x=0 ;x< COLUMNS; x++){
Rectangle rect = new Rectangle((BOARD/2), (ROWS + 1)* BOARD);
rect.setTranslateX(x * (BOARD +5)+ BOARD/4);
rect.setFill(Color.TRANSPARENT);
rect.setOnMouseDragEntered(e -> rect.setFill(Color.BLUE));
rect.setOnMouseDragExited(e -> rect.setFill(Color.TRANSPARENT));
final int column =x;
rect.setOnMouseClicked(e -> placeDisc(new Disc (playerX), column ));
list.add(rect);
}
return list;
}
private void placeDisc(Disc disc,int column)
{
int row = ROWS -1;
do{
if (!getDisc(column,row).isPresent())
break;
row--;
}while (row>=0);
if (row <0)
return;
grid[column][row]= disc;
discRoot.getChildren().add(disc);
disc.setTranslateX(column * (BOARD + 5) + BOARD/4);
final int currentRow=row;
TranslateTransition animation = new TranslateTransition (Duration.seconds(0.5),disc);
animation.setToY(row * (BOARD + 5) + BOARD/4);
animation.setOnFinished(e ->{
if (gameEnded(column, currentRow))
{
gameOver();
}
playerX = !playerX;
});
animation.play();
}
private boolean gameEnded(int column, int row) 
{
List<Point2D> vertical = IntStream.rangeClosed(row -3 , row +3)
.mapToObj(r -> new Point2D(column,r))
.collect(Collectors.toList());
List<Point2D> horizontal = IntStream.rangeClosed(column -3 , column +3)
.mapToObj(c -> new Point2D(c ,row))
.collect(Collectors.toList());
Point2D topLeft = new Point2D(column -3 , row -3);
List<Point2D> diagonal1 = IntStream.rangeClosed(0,6)
.mapToObj(i -> topLeft.add(i ,i))
.collect(Collectors.toList());
Point2D botLeft = new Point2D(column -3 , row +3);
List<Point2D> diagonal2 = IntStream.rangeClosed(0,6)
.mapToObj(i -> botLeft.add(i ,-i))
.collect(Collectors.toList());
return checkRange(vertical) || checkRange(horizontal)||checkRange(diagonal1)||checkRange(diagonal2);
}
private boolean checkRange(List<Point2D> points)
{
int chain = 0;
for (Point2D p: points)
{
int column = (int) p.getX();
int row = (int) p.getY();
Disc disc = getDisc(column , row).orElse(new Disc(!playerX));
if (disc.red==playerX)
{
chain++;
if (chain ==4)
{
return true;
}
} else 
{
chain=0;
}
}
return false;
}
private void gameOver()
{
System.out.println("winner:"+ (playerX ? "RED":"player0"));

}
private Optional<Disc>getDisc(int column, int row)
{
if (column <0||column >=COLUMNS || row <0 || row>=ROWS)
return Optional.empty();
return Optional.ofNullable(grid[column][row]);
}
private static class Disc extends Circle{
private final boolean red;
public Disc(boolean red)
{
super(BOARD/2, red ? Color.RED : Color.YELLOW);
this.red = red;
setCenterX(BOARD/2);
setCenterY(BOARD/2);
}
}
@Override
public void start(Stage stage) throws Exception 
{
stage.setScene(new Scene(createContent()));
stage.show();
}

public static void main(String[] args) 
{
launch(args);
}
}