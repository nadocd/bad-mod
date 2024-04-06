package mod;

import arc.graphics.Color;
import arc.graphics.g2d.Lines;
import arc.math.Mathf;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.entities.Effect;
import mindustry.type.StatusEffect;

public class StatusEffectsJCM extends StatusEffects {
    public static StatusEffect overpowered;

    public static void load(){
        UnusableTrash.loadWarnOnError(
                ()-> overpowered = new StatusEffect("overpowered"){{
                    reloadMultiplier = 5f;
                    color = Color.valueOf("#30e368");
                    outline = true;
                    buildSpeedMultiplier = 5f;
                    effect= new Effect(10,ec -> {
                        ec.color = Color.valueOf("30e368");
                        float dx = Mathf.random() - .5f;
                        float dy = Mathf.random() - .5f;
                        Lines.circle(ec.x+dx,ec.y+dy,1);
                    });
        }}, "overpowered");
    }
}
