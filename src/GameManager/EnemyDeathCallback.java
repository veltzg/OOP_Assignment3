package GameManager;

import Tiles.*;
import java.util.*;

public interface EnemyDeathCallback {
    void call(Enemy e);
    //to Call enemyIsDead
}
