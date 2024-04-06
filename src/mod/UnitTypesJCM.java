package mod;

import arc.func.Prov;
import arc.graphics.Color;
import arc.struct.Seq;
import mindustry.content.UnitTypes;
import mindustry.ctype.ContentType;
import mindustry.entities.bullet.LaserBulletType;
import mindustry.game.Team;
import mindustry.gen.Groups;
import mindustry.gen.Unit;
import mindustry.type.UnitType;
import mindustry.type.Weapon;

public class UnitTypesJCM extends UnitTypes {
    public static UnitType test;

    public static void load(){
        test = new UnitType("test"){{
            range = 15*8;
            weapons.add(
                    new Weapon("test-inv-laser"){{
                        bullet = new LaserBulletType(30){{
                            length = range;
                            width = 1.5f*8f;
                            colors = new Color[]{
                                    Color.valueOf("#0f0f4a"),
                                    Color.black,
                                    Color.valueOf("#0f0f4a")
                            };
                        }};
                        reload = 60;
                    }}
            );
            hidden = true;
        }};
    }
}

