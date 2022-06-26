package GameManager;

import Tiles.*;
import java.util.*;

public interface EnemyDeathCallback {
    void call(Enemy e, Player p);
    //to Call enemyIsDead
}
