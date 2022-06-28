package BusinessLayer.Tiles;

public class Wall extends Tile{
    private static final char wallCharTile = '#';

    public Wall(){super(wallCharTile) ;}

    public Wall(Position p) {
        super(wallCharTile);
        this.position = p; }


    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }
}
