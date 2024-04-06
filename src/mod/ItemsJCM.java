package mod;

import arc.graphics.Color;
import arc.util.Log;
import mindustry.content.Items;
import mindustry.content.TechTree;
import mindustry.type.Item;
import mindustry.type.ItemStack;

public class ItemsJCM extends Items {

    public static Item niotium;

    public static void load(){
        Log.info("Loading JCM items");
        UnusableTrash.loadWarnOnError(
                ()->niotium=new Item("niotium", Color.valueOf("#5000a2")){{
            charge = 0.6f;
            hardness = 4;
            explosiveness = 0.2f;
            radioactivity = 1.75f;
            techNode = new TechTree.TechNode(
                    thorium.techNode,
                    this,
                    new ItemStack[]{}
            );
        }}, "notium");



    }


}
